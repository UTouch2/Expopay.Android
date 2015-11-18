package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.PropertiesEntity;

import java.util.ArrayList;
import java.util.List;

public class ChoosePeriodActivity extends BaseActivity {

    private GridView myGridView;
    private PeriodAdapter adapter;
    private static String str_periods;

    private ImageView img;
    private TextView productname,productamount;

    Intent intent = new Intent();

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
        adapter = new PeriodAdapter(this, testData());
        adapter.setPosition(2);
        myGridView.setAdapter(adapter);
    }

    protected void selected() {
        super.onPause();
        str_periods = adapter.getStr();
        intent.putExtra("str_periods", str_periods);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        selected();
    }

    public void blankOnClick(View view) {
        selected();
    }

    public void cancelOnClick(View view) {
        selected();
    }

    public void periodOkOnclick(View view) {
        selected();
    }

    private List<PropertiesEntity> testData() {
        List<PropertiesEntity> list = new ArrayList<PropertiesEntity>();
        for (int i = 0; i < 7; i++) {
            PropertiesEntity mp = new PropertiesEntity();
            mp.setProperties("按钮" + i);
            list.add(mp);
        }
        return list;
    }
}
