package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.BannerPagerAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.request.OrderRequest;
import com.expopay.android.view.BannerFootView;
import com.expopay.android.view.CustormLoadingButton;
import com.expopay.android.view.CustormViewPager;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener{

    private RelativeLayout relativeLayoutProperties,relativeLayoutStage,llDetail;
    private CustormLoadingButton btnImmediatelyOrder;
    private TextView detailProductName;
    private TextView detailAmount;
    private TextView tvSelected;
    private TextView tvStaging;

    private CustormViewPager viewPager;
    BannerFootView footView;

    private void assignViews() {
        relativeLayoutProperties = (RelativeLayout) findViewById(R.id.relativeLayoutProperties);
        relativeLayoutStage = (RelativeLayout) findViewById(R.id.relativeLayoutStage);
        llDetail = (RelativeLayout) findViewById(R.id.llDetail);
        btnImmediatelyOrder = (CustormLoadingButton) findViewById(R.id.btnImmediatelyOrder);
        detailProductName = (TextView) findViewById(R.id.detailProductName);
        detailAmount = (TextView) findViewById(R.id.detailAmount);

        tvSelected = (TextView) findViewById(R.id.tvSelected);
        tvStaging = (TextView) findViewById(R.id.tvStaging);

        btnImmediatelyOrder.showNormal("立即下单");
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
        setContentView(R.layout.activity_produte_details);

        assignViews();
        setTextView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //可以根据多个请求代码来作相应的操作
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    String str_colour = data.getExtras().getString("str_colour");
                    String str_g = data.getExtras().getString("str_g");
                    tvSelected.setText(str_colour+" " +str_g+ "非合约机");
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
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
                try {
                    getOrder(getUser().getOpenId(), "", "","","","","");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                intent.setClass(ProductDetailsActivity.this, OrderDetailCommitActivity.class);
//                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void getOrder(String openId, String orderSource, String paymentMethod, String orerAmount,
                          String productId, String periodId, String addressId)throws JSONException {
        btnImmediatelyOrder.showLoading("正在下单...");
        OrderRequest request = new OrderRequest(MyApplication.HOST +"");
        request.setEntity(request.createCreateOrderParms(openId, orderSource,
                paymentMethod, orerAmount, productId, periodId, addressId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                btnImmediatelyOrder.showResult("网络请求失败", false);
            }

            @Override
            public void onSuccess(Object o) {
                try {
                    JSONObject json = (JSONObject) o;
                    if (json.getJSONObject("header").getString("code").equals("0000")) {
                        btnImmediatelyOrder.showResult("下单成功", true);
                    }else{
                        btnImmediatelyOrder.showResult(json.getJSONObject("header").getString("desc"), false);
                    }
                } catch (JSONException e) {

                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }

    private void setTextView(){
        detailProductName.setText("iPhone6S");
        detailAmount.setText("5288.00");
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
