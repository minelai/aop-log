package com.github.example.modules.base.controller;

import com.github.example.contans.ApiResult;
import com.github.example.contans.enums.ApiResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: Mr.ali
 * @date: 2020/10/20 22:19
 */
@Slf4j
@RestController
public class BaseController<T> {

    private static ApiResult result = new ApiResult();

    @RequestMapping("/")
    public ResponseEntity<ApiResult> baseRoot() {
        return ResponseEntity.ok(success());
    }

    /**
     * 获取 Request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

    /**
     * 获取response
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return resp;
    }

    /**
     * 获取 Session
     *
     * @return HttpSession
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public ApiResult error() {
        result.setCode(ApiResultEnum.ERROR.getCode());
        result.setMessage(ApiResultEnum.ERROR.getMessage());
        return result;
    }

    public ApiResult error(String message) {
        result.setCode(ApiResultEnum.ERROR.getCode());
        result.setMessage(message);
        return result;
    }

    public ApiResult error(Throwable throwable) {
        return error(throwable.getMessage());
    }

    public ApiResult error(ApiResultEnum resultEnum) {
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    public ApiResult success() {
        result.setCode(ApiResultEnum.SUCCESS.getCode());
        result.setMessage(ApiResultEnum.SUCCESS.getMessage());
        return result;
    }

    public ApiResult success(String message) {
        result.setCode(ApiResultEnum.SUCCESS.getCode());
        result.setMessage(message);
        return result;
    }

    public ApiResult success(T data) {
        result.setCode(ApiResultEnum.SUCCESS.getCode());
        result.setMessage(ApiResultEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public ApiResult success(String message, T data) {
        result.setCode(ApiResultEnum.SUCCESS.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
