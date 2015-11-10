package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.ChooseCardListAdapter;
import com.expopay.android.adapter.listview.ChooseQuestionListAdapter;
import com.expopay.android.model.CardEntity;
import com.expopay.android.model.PasswordQuestionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/24.
 */
public class ChooseQuestionActivity extends BaseActivity {
    ListView mListView;
    List<PasswordQuestionEntity> questionEntities = new ArrayList<>();
    ChooseQuestionListAdapter adapter;
    PasswordQuestionEntity result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_choosecard);
        questionEntities =(List<PasswordQuestionEntity>)getIntent().getSerializableExtra("questions");
        mListView = (ListView) findViewById(R.id.choosecard_listview);
        adapter = new ChooseQuestionListAdapter(this, questionEntities);
        mListView.setAdapter(adapter);
    }

    public void closeOnclick(View v) {
        finish();
    }

    public void okOnclick(View v) {
        result = (PasswordQuestionEntity) mListView.getTag();
        setResult(RESULT_OK, new Intent().putExtra("question", result));
        finish();
    }
}
