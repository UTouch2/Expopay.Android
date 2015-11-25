package com.android.kechong.lib.util;

import java.util.ArrayList;

import org.apache.http.NameValuePair;


public class TextUtil {
	public static boolean isValidate(String content){
		return content != null && !"".equals(content.trim());
	}
	public static boolean isValidate(ArrayList<NameValuePair> content){
		return content != null && content.size() > 0;
	}
}
