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
}
