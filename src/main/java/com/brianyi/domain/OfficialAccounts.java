package com.brianyi.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * (OfficialAccounts)实体类
 *
 * @author makejava
 * @since 2020-10-05 09:42:09
 */
public class OfficialAccounts implements Serializable {
    private static final long serialVersionUID = 718806100033695864L;
    /**
     * 公众号id
     */
    private String accounts_id;
    /**
     * 用户id
     */
    private String user_id;
    /**
     * 创建时间
     */
    private Date creation_time;
    /**
     * 简介
     */
    private String introduction;
    /**
     * 公众号名称
     */
    private String name;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAccounts_id() {
        return accounts_id;
    }

    public void setAccounts_id(String accounts_id) {
        this.accounts_id = accounts_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OfficialAccounts{" +
                "accounts_id='" + accounts_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", creation_time=" + creation_time +
                ", introduction='" + introduction + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}