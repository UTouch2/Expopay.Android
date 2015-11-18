package com.expopay.android.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.MainPagerAdepter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.fragment.RepaymentedFragment;
import com.expopay.android.fragment.RepaymentFragment;
import com.expopay.android.model.BillEntity;
import com.expopay.android.request.OrderRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class MyBillsActivity extends BaseActivity {

    private Button repaymentBtn;
    private Button repaymentedBtn;
    private ViewPager viewPager;

    private TextView creditAmountText, billAmountText, repaymentAmountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("我的账单");
        setContentView(R.layout.activity_my_bills);
        initView();
        setTabSelection(0);
        try {
            getBillsRequest(getUser().getOpenId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void initView() {
        repaymentBtn = (Button) findViewById(R.id.mybills_repayment);
        repaymentedBtn = (Button) findViewById(R.id.mybills_repaymented);

        creditAmountText = (TextView) findViewById(R.id.mybills_creditamount);
        billAmountText = (TextView) findViewById(R.id.mybills_billamount);
        repaymentAmountText = (TextView) findViewById(R.id.mybills_repaymentamount);

        viewPager = (ViewPager) findViewById(R.id.bill_content);
        viewPager.setOnPageChangeListener(new AbsOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
                super.onPageScrollStateChanged(arg0);
                setTabSelection(viewPager.getCurrentItem());
            }
        });
        repaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTabSelection(0);
            }
        });
        repaymentedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTabSelection(1);
            }
        });
    }

    private void setTabSelection(int index) {
        if (0 == index) {
            repaymentBtn.setBackgroundColor(Color.parseColor("#BCBCBC"));
            repaymentBtn.setTextColor(Color.WHITE);
            repaymentedBtn.setBackgroundColor(Color.WHITE);
            repaymentedBtn.setTextColor(Color.parseColor("#666666"));
        } else {
            repaymentedBtn.setBackgroundColor(Color.parseColor("#BCBCBC"));
            repaymentedBtn.setTextColor(Color.WHITE);
            repaymentBtn.setBackgroundColor(Color.WHITE);
            repaymentBtn.setTextColor(Color.parseColor("#666666"));
        }
        viewPager.setCurrentItem(index);
    }

    private void getBillsRequest(String openId) throws JSONException {
        OrderRequest request = new OrderRequest(MyApplication.HOST + "/credit/bills");
        request.setEntity(request.createGetBillsParams(openId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code").equals("0000")) {
                        Gson gson = new Gson();
                        BillEntity bills = gson.fromJson(json.getJSONObject("body").toString(),
                                new TypeToken<BillEntity>() {
                                }.getType());

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

    private void setBill(BillEntity entity) {
        creditAmountText.setText(entity.getCreditLimitAmt());
        billAmountText.setText(entity.getBillAmount());
        repaymentAmountText.setText(entity.getRepaymentAmt());
        RepaymentFragment a = new RepaymentFragment();
        Bundle ab = new Bundle();
        ab.putSerializable("records", (Serializable) entity.getRepaymentBills());
        a.setArguments(ab);
        RepaymentedFragment b = new RepaymentedFragment();
        Bundle bb = new Bundle();
        bb.putSerializable("records", (Serializable) entity.getRepaymentedBills());
        a.setArguments(bb);
        viewPager.setAdapter(new MainPagerAdepter(getSupportFragmentManager(), new Fragment[]{a, b}));
    }

}
