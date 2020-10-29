package com.example.imsoyeong.neighbornetwork;

/**
 * Created by imsoyeong on 2017. 11. 5..
 */

public class TimeLine {
    String timelineNo;
    String writerId;
    String title;
    String contents;
    String imgPath;
    String range;
    String writeDate;
    String categoryNo;
    int hits;

    public TimeLine() {
    }

    public TimeLine(String writerId, String title, String contents, String imgPath, String range, String writeDate, String categoryNo, int hits) {
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.imgPath = imgPath;
        this.range = range;
        this.writeDate = writeDate;
        this.categoryNo = categoryNo;
        this.hits = hits;
    }

    public TimeLine(String timelineNo, String writerId, String title, String contents, String imgPath, String range, String writeDate, String categoryNo, int hits) {
        this.timelineNo = timelineNo;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.imgPath = imgPath;
        this.range = range;
        this.writeDate = writeDate;
        this.categoryNo = categoryNo;
        this.hits = hits;
    }

    public String getTimelineNo() {
        return timelineNo;
    }

    public void setTimelineNo(String timelineNo) {
        this.timelineNo = timelineNo;
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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "TimeLine{" +
                "timelineNo='" + timelineNo + '\'' +
                ", writerId='" + writerId + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", range='" + range + '\'' +
                ", writeDate='" + writeDate + '\'' +
                ", categoryNo='" + categoryNo + '\'' +
                ", hits=" + hits +
                '}';
    }
}
