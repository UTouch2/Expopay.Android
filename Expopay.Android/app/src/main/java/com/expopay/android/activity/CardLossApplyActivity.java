package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.expopay.android.R;
import com.expopay.android.view.CustormLoadingButton;

/**
 * Created by misxu012 on 2015/11/5.
 */
public class CardLossApplyActivity extends BaseActivity {
    private EditText etCardNumber;
    private EditText etCardPassword;
    private EditText etLossCardReason;
    private CustormLoadingButton btnSubmitOnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_cardlossapply);

        etCardNumber = (EditText) findViewById(R.id.etCardNumber);
//        etCardPassword = (EditText) findViewById(R.id.etCardPassword);
        etLossCardReason = (EditText) findViewById(R.id.etLossCardReason);

        btnSubmitOnClick = (CustormLoadingButton) findViewById(R.id.btnSubmitOnClick);
        btnSubmitOnClick.showNormal("提交");
        btnSubmitOnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
