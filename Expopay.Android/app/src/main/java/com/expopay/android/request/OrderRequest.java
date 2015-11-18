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
public class OrderRequest extends Request {
    public OrderRequest(String url){
        setRequestMethod(RequestMethod.POST);
        setOutTime(10 * 1000);
        setUrl(url);
    }
    /**
     * 创建卡订单
     *
     * @param openId
     * @return
     * @throws JSONException
     */
    public Map<String, String> createCreateOrder(String openId,
                                                 String orderSource,
                                                 String paymentMethod,
                                                 String orerAmount,
                                                 String cardNumber
    ) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("paymentMethod", paymentMethod);
        body.put("orderSource", orderSource);
        body.put("orerAmount", orerAmount);
        body.put("cardNumber", cardNumber);

        String signHead = MD5Util.GetMD5Code(body.toString());
        String signTail = MD5Util.getTail(openId);
        header.put("sign", signHead + signTail);

        data.put("header", header);
        data.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", data.toString());
        return map;
    }
    public Map<String, String> createGetBillsParams(String openId
    ) throws JSONException {
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
     * 创建卡订单
     *
     * @param openId
     * @return
     * @throws JSONException
     */
    public Map<String, String> createCreateOrderParms(String openId,
                                                 String orderSource,
                                                 String paymentMethod,
                                                 String orerAmount,
                                                 String publicUtilityType,
                                                 String publicUtilityNum
    ) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("paymentMethod", paymentMethod);
        body.put("orderSource", orderSource);
        body.put("orerAmount", orerAmount);
        body.put("publicUtilityType", publicUtilityType);
        body.put("publicUtilityNum", publicUtilityNum);
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
     * 创建卡订单
     * @param openId
     * @return
     * @throws JSONException
     */
    public Map<String, String> createCreateOrderParms(String openId,
                                                 String orderSource,
                                                 String paymentMethod,
                                                 String orerAmount,
                                                 String productId,
                                                 String periodId,
                                                 String addressId
    ) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("paymentMethod", paymentMethod);
        body.put("orderSource", orderSource);
        body.put("orerAmount", orerAmount);
        body.put("productId", productId);
        body.put("periodId", periodId);
        body.put("addressId", addressId);

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
     * @param orderType
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws JSONException
     */
    public Map<String, String> createGetOrdersParms(String openId,
                                                 String orderType,
                                                 String pageIndex,
                                                 String pageSize
    ) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("pageIndex", pageIndex);
        body.put("orderSource", orderType);
        body.put("pageSize", pageSize);

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
     * 订单详情
     * @param openId
     * @param orderNumber
     * @param orderType
     * @return
     * @throws JSONException
     */
    public Map<String, String> createOrderDetailsParms(String openId,
                                                    String orderNumber,
                                                    String orderType
    ) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("orderType", orderType);
        body.put("orderNumber", orderNumber);

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
     * 取消订单
     * @param openId
     * @param orderNumber
     * @param cancelReason
     * @return
     * @throws JSONException
     */
    public Map<String, String> createCancelOrderParms(String openId,
                                                       String orderNumber,
                                                       String cancelReason
    ) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("cancelReason", cancelReason);
        body.put("orderNumber", orderNumber);

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
