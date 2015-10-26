package com.expopay.android.utils;

import android.net.IpPrefix;
import android.os.Environment;

import com.android.kechong.lib.util.FileUtil;

import java.io.IOException;

/**
 * Created by misxu012 on 2015/10/24.
 */
public class FileManager {
    public static String rootPath = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/expopay/";
    public static String cachePath = rootPath + "cache/";
    public static String apkPath = rootPath + "apk/";
    public static String dbPath = rootPath + "db/";

    public static void createDir() throws IOException{
            FileUtil.createDirByPath(rootPath);
            FileUtil.createDirByPath(cachePath);
            FileUtil.createDirByPath(apkPath);
    }
}
