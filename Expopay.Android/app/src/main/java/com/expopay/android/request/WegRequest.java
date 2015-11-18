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
 * Created by misxu012 on 2015/10/21.
 */
public class WegRequest extends Request {


    public WegRequest(String url) {
        setRequestMethod(RequestMethod.POST);
        setOutTime(10 * 1000);
        setUrl(url);
    }

    /**
     * 获取收费公司 参数
     *
     * @param
     * @return
     * @throws JSONException
     */
    public Map<String, String> createGetCompanyParam(String openId,
                                                     String type, String provinceCode, String cityId)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("sign", "");
        header.put("openId", openId);
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("publicUtilityType", type);
        body.put("provinceNum", provinceCode);
        body.put("cityNum", cityId);

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
     * 查询缴费金额 参数
     *
     * @param openId
     * @param companyId
     * @return
     * @throws JSONException
     */
    public Map<String, String> createQueryAmountParam(String openId,
                                                      String companyId, String publicParamName, String publicParamValue)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("source", "1");
        header.put("machinNumber", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        header.put("openId", openId);
        JSONObject body = new JSONObject();
        body.put("companyId", companyId);
        body.put("publicParamName", publicParamName);
        body.put("publicParamValue", publicParamValue);

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
     * @param openId
     * @param orderSource
     * @param pageIndex
     * @param pageSize
     * @param beginDate
     * @param endDate
     * @param status
     * @param type
     * @return
     * @throws JSONException
     */
    public Map<String, String> createGetTransficationParam(String openId,
                                                           String orderSource, String pageIndex, String pageSize,
                                                           String beginDate, String endDate, String status, String type)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("orderSource", orderSource);
        body.put("pageIndex", pageIndex);
        body.put("pageSize", pageSize);
        body.put("beginDate", beginDate);
        body.put("endDate", endDate);
        body.put("status", status);
        body.put("type", type);

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
