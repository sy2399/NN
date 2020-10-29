package com.example.imsoyeong.neighbornetwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FilterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_filter);

        Button okBtn = (Button) findViewById(R.id.okBtn);
        final RadioGroup rangeRadio = (RadioGroup) findViewById(R.id.rangeRadio);
        final RadioGroup categoryRadio = (RadioGroup) findViewById(R.id.categoryRadio);

        okBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int rangeType=0;//0,1,2
                int categoryType=0;//0,1,2,3

                int rangeChecked = rangeRadio.getCheckedRadioButtonId();
                int categoryChecked = categoryRadio.getCheckedRadioButtonId();

                RadioButton rangeRadioBtn = (RadioButton) findViewById(rangeChecked);
                RadioButton categoryRadioBtn = (RadioButton) findViewById(categoryChecked);

                if(rangeRadioBtn != null){
                    if(rangeRadioBtn.getText().equals("전체 글 보기")){
                        rangeType = 0;
                    }else if(rangeRadioBtn.getText().equals("우리 동 사람이 올린 글만 보기")){
                        rangeType=1;
                    }else if(rangeRadioBtn.getText().equals("내 이웃이 올린 글만 보기")){
                        rangeType=2;
                    }
                }

                if(categoryRadioBtn !=null){
                    if(categoryRadioBtn.getText().equals("안전")){
                        categoryType = 1;
                    }else if(categoryRadioBtn.getText().equals("물건 공유")){
                        categoryType = 2;
                    }else if(categoryRadioBtn.getText().equals("정보 공유")){
                        categoryType = 3;
                    }
                }

                Intent intent = new Intent();
                intent.putExtra("rangeType", rangeType);
                intent.putExtra("categoryType", categoryType);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
