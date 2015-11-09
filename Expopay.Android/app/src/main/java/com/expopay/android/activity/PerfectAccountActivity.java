package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.request.CustomerRequest;
import com.expopay.android.view.CustormLoadingButton;

/**
 * Created by misxu012 on 2015/11/9.
 */
public class PerfectAccountActivity extends BaseActivity {
    private EditText usernameText, passwordText, answerText;
    private TextView questionText, statusText;

    private CustormLoadingButton loadingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfectaccount);
        usernameText = (EditText) findViewById(R.id.perfectaccount_username);
        passwordText = (EditText) findViewById(R.id.perfectaccount_password);
        answerText = (EditText) findViewById(R.id.perfectaccount_answer);
        questionText = (TextView) findViewById(R.id.perfectaccount_question);
        statusText = (TextView) findViewById(R.id.perfectaccount_status);
        loadingButton = (CustormLoadingButton) findViewById(R.id.perfectaccount_ok);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        loadingButton.showNormal("确 定");
    }

    private void perfectAccountRequest() {
        CustomerRequest request = new CustomerRequest(MyApplication.HOST + "");
        request.setEntity("");
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }
}
