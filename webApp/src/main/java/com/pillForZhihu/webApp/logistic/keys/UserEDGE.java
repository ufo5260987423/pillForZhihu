package com.pillForZhihu.webApp.logistic.keys;

/**
 * Created by ufo on 6/16/15.
 */
public enum UserEDGE {
    FOLLOW("follow"),BLOCK("block");

    private String edge;

    UserEDGE(String edge) {
        this.setEdge(edge);
    }

    public String getEdge() {
        return edge;
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }
}
