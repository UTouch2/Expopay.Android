package com.expopay.android.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.activity.ChooseCardActivity;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.CardEntity;
import com.expopay.android.request.CardRequest;
import com.expopay.android.request.PayRequest;
import com.expopay.android.view.CustormLoadingButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/26.
 */
public class NBKCardCreditPayFragment extends BaseFragment {
    CustormLoadingButton okButton;
    TextView amountText;
    EditText payPwdText;
    String orderNumber, orderSource, orderAmount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nbkcardcreditpay, container, false);
        okButton = (CustormLoadingButton) view.findViewById(R.id.nbkcardcreditpay_okbtn);
        amountText = (TextView) view.findViewById(R.id.nbkcardcreditpay_amount_text);
        payPwdText = (EditText) view.findViewById(R.id.nbkcardcreditpay_paypwd_text);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String payPwd = payPwdText.getText().toString().trim();
                    payRequest(getUser().getOpenId(), orderNumber, orderSource, "", payPwd, "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        okButton.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                Fragment fragment = new NBKCardpayResultFragment();
                Bundle args = new Bundle();
                args.putString("", "");
                getBaseActivity().repleaceFragment(R.id.nbkcardpay_container,fragment );
            }
            @Override
            public void onFailureResult() {
                okButton.showNormal("确定");
            }
        });
        Bundle arg = getArguments();
        if (arg != null) {
            orderNumber = arg.getString("orderNumber");
            orderSource = arg.getString("orderSource");
            orderAmount = arg.getString("orderAmount");
            amountText.setText(orderAmount);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void payRequest(String openId,
                            String orderNumber,
                            String orderSource,
                            String payCardNumber,
                            String payPwd,
                            String cardPwd) throws JSONException {
        okButton.showLoading("正在努力加载中···");
        PayRequest request = new PayRequest(MyApplication.HOST+"/payment/pay");
        request.setEntity(request.createPayParams(openId, orderNumber, orderSource, payCardNumber, payPwd, cardPwd));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                okButton.showResult("网络请求失败",false);
            }

            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        okButton.showResult("",true);
                    } else {
                        // 失败
                        okButton.showResult(json.getJSONObject("header").getString("desc"),false);
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }
}
