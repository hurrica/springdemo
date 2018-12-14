package com.bluepay.spring.mybatis.demo;

public interface BaseEnum<E, T> {
    T getDickKey();
    BaseEnum<E, T> toBaseEnum(T t);
}
