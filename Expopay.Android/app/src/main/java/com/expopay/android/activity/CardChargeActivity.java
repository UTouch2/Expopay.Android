package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.expopay.android.R;

import java.nio.charset.CoderResult;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class CardChargeActivity extends BaseActivity {
    private TextView cardNumberText, cardBalanceText;
    private EditText amountText;
    private String cardNumber, cardBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        statusBarCoverActivity();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardcharge);
        amountText = (EditText) findViewById(R.id.cardcharge_amount_text);
        cardNumberText = (TextView) findViewById(R.id.cardcharge_cardnumber_text);
        cardBalanceText = (TextView) findViewById(R.id.cardcharge_cardbalancer_text);
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
