package com.brianyi.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * (Article)实体类
 *
 * @author makejava
 * @since 2020-10-06 15:16:53
 */
public class Article implements Serializable {
    private static final long serialVersionUID = 733923567375113595L;
    /**
     * 文章id
     */
    private String articleId;
    /**
     * 公众号id
     */
    private String accountsId;
    /**
     * 用户id
     */
    private String user_Id;
    /**
     * 标题
     */
    private String title;
    /**
     * 发布时间
     */
    private Date time;
    /**
     * 文章内容
     */
    private String text;
    /**
     * 成证标志
     */
    private Integer flag;


    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(String accountsId) {
        this.accountsId = accountsId;
    }

    public String getUser_id() {
        return user_Id;
    }

    public void setUser_id(String userId) {
        this.user_Id = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

}