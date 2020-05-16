package com.example.battery.bo;

import lombok.Data;

/**
 * 机动车检验之后，
 * 机动车检验部门P把第三步的签名信息中加入IDP，
 * 时间戳Date'，重新用政府G的公开钥进行签名，
 * 再用P的私密钥进行签名
 */
@Data
public class ForthDto {
    private String idp;
    private Long date;
    private String gpk;
    private String pk;
}
