package com.oriental.finance.dto;

/**
 * Created by wuwenchang on 2/6/16.
 */
public class Node {
    private String id;
    private String text;
    private String pid;
    private String url;
    private String  isLeaf;
    private boolean expanded;


    public Node(String id,String pid,String text,String url,String isLeaf,boolean expanded){
        this.id=id;
        this.pid=pid;
        this.text=text;
        this.url=url;
        this.isLeaf=isLeaf;
        this.expanded=expanded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

}
