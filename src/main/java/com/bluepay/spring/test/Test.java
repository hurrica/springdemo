package com.bluepay.spring.test;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenping
 * @Description test
 * @Date 2019/3/11
 **/
public class Test {
    private String field;

    public static void main(String[] args) throws IOException, IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        map.put("123", "1455");
        List<String> values = (List<String>) map.values();
        System.out.println(map.values().getClass());
    }

}
