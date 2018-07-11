package com.yly.testboot.controller;

import com.yly.testboot.entity.GoodInfoEntity;
import com.yly.testboot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quartz")
public class QuartzController {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/savegood")
    public Long saveGood(@RequestBody GoodInfoEntity good) throws Exception {
        return goodsService.saveGood(good);
    }
}
