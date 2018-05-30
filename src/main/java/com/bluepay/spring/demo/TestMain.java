package com.bluepay.spring.demo;


import org.apache.commons.codec.binary.BinaryCodec;

import java.io.UnsupportedEncodingException;

public class TestMain {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String ss = "测试";
        byte[] utf8 = ss.getBytes("utf-8");
        System.out.println(arrayToString(utf8) + "    " + new String(utf8));
        byte[] ascii = ss.getBytes("ASCII");
        System.out.println(arrayToString(ascii) + "   " + new String(ascii));
        byte[] iso88591 = ss.getBytes("iso-8859-1");
        System.out.println(arrayToString(iso88591) + "    " + new String(iso88591));
    }

    public static String arrayToString(byte[] array){
        StringBuilder stringBuilder = new StringBuilder(array.length);
        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(array[i] + " ");
        }
        return stringBuilder.toString();
    }

    public static void test() throws UnsupportedEncodingException {
        String ss = "测试";
        String ascii = BinaryCodec.toAsciiString(ss.getBytes());
        System.out.println(ascii);
        System.out.println(new String(BinaryCodec.fromAscii(ascii.getBytes()), "utf-8"));
    }
}
