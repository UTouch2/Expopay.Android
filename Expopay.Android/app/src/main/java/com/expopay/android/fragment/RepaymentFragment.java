package com.expopay.android.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.BillRepaymentAdapter;
import com.expopay.android.model.BillRepaymentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepaymentFragment extends BaseFragment {

    private ListView lvRepayment;
    private BillRepaymentAdapter adapter;

    public RepaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repayment, container, false);
        lvRepayment = (ListView) view.findViewById(R.id.lvRepayment);
        adapter = new BillRepaymentAdapter(getActivity().getApplicationContext(), testData());
        lvRepayment.setAdapter(adapter);

        return view;
    }

    private List<BillRepaymentEntity> testData() {
        List<BillRepaymentEntity> list = new ArrayList<BillRepaymentEntity>();
        for (int i = 0; i < 20; i++) {
            BillRepaymentEntity br = new BillRepaymentEntity();
            br.setRemainingDays("逾期5天");
            br.setProductName("iPHone");
            br.setOverdueAmount("440+30");
            br.setRepaymentTime("2015-10-26");
            br.setOrderTime("2015-10-10");
            br.setRepaymentPeriod("剩余8期");
            list.add(br);
        }
        return list;
    }
}
