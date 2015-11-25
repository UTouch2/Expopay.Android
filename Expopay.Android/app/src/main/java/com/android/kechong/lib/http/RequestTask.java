package com.android.kechong.lib.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpStatus;
import org.apache.http.ParseException;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.android.kechong.lib.http.Exception.RequestInterruptedException;

public class RequestTask extends AsyncTask<Object, Integer, Object> {

	/***
	 * 为所有的请求分配线程池,创建三次个线程池，根据请求的不同选择不同的线程池。
	 */
	static ExecutorService SINGLE_TASK_EXECUTOR;
	static ExecutorService LIMITED_TASK_EXECUTOR;
	static ExecutorService FULL_TASK_EXECUTOR;

	static {
		SINGLE_TASK_EXECUTOR = (ExecutorService) Executors
				.newSingleThreadExecutor();
		LIMITED_TASK_EXECUTOR = (ExecutorService) Executors
				.newFixedThreadPool(7);
		FULL_TASK_EXECUTOR = (ExecutorService) Executors.newCachedThreadPool();
	}

	private com.android.kechong.lib.http.Request request;

	public RequestTask(com.android.kechong.lib.http.Request request) {
		this.request = request;
	}

	@Override
	protected Object doInBackground(Object... params) {
		try {
			if (request.getRequestType() == RequestType.HTTP_CLIENT) {
				com.android.kechong.lib.http.Response response = HttpClientUtil.excute(request);
				if (request.getIRequestListener() == null) {
					return new RequestInterruptedException("结果不做处理");
				}
				return handle(response);
			} else {
				com.android.kechong.lib.http.Response response = URLConnectionUtil.execute(request);
				return handle(response);
			}
		} catch (Exception e) {
			return e;
		}
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCancelled(Object result) {
		super.onCancelled(result);
		if (request.getIRequestListener() == null) {
			return;
		}
		if (request.getIRequestListener() != null) {
			request.getIRequestListener().cancel();
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		request.getIRequestListener().onProgressUpdate(values[0], values[1]);
	}

	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		if (request.getIRequestListener() == null) {
			return;
		}
		if (result instanceof Exception) {
			request.getIRequestListener().onFilure((Exception) result);
		} else {
			request.getIRequestListener().onSuccess(result);
		}
	}

	public void updateProgress(Integer... values) {
		publishProgress(values);
	}

	public Object handle(Response response) throws RequestInterruptedException {
		// 在这里我们调用检查是否取消请求的方法
		request.getIRequestListener().checkIfCanceled();
		try {
			int statusCode = response.getResponseCode();
			if (statusCode == HttpStatus.SC_OK) {
				// 调用重写的方法去处理返回的数据
				return request.getIRequestListener().bindData(this, response);
			} else {
				return new RequestInterruptedException("服务器" + statusCode
						+ "异常");
			}
		} catch (ParseException e) {
			throw new RequestInterruptedException(e.getMessage());
		}
	}
}
