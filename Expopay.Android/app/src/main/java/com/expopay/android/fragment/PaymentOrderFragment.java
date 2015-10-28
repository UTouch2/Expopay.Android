package com.expopay.android.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.PaymentOrderAdapter;
import com.expopay.android.model.PaymentOrderEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentOrderFragment extends BaseFragment {

    private ListView lvPaymentOrder;
    private PaymentOrderAdapter adapter;

    public PaymentOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_order, container, false);

        lvPaymentOrder = (ListView) view.findViewById(R.id.lvPaymentOrder);
        adapter = new PaymentOrderAdapter(getActivity().getApplicationContext(),testData());
        lvPaymentOrder.setAdapter(adapter);
        return view;
    }

    private List<PaymentOrderEntity> testData() {
        List<PaymentOrderEntity> list = new ArrayList<PaymentOrderEntity>();
        for(int i = 0;i<20;i++){
            PaymentOrderEntity po = new PaymentOrderEntity();
            po.setProductName("iPhone");
            po.setOrderAmount("18.00");
            po.setOrderTime("2015-10-26");
            po.setOrderStatus("未完成");
            list.add(po);
        }
        return list;
    }

}
