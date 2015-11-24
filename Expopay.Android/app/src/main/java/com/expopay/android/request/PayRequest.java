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
public class PayRequest extends Request {
    public PayRequest(String url) {
        setRequestMethod(RequestMethod.POST);
        setOutTime(10 * 1000);
        setUrl(url);
    }

    /**
     * @param openId
     * @param orderNumber
     * @param orderSource
     * @param payCardNumber
     * @param payPwd
     * @param cardPwd
     */
    public Map<String, String> createPayParams(String openId,
                                               String orderNumber,
                                               String orderSource,
                                               String payCardNumber,
                                               String payPwd,
                                               String cardPwd) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("orderNumber", orderNumber);
        body.put("orderSource", orderSource);
        body.put("payCardNumber", payCardNumber);
        body.put("payPwd", payPwd);
        body.put("cardPwd", cardPwd);

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
