package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;

import com.expopay.android.R;
import com.expopay.android.view.CustormLoadingButton;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class ChangeCardPasswordActivity extends BaseActivity {
    CustormLoadingButton loadingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_changecardpassword);
        loadingButton = (CustormLoadingButton) findViewById(R.id.changecardpassword_ok);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void chooseCardOnclick(View v) {

    }


}
