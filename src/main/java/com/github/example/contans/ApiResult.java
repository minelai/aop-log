package com.github.example.contans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 接口返回结果集
 * @author: Mr.lai
 * @date: 2020/10/10 18:07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

}
