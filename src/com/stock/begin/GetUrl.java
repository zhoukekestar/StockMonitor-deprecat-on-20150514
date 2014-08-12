package com.stock.begin;


import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * This example demonstrates the use of the {@link ResponseHandler} to simplify
 * the process of processing the HTTP response and releasing associated resources.
 */
/*
 * This example is come from :
 * http://hc.apache.org/httpcomponents-client-4.3.x/examples.html
 * */

public class GetUrl {
	public static String Get(String url) throws Exception {
	
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		String responseBody = "";
		try {
			HttpGet httpget = new HttpGet(url);
//			/httpget.addHeader("charset", "Gbk");
			//System.out.println("Executing request " + httpget.getRequestLine());
		
			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				
				@Override
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

				

			};
			responseBody = new String(httpclient.execute(httpget, responseHandler).getBytes(), "GBK");
		} finally {
			httpclient.close();
		}
		
		return responseBody;
	}
}
