package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.expopay.android.R;
import com.expopay.android.adapter.gridview.ChoosePeriodAdapter;
import com.expopay.android.adapter.gridview.ChoosePropertiesAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.MallProductEntity;
import com.expopay.android.model.ProductPeroidEntity;
import com.expopay.android.model.ProductPropertyEntity;

import java.util.List;

public class ChoosePeriodActivity extends BaseActivity {

    private GridView myGridView;
    private ChoosePeriodAdapter adapter;

    private ImageView img;
    private TextView productname, productamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_choose_period);

        img = (ImageView) findViewById(R.id.choose_period_img);
        productname = (TextView) findViewById(R.id.choose_period_name);
        productamount = (TextView) findViewById(R.id.choose_period_amount);

        productname.setText(getIntent().getStringExtra("detailProductName"));
        productamount.setText(getIntent().getStringExtra("detailAmount"));

        List<ProductPeroidEntity> periods = (List<ProductPeroidEntity>) getIntent().getSerializableExtra("peroids");
        myGridView = (GridView) findViewById(R.id.buttonGridView);
        adapter = new ChoosePeriodAdapter(this, periods);
        myGridView.setAdapter(adapter);

        MallProductEntity entity = (MallProductEntity) getIntent().getSerializableExtra("product");
        img.setImageBitmap(MyApplication.cache.getBitmapFromMemCache(entity.getProductImg()));
    }


    public void blankOnClick(View view) {
        finish();
    }

    public void cancelOnClick(View view) {
        finish();
    }

    public void periodOkOnclick(View view) {
        Intent intent = new Intent();
        ProductPeroidEntity periods = (ProductPeroidEntity) myGridView.getTag();
        if (null == periods) {
            Toast.makeText(this, "请选择期数", Toast.LENGTH_SHORT).show();
        } else {
            intent.putExtra("periods", periods);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
