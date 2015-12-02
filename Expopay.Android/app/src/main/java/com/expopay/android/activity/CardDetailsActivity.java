package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.MainPagerAdepter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.fragment.CardIncomeFragment;
import com.expopay.android.fragment.CardPayoutFragment;
import com.expopay.android.model.CardDetailsEntity;
import com.expopay.android.model.CardEntity;
import com.expopay.android.request.CardRequest;
import com.expopay.android.view.CustormLoadingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class CardDetailsActivity extends BaseActivity {
    private CardEntity cardEntity;
    private CardDetailsEntity cardDetailsEntity;

    private Button incomeBtn, payoutBtn;
    private TextView cardnumberText, balancetext, cardTypeText, incomeText, payoutText;
    private ViewPager viewPager;
    private CustormLoadingView loadingView;

    public CardDetailsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("卡片详情");
        setContentView(R.layout.activity_carddetails);
        loadingView = (CustormLoadingView) findViewById(R.id.carddetails_loadingview);
        incomeBtn = (Button) findViewById(R.id.carddetails_incomebtn);
        payoutBtn = (Button) findViewById(R.id.carddetails_payoutbtn);

        cardnumberText = (TextView) findViewById(R.id.carddetails_cardnumber);
        balancetext = (TextView) findViewById(R.id.carddetails_balance);
        cardTypeText = (TextView) findViewById(R.id.carddetails_cardtype);
        incomeText = (TextView) findViewById(R.id.carddetails_income);
        payoutText = (TextView) findViewById(R.id.carddetails_payout);
        viewPager = (ViewPager) findViewById(R.id.carddetails_viewpager);
        cardEntity = (CardEntity) getIntent().getSerializableExtra("card");
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
        loadingView.setRetryOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cardDetailsRequest(getUser().getOpenId(), cardEntity.getCardNumber());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            cardDetailsRequest(getUser().getOpenId(), cardEntity.getCardNumber());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        intent.putExtra("cardNumber", cardEntity.getCardNumber());
        startActivity(intent);
    }

    public void deleteOnclick(View v) {
        try {
            deleteCardRequest(getUser().getOpenId(), cardEntity.getCardNumber());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void chargeOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), CardChargeActivity.class);
        intent.putExtra("cardNumber", cardEntity.getCardNumber());
        intent.putExtra("cardBalance", cardEntity.getBalance());
        startActivity(intent);
    }

    private void deleteCardRequest(String openId, String cardNum) throws JSONException {
        CardRequest request = new CardRequest(MyApplication.HOST + "/customer/deletecard");
        request.setEntity(request.createDeleteCardParams(openId, cardNum));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                System.out.print(e);
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        // 成功
                        Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                    }
                } catch (JSONException e) {
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }

    private void cardDetailsRequest(String openId, String cardNum) throws JSONException {
        loadingView.show();
        loadingView.showLoading();
        CardRequest request = new CardRequest(MyApplication.HOST + "/card/cardInfo");
        request.setEntity(request.createCardDetailsParams(openId, cardNum));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                loadingView.showRetry();
                loadingView.setRetryMessage("网络请求失败");
            }
            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        // 成功
                        Gson gson = new Gson();
                        cardDetailsEntity = gson.fromJson(json.getJSONObject("body").toString(), new TypeToken<CardDetailsEntity>() {
                        }.getType());
                        cardnumberText.setText(cardEntity.getCardNumber());
                        cardTypeText.setText(cardEntity.getCardType());
                        balancetext.setText(cardEntity.getBalance());
                        incomeText.setText(cardDetailsEntity.getChargeAmount());
                        payoutText.setText(cardDetailsEntity.getConsumeAmount());
                        Fragment incomeFragment = new CardIncomeFragment();
                        Bundle incomeBundle = new Bundle();
                        incomeBundle.putSerializable("records", (Serializable) cardDetailsEntity.getChargeRecords());
                        incomeFragment.setArguments(incomeBundle);
                        Fragment payoutFragment = new CardPayoutFragment();
                        Bundle payoutBundle = new Bundle();
                        payoutBundle.putSerializable("records", (Serializable) cardDetailsEntity.getConsumeRecords());
                        incomeFragment.setArguments(payoutBundle);
                        viewPager.setAdapter(new MainPagerAdepter(getSupportFragmentManager(), new Fragment[]{incomeFragment, payoutFragment}));
                        loadingView.dismiss();
                    } else {
                        loadingView.showRetry();
                        loadingView.setRetryMessage(json.getJSONObject("header").getString("desc"));
                    }
                } catch (JSONException e) {
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }
}
