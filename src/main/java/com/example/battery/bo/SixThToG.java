package com.example.battery.bo;

import lombok.Data;

/**
 * //第六步，回收单位R把第五步收到的消息验签之后，\
 * 得到用政府公钥签名的信息，加上IDR  IDC  IDP  IDG和时间戳date"，
 * 用R的私密钥签名，再加上IDR  IDG一起发送给政府G
 *
 */
@Data
public class SixThToG {
    private String msg;
    private String idr;
    private String idg;
}
