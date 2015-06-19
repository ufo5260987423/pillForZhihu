package com.pillForZhihu.webApp.logistic.controllers;

import com.pillForZhihu.webApp.dao.Content;
import com.pillForZhihu.webApp.dao.Content_key;
import com.pillForZhihu.webApp.dao.Content_value;
import com.pillForZhihu.webApp.logistic.keys.ContentKEY;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by ufo on 6/18/15.
 */
@Controller
public class ContentController extends BaseController<Content,Content_key,Content_value> {
    public ContentController(){
        this.setEntityClass(Content.class);
        this.setKeyClass(Content_key.class);
        this.setValueClass(Content_value.class);
    }

    @RequestMapping(value = "/content/{content_id}",method = RequestMethod.GET)
    public
    @ResponseBody
    Content getContent(@PathVariable Long content_id){
        return this.getEntity(content_id);
    }

    @RequestMapping(value="/content/{content_id}/list",method=RequestMethod.GET)
    public
    @ResponseBody
    Map<String,String> listContentInfo(@PathVariable Long content_id){
        return this.listEntityInfo(content_id);
    }

    @RequestMapping(value = "/content/{content_id}/{content_key}",method = RequestMethod.GET)
    public
    @ResponseBody
    String[] getContentInfo(@PathVariable Long content_id,
                            @PathVariable String content_key){
        return this.getEntityInfo(content_id,content_key);
    }

    @RequestMapping(value="/content/{content_id}",method = RequestMethod.DELETE)
    public
    @ResponseBody
    String[] deleteContentInfo(@PathVariable Long content_id, HttpSession session){
        Map<String,String>contentInfo=this.listEntityInfo(content_id);
        if(!contentInfo.get(ContentKEY.AUTHOR_ID.toString()).equals(session.getAttribute("user_id"))
                ||contentInfo.get(ContentKEY.QUESTION_TITLE)!=null)
            return FAIL;

        return this.deleteEntity(this.getEntity(content_id));
    }

    @RequestMapping(value="content/{content_id}/{content_key}/{content_value}")
    public
    String[] putContentInfo(@PathVariable Long content_id,
                            @PathVariable String content_key,
                            @PathVariable String content_value,
                            HttpSession session){
        Map<String,String>contentInfo=this.listEntityInfo(content_id);
        if(!contentInfo.get(ContentKEY.AUTHOR_ID.toString()).equals(session.getAttribute("user_id"))
                ||contentInfo.get(ContentKEY.QUESTION_TITLE)!=null
                ||contentInfo.get(ContentKEY.AUTHOR_ID)!=null)
            return FAIL;

        return this.putEntityInfo(content_id,content_key,content_value);
    }
}
