package com.android.kechong.lib.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProgressFileInputStream extends FileInputStream {

	private long currentLen;
	private ProgressListener listener;
	private long fileLen;
	//private int progressPlus = -1;

	public ProgressFileInputStream(File file) throws FileNotFoundException {
		super(file);
		fileLen = file.length();
	}

	@Override
	public int read(byte[] buffer, int byteOffset, int byteCount)
			throws IOException {
		currentLen += byteCount;
		int progress = (int) (currentLen / fileLen);
		if (listener != null) { 
			listener.onProgress(progress);
		}
		return super.read(buffer, byteOffset, byteCount);
	}

	public interface ProgressListener {
		void onProgress(int trans);
	}

	public ProgressListener getListener() {
		return listener;
	}

	public void setListener(ProgressListener listener) {
		this.listener = listener;
	}

	
}
