package com.brianyi.domain;


public class ShowArticle {
    private User user;
    private Article article;
    private Official_Accounts official_accounts;

    public Official_Accounts getOfficial_accounts() {
        return official_accounts;
    }

    public void setOfficial_accounts(Official_Accounts official_accounts) {
        this.official_accounts = official_accounts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
