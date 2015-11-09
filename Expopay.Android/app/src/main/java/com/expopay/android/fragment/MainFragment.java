package com.expopay.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.expopay.android.R;
import com.expopay.android.activity.LoginByPasswordActivity;
import com.expopay.android.activity.RechargeTelephoneActivity;
import com.expopay.android.activity.WebActivity;
import com.expopay.android.activity.WegQueryTransActivity;
import com.expopay.android.adapter.pager.BannerPagerAdapter;
import com.expopay.android.view.BannerFootView;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MainFragment extends BaseFragment {
    int size = 3;
    ImageView[] bannerViews;
    BannerFootView footView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        view.findViewById(R.id.main_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", "http://www.baidu.com");
                intent.putExtra("title", "便民服务");
                startActivity(intent);
            }
        });
        view.findViewById(R.id.main_water).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUser().getOpenId() != null) {
                    Intent intent = new Intent(getActivity(), WegQueryTransActivity.class);
                    intent.putExtra("type","1");
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(getActivity(), LoginByPasswordActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.main_power).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUser().getOpenId() != null) {
                    Intent intent = new Intent(getActivity(), WegQueryTransActivity.class);
                    intent.putExtra("type","2");
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(getActivity(), LoginByPasswordActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.main_mobile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUser().getOpenId() != null) {
                    Intent intent = new Intent(getActivity(), RechargeTelephoneActivity.class);
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(getActivity(), LoginByPasswordActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.main_merchant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        view.findViewById(R.id.main_buycard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        view.findViewById(R.id.main_callme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        final ViewPager pager = (ViewPager) view.findViewById(R.id.main_bannerpager);
        footView = (BannerFootView) view.findViewById(R.id.main_bannerpager_footview);

        pager.setAdapter(new BannerPagerAdapter(createViews()));
        pager.setOnPageChangeListener(new AbsOnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                footView.setSelectedIndex(createViews().length, i % createViews().length);
            }
        });
        footView.setSelectedIndex(createViews().length, pager.getCurrentItem() % createViews().length);
        return view;
    }

    private View[] createViews() {
        View[] views = new View[3];
        ImageView view = new ImageView(getActivity());
        views[0] = view;
        views[0].setBackgroundResource(R.mipmap.mall_banner01);
        view = new ImageView(getActivity());
        views[1] = view;
        views[1].setBackgroundResource(R.mipmap.mall_banner01);
        view = new ImageView(getActivity());
        views[2] = view;
        views[2].setBackgroundResource(R.mipmap.mall_banner01);
        return views;
    }
}
