package com.expopay.android.request;

import com.android.kechong.lib.http.Request;
import com.android.kechong.lib.http.RequestMethod;
import com.android.kechong.lib.util.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class CardRequest extends Request {
    public CardRequest(String url) {
        setRequestMethod(RequestMethod.POST);
        setOutTime(10 * 1000);
        setUrl(url);
    }

    /**
     * 删除卡参数
     *
     * @return
     * @throws JSONException
     */
    public Map<String, String> createDeleteCardParams(String openId, String cardNum)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("cardNumber", cardNum);
        String signHead = MD5Util.GetMD5Code(body.toString());
        String signTail = MD5Util.getTail(openId);
        header.put("sign", signHead + signTail);
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * 添加卡参数
     *
     * @return
     * @throws JSONException
     */
    public Map<String, String> createAddCardParams(String openId, String cardNumber, String password)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("cardPwd", password);
        body.put("cardNumber", cardNumber);
        String signHead = MD5Util.GetMD5Code(body.toString());
        String signTail = MD5Util.getTail(openId);
        header.put("sign", signHead + signTail);
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * 卡挂失参数
     *
     * @param openId
     * @param cardNumber
     * @param reason
     * @return
     * @throws JSONException
     */
    public Map<String, String> createCardLossApplyParams(String openId, String cardNumber, String reason)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("reason", reason);
        body.put("cardNumber", cardNumber);
        String signHead = MD5Util.GetMD5Code(body.toString());
        String signTail = MD5Util.getTail(openId);
        header.put("sign", signHead + signTail);
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * 卡列表
     *
     * @param openId
     * @return
     * @throws JSONException
     */
    public Map<String, String> createCardListParams(String openId)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        String signHead = MD5Util.GetMD5Code(body.toString());
        String signTail = MD5Util.getTail(openId);
        header.put("sign", signHead + signTail);
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * 卡实名
     *
     * @param openId
     * @param cardNumber
     * @param personId
     * @param personName
     * @return
     * @throws JSONException
     */
    public Map<String, String> createCertificationApplyParams(String openId, String cardNumber, String personId, String personName)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("personId", personId);
        body.put("personName", personName);
        body.put("cardNumber", cardNumber);
        String signHead = MD5Util.GetMD5Code(body.toString());
        String signTail = MD5Util.getTail(openId);
        header.put("sign", signHead + signTail);
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * 卡详情
     *
     * @param openId
     * @param cardNum
     * @return
     * @throws JSONException
     */
    public Map<String, String> createCardDetailsParams(String openId, String cardNum)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("cardNumber", cardNum);
        String signHead = MD5Util.GetMD5Code(body.toString());
        String signTail = MD5Util.getTail(openId);
        header.put("sign", signHead + signTail);
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * 卡详情
     *
     * @param openId
     * @param cardNum
     * @return
     * @throws JSONException
     */
    public Map<String, String> createSetDefaultCardParams(String openId, String cardNum)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("cardNumber", cardNum);
        String signHead = MD5Util.GetMD5Code(body.toString());
        String signTail = MD5Util.getTail(openId);
        header.put("sign", signHead + signTail);
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }
}
