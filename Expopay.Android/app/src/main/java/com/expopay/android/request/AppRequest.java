package com.expopay.android.request;

import com.android.kechong.lib.http.Request;
import com.android.kechong.lib.http.RequestMethod;
import com.android.kechong.lib.util.MD5Util;
import com.expopay.android.application.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by misxu012 on 2015/10/15.
 */
public class AppRequest extends Request{

    public AppRequest(String url){
        setRequestMethod(RequestMethod.POST);
        setOutTime(10 * 1000);
        setUrl(url);
    }


    public AppRequest(String url, int method) {
        super(url, method);
    }

    public Map<String, String> createVersionCodeParams() throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openid", "");
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        data.put("header", header);
        data.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", data.toString());
        return map;
    }
}
