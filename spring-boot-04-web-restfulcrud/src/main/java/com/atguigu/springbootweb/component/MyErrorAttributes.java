package com.atguigu.springbootweb.component;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by JHW on 2021/8/26.
 */

//给容器中加入我们自己定义的ErrorAttributes,将我们的定制数据携带出去
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        // 添加自定义的错误信息到指定的错误页面
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.put("company","atguigu");

        // 自己的异常处理器携带的数据
        Map<String, Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);// 0: 表示从request中取
        map.put("ext",ext);
        return map;
    }

}
