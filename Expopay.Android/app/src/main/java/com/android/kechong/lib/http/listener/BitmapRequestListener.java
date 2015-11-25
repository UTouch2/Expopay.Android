package com.android.kechong.lib.http.listener;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;

import android.graphics.Bitmap;

import com.android.kechong.lib.http.RequestTask;
import com.android.kechong.lib.http.Response;
import com.android.kechong.lib.http.Exception.RequestInterruptedException;
import com.android.kechong.lib.util.BitmapUtil;

public abstract class BitmapRequestListener extends AbstractRequestListener {

	public BitmapRequestListener() {
		super();
	}

	@Override
	public Object bindData(RequestTask task, Response response)
			throws RequestInterruptedException {
		Object content = response.getEntity();
		Bitmap bitmap = null;
		try {
			if (content instanceof HttpEntity) {
				InputStream is = ((HttpEntity) content).getContent();
				bitmap = BitmapUtil.readBitMap(is);
			} else {
				bitmap = BitmapUtil.readBitMap((InputStream) content);
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
}
