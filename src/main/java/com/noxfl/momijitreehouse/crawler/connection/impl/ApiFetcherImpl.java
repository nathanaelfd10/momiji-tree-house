/**
 * 
 */
package com.noxfl.momijitreehouse.crawler.connection.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.noxfl.momijitreehouse.crawler.connection.ApiFetcher;

/**
 * @author Fernando Nathanael
 *
 */
@Component
public class ApiFetcherImpl implements ApiFetcher {

	public JSONObject fetchPost(String body, HashMap<String, String> headers, String targetUrl) throws IOException {

		HttpPost post = new HttpPost(targetUrl);
		StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
		post.setEntity(entity);
		for (Entry<String, String> header : headers.entrySet())
			post.setHeader(header.getKey(), header.getValue());

		JSONObject result = null;

		try {
			CloseableHttpClient httpClient = HttpClients.custom()
					.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
					.build();
			CloseableHttpResponse response = httpClient.execute(post);

			String responseString = EntityUtils.toString(response.getEntity());

			result = new JSONObject(responseString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public JSONObject fetchGet(HashMap<String, String> headers, String targetUrl) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}