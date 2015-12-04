package com.expopay.android.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.util.PatternUtil;
import com.expopay.android.R;
import com.expopay.android.request.OrderRequest;
import com.expopay.android.view.CustormLoadingButton;

import org.json.JSONException;
import org.json.JSONObject;

public class RechargeTelephoneActivity extends BaseActivity implements View.OnClickListener{

    private EditText contacts;
    private ImageView imgContacts;
    private TextView rechange;
    private Button charge10;
    private Button charge20;
    private Button charge30;
    private Button charge50;
    private Button charge100;
    private Button charge200;
    private Button charge300;
    private Button charge500;
    private CustormLoadingButton btnRecharge;

    private void assignViews() {
        contacts = (EditText) findViewById(R.id.contacts);
        imgContacts = (ImageView) findViewById(R.id.imgContacts);
        rechange = (TextView) findViewById(R.id.rechange);
        charge10 = (Button) findViewById(R.id.charge10);
        charge20 = (Button) findViewById(R.id.charge20);
        charge30 = (Button) findViewById(R.id.charge30);
        charge50 = (Button) findViewById(R.id.charge50);
        charge100 = (Button) findViewById(R.id.charge100);
        charge200 = (Button) findViewById(R.id.charge200);
        charge300 = (Button) findViewById(R.id.charge300);
        charge500 = (Button) findViewById(R.id.charge500);
        btnRecharge = (CustormLoadingButton) findViewById(R.id.btnRecharge);
        btnRecharge.showNormal("充值");
        btnRecharge.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                finish();
            }

            @Override
            public void onFailureResult() {
                btnRecharge.showNormal("充值");
            }
        });
        imgContacts.setOnClickListener(this);
        charge10.setOnClickListener(this);
        charge20.setOnClickListener(this);
        charge30.setOnClickListener(this);
        charge50.setOnClickListener(this);
        charge100.setOnClickListener(this);
        charge200.setOnClickListener(this);
        charge300.setOnClickListener(this);
        charge500.setOnClickListener(this);

        setTabSelection(10);
        rechange.setText("10");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("充值话费");
        setContentView(R.layout.activity_recharge_telephone);

        assignViews();

        btnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact = contacts.getText().toString().trim();
                if(PatternUtil.isMobile(contact)) {
                    String amount = rechange.getText().toString().trim();
                    try {
                        getOrder("123","1","","","","");
                    } catch (JSONException e) {
                    }
                }
                else {
                    Toast.makeText(RechargeTelephoneActivity.this, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.charge10:
                setTabSelection(10);
                rechange.setText("10");
                break;
            case R.id.charge20:
                setTabSelection(20);
                rechange.setText("20");
                break;
            case R.id.charge30:
                setTabSelection(30);
                rechange.setText("30");
                break;
            case R.id.charge50:
                setTabSelection(50);
                rechange.setText("50");
                break;
            case R.id.charge100:
                setTabSelection(100);
                rechange.setText("100");
                break;
            case R.id.charge200:
                setTabSelection(200);
                rechange.setText("200");
                break;
            case R.id.charge300:
                setTabSelection(300);
                rechange.setText("300");
            break;
            case R.id.charge500:
                setTabSelection(500);
                rechange.setText("500");
                break;
            case R.id.imgContacts:
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("vnd.android.cursor.dir/phone");
                startActivityForResult(i, 0);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        switch (index) {
            case 10:
                charge10.setBackgroundColor(Color.parseColor("#07D97E"));
                charge10.setTextColor(Color.WHITE);
                break;
            case 20:
                charge20.setBackgroundColor(Color.parseColor("#07D97E"));
                charge20.setTextColor(Color.WHITE);
                break;
            case 30:
                charge30.setBackgroundColor(Color.parseColor("#07D97E"));
                charge30.setTextColor(Color.WHITE);
                break;
            case 50:
                charge50.setBackgroundColor(Color.parseColor("#07D97E"));
                charge50.setTextColor(Color.WHITE);
                break;
            case 100:
                charge100.setBackgroundColor(Color.parseColor("#07D97E"));
                charge100.setTextColor(Color.WHITE);
                break;
            case 200:
                charge200.setBackgroundColor(Color.parseColor("#07D97E"));
                charge200.setTextColor(Color.WHITE);
                break;
            case 300:
                charge300.setBackgroundColor(Color.parseColor("#07D97E"));
                charge300.setTextColor(Color.WHITE);
                break;
            case 500:
                charge500.setBackgroundColor(Color.parseColor("#07D97E"));
                charge500.setTextColor(Color.WHITE);
                break;
            default:
                break;
        }
    }

    //清除掉所有的选中状态
    private void clearSelection() {
        rechange.setText("");
        charge10.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge10.setTextColor(Color.parseColor("#666666"));
        charge20.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge20.setTextColor(Color.parseColor("#666666"));
        charge30.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge30.setTextColor(Color.parseColor("#666666"));
        charge50.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge50.setTextColor(Color.parseColor("#666666"));
        charge100.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge100.setTextColor(Color.parseColor("#666666"));
        charge200.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge200.setTextColor(Color.parseColor("#666666"));
        charge300.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge300.setTextColor(Color.parseColor("#666666"));
        charge500.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge500.setTextColor(Color.parseColor("#666666"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor cursor = managedQuery(contactData, null, null, null, null);
                    cursor.moveToFirst();
                    String num = this.getContactPhone(cursor);
                    contacts.setText(num);
                }
                break;

            default:
                break;
        }
    }

    private String getContactPhone(Cursor cursor) {
        int phoneColumn = cursor
                .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
        int phoneNum = cursor.getInt(phoneColumn);
        String result = "";
        if (phoneNum > 0) {
            // 获得联系人的ID号
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contactId = cursor.getString(idColumn);
            // 获得联系人电话的cursor
            Cursor phone = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
                            + contactId, null, null);
            if (phone.moveToFirst()) {
                for (; !phone.isAfterLast(); phone.moveToNext()) {
                    int index = phone
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    int typeindex = phone
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
                    int phone_type = phone.getInt(typeindex);
                    String phoneNumber = phone.getString(index);
                    result = phoneNumber;
                  switch (phone_type) {//此处请看下方注释
                  case 2:
                      result = phoneNumber;
                      break;

                  default:
                      break;
                  }
                }
                if (!phone.isClosed()) {
                    phone.close();
                }
            }
        }
        return result;
    }

    private void getOrder(String openId, String orderSource, String paymentMethod, String orerAmount,
                          String publicUtilityType, String publicUtilityNum)throws JSONException{
        btnRecharge.showLoading("正在充值...");
        OrderRequest request = new OrderRequest("");
//        request.setEntity(request.createCreateOrderParms(openId,
//                orderSource,
//                paymentMethod,
//                orerAmount,
//                publicUtilityType,
//                publicUtilityNum));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                btnRecharge.showResult("网络请求失败", false);
            }

            @Override
            public void onSuccess(Object o) {
                try {
                    JSONObject json = (JSONObject) o;
                    if(json.getJSONObject("header").getString("code").equals("0000")){
                        btnRecharge.showResult("下单成功", true);
                    }else{
                        btnRecharge.showResult(json.getJSONObject("header").getString("desc"), false);
                    }
                } catch (JSONException e) {

                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
    }
}
