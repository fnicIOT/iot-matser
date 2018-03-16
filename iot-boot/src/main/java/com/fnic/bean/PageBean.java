package com.fnic.bean;

public class PageBean {

    private int pageNum;

    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageStart() {
        return (pageNum - 1) * pageSize;
    }

    public int getPageEnd() {
        return pageNum + pageSize;
    }
}
