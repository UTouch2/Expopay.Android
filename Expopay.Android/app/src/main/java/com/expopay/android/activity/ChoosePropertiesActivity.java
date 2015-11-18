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

public class ChoosePropertiesActivity extends BaseActivity {
    private GridView colourGridView,contentGridView;
    private PropertiesAdapter adapterColour;
    private PropertiesAdapter adapterContent;

    private ImageView img;
    private TextView productname,productamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_choose_properties);

        img = (ImageView) findViewById(R.id.choose_properties_img);
        productname = (TextView) findViewById(R.id.choose_properties_name);
        productamount = (TextView) findViewById(R.id.choose_properties_mount);
        if(getIntent() !=null)
        {
            byte[] bis=getIntent().getByteArrayExtra("bitmap");
            Bitmap bitmap= BitmapFactory.decodeByteArray(bis, 0, bis.length);
            img.setImageBitmap(bitmap);
        }
        productname.setText(getIntent().getStringExtra("detailProductName"));
        productamount.setText(getIntent().getStringExtra("detailAmount"));

        colourGridView = (GridView) findViewById(R.id.colourGridView);
        adapterColour = new PropertiesAdapter(this, colourData());
        colourGridView.setAdapter(adapterColour);

        contentGridView = (GridView) findViewById(R.id.contentGridView);
        adapterContent = new PropertiesAdapter(this, contentData());
        contentGridView.setAdapter(adapterContent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void propertiesBlankOnClick(View view) {
        finish();
    }

    public void cancelPropertiesOnClick(View view) {
        finish();
    }

    public void okPropertiesOnclick(View view) {
        Intent intent = new Intent();
        PropertiesEntity str_colour = (PropertiesEntity)colourGridView.getTag();
        PropertiesEntity str_g = (PropertiesEntity)contentGridView.getTag();
        if(null == str_colour || null == str_g){
            Toast.makeText(this, "请选择颜色和容量", Toast.LENGTH_SHORT).show();
        }else{
            intent.putExtra("str_colour", str_colour);
            intent.putExtra("str_g", str_g);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private List<PropertiesEntity> colourData() {
        List<PropertiesEntity> list = new ArrayList<PropertiesEntity>();
        for (int i = 0; i < 6; i++) {
            PropertiesEntity mp = new PropertiesEntity();
            mp.setProperties(i+"金色");
            list.add(mp);
        }
        return list;
    }

    private List<PropertiesEntity> contentData() {
        List<PropertiesEntity> list = new ArrayList<PropertiesEntity>();
        for (int i = 1; i < 4; i++) {
            PropertiesEntity mp = new PropertiesEntity();
            mp.setProperties(i + "G");
            list.add(mp);
        }
        return list;
    }

}
