package com.android.kechong.lib.http.listener;

import com.android.kechong.lib.http.RequestTask;
import com.android.kechong.lib.http.Response;
import com.android.kechong.lib.http.Exception.RequestInterruptedException;

public interface IRequestListener {

	/**
	 * 当失败的时候，所做的处理
	 * 
	 * @param result
	 */
	void onFilure(Exception result);

	/**
	 * 当成功的时候，所做的处理
	 * 
	 * @param result
	 */
	void onSuccess(Object result);

	/**
	 * 
	 * @param progress
	 *            进度
	 * @param status
	 *            状态
	 */
	void onProgressUpdate(int progress, int status);

	/**
	 * 取消
	 */
	void cancel();

	/**
	 * 检查是否取消
	 * 
	 * @throws RequestInterruptedException
	 */
	void checkIfCanceled() throws RequestInterruptedException;

	Object bindData(RequestTask task, Response response)
			throws RequestInterruptedException;
}
