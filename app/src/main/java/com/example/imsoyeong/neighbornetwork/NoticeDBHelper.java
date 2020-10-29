package com.example.imsoyeong.neighbornetwork;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by imsoyeong on 2017. 11. 5..
 */

public class NoticeDBHelper extends SQLiteOpenHelper{
    public NoticeDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */





        db.execSQL("create table notice(\n" +
                "    noticeNo integer primary key autoincrement,\n" +
                "    writerId text not null,\n" +
                "    title text not null,\n" +
                "    contents text not null,\n" +
                "    imgPath text,\n" +
                "    write_date text not null,\n" +
                "    hits integer not null, \n"+
                "    foreign key(writerId) references member(id)\n" +
                "    \n" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertNotice(Notice n) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        if(n.getImgPath() != null){

        }else {
            String sql = "insert into notice values("+null +",'"+n.getWriterId()+"','"+ n.getTitle()+"','"+ n.getContents() + "',"
                    +null + ",datetime('NOW', 'LOCALTIME')," + n.getHits()
                    +")";

            db.execSQL(sql);


        }
        db.close();
    }

    public ArrayList<Notice> getNoticeList(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Notice> list = new ArrayList<>();
        String sql = "select noticeNo, writerId, title, contents, imgPath, strftime('%Y-%m-%d', write_date) ,hits from notice";
        Cursor cursor = db.rawQuery(sql, null);


        while(cursor.moveToNext()) {
            list.add(new Notice(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getShort(6)));

        }

        db.close();
        return list;
    }

    public Notice getNoticeByNo(String noticeNo){
        SQLiteDatabase db = getReadableDatabase();
        Notice noticeDetail;
        String sql = "select writerId, title, contents, imgPath, strftime('%Y-%m-%d', write_date) ,hits from notice where noticeNo="+noticeNo;
        Cursor cursor = db.rawQuery(sql,null);

        String writerId="";
        String title="";
        String contents="";
        String imgPath="";
        String writeDate="";
        int hits=0;

        while(cursor.moveToNext()) {
            writerId = cursor.getString(0);
            title = cursor.getString(1);
            contents = cursor.getString(2);
            imgPath = cursor.getString(3);
            writeDate = cursor.getString(4);
            hits = cursor.getInt(5);


        }
        noticeDetail = new Notice(writerId,title,contents,imgPath,writeDate,hits);
        db.close();
        return noticeDetail;
    }
    public void updateHit(int noticeNo){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "update notice set hits=hits+1 where noticeNo=" + noticeNo;
        try{
            db.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateNotice(Notice n){
        SQLiteDatabase db = getReadableDatabase();
        String sql ="update notice set title ='"+n.getTitle() + "',contents= '"+n.getContents()+"' where noticeNo="+n.getNoticeNo();

        try{
            db.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }

    public void deleteNotice(String noticeNo){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "delete from notice where noticeNo="+noticeNo;

        try{
            db.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }

}
