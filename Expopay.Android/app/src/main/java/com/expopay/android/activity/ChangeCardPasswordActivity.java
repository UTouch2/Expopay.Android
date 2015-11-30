package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.CardEntity;
import com.expopay.android.request.CardRequest;
import com.expopay.android.request.PasswordRequest;
import com.expopay.android.view.CustormLoadingButton;
import com.expopay.android.view.CustormLoadingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class ChangeCardPasswordActivity extends BaseActivity {
    CustormLoadingButton okBtn;
    TextView cardNumberText;
    ImageView showPsdImageView;
    EditText oldPwdtext, newPwdText;
    CustormLoadingView loadingView;
    List<CardEntity> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("更改卡密码");
        setContentView(R.layout.activity_changecardpassword);
        cardNumberText = (TextView) findViewById(R.id.changecardpassword_cardnumber);
        oldPwdtext = (EditText) findViewById(R.id.changecardpassword_oldpsd);
        newPwdText = (EditText) findViewById(R.id.changecardpassword_newpsd);
        loadingView = (CustormLoadingView) findViewById(R.id.changecardpassword_loadingview);
        showPsdImageView = (ImageView) findViewById(R.id.changecardpassword_timeout_btn);
        showPsdImageView.setOnClickListener(new View.OnClickListener() {
            boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    newPwdText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    showPsdImageView.setImageResource(R.mipmap.changepassword_icon);
                } else {
                    newPwdText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                    showPsdImageView.setImageResource(R.mipmap.changepassword_icon);
                }
                flag = !flag;
            }
        });

        okBtn = (CustormLoadingButton) findViewById(R.id.changecardpassword_ok);
        okBtn.showNormal("更改");
        okBtn.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                finish();
            }

            @Override
            public void onFailureResult() {
                okBtn.showNormal("更改");
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNumber = cardNumberText.getText().toString().trim();
                String oldpwd = oldPwdtext.getText().toString().trim();
                String newpwd = newPwdText.getText().toString().trim();
                try {
                    changePasswoedRequest(getUser().getOpenId(), cardNumber, oldpwd, newpwd);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            getMyCards(getUser().getOpenId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingView.setRetryOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getMyCards(getUser().getOpenId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void chooseCardOnclick(View v) {
        Intent intent =new Intent(this, ChooseCardActivity.class);
        intent.putExtra("cards",(Serializable)cards);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            CardEntity card = (CardEntity) data.getSerializableExtra("card");
            cardNumberText.setText(card.getCardNumber());
        }
    }

    private void changePasswoedRequest(String openId, String cardNumber, String loginPwd,
                                       String newLoginPwd) throws JSONException {
        okBtn.showLoading("正在加载···");
        PasswordRequest request = new PasswordRequest(MyApplication.HOST + "/card/resetcardpwd");
        request.setEntity(request.createChangeCardPasswordParams(openId, cardNumber, loginPwd, newLoginPwd));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                okBtn.showResult("网络请求失败", false);
            }
            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        okBtn.showResult("修改成功", true);
                    } else {
                        okBtn.showResult(json.getJSONObject("header").getString("desc"), false);
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

    private void getMyCards(String openId) throws JSONException {
        loadingView.show();
        loadingView.showLoading();
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
                        List<CardEntity> list = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(), new TypeToken<List<CardEntity>>() {
                        }.getType());
                        if (list.size() == 0) {
                            loadingView.setAddMessage("你还没有添加卡");
                            loadingView.showAdd();
                            loadingView.setAddOnclickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(ChangeCardPasswordActivity.this,AddCardActivity.class));
                                }
                            });
                        } else {
                            cards = list;
                            CardEntity card = list.get(0);
                            cardNumberText.setText(card.getCardNumber());
                            loadingView.dismiss();
                        }
                    } else {
                        // 失败
                        loadingView.showRetry();
                        loadingView.setRetryMessage(json.getJSONObject("header").getString("desc"));
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                    // 失败
                    loadingView.showRetry();
                    loadingView.setRetryMessage("数据解析异常");
                }
            }
            @Override
            public void onProgressUpdate(int i, int j) {

            }
            @Override
            public void onFilure(Exception result) {
                System.out.println(result);
                loadingView.showRetry();
                loadingView.setRetryMessage("请求失败");
            }
        });
        request.execute();
        cancelRequest(request);
    }
}
