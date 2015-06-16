package com.pillForZhihu.webApp.utils.map;

import java.util.Map;

/**
 * Created by ufo on 6/16/15.
 */
public interface DatabaseMapInf extends Map<String, String> {
    void load();

    void save();
}
