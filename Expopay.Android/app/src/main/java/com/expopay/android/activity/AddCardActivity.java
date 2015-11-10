package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.UserEntity;
import com.expopay.android.request.CardRequest;
import com.expopay.android.view.CustormLoadingButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by misxu012 on 2015/11/5.
 */
public class AddCardActivity extends BaseActivity {
    private CustormLoadingButton loadingButton;
    private EditText cardnumberText, cardPsdText;
    private String cardNumber, cardPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_addcard);
        cardnumberText = (EditText) findViewById(R.id.addcard_cardnumber);
        cardPsdText = (EditText) findViewById(R.id.addcard_cardpassword);
        loadingButton = (CustormLoadingButton) findViewById(R.id.addcard_ok);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNumber = cardnumberText.getText().toString().trim();
                cardPassword = cardPsdText.getText().toString().trim();
                try {
                    addCardRequest(getUser().getOpenId(), cardNumber, cardPassword);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        loadingButton.showNormal("绑定");
    }

    private void addCardRequest(String openId, String cardNumber, String password) throws JSONException {
        loadingButton.showLoading("正在绑定···");
        CardRequest request = new CardRequest(MyApplication.HOST + "/customer/addcard");
        request.setEntity(request.createAddCardParams(openId, cardNumber, password));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                loadingButton.showResult("网络请求失败", false);
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        loadingButton.showResult("绑定成功", true);
                    } else {
                        // 失败
                        loadingButton.showResult(json.getJSONObject("header").getString("desc"), false);
                    }
                } catch (JSONException e) {
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
