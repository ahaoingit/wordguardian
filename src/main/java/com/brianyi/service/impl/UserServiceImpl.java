package com.brianyi.service.impl;

import com.alibaba.fastjson.JSON;
import com.brianyi.dao.UserDao;
import com.brianyi.dao.impl.UserDaoImpl;
import com.brianyi.domain.Official_Accounts;
import com.brianyi.domain.Result;
import com.brianyi.domain.ToBlockData;
import com.brianyi.domain.User;
import com.brianyi.service.UserService;
import com.brianyi.utils.BlockchainUtils;
import com.brianyi.utils.JedisUtils;
import com.brianyi.utils.SHA1Utils;
import com.brianyi.utils.SMSUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UserDao userDao =  new UserDaoImpl();
    private Jedis jedis = JedisUtils.getJedis();//获取redis连接
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Result register(User user) {

        User findPhone = userDao.findPhone(user.getMoble_phone());
        Result result = new Result();
        int register = 0;
        if (findPhone!=null){
            result.setCode(Result.FAILS);
            result.setMessage("手机号已存在，请重新输入!");
            return result;
        }

            String nickname = phoneToHex(user.getMoble_phone());
            nickname = "wordguardian_"+nickname;
            register = userDao.register(user.getMoble_phone(),nickname);
            result.setCode(Result.SUCCESS);
            result.setMessage("注册成功");

        return result;
    }

    /*
     * 手机号转十六进制，返回昵称
     * */
    public  String phoneToHex(String moblePhone){
        BigInteger bigInteger = new BigInteger(moblePhone);
        long l = bigInteger.longValue();
        String s = Long.toHexString(l);
        return s;
    }
    /*
    * 用户登录验证是否存在用户
    * */
    @Override
    public Result login(User user) {
        Result result = new Result();

        String checkNm = jedis.get(user.getMoble_phone());
        if (!checkNm.equals(user.getVerifyCode())){
            result.setCode(Result.FAILS);
            result.setMessage("验证码错误");
            return result;
        }

        User login = userDao.findPhone(user.getMoble_phone());

        if (login!=null){
            result.setCode(Result.SUCCESS);
            result.setMessage("登录成功");
            result.setObj(login);
        }else {
            result.setCode(Result.FAILS);
            result.setMessage("登录失败，手机号未绑定");
        }
        return result;
    }

    /*
    * 获取验证码
    * */

    @Override
    public Result getCheckNm(String phone) {

        String checkNum = checkNum();
        Result result = new Result();
        //正则验证
        if (!match(phone)){
            result.setMessage("手机号不合法");
            result.setCode(Result.FAILS);
            return result;
        }
        //调用第三方
        int i = SMSUtils.sendMsg(phone, checkNum);
        //手机号验证通过
        if (i == 200) {

            //NX key 不存在时设置    XX 是key存在时设置 EX 代表秒
            jedis.set(phone,checkNum,"NX","EX",300);
            result.setCode(Result.SUCCESS);
            result.setMessage("短信已发送");
        }else {
            result.setCode(Result.FAILS);
            result.setMessage("内部错误 错误码为:"+i);
        }
        return result;
    }
    /***.
     *@param :
     *@author ahao
     *@date 2020-10-05 16:25
     *@return {@link String}
     *随机验证码
     */
    private String checkNum () {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    /***.
     *@param phone:
     *@author ahao
     *@date 2020-10-05 16:25
     *@return {@link boolean}
     *正则匹配手机号
     */
    private boolean match(String phone) {
        return phone.matches("^(1[3-9])\\d{9}$");
    }


    /**
     * 文章 保存 并上链
     *
     * @param userId
     * @param title
     * @param phoneNum
     * @param content
     * @return
     */
    @Override
    public Result justified(String userId, String title, String phoneNum, String content) throws SQLException, IOException, NoSuchAlgorithmException {
        Result result = new Result();
        //UUID生成文章id
        UUID uuid = UUID.randomUUID();
        String articleId = uuid.toString().replace("-", "");
        //获取当前时间
        Date now = new Date();
        UserDao userDao   = new UserDaoImpl();
        //根据 userId 获取 公众号
        Official_Accounts officialAccounts =  userDao.queryWeChatPublicAccount(userId);
        //准备上链数据
        ToBlockData toBlockData = new ToBlockData();
        toBlockData.setArticleId(articleId);
        toBlockData.setTitle(title);
        toBlockData.setText(SHA1Utils.sha1(content));
        //返回结果
        Map<String, Object> stringObjectMap = BlockchainUtils.insertIntoChain(articleId, JSON.toJSONString(toBlockData));
        int code = (int)stringObjectMap.get("code");
        System.out.println(code);
        //验证
        if (code == 200) {
            //插入文章
            int i =  userDao.insertArticle(articleId,officialAccounts == null ?null:officialAccounts.getAccounts_id(),userId,title,now,content);
            result.setMessage("已成证");
            result.setCode(Result.SUCCESS);
        }else {
            logger.error("上链失败");
            result.setMessage("成证失败 ");
            result.setCode(Result.FAILS);
        }

        return result;
    }


}
