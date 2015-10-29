package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.expopay.android.Dialog.DialogFactory;
import com.expopay.android.Dialog.MyDialog;
import com.expopay.android.R;
import com.expopay.android.view.CustormLoadingButton;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class SettingsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_settings);
    }

    public void changeMobileOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), ChangeMobileActivity.class);
        startActivity(intent);
    }

    public void changeLoginPasswordOnclock(View v) {
        Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void changePayPasswordOnclock(View v) {
        Intent intent = new Intent(getApplicationContext(), ChangePayPasswordActivity.class);
        startActivity(intent);
    }

    public void changePasswordQuestionOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), ChangePasswordQuestionAcitivy.class);
        startActivity(intent);
    }

    public void autoPayOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void addressOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), MyAddressActivity.class);
        startActivity(intent);
    }

    public void cardPasswordOnclock(View v) {
        Intent intent = new Intent(getApplicationContext(), ChangeCardPasswordActivity.class);
        startActivity(intent);
    }

    public void exitOnclick(View v){
        MyDialog dialog =  DialogFactory.createDialog(this,"提示","您确认要退出吗？");
        dialog.setOkOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.show();
    }
}
