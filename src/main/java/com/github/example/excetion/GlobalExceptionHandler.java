package com.github.example.excetion;

import com.github.example.contans.ApiResult;
import com.github.example.contans.enums.ApiResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常统一处理
 * RestControllerAdvice与ControllerAdvice的区别
 * 不需要添加ResponseBody即可返回JSON数据
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 默认异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResult> defaultHandle(Exception e){
        String message = e.getMessage();
        log.error("全局异常：{}", message, e);
        return ResponseEntity.ok(ApiResult.builder()
                .code(ApiResultEnum.INTERNAL_ERROR.getCode())
                .message(message)
                .build());
    }

    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ApiResult> businessExceptionHandler(BusinessException e){
        String message = e.getMessage();
        log.error("业务异常：{}", message, e);
        return ResponseEntity.ok(ApiResult.builder()
                .code(e.getCode())
                .message(message)
                .build());
    }

}
