package com.brianyi.dao;

import com.brianyi.domain.Official_Accounts;

public interface Official_AccountsDao {
    public Official_Accounts findAuthorizeToUser(String user_id);

    public int UserAuthorize(String accounts_id,String user_id);
}
