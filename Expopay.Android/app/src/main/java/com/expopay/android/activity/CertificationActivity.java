package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.expopay.android.R;
import com.expopay.android.view.CustormLoadingButton;

public class CertificationActivity extends BaseActivity {

    private RelativeLayout rlCard;
    private EditText certificationName;
    private EditText certificationID;
    private CustormLoadingButton btnBind;

    protected void initView() {
        rlCard = (RelativeLayout) findViewById(R.id.rlCard);
        certificationName = (EditText) findViewById(R.id.certificationName);
        certificationID = (EditText) findViewById(R.id.certificationID);
        btnBind = (CustormLoadingButton) findViewById(R.id.btnBind);
        btnBind.showNormal("绑定");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_certification);
        initView();

        rlCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
