package com.monitor.dal.cfgArticlesType.entity;

/**
 * Created by zeusw on 2017/2/6.
 * @author zeusw
 */
public class CfgArticlesTypeDO {

    private Integer id;
    private String articleTypeName;

    @Override
    public String toString() {
        return "CfgArticlesTypeDO{" +
                "id=" + id +
                ", articleTypeName='" + articleTypeName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleTypeName() {
        return articleTypeName;
    }

    public void setArticleTypeName(String articleTypeName) {
        this.articleTypeName = articleTypeName;
    }
}
