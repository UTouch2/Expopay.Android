package com.expopay.android.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.adapter.listview.BillRepaymentAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.BillRepaymentEntity;
import com.expopay.android.request.CardRequest;
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
public class RepaymentFragment extends Fragment {

    private ListView lvRepayment;
    private CustormLoadingView billRepayment_loading;
    private BillRepaymentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repayment, container, false);
        lvRepayment = (ListView) view.findViewById(R.id.lvRepayment);
        billRepayment_loading = (CustormLoadingView) view.findViewById(R.id.billRepayment_loading);
        adapter = new BillRepaymentAdapter(getActivity().getApplicationContext(), new ArrayList<BillRepaymentEntity>());
        lvRepayment.setAdapter(adapter);
        try {
            getBillRepayment("123456");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void getBillRepayment(String openId) throws JSONException {
        billRepayment_loading.show();
        CardRequest request = new CardRequest(MyApplication.HOST + "/system/version");
        request.setEntity(request.createCardListParams(openId));
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
                        List<BillRepaymentEntity> list = gson.fromJson(json.getJSONObject("").toString(), new TypeToken<List<BillRepaymentEntity>>() {
                        }.getType());
                        adapter.setData(list);
                        adapter.notifyDataSetChanged();
                        billRepayment_loading.dismiss();
                    } else {
                        // 失败
                        billRepayment_loading.showRetry();
                        billRepayment_loading.setMessage(json.getJSONObject("header").getString("desc"));
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                    // 失败
                    billRepayment_loading.showRetry();
                    billRepayment_loading.setMessage("数据解析异常");
                }
            }

            @Override
            public void onProgressUpdate(int i, int j) {

            }

            @Override
            public void onFilure(Exception result) {
                System.out.println(result);
                billRepayment_loading.showRetry();
                billRepayment_loading.setMessage("请求失败");
            }
        });
        request.execute();
//        cancelRequest(request);
    }

    private List<BillRepaymentEntity> testData() {
        List<BillRepaymentEntity> list = new ArrayList<BillRepaymentEntity>();
        for (int i = 0; i < 20; i++) {
            BillRepaymentEntity br = new BillRepaymentEntity();
            br.setRemainingDays("10月16日");
            br.setProductName("iPHone");
            br.setPro("金粉色32G");
            br.setOverdueAmount("440+30");
            br.setRepaymentTime("2020年10月30日");
            br.setOrderTime("2018年10月30日");
            br.setRepaymentPeriod("剩余8期");
            br.setOverdueDays("逾期5天");
            list.add(br);
        }
        return list;
    }
}
