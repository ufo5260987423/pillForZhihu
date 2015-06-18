package com.pillForZhihu.webApp.logistic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ufo on 6/17/15.
 */
@Controller
@SessionAttributes({"user_id"})
public class UserController {

    @RequestMapping(value = "/login")
    public
    @ResponseBody
    Map login() {
        Map tmp = new HashMap();
        tmp.put("test", "aaa");
        return tmp;
    }
}
