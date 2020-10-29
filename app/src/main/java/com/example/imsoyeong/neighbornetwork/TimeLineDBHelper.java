package com.example.imsoyeong.neighbornetwork;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by imsoyeong on 2017. 11. 5..
 */

public class TimeLineDBHelper extends SQLiteOpenHelper{
    public TimeLineDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */


        db.execSQL("create table category(\n" +
                "    categoryNo text primary key,\n" +
                "    categoryName text not null \n" +
                ");");

        db.execSQL("INSERT INTO category VALUES('0', '안전');");
        db.execSQL("INSERT INTO category VALUES('1', '물건 공유');");
        db.execSQL("INSERT INTO category VALUES('2', '정보 공유');");



        db.execSQL("create table timeline(\n" +
                "    timeLineNo integer primary key autoincrement,\n" +
                "    writerId text not null,\n" +
                "    title text not null,\n" +
                "    contents text not null,\n" +
                "    imgPath text,\n" +
                "    range text not null,\n" +
                "    categoryNo text not null,\n" +
                "    write_date text not null,\n" +
                "    hits integer not null, \n"+
                "    foreign key(writerId) references member(id),\n" +
                "    foreign key(categoryNo) references category(categoryNo)\n" +
                "    \n" +
                ");");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertTimeLine(TimeLine tl) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        if(tl.getImgPath() != null){

        }else {

            db.execSQL("INSERT INTO timeline VALUES(" + null + ", '" + tl.getWriterId() + "', '" +tl.getTitle() + "','"
                    + tl.getContents() + "',"+ null +",'"+ tl.getRange() + "','" +tl.getCategoryNo() +"',datetime('NOW', 'LOCALTIME'),"+tl.getHits()
                    + ");");


        }
        db.close();
    }

    public ArrayList<TimeLine> getTimelineList(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TimeLine> list = new ArrayList<TimeLine>();

        String sql = "select timeLineNo, writerId, title, contents, imgPath, range, strftime('%Y-%m-%d', write_date) ,categoryNo,hits from timeline";
        Cursor cursor = db.rawQuery(sql, null);


        while(cursor.moveToNext()) {
            list.add(new TimeLine(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8)));

        }

        db.close();
        return list;
    }
    public ArrayList<TimeLine> getTimeLineListByCategory(String categoryNo){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TimeLine> list = new ArrayList<TimeLine>();

        String sql = "select timeLineNo, writerId, title, contents, imgPath, range, strftime('%Y-%m-%d', write_date) ,categoryNo,hits from timeline where categoryNo="+categoryNo;
        Cursor cursor = db.rawQuery(sql, null);


        while(cursor.moveToNext()) {
            list.add(new TimeLine(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8)));

        }

        db.close();
        return list;
    }

    public ArrayList<TimeLine> getTimelineListByKeyword(String keyword){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TimeLine> list = new ArrayList<TimeLine>();

        String sql = "select timeLineNo, writerId, title, contents, imgPath, range, strftime('%Y-%m-%d', write_date) ,categoryNo,hits from timeline where title like '%"+keyword+"%'";
        Cursor cursor = db.rawQuery(sql, null);


        while(cursor.moveToNext()) {
            list.add(new TimeLine(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8)));

        }

        db.close();
        return list;

    }
    public TimeLine findTimeLineByNo(String timeLineNo){
        SQLiteDatabase db = getReadableDatabase();
        TimeLine timeLineDetail;

        String sql = "select writerId, title, contents, imgPath, range, strftime('%Y-%m-%d', write_date) ,categoryNo,hits from timeline where timeLineNo = " + timeLineNo;
        Cursor cursor = db.rawQuery(sql, null);


        String writerId="";
        String title="";
        String contents="";
        String imgPath="";
        String range="";
        String writeDate="";
        String categoryNo="";
        int hits=0;

        while(cursor.moveToNext()) {
            writerId = cursor.getString(0);
            title = cursor.getString(1);
            contents = cursor.getString(2);
            imgPath = cursor.getString(3);
            range = cursor.getString(4);
            writeDate = cursor.getString(5);
            categoryNo = cursor.getString(6);
            hits = cursor.getInt(7);


        }
        timeLineDetail = new TimeLine(writerId,title,contents,imgPath,range,writeDate,categoryNo,hits);
        db.close();
        return timeLineDetail;
    }
    public void updateHit(int timeLineNo){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "update timeline set hits=hits+1 where timelineNo=" + timeLineNo;
        try{
            db.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }


    public void updateTimeline(TimeLine tl){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "update timeline set title = '"+ tl.getTitle()+"', contents= '"+ tl.getContents()+"',categoryNo= '"+tl.getCategoryNo()+"', range= '"+ tl.getRange() +"'"
                +" where timelineNo=" + tl.getTimelineNo();

        try{
            db.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }

    public void deleteTimeline(String timelineNo){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "delete from timeline where timelineNo="+timelineNo;

        try{
            db.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }

}
