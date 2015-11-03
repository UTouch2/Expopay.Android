package com.expopay.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.expopay.android.R;
import com.expopay.android.activity.ChooseCardActivity;
import com.expopay.android.view.CustormLoadingButton;

/**
 * Created by misxu012 on 2015/10/26.
 */
public class NBKCardpayFragment extends BaseFragment {
    CustormLoadingButton okButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nbkcardpay, container, false);
        okButton = (CustormLoadingButton) view.findViewById(R.id.nbkcardpay_okbtn);
        view.findViewById(R.id.nbkcardpay_choosecard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), ChooseCardActivity.class), 0);
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
