package com.oriental.finance.dto;

import java.util.List;

/**
 * Created by wuwenchang on 29/5/16.
 */
public class Datagrid {
    private int total;
    private List data;
    private String messge;
    private String status;
    private boolean succeed;

    public Datagrid(List data,boolean succeed){
        this.data=data;
        this.succeed=succeed;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }
}
