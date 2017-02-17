package com.monitor.dal.infArticles.entity;

/**
 * Created by zeusw on 2017/2/6.
 * @author zeusw
 */
public class InfArticlesDO {

    private Long id;
    private Integer articleTypeId;
    private String articleContent;

    @Override
    public String toString() {
        return "InfArticlesDO{" +
                "id=" + id +
                ", articleTypeId=" + articleTypeId +
                ", articleContent='" + articleContent + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(int articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}
