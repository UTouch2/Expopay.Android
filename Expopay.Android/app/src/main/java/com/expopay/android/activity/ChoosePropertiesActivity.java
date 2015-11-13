package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.expopay.android.R;
import com.expopay.android.adapter.gridview.PropertiesAdapter;
import com.expopay.android.model.PropertiesEntity;

import java.util.ArrayList;
import java.util.List;

public class ChoosePropertiesActivity extends BaseActivity {

    private static String str_colour ;
    private static String str_content ;
    private GridView colourGridView, contentGridView;
    private PropertiesAdapter adapterColour,adapterContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_choose_properties);

        colourGridView = (GridView) findViewById(R.id.colourGridView);
        adapterColour = new PropertiesAdapter(this, colourData());
        str_colour = adapterColour.getStr();
        colourGridView.setAdapter(adapterColour);

        contentGridView = (GridView) findViewById(R.id.contentGridView);
        adapterContent = new PropertiesAdapter(this, contentData());
        str_content = adapterContent.getStr();
        contentGridView.setAdapter(adapterContent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("str_colour", str_colour);
        intent.putExtra("str_g", str_content);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void propertiesBlankOnClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("str_colour", str_colour);
        intent.putExtra("str_g", str_content);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelPropertiesOnClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("str_colour", str_colour);
        intent.putExtra("str_g", str_content);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void okPropertiesOnclick(View view) {
        Intent intent = new Intent();
        intent.putExtra("str_colour", str_colour);
        intent.putExtra("str_g", str_content);
        setResult(RESULT_OK, intent);
        finish();
    }

    private List<PropertiesEntity> colourData() {
        List<PropertiesEntity> list = new ArrayList<PropertiesEntity>();
        for (int i = 0; i < 6; i++) {
            PropertiesEntity mp = new PropertiesEntity();
            mp.setProperties("金色" + i);
            list.add(mp);
        }
        return list;
    }

    private List<PropertiesEntity> contentData() {
        List<PropertiesEntity> list = new ArrayList<PropertiesEntity>();
        for (int i = 0; i < 3; i++) {
            PropertiesEntity mp = new PropertiesEntity();
            mp.setProperties("64G" + i);
            list.add(mp);
        }
        return list;
    }
}
