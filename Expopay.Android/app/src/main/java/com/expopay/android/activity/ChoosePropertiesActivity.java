package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.expopay.android.R;

public class ChoosePropertiesActivity extends BaseActivity implements View.OnClickListener{

    private static String str_colour = "黑色";
    private static String str_g = "16G";

    private ImageButton imgBtnCancle;
    private Button colourBlack;
    private Button colourWhite;
    private Button colourGolden;
    private Button colourGoldPink;
    private Button g16;
    private Button g64;
    private Button g128;
    private Button btnSureStyle;

    Intent intent=new Intent();

    private void assignViews() {
        imgBtnCancle = (ImageButton) findViewById(R.id.imgBtnCancle);
        colourBlack = (Button) findViewById(R.id.colourBlack);
        colourWhite = (Button) findViewById(R.id.colourWhite);
        colourGolden = (Button) findViewById(R.id.colourGolden);
        colourGoldPink = (Button) findViewById(R.id.colourGoldPink);
        g16 = (Button) findViewById(R.id.g16);
        g64 = (Button) findViewById(R.id.g64);
        g128 = (Button) findViewById(R.id.g128);
        btnSureStyle = (Button) findViewById(R.id.btnSureStyle);

        imgBtnCancle.setOnClickListener(this);
        colourBlack.setOnClickListener(this);
        colourWhite.setOnClickListener(this);
        colourGolden.setOnClickListener(this);
        colourGoldPink.setOnClickListener(this);
        g16.setOnClickListener(this);
        g64.setOnClickListener(this);
        g128.setOnClickListener(this);
        btnSureStyle.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_properties);
        assignViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.colourBlack:
                setColourSelection(1);
                str_colour=colourBlack.getText().toString();
                break;
            case R.id.colourWhite:
                setColourSelection(2);
                str_colour=colourWhite.getText().toString();
                break;
            case R.id.colourGolden:
                setColourSelection(3);
                str_colour=colourGolden.getText().toString();
                break;
            case R.id.colourGoldPink:
                setColourSelection(4);
                str_colour=colourGoldPink.getText().toString();
                break;
            case R.id.g16:
                setGSelection(5);
                str_g=g16.getText().toString();
                break;
            case R.id.g64:
                setGSelection(6);
                str_g=g64.getText().toString();
                break;
            case R.id.g128:
                setGSelection(7);
                str_g=g128.getText().toString();
                break;
            default:
                //判断空，我就不判断了。。。。
                Toast.makeText(this, "sure", Toast.LENGTH_SHORT).show();
                intent.putExtra("str_colour", str_colour);
                intent.putExtra("str_g", str_g);
                //请求代码可以自己设置，这里设置成0
                setResult(0, intent);
                //关闭掉这个Activity
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "ggggg", Toast.LENGTH_SHORT).show();
        intent.putExtra("str_colour", str_colour);
        intent.putExtra("str_g", str_g);
        setResult(0, intent);
        finish();
    }

    //选择手机颜色
    private void setColourSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearColourSelection();
        switch (index) {
            case 1:
                // 当点击了按钮installment3时，改变控件的背景图片
                colourBlack.setBackgroundResource(R.mipmap.mall_outline);
                colourBlack.setTextColor(Color.RED);
                break;
            case 2:
                colourWhite.setBackgroundResource(R.mipmap.mall_outline);
                colourWhite.setTextColor(Color.RED);
                break;
            case 3:
                colourGolden.setBackgroundResource(R.mipmap.mall_outline);
                colourGolden.setTextColor(Color.RED);
                break;
            case 4:
                colourGoldPink.setBackgroundResource(R.mipmap.mall_outline);
                colourGoldPink.setTextColor(Color.RED);
                break;
            default:
                break;
        }
    }

    //选择手机内存
    private void setGSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearGSelection();
        switch (index) {
            case 5:
                // 当点击了按钮installment5时，改变控件的背景图片
                g16.setBackgroundResource(R.mipmap.mall_outline);
                g16.setTextColor(Color.RED);
                break;
            case 6:
                g64.setBackgroundResource(R.mipmap.mall_outline);
                g64.setTextColor(Color.RED);
                break;
            case 7:
                g128.setBackgroundResource(R.mipmap.mall_outline);
                g128.setTextColor(Color.RED);
                break;
            default:
                break;
        }
    }

    //清除掉所有的手机颜色选中状态
    private void clearColourSelection() {
        colourBlack.setBackgroundResource(R.mipmap.mall_unoutline);
        colourBlack.setTextColor(Color.BLACK);
        colourWhite.setBackgroundResource(R.mipmap.mall_unoutline);
        colourWhite.setTextColor(Color.BLACK);
        colourGolden.setBackgroundResource(R.mipmap.mall_unoutline);
        colourGolden.setTextColor(Color.BLACK);
        colourGoldPink.setBackgroundResource(R.mipmap.mall_unoutline);
        colourGoldPink.setTextColor(Color.BLACK);
    }

    //清除掉所有的手机内存选中状态
    private void clearGSelection() {
        g16.setBackgroundResource(R.mipmap.mall_unoutline);
        g16.setTextColor(Color.BLACK);
        g64.setBackgroundResource(R.mipmap.mall_unoutline);
        g64.setTextColor(Color.BLACK);
        g128.setBackgroundResource(R.mipmap.mall_unoutline);
        g128.setTextColor(Color.BLACK);
    }

}
