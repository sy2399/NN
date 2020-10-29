package com.example.imsoyeong.neighbornetwork;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by imsoyeong on 2017. 11. 7..
 */

public class ProfileInfoActivity extends Activity{
    SharedPreferences setting;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_info);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "NNmember.db",null,1);


        Intent intent = getIntent();
        //final String loginId = intent.getStringExtra("loginId");
        setting = getSharedPreferences("setting", 0);
        final String loginId = setting.getString("loginInfo","");

        Member loginInfo = dbHelper.findMemberById(loginId);

        TextView idInput = (TextView) findViewById(R.id.idInput);
        TextView nameInput = (TextView) findViewById(R.id.nameInput);
        TextView phoneInput = (TextView) findViewById(R.id.phoneInput);
        final EditText interestInput = (EditText) findViewById(R.id.interest);

        if(loginInfo.getInterests() != null){
            interestInput.setText(loginInfo.getInterests());


        }



        idInput.setText(loginInfo.getId());
        nameInput.setText(loginInfo.getName());
        phoneInput.setText(loginInfo.getPhone());

        Button registerUpdatebtn = (Button) findViewById(R.id.registerupdatebtn);
        registerUpdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String interests = interestInput.getText().toString();

                dbHelper.updateMember(loginId,interests);
                Intent intent1 = new Intent(getApplicationContext(), ProfileSettingActivity.class);
                startActivity(intent1);
            }
        });
    }
}
