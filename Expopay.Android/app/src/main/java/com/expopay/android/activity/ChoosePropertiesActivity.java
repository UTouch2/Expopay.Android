package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.expopay.android.R;
import com.expopay.android.adapter.gridview.ChoosePropertiesAdapter;
import com.expopay.android.model.ProductPropertyEntity;

import java.util.List;

public class ChoosePropertiesActivity extends BaseActivity {
    private GridView colourGridView, contentGridView;
    private ChoosePropertiesAdapter adapterColour;
    private ChoosePropertiesAdapter adapterContent;
    private ImageView img;
    private TextView productname, productamount;

    private List<ProductPropertyEntity> colors, memorys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_choose_properties);

        img = (ImageView) findViewById(R.id.choose_properties_img);
        productname = (TextView) findViewById(R.id.choose_properties_name);
        productamount = (TextView) findViewById(R.id.choose_properties_mount);

        productname.setText(getIntent().getStringExtra("detailProductName"));
        productamount.setText(getIntent().getStringExtra("detailAmount"));
        Intent data = getIntent();
        colors = (List) data.getSerializableExtra("colors");
        memorys = (List) data.getSerializableExtra("memorys");
        colourGridView = (GridView) findViewById(R.id.colourGridView);
        adapterColour = new ChoosePropertiesAdapter(this, colors);
        colourGridView.setAdapter(adapterColour);

        contentGridView = (GridView) findViewById(R.id.contentGridView);
        adapterContent = new ChoosePropertiesAdapter(this, memorys);
        contentGridView.setAdapter(adapterContent);
    }

    public void propertiesBlankOnClick(View view) {
        finish();
    }

    public void cancelPropertiesOnClick(View view) {
        finish();
    }

    public void okPropertiesOnclick(View view) {
        Intent intent = new Intent();
        ProductPropertyEntity color = (ProductPropertyEntity) colourGridView.getTag();
        ProductPropertyEntity memory = (ProductPropertyEntity) contentGridView.getTag();
        if (null == color) {
            Toast.makeText(this, "请选择颜色 ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == memory) {
            Toast.makeText(this, "请选择容量", Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra("color", color);
        intent.putExtra("memory", memory);
        setResult(RESULT_OK, intent);
        finish();
    }
}

