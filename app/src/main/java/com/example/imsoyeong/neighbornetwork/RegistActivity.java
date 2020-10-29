package com.example.imsoyeong.neighbornetwork;

/**
 * Created by imsoyeong on 2017. 11. 1..
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class RegistActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_layout);
        ImageButton adminBtn = (ImageButton)findViewById(R.id.adminbtn);
        ImageButton generalBtn = (ImageButton)findViewById(R.id.generalbtn);

        adminBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(getApplicationContext(), SearchAddressActivity.class);
                intent.putExtra("admin_flag", "0");
                startActivity(intent);


            }
        });

        generalBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(getApplicationContext(), "액티비티 전환", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), SearchAddressActivity.class);
                intent.putExtra("admin_flag", "1");
                startActivity(intent);


            }
        });
    }


}

