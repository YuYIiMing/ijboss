package com.injoin.ijboss.api.vo;

import java.util.List;

/**
 * @author figo.xu
 * @param <T>
 */
public class PageVo<T,V> {
    //
    private long count;
    //本页数据
    private List<T> rows;

    private V nextKeys;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public V getNextKeys() {
        return nextKeys;
    }

    public void setNextKeys(V nextKeys) {
        this.nextKeys = nextKeys;
    }
}
