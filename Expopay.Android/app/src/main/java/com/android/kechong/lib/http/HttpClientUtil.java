package com.android.kechong.lib.http;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import com.android.kechong.lib.http.Exception.RequestInterruptedException;

public class HttpClientUtil {
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static com.android.kechong.lib.http.Response excute(com.android.kechong.lib.http.Request request)
			throws RequestInterruptedException {
		if (request.getRequestMethod() == com.android.kechong.lib.http.RequestMethod.GET)
			return get(request);
		else if (request.getRequestMethod() == com.android.kechong.lib.http.RequestMethod.POST)
			return post(request);
		else if (request.getRequestMethod() == com.android.kechong.lib.http.RequestMethod.PUT)
			return put(request);
		else if (request.getRequestMethod() == com.android.kechong.lib.http.RequestMethod.DELETE)
			return delete(request);
		else
			throw new IllegalStateException(
					"you doesn't define this requestmethod");
	}

	private static com.android.kechong.lib.http.Response get(com.android.kechong.lib.http.Request request)
			throws RequestInterruptedException {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, request.getOutTime());
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				request.getOutTime());
		HttpGet get = new HttpGet(request.getUrl());
		addHeader(get, request.getHeaders());
		HttpResponse response = null;
		try {
			response = client.execute(get);
		} catch (ClientProtocolException e) {
			throw new RequestInterruptedException("请求超时");
		} catch (IOException e) {
			throw new RequestInterruptedException("未连接到服务器");
		}
		com.android.kechong.lib.http.Response res = new com.android.kechong.lib.http.Response();
		res.setEntity(response.getEntity());
		res.setResponseCode(response.getStatusLine().getStatusCode());
		res.setContentLength(response.getEntity().getContentLength());
		return res;
	}

	private static com.android.kechong.lib.http.Response put(com.android.kechong.lib.http.Request request)
			throws RequestInterruptedException {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, request.getOutTime());
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				request.getOutTime());
		HttpPut put = new HttpPut(request.getUrl());
		addHeader(put, request.getHeaders());
		put.setEntity(request.getEntity());
		HttpResponse response = null;
		try {
			response = client.execute(put);
		} catch (ClientProtocolException e) {
			throw new RequestInterruptedException(e.getMessage());
		} catch (IOException e) {
			throw new RequestInterruptedException(e.getMessage());
		}
		com.android.kechong.lib.http.Response res = new com.android.kechong.lib.http.Response();
		res.setEntity(response.getEntity());
		res.setResponseCode(response.getStatusLine().getStatusCode());
		res.setContentLength(response.getEntity().getContentLength());
		return res;
	}

	private static com.android.kechong.lib.http.Response post(com.android.kechong.lib.http.Request request)
			throws RequestInterruptedException {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, request.getOutTime());
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				request.getOutTime());
		HttpPost post = new HttpPost(request.getUrl());
		addHeader(post, request.getHeaders());
		if (request.getEntity() == null) {
			throw new IllegalStateException(
					"you forget to set post content to the httpost");
		} else {
			post.setEntity(request.getEntity());
		}

		HttpResponse response = null;
		try {
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			throw new RequestInterruptedException("请求超时");
		} catch (IOException e) {
			throw new RequestInterruptedException("未连接到服务器");
		}
		com.android.kechong.lib.http.Response res = new com.android.kechong.lib.http.Response();
		res.setEntity(response.getEntity());
		res.setContentLength(response.getEntity().getContentLength());
		res.setResponseCode(response.getStatusLine().getStatusCode());
		return res;
	}

	private static com.android.kechong.lib.http.Response delete(Request request)
			throws RequestInterruptedException {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, request.getOutTime());
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				request.getOutTime());
		HttpDelete delete = new HttpDelete(request.getUrl());
		addHeader(delete, request.getHeaders());
		HttpResponse response = null;
		try {
			response = client.execute(delete);
		} catch (ClientProtocolException e) {
			throw new RequestInterruptedException(e.getMessage());
		} catch (IOException e) {
			throw new RequestInterruptedException(e.getMessage());
		}
		com.android.kechong.lib.http.Response res = new Response();
		res.setEntity(response.getEntity());
		res.setContentLength(response.getEntity().getContentLength());
		res.setResponseCode(response.getStatusLine().getStatusCode());
		return res;
	}

	/**
	 * 
	 * @param request
	 * @param headers
	 */
	private static void addHeader(HttpUriRequest request,
			Map<String, String> headers) {
		if (headers != null && headers.size() > 0) {
			for (Entry<String, String> entry : headers.entrySet()) {
				request.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}
}
