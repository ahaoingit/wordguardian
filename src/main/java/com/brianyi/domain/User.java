package com.brianyi.domain;

public class User {
    private String username;
    private String moble_phone;
    private String nickname;
    private String verifyCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMoble_phone() {
        return moble_phone;
    }

    public void setMoble_phone(String moble_phone) {
        this.moble_phone = moble_phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", moblePhone='" + moble_phone+ '\'' +
                ", nickname='" + nickname + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
