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
package com.intel.iotkitlib.LibModules.ComponentTypesCatalog;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;

//inner class to hold device details
public class CreateOrUpdateComponentCatalog {
    String componentName;
    String componentVersion;
    String componentType;
    String componentDataType;
    String componentFormat;
    String componentUnit;
    String componentDisplay;
    Double minValue;
    Double maxValue;
    Boolean isMinSet;
    Boolean isMaxSet;
    String commandString;
    List<NameValuePair> actuatorCommandParams;

    //constructor of inner class
    public CreateOrUpdateComponentCatalog(String componentName, String componentVersion, String componentType,
                                          String componentDataType, String componentFormat, String componentUnit,
                                          String componentDisplay) {
        this.componentName = componentName;
        this.componentVersion = componentVersion;
        this.componentType = componentType;
        this.componentDataType = componentDataType;
        this.componentFormat = componentFormat;
        this.componentUnit = componentUnit;
        this.componentDisplay = componentDisplay;
        isMinSet = false;
        isMaxSet = false;
        minValue = 0.0;
        maxValue = 0.0;
    }

    public void setMinValue(Double minValue) {
        isMinSet = true;
        this.minValue = minValue;
    }

    public void setMaxValue(Double maxValue) {
        isMaxSet = true;
        this.maxValue = maxValue;
    }

    public void setCommandString(String commandString) {
        this.commandString = commandString;
    }

    public void addCommandParameters(String commandName, String commandValue) {
        if (actuatorCommandParams == null) {
            actuatorCommandParams = new LinkedList<NameValuePair>();
        }
        actuatorCommandParams.add(new BasicNameValuePair(commandName, commandValue));
    }

}
