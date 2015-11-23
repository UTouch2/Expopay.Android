package com.expopay.android.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.activity.ChooseCardActivity;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.CardEntity;
import com.expopay.android.request.CardRequest;
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
public class NBKCardpayFragment extends BaseFragment {
    CustormLoadingButton okButton;
    TextView cardNumberText;
    TextView amountText;
    EditText passwordText;
    String orderNumber, orderSource, orderAmount;
    List<CardEntity> list;
    CardEntity currentCard;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nbkcardpay, container, false);
        okButton = (CustormLoadingButton) view.findViewById(R.id.nbkcardpay_okbtn);
        cardNumberText = (TextView) view.findViewById(R.id.nbkcardpay_cardnumber_text);
        amountText = (TextView) findViewById(R.id.nbkcardpay_amount_text);
        passwordText = (EditText) findViewById(R.id.nbkcardpay_password_text);
        view.findViewById(R.id.nbkcardpay_choosecard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), ChooseCardActivity.class).putExtra("cards", (Serializable) list), 0);
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okButton.showLoading("正在努力加载中···");
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
        try {
            getMyCards(getUser().getOpenId());
        } catch (JSONException e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            currentCard = (CardEntity) data.getSerializableExtra("card");
            cardNumberText.setText(currentCard.getCardNumber());
            cardNumberText.setText(currentCard.getCardNumber());
        }
    }

    private void getMyCards(String openId) throws JSONException {
        CardRequest request = new CardRequest(MyApplication.HOST + "/customer/cardList");
        request.setEntity(request.createCardListParams(openId));
        request.setOutTime(10 * 1000);
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        // 成功
                        Gson gson = new Gson();
                        list = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(), new TypeToken<List<CardEntity>>() {
                        }.getType());
                        if (list.size() > 0) {
                            currentCard = list.get(0);
                        }
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
}
