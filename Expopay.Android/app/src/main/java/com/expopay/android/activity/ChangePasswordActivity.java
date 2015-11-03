package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.request.CustomerRequest;
import com.expopay.android.view.CustormLoadingButton;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class ChangePasswordActivity extends BaseActivity {
    CustormLoadingButton okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_changepassword);
        okBtn = (CustormLoadingButton)findViewById(R.id.changecardpassword_ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okBtn.showLoading("正在加载···");
            }
        });
    }


    private void changePasswoedRequest() {
        CustomerRequest request = new CustomerRequest("");
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
