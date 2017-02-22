package com.monitor.service.article.impl;

import com.monitor.dal.cfgArticlesType.dao.CfgArticlesTypeDAO;
import com.monitor.dal.cfgArticlesType.entity.CfgArticlesTypeDO;
import com.monitor.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/21.
 *
 * @author Zeus Feng
 * @date 2017/02/21
 */
public class ArticleImpl implements ArticleService {
    @Autowired
    CfgArticlesTypeDAO cfgArticlesTypeDAO;

    @Override
    public List<CfgArticlesTypeDO> getAllArticleTypes() {
        return cfgArticlesTypeDAO.findAllCfgArticlesType();
    }

    @Override
    public CfgArticlesTypeDO findArticleTypeById(int id) {
        return cfgArticlesTypeDAO.findArticleTypeById(id);
    }

    @Override
    public CfgArticlesTypeDO findArticleTypeByArticleTypeName(String articleTypeName) {
        return cfgArticlesTypeDAO.findArticleTypeByArticleTypeName(articleTypeName);
    }

    @Override
    public int insertNewArticleType(CfgArticlesTypeDO cfgArticlesTypeDO) {
        return cfgArticlesTypeDAO.insertNewArticleType(cfgArticlesTypeDO);
    }

    @Override
    public int deleteArticleTypeById(int id) {
        return cfgArticlesTypeDAO.deleteArticleTypeById(id);
    }

    @Override
    public int deleteArticleTypeByArticleTypeName(String articleTypeName) {
        return cfgArticlesTypeDAO.deleteArticleTypeByArticleTypeName(articleTypeName);
    }

    @Override
    public int updateArticleTypeNameById(CfgArticlesTypeDO cfgArticlesTypeDO) {
        return cfgArticlesTypeDAO.updateArticleTypeNameById(cfgArticlesTypeDO);
    }
}
