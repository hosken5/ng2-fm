package com.yimei.cron.util;

import java.io.Serializable;

public interface PagedListHolder extends Serializable {

    int DEFAULT_PAGE_SIZE = 10;
    int DEFAULT_MAX_LINKED_PAGES = 5;


    void setTotal(long realRecordCount);

    void setPageSize(int pageSize);

    int getPageSize();

    int getPageCount();

    int getPage();

    void setPage(int page);

    void setMaxLinkedPages(int maxLinkedPages);

    int getMaxLinkedPages();

    int getFirstLinkedPage();

    int getLastLinkedPage();

    void previousPage();

    void nextPage();

    void firstPage();

    void lastPage();

    long getNrOfElements();

    int getFirstElementOnPage();

    int getLastElementOnPage();
}
