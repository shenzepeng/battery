package com.example.battery;

import com.example.battery.bo.*;
import com.example.battery.service.SentService;
import com.example.battery.utils.RSAUtils;
import com.example.battery.utils.RsaTest;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BatteryApplicationTests {
    private static String uPublicKey;
    private static String uPrivateKey;
    private static String gPublicKey;
    private static String gPrivateKey;
    private static String pPublicKey;
    private static String pPrivateKey;
    private static List<String> ikList=new ArrayList<>();
    private String idc="idc";
    private String idg="idg";
    private String idr="idr";
    private String m="m";
    @Autowired
    private SentService sentService;
    /**
     * init
     * 初始化回收部门
     */
    static {
        ikList.add("IDK1");

        try {
            Map<String, Object> keyMap = RsaTest.genKeyPair();
            String publicKey = RsaTest.getPublicKey(keyMap);
            String privateKey = RsaTest.getPrivateKey(keyMap);
            uPublicKey = publicKey;
            uPrivateKey = privateKey;

            Integer key2 = (int) (Math.random() * 10000);
            Map<String, String> keys2 = RSAUtils.createKeys(key2);
            gPublicKey = keys2.get("publicKey");
            gPrivateKey = keys2.get("privateKey");

            Integer key3 = (int) (Math.random() * 10000);
            Map<String, String> keys3 = RSAUtils.createKeys(key3);
            pPublicKey = keys3.get("publicKey");
            pPrivateKey = keys3.get("privateKey");
        }catch (Exception e){

        }

    }
   @Test
    public void contextLoads() {

       FirstUserSentGovernment firstUserSentGovernment = new FirstUserSentGovernment();
       long time = System.currentTimeMillis();
       firstUserSentGovernment.setDate(time);
       firstUserSentGovernment.setIdc(idc);
       firstUserSentGovernment.setIdg(idg);
       firstUserSentGovernment.setM(m);
       //M是发票
       //第一步，顾客C把自己的信息IDC，政府信息IDG，时间戳Date和M用C的密钥加密后，发给政府G

       String msg= sentService.GetMsgToG1(firstUserSentGovernment, uPrivateKey);
       //第二步，政府G把第一步收到的信息解密 ，再用政府的公开钥KPG签名，签名后的信息加上IDC  IDG  IDP一起用C的密钥加密，加密后的信息再加上IDC  IDG  IDP和IDK1  IDK2……IDKK，一并发给顾客C，其中K指的是政府回收部门

       SecondSentUser msgToC2 = sentService.getMsgToC2(msg, uPublicKey, gPublicKey, uPrivateKey,ikList);
       //第三步，顾客C把第二步得到的信息解密之后，得到G签名的信息，加上IDC  IDP一起发给机动车检验部门P

       ThirdInfoToP msgToCheck3 = sentService.getMsgToCheck3(uPublicKey, msgToC2);
       // 第四步，机动车检验之后，机动车检验部门P把第三步的签名信息中加入IDP，时间戳Date'，重新用政府G的公开钥进行签名，再用P的私密钥进行签名，加上IDP  IDC发给顾客C。其中重新签名后的信息，是C和R无法伪造的。

       ForthDto forthDto = sentService.addInfo4(msgToCheck3, gPrivateKey, pPublicKey);
       // 第五步，顾客C把第四步得到的签名后的信息，加上IDC  IDR发送给回收单位R

       FifthToR fifthToR = sentService.sentToRByC5(forthDto, idc, idr);
       //第六步，回收单位R把第五步收到的消息验签之后，得到用政府公钥签名的信息，加上IDR  IDC  IDP  IDG和时间戳date"，用R的私密钥签名，再加上IDR  IDG一起发送给政府G

       SixThToG sixThToG = sentService.rSentToG6(fifthToR, idr, idg);
   }

}
