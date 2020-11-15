package com.github.example.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mr.lai
 * @date: 2020/11/8 11:03
 */
public class RequestUtil {


    /**
     * 获取登录用户的IP地址
     *
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1"))
        {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1)
        {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 获取请求参数
     * @param request
     * @return
     */
    public static String getParams(HttpServletRequest request){
        String method = request.getMethod();
        // GET请求
        if(HttpMethod.GET.toString().equals(method)){
            // 获取参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, String> coverMap = converMap(parameterMap);
            // 参数转换成json
            String params = coverMap.size() != 0 ? JSON.toJSONString(coverMap) : "";
            return params;
        }
        // OTHER
        RequestWrapperUtil wrapper = new RequestWrapperUtil(request);
        return wrapper.getData();
    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public static Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

}
