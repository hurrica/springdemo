package com.ping.chen.spring.mybatis.demo;


import java.util.Arrays;
import java.util.Optional;

public interface BaseEnum<T>{

    T getDictKey();

    default T getValue() {
        return this.getDictKey();
    }

    static <T> BaseEnum valueOfEnum(Class<BaseEnum> enumClass, T value){
        if (value == null){
            return null;
        }
        BaseEnum[] enums = enumClass.getEnumConstants();
        Optional<BaseEnum> optional = Arrays.asList(enums).stream().filter(baseEnum -> baseEnum.getDictKey().equals(value)).findAny();
        if (optional.isPresent()){
            return optional.get();
        }
        throw new RuntimeException("未找到：" + value + "对应的" + enumClass.getName());
    }
}
