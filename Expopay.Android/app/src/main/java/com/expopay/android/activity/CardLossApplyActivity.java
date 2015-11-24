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
    private EditText cardNumber;
//    private EditText cardPassword;
    private EditText lossCardReason;
    private CustormLoadingButton btnSubmitOnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("挂失卡片");
        setContentView(R.layout.activity_cardlossapply);

        cardNumber = (EditText) findViewById(R.id.cardLossApply_cardNumber);
//        etCardPassword = (EditText) findViewById(R.id.cardLossApply_cardPassword);
        lossCardReason = (EditText) findViewById(R.id.cardLossApply_lossCardReason);

        btnSubmitOnClick = (CustormLoadingButton) findViewById(R.id.btnSubmitOnClick);
        btnSubmitOnClick.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                finish();
            }

            @Override
            public void onFailureResult() {
                btnSubmitOnClick.showNormal("提交");
            }
        });
        btnSubmitOnClick.showNormal("提交");
        btnSubmitOnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
