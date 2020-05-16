package com.example.battery.utils;

import java.io.UnsupportedEncodingException;

/**
 * 要写注释呀
 */
public class DataProcessUtils {
    public static byte[] stringToBytes(String str) {
        try {
            // 使用指定的字符集将此字符串编码为byte序列并存到一个byte数组中
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytesToString(byte[] bs) {
        try {
            // 通过指定的字符集解码指定的byte数组并构造一个新的字符串
            return new String(bs, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
