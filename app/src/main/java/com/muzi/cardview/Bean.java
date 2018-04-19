package com.muzi.cardview;

/**
 * Created by muzi on 2018/4/19.
 * 727784430@qq.com
 */

public class Bean {

    private boolean isOpen = false;

    private String text;

    public Bean() {
    }

    public Bean(String text) {
        this.text = text;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
