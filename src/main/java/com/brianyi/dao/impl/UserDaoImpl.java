package com.brianyi.dao.impl;

import com.brianyi.dao.UserDao;
import com.brianyi.domain.Official_Accounts;
import com.brianyi.domain.User;
import com.brianyi.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.util.Date;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        /*
        * 注册用户，插入手机号
        * */
        @Override
        public int register(String moblePhone,String nickname) {
                int update = 0;
                String sql = "insert into user(user_id,moble_phone,nickname) values(REPLACE(UUID(), '-', ''),?,?)";
                try {
                         update = qr.update(sql,  moblePhone,nickname);
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return update;
        }
        /*
        * 查询手机号是否存在
        * */
        @Override
        public User findPhone(String moblePhone) {
                User user = null;
                try {
                        String sql = "select * from user where moble_phone = ? ";

                        user= qr.query(sql, new BeanHandler<User>(User.class), moblePhone);
                        System.out.println(user);
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
                return user;
        }
        /*
        * 通过用户ID查询用户
        * */
        @Override
        public User findUserToId(String user_id){
                User user = null;
                String sql= "select * from user where user_id = ?";
                try {
                        user = qr.query(sql, new BeanHandler<User>(User.class),user_id);
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
                return  user;
        }

        /**
         * 根据userId查询微信号
         *
         * @param userId
         * @return
         */
        @Override
        public Official_Accounts queryWeChatPublicAccount(String userId) throws SQLException {
                QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
                String sql = "select * from official_accounts where user_id=?";
                Official_Accounts query = qr.query(sql, new BeanHandler<>(Official_Accounts.class),userId);
                return query;
        }

        /**
         * 插入文章数据
         *
         * @param articleId
         * @param accountsId
         * @param userId
         * @param title
         * @param now
         * @param content
         * @return
         */
        @Override
        public int insertArticle(String articleId, String accountsId, String userId, String title, Date now, String content) throws SQLException {
                QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
                String sql = "insert into article values(?,?,?,?,?,?,?)";
                int update = qr.update(sql, articleId, accountsId, userId, title, now, content,1);
                return update;
        }

//        @Test
//        public void  insertTest() throws SQLException {
//                //insertArticle("ahao","ahao","ahoa","fdsafd",new Date(),"fdsakjfhksdja");
//                OfficialAccounts officialAccounts = queryWeChatPublicAccount("381ebd7806a311eb8a9c00163f005d9e");
//                System.out.println(officialAccounts);
//
//        }

}
