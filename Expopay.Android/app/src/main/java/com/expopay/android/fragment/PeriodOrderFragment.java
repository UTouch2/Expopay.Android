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

import java.util.ArrayList;
import java.util.List;

public class PeriodOrderFragment extends Fragment {

    private ListView lvPeriodOrder;
    private PeriodOrderAdapter adapter;

    public PeriodOrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_period_order, container, false);

        lvPeriodOrder = (ListView) view.findViewById(R.id.lvPeriodOrder);
        adapter = new PeriodOrderAdapter(getActivity().getApplicationContext(), testData());
        lvPeriodOrder.setAdapter(adapter);

        return view;
    }

    private List<PeriodOrderEntity> testData(){
        List<PeriodOrderEntity> list = new ArrayList<PeriodOrderEntity>();
        for(int i = 0;i<10;i++){
            PeriodOrderEntity po = new PeriodOrderEntity();
            po.setProductName("iPhone6S4G"+i);
            po.setOrderAmount("5200.00");
            po.setProperties("yu");
            po.setRepaymentPeriod("12期");
            po.setOrderTime("2015-09-26");
            po.setOrderStatus("未完成");
            list.add(po);
        }
        return list ;
    }

}
