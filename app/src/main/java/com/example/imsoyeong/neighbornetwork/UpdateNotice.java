package com.example.imsoyeong.neighbornetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateNotice extends AppCompatActivity {

    EditText noticeTitle,noticeContents;
    Button updateBtn,deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notice);

        final NoticeDBHelper dbHelper = new NoticeDBHelper(getApplicationContext(), "notice.db",null,1);;

        Intent intent = getIntent();
        final String noticeNo = intent.getStringExtra("noticeNo");

        Notice notice = dbHelper.getNoticeByNo(noticeNo);

        noticeTitle = (EditText) findViewById(R.id.noticeTitle);
        noticeContents = (EditText) findViewById(R.id.noticeContents);

        noticeTitle.setText(notice.getTitle());
        noticeContents.setText(notice.getContents());

        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        updateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Notice newNotice = new Notice();
                newNotice.setNoticeNo(noticeNo);
                newNotice.setTitle(noticeTitle.getText().toString());
                newNotice.setContents(noticeContents.getText().toString());

                dbHelper.updateNotice(newNotice);

                Intent intent = new Intent(getApplicationContext(),NoticeDetailActivity.class);
                intent.putExtra("noticeNo", noticeNo);
                startActivity(intent);

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbHelper.deleteNotice(noticeNo);
                Intent intent = new Intent(getApplicationContext(),BarActivity.class);
                startActivity(intent);
            }
        });
    }
}
