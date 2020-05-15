package com.example.battery.mq;

import com.example.battery.constants.GroupIds;
import com.example.battery.constants.Topics;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * kafka消费者测试
 */
@Slf4j
@Component
public class KafkaMessageListener {

    @KafkaListener(topics = Topics.R_MSG_TO_USER,groupId = GroupIds.R_MSG_TO_USER)
    public void rMsgToUser(ConsumerRecord<?, ?> record) throws Exception {
        //控制台打印send进来的信息
        log.info(" topic {} ,offset {} ,value {}", record.topic(), record.offset(), record.value());
    }
    @KafkaListener(topics = Topics.SENT_KEY_TO_GOVERNMENT,groupId = GroupIds.SENT_KEY_TO_GOVERNMENT)
    public void sentKeyToGovernment(ConsumerRecord<?, ?> record) throws Exception {
        //控制台打印send进来的信息
        log.info(" topic {} ,offset {} ,value {}", record.topic(), record.offset(), record.value());
    }
    @KafkaListener(topics = Topics.SENT_KEY_TO_P,groupId = GroupIds.SENT_KEY_TO_P)
    public void sentKeyToP(ConsumerRecord<?, ?> record) throws Exception {
        //控制台打印send进来的信息
        log.info(" topic {} ,offset {} ,value {}", record.topic(), record.offset(), record.value());
    }
    @KafkaListener(topics = Topics.SENT_MSG_TO_LANDFILL,groupId = GroupIds.SENT_MSG_TO_LANDFILL)
    public void sentMsgToLdnFill(ConsumerRecord<?, ?> record) throws Exception {
        //控制台打印send进来的信息
        log.info(" topic {} ,offset {} ,value {}", record.topic(), record.offset(), record.value());
    }

}
