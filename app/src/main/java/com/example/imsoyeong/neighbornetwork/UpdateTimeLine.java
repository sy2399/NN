package com.example.imsoyeong.neighbornetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UpdateTimeLine extends AppCompatActivity {

    RadioGroup categoryRadio;
    RadioButton category1,category2,category3;

    RadioGroup rangeRadio;
    RadioButton range1,range2;

    EditText timelineTitle;
    EditText timelineContents;

    Button updateTimelineBtn;
    Button deleteTimelineBtn;

    String category;
    String timelineRange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_time_line);
        final TimeLineDBHelper dbHelper = new TimeLineDBHelper(getApplicationContext(), "timeline.db",null,1);;

        Intent intent = getIntent();
        final String timeLineNo = intent.getStringExtra("timeLineNo");

        TimeLine tl = dbHelper.findTimeLineByNo(timeLineNo);
        categoryRadio = (RadioGroup) findViewById(R.id.categoryRadio);
        category1 = (RadioButton) findViewById(R.id.categoryOption1);
        category2 = (RadioButton) findViewById(R.id.categoryOption2);
        category3 = (RadioButton) findViewById(R.id.categoryOption3);

        if(tl.getCategoryNo().equals("0")){
            category1.setChecked(true);
            category = "0";
        }else if(tl.getCategoryNo().equals("1")){
            category2.setChecked(true);
            category = "1";
        }else if(tl.getCategoryNo().equals("2")){
            category3.setChecked(true);
            category = "2";
        }

        rangeRadio = (RadioGroup) findViewById(R.id.timelineRadio);
        range1 = (RadioButton) findViewById(R.id.timelineRangeOption1);
        range2 = (RadioButton) findViewById(R.id.timelineRangeOption2);

        if(tl.getRange().equals("0")){
            range1.setChecked(true);
            timelineRange = "0";
        }else if(tl.getRange().equals("1")){
            range2.setChecked(true);
            timelineRange = "1";
        }

        timelineTitle = (EditText) findViewById(R.id.timelineTitle);
        timelineContents = (EditText) findViewById(R.id.timelineContents);
        timelineTitle.setText(tl.getTitle());
        timelineContents.setText(tl.getContents());

        updateTimelineBtn = (Button) findViewById(R.id.updateTimeLineBtn);
        deleteTimelineBtn = (Button) findViewById(R.id.deleteTimeLineBtn);

        categoryRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.categoryOption1:
                        category = "0";
                        break;
                    case R.id.categoryOption2:
                        category = "1";
                        break;
                    case R.id.categoryOption3:
                        category="2";
                        break;
                }
            }
        });

        rangeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.timelineRangeOption1:
                        timelineRange = "0";
                        break;
                    case R.id.timelineRangeOption2:
                        timelineRange = "1";
                        break;

                }
            }
        });
        updateTimelineBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TimeLine updatetl = new TimeLine();
                updatetl.setTimelineNo(timeLineNo);
                updatetl.setTitle(timelineTitle.getText().toString());
                updatetl.setContents(timelineContents.getText().toString());
                updatetl.setCategoryNo(category);
                updatetl.setRange(timelineRange);

                dbHelper.updateTimeline(updatetl);

                Intent intent = new Intent(getApplicationContext(),TimeLineDetailActivity.class);
                intent.putExtra("timeLineNo", timeLineNo);
                startActivity(intent);
            }
        });

        deleteTimelineBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbHelper.deleteTimeline(timeLineNo);

                Intent intent = new Intent(getApplicationContext(),BarActivity.class);
                startActivity(intent);
            }
        });


    }
}
