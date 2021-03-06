package com.pillForZhihu.webApp.logistic.controllers;

import com.pillForZhihu.webApp.dao.User;
import com.pillForZhihu.webApp.dao.User_key;
import com.pillForZhihu.webApp.dao.User_value;
import com.pillForZhihu.webApp.utils.map.DatabaseMapBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    Map<String,String> listUserInfo(@PathVariable Long user_id){
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
        result.put(USER_ID_ATTR,tmp.get(0).getEntityId().toString());
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> register(User user, HttpSession session) {
        Map<String,String> result=this.login(user, session);
        if (null ==result ) {
            this.saveEntity(user);
            this.setSqlSession(this.getDatabaseController().getSqlSession());
            result=this.login(user,session);
        }
        return result;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public
    @ResponseBody
    String[] getUserInfo(@RequestParam Long user_id,
                         @RequestParam String user_attribute_key) {
        return this.getEntityInfo(user_id,user_attribute_key);
    }

    @RequestMapping(value = "/user"
            ,method = RequestMethod.PUT)
    public
    @ResponseBody
    String[] putUserInfo(@RequestParam Long user_id,
                         @RequestParam String user_attribute_key,
                         @RequestParam String user_attribute_value,
                         HttpSession session) {
        if(!session.getAttribute(USER_ID_ATTR).equals(user_id))
            return null;

        return this.putEntityInfo(user_id,user_attribute_key,user_attribute_value);
    }

    @RequestMapping(value = "/user"
            ,method = RequestMethod.DELETE)
    public
    @ResponseBody
    String[] deleteUserInfo(@RequestParam Long user_id,
                            @RequestParam String user_attribute_key,
                            HttpSession session) {
        if(!session.getAttribute(USER_ID_ATTR).equals(user_id))
            return null;

        return this.deleteEntityInfo(user_id,user_attribute_key);
    }
}
