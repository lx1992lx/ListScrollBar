package com.yyxk.listscrollbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ListScrollBar mListScrollBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListScrollBar= (ListScrollBar) findViewById(R.id.list);
//        在这里加载数据
//        mListScrollBar.setData();
    }
}
