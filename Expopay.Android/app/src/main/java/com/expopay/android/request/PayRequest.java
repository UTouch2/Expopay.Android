package com.expopay.android.request;

import com.android.kechong.lib.http.Request;
import com.android.kechong.lib.http.RequestMethod;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class PayRequest extends Request {
    public PayRequest(String url){
        setRequestMethod(RequestMethod.POST);
        setOutTime(10 * 1000);
        setUrl(url);
    }
}
