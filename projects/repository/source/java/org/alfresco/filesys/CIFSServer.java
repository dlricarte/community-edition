/*
 * Copyright (C) 2005 Alfresco, Inc.
 *
 * Licensed under the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version
 * 2.1 of the License, or (at your option) any later version.
 * You may obtain a copy of the License at
 *
 *     http://www.gnu.org/licenses/lgpl.txt
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package org.alfresco.filesys;

import java.io.IOException;
import java.net.SocketException;

import org.alfresco.config.source.ClassPathConfigSource;
import org.alfresco.config.xml.XMLConfigService;
import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.filesys.netbios.server.NetBIOSNameServer;
import org.alfresco.filesys.netbios.win32.Win32NetBIOS;
import org.alfresco.filesys.server.NetworkServer;
import org.alfresco.filesys.server.config.ServerConfiguration;
import org.alfresco.filesys.server.filesys.DiskInterface;
import org.alfresco.filesys.smb.server.SMBServer;
import org.alfresco.service.ServiceRegistry;
import org.apache.log4j.Logger;

/**
 * CIFS Server Class
 * 
 * <p>
 * Create and start the various server components required to run the CIFS
 * server.
 * 
 * @author GKSpencer
 */
public class CIFSServer
{
    private static final Logger logger = Logger.getLogger("org.alfresco.smb.server");

    // Filesystem configuration

    private ServiceRegistry serviceRegistry;
    private String configLocation;
    private DiskInterface diskInterface;
    private ServerConfiguration filesysConfig;

    /**
     * @param serviceRegistry connects to the repository
     * @param configService
     */
    public CIFSServer(ServiceRegistry serviceRegistry, String configLocation, DiskInterface diskInterface)
    {
        this.serviceRegistry = serviceRegistry;
        this.configLocation = configLocation;
        this.diskInterface = diskInterface;
    }

    /**
     * Start the CIFS server components
     * 
     * @exception SocketException
     *                If a network error occurs
     * @exception IOException
     *                If an I/O error occurs
     */
    public final void startServer() throws SocketException, IOException
    {
        try
        {
            ClassPathConfigSource classPathConfigSource = new ClassPathConfigSource(configLocation);
            XMLConfigService xmlConfigService = new XMLConfigService(classPathConfigSource);
            xmlConfigService.init();
            filesysConfig = new ServerConfiguration(serviceRegistry, xmlConfigService, diskInterface);
            filesysConfig.init();
        
            // Load the Win32 NetBIOS library
            //
            // For some strange reason the native code loadLibrary() call hangs if
            // done later by the SMBServer.
            // Forcing the Win32NetBIOS class to load here and run the static
            // initializer fixes the problem.

            if (filesysConfig.hasWin32NetBIOS())
            {

                // Try and load the Win32 NetBIOS JNI code, if it fails switch off Win32 NetBIOS support

                try
                {

                    // Get a list of available NetBIOS LANAs

                    Win32NetBIOS.LanaEnum();
                }
                catch (UnsatisfiedLinkError ex)
                {

                    // Switch off Win32 NetBIOS support

                    filesysConfig.setWin32NetBIOS(false);

                    // Log the error

                    logger.error("Win32 NetBIOS support not available, required DLL not found");
                }
            }
    
            // Create the SMB server and NetBIOS name server, if enabled
            if (filesysConfig.isSMBServerEnabled())
            {
    
                // Create the NetBIOS name server if NetBIOS SMB is enabled
                if (filesysConfig.hasNetBIOSSMB())
                    filesysConfig.addServer(new NetBIOSNameServer(serviceRegistry, filesysConfig));
    
                // Create the SMB server
                filesysConfig.addServer(new SMBServer(serviceRegistry, filesysConfig));
            }
    
            // Start the configured servers
            for (int i = 0; i < filesysConfig.numberOfServers(); i++)
            {
                // Get the current server
                NetworkServer server = filesysConfig.getServer(i);
    
                if (logger.isInfoEnabled())
                    logger.info("Starting server " + server.getProtocolName() + " ...");
    
                // Start the server
                filesysConfig.getServer(i).startServer();
            }
        }
        catch (Throwable e)
        {
            filesysConfig = null;
            throw new AlfrescoRuntimeException("Failed to start CIFS Server", e);
        }
    }

    /**
     * Stop the CIFS server components
     */
    public final void stopServer()
    {
        if (filesysConfig == null)
        {
            // initialisation failed
            return;
        }
        // Shutdown the servers
        for (int i = 0; i < filesysConfig.numberOfServers(); i++)
        {
            // Get the current server
            NetworkServer server = filesysConfig.getServer(i);

            if (logger.isInfoEnabled())
                logger.info("Shutting server " + server.getProtocolName() + " ...");

            // Start the server
            filesysConfig.getServer(i).shutdownServer(false);
        }
    }
}
