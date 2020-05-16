package com.example.battery.bo;

import lombok.Data;

import java.util.List;

/**
 * //第二步，政府G把第一步收到的信息解密 ，
 * 再用政府的公开钥KPG签名，签名后的信息加上IDC  IDG
 * IDP一起用C的密钥加密，加密后的信息再加上IDC  IDG
 * IDP和IDK1  IDK2……IDKK，一并发给顾客C，其中K指的是政府回收部门
 *
 */
@Data
public class SecondSentUser {

    private String idk;
    /**
     * 公钥 通过c的密钥 加密后
     */
    private String idp;

    private String idc;

    private String idg;
    /**
     *  再用政府的公开钥KPG签名
     */
    private String msg;
}
