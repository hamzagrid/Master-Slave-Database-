package com.practice.masterslave.config;

public class DBContext {

    private static final ThreadLocal<DBType> contextHolder = new ThreadLocal<>();

    public static void set(DBType dbType) {
        contextHolder.set(dbType);
    }

    public static DBType get() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}