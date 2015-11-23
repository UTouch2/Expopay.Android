package com.expopay.android.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.adapter.listview.RepaymentBillAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.RepaymentBillEntity;
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
public class RepaymentFragment extends BaseFragment {

    private ListView lvUnRepayment;
    private CustormLoadingView billUnrepayment_loading;
    private RepaymentBillAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repayment, container, false);
        lvUnRepayment = (ListView) view.findViewById(R.id.lvUnRepayment);
//        billUnrepayment_loading = (CustormLoadingView) view.findViewById(R.id.billUnrepayment_loading);
        adapter = new RepaymentBillAdapter(getActivity().getApplicationContext(), testData());
        lvUnRepayment.setAdapter(adapter);
//        try {
//            getBillRepayment("123456");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return view;
    }

    private void getBillRepayment(String openId,
                                  String orderSource,
                                  String paymentMethod,
                                  String orerAmount,
                                  String publicUtilityType,
                                  String publicUtilityNum) throws JSONException {
        billUnrepayment_loading.show();
        OrderRequest request = new OrderRequest(MyApplication.HOST + "");
        //request.setEntity(request.createCreateOrderParms(openId, "", "", "", "", ""));
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
                        List<RepaymentBillEntity> list = gson.fromJson(json.getJSONObject("").toString(), new TypeToken<List<RepaymentBillEntity>>() {
                        }.getType());
                        adapter.setData(list);
                        adapter.notifyDataSetChanged();
                        billUnrepayment_loading.dismiss();
                    } else {
                        // 失败
                        billUnrepayment_loading.showRetry();
                        billUnrepayment_loading.setRetryMessage(json.getJSONObject("header").getString("desc"));
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                    // 失败
                    billUnrepayment_loading.showRetry();
                    billUnrepayment_loading.setRetryMessage("数据解析异常");
                }
            }

            @Override
            public void onProgressUpdate(int i, int j) {

            }

            @Override
            public void onFilure(Exception result) {
                System.out.println(result);
                billUnrepayment_loading.showRetry();
                billUnrepayment_loading.setRetryMessage("请求失败");
            }
        });
        request.execute();
        cancelRequest(request);
    }

    private List<RepaymentBillEntity> testData() {
        List<RepaymentBillEntity> list = new ArrayList<RepaymentBillEntity>();
        for (int i = 0; i < 20; i++) {
            RepaymentBillEntity br = new RepaymentBillEntity();
            br.setOverdueDays("逾期6天");
            br.setProductName("iPHone6S");
            //br.setPro("土豪金64G");
            br.setOverdueAmount("440+30");
            br.setRepaymentTime("2015年10月30日");
            br.setOrderTime("2015年10月10日");
            br.setRepaymentPeriod("剩余10期");
            list.add(br);
        }
        return list;
    }
}
