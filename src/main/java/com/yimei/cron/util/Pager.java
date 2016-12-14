package com.yimei.cron.util;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * Created by hongpf on 16/9/28.
 */
public class Pager<E> {
    public static Pager  of(Page page  ){
        Pager pager = new  Pager();
        pager.setPageSize(page.getPageSize());
        pager.setPage( page.getPageNum()==0?1:page.getPageNum());
        pager.setTotal(page.getTotal());
        pager.setData(page);
        return  pager ;
    }

    private int pageSize   ;
    private int page ;
    private long total;
    private List<E> data ;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
}
