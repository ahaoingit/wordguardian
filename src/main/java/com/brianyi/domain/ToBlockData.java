package com.brianyi.domain;

/**
 * TODO
 *上链数据 供json转换
 * @author ahao 2020-10-06
 *
 *         Map<String,String> blockData = new HashMap<>();
 *         blockData.put("articleId",articleId);
 *         blockData.put("title",title);
 *         blockData.put("text",content);
 */
public class ToBlockData {
    private String articleId;
    private String title;
    private String text;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
