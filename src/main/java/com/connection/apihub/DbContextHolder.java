package com.connection.apihub;

public class DbContextHolder {

    private static final ThreadLocal<UtilsTransactiion.DataSourceType> contextHolder = new ThreadLocal<>();

    public static void setDbType(UtilsTransactiion.DataSourceType dbType) {
        if(dbType == null){
            throw new NullPointerException();
        }
        contextHolder.set(dbType);
    }

    public static UtilsTransactiion.DataSourceType getDbType() {
        return (UtilsTransactiion.DataSourceType) contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }

}
