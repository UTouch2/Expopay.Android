package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.PasswordQuestionEntity;
import com.expopay.android.model.UserEntity;
import com.expopay.android.request.CustomerRequest;
import com.expopay.android.view.CustormLoadingButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by misxu012 on 2015/11/9.
 */
public class PerfectAccountActivity extends BaseActivity {
    private EditText usernameText, passwordText, answerText;
    private TextView questionText;
    private CustormLoadingButton loadingButton;

    private PasswordQuestionEntity passwordQuestionEntity;
    private List<PasswordQuestionEntity> passwordQuestionEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_perfectaccount);
        usernameText = (EditText) findViewById(R.id.perfectaccount_username);
        passwordText = (EditText) findViewById(R.id.perfectaccount_password);
        answerText = (EditText) findViewById(R.id.perfectaccount_answer);
        questionText = (TextView) findViewById(R.id.perfectaccount_question);
        loadingButton = (CustormLoadingButton) findViewById(R.id.perfectaccount_ok);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();
                String questionId = passwordQuestionEntity.getSecuQuestionId();
                String answer = answerText.getText().toString().trim();
                try {
                    perfectAccountRequest(getUser().getOpenId(), userName, password, questionId, answer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        loadingButton.showNormal("确 定");
        loadingButton.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                finish();
            }

            @Override
            public void onFailureResult() {
                loadingButton.showNormal("确 定");
            }
        });
        try {
            getQustionRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            passwordQuestionEntity = (PasswordQuestionEntity) data.getSerializableExtra("question");
            questionText.setText(passwordQuestionEntity.getSecuQuestion());
        }
    }

    public void chooseQuestionOnclick(View v) {
        Intent intent = new Intent(this, ChooseQuestionActivity.class);
        intent.putExtra("questions", (Serializable) passwordQuestionEntities);
        startActivityForResult(intent, 0);
    }

    private void perfectAccountRequest(String openId, final String userName, final String loginPwd, String secuQuestionId, String secuAnswer) throws JSONException {
        loadingButton.showLoading("正在加载···");
        CustomerRequest request = new CustomerRequest(MyApplication.HOST + "/customer/accountSetting");
        request.setEntity(request.createPerfectAccountParams(openId, userName, loginPwd, secuQuestionId, secuAnswer));
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
                        loadingButton.showResult("true", true);
                        UserEntity userEntity = new UserEntity();
                        userEntity.setUserName(userName);
                        userEntity.setPassword(loginPwd);
                        saveUser(userEntity);
                        finish();
                    } else {
                        // 失败
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

    private void getQustionRequest() throws JSONException {
        CustomerRequest request = new CustomerRequest(MyApplication.HOST + "/system/secuQuestions");
        request.setEntity(request.createGetQustionParams());
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        Gson gson = new Gson();
                        passwordQuestionEntities = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(),
                                new TypeToken<List<PasswordQuestionEntity>>() {
                                }.getType());
                        if (passwordQuestionEntities.size() > 0) {
                            passwordQuestionEntity = passwordQuestionEntities.get(0);
                            questionText.setText(passwordQuestionEntity.getSecuQuestion());
                        }
                    } else {
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }

            @Override
            public void onFilure(Exception e) {

            }
        });
        request.execute();
        cancelRequest(request);
    }
}
