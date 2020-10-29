package com.example.imsoyeong.neighbornetwork;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText et_id,et_pw;
    String id,pw;
    CheckBox rememberLogin;
    static String loginMemberID;

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "NNmember.db",null,1);

        Button registerBtn = (Button)findViewById(R.id.signupbtn);
        Button loginBtn = (Button) findViewById(R.id.loginbtn);
        //rememberLogin = (CheckBox) findViewById(R.id.rememberLogin);

        et_id = (EditText) findViewById(R.id.idInput);
        et_pw = (EditText) findViewById(R.id.passwordInput);

        setting = getSharedPreferences("setting", 0);
        editor = setting.edit();


//        rememberLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    String id = et_id.getText().toString();
//
//                    editor.putString("loginInfo", id);
//                    editor.putBoolean("Auto_login_enabled", true);
//                    editor.commit();
//                }
//            }
//        });

//        if(setting.getBoolean("Auto_login_enabled", true)){
//            et_id.setText(setting.getString("loginInfo", ""));
//            rememberLogin.setChecked(true);
//        }
        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(getApplicationContext(), "액티비티 전환", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), RegistActivity.class);
                startActivity(intent);


            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Member loginMember = new Member();

                id = et_id.getText().toString();
                pw = et_pw.getText().toString();



                if(id.length() == 0 ){
                    Toast.makeText(MainActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                    et_id.requestFocus();
                    return;
                }
                if(pw.length() == 0 ){
                    Toast.makeText(MainActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    et_pw.requestFocus();
                    return;
                }

                loginMember.setId(id);
                loginMember.setPassword(pw);
                Member loginResult = dbHelper.getMember(loginMember);
                if(loginResult!=null){



                    Toast.makeText(MainActivity.this, "로그인성공", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), BarActivity.class);
                    intent.putExtra("loginId", id);
                    editor.putString("loginInfo", id);
                    editor.commit();
                    startActivity(intent);



                }else{
                    Toast.makeText(MainActivity.this, "로그인실패", Toast.LENGTH_SHORT).show();
                    return;
                }




            }
        });




    }
}