package me.isaiah.common;

public class NamedID {

    public String namespace;
    public String key;

    public NamedID(String namespace, String key) {
        this.namespace = namespace;
        this.key = key;
    }

    @Override
    public String toString() {
        return this.namespace + ":" + this.key;
    }

}