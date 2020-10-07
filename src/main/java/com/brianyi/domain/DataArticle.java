package com.brianyi.domain;

import java.sql.Time;
import java.util.Date;

public class DataArticle {
    private String title;
    private Date time;
    private String text;
    private String wechat;
    private String wechatID;
    private  Double similarity;
    public DataArticle(){
        super();
    }
    public DataArticle(String title,Time time,String text,String wechat,String wechatID,Double similarity) {
        this.title = title;
        this.text = text;
        this.time = time;
        this.title = title;
        this.wechatID = wechatID;
        this.similarity = similarity;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    public String getWechatID() {
        return wechatID;
    }

    public void setWechatID(String wechatID) {
        this.wechatID = wechatID;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DataArticle{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", text='" + text + '\'' +
                ", wechat='" + wechat + '\'' +
                ", wechatID='" + wechatID + '\'' +
                ", similarity=" + similarity +
                '}';
    }
}
