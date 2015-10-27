package com.expopay.android.Dialog;

import android.app.AlertDialog;
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
public class MyDialog extends AlertDialog {
    TextView titleTextView;
    Button okButton, cancelButton;
    LinearLayout contentLayout;

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
        setContentView(R.layout.view_dialog);
        titleTextView = (TextView) findViewById(R.id.dialog_title);
        okButton = (Button) findViewById(R.id.dialog_ok);
        cancelButton = (Button) findViewById(R.id.dialog_cancel);
        contentLayout = (LinearLayout) findViewById(R.id.dialog_content);
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public void setContent(String[] content) {
        int padding = (int) getContext().getResources().getDimension(R.dimen.dialogtextpadding);
        for (int i = 0; i < content.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(content[i]);
            textView.setPadding(padding, padding, padding, padding);
            contentLayout.addView(textView);
        }
    }

    public void setOkOnclickListener(View.OnClickListener l) {
        okButton.setOnClickListener(l);
    }

    public void setCancelOnclickListener(View.OnClickListener l) {
        cancelButton.setOnClickListener(l);
    }
}
