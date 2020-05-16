package com.example.battery.bo;

import lombok.Data;

/**
 * //M是发票
 *     //第一步，顾客C把自己的信息IDC，政府信息IDG，时间戳Date和M用C的密钥加密后，发给政府G
 *
 */
@Data
public class FirstUserSentGovernment {

    private String idc;

    private String idg;
    private Long date;
    private String m;
}
