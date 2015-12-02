package com.android.kechong.lib.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

@SuppressLint("DefaultLocale")
public class FileUtil {
    /**
     * @param path
     * @return
     */

    public static final long B = 1;
    public static final long KB = B * 1024;
    public static final long MB = KB * 1024;
    public static final long GB = MB * 1024;
    public static final int BUFFER = 8192;
    public static final String ENVIROMENT_DIR_SAVE = "";

    public static File createFileByPath(String apkPath, String apkname)
            throws IOException {
        if (!new File(apkPath).exists()) {
            new File(apkPath).mkdir();
        }
        File file = new File(apkPath, apkname);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static File createFileByPath(String apkPath) throws IOException {
        File file = new File(apkPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static void createDirByPath(String apkPath) throws IOException {
        if (!isSdCardMounted())
            return;
        if (!new File(apkPath).exists()) {
            new File(apkPath).mkdir();
        }
    }

    /**
     * 格式化文件大小<b> 带有单位
     *
     * @param size
     * @return
     */
    public static String formatFileSize(long size) {
        StringBuilder sb = new StringBuilder();
        String u = null;
        double tmpSize = 0;
        if (size < KB) {
            sb.append(size).append("B");
            return sb.toString();
        } else if (size < MB) {
            tmpSize = getSize(size, KB);
            u = "KB";
        } else if (size < GB) {
            tmpSize = getSize(size, MB);
            u = "MB";
        } else {
            tmpSize = getSize(size, GB);
            u = "GB";
        }
        return sb.append(twodot(tmpSize)).append(u).toString();
    }

    /**
     * 保留两位小数
     *
     * @param d
     * @return
     */
    public static String twodot(double d) {
        return String.format("%.2f", d);
    }

    public static double getSize(long size, long u) {
        return (double) size / (double) u;
    }

    /**
     * sd卡挂载且可用
     *
     * @return
     */
    public static boolean isSdCardMounted() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 读取文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String readTextFile(File file) throws IOException {
        String text = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            text = readTextInputStream(is);
            ;
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return text;
    }

    /**
     * 从流中读取文件
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String readTextInputStream(InputStream is) throws IOException {
        StringBuffer strbuffer = new StringBuffer();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                strbuffer.append(line).append("\r\n");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return strbuffer.toString();
    }

    /**
     * 将文本内容写入文件
     *
     * @param file
     * @param str
     * @throws IOException
     */
    public static void writeTextFile(File file, String str) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(file));
            out.write(str.getBytes());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 将Bitmap保存本地JPG图片
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String saveBitmap2File(String url) throws IOException {
        // BufferedInputStream inBuff = null;
        // BufferedOutputStream outBuff = null;
        // SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        // String timeStamp = sf.format(new Date());
        // File targetFile = new File(ENVIROMENT_DIR_SAVE, timeStamp
        // + ".jpg");
        // File oldfile = ImageLoader.getInstance().getDiscCache().get(url);
        // try {
        //
        // inBuff = new BufferedInputStream(new FileInputStream(oldfile));
        // outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
        // byte[] buffer = new byte[BUFFER];
        // int length;
        // while ((length = inBuff.read(buffer)) != -1) {
        // outBuff.write(buffer, 0, length);
        // }
        // outBuff.flush();
        // return targetFile.getPath();
        // } catch (Exception e) {
        //
        // } finally {
        // if (inBuff != null) {
        // inBuff.close();
        // }
        // if (outBuff != null) {
        // outBuff.close();
        // }
        // }
        // return targetFile.getPath();
        return "";
    }

    /**
     * 读取表情配置文件
     *
     * @param context
     * @return
     */
    public static List<String> getEmojiFile(Context context) {
        try {
            List<String> list = new ArrayList<String>();
            InputStream in = context.getResources().getAssets().open("emoji");// 文件名字为rose.txt
            BufferedReader br = new BufferedReader(new InputStreamReader(in,
                    "UTF-8"));
            String str = null;
            while ((str = br.readLine()) != null) {
                list.add(str);
            }

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取一个文件夹大小
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static long getFileSize(File f) {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {

        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        }
    }

    /**
     * @param stream
     * @param file
     */
    public static void writeStreamToFile(InputStream stream, File file) {
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            final byte[] buffer = new byte[1024];
            int read = -1;
            while ((read = stream.read(buffer)) != -1)
                output.write(buffer, 0, read);
            output.flush();
        } catch (Exception e) {
        } finally {
            try {
                stream.close();
                output.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 流转字符串方法
     *
     * @param is
     * @return
     */
    @SuppressLint("NewApi")
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                if (line.isEmpty()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 字符串转json对象
     *
     * @param str
     * @param split
     * @return
     */
    public static JSONObject string2JSON(String str, String split) {
        JSONObject json = new JSONObject();
        try {
            String[] arrStr = str.split(split);
            for (int i = 0; i < arrStr.length; i++) {
                String[] arrKeyValue = arrStr[i].split("=");
                json.put(arrKeyValue[0],
                        arrStr[i].substring(arrKeyValue[0].length() + 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * @param stream
     * @param apkPath 目标文件的路径
     */
    public static void createFileByStream(InputStream stream, String apkPath,
                                          String apkname) {
        try {
            if (stream != null) {
                File file = createFileByPath(apkPath, apkname);
                // 将资源中的文件重写到sdcard中
                writeStreamToFile(stream, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param apkPath 目标文件的路径
     */
    public static void deleteFile(String apkPath) {
        File file = new File(apkPath);
        if (file.exists()) {
            file.delete();
        }
    }
}
