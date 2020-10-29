package com.example.imsoyeong.neighbornetwork;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by imsoyeong on 2017. 11. 5..
 */

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("create table NNmember(\n" +
                "   id text primary key,\n" +
                "    password text not null,\n" +
                "    name text not null,\n" +
                "    phone text not null,\n" +
                "    photo text,\n" +
                "    address_num text not null,\n" +
                "    address_detail text not null,\n" +
                "    admin text not null,\n" +
                "    interests text,\n" +
                "    address_range text default '0',\n" +
                "    profile_range text default '0' \n" +
                "    \n" +
                ");");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertMember(Member member) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        if(member.getImgPath() != null){

        }else {
            if((member.getInterests() == null) && (member.getAddress_range() == null) && (member.getProfile_range() == null)) {
                db.execSQL("INSERT INTO NNmember VALUES('" + member.getId() + "', '" + member.getPassword() + "', '" + member.getName() + "','"
                        + member.getPhone() + "',"+ null +",'"+ member.getAddress_num() + "','" + member.getAddress_detail()
                        + "','" + member.getAdmin()+"',"+ null +",'0','0');");
            }else{

            }

        }
        db.close();
    }


    public Member getMember(Member loginMember){
        SQLiteDatabase db = getReadableDatabase();
        Member member = new Member();
        String idResult = "";
        String pwResult = "";
        String sql = "Select id, password from NNmember where id='" + loginMember.getId() + "' and password = '"+loginMember.getPassword()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            idResult = cursor.getString(0);
            pwResult = cursor.getString(1);

        }

        member.setId(idResult);
        member.setPassword(pwResult);

        return member;

    }

    public boolean idCheck(String id){
        boolean flag = false;
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select count(*) from NNmember where id='"+id+"'";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext() && cursor.getInt(0)>0){

            flag = true;
        }
        return flag;
    }
    public Member findMemberById(String id){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from NNmember where id='"+id+"'";
        Cursor cursor = db.rawQuery(sql, null);
        Member loginMember = new Member();

        String mem_id = "";
        String pw = "";
        String name = "";
        String phone = "";
        String address_num = "";
        String address_detail = "";
        String imgPath = "";
        String admin = "";//0이면 관리자 1이면 일반 사용자
        String interests = "";
        String address_range = "";
        String progile_range = "";

        while(cursor.moveToNext()) {
            mem_id = cursor.getString(0);
            pw = cursor.getString(1);
            name = cursor.getString(2);
            phone = cursor.getString(3);
            address_num = cursor.getString(5);
            address_detail = cursor.getString(6);
            imgPath = cursor.getString(4);
            admin = cursor.getString(7);//0이면 관리자 1이면 일반 사용자
            interests = cursor.getString(8);
            address_range = cursor.getString(9);
            progile_range = cursor.getString(10);
        }
        loginMember = new Member(mem_id,pw,name,phone,address_num,address_detail,imgPath,admin,interests,address_range,progile_range);

        return loginMember;
    }

    public void updateMember(String id, String interest){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "update NNmember set interests = '"+interest+"' where id = '"+id+"'";
        db.execSQL(sql);
        db.close();

    }

    public void updateRange(String addressRange, String profileRange, String id){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "update NNmember set address_range = '"+addressRange+"',profile_range= '"+profileRange +"' where id = '"+id+"'";
        db.execSQL(sql);
        db.close();
    }


}
