package com.example.battery.handler.impl;

import com.example.battery.cache.Cahces;
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
        //顾客申请报废前，到机动车检验部门P处申请查验
        //P审验合格发给顾客C一个密钥K，且一并发给政府G
        producer.sent(Topics.SENT_KEY_TO_P, JsonUtils.objectToJson(sentBatteryToDto));
        //顾客C然后到回收单位R处提交发票信息和K
        audit()
        //R回收后将发票信息提交政府
        producer.sent(Topics.SENT_KEY_TO_GOVERNMENT, GroupIds.SENT_KEY_TO_GOVERNMENT);
        return null;
    }

    private void audit(String key, String user){
        AuditUserAndKey auditUserAndKey=new AuditUserAndKey();
        Cahces.USER_AND_KEY.add(auditUserAndKey);
    }


}
