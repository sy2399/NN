package com.example.imsoyeong.neighbornetwork;

/**
 * Created by imsoyeong on 2017. 11. 25..
 */

public class Notice {
    String noticeNo;
    String writerId;
    String title;
    String contents;
    String imgPath;
    String writeDate;
    int hits;

    public Notice() {
    }

    public Notice(String noticeNo, String writerId, String title, String contents, String imgPath, String writeDate, int hits) {
        this.noticeNo = noticeNo;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.imgPath = imgPath;
        this.writeDate = writeDate;
        this.hits = hits;
    }

    public Notice(String writerId, String title, String contents, String imgPath, String writeDate, int hits) {
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.imgPath = imgPath;
        this.writeDate = writeDate;
        this.hits = hits;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeNo='" + noticeNo + '\'' +
                ", writerId='" + writerId + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", writeDate='" + writeDate + '\'' +
                ", hits=" + hits +
                '}';
    }
}
