package com.brianyi.domain;

import java.sql.Time;

public class Official_Accounts {
    private String accounts_id;
    private String user_id;
    private Time creation_time;
    private String introduction;
    private String name;

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

    public Time getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Time creation_time) {
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
}
