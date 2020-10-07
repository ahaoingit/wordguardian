package com.brianyi.dao.impl;

import com.brianyi.dao.Official_AccountsDao;
import com.brianyi.domain.Official_Accounts;
import com.brianyi.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class Official_AccountsDaoImpl implements Official_AccountsDao{
    private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

    @Override
    public Official_Accounts findAuthorizeToUser(String user_id) {
        Official_Accounts official_accounts = null;
        try {
            String sql = "select * from official_accounts where user_id = ?";
            official_accounts = qr.query(sql,new BeanHandler<Official_Accounts>(Official_Accounts.class),user_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return official_accounts;

    }

    @Override
    public int UserAuthorize(String accounts_id, String user_id) {
        int update = 0;

        try {
            String sql = "update  official_accounts set user_id = ? where accounts_id = ?";
             update = qr.update(sql, user_id, accounts_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return update;
    }
}
