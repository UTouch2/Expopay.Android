package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.BannerPagerAdapter;
import com.expopay.android.model.PropertiesEntity;
import com.expopay.android.view.BannerFootView;
import com.expopay.android.view.CustormViewPager;

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener{

    private RelativeLayout relativeLayoutProperties,relativeLayoutStage,llDetail;
    private Button btnImmediatelyOrder;
    private TextView detailProductName;
    private TextView detailAmount;
    private TextView tvSelected;
    private TextView tvStaging;
    private ImageView imgblowe;

    private byte[] bis;
    private Bitmap bitmap;

    private CustormViewPager viewPager;
    BannerFootView footView;

    private void assignViews() {
        relativeLayoutProperties = (RelativeLayout) findViewById(R.id.relativeLayoutProperties);
        relativeLayoutStage = (RelativeLayout) findViewById(R.id.relativeLayoutStage);
        llDetail = (RelativeLayout) findViewById(R.id.llDetail);
        btnImmediatelyOrder = (Button) findViewById(R.id.btnImmediatelyOrder);
        detailProductName = (TextView) findViewById(R.id.detailProductName);
        detailAmount = (TextView) findViewById(R.id.detailAmount);

        tvSelected = (TextView) findViewById(R.id.tvSelected);
        tvStaging = (TextView) findViewById(R.id.tvStaging);

        imgblowe = (ImageView) findViewById(R.id.imgblowe);
        if(getIntent() !=null)
        {
            bis=getIntent().getByteArrayExtra("bitmap");
            bitmap= BitmapFactory.decodeByteArray(bis, 0, bis.length);
            imgblowe.setImageBitmap(bitmap);
        }

        btnImmediatelyOrder.setOnClickListener(this);
        relativeLayoutProperties.setOnClickListener(this);
        relativeLayoutStage.setOnClickListener(this);
        llDetail.setOnClickListener(this);

        //加载商品的ViewPage
        viewPager = (CustormViewPager) findViewById(R.id.product_bannerpager);
        viewPager.setAdapter(new BannerPagerAdapter(createViews()));
        viewPager.setCurrentItem(100);
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000l);
                        handler.sendEmptyMessage(1);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
        footView = (BannerFootView) findViewById(R.id.product_bannerpager_footview);

        viewPager.setAdapter(new BannerPagerAdapter(createViews()));
        viewPager.setOnPageChangeListener(new AbsOnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                footView.setSelectedIndex(createViews().length, i % createViews().length);
            }
        });
        footView.setSelectedIndex(createViews().length, viewPager.getCurrentItem() % createViews().length);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("商品详情");
        setContentView(R.layout.activity_produte_details);

        assignViews();
        setTextView();

        bis=getIntent().getByteArrayExtra("bitmap");
        bitmap= BitmapFactory.decodeByteArray(bis, 0, bis.length);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //可以根据多个请求代码来作相应的操作
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    String str_colour = ((PropertiesEntity)data.getExtras().getSerializable("str_colour")).getProperties();
                    String str_g = ((PropertiesEntity)data.getExtras().getSerializable("str_g")).getProperties();
                    tvSelected.setText(str_colour+"  " +str_g);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    String str_periods = ((PropertiesEntity)data.getExtras().getSerializable("str_periods")).getProperties();
                    tvStaging.setText(str_periods);
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
                intent.putExtra("bitmap",bis);
                intent.putExtra("detailProductName", detailProductName.getText());
                intent.putExtra("detailAmount", detailAmount.getText());
                startActivityForResult(intent,0);
                break;
            case R.id.relativeLayoutStage:
                intent.setClass(ProductDetailsActivity.this, ChoosePeriodActivity.class);
                intent.putExtra("bitmap", bis);
                intent.putExtra("detailProductName", detailProductName.getText());
                intent.putExtra("detailAmount", detailAmount.getText());
                startActivityForResult(intent,1);
                break;
            case R.id.llDetail:
                intent.setClass(ProductDetailsActivity.this, OrderDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.btnImmediatelyOrder:
                intent.setClass(ProductDetailsActivity.this, OrderDetailCommitActivity.class);
                intent.putExtra("bitmap", bis);
                intent.putExtra("detailProductName", detailProductName.getText().toString().trim());
                intent.putExtra("detailAmount",detailAmount.getText().toString().trim());
                intent.putExtra("tvSelected",tvSelected.getText().toString().trim());
                intent.putExtra("tvStaging",tvStaging.getText().toString().trim());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void setTextView(){
        Intent intent = getIntent();
        detailProductName.setText(intent.getStringExtra("productName"));
        detailAmount.setText(intent.getStringExtra("orderAmount"));
    }

    private View[] createViews() {
        View[] views = new View[3];
        ImageView view = new ImageView(this);
        views[0] = view;
        views[0].setBackgroundResource(R.mipmap.mall_banner01);
        view = new ImageView(this);
        views[1] = view;
        views[1].setBackgroundResource(R.mipmap.mall_banner02);
        view = new ImageView(this);
        views[2] = view;
        views[2].setBackgroundResource(R.mipmap.mall_banner03);
        return views;
    }

    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int startItem = viewPager.getCurrentItem()+1;
            viewPager.setCurrentItem(startItem);
        }
    };
}
