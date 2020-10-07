package com.brianyi.dao;

import com.brianyi.domain.Article;
import com.brianyi.domain.Official_Accounts;

import java.util.List;

public interface ArticleDao {
    public List<Article> findAll();
    public List<Article> findArticleToUser(String user_id);
    List<Article> findArticleText(String user_id);
    Official_Accounts findAccountsText(String user_id);
}
