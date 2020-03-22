package com.wen233.mvc.Utils;

/**
 * @author: huu
 * @date: 2020/3/21 17:01
 * @description:
 */
public class View {

    private String url;

    private String disPathAction = "FORWARD";

    public View(String url) {
        this.url = url;
    }

    public View(String url, String disPathAction) {
        this.url = url;
        this.disPathAction = disPathAction;
    }

    public View(String url, String name, Object value) {
        this(url);
        ViewData data = new ViewData();
        data.put(name, value);
    }

    public View(String url, String disPathAction, String name, Object value) {
        this.disPathAction = disPathAction;
        this.url = url;
        ViewData data = new ViewData();
        data.put(name, value);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getDisPathAction() {
        return disPathAction;
    }

    public void setDisPathAction(String disPathAction) {
        this.disPathAction = disPathAction;
    }
}
