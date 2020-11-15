package com.github.example.modules.test.controller;

import com.github.example.modules.base.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr.lai
 * @date: 2020/11/8 9:52
 */
@RestController
@RequestMapping("/test")
public class HelloController extends BaseController {

    @GetMapping("hello")
    public ResponseEntity helloTest(){
        return ResponseEntity.ok(success("SpringBoot 测试"));

    }
}
