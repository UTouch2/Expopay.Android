package com.expopay.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.kechong.lib.http.RequestMethod;
import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.activity.AboutActivity;
import com.expopay.android.activity.LoginByPasswordActivity;
import com.expopay.android.activity.MyBillsActivity;
import com.expopay.android.activity.MyCardsActivity;
import com.expopay.android.activity.MyOrderActivity;
import com.expopay.android.activity.SettingsActivity;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.CustomerCompositeInfoEntity;
import com.expopay.android.request.AppRequest;
import com.expopay.android.request.CustomerRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MyAccFragment extends BaseFragment {
    private TextView cardBalance;
    private TextView remainingDays;
    private TextView defCardNumber;
    private TextView personName;
    private TextView cardQuantity;
    private TextView creditLimitAmt;
    private TextView billAmount;
    View accountGroupView, loginGroupView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myaccount, container, false);

        cardBalance = (TextView) view.findViewById(R.id.myaccount_cardbalance);
        remainingDays = (TextView) view.findViewById(R.id.myaccount_remainingdays);
        defCardNumber = (TextView) view.findViewById(R.id.myaccount_defCardNumber);
        personName = (TextView) view.findViewById(R.id.myaccount_personname);
        cardQuantity = (TextView) view.findViewById(R.id.myaccount_cardQuantity);
        creditLimitAmt = (TextView) view.findViewById(R.id.myaccount_creditLimitAmt);
        billAmount = (TextView) view.findViewById(R.id.myaccount_billAmount);

        accountGroupView = view.findViewById(R.id.myaccount_accountGroupView);
        loginGroupView = view.findViewById(R.id.myaccount_loginGroupView);
        view.findViewById(R.id.myaccount_mycards).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(getUser().getOpenId())) {
                    Intent intent = new Intent(getActivity(), MyCardsActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(getActivity(), LoginByPasswordActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.myaccount_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(getUser().getOpenId())) {
                    Intent intent = new Intent(getActivity(), SettingsActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(getActivity(), LoginByPasswordActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.account_aboutme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.account_bill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(getUser().getOpenId())) {
                    Intent intent = new Intent(getActivity(), MyBillsActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(getActivity(), LoginByPasswordActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.account_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(getUser().getOpenId())) {
                    Intent intent = new Intent(getActivity(), MyOrderActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(getActivity(), LoginByPasswordActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.myaccount_buttonup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginByPasswordActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        if (!"".equals(getUser().getOpenId())) {
            loginGroupView.setVisibility(View.GONE);
            accountGroupView.setVisibility(View.VISIBLE);
            try {
                getCompositeinfoRequest(getUser().getOpenId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            loginGroupView.setVisibility(View.VISIBLE);
            accountGroupView.setVisibility(View.GONE);
        }
    }

    private void getNewVersionCode() throws JSONException {
        AppRequest request = new AppRequest(MyApplication.HOST + "/system/version");
        request.setEntity(request.createVersionCodeParams());
        request.setRequestMethod(RequestMethod.POST);
        request.setOutTime(5 * 1000);
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        // 成功
                        Gson gson = new Gson();

                    } else {
                        // 失败
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                }
            }

            @Override
            public void onProgressUpdate(int i, int j) {

            }

            @Override
            public void onFilure(Exception result) {
                System.out.println(result);
            }
        });
        request.execute();
        cancelRequest(request);
    }

    private void getCompositeinfoRequest(String openId) throws JSONException {
        CustomerRequest request = new CustomerRequest(MyApplication.HOST + "/customer/compositeinfo");
        request.setEntity(request.createGetCustormerParams(openId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception result) {

            }

            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code").equals("0000")) {
                        Gson gson = new Gson();
                        CustomerCompositeInfoEntity entity = gson.fromJson(json.getJSONObject("body").
                                toString(), new TypeToken<CustomerCompositeInfoEntity>() {
                        }.getType());
                        cardBalance.setText(entity.getCardBalance());
                        remainingDays.setText(entity.getRemainingDays());
                        defCardNumber.setText(entity.getDefCardNumber());
                        personName.setText(entity.getPersonName());
                        cardQuantity.setText(entity.getCardQuantity());
                        creditLimitAmt.setText(entity.getCreditLimitAmt());
                        billAmount.setText(entity.getBillAmount());
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onProgressUpdate(int progress, int status) {

            }
        });
        request.execute();
        cancelRequest(request);
    }
}
