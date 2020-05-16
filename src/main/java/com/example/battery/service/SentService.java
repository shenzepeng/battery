package com.example.battery.service;

import com.example.battery.bo.*;

import java.util.List;

/**
 * 要写注释呀
 */
public interface SentService {
    //M是发票
    //第一步，顾客C把自己的信息IDC，政府信息IDG，时间戳Date和M用C的密钥加密后，发给政府G
    public String GetMsgToG1(FirstUserSentGovernment firstUserSentGovernment,String usk);
    //第二步，政府G把第一步收到的信息解密 ，再用政府的公开钥KPG签名，签名后的信息加上IDC  IDG  IDP一起用C的密钥加密，加密后的信息再加上IDC  IDG  IDP和IDK1  IDK2……IDKK，一并发给顾客C，其中K指的是政府回收部门
    public SecondSentUser getMsgToC2(String msg, String upk,String gpc,String usk,String idk,String idp);
    //第三步，顾客C把第二步得到的信息解密之后，得到G签名的信息，加上IDC  IDP一起发给机动车检验部门P
    public ThirdInfoToP getMsgToCheck3(String pc, SecondSentUser secondSentUser);
   // 第四步，机动车检验之后，机动车检验部门P把第三步的签名信息中加入IDP，时间戳Date'，重新用政府G的公开钥进行签名，再用P的私密钥进行签名，加上IDP  IDC发给顾客C。其中重新签名后的信息，是C和R无法伪造的。
    public ForthDto addInfo4(ThirdInfoToP thirdInfoToP,String gpk,String ppk);
   // 第五步，顾客C把第四步得到的签名后的信息，加上IDC  IDR发送给回收单位R
    public FifthToR sentToRByC5(ForthDto forthDto,String idc,String idr);
    //第六步，回收单位R把第五步收到的消息验签之后，得到用政府公钥签名的信息，加上IDR  IDC  IDP  IDG和时间戳date"，用R的私密钥签名，再加上IDR  IDG一起发送给政府G
    public SixThToG rSentToG6(FifthToR fifthToR,String idr,String idg);
}
