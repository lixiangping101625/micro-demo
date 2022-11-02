package com.hlkj.sharedpojo.enums;

import java.util.stream.Stream;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.enumerate
 * @ClassName: LoginType
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 17:19
 * @Version: 1.0
 */
public enum SexEnum {
    FEMALE(0,"女"),
    MALE(1,"男"),
    UNKOWN(2,"未知");

    private Integer code;
    private String desc;

    SexEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }
    public Integer getCode(){
        return this.code;
    }

    /**
     * 将code转换为枚举
     * @param code
     * @return
     */
    public static SexEnum toType(int code){
        return Stream.of(SexEnum.values())
                .filter(c -> c.code==code)
                .findAny()
                .orElse(null);
    }
}
