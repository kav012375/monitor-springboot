package com.monitor.web.controller;

import com.monitor.dal.cfgArticlesType.entity.CfgArticlesTypeDO;
import com.monitor.dal.infArticles.entity.InfArticlesDO;
import com.monitor.service.article.ArticleService;
import com.monitor.service.article.ArticlesInformationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zeus Feng on 2017/2/21.
 *
 * @author Zeus Feng
 * @date 2017/02/21
 */
@RestController
@RequestMapping(value = "/article",method = RequestMethod.POST)
public class Articles {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticlesInformationService articlesInformationService;

    @RequestMapping(value = "/add_new_article_type")
    @ResponseBody
    public String addNewArticleType(@RequestParam("aType") String artName){
        try {
            if (artName == null || artName.equals("")){
                return "评论类型名为空!";
            }
            CfgArticlesTypeDO cfgArticlesTypeDO = new CfgArticlesTypeDO();
            cfgArticlesTypeDO.setArticleTypeName(artName);
            int insertNum = articleService.insertNewArticleType(cfgArticlesTypeDO);
            if (insertNum<1){
                return "插入失败!";
            }else{
                return "新增成功!";
            }
        }catch (Exception e){
            return "系统异常!";
        }
    }

    @RequestMapping(value = "/del_article_type")
    @ResponseBody
    public String delArticleTypeById(@RequestParam("artName") String artName){
        try {
            if (artName == null || artName.equals("")){
                return "评论类型Id为空!";
            }
            final CfgArticlesTypeDO cfgArticlesTypeDO = articleService.findArticleTypeByArticleTypeName(artName);
            int delNum = articleService.deleteArticleTypeByArticleTypeName(artName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(cfgArticlesTypeDO.getId()>0 ){
                        articlesInformationService.deleteArticlesByArticleTypeId(cfgArticlesTypeDO.getId());
                    }
                }
            }).start();
            if (delNum<1){
                return "删除失败!";
            }else{
                return "删除成功!";
            }
        }catch (Exception e){
            return "系统异常!";
        }
    }

    @RequestMapping(value = "/modify_article_type")
    @ResponseBody
    public String modifyArticleType(@RequestParam("aId") String artId,@RequestParam("aType") String artName){
        try {
            if (artId == null || artId.equals("") || artName == null || artName.equals("")){
                return "参数错误，有空值！";
            }
            CfgArticlesTypeDO cfgArticlesTypeDO = new CfgArticlesTypeDO();
            cfgArticlesTypeDO.setId(Integer.parseInt(artId));
            cfgArticlesTypeDO.setArticleTypeName(artName);
            int updateNum = articleService.updateArticleTypeNameById(cfgArticlesTypeDO);
            if (updateNum<1){
                return "更新失败!";
            }else{
                return "更新成功!";
            }
        }catch (Exception e){
            return "系统异常!";
        }
    }

    @RequestMapping(value = "/modify_articlescontent_by_id")
    @ResponseBody
    public String modifyArticlesContentById(@RequestParam("aId") String artId,@RequestParam("aContent") String artContent){
        try {
            if (artId == null || artId.equals("")){
                return "参数错误，有空值！";
            }
            InfArticlesDO infArticlesDO = new InfArticlesDO();
            infArticlesDO.setArticleContent(artContent);
            infArticlesDO.setId(Long.parseLong(artId));
            int updateNum = articlesInformationService.updateArticlesContentById(infArticlesDO);
            if (updateNum<1){
                return "更新失败!";
            }else{
                return "更新成功!";
            }
        }catch (Exception e){
            return "系统异常!";
        }
    }

    @RequestMapping(value = "/delete_articlescontent_by_id")
    @ResponseBody
    public String deleteArticlesContentById(@RequestParam("aId") String aidString){
        //传入多个id时，使用;分隔
        String[] artIds = aidString.split(";");
        String updateFailedArticlesId = "";
        try {
            if (artIds == null || artIds.length<1){
                return "参数错误，有空值！";
            }
            for( String artId : artIds){
                int updateNum = articlesInformationService.deleteArticlesById(Long.parseLong(artId));
                if (updateNum<1) {
                    updateFailedArticlesId = updateFailedArticlesId + artId + ";";
                }
            }
            if(updateFailedArticlesId.length() >= 2){
                return "更新完成，失败的数据为：" + updateFailedArticlesId;
            }else{
                return "更新完成!";
            }
        }catch (Exception e){
            return "系统异常！";
        }
    }

    @RequestMapping(value = "/add_new_articles_content")
    @ResponseBody
    public String addNewArticlesContet(@RequestParam("aId") String artId,@RequestParam("aContent") String artContent){
        try {
            if (artId == null || artId.equals("")){
                return "参数错误，有空值！";
            }
            InfArticlesDO infArticlesDO = new InfArticlesDO();
            infArticlesDO.setArticleContent(artContent);
            infArticlesDO.setArticleTypeId(Integer.parseInt(artId));
            int updateNum = articlesInformationService.insertNewArticlesContentByArticleTypeId(infArticlesDO);
            if (updateNum<1){
                return "新增失败!";
            }else{
                return "新增成功!";
            }
        }catch (Exception e){
            return "系统异常!";
        }
    }
}
