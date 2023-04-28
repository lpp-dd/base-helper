package org.example.base.helper.controller;

import org.example.base.helper.logic.HelperLogic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author panfudong
 * @description
 */
@RestController
@RequestMapping
public class HelperController {

    @Resource
    private HelperLogic helperLogic;

    @GetMapping("/redisLockTest")
    public String redisLockTest(Long id) {
        helperLogic.redisLockTest(id);
        return "OK";
    }

    @GetMapping("/redissionLockTest")
    public String redissionLockTest(Long id) {
        helperLogic.redissionLockTest(id);
        return "OK";
    }
}
