package com.example.battery.handler.impl;

import com.example.battery.cache.Caches;
import com.example.battery.constants.Constants;
import com.example.battery.constants.GroupIds;
import com.example.battery.constants.Topics;
import com.example.battery.dto.SentBatteryToDto;
import com.example.battery.handler.SentToHandler;
import com.example.battery.mq.KafkaMessageProducer;
import com.example.battery.pojo.AuditUserAndKey;
import com.example.battery.pojo.IntegerResultResponse;
import com.example.battery.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 要写注释呀
 */
public class SentHandlerImpl implements SentToHandler {
    @Autowired
    private KafkaMessageProducer producer;
    @Override
    public IntegerResultResponse sentTo(SentBatteryToDto sentBatteryToDto) {
//        IntegerResultResponse result=new IntegerResultResponse(0);
//        //顾客将自己的东西发到审批部门P，检验成功
//        boolean checkByP = checkByP();
//        if (checkByP==false){
//            result.setResult(Constants.FAIL);
//            return result;
//        }else {
//            result.setResult(Constants.SUCCESS);
//        }
//
//        //顾客C然后到回收单位R处提交发票信息和K
//        //audit();
//        //R回收后将发票信息提交政府
//        producer.sent(Topics.SENT_KEY_TO_GOVERNMENT, GroupIds.SENT_KEY_TO_GOVERNMENT);
//        return result;n
        return null;
    }

    private void audit(String key, String user){
        AuditUserAndKey auditUserAndKey=new AuditUserAndKey();
        Caches.USER_AND_AUDIT_KEY.put(user,auditUserAndKey);
    }

    /**
     * 审计部门审计
     * @return
     */
    private Boolean checkByP(){
        boolean end= false;
        //审计部门审计，审计部门审计成功后将信息提交给政府和用户
        String sentToGovernmentKey="";
        //把密钥发给政府
        producer.sent(Topics.SENT_KEY_TO_GOVERNMENT, sentToGovernmentKey);
        //发送给user密钥
        producer.sent(Topics.R_MSG_TO_USER,GroupIds.R_MSG_TO_USER);
        return end;
    }


}
