package com.example.util;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 生成一个随机 UUID（32位，不带'-'）
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成原生 UUID（36位，带'-'）
     */
    public static String rawUUID() {
        return UUID.randomUUID().toString();
    }
}
