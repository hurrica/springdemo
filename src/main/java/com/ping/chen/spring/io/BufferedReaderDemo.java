package com.ping.chen.spring.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ping.chen on 2018/8/9.
 */
public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
            while (true){
                System.out.println("Enter a String:");
                String value = reader.readLine();
                System.out.println("your string is:" + value);
            }
        } finally {
            if (reader != null){
                reader.close();
            }
        }
    }
}
