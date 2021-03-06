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

import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;

import junit.framework.TestSuite;

public class TestRunner extends InstrumentationTestRunner {

    @Override
    public TestSuite getAllTests() {
        TestSuite suite = new InstrumentationTestSuite(this);
        //suite.addTestSuite(AuthorizationTest.class);
        //Authorization Tests
        suite.addTest(TestSuite.createTest(AuthorizationTest.class, "testGetNewAuthorizationToken"));
        suite.addTest(TestSuite.createTest(AuthorizationTest.class, "testGetAuthorizationTokenInfo"));
        suite.addTest(TestSuite.createTest(AuthorizationTest.class, "testValidateAuthToken"));
        //Account Management
        suite.addTest(TestSuite.createTest(AccountManagementTest.class, "testCreateAnAccount"));

        suite.addTest(TestSuite.createTest(AuthorizationTest.class, "testGetNewAuthorizationToken"));
        suite.addTest(TestSuite.createTest(AuthorizationTest.class, "testGetAuthorizationTokenInfo"));

        suite.addTest(TestSuite.createTest(AccountManagementTest.class, "testGetAccountInformation"));
        suite.addTest(TestSuite.createTest(AccountManagementTest.class, "testRenewAccountActivationCode"));
        suite.addTest(TestSuite.createTest(AccountManagementTest.class, "testGetAccountActivationCode"));
        suite.addTest(TestSuite.createTest(AccountManagementTest.class, "testAddAnotherUserToYourAccount"));
        suite.addTest(TestSuite.createTest(AccountManagementTest.class, "testUpdateAnAccount"));
        //suite.addTest(TestSuite.createTest(AccountManagementTest.class, "testDeleteAnAccount"));

        //Device Management
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testCreateNewDevice"));
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testUpdateADevice"));
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testGetDeviceList"));
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testGetMyDeviceInfo"));
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testGetInfoOnDevice"));
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testActivateADevice"));
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testAddComponentToDevice"));
        /*suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testDeleteAComponent"));
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testDeleteADevice"));*/
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testGetAllAttributes"));
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testGetAllTags"));
        //Component Types Catalog
        suite.addTest(TestSuite.createTest(ComponentTypesCatalogTest.class, "testListAllComponentTypesCatalog"));
        suite.addTest(TestSuite.createTest(ComponentTypesCatalogTest.class, "testListAllDetailsOfComponentTypesCatalog"));
        suite.addTest(TestSuite.createTest(ComponentTypesCatalogTest.class, "testListComponentTypeDetails"));
        suite.addTest(TestSuite.createTest(ComponentTypesCatalogTest.class, "testCreateCustomComponent"));
        suite.addTest(TestSuite.createTest(ComponentTypesCatalogTest.class, "testUpdateAComponent"));
        //Data management
        suite.addTest(TestSuite.createTest(DataManagementTest.class, "testSubmitData"));
        suite.addTest(TestSuite.createTest(DataManagementTest.class, "testRetrieveData"));
        //User management
        suite.addTest(TestSuite.createTest(UserManagementTest.class, "testCreateNewUser"));
        suite.addTest(TestSuite.createTest(UserManagementTest.class, "testAcceptTermsAndConditions"));
        suite.addTest(TestSuite.createTest(UserManagementTest.class, "testGetUserInfo"));
        suite.addTest(TestSuite.createTest(UserManagementTest.class, "testUpdateUserAttributes"));
        suite.addTest(TestSuite.createTest(UserManagementTest.class, "testChangePassword"));
        suite.addTest(TestSuite.createTest(UserManagementTest.class, "testRequestChangePassword"));
        suite.addTest(TestSuite.createTest(UserManagementTest.class, "testUpdateForgotPassword"));
        //suite.addTest(TestSuite.createTest(UserManagementTest.class, "testDeleteAUser"));
        //Invitation Management
        suite.addTest(TestSuite.createTest(InvitationManagementTest.class, "testCreateInvitation"));
        suite.addTest(TestSuite.createTest(InvitationManagementTest.class, "testGetInvitationListSendToSpecificUser"));
        suite.addTest(TestSuite.createTest(InvitationManagementTest.class, "testGetListOfInvitation"));
        suite.addTest(TestSuite.createTest(InvitationManagementTest.class, "testDeleteInvitations"));
        //Rule Management
        suite.addTest(TestSuite.createTest(RuleManagementTest.class, "testCreateARule"));
        suite.addTest(TestSuite.createTest(RuleManagementTest.class, "testUpdateRule"));
        suite.addTest(TestSuite.createTest(RuleManagementTest.class, "testCreateRuleAsDraft"));
        suite.addTest(TestSuite.createTest(RuleManagementTest.class, "testDeleteADraftRule"));
        suite.addTest(TestSuite.createTest(RuleManagementTest.class, "testGetInformationOnRule"));
        suite.addTest(TestSuite.createTest(RuleManagementTest.class, "testGetListOfRules"));
        suite.addTest(TestSuite.createTest(RuleManagementTest.class, "testUpdateStatusOfRule"));
        //Advanced Data Inquiry
        //suite.addTest(TestSuite.createTest(AuthorizationTest.class, "testGetNewAuthorizationToken"));
        //suite.addTest(TestSuite.createTest(AdvancedDataEnquiryTest.class, "testAdvancedDataEnquiry"));
        //Aggregated report interface
        //suite.addTest(TestSuite.createTest(AggregatedReportInterfaceTest.class, "testAggregatedReportInterface"));
        //Alert management
        suite.addTest(TestSuite.createTest(AlertManagementTest.class, "testCreateAlert"));
        suite.addTest(TestSuite.createTest(AlertManagementTest.class, "testGetListOfAlerts"));
        suite.addTest(TestSuite.createTest(AlertManagementTest.class, "testGetInfoOnAlert"));
        suite.addTest(TestSuite.createTest(AlertManagementTest.class, "testUpdateAlertStatus"));
        suite.addTest(TestSuite.createTest(AlertManagementTest.class, "testAddCommentsToTheAlert"));
        suite.addTest(TestSuite.createTest(AlertManagementTest.class, "testResetAlert"));

        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testDeleteAComponent"));
        suite.addTest(TestSuite.createTest(DeviceManagementTest.class, "testDeleteADevice"));
        suite.addTest(TestSuite.createTest(AccountManagementTest.class, "testDeleteAnAccount"));
        suite.addTest(TestSuite.createTest(UserManagementTest.class, "testDeleteAUser"));
        return suite;
    }
}
