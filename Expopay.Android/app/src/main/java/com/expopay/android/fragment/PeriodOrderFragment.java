package com.expopay.android.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.adapter.listview.PeriodOrderAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.PeriodOrderEntity;
import com.expopay.android.request.OrderRequest;
import com.expopay.android.view.CustormLoadingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PeriodOrderFragment extends BaseFragment {

    private ListView lvPeriodOrder;
    private CustormLoadingView periodOrder_loading;
    private PeriodOrderAdapter adapter;

    private int pageIndex = 0, pageSize = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_period_order, container, false);

        lvPeriodOrder = (ListView) view.findViewById(R.id.lvPeriodOrder);
        periodOrder_loading = (CustormLoadingView) view.findViewById(R.id.periodOrder_loading);
        adapter = new PeriodOrderAdapter(getActivity().getApplicationContext(), new ArrayList<PeriodOrderEntity>());
        lvPeriodOrder.setAdapter(adapter);
        try {
            getPeriodOrder(getUser().getOpenId(), "2", pageIndex + "", pageSize + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void getPeriodOrder(String openId, String orderSource, String pageIndex,
                                String pageSize) throws JSONException {
        periodOrder_loading.show();
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
                        List<PeriodOrderEntity> list = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(), new TypeToken<List<PeriodOrderEntity>>() {
                        }.getType());
                        adapter.setData(list);
                        adapter.notifyDataSetChanged();
                        periodOrder_loading.dismiss();
                    } else {
                        // 失败
                        periodOrder_loading.showRetry();
                        periodOrder_loading.setRetryMessage(json.getJSONObject("header").getString("desc"));
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                    // 失败
                    periodOrder_loading.showRetry();
                    periodOrder_loading.setRetryMessage("数据解析异常");
                }
            }

            @Override
            public void onProgressUpdate(int i, int j) {

            }

            @Override
            public void onFilure(Exception result) {
                System.out.println(result);
                periodOrder_loading.showRetry();
                periodOrder_loading.setRetryMessage("请求失败");
            }
        });
        request.execute();
        cancelRequest(request);
    }
}
