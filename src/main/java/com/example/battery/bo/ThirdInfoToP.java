package com.example.battery.bo;

import lombok.Data;

/**
 *  //第三步，顾客C把第二步得到的信息解密之后，得到G签名的信息，加上IDC  IDP一起发给机动车检验部门P
 *
 */
@Data
public class ThirdInfoToP {
    private String idc;
    private String idp;
    private String msg;
}
