package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.LogisticsAdapter;
import com.expopay.android.model.OrderDetailsEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends BaseActivity {

    private LinearLayout llAddress;
    private TextView consigneeName;
    private TextView consigneeMobile;
    private TextView consigneeAddress;
    private ImageView productImg;
    private TextView productName;
    private TextView properties;
    private TextView orderAmount;
    private TextView repaymentPeriod;
    private TextView serviceAmount;
    private TextView cancelPeriodAmount;
    private TextView orderStatus;
    private TextView orderNumber;
    private TextView transcationCode;
    private TextView orderTime;
    private TextView carrierCompany;
    private TextView carrierCode;
    private Button btnContact;
    private Button btnCancelOrder;
    private ListView lvLogistics;
    private LogisticsAdapter adapter;

    private void assignViews() {
        llAddress = (LinearLayout) findViewById(R.id.llAddress);
        consigneeName = (TextView) findViewById(R.id.consigneeName);
        consigneeMobile = (TextView) findViewById(R.id.consigneeMobile);
        consigneeAddress = (TextView) findViewById(R.id.consigneeAddress);
        productImg = (ImageView) findViewById(R.id.productImg);
        productName = (TextView) findViewById(R.id.productName);
        properties = (TextView) findViewById(R.id.properties);
        orderAmount = (TextView) findViewById(R.id.orderAmount);
        repaymentPeriod = (TextView) findViewById(R.id.repaymentPeriod);
        serviceAmount = (TextView) findViewById(R.id.serviceAmount);
        cancelPeriodAmount = (TextView) findViewById(R.id.periodAmount);
        orderStatus = (TextView) findViewById(R.id.orderStatus);
        orderNumber = (TextView) findViewById(R.id.orderNumber);
        transcationCode = (TextView) findViewById(R.id.transcationCode);
        orderTime = (TextView) findViewById(R.id.orderTime);
        carrierCompany = (TextView) findViewById(R.id.carrierCompany);
        carrierCode = (TextView) findViewById(R.id.carrierCode);
        btnCancelOrder = (Button) findViewById(R.id.btnCancelOrder);
        btnContact = (Button) findViewById(R.id.btnContact);
        lvLogistics = (ListView) findViewById(R.id.lvLogistics);
        adapter = new LogisticsAdapter(this,testData());
        lvLogistics.setAdapter(adapter);
        setListViewHeightBasedOnChildren(lvLogistics);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("订单详情");
        setContentView(R.layout.activity_order_detail);

        assignViews();
        setTextView();
        llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void setTextView(){
        productImg.setImageResource(R.mipmap.mall_mobile);
        productName.setText("iPhone6S");
        properties.setText("白色");
        orderAmount.setText("5200");
        repaymentPeriod.setText("12期");
        serviceAmount.setText("12元");
        cancelPeriodAmount.setText("每期还款470元");
        consigneeName.setText("克里斯");
        consigneeMobile.setText("13600000000");
        consigneeAddress.setText("云南省昆明市五华区海源中路1088号和成国际A座25楼");
        orderStatus.setText("已完成");
        orderNumber.setText("1234567890");
        transcationCode.setText("交易流水号:678839903987484");
        orderTime.setText("交易时间：2015-09-26  00:00:00");
        carrierCompany.setText("顺丰快递");
        carrierCode.setText("888888888888");
    }

    private List<OrderDetailsEntity> testData(){
        List<OrderDetailsEntity> list = new ArrayList<OrderDetailsEntity>();
        for (int i = 0; i <10 ; i++){
            OrderDetailsEntity le = new OrderDetailsEntity();
            le.setLogistics("【昆明市】正在配送中，李师傅13900003325  2015年10月05日   09:10:00");
            list.add(le);
        }
        return list;
    }

    //ListView嵌套到ScrollView中显示
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
