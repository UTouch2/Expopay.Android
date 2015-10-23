package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class SettingsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void changeMobileOnclick(View v) {
        Intent intent =new Intent(getApplicationContext(),ChangeMobileActivity.class);
        startActivity(intent);
    }
    public void changeLoginPasswordOnclock(View v) {
        Intent intent =new Intent(getApplicationContext(),ChangePasswordActivity.class);
        startActivity(intent);
    }
    public void changePayPasswordOnclock(View v) {
        Intent intent =new Intent(getApplicationContext(),ChangePayPasswordActivity.class);
        startActivity(intent);
    }
    public void changePasswordQuestionOnclick(View v) {
        Intent intent =new Intent(getApplicationContext(),ChangePasswordQuestionAcitivy.class);
        startActivity(intent);
    }
    public void autoPayOnclick(View v) {
        Intent intent =new Intent(getApplicationContext(),ChangePasswordActivity.class);
        startActivity(intent);
    }

    public void addressOnclick(View v){

    }

    public void cardPasswordOnclock(View v){

    }
}
