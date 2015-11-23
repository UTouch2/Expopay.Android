package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.BannerPagerAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.MallProductEntity;
import com.expopay.android.model.ProductDetailsEntity;
import com.expopay.android.model.ProductPeroidEntity;
import com.expopay.android.model.ProductPropertyEntity;
import com.expopay.android.request.ProductRequest;
import com.expopay.android.view.BannerFootView;
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
    private ImageView imgblowe;
    private CustormViewPager viewPager;
    BannerFootView footView;
    //商品组所有的商品
    private List<ProductDetailsEntity> productDetails;
    //当前商品
    private ProductDetailsEntity productEntity;
    //内存和颜色等属性规格
    private List<ProductPropertyEntity> colors, memorys;
    private ProductPropertyEntity color, memory;
    //分期书，和选择的分期书
    private List<ProductPeroidEntity> peroids;
    private ProductPeroidEntity peroid;

    protected void initView() {
        productNameText = (TextView) findViewById(R.id.productdetails_productname);
        productPriceText = (TextView) findViewById(R.id.productdetails_productprice);

        propertisText = (TextView) findViewById(R.id.productdetails_productproperties);
        periodText = (TextView) findViewById(R.id.productdetails_productperoids);

        imgblowe = (ImageView) findViewById(R.id.imgblowe);

        viewPager = (CustormViewPager) findViewById(R.id.product_bannerpager);
        viewPager.setAdapter(new BannerPagerAdapter(createViews()));
        viewPager.setCurrentItem(100);

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
        initView();
        MallProductEntity entity = (MallProductEntity) getIntent().getSerializableExtra("entity");
        try {
            getProductDetailsRequest(entity.getProductId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void chooseColorOnclick(View v) {
        Intent intent = new Intent(this, ChoosePropertiesActivity.class);
        intent.putExtra("colors", (Serializable) colors);
        intent.putExtra("memorys", (Serializable) memorys);
        startActivityForResult(intent, 0);
    }

    public void chooseMemoryOnclick(View v) {
        Intent intent = new Intent(this, ChoosePeriodActivity.class);
        intent.putExtra("peroids", (Serializable) peroids);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //可以根据多个请求代码来作相应的操作
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    color = ((ProductPropertyEntity) data.getExtras().getSerializable("color"));
                    memory = ((ProductPropertyEntity) data.getExtras().getSerializable("memory"));
                    getProductByProperties(productDetails, color, memory);
                    peroids = productEntity.getProductPeriods();
                    peroid = peroids.get(0);
                    propertisText.setText(color.getPropertyValue() + "  " + memory.getPropertyValue());
                    periodText.setText(peroid.getPeriod());
                    productNameText.setText(productEntity.getProductName());
                    productPriceText.setText(productEntity.getProductPrice());
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    peroid = ((ProductPeroidEntity) data.getExtras().getSerializable("periods"));
                    periodText.setText(peroid.getPeriod());
                }
                break;
            default:
        }
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
            int startItem = viewPager.getCurrentItem() + 1;
            viewPager.setCurrentItem(startItem);
        }
    };

    private void getProductDetailsRequest(String productId) throws JSONException {
        ProductRequest request = new ProductRequest(MyApplication.HOST + "/product/productdetail");
        request.setEntity(request.createProductDetailsParams(productId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                System.out.print(e);
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
                        parseData(productDetails);
                        color = colors.get(0);
                        memory = memorys.get(0);
                        getProductByProperties(productDetails, color, memory);
                        peroids = productEntity.getProductPeriods();
                        peroid = peroids.get(0);
                        propertisText.setText(color.getPropertyValue() + "  " + memory.getPropertyValue());
                        periodText.setText(peroid.getPeriod());
                        productNameText.setText(productEntity.getProductName());
                        productPriceText.setText(productEntity.getProductPrice());
                    }
                } catch (Exception e) {
                    System.out.print(e);
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
     * 根据舒心找到对应的商品
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
    }
}
