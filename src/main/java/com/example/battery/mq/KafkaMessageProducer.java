package com.example.battery.mq;


import com.example.battery.pojo.IntegerResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final IntegerResultResponse INTEGER_RESULT_RESPONSE=new IntegerResultResponse(1);
    public IntegerResultResponse sent(String topic, String msg){
        kafkaTemplate.send(topic,msg);
        return INTEGER_RESULT_RESPONSE;
    }
}
