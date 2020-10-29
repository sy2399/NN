package com.example.imsoyeong.neighbornetwork;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by imsoyeong on 2017. 11. 8..
 */

public class ProfileRangeActivity extends Activity{

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_range);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "NNmember.db",null,1);

        Intent intent = getIntent();
        //final String loginId = intent.getStringExtra("loginId").toString();
        setting = getSharedPreferences("setting", 0);
        final String loginId = setting.getString("loginInfo","");

        Member loginMember = dbHelper.findMemberById(loginId);


        String addressRange = loginMember.getAddress_range();
        String profileRange = loginMember.getProfile_range();


        final RadioGroup addressRadio = (RadioGroup) findViewById(R.id.addressRadio);
        final RadioGroup profileRadio = (RadioGroup) findViewById(R.id.profileRadio);

        RadioButton addressOption1 = (RadioButton)findViewById(R.id.addressRangeOption1);
        RadioButton addressOption2 = (RadioButton) findViewById(R.id.addressRangeOption2);


        RadioButton profileRangeOption1 = (RadioButton) findViewById(R.id.profileRangeOption1);
        RadioButton profileRangeOprion2 = (RadioButton) findViewById(R.id.profileRangeOption2);

        if(addressRange.equals("0")){
            addressOption1.setChecked(true);
        }else{
            addressOption2.setChecked(true);
        }

        if(profileRange.equals("0")){
            profileRangeOption1.setChecked(true);
        }else{
            profileRangeOprion2.setChecked(true);
        }

        Button saveBtn = (Button) findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int addressChecked = addressRadio.getCheckedRadioButtonId();
                int profileChecked = profileRadio.getCheckedRadioButtonId();

                RadioButton rb1 = (RadioButton) findViewById(addressChecked);
                RadioButton rb2 = (RadioButton) findViewById(profileChecked);
                String addressRange;
                String profileRange;

                if(rb1.getText().equals("전체 주소 공개")){
                    addressRange = "0";
                }else{
                    addressRange = "1";
                }

                if(rb2.getText().equals("전체 공개")){
                    profileRange = "0";
                }else{
                    profileRange = "1";
                }
                dbHelper.updateRange(addressRange, profileRange, loginId);

                //Member memberInfo = dbHelper.findMemberById(loginId);
                //Toast.makeText(ProfileRangeActivity.this, memberInfo.toString(), Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), ProfileSettingActivity.class);
                startActivity(intent2);
            }
        });





    }
}
