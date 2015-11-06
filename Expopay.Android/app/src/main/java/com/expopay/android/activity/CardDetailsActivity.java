package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class CardDetailsActivity extends BaseActivity {
    private Button incomeBtn, payoutBtn;
    private String cardNumber, cardBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carddetails);
        incomeBtn = (Button) findViewById(R.id.carddetails_incomebtn);
        payoutBtn = (Button) findViewById(R.id.carddetails_payoutbtn);
        incomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentBtn(1);
            }
        });
        payoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentBtn(0);
            }
        });
        cardNumber = "1231231231231";
        cardBalance = "12.00";
    }

    private void setCurrentBtn(int i) {
        if (i == 0) {
            payoutBtn.setBackgroundColor(Color.parseColor("#959595"));
            payoutBtn.setTextColor(Color.parseColor("#ffffff"));
            incomeBtn.setBackgroundColor(Color.parseColor("#ffffff"));
            incomeBtn.setTextColor(Color.parseColor("#959595"));
        } else if (i == 1) {
            incomeBtn.setBackgroundColor(Color.parseColor("#959595"));
            incomeBtn.setTextColor(Color.parseColor("#ffffff"));
            payoutBtn.setBackgroundColor(Color.parseColor("#ffffff"));
            payoutBtn.setTextColor(Color.parseColor("#959595"));
        }
    }

    public void certificationOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), CertificationActivity.class);
        intent.putExtra("cardNumber", cardNumber);
        startActivity(intent);
    }

    public void deleteOnclick(View v) {
        
    }

    public void chargeOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), CardChargeActivity.class);
        intent.putExtra("cardNumber", cardNumber);
        intent.putExtra("cardBalance", cardBalance);
        startActivity(intent);
    }
}
