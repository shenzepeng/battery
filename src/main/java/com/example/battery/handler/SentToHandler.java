package com.example.battery.handler;

import com.example.battery.dto.SentBatteryToDto;
import com.example.battery.pojo.IntegerResultResponse;

/**
 * 要写注释呀
 */
public interface SentToHandler {
    IntegerResultResponse sentTo(SentBatteryToDto sentBatteryToDto);
}
