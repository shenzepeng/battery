package com.example.battery.bo;

import lombok.Data;

/**
 * 再用政府的公开钥KPG签名
 */
@Data
public class SingWithGSign {

    private String idp;

    private String idc;

    private String idg;

    private String gpk;
}
