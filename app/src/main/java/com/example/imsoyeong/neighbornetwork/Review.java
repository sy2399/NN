package com.example.imsoyeong.neighbornetwork;

/**
 * Created by imsoyeong on 2017. 11. 26..
 */

public class Review {
    String reviewNo;
    String parentNo;
    String writerId;
    String contents;
    String write_date;
    String type;


    public Review() {
    }

    public Review(String reviewNo, String parentNo, String writerId, String contents, String write_date, String type) {
        this.reviewNo = reviewNo;
        this.parentNo = parentNo;
        this.writerId = writerId;
        this.contents = contents;
        this.write_date = write_date;
        this.type = type;
    }

    public Review(String parentNo, String writerId, String contents, String write_date,String type) {
        this.parentNo = parentNo;
        this.writerId = writerId;
        this.contents = contents;
        this.write_date = write_date;
        this.type = type;
    }

    public String getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(String reviewNo) {
        this.reviewNo = reviewNo;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewNo='" + reviewNo + '\'' +
                ", parentNo='" + parentNo + '\'' +
                ", writerId='" + writerId + '\'' +
                ", contents='" + contents + '\'' +
                ", write_date='" + write_date + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
