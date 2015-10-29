package com.expopay.android.Dialog;

import android.content.Context;
import android.util.TypedValue;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/27.
 */
public class DialogFactory {

    public static MyDialog createDialog(Context context,String Title,String... content){
        MyDialog d = new MyDialog(context);
        d.setContent(content);
        d.setTitle(Title);
        return d;
    }
}
