package com.buffer;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.buffer.bean.DemoBean;
import com.bufferdb.BufferDB;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DemoBean> lists = new ArrayList<>();
    private DemoBean demoBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoBean=new DemoBean();
       // demoBean.setBigDecimal(new BigDecimal(9.1212111102233));
        demoBean.setTime(new Date(701857136000L));
        demoBean.setCredit(-9.2F);
        demoBean.setValid(true);
        demoBean.setId(11546513001L);
        demoBean.setName("gxy 😯");
        demoBean.setGrade(12);

        lists.add(demoBean);


    }



    public void save(View view) {

        BufferDB.get(GxyApplication.get()).writeInDatabase("string1", "{}");
        BufferDB.get(GxyApplication.get()).writeInDatabase("test_1", 1);
        BufferDB.get(GxyApplication.get()).writeInDatabase("test_2", 2.0f);
        BufferDB.get(GxyApplication.get()).writeInDatabase("test_3", "3");
        BufferDB.get(GxyApplication.get()).writeInDatabase("test_4", false);


        BufferDB.get(GxyApplication.get()).writeInDatabase("demo", demoBean);

        BufferDB.get(GxyApplication.get()).writeInDatabase("demoList", lists);


    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void get(View view) {
        BufferDB.get(GxyApplication.get()).readStringFromDatabase("string1");
        BufferDB.get(GxyApplication.get()).readIntFromDatabase("test_1", 0);
        BufferDB.get(GxyApplication.get()).readDoubleFromDatabase("test_2", 0D);
        BufferDB.get(GxyApplication.get()).readStringFromDatabase("test_3", "");
        BufferDB.get(GxyApplication.get()).readBooleanFromDatabase("test_4", false);


        DemoBean demoBean = BufferDB.get(this).readFromDatabase("demo", DemoBean.class);
        Log.i("demoBean", demoBean.toString());
    }
}
