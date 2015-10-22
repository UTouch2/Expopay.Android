package com.expopay.android.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class MyActionBarView extends FrameLayout {
    private TextView textView;

    public MyActionBarView(Context context) {
        super(context);
        init();
    }

    public MyActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_myactionbar, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.setMinimumHeight((int) getContext().getResources().getDimension(R.dimen.actionbar2));
        } else {
            this.setMinimumHeight((int) getContext().getResources().getDimension(R.dimen.actionbar1));
        }
        textView = (TextView) view.findViewById(R.id.title);
        view.findViewById(R.id.leftbutton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }
            }
        });
        view.findViewById(R.id.leftbutton).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }
            }
        });
        addView(view);
    }

    public void settTitle(String t) {
        textView.setText(t);
    }
}
