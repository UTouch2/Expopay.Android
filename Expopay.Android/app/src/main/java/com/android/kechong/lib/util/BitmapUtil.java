package com.android.kechong.lib.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.android.kechong.lib.BaseApplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {
	/**
	 *
	 * @param context
	 * @param resId
	 * @return
	 */
	static public Bitmap readBitMap(Context context, int resId) {
		InputStream is = context.getResources().openRawResource(resId);
		Bitmap bitmap =readBitMap(is);
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	/**
	 *
	 * @param resId
	 * @return
	 */
	static public Bitmap readBitMap(int resId) {
		InputStream is = BaseApplication.context.getResources()
				.openRawResource(resId);
		return readBitMap(is);
	}

	static public Bitmap readBitMap(File file) {
		Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
		return bm;
	}

	static public Bitmap readBitMap(InputStream is) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		return BitmapFactory.decodeStream(is, null, opt);
	}

}
