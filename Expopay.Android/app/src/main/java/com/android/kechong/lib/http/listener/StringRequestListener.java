package com.android.kechong.lib.http.listener;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.android.kechong.lib.http.RequestTask;
import com.android.kechong.lib.http.Response;
import com.android.kechong.lib.http.Exception.RequestInterruptedException;
import com.android.kechong.lib.util.FileUtil;

public abstract class StringRequestListener extends AbstractRequestListener {

	public StringRequestListener() {
		super();
	}

	@Override
	public Object bindData(RequestTask task, Response response)
			throws RequestInterruptedException {
		Object content = response.getEntity();
		try {
			String s = null;
			if (content instanceof HttpEntity) {
				s = EntityUtils.toString((HttpEntity) content);
			} else if (content instanceof InputStream) {
				s = FileUtil.readTextInputStream(((InputStream) content));
			}
			return s;
		} catch (ParseException e) {

		} catch (IOException e) {

		}
		return null;
	}
}
