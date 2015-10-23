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

import java.util.List;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class MallFragment extends  BaseFragment {

    private GridView myGridView;
    private MallProductAdapter adapter;
    private List<MallProductEntity> list = null;

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

//        myGridView = (GridView) view.findViewById(R.id.mygridview);
//        adapter = new MallProductAdapter(getActivity().getApplicationContext(),list);
//        myGridView.setAdapter(adapter);

        return view;
    }
}
