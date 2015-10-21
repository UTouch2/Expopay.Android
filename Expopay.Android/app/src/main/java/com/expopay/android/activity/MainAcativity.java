package com.expopay.android.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.android.kechong.lib.http.RequestMethod;
import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.android.kechong.lib.util.ApkUtil;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.MainPagerAdepter;
import com.expopay.android.fragment.MainFragment;
import com.expopay.android.fragment.MallFragment;
import com.expopay.android.fragment.MyAccFragment;
import com.expopay.android.model.UpdateAppEntity;
import com.expopay.android.request.AppRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MainAcativity extends BaseActivity {

    private ViewPager mViewPager;
    private Fragment[] frags;
    private MainPagerAdepter mainPagerAdapter;

    private List<ImageView> mTabIndicator = new ArrayList<ImageView>();
    private List<ImageView> mBgTabIndicator = new ArrayList<ImageView>();

    private ImageView mainImg, mallImg, myaccImg;
    private ImageView mainBgImg, mallBgImg, myaccBgImg;

    private Fragment cardFragment, discoveryFragment, myAccountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainact);
        initPerp();
        initView();
    }

    @Override
    protected void initPerp() {
        frags = new Fragment[]{
                new MainFragment(), new MallFragment(), new MyAccFragment()
        };
        mainPagerAdapter = new MainPagerAdepter(getSupportFragmentManager(),
                frags);
    }

    protected void initView() {
        // 蓝色tb的图片
        mainImg = (ImageView) findViewById(R.id.main_main);
        mallImg = (ImageView) findViewById(R.id.main_mall);
        myaccImg = (ImageView) findViewById(R.id.main_myacc);

        mTabIndicator.add(mainImg);
        mTabIndicator.add(mallImg);
        mTabIndicator.add(myaccImg);
        // 灰色tb的图片
        mainBgImg = (ImageView) findViewById(R.id.main_mainbg);
        mallBgImg = (ImageView) findViewById(R.id.main_mallbg);
        myaccBgImg = (ImageView) findViewById(R.id.main_myaccbg);

        mBgTabIndicator.add(mainBgImg);
        mBgTabIndicator.add(mallBgImg);
        mBgTabIndicator.add(myaccBgImg);
        // 初始化位第一个
        setCurrent(0);
        // viewpager
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mViewPager.setAdapter(mainPagerAdapter);
        // 互动事件
        mViewPager.setOnPageChangeListener(new AbsOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset,
                        positionOffsetPixels);
                if (positionOffset > 0) {
                    System.out.println(positionOffset);
                    ImageView left = mTabIndicator.get(position);
                    ImageView right = mTabIndicator.get(position + 1);

                    ImageView bgLeft = mBgTabIndicator.get(position);
                    ImageView bgRight = mBgTabIndicator.get(position + 1);

                    left.setAlpha(1 - positionOffset);
                    right.setAlpha(positionOffset);
                    bgRight.setAlpha(1 - positionOffset);
                    bgLeft.setAlpha(positionOffset);
                }
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                super.onPageScrollStateChanged(arg0);
                if (arg0 == 0) {
                    setCurrent(mViewPager.getCurrentItem());
                }
            }
        });
    }

    // tab的点击事件
    public void tabOnclick(View v) {
        String tag = v.getTag().toString();
        int index = Integer.parseInt(tag);
        setCurrent(index);
        mViewPager.setCurrentItem(index, false);
    }

    /**
     * 根据index设置当前tab和title
     *
     * @param index
     */
    private void setCurrent(int index) {
        for (int i = 0; i < mTabIndicator.size(); i++) {
            mTabIndicator.get(i).setAlpha(0f);
            mBgTabIndicator.get(i).setAlpha(1f);
        }
        mTabIndicator.get(index).setAlpha(1f);
        mBgTabIndicator.get(index).setAlpha(0f);
        if (index == 0) {
            setTitle("首页");
        } else if (index == 1) {
            setTitle("商城");
        } else {
            setTitle("我的");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getNewVersionCode() throws JSONException {
        AppRequest request = new AppRequest(AppRequest.GET_VERSIONCODE);
        //request.setEntity(request.createOpenIdParams(""));
        request.setRequestMethod(RequestMethod.POST);
        request.setOutTime(5 * 1000);
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        // 成功
                        Gson gson = new Gson();
                        UpdateAppEntity e = gson.fromJson(json.getJSONObject("body").toString(),UpdateAppEntity.class);
                        final String newVercode = e.getVersionCode();
                        final String[] details = e.getUpdateexplain();
                        final int oldVercode = ApkUtil.findVersionCodeByName(
                                getApplicationContext(),
                                "com.expopay.android");
                        // 如果最新版本大于当前版本
                        if (Integer.parseInt(newVercode) > oldVercode) {

                        }
                    } else {
                        // 失败
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                }
            }
            @Override
            public void onProgressUpdate(int i, int j) {

            }

            @Override
            public void onFilure(Exception result) {
                System.out.println(result);
            }
        });
        request.execute();
        cancelRequest(request);
    }
}
