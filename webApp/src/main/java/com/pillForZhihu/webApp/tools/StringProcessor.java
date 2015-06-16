package com.pillForZhihu.webApp.tools;

/**
 * Created by ufo on 6/16/15.
 */
public class StringProcessor {
    public static String lowerFirstChar(String target) {
        return target.replaceFirst(
                target.substring(0, 1),
                target.substring(0, 1).toLowerCase());
    }
}
