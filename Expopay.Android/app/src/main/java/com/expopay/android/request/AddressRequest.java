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
public class AddressRequest extends Request {

    public AddressRequest(String url){
        setRequestMethod(RequestMethod.POST);
        setOutTime(10 * 1000);
        setUrl(url);
    }
    /**
     * 获取收货地址列表
     * @param openId
     * @return
     * @throws JSONException
     */
    public Map<String, String> createGetAddressesParams(String openId)
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
     * @param personName
     * @param mobile
     * @param provinceNum
     * @param cityNum
     * @param districtNum
     * @param street
     * @param address
     * @param ZipCode
     * @param isDefault
     * @return
     * @throws JSONException
     */
    public Map<String, String> createAddAddressParams(String openId, String personName,
                                                      String mobile,
                                                      String provinceNum,
                                                      String cityNum,
                                                      String districtNum,
                                                      String street,
                                                      String address,
                                                      String ZipCode,
                                                      String isDefault
    ) throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
        body.put("personName", personName);
        body.put("mobile", mobile);
        body.put("provinceNum", provinceNum);
        body.put("cityNum", cityNum);
        body.put("districtNum", districtNum);
        body.put("street", street);
        body.put("address", address);
        body.put("ZipCode", ZipCode);
        body.put("isDefault", isDefault);

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
     * @param addressId
     * @return
     * @throws JSONException
     */
    public Map<String, String> createDeleteAddressParams(String openId, String addressId)
            throws JSONException {
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("openId", openId);
        header.put("action", "");
        header.put("machineNumber", "android");
        JSONObject body = new JSONObject();
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

}
