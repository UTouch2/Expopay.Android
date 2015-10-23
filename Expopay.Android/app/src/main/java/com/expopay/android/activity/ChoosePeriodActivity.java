package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.expopay.android.R;

public class ChoosePeriodActivity extends BaseActivity implements View.OnClickListener{

    private String str_periods;

    private ImageButton imageButton;
    private Button installment3;
    private Button installment6;
    private Button installment9;
    private Button installment12;
    private Button installment15;
    private Button installment18;
    private Button installment24;
    private Button btnSureInstallment;

    Intent intent = new Intent();

    private void assignViews() {
        imageButton = (ImageButton) findViewById(R.id.ibCancle);
        installment3 = (Button) findViewById(R.id.installment3);
        installment6 = (Button) findViewById(R.id.installment6);
        installment9 = (Button) findViewById(R.id.installment9);
        installment12 = (Button) findViewById(R.id.installment12);
        installment15 = (Button) findViewById(R.id.installment15);
        installment18 = (Button) findViewById(R.id.installment18);
        installment24 = (Button) findViewById(R.id.installment24);
        btnSureInstallment = (Button) findViewById(R.id.btnSureInstallment);

        imageButton.setOnClickListener(this);
        installment3.setOnClickListener(this);
        installment6.setOnClickListener(this);
        installment9.setOnClickListener(this);
        installment12.setOnClickListener(this);
        installment15.setOnClickListener(this);
        installment18.setOnClickListener(this);
        installment24.setOnClickListener(this);
        btnSureInstallment.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_period);
        assignViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.installment3:
                setTabSelection(3);
                str_periods=installment3.getText().toString();
                break;
            case R.id.installment6:
                setTabSelection(6);
                str_periods=installment6.getText().toString();
                break;
            case R.id.installment9:
                setTabSelection(9);
                str_periods=installment9.getText().toString();
                break;
            case R.id.installment12:
                setTabSelection(12);
                str_periods=installment12.getText().toString();
                break;
            case R.id.installment15:
                setTabSelection(15);
                str_periods=installment15.getText().toString();
                break;
            case R.id.installment18:
                setTabSelection(18);
                str_periods=installment18.getText().toString();
                break;
            case R.id.installment24:
                setTabSelection(24);
                str_periods=installment24.getText().toString();
                break;
            default:
                intent.putExtra("str_periods", str_periods);
                setResult(1, intent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        intent.putExtra("str_periods", str_periods);
        setResult(1, intent);
        finish();
    }

    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        switch (index) {
            case 3:
                // 当点击了按钮installment3时，改变控件的背景图片
                installment3.setBackgroundResource(R.mipmap.mall_outline);
                break;
            case 6:
                installment6.setBackgroundResource(R.mipmap.mall_outline);
                break;
            case 9:
                installment9.setBackgroundResource(R.mipmap.mall_outline);
                break;
            case 12:
                installment12.setBackgroundResource(R.mipmap.mall_outline);
                break;
            case 15:
                installment15.setBackgroundResource(R.mipmap.mall_outline);
                break;
            case 18:
                installment18.setBackgroundResource(R.mipmap.mall_outline);
                break;
            case 24:
                installment24.setBackgroundResource(R.mipmap.mall_outline);
                break;
            default:

                break;
        }
    }

    //清除掉所有的选中状态
    private void clearSelection() {
        installment3.setBackgroundResource(R.mipmap.mall_unoutline);
        installment6.setBackgroundResource(R.mipmap.mall_unoutline);
        installment9.setBackgroundResource(R.mipmap.mall_unoutline);
        installment12.setBackgroundResource(R.mipmap.mall_unoutline);
        installment15.setBackgroundResource(R.mipmap.mall_unoutline);
        installment18.setBackgroundResource(R.mipmap.mall_unoutline);
        installment24.setBackgroundResource(R.mipmap.mall_unoutline);
    }

}
