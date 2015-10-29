package com.expopay.android.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.BillUnrepaymentAdapter;
import com.expopay.android.model.BillUnrepaymentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnrepaymentFragment extends Fragment {

    private ListView lvUnRepayment;
    private BillUnrepaymentAdapter adapter;

    public UnrepaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unrepayment, container, false);
        lvUnRepayment = (ListView) view.findViewById(R.id.lvUnRepayment);
        adapter = new BillUnrepaymentAdapter(getActivity().getApplicationContext(), testData());
        lvUnRepayment.setAdapter(adapter);

        return view;
    }

    private List<BillUnrepaymentEntity> testData() {
        List<BillUnrepaymentEntity> list = new ArrayList<BillUnrepaymentEntity>();
        for (int i = 0; i < 20; i++) {
            BillUnrepaymentEntity br = new BillUnrepaymentEntity();
            br.setOverdueDays("逾期6天");
            br.setProductName("iPHone6S");
            br.setPro("土豪金64G");
            br.setOverdueAmount("440+30");
            br.setRepaymentTime("2015年10月30日");
            br.setOrderTime("2015年10月10日");
            br.setRepaymentPeriod("剩余10期");
            list.add(br);
        }
        return list;
    }
}
