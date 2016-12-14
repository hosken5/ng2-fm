package com.yimei.cron.util;

/**
 * Created by hongpf on 16/7/29.
 */

import com.github.pagehelper.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PageHolder<E> extends ArrayList<E> implements  PagedListHolder {

    public static PageHolder  of(Page page  ){
        PageHolder holder = new  PageHolder();
        holder.setPageSize(page.getPageSize());
        holder.setPage( page.getPageNum()==0?1:page.getPageNum());
        holder.setTotal(page.getTotal());
        holder.addAll(page);
        return  holder ;
    }
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int maxLinkedPages = DEFAULT_MAX_LINKED_PAGES;
    private int page = 1;
    private String url = "";

    private long total;


    public String getUrl() {
        return url;
    }

    public PageHolder<E> setUrl(String url) {
        this.url = url ;
        return this ;
    }

    public long  getTotal(){
        return total ;
    }
    public void setTotal(long total) {
        this.total = total;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageCount() {
        float nrOfPages = (float) getNrOfElements() / getPageSize();
        return (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setMaxLinkedPages(int maxLinkedPages) {
        this.maxLinkedPages = maxLinkedPages;
    }

    public int getMaxLinkedPages() {
        return maxLinkedPages;
    }

    public int getFirstLinkedPage() {
        return Math.max(1, getPage() - getMaxLinkedPages()/2);
    }

    public int getLastLinkedPage() {
        return Math.min(getFirstLinkedPage() + getMaxLinkedPages() - 1, getPageCount());
    }

    public void previousPage() {
        if (!isAtFirstPage()) {
            page--;
        }
    }

    public void nextPage() {
        if (!isAtLastPage()) {
            page++;
        }
    }

    public void firstPage() {
        setPage(1);
    }

    public void lastPage() {
        setPage(getPageCount());
    }

    public long getNrOfElements() {
        return total;
    }

    public int getFirstElementOnPage() {
        return (getPageSize() * (getPage()-1)+1);
    }

    public int getLastElementOnPage() {
        int endIndex = getPageSize() * getPage() ;
        return (endIndex > getNrOfElements() ? (int) getNrOfElements() : endIndex);
    }

    public List<Integer> getPageNumList() {
        List<Integer> pageNumList = new ArrayList<>();
        for (int i = getFirstLinkedPage(); i <= getLastLinkedPage(); i++) {
            pageNumList.add(i);
        }

        return pageNumList;
    }

    public boolean isAtFirstPage() {
        return getPage() == 1;
    }

    public boolean isAtLastPage() {
        return getPage() == getPageCount();
    }

    public static    String getUrl(HttpServletRequest request ) {
        Enumeration em = request.getParameterNames();
        StringBuffer queryString =  new StringBuffer("");
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            if(name.equalsIgnoreCase("page") || name.equalsIgnoreCase("size")){
                continue;
            }
            queryString.append(name+"="+value+"&") ;
        }
//        queryString.append("page="+page+"&size="+pageSize);
        String qr = queryString.toString();
        if(qr!=null && qr!=""){
            qr="?"+qr  ;
        }
        return  request.getRequestURI() + qr;
    }
}
