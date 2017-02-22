package com.monitor.service.article;

import com.monitor.dal.infArticles.entity.InfArticlesDO;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/21.
 *
 * @author Zeus Feng
 * @date 2017/02/21
 */
public interface ArticlesInformationService {
    List<InfArticlesDO> findArticlesContentByArticleTypeId(int articleTypeId);
    int updateArticlesContentById(InfArticlesDO infArticlesDO);
    int deleteArticlesById(Long id);
    int deleteArticlesByArticleTypeId(int articleTypeId);
    int insertNewArticlesContentByArticleTypeId(InfArticlesDO infArticlesDO);
}
