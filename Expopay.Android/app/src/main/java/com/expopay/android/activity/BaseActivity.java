package com.expopay.android.activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toolbar;

import com.android.kechong.lib.AbsFragmentActivity;
import com.expopay.android.R;
/**
 * Created by misxu012 on 2015/10/16.
 */
public class BaseActivity extends AbsFragmentActivity {
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar mActionbar = getActionBar();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//        if (mActionbar != null) {
//            mActionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3D3D3C")));
//            mActionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//            mActionbar.setDisplayShowCustomEnabled(true);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//              setActionBar(null);
//            }else{
//                mActionbar.setCustomView(R.layout.view_myactionbar);
//            }
//            tvTitle = (TextView) mActionbar.getCustomView().findViewById(R.id.title);
//            mActionbar.getCustomView().findViewById(R.id.leftbutton).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//            mActionbar.getCustomView().findViewById(R.id.rightbutton).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        i = menu.findItem(R.id.action_notify);
        return super.onCreateOptionsMenu(menu);
    }

    public void setTitle(String title) {
       // if (initCustomActionBar())
       // tvTitle.setText(title);
    }

    protected void showAsAction(boolean flag) {
        // i.setShowAsAction(flag ? MenuItem.SHOW_AS_ACTION_ALWAYS : MenuItem.SHOW_AS_ACTION_NEVER);
    }

    private void initActionbar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
    }


}
