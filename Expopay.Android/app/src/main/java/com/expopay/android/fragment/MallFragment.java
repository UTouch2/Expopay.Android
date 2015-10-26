package com.expopay.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.expopay.android.R;
import com.expopay.android.activity.ProductDetailsActivity;
import com.expopay.android.adapter.gridview.MallProductAdapter;
import com.expopay.android.model.MallProductEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MallFragment extends  BaseFragment {

    private GridView myGridView;
    private MallProductAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_mall,container,false);

        view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });

        myGridView = (GridView) view.findViewById(R.id.mygridview);
        adapter = new MallProductAdapter(getActivity().getApplicationContext(),testData());
        myGridView.setAdapter(adapter);

        return view;
    }

    private List<MallProductEntity> testData() {
        List<MallProductEntity> list = new ArrayList<MallProductEntity>();
        for (int i = 0; i < 15; i++) {
            MallProductEntity mp = new MallProductEntity();
            mp.setProductName("iPhone6S4GÊÖ»ú"+i);
            mp.setOrderAmount("5200.00");
            list.add(mp);
        }
        return list;
    }
}
