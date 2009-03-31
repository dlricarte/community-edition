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

package org.alfresco.repo.invitation;

import org.alfresco.service.namespace.QName;

/**
 * Workflow Model for a Nominated Invitation
 */
public interface WorkflowModelNominatedInvitation
{
    // namespace
    public static final String NAMESPACE_URI = "http://www.alfresco.org/model/workflow/invite/nominated/1.0";

    // process name
    public static final QName WF_PROCESS_INVITE = QName.createQName(NAMESPACE_URI, "invitation-nominated");

    // workflow definition name
    public static final String WORKFLOW_DEFINITION_NAME = "jbpm$inwf:invitation-nominated";
    
    // tasks
    public static final QName WF_INVITE_TASK_INVITE_TO_SITE = QName.createQName(NAMESPACE_URI, "inviteToSiteTask");
    public static final QName WF_INVITE_TASK_INVITE_PENDING = QName.createQName(NAMESPACE_URI, "invitePendingTask");
    public static final QName WF_TASK_ACCEPT_INVITE = QName.createQName(NAMESPACE_URI, "acceptInviteTask");
    public static final QName WF_TASK_REJECT_INVITE = QName.createQName(NAMESPACE_URI, "rejectInviteTask");
    
    // transition names
    public static final String WF_TRANSITION_SEND_INVITE = "sendInvite";
    public static final String WF_TRANSITION_ACCEPT = "accept";
    public static final String WF_TRANSITION_REJECT = "reject";
    public static final String WF_TRANSITION_CANCEL = "cancel";
    public static final String WF_TRANSITION_ACCEPT_INVITE_END = "end";
    public static final String WF_TRANSITION_REJECT_INVITE_END = "end";
    
    // workflow properties
    public static final QName WF_PROP_SERVER_PATH = QName.createQName(NAMESPACE_URI, "serverPath");
    public static final QName WF_PROP_ACCEPT_URL = QName.createQName(NAMESPACE_URI, "acceptUrl");
    public static final QName WF_PROP_REJECT_URL = QName.createQName(NAMESPACE_URI, "rejectUrl");
    public static final QName WF_PROP_INVITE_TICKET = QName.createQName(NAMESPACE_URI, "inviteTicket");
    public static final QName WF_PROP_INVITER_USER_NAME = QName.createQName(NAMESPACE_URI, "inviterUserName");
    public static final QName WF_PROP_INVITEE_USER_NAME = QName.createQName(NAMESPACE_URI, "inviteeUserName");
    public static final QName WF_PROP_INVITEE_EMAIL = QName.createQName(NAMESPACE_URI, "inviteeEmail");
    public static final QName WF_PROP_INVITEE_FIRSTNAME = QName.createQName(NAMESPACE_URI, "inviteeFirstName");
    public static final QName WF_PROP_INVITEE_LASTNAME = QName.createQName(NAMESPACE_URI, "inviteeLastName");
    public static final QName WF_PROP_RESOURCE_TYPE = QName.createQName(NAMESPACE_URI, "resourceType");
    public static final QName WF_PROP_RESOURCE_NAME = QName.createQName(NAMESPACE_URI, "resourceName");
    public static final QName WF_PROP_INVITEE_ROLE = QName.createQName(NAMESPACE_URI, "inviteeRole");
    public static final QName WF_PROP_INVITEE_GEN_PASSWORD = QName.createQName(NAMESPACE_URI, "inviteeGenPassword");
    
    // workflow execution context variable names
    public static final String wfVarInviteeUserName = "inwf_inviteeUserName";
    public static final String wfVarInviterUserName = "inwf_inviterUserName";
    public static final String wfVarResourceName = "inwf_resourceName";
    public static final String wfVarResourceType = "inwf_resourceType";
    public static final String wfVarWorkflowInstanceId = "workflowinstanceid";
    public static final String wfVarRole =  "inwf_inviteeRole";
    
}
