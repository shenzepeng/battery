package com.example.battery.api;

import com.example.battery.config.SzpJsonResult;
import com.example.battery.dto.SentBatteryToDto;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
public class ApiController {
    /**
     * 将电池送到图书馆
     */
    public SzpJsonResult<String> sentToBattery(SentBatteryToDto batteryToDto){

    }

    /**
     * 用户查询信息
     * @return
     */
    public SzpJsonResult<String> getUserInfo(){

    }
}
