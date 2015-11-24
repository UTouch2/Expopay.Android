package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.PasswordQuestionEntity;
import com.expopay.android.request.PasswordRequest;
import com.expopay.android.view.CustormLoadingButton;
import com.expopay.android.view.CustormLoadingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by misxu012 on 2015/11/23.
 */
public class AddPayPasswordActivity extends BaseActivity {
    private EditText pwdText, confirmPwdText;
    private TextView pwdQuestionText;
    private EditText answerText;
    private CustormLoadingView loadingView;
    private PasswordQuestionEntity entity;
    private CustormLoadingButton loadingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("设置支付密码");
        setContentView(R.layout.activity_addpaypassword);
        pwdText = (EditText) findViewById(R.id.addpaypassword_oldpsd);
        confirmPwdText = (EditText) findViewById(R.id.addpaypassword_newpsd);
        answerText = (EditText) findViewById(R.id.addpaypassword_psdanswer);
        pwdQuestionText = (TextView) findViewById(R.id.addpaypassword_psdquestion);
        loadingButton = (CustormLoadingButton) findViewById(R.id.addpaypassword_ok);
        loadingView = (CustormLoadingView) findViewById(R.id.addpaypassword_loadingview);
        loadingView.setRetryOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getQuestionRequest(getUser().getOpenId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = answerText.getText().toString().trim();
                String payPwd = pwdText.getText().toString().trim();
                String confirmPayPwd = confirmPwdText.getText().toString().trim();
                if (!payPwd.equals(confirmPayPwd)) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    addPayPwdRequest(getUser().getOpenId(), entity.getSecuQuestionId(), answer, payPwd);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            getQuestionRequest(getUser().getOpenId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getQuestionRequest(String openId) throws JSONException {
        loadingView.showLoading();
        PasswordRequest request = new PasswordRequest(MyApplication.HOST + "/customer/customerquestion");
        request.setEntity(request.createPasswordQuestionsParams(openId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }
            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        Gson gson = new Gson();
                        entity = gson.fromJson(json.getJSONObject("body").toString(),
                                new TypeToken<PasswordQuestionEntity>() {
                                }.getType());
                        pwdQuestionText.setText(entity.getSecuQuestion());
                        loadingView.dismiss();
                    } else {
                        loadingView.showRetry();
                        loadingView.setRetryMessage(json.getJSONObject("header").getString("desc"));
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                    loadingView.showRetry();
                    loadingView.setRetryMessage("数据解析异常");
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }

    private void addPayPwdRequest(String openId,
                                  String secuQuestionId,
                                  String secuAnswer,
                                  String payPwd) throws JSONException {
        loadingButton.showLoading("正在加载···");
        PasswordRequest request = new PasswordRequest(MyApplication.HOST + "/customer/setpaypwd");
        request.setEntity(request.createSetPayPasswordParams(openId, secuQuestionId, secuAnswer, payPwd));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                loadingButton.showResult("网络请求失败", true);
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        loadingButton.showResult("", true);
                    } else {
                        loadingButton.showResult(json.getJSONObject("header").getString("desc"),false);
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                    loadingButton.showResult("参数解析异常", true);
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
