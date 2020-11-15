package com.github.example.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 获取请求中的Body参数
 * @author: Mr.lai
 * @date: 2020/11/7 19:31
 * 获取application/x-www-form-urlencoded类型的参数中文无法正常显示
 */
@Slf4j
@Getter
public class RequestWrapperUtil extends HttpServletRequestWrapper {

    /**
     * body数据
     */
    private String data;

    public RequestWrapperUtil(HttpServletRequest request) {
        super(request);

        StringBuilder builder = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
            inputStream = request.getInputStream();
            if(!ObjectUtils.isEmpty(inputStream)){
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String row = "";
                while ((row = bufferedReader.readLine()) != null){
                    builder.append(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(!ObjectUtils.isEmpty(inputStream)){
                    inputStream.close();
                }
                if(!ObjectUtils.isEmpty(bufferedReader)){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        data = builder.toString();
    }

}