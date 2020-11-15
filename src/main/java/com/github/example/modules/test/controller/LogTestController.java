package com.github.example.modules.test.controller;

import com.github.example.annotation.OperLog;
import com.github.example.contans.ApiResult;
import com.github.example.excetion.BusinessException;
import com.github.example.modules.base.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试记录日志操作
 *
 * @author: Mr.lai
 * @date: 2020/11/8 11:13
 */
@RequestMapping("/test/log")
@RestController
public class LogTestController extends BaseController {

    /**
     * 正常记录日志
     *
     * @return
     */
    @OperLog(module = "记录操作日志", name = "log", desc = "测试操作日志")
    @GetMapping("/aopLog")
    public ResponseEntity<ApiResult> aopLog() {
        return ResponseEntity.ok(success("aopLog"));
    }

    /**
     * 记录异常日志
     *
     * @return
     */
    @OperLog(module = "记录操作日志", name = "error", desc = "测试异常日志")
    @GetMapping("/aopError")
    public ResponseEntity<ApiResult> aopError() {
        try {
            int value = 1 / 0;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return ResponseEntity.ok(success("aopError"));
    }

}
