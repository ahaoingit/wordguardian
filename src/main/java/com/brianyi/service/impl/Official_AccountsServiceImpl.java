package com.brianyi.service.impl;

import com.brianyi.dao.Official_AccountsDao;
import com.brianyi.dao.impl.Official_AccountsDaoImpl;
import com.brianyi.domain.Official_Accounts;
import com.brianyi.domain.Result;
import com.brianyi.domain.ShowArticle;
import com.brianyi.service.Official_AccountsService;

public class Official_AccountsServiceImpl implements Official_AccountsService {
    private Official_AccountsDao accountsDao = new Official_AccountsDaoImpl();
    @Override
    public Result findAuthorizeToUser(String user_id) {
        Official_Accounts authorizeToUser = accountsDao.findAuthorizeToUser(user_id);
        Result result = new Result();
        if (authorizeToUser==null){
            result.setCode(Result.FAILS);
            result.setMessage("未授权");
            return result;
        }
        ShowArticle showArticle = new ShowArticle();
        showArticle.setOfficial_accounts(authorizeToUser);

        result.setCode(Result.SUCCESS);
        result.setMessage("已授权");
        result.setObj(showArticle);
        return result;
    }

    @Override
    public Result UserAuthorize(String accounts_id, String user_id) {
        int i = accountsDao.UserAuthorize(accounts_id, user_id);
        Result result = new Result();

        if (i==0){
            result.setCode(Result.FAILS);
            result.setMessage("授权失败");
            return result;
        }

        result.setCode(Result.SUCCESS);
        result.setMessage("授权成功");
        return result;

    }
}
