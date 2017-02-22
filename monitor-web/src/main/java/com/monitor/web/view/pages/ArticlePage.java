package com.monitor.web.view.pages;

import com.monitor.dal.cfgArticlesType.entity.CfgArticlesTypeDO;
import com.monitor.dal.infArticles.entity.InfArticlesDO;
import com.monitor.service.article.ArticleService;
import com.monitor.service.article.ArticlesInformationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/22.
 *
 * @author Zeus Feng
 * @date 2017/02/22
 */
@RestController
@RequestMapping(value = "/article")
public class ArticlePage {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticlesInformationService articlesInformationService;

    @RequestMapping(value = "/manager")
    @ResponseBody
    public ModelAndView articlePageArticleManager(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/article/articlemanager");
        List<CfgArticlesTypeDO> cfgArticlesTypeDOList = articleService.getAllArticleTypes();
        modelAndView.addObject("artList",cfgArticlesTypeDOList);
        return modelAndView;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public ModelAndView articlePageArticleList(@Param("artId") String artId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/article/articleslist");

        if(artId != null && !artId.equals("")){
            List<InfArticlesDO> infArticlesDOList
                    = articlesInformationService.findArticlesContentByArticleTypeId(Integer.parseInt(artId));
            modelAndView.addObject("artList",infArticlesDOList);
            return modelAndView;
        }else{
            return modelAndView;
        }

    }
}
