package com.expopay.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.kechong.lib.http.Request;
import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.expopay.android.R;
import com.expopay.android.activity.ProductDetailsActivity;
import com.expopay.android.adapter.gridview.MallProductAdapter;
import com.expopay.android.adapter.pager.BannerPagerAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.AddressEntity;
import com.expopay.android.model.MallProductEntity;
import com.expopay.android.request.ProductRequest;
import com.expopay.android.view.BannerFootView;
import com.expopay.android.view.CustormViewPager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MallFragment extends BaseFragment {

    private CustormViewPager viewPager;
    BannerFootView footView;
    private GridView myGridView;
    private MallProductAdapter adapter;
    List<MallProductEntity> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mall, container, false);
        data = new ArrayList<>();
        viewPager = (CustormViewPager) view.findViewById(R.id.mall_bannerpager);
        viewPager.setAdapter(new BannerPagerAdapter(createViews()));
        viewPager.setCurrentItem(100);
        footView = (BannerFootView) view.findViewById(R.id.mall_bannerpager_footview);
        viewPager.setAdapter(new BannerPagerAdapter(createViews()));
        viewPager.setOnPageChangeListener(new AbsOnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                footView.setSelectedIndex(createViews().length, i % createViews().length);
            }
        });
        footView.setSelectedIndex(createViews().length, viewPager.getCurrentItem() % createViews().length);
        myGridView = (GridView) view.findViewById(R.id.mygridview);
        adapter = new MallProductAdapter(getActivity().getApplicationContext(), data);
        myGridView.setAdapter(adapter);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                intent.putExtra("entity", data.get(position));
                getActivity().startActivity(intent);
            }
        });
        startBanner();
        try {
            getMallProductRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void startBanner() {
        new Thread() {
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
    }


    private View[] createViews() {
        View[] views = new View[3];
        ImageView view = new ImageView(getActivity().getApplicationContext());
        views[0] = view;
        views[0].setBackgroundResource(R.mipmap.mall_banner01);
        view = new ImageView(getActivity().getApplicationContext());
        views[1] = view;
        views[1].setBackgroundResource(R.mipmap.mall_banner02);
        view = new ImageView(getActivity().getApplicationContext());
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

    private void getMallProductRequest() throws JSONException {
        ProductRequest request = new ProductRequest(MyApplication.HOST + "/product/productlist");
        request.setEntity(request.createProductsParams());
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code").equals("0000")) {
                        Gson gson = new Gson();
                        data = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(),
                                new TypeToken<List<MallProductEntity>>() {
                                }.getType());
                        adapter.setData(data);
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }
}
