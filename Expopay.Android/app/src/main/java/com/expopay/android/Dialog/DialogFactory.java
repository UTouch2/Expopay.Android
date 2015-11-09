package com.expopay.android.dialog;

import android.content.Context;

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
