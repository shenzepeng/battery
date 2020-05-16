package com.example.battery.api;

import com.example.battery.cache.Caches;
import com.example.battery.config.SzpJsonResult;
import com.example.battery.dto.SentBatteryToDto;
import com.example.battery.handler.SentToHandler;
import com.example.battery.pojo.IntegerResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
public class ApiController {
//    @Autowired
//    private SentToHandler sentToHandler;
//    /**
//     * 将电池送到出
//     */
//    @PostMapping("sent")
//    public SzpJsonResult<IntegerResultResponse> sentToBattery(SentBatteryToDto batteryToDto){
//        return SzpJsonResult.ok(sentToHandler.sentTo(batteryToDto));
//    }
//
//    /**
//     * 用户查询信息
//     * @return
//     */
//    @GetMapping
//    public SzpJsonResult<String> getUserInfo(String key){
//        return SzpJsonResult.ok(Caches.USER_AND_KEY.get(key));
//    }
}
