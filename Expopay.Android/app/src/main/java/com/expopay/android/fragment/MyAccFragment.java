package com.expopay.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MyAccFragment extends  BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_myaccount,null,false);
        return view;
    }
}
