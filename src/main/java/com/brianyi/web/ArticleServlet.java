package com.brianyi.web;

import com.brianyi.domain.Result;
import com.brianyi.service.ArticleService;
import com.brianyi.service.impl.ArticleServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet {
    private ArticleService articleService = new ArticleServiceImpl();

    /*
     * 查询所有数据库文章
     * */
    public void findAll(HttpServletRequest req, HttpServletResponse resp) {
        Result all = articleService.findAll();
        writeValue(resp, all);
    }

    /*
     * 通过用户ID查询用户作品库
     * */
    public void findArticleToUser(HttpServletRequest req, HttpServletResponse resp) {
        String user_id = req.getParameter("user_id");
        Result userToArticle = articleService.findArticleToUser(user_id);
        writeValue(resp, userToArticle);
    }


    /*
     * 查询文章内容检测
     * */


    /**
     * 查询文章内容检测文章
     * @param request
     * @param response
     * @throws IOException
     */
    public void findArticleText(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String text = request.getParameter("text");
        String user_id = request.getParameter("user_id");
        Result result = articleService.findArticleText(text, user_id);
        System.out.println(result.toString());
        writeValue(response, result);
    }
}
