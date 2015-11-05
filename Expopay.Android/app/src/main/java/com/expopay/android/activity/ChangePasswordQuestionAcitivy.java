package com.expopay.android.activity;

import android.os.Bundle;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.request.PasswordRequest;
import com.expopay.android.view.CustormLoadingButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class ChangePasswordQuestionAcitivy extends BaseActivity {
    private CustormLoadingButton okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_changepasswordquestion);
    }


    private void getPasswordQuestion(String openId) throws JSONException {
        okBtn.showLoading("正在加载···");
        PasswordRequest request = new PasswordRequest("");
        request.setEntity(request.createPasswordQuestionsParams(openId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }

            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {


                    } else {
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
