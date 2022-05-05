package com.labor.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DataList<T> implements Serializable {

    protected List<T> rows;

    public DataList() {
    }

    public DataList(List<T> beans) {
        this.rows = beans;
    }

    public DataList(List<?> beans, String... ignoreProperties) {
        describe(beans, ignoreProperties);
    }

    private void describe(List<?> beans, String... ignoreProperties) {
        List rows = new ArrayList<>();
        for (Object bean : beans) {
            rows.add(new DataItem(bean, ignoreProperties));
        }
        this.rows = rows;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
