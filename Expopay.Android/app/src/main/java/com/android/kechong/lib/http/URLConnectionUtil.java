package com.android.kechong.lib.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.android.kechong.lib.http.Exception.RequestInterruptedException;

public class URLConnectionUtil {

	static public com.android.kechong.lib.http.Response execute(com.android.kechong.lib.http.Request request)
			throws RequestInterruptedException {
		switch (request.getRequestMethod()) {
		case com.android.kechong.lib.http.RequestMethod.GET:
			return get(request);
		case com.android.kechong.lib.http.RequestMethod.POST:
			return post(request);
		case com.android.kechong.lib.http.RequestMethod.PUT:
			return put(request);
		case com.android.kechong.lib.http.RequestMethod.DELETE:
			return delete(request);
		default:
			throw new IllegalStateException(
					"you doesn't define this requestmethod");
		}
	}

	private static com.android.kechong.lib.http.Response delete(com.android.kechong.lib.http.Request request) {
		return null;
	}

	private static com.android.kechong.lib.http.Response put(com.android.kechong.lib.http.Request request) {
		return null;
	}

	private static com.android.kechong.lib.http.Response post(com.android.kechong.lib.http.Request request)
			throws RequestInterruptedException {
		// Post请求的url，与get不同的是不需要带参数
		try {
			URL postUrl = new URL(request.getUrl());
			// 打开连接
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();

			// 设置是否向connection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			// Post 请求不能使用缓存
			connection.setUseCaches(false);

			// URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
			// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
			connection.setInstanceFollowRedirects(true);
			// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
			// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
			// 进行编码
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
			// 要注意的是connection.getOutputStream会隐含的进行connect。
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			// 正文，正文内容其实跟get的URL中'?'后的参数字符串一致
			String content = request.getParameters();
			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
			out.writeBytes(content);
			out.flush();
			out.close(); // flush and close
			com.android.kechong.lib.http.Response res = new com.android.kechong.lib.http.Response();
			res.setEntity(connection.getInputStream());
			res.setResponseCode(connection.getResponseCode());
			res.setContentLength(connection.getContentLength());
			connection.disconnect();
			return res;
		} catch (MalformedURLException e) {
			throw new RequestInterruptedException("请求超时");
		} catch (ProtocolException e) {
			throw new RequestInterruptedException("请求超时");
		} catch (IOException e) {
			throw new RequestInterruptedException("请求超时");
		}
	}

	private static com.android.kechong.lib.http.Response get(Request request)
			throws RequestInterruptedException {
		// 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
		String url = request.getUrl() + "?" + request.getParameters();
		try {
			URL getUrl = new URL(url);
			// 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
			// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
			// 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
			// 服务器
			connection.connect();
			com.android.kechong.lib.http.Response res = new Response();
			res.setEntity(connection.getInputStream());
			res.setContentLength(connection.getContentLength());
			res.setResponseCode(connection.getResponseCode());
			connection.disconnect();
			return res;
		} catch (MalformedURLException e) {
			throw new RequestInterruptedException("请求超时");
		} catch (IOException e) {
			throw new RequestInterruptedException("请求超时");
		}
	}
}
