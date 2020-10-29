package com.example.imsoyeong.neighbornetwork;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by imsoyeong on 2017. 11. 5..
 */

public class ReviewDBHelper extends SQLiteOpenHelper{
    public ReviewDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성

        db.execSQL("create table reviewTimeline(\n" +
                "    reviewNo integer primary key autoincrement,\n" +
                "    parentNo integer not null,\n" +
                "    writerId text not null,\n" +
                "    contents text,\n" +
                "    write_date text not null,\n" +
                "    foreign key(writerId) references member(id),\n" +
                "    foreign key(parentNo) references timeline(timelineNo)\n" +
                "    \n" +
                ");");
        db.execSQL("create table reviewNotice(\n" +
                "    reviewNo integer primary key autoincrement,\n" +
                "    parentNo integer not null,\n" +
                "    writerId text not null,\n" +
                "    contents text,\n" +
                "    write_date text not null,\n" +
                "    foreign key(writerId) references member(id),\n" +
                "    foreign key(parentNo) references notice(noticeNo)\n" +
                "    \n" +
                ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertReview(Review rv){
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가

        db.execSQL("INSERT INTO reviewTimeline VALUES(" + null + ", '" + rv.getParentNo() + "', '" +rv.getWriterId() + "','"
                    + rv.getContents() + "',"+"datetime('NOW', 'LOCALTIME')"
                    + ");");



        db.close();
    }

    public ArrayList<Review> getReviewByTimelineNo(String timelineNo){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Review> list = new ArrayList<Review>();

        String sql = "select reviewNo, writerId, contents, strftime('%Y-%m-%d', write_date), parentNo from reviewTimeline where parentNo="+timelineNo;
        try {
            Cursor cursor = db.rawQuery(sql, null);


            while (cursor.moveToNext()) {
                list.add(new Review(cursor.getString(0), cursor.getString(4),cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), "0"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return list;
    }
    public void insertNoticeReview(Review rv){
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가

        db.execSQL("INSERT INTO reviewNotice VALUES(" + null + ", '" + rv.getParentNo() + "', '" +rv.getWriterId() + "','"
                + rv.getContents() + "',"+"datetime('NOW', 'LOCALTIME')"
                + ");");



        db.close();
    }

    public ArrayList<Review> getReviewByNoticeNo(String noticeNo){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Review> list = new ArrayList<Review>();

        String sql = "select reviewNo, writerId, contents, strftime('%Y-%m-%d', write_date),parentNo from reviewNotice where parentNo="+noticeNo;
        try {
            Cursor cursor = db.rawQuery(sql, null);


            while (cursor.moveToNext()) {
                list.add(new Review(cursor.getString(0),cursor.getString(4), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), "1"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return list;
    }

    public void deleteTimelineReview(Review rv){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "delete from reviewTimeline where reviewNo="+rv.getReviewNo() + " and parentNo=" + rv.getParentNo();

        try{
            db.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }
    public void deleteNoticeReview(Review rv){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "delete from reviewNotice where reviewNo="+rv.getReviewNo() + " and parentNo="+rv.getParentNo();

        try{
            db.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }

    public void updateTimeLineReview(Review rv){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "update reviewTimeline set contents = '"+ rv.getContents() + "' where reviewNo="+rv.getReviewNo() + " and parentNo="+rv.getParentNo();
        db.execSQL(sql);
        db.close();
    }

    public void updateNoticeReview(Review rv){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "update reviewNotice set contents = '"+ rv.getContents() + "' where reviewNo="+rv.getReviewNo() + " and parentNo="+rv.getParentNo();
        db.execSQL(sql);

        db.close();
    }

}
