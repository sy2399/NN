package com.example.imsoyeong.neighbornetwork;

import java.io.Serializable;

/**
 * Created by imsoyeong on 2017. 11. 5..
 */

public class Member implements Serializable{
    String id;
    String password;

    String name;
    String phone;
    String address_num;
    String address_detail;
    String imgPath;
    String admin;//0이면 관리자 1이면 일반 사용자
    String interests;
    String address_range;
    String profile_range;

    public Member() {}

    public Member(String id, String password, String name, String phone, String address_num, String address_detail, String imgPath, String admin, String interests, String address_range, String profile_range) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address_num = address_num;
        this.address_detail = address_detail;
        this.imgPath = imgPath;
        this.admin = admin;
        this.interests = interests;
        this.address_range = address_range;
        this.profile_range = profile_range;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress_num() {
        return address_num;
    }

    public void setAddress_num(String address_num) {
        this.address_num = address_num;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getAddress_range() {
        return address_range;
    }

    public void setAddress_range(String address_range) {
        this.address_range = address_range;
    }

    public String getProfile_range() {
        return profile_range;
    }

    public void setProfile_range(String profile_range) {
        this.profile_range = profile_range;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address_num='" + address_num + '\'' +
                ", address_detail='" + address_detail + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", admin='" + admin + '\'' +
                ", interests='" + interests + '\'' +
                ", address_range='" + address_range + '\'' +
                ", profile_range='" + profile_range + '\'' +
                '}';
    }
}

