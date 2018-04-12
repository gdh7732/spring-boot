package com.example.datasource;

import com.example.common.DatabaseType;

/**
 * 数据源容器
 *
 * @author guodahai
 * @version 2018/4/12
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = ThreadLocal.withInitial(() -> DatabaseType.NEW_DATASOURCE);

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    public static void resetDatabaseType() {
        contextHolder.set(DatabaseType.NEW_DATASOURCE);
    }

    public static void clearDatabaseType() {
        contextHolder.remove();
    }
}
