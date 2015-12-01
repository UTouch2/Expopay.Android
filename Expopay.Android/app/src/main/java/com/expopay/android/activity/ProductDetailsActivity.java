package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kechong.lib.http.Request;
import com.android.kechong.lib.http.RequestMethod;
import com.android.kechong.lib.http.listener.BitmapRequestListener;
import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.BannerPagerAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.MallProductEntity;
import com.expopay.android.model.ProductDetailsEntity;
import com.expopay.android.model.ProductImageEntity;
import com.expopay.android.model.ProductPeroidEntity;
import com.expopay.android.model.ProductPropertyEntity;
import com.expopay.android.request.ProductRequest;
import com.expopay.android.view.BannerFootView;
import com.expopay.android.view.CustormLoadingView;
import com.expopay.android.view.CustormViewPager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends BaseActivity {

    private TextView productNameText;
    private TextView productPriceText;
    private TextView propertisText;
    private TextView periodText;
    private CustormLoadingView loadingView;
    private CustormViewPager viewPager;
    BannerFootView footView;
    //商品组所有的商品
    private List<ProductDetailsEntity> productDetails;
    //当前商品
    private ProductDetailsEntity productEntity;
    //内存和颜色等属性规格
    private List<ProductPropertyEntity> colors, memorys;
    private ProductPropertyEntity color, memory;
    //分期数，和选择的分期书
    private List<ProductPeroidEntity> peroids;
    private ProductPeroidEntity peroid;

    private List<View> bannerViews;

    private BannerPagerAdapter adapter;
    private MallProductEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("商品详情");
        setContentView(R.layout.activity_produte_details);
        initView();
        bannerViews = new ArrayList<>();
        entity = (MallProductEntity) getIntent().getSerializableExtra("entity");
        try {
            getProductDetailsRequest(entity.getProductId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void initView() {
        productNameText = (TextView) findViewById(R.id.productdetails_productname);
        productPriceText = (TextView) findViewById(R.id.productdetails_productprice);

        propertisText = (TextView) findViewById(R.id.productdetails_productproperties);
        periodText = (TextView) findViewById(R.id.productdetails_productperoids);
        loadingView = (CustormLoadingView) findViewById(R.id.orderDetail_loadingview);

        viewPager = (CustormViewPager) findViewById(R.id.product_bannerpager);
        adapter = new BannerPagerAdapter(new View[]{new ImageView(getApplicationContext())});
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(100);

        footView = (BannerFootView) findViewById(R.id.product_bannerpager_footview);
        viewPager.setOnPageChangeListener(new AbsOnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                footView.setSelectedIndex(bannerViews.size(), i % bannerViews.size());
            }
        });
    }

    public void okOnckick(View v) {
        Intent intent = new Intent(this, OrderDetailCommitActivity.class);
        intent.putExtra("product", productEntity);
        intent.putExtra("peroid", peroid);
        startActivity(intent);
    }

    public void chooseColorOnclick(View v) {
        Intent intent = new Intent(this, ChoosePropertiesActivity.class);
        intent.putExtra("detailProductName", productNameText.getText().toString().trim());
        intent.putExtra("detailAmount", productPriceText.getText().toString().trim());
        intent.putExtra("colors", (Serializable) colors);
        intent.putExtra("memorys", (Serializable) memorys);
        intent.putExtra("product", entity);
        startActivityForResult(intent, 0);
    }

    public void choosePeriodOnclick(View v) {
        Intent intent = new Intent(this, ChoosePeriodActivity.class);
        intent.putExtra("detailProductName", productNameText.getText().toString().trim());
        intent.putExtra("detailAmount", productPriceText.getText().toString().trim());
        intent.putExtra("peroids", (Serializable) peroids);
        intent.putExtra("product", entity);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //可以根据多个请求代码来作相应的操作
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                color = ((ProductPropertyEntity) data.getExtras().getSerializable("color"));
                memory = ((ProductPropertyEntity) data.getExtras().getSerializable("memory"));
                getProductByProperties(productDetails, color, memory);
                propertisText.setText(color.getPropertyValue() + "  " + memory.getPropertyValue());
                productNameText.setText(productEntity.getProductName());
                productPriceText.setText(productEntity.getProductPrice());
            }
            if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    peroid = ((ProductPeroidEntity) data.getExtras().getSerializable("periods"));
                    periodText.setText(peroid.getPeriod());
                }
            }
        }
    }

    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int startItem = viewPager.getCurrentItem() + 1;
            viewPager.setCurrentItem(startItem);
        }
    };

    private void getProductDetailsRequest(String productId) throws JSONException {
        loadingView.show();
        loadingView.showLoading();
        ProductRequest request = new ProductRequest(MyApplication.HOST + "/product/productdetail");
        request.setEntity(request.createProductDetailsParams(productId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                loadingView.showRetry();
                loadingView.setRetryMessage("网络请求失败");
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code").equals("0000")) {
                        Gson gson = new Gson();
                        productDetails = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(),
                                new TypeToken<List<ProductDetailsEntity>>() {
                                }.getType());
                        loadingView.dismiss();
                        parseData(productDetails);
                        color = colors.get(0);
                        memory = memorys.get(0);
                        getProductByProperties(productDetails, color, memory);
                        propertisText.setText(color.getPropertyValue() + "  " + memory.getPropertyValue());
                        periodText.setText(peroid.getPeriod());
                        productNameText.setText(productEntity.getProductName());
                        productPriceText.setText(productEntity.getProductPrice());
                    }
                } catch (Exception e) {
                    loadingView.showRetry();
                    loadingView.setRetryMessage("参数解析错误");
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }

    /**
     * 计算总的颜色和内存的规格
     *
     * @param data
     */
    private void parseData(List<ProductDetailsEntity> data) {
        colors = new ArrayList<>();
        memorys = new ArrayList<>();
        for (ProductDetailsEntity entity : data) {
            if (entity.getPropertyName1().equals("颜色")) {
                ProductPropertyEntity color = new ProductPropertyEntity();
                color.setPropertyId(entity.getPropertyId1());
                color.setPropertyName(entity.getPropertyName1());
                color.setPropertyValue(entity.getPropertyValue1());

                ProductPropertyEntity memory = new ProductPropertyEntity();
                memory.setPropertyId(entity.getPropertyId2());
                memory.setPropertyName(entity.getPropertyName2());
                memory.setPropertyValue(entity.getPropertyValue2());
                if (!colors.contains(color)) {
                    colors.add(color);
                }
                if (!memorys.contains(memory)) {
                    memorys.add(memory);
                }
            } else if (entity.getPropertyName1().equals("内存")) {
                ProductPropertyEntity color = new ProductPropertyEntity();
                color.setPropertyId(entity.getPropertyId2());
                color.setPropertyName(entity.getPropertyName2());
                color.setPropertyValue(entity.getPropertyValue2());

                ProductPropertyEntity memory = new ProductPropertyEntity();
                memory.setPropertyId(entity.getPropertyId1());
                memory.setPropertyName(entity.getPropertyName1());
                memory.setPropertyValue(entity.getPropertyValue1());

                if (!colors.contains(color)) {
                    colors.add(color);
                }
                if (memorys.contains(memory)) {
                    memorys.add(memory);
                }
            }
        }
    }

    /**
     * 根据属性找到对应的商品
     *
     * @param data
     * @param color
     * @param memory
     * @return
     */
    private void getProductByProperties(List<ProductDetailsEntity> data, ProductPropertyEntity color, ProductPropertyEntity memory) {
        for (ProductDetailsEntity entity : data) {
            if (entity.getPropertyId1().equals(color.getPropertyId()) && entity.getPropertyId2().equals(memory.getPropertyId())) {
                productEntity = entity;
            }
            if (entity.getPropertyId2().equals(color.getPropertyId()) && entity.getPropertyId1().equals(memory.getPropertyId())) {
                productEntity = entity;
            }
        }
        peroids = productEntity.getProductPeriods();
        if (peroids.size() > 0) {
            peroid = peroids.get(0);
        }
        List<ProductImageEntity> images = productEntity.getProductImgs();
        bannerViews.clear();
        for (ProductImageEntity image : images) {
            String url = image.getImgUrl();
            getProductImage(url);
        }

    }

    private void getProductImage(final String url) {
        if (MyApplication.cache.getBitmapFromMemCache(url) != null) {
            ImageView view = new ImageView(getApplicationContext());
            view.setImageBitmap(MyApplication.cache.getBitmapFromMemCache(url));
            bannerViews.add(view);
            viewPager.setAdapter(new BannerPagerAdapter(bannerViews));
            footView.setSelectedIndex(bannerViews.size(), viewPager.getCurrentItem() % bannerViews.size());
            return;
        }
        Request request = new Request(url, RequestMethod.GET);
        request.setIRequestListener(new BitmapRequestListener() {
            @Override
            public void onFilure(Exception result) {
                System.out.print(result);
            }

            @Override
            public void onSuccess(Object result) {
                Bitmap map = (Bitmap) result;
                ImageView view = new ImageView(getApplicationContext());
                view.setImageBitmap(map);
                bannerViews.add(view);
                MyApplication.cache.addBitmapToMemoryCache(url, map);
                viewPager.setAdapter(new BannerPagerAdapter(bannerViews));
                footView.setSelectedIndex(bannerViews.size(), viewPager.getCurrentItem() % bannerViews.size());
            }

            @Override
            public void onProgressUpdate(int progress, int status) {

            }
        });
        request.execute();
    }
}
