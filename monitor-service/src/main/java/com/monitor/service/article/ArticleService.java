package com.monitor.service.article;

import com.monitor.dal.cfgArticlesType.entity.CfgArticlesTypeDO;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/21.
 *
 * @author Zeus Feng
 * @date 2017/02/21
 */
public interface ArticleService {
    /**
     * 获取所有的评论类型
     * @return
     */
    List<CfgArticlesTypeDO> getAllArticleTypes();
    CfgArticlesTypeDO findArticleTypeById(int id);
    CfgArticlesTypeDO findArticleTypeByArticleTypeName(String articleTypeName);
    int insertNewArticleType(CfgArticlesTypeDO cfgArticlesTypeDO);
    int deleteArticleTypeById(int id);
    int deleteArticleTypeByArticleTypeName(String articleTypeName);
    int updateArticleTypeNameById(CfgArticlesTypeDO cfgArticlesTypeDO);
}
