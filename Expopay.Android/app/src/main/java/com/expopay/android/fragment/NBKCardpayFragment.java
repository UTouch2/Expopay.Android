package com.expopay.android.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.activity.ChooseCardActivity;
import com.expopay.android.model.CardEntity;
import com.expopay.android.view.CustormLoadingButton;

/**
 * Created by misxu012 on 2015/10/26.
 */
public class NBKCardpayFragment extends BaseFragment {
    CustormLoadingButton okButton;
    TextView cardNumberText;
    TextView amountText;
    EditText passwordText;

    String orderNumber, orderSource, orderAmount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nbkcardpay, container, false);
       // view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        okButton = (CustormLoadingButton) view.findViewById(R.id.nbkcardpay_okbtn);
        cardNumberText = (TextView) view.findViewById(R.id.nbkcardpay_cardnumber_text);
        amountText = (TextView) findViewById(R.id.nbkcardpay_amount_text);
        view.findViewById(R.id.nbkcardpay_choosecard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), ChooseCardActivity.class), 0);
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okButton.showLoading("正在努力加载中···");
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arg = getArguments();
        if (arg != null) {
            orderNumber = arg.getString("orderNumber");
            orderSource = arg.getString("orderSource");
            orderAmount = arg.getString("orderAmount");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            CardEntity card = (CardEntity) data.getSerializableExtra("card");
            cardNumberText.setText(card.getCardNumber());
        }
    }
}
