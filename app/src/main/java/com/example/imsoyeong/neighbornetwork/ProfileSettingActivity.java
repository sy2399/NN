package com.example.imsoyeong.neighbornetwork;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by imsoyeong on 2017. 11. 7..
 */

public class ProfileSettingActivity extends Activity{
    SharedPreferences setting;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_setting);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "NNmember.db",null,1);

        setting = getSharedPreferences("setting", 0);
        final String loginId = setting.getString("loginInfo","");


        Intent intent = getIntent();
        //final String loginId = intent.getStringExtra("loginId");


        Button infoBtn = (Button) findViewById(R.id.infobtn);
        Button profileBtn = (Button) findViewById(R.id.profilebtn);
        Button alarmBtn = (Button) findViewById(R.id.alarambtn);
        Button updateContentBtn = (Button) findViewById(R.id.updateContentBtn);

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), ProfileInfoActivity.class);
                intent2.putExtra("loginId", loginId);
                startActivity(intent2);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), ProfileRangeActivity.class);
                intent3.putExtra("loginId", loginId);
                startActivity(intent3);
            }
        });

        alarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), SearchAddressActivity.class);
                intent4.putExtra("loginId", loginId);
                startActivity(intent4);
            }
        });

        updateContentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(), UpdateContentsActivity.class);
                startActivity(intent5);
            }
        });
    }
}
