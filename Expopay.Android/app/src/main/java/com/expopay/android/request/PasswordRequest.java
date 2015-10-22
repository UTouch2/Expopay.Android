package com.expopay.android.request;

import com.android.kechong.lib.http.Request;
import com.android.kechong.lib.http.RequestMethod;
import com.android.kechong.lib.util.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class PasswordRequest extends Request {
    public PasswordRequest(String url){
        setRequestMethod(RequestMethod.POST);
        setOutTime(10 * 1000);
        setUrl(url);
    }
    /**
     * 获取密保问题
     *
     * @param openId
     * @return
     * @throws JSONException
     */
    public Map<String, String> createPasswordQuestionsParams(String openId)
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
     *忘记密码
     * @param openId
     * @param userName
     * @param mobile
     * @param secuQuestionId
     * @param secuAnswer
     * @param newLoginPwd
     * @return
     * @throws JSONException
     */
    public Map<String, String> createForgetPasswordParams(String openId, String userName,
                                                             String mobile,
                                                             String secuQuestionId,
                                                             String secuAnswer,
                                                             String newLoginPwd) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("userName",userName);
        body.put("mobile",mobile);
        body.put("secuQuestionId",secuQuestionId);
        body.put("secuAnswer",secuAnswer);
        body.put("newLoginPwd",newLoginPwd);

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
     *
     * @param openId
     * @param loginPwd
     * @param newLoginPwd
     * @return
     * @throws JSONException
     */
    public Map<String, String> createChangePasswordParams(String openId, String loginPwd,
                                                                  String newLoginPwd) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("loginPwd",loginPwd);
        body.put("newLoginPwd",newLoginPwd);

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
     *
     * @param openId
     * @param payPwd
     * @param newPayPwd
     * @return
     * @throws JSONException
     */
    public Map<String, String> createChangePayPasswordParams(String openId, String payPwd,
                                                          String newPayPwd) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("payPwd",payPwd);
        body.put("newPayPwd",newPayPwd);

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
     * 修改密保问题
     * @param openId
     * @param userName
     * @param secuQuestionId
     * @param secuAnswer
     * @param newSecuQuestionId
     * @param newSecuAnswer
     * @return
     * @throws JSONException
     */
    public Map<String, String> createChangePasswordQuestionParams(String openId, String userName,
                                                          String secuQuestionId,
                                                          String secuAnswer,
                                                          String newSecuQuestionId, String newSecuAnswer) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("userName",userName);
        body.put("secuQuestionId",secuQuestionId);
        body.put("secuAnswer",secuAnswer);
        body.put("newSecuQuestionId",newSecuQuestionId);
        body.put("newSecuAnswer",newSecuAnswer);

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
