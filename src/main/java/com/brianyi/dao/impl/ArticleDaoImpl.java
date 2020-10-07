package com.brianyi.dao.impl;

import com.brianyi.dao.ArticleDao;
import com.brianyi.domain.Article;
import com.brianyi.domain.Official_Accounts;
import com.brianyi.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao{
    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    @Override
    public List<Article> findAll() {
        String sql;
        List<Article> articles;
        try {
            sql = " select * from article";
            articles = this.queryRunner.query(sql, new BeanListHandler<Article>(Article.class));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return articles;
    }

    @Override
    public List<Article> findArticleToUser(String user_id) {
        String sql;
        List<Article> articles;
        try {
            sql = " select * from article where user_id = ?";
            articles = this.queryRunner.query(sql, new BeanListHandler<Article>(Article.class),user_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return articles;
    }

    /*
    * 检测
    * */

    @Override
    public List<Article> findArticleText(String user_id) {
        String sql;
        List<Article> articles = null;
        try{
            sql = "select * from  article where user_id=?";
            articles = this.queryRunner.query(sql, new BeanListHandler<Article>(Article.class),user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }
    public Official_Accounts findAccountsText(String user_id){
        String sql;
        Official_Accounts official_accounts = null;
        try{
            sql = "select * from  official_accounts where user_id=?";
            official_accounts = this.queryRunner.query(sql, new BeanHandler<Official_Accounts>(Official_Accounts.class),user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return official_accounts;
    }
}
