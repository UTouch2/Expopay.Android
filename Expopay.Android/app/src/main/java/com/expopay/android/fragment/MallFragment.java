package com.expopay.android.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.expopay.android.R;
import com.expopay.android.adapter.gridview.MallProductAdapter;
import com.expopay.android.adapter.pager.MallPagerAdapter;
import com.expopay.android.model.MallProductEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MallFragment extends  BaseFragment {

    private ViewPager viewPager;
    private GridView myGridView;
    private MallProductAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_mall,container,false);

        viewPager = (ViewPager) view.findViewById(R.id.mall_viewpager);
        viewPager.setAdapter(new MallPagerAdapter(createViews()));
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
        views[1].setBackgroundResource(R.mipmap.mall_banner01);
        view = new ImageView(getActivity().getApplicationContext());
        views[2] = view;
        views[2].setBackgroundResource(R.mipmap.mall_banner01);
        return views;
    }

    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int startItem=viewPager.getCurrentItem()+1;
            viewPager.setCurrentItem(startItem);
        }
    };
}
