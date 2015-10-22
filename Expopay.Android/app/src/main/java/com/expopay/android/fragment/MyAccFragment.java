package com.expopay.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.expopay.android.R;
import com.expopay.android.activity.MyCardsActivity;
import com.expopay.android.activity.SettingsActivity;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MyAccFragment extends  BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_myaccount,container,false);
        view.findViewById(R.id.myaccount_mycards).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), MyCardsActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.myaccount_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
