package com.expopay.android.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/27.
 */
public class MyDialog extends Dialog {
    TextView titleTextView;
    Button okButton, cancelButton;
    LinearLayout contentLayout;
    View.OnClickListener okOnclickListener, cancelOnclickListener;

    MyDialog(Context context) {
        super(context);
        init();
    }

    MyDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setContentView(R.layout.view_dialog);
        titleTextView = (TextView) findViewById(R.id.dialog_title);
        okButton = (Button) findViewById(R.id.dialog_ok);
        cancelButton = (Button) findViewById(R.id.dialog_cancel);
        contentLayout = (LinearLayout) findViewById(R.id.dialog_content);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (okOnclickListener != null) {
                    okOnclickListener.onClick(v);
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (cancelOnclickListener != null) {
                    cancelOnclickListener.onClick(v);
                }
            }
        });
    }

    public MyDialog setTitle(String title) {
        titleTextView.setText(title);

        return this;
    }

    public MyDialog setContent(String... content) {
        int padding = (int) getContext().getResources().getDimension(R.dimen.dialogtextpadding);
        for (int i = 0; i < content.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(content[i]);
            textView.setPadding(padding, padding, padding, padding);
            contentLayout.addView(textView);
        }
        return this;
    }

    public MyDialog setOkOnclickListener(View.OnClickListener l) {
        okOnclickListener = l;
        return this;
    }

    public MyDialog setCancelOnclickListener(View.OnClickListener l) {
        cancelOnclickListener = l;
        return this;
    }
}
