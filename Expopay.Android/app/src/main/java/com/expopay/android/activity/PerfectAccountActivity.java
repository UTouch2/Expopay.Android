package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.android.kechong.lib.util.PatternUtil.checkPwd;
import static com.android.kechong.lib.util.PatternUtil.checkUserName;

/**
 * Created by misxu012 on 2015/11/9.
 */
public class PerfectAccountActivity extends BaseActivity {
    private EditText usernameText, passwordText, answerText;
    private TextView questionText;
    private CustormLoadingButton loadingButton;
    private ImageView showPsdImageView;

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
        showPsdImageView = (ImageView) findViewById(R.id.perfectaccount_showpsd_btn);
        showPsdImageView.setOnClickListener(new View.OnClickListener() {
            boolean flag = true;
            @Override
            public void onClick(View v) {
                if (flag) {
                    passwordText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    showPsdImageView.setImageResource(R.mipmap.changepassword_icon);
                } else {
                    passwordText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                    showPsdImageView.setImageResource(R.mipmap.changepassword_icon);
                }
                flag = !flag;
            }
        });
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = usernameText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();
                String questionId = passwordQuestionEntity.getSecuQuestionId();
                String answer = answerText.getText().toString().trim();
                if ((2 > userName.length()) || (11 < userName.length()) || (!checkUserName(userName))) {
                    Toast.makeText(PerfectAccountActivity.this, "请输入正确的电话号码或用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (6>password.length() || 16<password.length() || (!checkPwd(password))) {
                   Toast.makeText(PerfectAccountActivity.this, "密码长度在6-16位,并且只能是数字和字母组合", Toast.LENGTH_SHORT).show();
                    return;
                }
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void perfectAccountRequest(String openId, final String userName, final String loginPwd, String secuQuestionId, String secuAnswer) throws JSONException {
        loadingButton.showLoading("正在加载···");
        CustomerRequest request = new CustomerRequest(MyApplication.HOST + "/customer/accountSetting");
        request.setEntity(request.createPerfectAccountParams(openId, userName, loginPwd, secuQuestionId, secuAnswer));
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
                        loadingButton.showResult("true", true);
                        UserEntity userEntity = new UserEntity();
                        userEntity.setUserName(userName);
                        userEntity.setPassword(loginPwd);
                        saveUser(userEntity);
                        finish();
                    } else {
                        // 失败
                        loadingButton.showResult(json.getJSONObject("header").getString("desc"), false);
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                    loadingButton.showResult("参数解析异常", false);
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
