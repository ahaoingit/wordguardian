package com.brianyi.service;

import com.brianyi.domain.Article;
import com.brianyi.domain.Result;

import java.util.List;

public interface ArticleService {
    public Result findAll();
    public Result findArticleToUser(String user_id);

    public Result findArticleText(String text,String user_id);
}
