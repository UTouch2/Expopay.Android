package com.android.kechong.lib.util;

import java.io.IOException;

import android.os.Environment;

public class FileManager {
	public static String rootPath = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/expopay/";
	public static String cachePath = rootPath+"cache/";
	public static String apkPath = rootPath+"apk/";
	public static String dbPath = rootPath+"db/";
	public static void createDir() {
		try {
			com.android.kechong.lib.util.FileUtil.createDirByPath(rootPath);
			com.android.kechong.lib.util.FileUtil.createDirByPath(cachePath);
			com.android.kechong.lib.util.FileUtil.createDirByPath(apkPath);
		} catch (IOException e) { 
			e.printStackTrace();
		}		
	}
}
