package com.example.imsoyeong.neighbornetwork;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by imsoyeong on 2017. 11. 26..
 */

public class ReviewAdapter extends BaseAdapter{

    ArrayList<Review> reviewArrayList = new ArrayList<>();
    SharedPreferences setting;
    SharedPreferences.Editor editor;



    public ReviewAdapter(ArrayList<Review> reviewArrayList){
        this.reviewArrayList = reviewArrayList;
    }

    @Override
    public int getCount() {
        return reviewArrayList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_review, parent, false);
        }
        try {
            final ReviewDBHelper dbnoticeHelper = new ReviewDBHelper(context, "reviewNotice.db",null,1);

            final ReviewDBHelper dbtimelineeHelper = new ReviewDBHelper(context, "reviewTimeline.db",null,1);

            setting = context.getSharedPreferences("setting", 0);
            final String loginInfo = setting.getString("loginInfo","");

            final EditText tv_contents_edit = (EditText) convertView.findViewById(R.id.tv_contents_edit);
            final RelativeLayout reviewLayout = (RelativeLayout) convertView.findViewById(R.id.reviewLayout);
            final Button reviewEditBtn = (Button) convertView.findViewById(R.id.reviewEditBtn);

            final TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_contents);
            TextView tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            TextView tv_writer = (TextView) convertView.findViewById(R.id.tv_writer);

            ImageButton updateBtn = (ImageButton) convertView.findViewById(R.id.updateBtn);
            ImageButton deleteBtn = (ImageButton) convertView.findViewById(R.id.deleteBtn);



            final Review rvItem = reviewArrayList.get(position);

            if(loginInfo.equals(rvItem.getWriterId())){
                updateBtn.setVisibility(View.VISIBLE);
                deleteBtn.setVisibility(View.VISIBLE);
            }
            tv_contents.setText(rvItem.getContents());
            tv_date.setText(rvItem.getWrite_date());
            tv_writer.setText(rvItem.getWriterId());

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(rvItem.getType().equals("0")){
                        dbtimelineeHelper.deleteTimelineReview(rvItem);

                        Intent intent = new Intent(context, TimeLineDetailActivity.class);
                        intent.putExtra("timeLineNo", rvItem.getParentNo());
                        context.startActivity(intent);

                    }else if(rvItem.getType().equals("1")){
                        dbnoticeHelper.deleteNoticeReview(rvItem);
                        Intent intent = new Intent(context, NoticeDetailActivity.class);
                        intent.putExtra("noticeNo", rvItem.getParentNo());
                        context.startActivity(intent);
                    }


                }
            });

            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reviewLayout.setVisibility(View.VISIBLE);
                    tv_contents_edit.setVisibility(View.VISIBLE);
                    reviewEditBtn.setVisibility(View.VISIBLE);

                    tv_contents.setVisibility(View.GONE);

                    tv_contents_edit.setText(tv_contents.getText().toString());

                }
            });

            reviewEditBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String editedContents = tv_contents_edit.getText().toString();
                    rvItem.setContents(editedContents);
                    if(rvItem.getType().equals("0")){
                        dbtimelineeHelper.updateTimeLineReview(rvItem);

                        Intent intent = new Intent(context, TimeLineDetailActivity.class);
                        intent.putExtra("timeLineNo", rvItem.getParentNo());
                        context.startActivity(intent);

                    }else if(rvItem.getType().equals("1")){
                        dbnoticeHelper.updateNoticeReview(rvItem);
                        Intent intent = new Intent(context, NoticeDetailActivity.class);
                        intent.putExtra("noticeNo", rvItem.getParentNo());
                        context.startActivity(intent);
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return reviewArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
