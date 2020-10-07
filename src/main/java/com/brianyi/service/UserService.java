package com.brianyi.service;

import com.brianyi.domain.Result;
import com.brianyi.domain.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface UserService {
    public Result register(User user);
    public Result login(User user);
    public Result getCheckNm(String phoneNum);


    /**
     * 文章 保存 并上链
     * @param userId
     * @param title
     * @param phoneNum
     * @param content
     * @return
     */
    Result justified(String userId, String title, String phoneNum, String content) throws SQLException, IOException, NoSuchAlgorithmException;
}
