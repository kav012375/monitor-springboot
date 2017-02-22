package com.monitor.service.article.impl;

import com.monitor.dal.infArticles.dao.InfArticlesDAO;
import com.monitor.dal.infArticles.entity.InfArticlesDO;
import com.monitor.service.article.ArticlesInformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/21.
 *
 * @author Zeus Feng
 * @date 2017/02/21
 */
public class ArticlesInformationImpl implements ArticlesInformationService {
    @Autowired
    InfArticlesDAO infArticlesDAO;

    @Override
    public List<InfArticlesDO> findArticlesContentByArticleTypeId(int articleTypeId) {
        return infArticlesDAO.findArticlesContentByArticleTypeId(articleTypeId);
    }

    @Override
    public int updateArticlesContentById(InfArticlesDO infArticlesDO) {
        return infArticlesDAO.updateArticlesContentById(infArticlesDO);
    }

    @Override
    public int deleteArticlesById(Long id) {
        return infArticlesDAO.deleteArticlesById(id);
    }

    @Override
    public int deleteArticlesByArticleTypeId(int articleTypeId) {
        return infArticlesDAO.deleteArticlesByArticleTypeId(articleTypeId);
    }

    @Override
    public int insertNewArticlesContentByArticleTypeId(InfArticlesDO infArticlesDO) {
        return infArticlesDAO.insertNewArticlesContentByArticleTypeId(infArticlesDO);
    }
}
