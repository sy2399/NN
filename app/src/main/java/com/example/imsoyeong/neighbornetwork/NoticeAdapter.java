package com.example.imsoyeong.neighbornetwork;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by imsoyeong on 2017. 11. 22..
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    Context context;
    ArrayList<Notice> noticeList;

    public NoticeAdapter(Context context, ArrayList<Notice> noticeList){
        this.context = context;
        this.noticeList = noticeList;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)

    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Notice noticeItem = noticeList.get(position);
        final NoticeDBHelper dbHelper = new NoticeDBHelper(context, "notice.db",null,1);;

        //Toast.makeText(context, timeLineItem.toString(), Toast.LENGTH_SHORT).show();
        try{
            holder.tv_title.setText(noticeItem.getTitle());
            holder.tv_content.setText(noticeItem.getContents());
            holder.tv_date.setText(noticeItem.getWriteDate());
            holder.tv_writer.setText(noticeItem.getWriterId());
            holder.tv_count.setText(String.valueOf(noticeItem.getHits()));

            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dbHelper.updateHit(Integer.parseInt(noticeItem.getNoticeNo()));
                    Intent intent = new Intent(context, NoticeDetailActivity.class);
                    intent.putExtra("noticeNo", noticeItem.getNoticeNo());
                    context.startActivity(intent);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return this.noticeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_date;
        TextView tv_content;
        TextView tv_writer;
        TextView tv_count;
        CardView cv;

        ImageView iv_image;
        RelativeLayout rl_image;

        public ViewHolder(View v){
            super(v);
            tv_title = (TextView) v.findViewById(R.id.tv_title);
            tv_date = (TextView) v.findViewById(R.id.tv_date);
            tv_content = (TextView) v.findViewById(R.id.tv_content);
            tv_writer = (TextView) v.findViewById(R.id.tv_writer);
            tv_count = (TextView) v.findViewById(R.id.tv_count);
            cv = (CardView) v.findViewById(R.id.cv);

            iv_image = (ImageView) v.findViewById(R.id.iv_image);
            rl_image = (RelativeLayout)v.findViewById(R.id.rl_image);
        }
    }
}
