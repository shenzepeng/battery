package com.example.battery.cache;

import com.example.battery.pojo.AuditUserAndKey;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 要写注释呀
 */
public class Caches {
    public static ConcurrentHashMap<String,AuditUserAndKey> USER_AND_AUDIT_KEY=new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String,String> USER_AND_KEY=new ConcurrentHashMap<>();

}
