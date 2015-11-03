package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.CardEntity;
import com.expopay.android.view.CustormLoadingButton;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class ChangeCardPasswordActivity extends BaseActivity {
    CustormLoadingButton loadingButton;
    TextView cardNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_changecardpassword);
        cardNumberText = (TextView) findViewById(R.id.changecardpassword_cardnumber);
        loadingButton = (CustormLoadingButton) findViewById(R.id.changecardpassword_ok);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void chooseCardOnclick(View v) {
        startActivityForResult(new Intent(this, ChooseCardActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            CardEntity card = (CardEntity) data.getSerializableExtra("card");
            cardNumberText.setText(card.getCardNumber());
        }
    }
}
