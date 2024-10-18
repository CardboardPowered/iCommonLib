/**
 * Isaiah's Configuration File Format
 * Tiny two file YAML-like configuration parser
 * 
 * Copyright (c) 2021-2024. All Rights Reserved.
 * 
 */
package me.isaiah.config;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

@SuppressWarnings("unchecked")
public class Configuration {

    protected LinkedHashMap<String, Object> contentMap;

    public Configuration() {
    }

    public Configuration(LinkedHashMap<String, Object> contentMap) {
        this.contentMap = contentMap;
    }

    /**
     */
    public <T> T getOrDefault(String key, T defaul) {
        return (T) (Object)contentMap.getOrDefault(key, defaul);
    }

    /**
     */
    public <T> T get(Class<T> type, String key) {
        return (T) contentMap.get(key);
    }

    /**
     */
    public Object getObject(String key) {
        return contentMap.get(key);
    }

    /**
     */
    public String getString(String key) {
        return (String) contentMap.get(key);
    }

    /**
     */
    public boolean getBoolean(String key) {
        return (Boolean) contentMap.get(key);
    }

    /**
     */
    public int getInt(String key) {
        return (Integer) (Object)contentMap.get(key);
    }

    /**
     */
    public double getDouble(String key) {
        return (Double) contentMap.get(key);
    }

    /**
     * @param key - key with which the specified value is to be associated
     * @param value - value to be associated with the specified key
     * 
     * @return the previous value, or null.
     */
    public void set(String key, Object value) {
        contentMap.put(key, value);
    }

    public void save(File to) throws IOException {
    }

    public void save() throws IOException {
    }

}