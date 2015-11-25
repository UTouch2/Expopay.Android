package com.android.kechong.lib.cache.manager;

import java.io.File;

import com.android.kechong.lib.BaseApplication;
import com.android.kechong.lib.util.BitmapUtil;
import com.android.kechong.lib.util.FileManager;
import com.android.kechong.lib.util.FileUtil;
import com.android.kechong.lib.util.NetworkUtil;

public class BitmapFileCacheManager extends AbsFileCacheManager {

	public BitmapFileCacheManager() {
		super();
		isNetWork = NetworkUtil.checkNetWork(BaseApplication.context);
	}

	@Override
	public Object load(String url, int model) throws Exception {
		// 缓存目录为缓存目录，文件名为url的hash值
		File file = getFile(
				FileManager.cachePath + "/" + String.valueOf(url.hashCode()),
				model);
		// 保存缓存数据
		return BitmapUtil.readBitMap(file);
	}

	@Override
	public void save(String url, Object data) throws Exception {
		// 缓存目录为缓存目录，文件名为url的hash值
		File file = saveFile(FileManager.cachePath,
				String.valueOf(url.hashCode()));
		// 创建缓存数据到磁盘，就是创建文件
		FileUtil.writeTextFile(file, (String) data);
	}
}
