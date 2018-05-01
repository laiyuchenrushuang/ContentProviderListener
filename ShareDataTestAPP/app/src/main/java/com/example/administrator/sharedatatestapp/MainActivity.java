package com.example.administrator.sharedatatestapp;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final Uri URI = Uri.parse("content://com.oak.contentprovider");
    private ListView mListView;
    private ArrayList<String[]> mData = new ArrayList<>();
    private String  mLock = "select";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.listview);
        String[] textString = new String[3];
        //查询数据库中的内容，然后返回结果
        Cursor cursor =  getContentResolver().query(URI,null,null,null,null);
        if (cursor == null){
            Log.d(TAG, "cursor = "+cursor);
            return;
        }
        //将返回的结果的指针移到第一个
        cursor.moveToFirst();
        //循环结果
        for (int i=0;i<cursor.getCount();i++) {
            synchronized(mLock){
                //将数据赋值给txt变量
                String txtPackageName =  cursor.getString(cursor.getColumnIndex("name"));
                String txtAppName =  cursor.getString(cursor.getColumnIndex("appName"));
                String txtRequestCount =  cursor.getString(cursor.getColumnIndex("requestCount"));
                textString[0] = txtPackageName;
                textString[1] = txtAppName;
                textString[2] = txtRequestCount;
                String[] text = {textString[0],textString[1],textString[2]};
                mData.add(text);
                textString[0] = textString[1]=textString[2]=null;
            }
            //没循环一次，移到指针到下一个
            cursor.moveToNext();
        }
        //最后置空
        cursor =null;
        textString = null;

        final MyAdater adater = new MyAdater(this,mData);
        mListView.setAdapter(adater);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getContentResolver().delete(URI, "_id"+"="+i,null);
            }
        });
    }
}
