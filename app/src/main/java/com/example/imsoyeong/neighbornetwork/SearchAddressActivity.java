package com.example.imsoyeong.neighbornetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchAddressActivity extends Activity {

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    private EditText et_address,et_address_detail;
    String address, address_detail;

    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_address);

        et_address = (EditText)findViewById(R.id.et_address);
        et_address_detail = (EditText) findViewById(R.id.et_address_detail);

        Button btn_search = (Button)findViewById(R.id.button);
        final Button nextbtn = (Button) findViewById(R.id.nextbtn);

        if (btn_search != null)
            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SearchAddressActivity.this, SearchAddressWebView.class);
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                    flag=true;
                }
            });

        nextbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                address = et_address.getText().toString();
                address_detail=et_address_detail.getText().toString();

                if(address.length() ==0){
                    Toast.makeText(SearchAddressActivity.this, "주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    et_address.requestFocus();
                    return;
                }

                if(address_detail.length()==0){
                    Toast.makeText(SearchAddressActivity.this, "상세주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                    et_address_detail.requestFocus();
                    return;
                }
                if(flag ==false){
                    Toast.makeText(SearchAddressActivity.this, "우편번호검색을 해주세요", Toast.LENGTH_SHORT).show();
                    nextbtn.requestFocus();
                    return;
                }

                Intent adminIntent = getIntent();
                String admin_flag = adminIntent.getExtras().getString("admin_flag");
                Intent intent = new Intent(getApplicationContext(), MemberRegisterActivity.class);

                intent.putExtra("admin_flag", admin_flag);
                intent.putExtra("address", address);
                intent.putExtra("address_detail", address_detail);
                startActivity(intent);



            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        super.onActivityResult(requestCode, resultCode, intent);

        switch(requestCode){

            case SEARCH_ADDRESS_ACTIVITY:

                if(resultCode == RESULT_OK){

                    String data = intent.getExtras().getString("data");
                    if (data != null)
                        et_address.setText(data);

                }
                break;

        }

    }


}



