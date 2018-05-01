package com.example.administrator.sharedatademo;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String mPackageName = null;
    private String mAppName = null;
    private int mRequestCount1 = 0;
    private int mRequestCount2 = 0;
    private TextView mTextViewButton1;
    private TextView mTextViewButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewButton1 = findViewById(R.id.textview1);
        mTextViewButton2 = findViewById(R.id.textview2);

        mPackageName = getPackageInfo(this).toString();
        mAppName = getPackageInfo(this).packageName;
    }

    public void Button2(View view) {
        mRequestCount2++;
        String packagex = mPackageName;
        String appNamex = mAppName;
        listenerInsetProvider(packagex, appNamex, mRequestCount2);
        mTextViewButton2.setText(packagex + " \n " + appNamex + " \n " + mRequestCount2);
    }


    public void Button1(View view) {
        mRequestCount1++;
        String packagex1 = "laiyuOneB";
        String appNamex1 = "laiyuOneB";
        listenerInsetProvider(packagex1, appNamex1, mRequestCount1);
        mTextViewButton1.setText(packagex1 + " \n " + appNamex1 + " \n " + mRequestCount1);
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pInfo = null;

        try {
            //通过PackageManager可以得到PackageInfo
            PackageManager pManager = context.getPackageManager();
            pInfo = pManager.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pInfo;
    }

    private void listenerInsetProvider(String packagex, String appNamex, int mRequestCount2) {
        ContentValues values = new ContentValues();
        values.put("name", packagex);
        values.put("appName", appNamex);
        values.put("requestCount", mRequestCount2);
        getContentResolver().insert(MyProvider.URL, values);
    }
}
