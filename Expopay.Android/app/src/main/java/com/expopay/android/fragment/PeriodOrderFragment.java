package com.expopay.android.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.PeriodOrderAdapter;
import com.expopay.android.model.PeriodOrderEntity;

import java.util.List;

public class PeriodOrderFragment extends Fragment {

    private ListView lvStageOrder;
    private PeriodOrderAdapter adapter;
    private List<PeriodOrderEntity> list = null;

    public PeriodOrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_period_order, container, false);

        lvStageOrder = (ListView) view.findViewById(R.id.lvInstallmentOrder);
        adapter = new PeriodOrderAdapter(getActivity().getApplicationContext(), list);
        lvStageOrder.setAdapter(adapter);

        return view;
    }

}
