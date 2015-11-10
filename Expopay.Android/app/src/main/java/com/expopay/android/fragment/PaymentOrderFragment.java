package com.expopay.android.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
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
public class PaymentOrderFragment extends Fragment {

    private ListView lvPaymentOrder;
    private CustormLoadingView paymentOrder_loading;
    private PaymentOrderAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_order, container, false);
        lvPaymentOrder = (ListView) view.findViewById(R.id.lvPaymentOrder);
        paymentOrder_loading = (CustormLoadingView) view.findViewById(R.id.paymentOrder_loading);
        adapter = new PaymentOrderAdapter(getActivity().getApplicationContext(), new ArrayList<PaymentOrderEntity>());
        lvPaymentOrder.setAdapter(adapter);
        try {
            getPaymentOrder("123456", "1", "", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void getPaymentOrder(String openId, String orderSource, String pageIndex,
                                 String pageSize) throws JSONException {
        paymentOrder_loading.show();
        OrderRequest request = new OrderRequest(MyApplication.HOST + "");
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
                        List<PaymentOrderEntity> list = gson.fromJson(json.getJSONObject("").toString(), new TypeToken<List<PaymentOrderEntity>>() {
                        }.getType());
                        adapter.setData(list);
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
                System.out.println(result);
                paymentOrder_loading.showRetry();
                paymentOrder_loading.setRetryMessage("请求失败");
            }
        });
        request.execute();
//        cancelRequest(request);
    }

    private List<PaymentOrderEntity> testData() {
        List<PaymentOrderEntity> list = new ArrayList<PaymentOrderEntity>();
        for (int i = 0; i < 20; i++) {
            PaymentOrderEntity po = new PaymentOrderEntity();
            po.setProductName("水电煤");
            po.setOrderAmount("18.00");
            po.setOrderTime("2015-10-26");
            po.setOrderStatus("未完成");
            list.add(po);
        }
        return list;
    }

}
