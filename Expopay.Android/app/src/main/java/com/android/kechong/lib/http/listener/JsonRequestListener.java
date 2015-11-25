package com.android.kechong.lib.http.listener;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.kechong.lib.http.RequestTask;
import com.android.kechong.lib.http.Response;
import com.android.kechong.lib.http.Exception.RequestInterruptedException;
import com.android.kechong.lib.util.FileUtil;

public abstract class JsonRequestListener extends AbstractRequestListener {

	public JsonRequestListener() {
		super();
	}

	@Override
	public Object bindData(RequestTask task, Response response)
			throws RequestInterruptedException {
		JSONObject json = new JSONObject();
		Object content = response.getEntity();
		try {
			if (content instanceof HttpEntity) {
				json = new JSONObject(
						EntityUtils.toString((HttpEntity) content));
			} else if (content instanceof InputStream) {
				json = new JSONObject(
						FileUtil.readTextInputStream(((InputStream) content)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
