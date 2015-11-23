package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.view.CustormLoadingButton;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class CardChargeActivity extends BaseActivity {
    private TextView cardNumberText, cardBalanceText;
    private EditText amountText;
    private String cardNumber, cardBalance;
    private CustormLoadingButton cardchargeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        statusBarCoverActivity();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardcharge);
        amountText = (EditText) findViewById(R.id.cardcharge_amount_text);
        cardNumberText = (TextView) findViewById(R.id.cardcharge_cardnumber_text);
        cardBalanceText = (TextView) findViewById(R.id.cardcharge_cardbalancer_text);
        cardchargeBtn = (CustormLoadingButton) findViewById(R.id.cardchargeBtn);
        cardchargeBtn.showNormal("充值");
        cardchargeBtn.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                finish();
            }

            @Override
            public void onFailureResult() {
                cardchargeBtn.showNormal("充值");
            }
        });
        Intent data = getIntent();
        cardNumber = data.getStringExtra("cardNumber");
        cardBalance = data.getStringExtra("cardBalance");
        cardNumberText.setText(cardNumber);
        cardBalanceText.setText(cardBalance);
    }

    public void closeOnclick(View v) {
        finish();
    }

    public void okOnclick(View v) {

    }

    public void amountClearOnclick(View v) {
        amountText.setText(null);
    }
}
