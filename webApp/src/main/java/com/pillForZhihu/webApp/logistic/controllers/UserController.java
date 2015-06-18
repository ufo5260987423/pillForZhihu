package com.pillForZhihu.webApp.logistic.controllers;

import com.pillForZhihu.webApp.dao.User;
import com.pillForZhihu.webApp.dao.User_key;
import com.pillForZhihu.webApp.dao.User_value;
import com.pillForZhihu.webApp.utils.map.DatabaseMapBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by ufo on 6/17/15.
 */
@Controller
public class UserController extends BaseController<User,User_key,User_value>{

    public UserController(){
        this.setEntityClass(User.class);
        this.setKeyClass(User_key.class);
        this.setValueClass(User_value.class);
    }

    @RequestMapping(value="/user/{user_id}/list")
    public
    @ResponseBody
    Map<String,String> listUserInfo(@PathVariable Long user_id,
                                    HttpSession session){
        if (!session.getAttribute("user_id").equals(user_id))
            return null;

        return this.listEntityInfo(user_id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> login(User user, HttpSession session) {
        List<User> tmp = this.getSqlSession().createQuery("from User where user_name=? and user_pwd=?")
                .setString(0, user.getUser_name())
                .setString(1, user.getUser_pwd()).list();

        if (tmp.isEmpty())
            return null;

        DatabaseMapBase<User, User_key, User_value> databaseMapBase =
                new DatabaseMapBase<User, User_key, User_value>
                        (tmp.get(0),this.getKeyClass(),this.getValueClass(),this.getSqlSession());

        session.setAttribute("user_id", tmp.get(0).getEntityId());

        Map<String,String> result= (Map<String, String>) databaseMapBase.clone();
        result.put("user_id",tmp.get(0).getEntityId().toString());
        return result;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> register(User user, HttpSession session) {
        Map<String,String> result=this.login(user,session);
        if (null ==result ) {
            this.saveEntity(user);
            this.setSqlSession(this.getDatabaseController().getSqlSession());
            result=this.login(user,session);
        }
        return result;
    }

    @RequestMapping(value = "/user/{user_id}/{user_attribute_key}", method = RequestMethod.GET)
    public
    @ResponseBody
    String[] getUserInfo(@PathVariable Long user_id,
                         @PathVariable String user_attribute_key,
                         HttpSession session) {
        if (!session.getAttribute("user_id").equals(user_id))
            return null;

        return this.getEntityInfo(user_id,user_attribute_key);
    }

    @RequestMapping(value = "/user/{user_id}/{user_attribute_key}/{user_attribute_value}"
            ,method = RequestMethod.PUT)
    public
    @ResponseBody
    String[] putUserInfo(@PathVariable Long user_id,
                         @PathVariable String user_attribute_key,
                         @PathVariable String user_attribute_value,
                         HttpSession session) {
        if(!session.getAttribute("user_id").equals(user_id))
            return null;

        return this.putEntityInfo(user_id,user_attribute_key,user_attribute_value);
    }

    @RequestMapping(value = "/user/{user_id}/{user_attribute_key}"
            ,method = RequestMethod.DELETE)
    public
    @ResponseBody
    String[] deleteUserInfo(@PathVariable Long user_id,
                            @PathVariable String user_attribute_key,
                            HttpSession session) {
        if(!session.getAttribute("user_id").equals(user_id))
            return null;

        return this.deleteEntityInfo(user_id,user_attribute_key);
    }
}
