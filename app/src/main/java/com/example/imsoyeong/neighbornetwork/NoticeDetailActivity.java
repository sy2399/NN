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

import java.util.ArrayList;

/**
 * Created by imsoyeong on 2017. 11. 23..
 */

public class NoticeDetailActivity extends AppCompatActivity {
    Notice noticeItem;

    ImageView profile_img, iv_image;
    TextView tv_title, tv_writer, tv_date,tv_content;

    FloatingActionButton writeReviewBtn;
    FloatingActionButton updateContentBtn;

    ListView reviewView;

    EditText reviewEditText;
    ArrayList<Review> arrayList;

    RelativeLayout reviewLayout;
    ReviewAdapter reviewAdapter;

    Button registerReview;

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_detail_layout);

        final NoticeDBHelper dbHelper = new NoticeDBHelper(getApplicationContext(), "notice.db",null,1);
        final ReviewDBHelper reviewdbHelper = new ReviewDBHelper(getApplicationContext(), "reviewNotice.db", null, 1);
        final DBHelper memberdbHelper = new DBHelper(getApplicationContext(), "NNmember.db",null,1);

        setting = getSharedPreferences("setting", 0);
        final String loginId = setting.getString("loginInfo","");
        Member loginMember = memberdbHelper.findMemberById(loginId);


        Intent intent = getIntent();
        final String noticeNo = intent.getStringExtra("noticeNo").toString();
        noticeItem = dbHelper.getNoticeByNo(noticeNo);
        updateContentBtn = (FloatingActionButton) findViewById(R.id.updateContentBtn);

        if(loginId.equals(noticeItem.getWriterId())){
            updateContentBtn.setVisibility(View.VISIBLE);
        }
        profile_img = (ImageView) findViewById(R.id.profile_img);
        iv_image = (ImageView)findViewById(R.id.iv_image);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_writer = (TextView) findViewById(R.id.tv_writer);
        tv_date = (TextView)findViewById(R.id.tv_date);
        tv_content = (TextView) findViewById(R.id.tv_content);

        if(noticeItem.getImgPath() == null){
                Drawable drawable = getResources().getDrawable(R.drawable.no_img);
                iv_image.setImageDrawable(drawable);
        }

        tv_title.setText(noticeItem.getTitle());
        tv_writer.setText(noticeItem.getWriterId());
        tv_date.setText(noticeItem.getWriteDate());
        tv_content.setText(noticeItem.getContents());


        arrayList = reviewdbHelper.getReviewByNoticeNo(noticeNo);
        reviewView = (ListView) findViewById(R.id.reviewlistview);

        reviewAdapter = new ReviewAdapter(arrayList);
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

                rv.setParentNo(noticeNo);
                rv.setWriterId(noticeItem.getWriterId());
                rv.setContents(review);
                rv.setType("1");

                reviewdbHelper.insertNoticeReview(rv);
                reviewLayout.setVisibility(View.INVISIBLE);

                Intent intent = new Intent(getApplicationContext(), NoticeDetailActivity.class);
                intent.putExtra("noticeNo", noticeNo);
                startActivity(intent);

            }
        });
        updateContentBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateNotice.class);
                intent.putExtra("noticeNo", noticeNo);
                startActivity(intent);
            }
        });

    }


}
