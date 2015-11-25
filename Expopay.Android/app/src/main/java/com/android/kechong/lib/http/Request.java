package com.android.kechong.lib.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;

import com.android.kechong.lib.http.ProgressFileInputStream.ProgressListener;
import com.android.kechong.lib.http.listener.AbstractRequestListener;
import com.android.kechong.lib.http.listener.IRequestListener;

public class Request {

	public static final String ENCODING = "UTF-8";
	public static String SINGLE_TASK_EXECUTOR = "single";
	public static String LIMITED_TASK_EXECUTOR = "limited";
	public static String FULL_TASK_EXECUTOR = "full";
	private int requestMethod;
	private String url;
	private HttpEntity entity;
	private String parameters;
	private Map<String, String> headers;
	private int outTime = 1000 * 10;
	private IRequestListener iRequestListener;
	private RequestTask task;
	private String executorType;

	private int requestType = RequestType.HTTP_CLIENT;

	public Request() {
		// 默认添加服务器关闭连接
		this.addHeader("Connection", "close");
	}

	public Request(String url, int method) {
		this.url = url;
		this.requestMethod = method;
		// 默认添加服务器关闭连接
		this.addHeader("Connection", "close");
	}

	public void setEntity(List<NameValuePair> params) {
		try {
			entity = new UrlEncodedFormEntity(params, ENCODING);

			for (int i = 0; i < params.size(); i++) {
				NameValuePair vp = params.get(i);
				if (i < params.size() - 1) {
					parameters += (vp.getName() + "=" + vp.getValue() + "&");
				} else {
					parameters += (vp.getName() + "=" + vp.getValue());
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void setEntity(Map<String, String> params) {
		List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		if (params != null) {
			for (String key : params.keySet()) {
				postParams.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		setEntity(postParams);
	}

	public void setEntity(String postContent) {
		if (this.requestMethod == RequestMethod.GET) {
			return;
		}
		try {
			entity = new StringEntity(postContent, ENCODING);
			parameters = postContent;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void setEntity(byte[] bytes) {
		entity = new ByteArrayEntity(bytes);
	}

	public HttpEntity getEntity() {
		return entity;
	}

	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}

	/**
	 * 执行task
	 */
	@SuppressLint("NewApi")
	public void execute() {
		task = new RequestTask(this);
		// 根据request的线程池类型执行的线程池。
		// 默认选择full
		if (LIMITED_TASK_EXECUTOR.equals(executorType)) {
			task.executeOnExecutor(RequestTask.LIMITED_TASK_EXECUTOR);
		} else if (SINGLE_TASK_EXECUTOR.equals(executorType)) {
			task.executeOnExecutor(RequestTask.SINGLE_TASK_EXECUTOR);
		} else {
			task.executeOnExecutor(RequestTask.FULL_TASK_EXECUTOR);
		}
	}

	public void cancel() {
		task.cancel(true);
		if (iRequestListener != null) {
			this.iRequestListener.cancel();
		}
	}

	public int getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(int requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public void addHeader(String k, String v) {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put(k, v);
	}

	public IRequestListener getIRequestListener() {
		return iRequestListener;
	}

	public void setIRequestListener(IRequestListener iRequestListener) {
		this.iRequestListener = iRequestListener;
	}

	public RequestTask getTask() {
		return task;
	}

	public void setTask(RequestTask task) {
		this.task = task;
	}

	public int getOutTime() {
		return outTime;
	}

	public void setOutTime(int outTime) {
		this.outTime = outTime;
	}

	public String getExecutorType() {
		return executorType;
	}

	public void setExecutorType(String executorType) {
		this.executorType = executorType;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	/**
	 * 设置上传文件,监控上传进度
	 * 
	 * @param file
	 */
	public void setEntity(File file) {
		try {
			ProgressFileInputStream upLoadInputStream = new ProgressFileInputStream(
					file);
			// 上传文件时， 上传文件流添加回调，用来监控文件进度。
			upLoadInputStream.setListener(new ProgressListener() {
				@Override
				public void onProgress(int trans) {
					task.updateProgress(new Integer[] { trans,
							AbstractRequestListener.DOING });
				}
			});
			InputStreamEntity reqEntity = new InputStreamEntity(
					upLoadInputStream, -1);
			reqEntity.setContentType("binary/octet-stream");
			reqEntity.setChunked(true);
			entity = reqEntity;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
