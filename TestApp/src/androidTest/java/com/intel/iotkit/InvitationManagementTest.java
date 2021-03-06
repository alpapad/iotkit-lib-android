/*
 * Copyright (c) 2014 Intel Corporation.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.intel.iotkit;

import com.intel.iotkitlib.LibModules.InvitationManagement;
import com.intel.iotkitlib.LibModules.RequestStatusHandler;

import org.json.JSONException;


public class InvitationManagementTest extends ApplicationTest {
    private boolean serverResponse = false;

    private void waitForServerResponse(Object object) {
        synchronized (object) {
            while (!serverResponse) {
            }
            serverResponse = false;
        }
    }

    public void testCreateInvitation() throws JSONException {
        InvitationManagement invitationManagement = new InvitationManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                assertEquals(201, responseCode);
                serverResponse = true;
            }

        });

        assertEquals(true, invitationManagement.createInvitation("intel.aricent.iot3@gmail.com"));
        waitForServerResponse(invitationManagement);
    }

    public void testGetInvitationListSendToSpecificUser() throws JSONException {
        InvitationManagement invitationManagement = new InvitationManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                assertEquals(200, responseCode);
                serverResponse = true;
            }

        });

        assertEquals(true, invitationManagement.getInvitationListSendToSpecificUser(
                "intel.aricent.iot3@gmail.com"));
        waitForServerResponse(invitationManagement);
    }

    public void testGetListOfInvitation() {
        InvitationManagement invitationManagement = new InvitationManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                assertEquals(200, responseCode);
                serverResponse = true;
            }

        });

        assertEquals(true, invitationManagement.getListOfInvitation());
        waitForServerResponse(invitationManagement);
    }

    public void testDeleteInvitations() {
        InvitationManagement invitationManagement = new InvitationManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                assertEquals(200, responseCode);
                serverResponse = true;
            }

        });

        assertEquals(true, invitationManagement.deleteInvitations("intel.aricent.iot3@gmail.com"));
        waitForServerResponse(invitationManagement);
    }
}
