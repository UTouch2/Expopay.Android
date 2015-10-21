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
    public static final int GET_VERCODE = 1;
    public static final int GET_OPENID = 2;
    public static final int GET_COMPANY = 3;
    public static final int QUERY_AMOUNT = 4;
    public static final int GET_TRANSFICATION = 5;
    public static final int GET_CUSTORM_CARD = 6;
    public static final int BIND_CARD = 7;
    public static final int REBIND_CARD = 8;
    public static final int CERTIFICATE = 9;
    public static final int CHANGE_PASSWORD = 10;
    private String host = MyApplication.HOST;

    public CustomerRequest(int action) {
        super();
        setRequestMethod(RequestMethod.POST);
        setOutTime(10 * 1000);
        if (action == GET_VERCODE) {
            setUrl(host + "system/sendcode");
        } else if (action == VERIFY_VERCODE) {
            setUrl(host + "system/verifycode");
        } else if (action == GET_OPENID) {
            setUrl(host + "customer/getopenid");
        } else if (action == GET_COMPANY) {
            setUrl(host + "ecommerce/getcompaines");
        } else if (action == QUERY_AMOUNT) {
            setUrl(host + "ecommerce/queryamount");
        } else if (action == GET_TRANSFICATION) {
            setUrl(host + "order/getorders");
        } else if (action == GET_CUSTORM_CARD) {
            setUrl(host + "customer/getcustomercard");
        } else if (action == BIND_CARD) {
            setUrl(host + "customer/bindcardtocustomer");
        } else if (action == REBIND_CARD) {
            setUrl(host + "customer/rebindcardtocustomer");
        } else if (action == CERTIFICATE) {
            setUrl(host + "customer/certificaterequest");
        } else if (action == CHANGE_PASSWORD) {
            setUrl(host + "customer/changecardpwd");
        }
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

        // String signHead = MD5Util.GetMD5Code(body.toString());
        // String signTail = MD5Util.getTail(phoneNum);
        // header.put("sign", signHead + signTail);

        Map<String, String> map = new HashMap<String, String>();
        map.put("data", data.toString());
        return map;
    }

    /**
     * 验证
     *
     * @param phoneNum
     * @param vercode
     * @return
     * @throws JSONException
     */
    public Map<String, String> createVerifyVerCodeParams(String phoneNum,
                                                         String vercode) throws JSONException {
        JSONObject data = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("openid", "");
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("mobile", phoneNum);
        body.put("code", vercode);

        data.put("header", header);
        data.put("body", body);

        Map<String, String> map = new HashMap<String, String>();
        map.put("data", data.toString());
        return map;
    }

    /**
     * 获取卡列表
     *
     * @param openId
     * @return
     * @throws JSONException
     */
    public Map<String, String> createMyCardsParams(String openId)
            throws JSONException {
        JSONObject data = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        // body.put("", "");
        data.put("header", header);
        data.put("body", body);

        Map<String, String> map = new HashMap<String, String>();
        map.put("data", data.toString());
        return map;
    }

    /**
     * @return
     * @throws JSONException
     */
    public Map<String, String> createGetVersionCodeParams()
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", "");
        header.put("action", "");
        header.put("machineNumber", "android");
        header.put("sign", "");
        JSONObject body = new JSONObject();
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * @return
     * @throws JSONException
     */
    public Map<String, String> createAddCardParams(String openId,
                                                   String cardNum, String cardType) throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("cardNumber", cardNum);
        body.put("cardType", cardType);
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * @return
     * @throws JSONException
     */
    public Map<String, String> createDeleteParams(String openId, String cardNum)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("cardNumber", cardNum);
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * @return
     * @throws JSONException
     */
    public Map<String, String> createGetOpenIdParams(String weixinOpenId)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", "");
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("weixinOpenId", weixinOpenId);
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
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
        body.put("type", type);
        body.put("provinceCode", provinceCode);
        body.put("cityCode", cityId);

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
     * @param companyId
     * @param
     * @param
     * @return
     * @throws JSONException
     */
    public Map<String, String> createQueryAmountParam(String openId,
                                                      String companyId, List paramList)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("source", "1");
        header.put("machinNumber", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        header.put("openId", openId);
        JSONObject body = new JSONObject();
        body.put("companyId", companyId);
        JSONArray arr = new JSONArray();
        for (int i = 0; i < paramList.size(); i++) {
           // arr.put(paramList.get(i).toJSONObject());
        }
        body.put("paramList", arr);

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

    /**
     * @return
     * @throws JSONException
     */
    public Map<String, String> createGetCustormCardParams(String openId,
                                                          String weixinOpenId, String mobile) throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("weixinOpenId", weixinOpenId);
        body.put("mobile", mobile);
        if (!"".equals(openId)) {
            String signHead = MD5Util.GetMD5Code(body.toString());
            String signTail = MD5Util.getTail(openId);
            header.put("sign", signHead + signTail);
        }
        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * @return
     * @throws JSONException
     */
    public Map<String, String> createBindCardParams(String mobile,
                                                    String cardNumber, String personName, String weixinOpenId)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", "");
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("weixinOpenId", weixinOpenId);
        body.put("mobile", mobile);
        body.put("cardNumber", cardNumber);
        body.put("personName", personName);

        JSONObject main = new JSONObject();
        main.put("header", header);
        main.put("body", body);
        Map<String, String> map = new HashMap<String, String>();
        map.put("data", main.toString());
        return map;
    }

    /**
     * @return
     * @throws JSONException
     */
    public Map<String, String> createReBindCardParams(String openId,
                                                      String mobile, String cardNumber, String personName,
                                                      String weixinOpenId) throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("weixinOpenId", weixinOpenId);
        body.put("mobile", mobile);
        body.put("cardNumber", cardNumber);
        body.put("personName", personName);

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
     * @return
     * @throws JSONException
     */
    public Map<String, String> createCertificateParams(String openId,
                                                       String cardNumber, String personName, String personId)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("personName", personName);
        body.put("cardNumber", cardNumber);
        body.put("personId", personId);

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
     * @return
     * @throws JSONException
     */
    public Map<String, String> createChangePswParams(String openId,
                                                     String cardNumber, String oldPsd, String newPsd)
            throws JSONException {
        JSONObject header = new JSONObject();
        header.put("openid", openId);
        header.put("action", "");
        header.put("sign", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("cardNumber", cardNumber);
        body.put("oldPwd", oldPsd);
        body.put("newPwd", newPsd);

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
