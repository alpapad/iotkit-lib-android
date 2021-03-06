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


/**
 * @file AccountManagementTest.java
 *
 * Provides Test APIs to test AccountManagement APIs
 */


package com.intel.iotkit;

import com.intel.iotkitlib.LibModules.AccountManagement;
import com.intel.iotkitlib.LibModules.RequestStatusHandler;

import org.json.JSONException;

public class AccountManagementTest extends ApplicationTest {
    private boolean serverResponse = false;

    private void waitForServerResponse(Object object) {
        synchronized (object) {
            while (!serverResponse) {
            }
            serverResponse = false;
        }
    }

    //should be called before any other test gets called
    public void testCreateAnAccount() {
        AccountManagement accountManagement = new AccountManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                //setResponse(responseCode, response);
                assertEquals(201, responseCode);
                serverResponse = true;
            }
        });

        assertEquals(true, accountManagement.createAnAccount("UnitTesting"));
        waitForServerResponse(accountManagement);
    }

    public void testGetAccountInformation() {
        AccountManagement accountManagement = new AccountManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                //setResponse(responseCode, response);
                assertEquals(200, responseCode);
                serverResponse = true;
            }
        });

        assertEquals(true, accountManagement.getAccountInformation());
        waitForServerResponse(accountManagement);
    }

    public void testGetAccountActivationCode() {
        AccountManagement accountManagement = new AccountManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                //setResponse(responseCode, response);
                assertEquals(200, responseCode);
                serverResponse = true;
            }
        });

        assertEquals(true, accountManagement.getAccountActivationCode());
        waitForServerResponse(accountManagement);
    }


    public void testRenewAccountActivationCode() {
        AccountManagement accountManagement = new AccountManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                //setResponse(responseCode, response);
                assertEquals(200, responseCode);
                serverResponse = true;
            }
        });

        assertEquals(true, accountManagement.renewAccountActivationCode());
        waitForServerResponse(accountManagement);
    }

    public void testUpdateAnAccount() {
        AccountManagement accountManagement = new AccountManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                //setResponse(responseCode, response);
                assertEquals(200, responseCode);
                serverResponse = true;
            }
        });

        assertEquals(true, accountManagement.updateAnAccount("UnitTestingNewName"));
        waitForServerResponse(accountManagement);
    }

    public void testDeleteAnAccount() {
        AccountManagement accountManagement = new AccountManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                //setResponse(responseCode, response);
                assertEquals(204, responseCode);
                serverResponse = true;
            }
        });

        assertEquals(true, accountManagement.deleteAnAccount());
        waitForServerResponse(accountManagement);
    }

    public void testAddAnotherUserToYourAccount() throws JSONException {
        AccountManagement accountManagement = new AccountManagement(new RequestStatusHandler() {
            @Override
            public void readResponse(int responseCode, String response) {
                //setResponse(responseCode, response);
                assertEquals(200, responseCode);
                serverResponse = true;
            }
        });

        assertEquals(true, accountManagement.addAnotherUserToYourAccount("1e5f2301-b121-4933-8f32-1abb0af3d777", "545b0cb707024be10dec1152", false));
        waitForServerResponse(accountManagement);
    }

}
