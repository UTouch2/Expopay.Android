package com.expopay.android.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.PeriodOrderAdapter;
import com.expopay.android.model.PeriodOrderEntity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentOrderFragment extends Fragment {

    private ListView lvPaymentOrder;
    private PeriodOrderAdapter adapter;
    private List<PeriodOrderEntity> list = null;

    public PaymentOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_order, container, false);

        lvPaymentOrder = (ListView) view.findViewById(R.id.lvPaymentOrder);
        adapter = new PeriodOrderAdapter(getActivity().getApplicationContext(),list);
        lvPaymentOrder.setAdapter(adapter);
        return view;
    }


}
