package com.expopay.android.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.activity.OrderDetailItemActivity;
import com.expopay.android.adapter.listview.PaymentOrderAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.PaymentOrderEntity;
import com.expopay.android.request.OrderRequest;
import com.expopay.android.view.CustormLoadingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentOrderFragment extends BaseFragment {

    private ListView lvPaymentOrder;
    private CustormLoadingView paymentOrder_loading;
    private PaymentOrderAdapter adapter;
    private int pageIndex = 0, pageSize =10;
    List<PaymentOrderEntity> paymentOrderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_order, container, false);
        lvPaymentOrder = (ListView) view.findViewById(R.id.lvPaymentOrder);
        paymentOrder_loading = (CustormLoadingView) view.findViewById(R.id.paymentOrder_loading);
        adapter = new PaymentOrderAdapter(getActivity().getApplicationContext(), new ArrayList<PaymentOrderEntity>());
        lvPaymentOrder.setAdapter(adapter);
        try {
            getPaymentOrder(getUser().getOpenId(), "1", pageIndex + "", pageSize + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        paymentOrder_loading.setRetryOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getPaymentOrder(getUser().getOpenId(), "1", pageIndex + "", pageSize + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        lvPaymentOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), OrderDetailItemActivity.class);
                intent.putExtra("paymentOrderData", paymentOrderList.get(position));
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    private void getPaymentOrder(String openId, String orderSource, String pageIndex,
                                 String pageSize) throws JSONException {
        paymentOrder_loading.show();
        paymentOrder_loading.showLoading();
        OrderRequest request = new OrderRequest(MyApplication.HOST + "/order/orderlist");
        request.setEntity(request.createGetOrdersParms(openId, orderSource, pageIndex, pageSize));
        request.setOutTime(10 * 1000);
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        // 成功
                        Gson gson = new Gson();
                        paymentOrderList = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(), new TypeToken<List<PaymentOrderEntity>>() {
                        }.getType());
                        adapter.setData(paymentOrderList);
                        adapter.notifyDataSetChanged();
                        paymentOrder_loading.dismiss();
                    } else {
                        // 失败
                        paymentOrder_loading.showRetry();
                        paymentOrder_loading.setRetryMessage(json.getJSONObject("header").getString("desc"));
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                    // 失败
                    paymentOrder_loading.showRetry();
                    paymentOrder_loading.setRetryMessage("数据解析异常");
                }
            }

            @Override
            public void onProgressUpdate(int i, int j) {

            }

            @Override
            public void onFilure(Exception result) {
                paymentOrder_loading.showRetry();
                paymentOrder_loading.setRetryMessage("网络请求失败");
            }
        });
        request.execute();
        cancelRequest(request);
    }

}
