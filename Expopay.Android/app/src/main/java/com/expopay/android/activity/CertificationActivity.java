package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.expopay.android.R;

public class CertificationActivity extends BaseActivity {

    private RelativeLayout rlCard;
    private EditText certificationName;
    private EditText certificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        initView();
    }

    protected void initView() {
        rlCard = (RelativeLayout) findViewById(R.id.rlCard);
        certificationName = (EditText) findViewById(R.id.certificationName);
        certificationID = (EditText) findViewById(R.id.certificationID);
    }

    public void btnBind(View view) {

    }

}
