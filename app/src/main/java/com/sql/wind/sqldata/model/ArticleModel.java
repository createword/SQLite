package com.sql.wind.sqldata.model;


import java.io.Serializable;

/**
 * Created by WINTER on 2016/12/16.
 */

public class ArticleModel implements Serializable {
    public int pid;
    public String userName;

    @Override
    public String toString() {
        return "ArticleModel{" +
                "pid=" + pid +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String title;
    public String content;
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
