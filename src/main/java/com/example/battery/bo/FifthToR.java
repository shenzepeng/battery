package com.example.battery.bo;

import lombok.Data;

/**
 * // 第五步，顾客C把第四步得到的签名后的信息，加上IDC  IDR发送给回收单位R
 *
 */
@Data
public class FifthToR {
    private ForthDto forthDto;
    private String idc;
    private String idr;
}
