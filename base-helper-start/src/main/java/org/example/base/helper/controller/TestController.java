package org.example.base.helper.controller;

import org.example.base.helper.logic.TestLogic;
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
public class TestController {

    @Resource
    private TestLogic testLogic;

    @GetMapping("/test")
    public String test() {
        return testLogic.test();
    }
}
