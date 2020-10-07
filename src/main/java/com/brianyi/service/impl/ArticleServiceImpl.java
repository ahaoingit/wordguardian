package com.brianyi.service.impl;

import com.brianyi.dao.ArticleDao;
import com.brianyi.dao.UserDao;
import com.brianyi.dao.impl.ArticleDaoImpl;
import com.brianyi.dao.impl.UserDaoImpl;
import com.brianyi.domain.*;
import com.brianyi.service.ArticleService;
import com.brianyi.utils.Similarity;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ArticleServiceImpl implements ArticleService {
    private Logger logger = LoggerFactory.getLogger(Article.class);
    private ArticleDao articleDao = new ArticleDaoImpl();
    private ShowArticle showArticle = new ShowArticle();
    /**
     * 查找所有文章
     * */
    @Override
    public Result findAll() {
        List<Article> allArticle = articleDao.findAll();
        UserDao userDao = new UserDaoImpl();
        List<ShowArticle> showArticles = new ArrayList<>();
         Result result = new Result();

        for (Article article : allArticle){
            User userToId = userDao.findUserToId(article.getUser_id());
            showArticle.setUser(userToId);
            showArticle.setArticle(article);
            showArticles.add(showArticle);
        }
        result.setCode(Result.SUCCESS);
        result.setMessage("查询所有文章成功");
        result.setObj(showArticles);
        return result;
    }
    /**
     * 查找用户所有文章
     * */
    @Override
    public Result findArticleToUser(String user_id) {
        UserDao userDao = new UserDaoImpl();
        Result result = new Result();
        User userToId = userDao.findUserToId(user_id);
        if (userToId==null){
            result.setCode(Result.FAILS);
            result.setMessage("用户不存在");
            return result;
        }
        List<Article> allArticle = articleDao.findArticleToUser(user_id);
        List<ShowArticle> showArticles = new ArrayList<>();

        for (Article article : allArticle){
            showArticle.setUser(userToId);
            showArticle.setArticle(article);
            showArticles.add(showArticle);
        }

        result.setCode(Result.SUCCESS);
        result.setMessage("查询作品库成功");
        result.setObj(showArticles);
        return result;
    }

    @Override
    public Result findArticleText(String text, String user_id) {

        List<DataArticle> dataArticles = new ArrayList<DataArticle>();
        Result result = new Result();

        List<Article> lists = articleDao.findArticleText(user_id);
        List<Word> addSeg = WordSegmenter.seg(text);
        String originText = getStringFormArticle(addSeg);
        Official_Accounts accountsText = articleDao.findAccountsText(user_id);
        for (Article article : lists) {
            List<Word> articleSeg = WordSegmenter.seg(article.getText());
            Similarity similarity = new Similarity(originText, getStringFormArticle(articleSeg));
            double sim_value = similarity.sim();
            if (sim_value >= 0.4) {
                DataArticle dataArticle = new DataArticle();
                dataArticle.setText(article.getText());
                dataArticle.setSimilarity(sim_value);
                dataArticle.setTime(article.getTime());
                dataArticle.setTitle(article.getTitle());
                dataArticle.setWechat(accountsText.getName());
                dataArticle.setWechatID(accountsText.getAccounts_id());
                dataArticles.add(dataArticle);
                result.setCode(1);
                result.setMessage("检测成功");
                result.setObj(dataArticles);
                return result;
            }

        }
        result.setCode(Result.FAILS);
        return result;
    }

//    @Test
//    public void test() {
//        List<Word> seg = WordSegmenter.seg("这是测试文章1");
//
//        Result articleText = findArticleText(getStringFormArticle(seg), "381ebd7806a311eb8a9c00163f005d9e");
//        System.out.println(articleText.toString());
//    }
//
    private String getStringFormArticle(List<Word> words) {
        StringBuffer sb = new StringBuffer();
//        for (Word word:words) {
//            sb.append(word);
//        }
        words.forEach(word->sb.append(word));
        return  sb.toString();
    }
}
