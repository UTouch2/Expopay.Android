package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.PasswordQuestionEntity;
import com.expopay.android.request.CustomerRequest;
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
public class ChangePasswordQuestionAcitivy extends BaseActivity {

    private List<PasswordQuestionEntity> passwordQuestionEntities;
    private PasswordQuestionEntity oldQuestion, newQuestion;
    private CustormLoadingView loadingView;
    private CustormLoadingButton okBtn;
    private TextView newQuestionText, oldQuestionText;
    private EditText newAnswerText, oldAnswerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("更改密保问题");
        setContentView(R.layout.activity_changepasswordquestion);
        loadingView = (CustormLoadingView) findViewById(R.id.changepasswordquestion_loadingview);
        okBtn = (CustormLoadingButton) findViewById(R.id.changepasswordquestion_ok);
        okBtn.showNormal("确认修改");
        okBtn.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                finish();
            }

            @Override
            public void onFailureResult() {
                okBtn.showNormal("确认修改");
            }
        });
        oldQuestionText = (TextView) findViewById(R.id.changepasswordquestion_oldpsdq);
        newQuestionText = (TextView) findViewById(R.id.changepasswordquestion_newpsdq);

        oldAnswerText = (EditText) findViewById(R.id.changepasswordquestion_oldpsda);
        newAnswerText = (EditText) findViewById(R.id.changepasswordquestion_newpsda);
        loadingView.setRetryOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getQustionRequest();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldAnswer = oldAnswerText.getText().toString().trim();
                String newAnswer = newAnswerText.getText().toString().trim();
                if ("".equals(oldAnswer) || "".equals(newAnswer)) {
                    Toast.makeText(ChangePasswordQuestionAcitivy.this, "密保答案不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    changeQuestionRequest(getUser().getOpenId(), oldQuestion.getSecuQuestionId(), oldAnswer, newQuestion.getSecuQuestionId(), newAnswer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            getQustionRequest();
            getMyQuestionRequest(getUser().getOpenId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void chooseNewQuestionOnclick(View v) {
        Intent intent = new Intent(this, ChooseQuestionActivity.class);
        intent.putExtra("questions", (Serializable) passwordQuestionEntities);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                newQuestion = (PasswordQuestionEntity) data.getSerializableExtra("question");
                newQuestionText.setText(newQuestion.getSecuQuestion());
            }
        }
    }

    /**
     * 获取预设密保问题
     *
     * @throws JSONException
     */
    private void getMyQuestionRequest(String openId) throws JSONException {
        loadingView.showLoading();
        PasswordRequest request = new PasswordRequest(MyApplication.HOST + "/customer/customerquestion");
        request.setEntity(request.createPasswordQuestionsParams(openId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                loadingView.showRetry();
                loadingView.setRetryMessage("网络请求失败");
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        Gson gson = new Gson();
                        oldQuestion = gson.fromJson(json.getJSONObject("body").toString(),
                                new TypeToken<PasswordQuestionEntity>() {
                                }.getType());
                        oldQuestionText.setText(oldQuestion.getSecuQuestion());
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

    /**
     * 获取所有密保问题
     *
     * @throws JSONException
     */
    private void getQustionRequest() throws JSONException {
        loadingView.show();
        loadingView.showLoading();
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
                        newQuestion = passwordQuestionEntities.get(0);
                        newQuestionText.setText(newQuestion.getSecuQuestion());
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

            @Override
            public void onFilure(Exception e) {
                loadingView.showRetry();
                loadingView.setRetryMessage("网络请求失败");
            }
        });
        request.execute();
        cancelRequest(request);
    }

    private void changeQuestionRequest(String openId,
                                       String secuQuestionId,
                                       String secuAnswer,
                                       String newSecuQuestionId, String newSecuAnswer) throws JSONException {
        okBtn.showLoading("正在努力加载中···");
        PasswordRequest request = new PasswordRequest(MyApplication.HOST + "/customer/resetsecuquest");
        request.setEntity(request.createChangePasswordQuestionParams(openId, secuQuestionId, secuAnswer, newSecuQuestionId, newSecuAnswer));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                okBtn.showResult("网络请求失败", false);
                okBtn.setBackgroundColor(Color.parseColor("#EC4545"));
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code").equals("0000")) {
                        okBtn.showResult("修改成功", true);
                    } else {
                        okBtn.showResult(json.getJSONObject("header").getString("desc"), false);
                        okBtn.setBackgroundColor(Color.parseColor("#EC4545"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    okBtn.showResult("参数解析异常", false);
                    okBtn.setBackgroundColor(Color.parseColor("#EC4545"));
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
