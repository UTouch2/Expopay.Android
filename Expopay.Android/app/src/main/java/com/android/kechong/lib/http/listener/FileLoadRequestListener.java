package com.android.kechong.lib.http.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;

import android.annotation.SuppressLint;

import com.android.kechong.lib.http.RequestTask;
import com.android.kechong.lib.http.Response;
import com.android.kechong.lib.http.Exception.RequestInterruptedException;
import com.android.kechong.lib.util.FileUtil;

@SuppressLint("HandlerLeak")
public abstract class FileLoadRequestListener extends com.android.kechong.lib.http.listener.AbstractRequestListener {

	// 如果是文件则需要文件路劲
	private String path;
	private int progress = 0;
	private int progressPlus = -1;

	public FileLoadRequestListener() {
		super();
	}

	public FileLoadRequestListener(String path) {
		super();
		this.path = path;
	}

	@Override
	public Object bindData(RequestTask task, Response response)
			throws RequestInterruptedException {
		Object ent = response.getEntity();
		// 获取文件大小
		long length = 0;
		// 创建输入流
		FileUtil.deleteFile(path);
		// 创建输入流
		File apkFile = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			if (ent instanceof HttpEntity) {
				is = ((HttpEntity) ent).getContent();
			} else if (ent instanceof InputStream) {
				is = ((InputStream) ent);				
			}
			length = response.getContentLength();
			apkFile = FileUtil.createFileByPath(path);
			fos = new FileOutputStream(apkFile);
			int count = 0;
			// 缓存
			byte buf[] = new byte[1024];
			int numread;
			// 写入到文件中
			while ((numread = is.read(buf)) != -1) {
				count += numread;
				// 计算进度
				progress = (int) (((float) count / length) * 100);
				fos.write(buf, 0, numread);
				// 更新进度,做标记位progressPlus，保证每一刻度值发一次消息。
				if (progress % 10 == 0 && progressPlus != progress) {
					progressPlus = progress;
					task.updateProgress(new Integer[] { progress, DOING });
				}
			}
			task.updateProgress(new Integer[] { progress, FINISH });
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return progress;
	}

	@Override
	public Integer[] getProgress() {
		return super.getProgress();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
