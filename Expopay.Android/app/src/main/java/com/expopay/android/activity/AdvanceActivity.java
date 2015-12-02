package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.expopay.android.R;
import com.expopay.android.view.CustormLoadingButton;

/**
 * Created by misxu012 on 2015/10/29.
 */
public class AdvanceActivity extends BaseActivity {
    CustormLoadingButton loadingButton;
    EditText contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("反馈意见");
        setContentView(R.layout.activity_advance);
        loadingButton = (CustormLoadingButton) findViewById(R.id.advance_ok);
        contentText = (EditText) findViewById(R.id.advance_content);
        loadingButton.showNormal("提  交");
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        loadingButton.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {

            }

            @Override
            public void onFailureResult() {

            }
        });
    }

    private void commitAdvanceRequest(){
        loadingButton.showLoading("");
    }
}
