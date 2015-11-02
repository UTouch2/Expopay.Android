package com.expopay.android.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.expopay.android.R;
import com.expopay.android.fragment.RepaymentFragment;
import com.expopay.android.fragment.UnrepaymentFragment;

public class MyBillsActivity extends BaseActivity implements View.OnClickListener {

    private RepaymentFragment repaymentFragment;
    private UnrepaymentFragment unrepaymentFragment;
    private FragmentManager fragmentManager;
    private Button btn_unrepayment;
    private Button btn_repayment;

    private void assignViews() {
        btn_unrepayment = (Button) findViewById(R.id.btn_unrepayment);
        btn_repayment = (Button) findViewById(R.id.btn_repayment);

        btn_unrepayment.setOnClickListener(this);
        btn_repayment.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_my_bills);

        assignViews();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_unrepayment:
                setTabSelection(0);
                break;
            case R.id.btn_repayment:
                setTabSelection(1);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                btn_unrepayment.setBackgroundColor(Color.parseColor("#BCBCBC"));
                if (unrepaymentFragment == null) {
                    unrepaymentFragment = new UnrepaymentFragment();
                    transaction.add(R.id.bill_content, unrepaymentFragment);
                } else {
                    transaction.show(unrepaymentFragment);
                }
                break;
            default:
                btn_repayment.setBackgroundColor(Color.parseColor("#BCBCBC"));
                if (repaymentFragment == null) {
                    repaymentFragment = new RepaymentFragment();
                    transaction.add(R.id.bill_content, repaymentFragment);
                } else {
                    transaction.show(repaymentFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void clearSelection() {
        btn_unrepayment.setBackgroundColor(Color.WHITE);
        btn_unrepayment.setTextColor(Color.BLACK);
        btn_repayment.setBackgroundColor(Color.WHITE);
        btn_repayment.setTextColor(Color.BLACK);
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (repaymentFragment != null) {
            transaction.hide(repaymentFragment);
        }
        if (unrepaymentFragment != null) {
            transaction.hide(unrepaymentFragment);
        }
    }
}
