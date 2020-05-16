package com.example.battery.service.impl;

import com.example.battery.bo.*;
import com.example.battery.service.SentService;
import com.example.battery.utils.DataProcessUtils;
import com.example.battery.utils.JsonUtils;
import com.example.battery.utils.RSAUtils;
import com.example.battery.utils.RsaTest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class SentServiceImpl implements SentService {

    //M是发票
    //第一步，顾客C把自己的信息IDC，政府信息IDG，时间戳Date和M用C的密钥加密后，发给政府G
    @SneakyThrows
    @Override
    public String GetMsgToG1(FirstUserSentGovernment firstUserSentGovernment,String privateKey) {
        log.info("第一步,{}", firstUserSentGovernment);
        String objectToJson = JsonUtils.objectToJson(firstUserSentGovernment);
        byte[] stringToBytes = DataProcessUtils.stringToBytes(objectToJson);
        byte[] bytes = RsaTest.decryptByPrivateKey(stringToBytes, privateKey);
        return new String(bytes, "utf-8");
    }
    //第二步，政府G把第一步收到的信息解密 ，
    // 再用政府的公开钥KPG签名，
    // 签名后的信息加上IDC  IDG  IDP一起用C的密钥加密，
    // 加密后的信息再加上IDC  IDG  IDP和IDK1  IDK2……IDKK，
    // 一并发给顾客C，其中K指的是政府回收部门
    @SneakyThrows
    @Override
    public SecondSentUser getMsgToC2(String msg, String upk,String gpc,String usk,List<String> ikList) {
        log.info("第二步 ,{},",msg,upk,gpc,usk);
        byte[] bytes = RsaTest.encryptByPublicKey(msg.getBytes(), upk);
        FirstUserSentGovernment firstUserSentGovernment = JsonUtils.jsonToPojo(new String(bytes), FirstUserSentGovernment.class);
        //再用政府的公开钥KPG签名，
        SingWithGSign singWithGSign=new SingWithGSign();
        BeanUtils.copyProperties(firstUserSentGovernment,singWithGSign);
        singWithGSign.setGpk(gpc);
        // 签名后的信息加上IDC  IDG  IDP一起用C的密钥加密，
        RSAPrivateKey privateKey = RSAUtils.getPrivateKey(usk);
        String objectToJson = JsonUtils.objectToJson(singWithGSign);
        String encrypt = RSAUtils.privateEncrypt(objectToJson, privateKey);
        //加密后的信息再加上IDC  IDG  IDP和IDK1  IDK2……IDKK，
        SecondSentUser secondSentUser=new SecondSentUser();
        BeanUtils.copyProperties(firstUserSentGovernment,secondSentUser);
        secondSentUser.setMsg(encrypt);
        secondSentUser.setIdks(ikList);
        return secondSentUser;
    }
    //第三步，顾客C把第二步得到的信息解密之后，
    //得到G签名的信息，加上IDC  IDP一起发给机动车检验部门P
    @SneakyThrows
    @Override
    public ThirdInfoToP getMsgToCheck3(String upc, SecondSentUser secondSentUser) {
        log.info("第三步 ,{},{}",upc,secondSentUser);
        String msg = secondSentUser.getMsg();
        RSAPrivateKey privateKey = RSAUtils.getPrivateKey(upc);
        String privateDecrypt = RSAUtils.privateDecrypt(msg, privateKey);
        ThirdInfoToP thirdInfoToP=new ThirdInfoToP();
        BeanUtils.copyProperties(secondSentUser,thirdInfoToP);
        thirdInfoToP.setMsg(privateDecrypt);
        return thirdInfoToP;
    }
    // 第四步，机动车检验之后，机动车检验部门P把第三步的签名信息中加入IDP，
    // 时间戳Date'，重新用政府G的公开钥进行签名，
    // 再用P的私密钥进行签名，加上IDP  IDC发给顾客C。其中重新签名后的信息，是C和R无法伪造的。

    @Override
    public ForthDto addInfo4(ThirdInfoToP thirdInfoToP,String gpk,String ppk) {
        log.info("第四步 {},{},{}",thirdInfoToP,gpk,ppk);
        ForthDto forthDto=new ForthDto();
        BeanUtils.copyProperties(thirdInfoToP,forthDto);
        forthDto.setDate(System.currentTimeMillis());
        forthDto.setGpk(gpk);
        forthDto.setPk(ppk);
        return forthDto;
    }
    // 第五步，顾客C把第四步得到的签名后的信息，加上IDC  IDR发送给回收单位R
    @Override
    public FifthToR sentToRByC5(ForthDto forthDto,String idc,String idr) {
        log.info("第五步 {},{},{}",forthDto,idc,idr);
        FifthToR fifthToR=new FifthToR();
        fifthToR.setForthDto(forthDto);
        fifthToR.setIdc(idc);
        fifthToR.setIdr(idr);
        return fifthToR;
    }

    @Override
    public SixThToG rSentToG6(FifthToR fifthToR,String idr,String idg) {
        log.info("第六部,{},{},{}",fifthToR,idr,idg);
        SixThToG sixThToG=new SixThToG();
        sixThToG.setMsg(UUID.randomUUID().toString());
        sixThToG.setIdg(idg);
        sixThToG.setIdr(idr);
        return sixThToG;
    }

    /**
     * Map<String, String> keyPairMap = new HashMap<String, String>();
     * keyPairMap.put("publicKey", publicKeyStr);
     * keyPairMap.put("privateKey", privateKeyStr);
     *
     * @param args
     */
    public static void main(String[] args) {
//        Integer key = (int) (Math.random() * 10000);
//        Map<String, String> keys = RSAUtils.createKeys(key);
//        publicKey = keys.get("publicKey");
//        privateKey = keys.get("privateKey");
//
//
//        FirstUserSentGovernment firstUserSentGovernment = new FirstUserSentGovernment();
//        long time = System.currentTimeMillis();
//        firstUserSentGovernment.setDate(time);
//        firstUserSentGovernment.setIdc("Idc");
//        firstUserSentGovernment.setM("idG");
//        firstUserSentGovernment.setM("m");

    }
}
