package com.example.imsoyeong.neighbornetwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by imsoyeong on 2017. 11. 23..
 */

public class TimeLineDetailActivity extends AppCompatActivity {
    TimeLine timeLineItem;

    ImageView profile_img, iv_image;
    TextView tv_title, tv_writer, tv_date,tv_content;

    FloatingActionButton writeReviewBtn;
    FloatingActionButton updateContentBtn;

    ListView reviewView;

    EditText reviewEditText;
    ArrayList<Review> reviewArrayList = new ArrayList<>();
    ReviewAdapter reviewAdapter;
    Button registerReview;

    RelativeLayout reviewLayout;


    SharedPreferences setting;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_detail_layout);

        final TimeLineDBHelper dbHelper = new TimeLineDBHelper(getApplicationContext(), "timeline.db", null, 1);
        final ReviewDBHelper reviewdbHelper = new ReviewDBHelper(getApplicationContext(), "reviewTimeline.db", null, 1);
        final DBHelper memberdbHelper = new DBHelper(getApplicationContext(), "NNmember.db",null,1);

        setting = getSharedPreferences("setting", 0);
        final String loginId = setting.getString("loginInfo","");
        Member loginMember = memberdbHelper.findMemberById(loginId);


        Intent intent = getIntent();
        final String timeLineNo = intent.getStringExtra("timeLineNo");

        updateContentBtn = (FloatingActionButton) findViewById(R.id.updateContentBtn);

        if(loginId.equals(dbHelper.findTimeLineByNo(timeLineNo).getWriterId())){
            updateContentBtn.setVisibility(View.VISIBLE);
        }



        timeLineItem = dbHelper.findTimeLineByNo(timeLineNo);




        profile_img = (ImageView) findViewById(R.id.profile_img);
        iv_image = (ImageView) findViewById(R.id.iv_image);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_writer = (TextView) findViewById(R.id.tv_writer);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_content = (TextView) findViewById(R.id.tv_content);

        if (timeLineItem.getImgPath() == null) {
            Drawable drawable = getResources().getDrawable(R.drawable.no_img);
            iv_image.setImageDrawable(drawable);
        }

            tv_title.setText(timeLineItem.getTitle());
            tv_writer.setText(timeLineItem.getWriterId());
            tv_date.setText(timeLineItem.getWriteDate());
            tv_content.setText(timeLineItem.getContents());


            reviewArrayList = reviewdbHelper.getReviewByTimelineNo(timeLineNo);
            reviewView = (ListView) findViewById(R.id.reviewlistview);

            reviewAdapter = new ReviewAdapter(reviewArrayList);
            reviewView.setAdapter(reviewAdapter);

            writeReviewBtn = (FloatingActionButton) findViewById(R.id.writeReviewBtn);
            registerReview = (Button)findViewById(R.id.registerReview);

            reviewLayout = (RelativeLayout) findViewById(R.id.reviewLayout);

            writeReviewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reviewLayout.setVisibility(View.VISIBLE);
                    writeReviewBtn.setVisibility(View.INVISIBLE);
                }
            });


            registerReview.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Review rv = new Review();

                    reviewEditText = (EditText) findViewById(R.id.reviewEditText);
                    String review = reviewEditText.getText().toString();

                    rv.setParentNo(timeLineNo);
                    rv.setWriterId(timeLineItem.getWriterId());
                    rv.setContents(review);
                    rv.setType("0");

                    reviewdbHelper.insertReview(rv);
                    Toast.makeText(TimeLineDetailActivity.this, "댓글등록성공", Toast.LENGTH_SHORT).show();
                    reviewLayout.setVisibility(View.INVISIBLE);

                    Intent intent = new Intent(getApplicationContext(), TimeLineDetailActivity.class);
                    intent.putExtra("timeLineNo", timeLineNo);
                    startActivity(intent);

                }
            });

        updateContentBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateTimeLine.class);
                intent.putExtra("timeLineNo", timeLineNo);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
