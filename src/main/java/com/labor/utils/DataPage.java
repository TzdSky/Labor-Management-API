package com.labor.utils;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DataPage<T> extends DataList<T> {

    private Integer page;
    private Integer size;
    private Long total;

    public DataPage() {
    }

    public DataPage(Page<T> page, String... ignoreProperties) {
        List rows = new ArrayList<>();
        for (Object bean : page.getContent()) {
            rows.add(new DataItem(bean, ignoreProperties));
        }
        this.rows = rows;
        this.page = page.getNumber();
        this.size = page.getSize();
        this.total = page.getTotalElements();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

