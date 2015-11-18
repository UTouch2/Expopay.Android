package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.expopay.android.R;
import com.expopay.android.adapter.gridview.PropertiesAdapter;
import com.expopay.android.model.PropertiesEntity;

import java.util.ArrayList;
import java.util.List;

public class ChoosePeriodActivity extends BaseActivity {

    private GridView myGridView;
    private PropertiesAdapter adapter;

    private ImageView img;
    private TextView productname,productamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_choose_period);

        img = (ImageView) findViewById(R.id.choose_period_img);
        productname = (TextView) findViewById(R.id.choose_period_name);
        productamount = (TextView) findViewById(R.id.choose_period_amount);
        if(getIntent() !=null)
        {
            byte[] bis=getIntent().getByteArrayExtra("bitmap");
            Bitmap bitmap= BitmapFactory.decodeByteArray(bis, 0, bis.length);
            img.setImageBitmap(bitmap);
        }
        productname.setText(getIntent().getStringExtra("detailProductName"));
        productamount.setText(getIntent().getStringExtra("detailAmount"));

        myGridView = (GridView) findViewById(R.id.buttonGridView);
        adapter = new PropertiesAdapter(this, testData());
        myGridView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void blankOnClick(View view) {
        finish();
    }

    public void cancelOnClick(View view) {
        finish();
    }

    public void periodOkOnclick(View view) {
        Intent intent = new Intent();
        PropertiesEntity str_periods = (PropertiesEntity)myGridView.getTag();
        if(null == str_periods) {
            Toast.makeText(this, "请选择期数", Toast.LENGTH_SHORT).show();
        }else {
            intent.putExtra("str_periods", str_periods);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private List<PropertiesEntity> testData() {
        List<PropertiesEntity> list = new ArrayList<PropertiesEntity>();
        for (int i = 0; i < 7; i++) {
            PropertiesEntity mp = new PropertiesEntity();
            mp.setProperties(i + "期");
            list.add(mp);
        }
        return list;
    }
}
