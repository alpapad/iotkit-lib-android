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
package com.intel.iotkitlib.LibModules;

import android.util.Log;

import com.intel.iotkitlib.LibHttp.HttpPostTask;
import com.intel.iotkitlib.LibHttp.HttpTaskHandler;
import com.intel.iotkitlib.LibUtils.AttributeFilters.AttributeFilter;
import com.intel.iotkitlib.LibUtils.AttributeFilters.AttributeFilterList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


public class AdvancedDataEnquiry extends ParentModule {
    private final static String TAG = "AdvancedDataEnquiry";
    String msgType;
    List<String> gatewayIds;
    List<String> deviceIds;
    List<String> componentIds;

    Long startTimestamp;
    Long endTimestamp;
    List<String> returnedMeasureAttributes;

    Boolean showMeasureLocation;

    AttributeFilterList devCompAttributeFilter;
    AttributeFilterList measurementAttributeFilter;
    AttributeFilter valueFilter;

    Integer componentRowLimit;
    Boolean countOnly;

    List<NameValuePair> sort;

    public AdvancedDataEnquiry(RequestStatusHandler requestStatusHandler) {
        super(requestStatusHandler);
    }

    public void assignMembersOfAdvancedDataEnquiryWithDefaults() {
        this.startTimestamp = 0L;
        this.endTimestamp = 0L;
        this.showMeasureLocation = false;
        this.componentRowLimit = 0;
        this.countOnly = false;
    }

    public void setMessageType(String msgType) {
        this.msgType = msgType;
    }

    public void addGatewayIds(String gatewayId) {
        if (this.gatewayIds == null) {
            this.gatewayIds = new LinkedList<String>();
        }
        this.gatewayIds.add(gatewayId);
    }

    public void addDeviceIds(String deviceId) {
        if (this.deviceIds == null) {
            this.deviceIds = new LinkedList<String>();
        }
        this.deviceIds.add(deviceId);
    }

    public void addComponentIds(String componentId) {
        if (this.componentIds == null) {
            this.componentIds = new LinkedList<String>();
        }
        this.componentIds.add(componentId);
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public void setEndTimestamp(Long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public void addReturnedMeasureAttributes(String attribute) {
        if (this.returnedMeasureAttributes == null) {
            this.returnedMeasureAttributes = new LinkedList<String>();
        }
        this.returnedMeasureAttributes.add(attribute);
    }

    public void setShowMeasureLocation(Boolean measureLocation) {
        this.showMeasureLocation = measureLocation;
    }

    public void addDevCompAttributeFilter(AttributeFilter attributeFilter) {
        if (this.devCompAttributeFilter == null) {
            this.devCompAttributeFilter = new AttributeFilterList();
            this.devCompAttributeFilter.filterData = new LinkedList<AttributeFilter>();
        }
        this.devCompAttributeFilter.filterData.add(attributeFilter);
    }

    public void addMeasurementAttributeFilter(AttributeFilter attributeFilter) {
        if (this.measurementAttributeFilter == null) {
            this.measurementAttributeFilter = new AttributeFilterList();
            this.measurementAttributeFilter.filterData = new LinkedList<AttributeFilter>();
        }
        this.measurementAttributeFilter.filterData.add(attributeFilter);
    }

    public void addValueFilter(AttributeFilter attributeFilter) {
        this.valueFilter = attributeFilter;
    }

    public void setComponentRowLimit(int componentRowLimit) {
        this.componentRowLimit = componentRowLimit;
    }

    public void setCountOnly(Boolean countOnly) {
        this.countOnly = countOnly;
    }

    public void addSortInfo(String name, String value) {
        if (this.sort == null) {
            this.sort = new LinkedList<NameValuePair>();
        }
        this.sort.add(new BasicNameValuePair(name, value));
    }

    public boolean advancedDataInquiry() throws JSONException {
        String body;
        if ((body = createBodyForAdvancedDataInquiry()) == null) {
            return false;
        }
        //initiating post for advanced data inquiry
        HttpPostTask advancedDataInquiry = new HttpPostTask(new HttpTaskHandler() {
            @Override
            public void taskResponse(int responseCode, String response) {
                Log.d(TAG, String.valueOf(responseCode));
                Log.d(TAG, response);
                statusHandler.readResponse(responseCode, response);
            }
        });
        advancedDataInquiry.setHeaders(basicHeaderList);
        advancedDataInquiry.setRequestBody(body);
        String url = objIotKit.prepareUrl(objIotKit.advancedEnquiryOfData, null);
        return super.invokeHttpExecuteOnURL(url, advancedDataInquiry, "advanced data inquiry");
    }

    private String createBodyForAdvancedDataInquiry() throws JSONException {
        JSONObject dataInquiryJson = new JSONObject();
        if (this.msgType == null) {
            dataInquiryJson.put("msgType", "advancedDataInquiryRequest");
        } else {
            dataInquiryJson.put("msgType", this.msgType);
        }
        if (this.gatewayIds != null) {
            JSONArray gatewayArray = new JSONArray();
            for (String gatewayId : this.gatewayIds) {
                gatewayArray.put(gatewayId);
            }
            dataInquiryJson.put("gatewayIds", gatewayArray);
        }
        if (this.deviceIds != null) {
            JSONArray deviceIdArray = new JSONArray();
            for (String deviceId : this.deviceIds) {
                deviceIdArray.put(deviceId);
            }
            dataInquiryJson.put("deviceIds", deviceIdArray);
        }
        if (this.componentIds != null) {
            JSONArray componentIdArray = new JSONArray();
            for (String componentId : this.componentIds) {
                componentIdArray.put(componentId);
            }
            dataInquiryJson.put("componentIds", componentIdArray);
        }
        dataInquiryJson.put("startTimestamp", this.startTimestamp);
        dataInquiryJson.put("endTimestamp", this.endTimestamp);
        /*dataInquiryJson.put("from", this.startTimestamp);
        dataInquiryJson.put("to", this.endTimestamp);*/
        //returnedMeasureAttributes
        if (this.returnedMeasureAttributes != null) {
            JSONArray returnedMeasureAttributesArray = new JSONArray();
            for (String attribute : this.returnedMeasureAttributes) {
                returnedMeasureAttributesArray.put(attribute);
            }
            dataInquiryJson.put("returnedMeasureAttributes", returnedMeasureAttributesArray);
        }
        if (this.showMeasureLocation) {
            dataInquiryJson.put("showMeasureLocation", this.showMeasureLocation);
        }
        if (this.componentRowLimit > 0) {
            dataInquiryJson.put("componentRowLimit", this.componentRowLimit);
        }
        //sort
        if (this.sort != null) {
            JSONArray sortArray = new JSONArray();
            for (NameValuePair nameValuePair : this.sort) {
                JSONObject nameValueJson = new JSONObject();
                nameValueJson.put(nameValuePair.getName(), nameValuePair.getValue());
                sortArray.put(nameValueJson);
            }
            dataInquiryJson.put("sort", sortArray);
        }
        if (this.countOnly) {
            dataInquiryJson.put("countOnly", this.countOnly);
        }
        if (this.devCompAttributeFilter != null) {
            JSONObject devCompAttributeJson = new JSONObject();
            for (AttributeFilter attributeFilter : this.devCompAttributeFilter.filterData) {
                JSONArray filterValuesArray = new JSONArray();
                for (String filterValue : attributeFilter.filterValues) {
                    filterValuesArray.put(filterValue);
                }
                devCompAttributeJson.put(attributeFilter.filterName, filterValuesArray);
            }
            dataInquiryJson.put("devCompAttributeFilter", devCompAttributeJson);
        }
        if (this.measurementAttributeFilter != null) {
            JSONObject measurementAttributeJson = new JSONObject();
            for (AttributeFilter attributeFilter : this.measurementAttributeFilter.filterData) {
                JSONArray filterValuesArray = new JSONArray();
                for (String filterValue : attributeFilter.filterValues) {
                    filterValuesArray.put(filterValue);
                }
                measurementAttributeJson.put(attributeFilter.filterName, filterValuesArray);
            }
            dataInquiryJson.put("measurementAttributeFilter", measurementAttributeJson);
        }
        if (this.valueFilter != null) {
            JSONObject valueFilterJson = new JSONObject();
            JSONArray filterValuesArray = new JSONArray();
            for (String filterValue : this.valueFilter.filterValues) {
                filterValuesArray.put(filterValue);
            }
            valueFilterJson.put(this.valueFilter.filterName, filterValuesArray);
            dataInquiryJson.put("valueFilter", valueFilterJson);
        }
        return dataInquiryJson.toString();
    }
}
