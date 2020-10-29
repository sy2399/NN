package com.example.imsoyeong.neighbornetwork;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder>  {
    Context context;
    ArrayList<TimeLine> timelineList;
    Drawable drawable;

    TimeLineDBHelper dbHelper;
    private int type;


    public TimeLineAdapter(Context context, ArrayList<TimeLine> timelineList){

        this.dbHelper = new TimeLineDBHelper(context, "timeline.db",null,1);
        this.context = context;
        this.timelineList = timelineList;

    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)

    @Override
    public TimeLineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tilmeline, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final TimeLine timeLineItem = timelineList.get(position);
        //Toast.makeText(context, timeLineItem.toString(), Toast.LENGTH_SHORT).show();
        try{

            if(timeLineItem.getCategoryNo().equals("0")){
               drawable = context.getResources().getDrawable(R.drawable.category1);
                holder.category_img.setImageDrawable(drawable);
            }else if(timeLineItem.getCategoryNo().equals("1")){
                drawable = context.getResources().getDrawable(R.drawable.category2);
                holder.category_img.setImageDrawable(drawable);
            }else{
                drawable = context.getResources().getDrawable(R.drawable.category3);
                holder.category_img.setImageDrawable(drawable);
            }

            holder.tv_title.setText(timeLineItem.getTitle());
            holder.tv_content.setText(timeLineItem.getContents());
            holder.tv_date.setText(timeLineItem.getWriteDate());
            holder.tv_writer.setText(timeLineItem.getWriterId());
            holder.tv_count.setText(String.valueOf(timeLineItem.getHits()));

            holder.cv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    //Toast.makeText(context, "이동", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,TimeLineDetailActivity.class);
                    intent.putExtra("timeLineNo", timeLineItem.getTimelineNo());

                    dbHelper.updateHit(Integer.parseInt(timeLineItem.getTimelineNo()));
                    context.startActivity(intent);

                }
            });

        }catch (Exception e){
            e.printStackTrace();

        }

    }



    @Override
    public int getItemCount() {

         return this.timelineList.size();


    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title;
        TextView tv_date;
        TextView tv_content;
        TextView tv_writer;
        TextView tv_count;
        CardView cv;
        ImageView category_img;
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
            category_img = (ImageView) v.findViewById(R.id.category_img);
            iv_image = (ImageView) v.findViewById(R.id.iv_image);
            rl_image = (RelativeLayout)v.findViewById(R.id.rl_image);
        }
    }


//    public void filter(String charText){
//        charText = charText.toLowerCase(Locale.getDefault());
//        filteredList.clear();
//        if(charText.length() ==0){
//            filteredList.addAll(timelineList);
//        }else{
//            for(TimeLine timeline:timelineList){
//                filteredList.add(timeline);
//            }
//        }
//        notifyDataSetChanged();
//    }
}
