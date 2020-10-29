package com.example.imsoyeong.neighbornetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by imsoyeong on 2017. 11. 5..
 */

public class TimeLineActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "NNmember.db",null,1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        TextView username = (TextView)findViewById(R.id.username);
        //TextView userId = (TextView) findViewById(R.id.userId);

        Intent intent = getIntent();
        final String loginId = intent.getStringExtra("loginId");
        final Member loginMember = dbHelper.findMemberById(loginId);

        username.setText(loginMember.getName());
        //userId.setText(loginMember.getId());


        Button profileBtn = (Button) findViewById(R.id.profilebtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TimeLineActivity.this, loginMember.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ProfileSettingActivity.class);
                intent.putExtra("loginId", loginId);
                startActivity(intent);

            }
        });

        Button moveBtn = (Button) findViewById(R.id.move);
        moveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BarActivity.class);
                startActivity(intent);

            }
        });

    }
}
