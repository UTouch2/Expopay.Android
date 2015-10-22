package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.expopay.android.R;

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener{

    private ImageButton imageStyle;
    private ImageButton imageInstallment;
    private Button btnDetail;
    private Button btnImmediatelyOrder;
    private TextView tvSelected;
    private TextView tvStaging;

    private void assignViews() {
        imageStyle = (ImageButton) findViewById(R.id.imageStyle);
        imageInstallment = (ImageButton) findViewById(R.id.imageInstallment);
        btnDetail = (Button) findViewById(R.id.btnDetail);
        btnImmediatelyOrder = (Button) findViewById(R.id.btnImmediatelyOrder);
        tvSelected = (TextView) findViewById(R.id.tvSelected);
        tvStaging = (TextView) findViewById(R.id.tvStaging);

        imageStyle.setOnClickListener(this);
        imageInstallment.setOnClickListener(this);
        btnDetail.setOnClickListener(this);
        btnImmediatelyOrder.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produte_details);

        assignViews();
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
            case R.id.imageStyle:
                intent.setClass(ProductDetailsActivity.this, ChoosePropertiesActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.imageInstallment:
                intent.setClass(ProductDetailsActivity.this, ChoosePeriodActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.btnDetail:
                startActivity(intent.setClass(ProductDetailsActivity.this, OrderDetailActivity.class));
                break;
            case R.id.btnImmediatelyOrder:
                Toast.makeText(this, "立即订单", Toast.LENGTH_SHORT).show();
                startActivity(intent.setClass(ProductDetailsActivity.this, MyOrderActivity.class));
                break;
            default:
                break;
        }
    }

}
