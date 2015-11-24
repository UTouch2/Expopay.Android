package com.expopay.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/26.
 */
public class NBKCardpayResultFragment extends BaseFragment {

    private TextView orderAmountText, orderSourceText, payMethodTextt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nbkcardpay_result, container, false);
        orderAmountText = (TextView) v.findViewById(R.id.payresult_amount);
        orderSourceText = (TextView) v.findViewById(R.id.payresult_ordersource);
        payMethodTextt = (TextView) v.findViewById(R.id.payresult_paymethod);
        findViewById(R.id.nbkcardpay_result_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        Bundle args = getArguments();
        orderAmountText.setText("");
        orderSourceText.setText("");
        payMethodTextt.setText("");
        return v;
    }
}
