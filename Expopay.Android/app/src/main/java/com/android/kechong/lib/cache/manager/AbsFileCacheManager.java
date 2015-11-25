package com.android.kechong.lib.cache.manager;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

import com.android.kechong.lib.BaseApplication;
import com.android.kechong.lib.cache.ConfigCacheModel;
import com.android.kechong.lib.cache.ConfigCacheTime;
import com.android.kechong.lib.util.FileManager;
import com.android.kechong.lib.util.FileUtil;
import com.android.kechong.lib.util.NetworkUtil;

public abstract class AbsFileCacheManager implements ICacheManager {
	boolean isNetWork;

	protected AbsFileCacheManager() {
		super();
		isNetWork = NetworkUtil.checkNetWork(BaseApplication.context);
	}

	public File getFile(String path, int model) {
		if (path == null) {
			return null;
		}
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			// 当网络是无效的,你只能读缓存
			if (isNetWork) {
				long expiredTime = System.currentTimeMillis()
						- file.lastModified();
				do {
					if (expiredTime < 0) {
						return null;
					}
					if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_SHORT) {
						if (expiredTime > ConfigCacheTime.CONFIG_CACHE_SHORT_TIMEOUT) {
							return null;
						}
					} else if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_MEDIUM) {
						if (expiredTime > ConfigCacheTime.CONFIG_CACHE_MEDIUM_TIMEOUT) {
							return null;
						}
					} else if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_ML) {
						if (expiredTime > ConfigCacheTime.CONFIG_CACHE_ML_TIMEOUT) {
							return null;
						}
					} else if (model == ConfigCacheModel.CONFIG_CACHE_MODEL_LONG) {
						if (expiredTime > ConfigCacheTime.CONFIG_CACHE_MEDIUM_TIMEOUT) {
							return null;
						}
					} else {
						if (expiredTime > ConfigCacheTime.CONFIG_CACHE_MAX_TIMEOUT) {
							return null;
						}
					}
				} while (false);
			}
			return file;
		}
		return null;
	}

	public File saveFile(String path, String name) throws IOException {
		return FileUtil.createFileByPath(path, name);
	}

	public void delete(File cacheFile) {
		if (cacheFile == null) {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				try {
					File cacheDir = new File(FileManager.cachePath);
					if (cacheDir.exists()) {
						delete(cacheDir);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (cacheFile.isFile()) {
			cacheFile.delete();
		} else if (cacheFile.isDirectory()) {
			File[] childFiles = cacheFile.listFiles();
			for (int i = 0; i < childFiles.length; i++) {
				delete(childFiles[i]);
			}
		}
	}
}
