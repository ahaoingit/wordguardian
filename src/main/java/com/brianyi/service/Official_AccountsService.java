package com.brianyi.service;

import com.brianyi.domain.Result;

public interface Official_AccountsService {
    public Result findAuthorizeToUser(String user_id);

    public Result UserAuthorize(String accounts_id,String user_id);
}
