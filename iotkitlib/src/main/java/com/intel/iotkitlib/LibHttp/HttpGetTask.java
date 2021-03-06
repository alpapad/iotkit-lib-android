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
package com.intel.iotkitlib.LibHttp;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


public class HttpGetTask extends AsyncTask<String, Void, CloudResponse> {
    private static final String TAG = "HttpGetTask";
    private static final boolean debug = true;

    //protected Context mContext;
    private List<NameValuePair> headerList;
    private HttpTaskHandler taskHandler;

    public HttpGetTask(HttpTaskHandler taskHandler) {
        this.taskHandler = taskHandler;
    }

    private CloudResponse doWork(HttpClient httpClient, String url) {
        try {
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet(url);
            //adding headers one by one
            for (NameValuePair nvp : headerList) {
                httpGet.addHeader(nvp.getName(), nvp.getValue());
            }
            if (debug) {
                Log.e(TAG, "URI is : " + httpGet.getURI());
                Header[] headers = httpGet.getAllHeaders();
                for (int i = 0; i < headers.length; i++) {
                    Log.e(TAG, "Header " + i + " is :" + headers[i].getName() + ":" + headers[i].getValue());
                }
            }
            HttpResponse response = httpClient.execute(httpGet, localContext);
            if (response != null && response.getStatusLine() != null) {
                if (debug) Log.d(TAG, "response: " + response.getStatusLine().getStatusCode());
            }


            HttpEntity responseEntity = response != null ? response.getEntity() : null;
            StringBuilder builder = new StringBuilder();
            if (responseEntity != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent(), HTTP.UTF_8));
                for (String line = null; (line = reader.readLine()) != null; ) {
                    builder.append(line).append("\n");
                }
                if (debug) Log.d(TAG, "Response received is :" + builder.toString());
            }

            CloudResponse cloudResponse = new CloudResponse();
            if (response != null) {
                cloudResponse.code = response.getStatusLine().getStatusCode();
            }
            cloudResponse.response = builder.toString();
            return cloudResponse;
        } catch (java.net.ConnectException cEx) {
            Log.e(TAG, cEx.getMessage());
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected CloudResponse doInBackground(String... url) {
        HttpClient httpClient = new DefaultHttpClient();
        return doWork(httpClient, url[0]);
    }

    @Override
    protected void onPostExecute(CloudResponse response) {
        // Done on UI Thread
        if (response != null) {
            taskHandler.taskResponse(response.code, response.response);
        }
    }

    public void setHeaders(List<NameValuePair> headers) {
        this.headerList = headers;
    }
}
