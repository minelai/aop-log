package com.github.example.contans.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一状态码
 * @author: Mr.lai
 * @date: 2020/10/10 18:12
 */
@Getter
@AllArgsConstructor
public enum ApiResultEnum {

    ERROR(1, "ERROR"),
    SUCCESS(200, "SUCCESS"),
    ERROR_404(404, "路径不存在"),
    INTERNAL_ERROR(500, "网络异常"),
    ;

    private Integer code;

    private String message;
}
