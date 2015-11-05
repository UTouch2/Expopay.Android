package com.expopay.android.fragment;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.expopay.android.R;
import com.expopay.android.adapter.gridview.MallProductAdapter;
import com.expopay.android.adapter.pager.BannerPagerAdapter;
import com.expopay.android.model.MallProductEntity;
import com.expopay.android.view.BannerFootView;
import com.expopay.android.view.CustormViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MallFragment extends  BaseFragment {

    private CustormViewPager viewPager;
    BannerFootView footView;
    private GridView myGridView;
    private MallProductAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_mall,container,false);

        viewPager = (CustormViewPager) view.findViewById(R.id.mall_bannerpager);
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
        adapter = new MallProductAdapter(getActivity().getApplicationContext(),testData());
        myGridView.setAdapter(adapter);

        return view;
    }

    private List<MallProductEntity> testData() {
        List<MallProductEntity> list = new ArrayList<MallProductEntity>();
        for (int i = 0; i < 15; i++) {
            MallProductEntity mp = new MallProductEntity();
            mp.setProductName("iPhone6S");
            mp.setOrderAmount("5200.00");
            list.add(mp);
        }
        return list;
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
            int startItem = viewPager.getCurrentItem()+1;
            viewPager.setCurrentItem(startItem);
        }
    };
}
