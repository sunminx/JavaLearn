package com.xy.dp.factory;

import java.util.HashMap;
import java.util.Map;

public class Config {
    private String name;
    private String author;
    private Map<String, Object> k2v;

    public Config(String name, String author) {
        this.name = name;
        this.author = author;
        this.k2v = new HashMap<>();
    }

    public void addProperties(String key, Object val) {
        /* 不进行属性覆盖 */
        if (!this.k2v.containsKey(key))
            this.k2v.put(key, val);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Map<String, Object> getK2v() {
        return k2v;
    }

    public void setK2v(Map<String, Object> k2v) {
        this.k2v = k2v;
    }
}
