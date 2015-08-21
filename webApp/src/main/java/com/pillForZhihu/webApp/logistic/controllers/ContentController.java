package com.pillForZhihu.webApp.logistic.controllers;

import com.pillForZhihu.webApp.dao.Content;
import com.pillForZhihu.webApp.dao.Content_key;
import com.pillForZhihu.webApp.dao.Content_value;
import com.pillForZhihu.webApp.logistic.keys.ContentKEY;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by ufo on 6/18/15.
 */
@Controller
@RequestMapping("/content")
public class ContentController extends BaseController<Content,Content_key,Content_value> {
    public ContentController(){
        this.setEntityClass(Content.class);
        this.setKeyClass(Content_key.class);
        this.setValueClass(Content_value.class);
    }

    @RequestMapping(value = "/entity",method = RequestMethod.GET)
    public
    @ResponseBody
    Content getContent(@RequestParam Long content_id){
        return this.getEntity(content_id);
    }

    @RequestMapping(value="/entity",method=RequestMethod.PUT)
    public
    @ResponseBody
    Content putContent(@RequestParam String content,
                       HttpSession httpSession){
        Content newContent=new Content();
        newContent.setContent(content);
        newContent=this.saveEntity(newContent);
        this.putEntityInfo(newContent.getEntityId(),
                ContentKEY.AUTHOR_ID.toString(),
                httpSession.getAttribute(USER_ID_ATTR).toString());
        return newContent;
    }

    @RequestMapping(value="/entity",method = RequestMethod.DELETE)
    public
    @ResponseBody
    String[]deleteContent(@RequestParam Long content_id,
                          HttpSession httpSession){
        if(httpSession.getAttribute(USER_ID_ATTR)
                .equals(this.getEntityInfo(content_id,ContentKEY.AUTHOR_ID.toString())))
            return this.deleteEntity(this.getEntity(content_id));
        return FAIL;
    }

    @RequestMapping(value="/listInfo",method=RequestMethod.GET)
    public
    @ResponseBody
    Map<String,String> listContentInfo(@RequestParam Long content_id){
        return this.listEntityInfo(content_id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    String[] getContentInfo(@RequestParam Long content_id,
                            @RequestParam String content_key){
        return this.getEntityInfo(content_id, content_key);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public
    @ResponseBody
    String[] deleteContentInfo(@RequestParam Long content_id,
                               HttpSession session){
        Map<String,String>contentInfo=this.listEntityInfo(content_id);
        if(!contentInfo.get(ContentKEY.AUTHOR_ID.toString()).equals(session.getAttribute(USER_ID_ATTR))
                ||contentInfo.get(ContentKEY.QUESTION_TITLE.toString())!=null)
            return FAIL;

        return this.deleteEntity(this.getEntity(content_id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public
    String[] putContentInfo(@RequestParam Long content_id,
                            @RequestParam String content_key,
                            @RequestParam String content_value,
                            HttpSession session){
        Map<String,String>contentInfo=this.listEntityInfo(content_id);
        if(!contentInfo.get(ContentKEY.AUTHOR_ID.toString()).equals(session.getAttribute(USER_ID_ATTR))
                ||contentInfo.get(ContentKEY.QUESTION_TITLE.toString())!=null
                ||contentInfo.get(ContentKEY.AUTHOR_ID.toString())!=null)
            return FAIL;

        return this.putEntityInfo(content_id, content_key, content_value);
    }
}
