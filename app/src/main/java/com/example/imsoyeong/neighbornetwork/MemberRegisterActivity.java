package com.example.imsoyeong.neighbornetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by imsoyeong on 2017. 11. 5..
 */

public class MemberRegisterActivity extends Activity{


    EditText et_id,et_pw, et_pwcheck, et_name, et_phone;
    String id,pw, pwcheck, name, addressNum, addressDetail,phone, admin_flag;
    boolean idCheckFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_register);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "NNmember.db",null,1);


        et_id = (EditText) findViewById(R.id.idInput);
        et_name = (EditText) findViewById(R.id.nameInput);
        et_phone = (EditText) findViewById(R.id.phoneInput);

        et_pw = (EditText) findViewById(R.id.passwordInput);
        et_pwcheck=(EditText) findViewById(R.id.passwordCheckInput);


        final Button idCheckBtn = (Button) findViewById(R.id.idCheckbtn);
        Button registerBtn = (Button)findViewById(R.id.registerbtn);



        idCheckBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                id = et_id.getText().toString();
                boolean flag = dbHelper.idCheck(id);
                if(flag){
                    Toast.makeText(MemberRegisterActivity.this, "아이디가 이미 존재합니다", Toast.LENGTH_SHORT).show();
                    et_id.requestFocus();
                    return;
                }else{
                    Toast.makeText(MemberRegisterActivity.this, "아이디 사용가능", Toast.LENGTH_SHORT).show();

                    idCheckFlag=true;
                }
            }
        });





        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                id = et_id.getText().toString();
                name = et_name.getText().toString();
                phone = et_phone.getText().toString();

                pw = et_pw.getText().toString();
                pwcheck = et_pwcheck.getText().toString();



                if(id.length() == 0 ){
                    Toast.makeText(MemberRegisterActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                    et_id.requestFocus();
                    return;
                }

                if(idCheckFlag == false){
                    Toast.makeText(MemberRegisterActivity.this, "아이디 중복 체크를 해주세요", Toast.LENGTH_SHORT).show();
                    idCheckBtn.requestFocus();
                    return;
                }
                if(pw.length() == 0 ){
                    Toast.makeText(MemberRegisterActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    et_pw.requestFocus();
                    return;
                }


                if(pwcheck.length() == 0 ){
                    Toast.makeText(MemberRegisterActivity.this, "상세주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    et_pwcheck.requestFocus();
                    return;
                }
                if(!pw.equals(pwcheck)){
                    Toast.makeText(MemberRegisterActivity.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    et_pwcheck.setText("");
                    et_pwcheck.requestFocus();

                    return;
                }
                if(name.length() == 0 ){
                    Toast.makeText(MemberRegisterActivity.this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    et_name.requestFocus();
                    return;
                }
                if(phone.length() == 0 ){
                    Toast.makeText(MemberRegisterActivity.this, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    et_phone.requestFocus();
                    return;
                }

                Intent searchAddressIntent = getIntent();
                addressNum = searchAddressIntent.getExtras().getString("address");
                addressDetail = searchAddressIntent.getExtras().getString("address_detail");
                admin_flag = searchAddressIntent.getExtras().getString("admin_flag");


                Member registerMember = new Member();
                registerMember.setId(id);
                registerMember.setPassword(pw);
                registerMember.setName(name);
                registerMember.setPhone(phone);
                registerMember.setAddress_num(addressNum);
                registerMember.setAddress_detail(addressDetail);
                registerMember.setAdmin(admin_flag);

                //Toast.makeText(getApplicationContext(), registerMember.toString(), Toast.LENGTH_LONG).show();
                dbHelper.insertMember(registerMember);

                Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


            }
        });

    }

}
