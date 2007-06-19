/*
 * Copyright (C) 2005-2007 Alfresco Software Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.

 * As a special exception to the terms and conditions of version 2.0 of 
 * the GPL, you may redistribute this Program in connection with Free/Libre 
 * and Open Source Software ("FLOSS") applications as described in Alfresco's 
 * FLOSS exception.  You should have recieved a copy of the text describing 
 * the FLOSS exception, and it is also available here: 
 * http://www.alfresco.com/legal/licensing"
 */
package org.alfresco.model;

import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

/**
 * @author Gavin Cornwell
 */
public interface WCMWorkflowModel
{
    // submission workflow properties and aspects
    static final QName PROP_FROM_PATH = QName.createQName(NamespaceService.WCMWF_MODEL_1_0_URI, "fromPath");
    static final QName PROP_LABEL = QName.createQName(NamespaceService.WCMWF_MODEL_1_0_URI, "label");
    static final QName PROP_LAUNCH_DATE = QName.createQName(NamespaceService.WCMWF_MODEL_1_0_URI, "launchDate");
    static final QName PROP_VALIDATE_LINKS = QName.createQName(NamespaceService.WCMWF_MODEL_1_0_URI, "validateLinks");
    static final QName PROP_WEBAPP = QName.createQName(NamespaceService.WCMWF_MODEL_1_0_URI, "webapp");
    static final QName ASSOC_WEBPROJECT = QName.createQName(NamespaceService.WCMWF_MODEL_1_0_URI, "webproject");
}
