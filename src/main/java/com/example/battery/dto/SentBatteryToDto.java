package com.example.battery.dto;

import lombok.Data;

/**
 * 要写注释呀
 * 电池和发票
 */
@Data
public class SentBatteryToDto {
    private String privateKey;
    private String publicKey;
    private String batteryKey;
}

