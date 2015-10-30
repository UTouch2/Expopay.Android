package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.view.CustormLoadingButton;

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener{

    private RelativeLayout relativeLayoutProperties,relativeLayoutStage,llDetail;
    private CustormLoadingButton btnImmediatelyOrder;
    private ImageView detailProductImg;
    private TextView detailProductName;
    private TextView detailAmount;

    private TextView tvSelected;
    private TextView tvStaging;

    private void assignViews() {
        relativeLayoutProperties = (RelativeLayout) findViewById(R.id.relativeLayoutProperties);
        relativeLayoutStage = (RelativeLayout) findViewById(R.id.relativeLayoutStage);
        llDetail = (RelativeLayout) findViewById(R.id.llDetail);
        btnImmediatelyOrder = (CustormLoadingButton) findViewById(R.id.btnImmediatelyOrder);
        detailProductImg = (ImageView) findViewById(R.id.detailProductImg);
        detailProductName = (TextView) findViewById(R.id.detailProductName);
        detailAmount = (TextView) findViewById(R.id.detailAmount);

        tvSelected = (TextView) findViewById(R.id.tvSelected);
        tvStaging = (TextView) findViewById(R.id.tvStaging);

        btnImmediatelyOrder.setText("立即下单");
        relativeLayoutProperties.setOnClickListener(this);
        relativeLayoutStage.setOnClickListener(this);
        llDetail.setOnClickListener(this);
        btnImmediatelyOrder.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_produte_details);

        assignViews();
        setTextView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //可以根据多个请求代码来作相应的操作
        switch (requestCode) {
            case 0:
                if (resultCode == 0) {
                    String str_colour = data.getExtras().getString("str_colour");
                    String str_g = data.getExtras().getString("str_g");
                    tvSelected.setText(str_colour+" " +str_g+ "非合约机");
                }
                break;
            case 1:
                if (resultCode == 1) {
                    String str_periods = data.getExtras().getString("str_periods");
                    tvStaging.setText(str_periods + "  (522+12)X12=5288");
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.relativeLayoutProperties:
                intent.setClass(ProductDetailsActivity.this, ChoosePropertiesActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.relativeLayoutStage:
                intent.setClass(ProductDetailsActivity.this, ChoosePeriodActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.llDetail:
                intent.setClass(ProductDetailsActivity.this, OrderDetailCommitActivity.class);
                startActivity(intent);
                break;
            case R.id.btnImmediatelyOrder:
                btnImmediatelyOrder.showLoading();
                btnImmediatelyOrder.setLoading(true);
                btnImmediatelyOrder.setLoadingText("正在下单...");

                intent.setClass(ProductDetailsActivity.this, RechargeTelephoneActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void setTextView(){
        detailProductImg.setImageResource(R.mipmap.mall_mobile);
        detailProductName.setText("iPhone6S");
        detailAmount.setText("5288.00");
    }

}
