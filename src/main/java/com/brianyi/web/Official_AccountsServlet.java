package com.brianyi.web;

import com.brianyi.domain.Result;
import com.brianyi.service.Official_AccountsService;
import com.brianyi.service.impl.Official_AccountsServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/account/*")
public class Official_AccountsServlet extends BaseServlet{
    private Official_AccountsService accountsService = new Official_AccountsServiceImpl();

    /*
    * 查看该用户是否已授权
    * */
    public void findAuthorizeToUser(HttpServletRequest req, HttpServletResponse resp){
        String user_id = req.getParameter("user_id");
        Result authorizeToUser = accountsService.findAuthorizeToUser(user_id);
        writeValue(resp,authorizeToUser);
    }

    /*
    * 请求授权
    * */
    public void UserAuthorize(HttpServletRequest req, HttpServletResponse resp){
        String user_id = req.getParameter("user_id");
        String accounts_id = req.getParameter("accounts_id");
        Result authorizeToUser = accountsService.UserAuthorize(accounts_id,user_id);
        writeValue(resp,authorizeToUser);
    }
}
