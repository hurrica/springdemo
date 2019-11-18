package com.bluepay.spring.test;

import java.io.IOException;
import java.util.*;

/**
 * @Author chenping
 * @Description test
 * @Date 2019/3/11
 **/
public class Test {
    private String field;

    public static void main(String[] args) throws IOException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("ss");
        list.forEach(e -> check(e));
        System.out.println("test");
    }

    private static boolean check(String e) {
        if (e.equals("ss")){
            throw new RuntimeException("错误");
        }
        return true;
    }

}
