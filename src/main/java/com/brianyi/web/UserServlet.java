package com.brianyi.web;

import com.alibaba.fastjson.JSON;
import com.brianyi.domain.Result;
import com.brianyi.domain.User;
import com.brianyi.service.UserService;
import com.brianyi.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    /*
    * 注册用户
    * */
    public void register(HttpServletRequest req , HttpServletResponse resp) {
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Result register = userService.register(user);

        writeValue(resp,register);
    }
    /*
    * 用户登录
    * */
    public void login(HttpServletRequest req , HttpServletResponse resp){
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Result register = userService.login(user);
        writeValue(resp,register);
    }

    /**
     * 获取验证码
     */
    public void getCheckNum(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String phone = request.getParameter("moble_phone");
        Result result = userService.getCheckNm(phone);
        writeValue(response,result);
    }


    public void justified(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException, NoSuchAlgorithmException {
        String userId = request.getParameter("userId");
        //文章题目
        String title = request.getParameter("title");
        System.out.println(title);
        //账号名称 通过手机号
        String phoneNum = request.getParameter("phoneNum");
        //文章内容
        String content = request.getParameter("content");
        System.out.println(content);
        UserService userService = new UserServiceImpl();
        Result justified = userService.justified(userId, title, phoneNum, content);
        response.getWriter().print(JSON.toJSONString(justified));
    }
}
