package com.expopay.android.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.expopay.android.R;
import com.expopay.android.activity.LoginByPasswordActivity;
import com.expopay.android.activity.LoginByVerifycodeActivity;
import com.expopay.android.activity.WebActivity;
import com.expopay.android.adapter.pager.BannerPagerAdapter;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MainFragment extends BaseFragment {
    int size = 3;
    ImageView[] bannerViews;

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
                Intent intent = new Intent(getActivity(), LoginByPasswordActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.main_power).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        view.findViewById(R.id.main_mobile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
//        final ViewPager pager =(ViewPager)view.findViewById(R.id.main_viewpager);
//        bannerViews = new  ImageView [3];
//        for(int i = 0;i<size;i++){
//            ImageView iv = new ImageView(getActivity());
//            iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//            iv.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.ic_launcher));
//            bannerViews[i] =iv;
//        }
//        pager.setAdapter(new BannerPagerAdapter(bannerViews));
        return view;
    }
}
