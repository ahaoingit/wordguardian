package com.brianyi.dao;

import com.brianyi.domain.Official_Accounts;
import com.brianyi.domain.User;

import java.util.Date;
import java.sql.SQLException;

public interface UserDao {
    public int register(String moblePhone,String nickname);
    public User findPhone(String moblePhone);
    public User findUserToId(String user_id);
    /**
     * 根据userId查询微信号
     * @param userId
     * @return
     */
    Official_Accounts queryWeChatPublicAccount(String userId) throws SQLException;

    /**
     * 插入文章数据
     * @param articleId
     * @param accountsId
     * @param userId
     * @param title
     * @param now
     * @param content
     * @return
     */
    int insertArticle(String articleId, String accountsId, String userId, String title, Date now, String content) throws SQLException;
}
