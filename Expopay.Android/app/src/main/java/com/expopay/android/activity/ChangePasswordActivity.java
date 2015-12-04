package com.expopay.android.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.util.PatternUtil;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.request.PasswordRequest;
import com.expopay.android.view.CustormLoadingButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class ChangePasswordActivity extends BaseActivity {
    private CustormLoadingButton okBtn;
    private EditText newLoginPwdText, loginPwdText;
    private String newLoginPwd, loginPwd;

    private ImageView showPsdImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("更改登录密码");
        setContentView(R.layout.activity_changepassword);
        okBtn = (CustormLoadingButton) findViewById(R.id.changecardpassword_ok);
        loginPwdText = (EditText) findViewById(R.id.changepassword_oldpsd);
        newLoginPwdText = (EditText) findViewById(R.id.changepassword_newpsd);
        showPsdImageView = (ImageView) findViewById(R.id.changepassword_showpsd_btn);
        okBtn.showNormal("确定修改");
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPwd = loginPwdText.getText().toString().trim();
                newLoginPwd = newLoginPwdText.getText().toString().trim();
                PatternUtil.PassWord oldPwd = new PatternUtil.PassWord(loginPwd);
                PatternUtil.PassWord newPwd = new PatternUtil.PassWord(newLoginPwd);
                if (!oldPwd.isvalid()) {
                    Toast.makeText(ChangePasswordActivity.this, oldPwd.getErrMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!newPwd.isvalid()) {
                    Toast.makeText(ChangePasswordActivity.this, newPwd.getErrMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    changePasswoedRequest(getUser().getOpenId(), loginPwd, newLoginPwd);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        okBtn.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                finish();
            }

            @Override
            public void onFailureResult() {
                okBtn.showNormal("确定修改");
                okBtn.setBackgroundResource(R.drawable._button);
            }
        });
        showPsdImageView.setOnClickListener(new View.OnClickListener() {
            boolean flag = true;

            @Override
            public void onClick(View v) {
                if (flag) {
                    newLoginPwdText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    showPsdImageView.setImageResource(R.mipmap.changepassword_icon);
                } else {
                    newLoginPwdText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                    showPsdImageView.setImageResource(R.mipmap.changepassword_icon);
                }
                flag = !flag;
            }
        });
    }

    private void changePasswoedRequest(String openId, String loginPwd,
                                       String newLoginPwd) throws JSONException {
        okBtn.showLoading("正在加载···");
        PasswordRequest request = new PasswordRequest(MyApplication.HOST + "/customer/resetloginpwd");
        request.setEntity(request.createChangePasswordParams(openId, loginPwd, newLoginPwd));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                okBtn.showResult("网络请求失败", false);
                okBtn.setBackgroundColor(Color.parseColor("#ff0000"));
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
                        okBtn.setBackgroundColor(Color.parseColor("#ff0000"));
                    }
                } catch (JSONException e) {
                    okBtn.showResult("参数解析错误", false);
                    okBtn.setBackgroundColor(Color.parseColor("#ff0000"));
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
