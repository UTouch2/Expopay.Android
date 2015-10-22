package com.expopay.android.request;

import com.android.kechong.lib.http.Request;
import com.android.kechong.lib.http.RequestMethod;
import com.android.kechong.lib.util.MD5Util;
import com.expopay.android.application.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class CustomerRequest extends Request {
    public static final int VERIFY_VERCODE = 0;
    public CustomerRequest(String url) {
        super();
        setRequestMethod(RequestMethod.POST);
        setOutTime(10 * 1000);
        setUrl(url);
    }

    public CustomerRequest(String url, int method) {
        super(url, method);
    }

    /**
     * 获取验证码
     *
     * @param phoneNum
     * @return
     * @throws JSONException
     */
    public Map<String, String> createGetVerCodeParams(String phoneNum)
            throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openid", "");
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("mobile", phoneNum);
        data.put("header", header);
        data.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", data.toString());
        return map;
    }

    /**
     * 登录
     *
     * @param phoneNum
     * @param vercode
     * @param userName
     * @param loginPwd
     * @return
     * @throws JSONException
     */
    public Map<String, String> createLoginParams(String phoneNum,
                                                 String vercode, String userName, String loginPwd) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openid", "");
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("mobile", phoneNum);
        body.put("code", vercode);
        body.put("loginPwd", loginPwd);
        body.put("userName", userName);


        data.put("header", header);
        data.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", data.toString());
        return map;
    }

    /**
     * 获取用户信息
     *
     * @param openId
     * @return
     * @throws JSONException
     */
    public Map<String, String> createGetCustormerParams(String openId)
            throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();

        String signHead = MD5Util.GetMD5Code(body.toString());
        String signTail = MD5Util.getTail(openId);
        header.put("sign", signHead + signTail);

        data.put("header", header);
        data.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", data.toString());
        return map;
    }

    /**
     * @param openId
     * @param mobile
     * @param code
     * @return
     * @throws JSONException
     */
    public Map<String, String> createChangeMobileParams(String openId, String mobile, String code)
            throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("mobile", mobile);
        body.put("code", code);
        String signHead = MD5Util.GetMD5Code(body.toString());
        String signTail = MD5Util.getTail(openId);
        header.put("sign", signHead + signTail);

        data.put("header", header);
        data.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", data.toString());
        return map;
    }
}
