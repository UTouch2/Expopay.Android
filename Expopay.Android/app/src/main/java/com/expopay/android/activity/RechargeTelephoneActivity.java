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

import com.expopay.android.R;
import com.expopay.android.view.CustormLoadingButton;

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
        imgContacts.setOnClickListener(this);
        charge10.setOnClickListener(this);
        charge20.setOnClickListener(this);
        charge30.setOnClickListener(this);
        charge50.setOnClickListener(this);
        charge100.setOnClickListener(this);
        charge200.setOnClickListener(this);
        charge300.setOnClickListener(this);
        charge500.setOnClickListener(this);
        btnRecharge.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_recharge_telephone);

        assignViews();
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
            case R.id.btnRecharge:
                if(contacts != null) {
                    btnRecharge.showLoading("正在充值...");
                    btnRecharge.showResult("充值失败",false);
                    String amount = rechange.getText().toString().trim();
//                    Intent intent = new Intent(RechargeTelephoneActivity.this,);
//
//                    startActivity(intent);
                }
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
                // 当点击了按钮installment3时，改变控件的背景图片
                charge10.setBackgroundColor(Color.parseColor("#07D97E"));
                break;
            case 20:
                charge20.setBackgroundColor(Color.parseColor("#07D97E"));
                break;
            case 30:
                charge30.setBackgroundColor(Color.parseColor("#07D97E"));
                break;
            case 50:
                charge50.setBackgroundColor(Color.parseColor("#07D97E"));
                break;
            case 100:
                charge100.setBackgroundColor(Color.parseColor("#07D97E"));
                break;
            case 200:
                charge200.setBackgroundColor(Color.parseColor("#07D97E"));
                break;
            case 300:
                charge300.setBackgroundColor(Color.parseColor("#07D97E"));
                break;
            case 500:
                charge500.setBackgroundColor(Color.parseColor("#07D97E"));
                break;
            default:
                break;
        }
    }

    //清除掉所有的选中状态
    private void clearSelection() {
        rechange.setText("");
        charge10.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge20.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge30.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge50.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge100.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge200.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge300.setBackgroundColor(Color.parseColor("#F9F9F9"));
        charge500.setBackgroundColor(Color.parseColor("#F9F9F9"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor cursor = managedQuery(contactData, null, null, null,
                            null);
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
//                  switch (phone_type) {//此处请看下方注释
//                  case 2:
//                      result = phoneNumber;
//                      break;
//
//                  default:
//                      break;
//                  }
                }
                if (!phone.isClosed()) {
                    phone.close();
                }
            }
        }
        return result;
    }
}
