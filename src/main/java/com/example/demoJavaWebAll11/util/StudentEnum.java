package com.example.demoJavaWebAll11.util;

//1 表示在读, 2 休学， 3 退学， 4 删除
public enum StudentEnum {

    // 快捷键 Ctrl + Shift + U 快速变成大小写
    // 注意：这里用 “,” 隔开所有的枚举值
    READING(1, "在读"),
    SUSPENSION(2, "休学"),
    DROPOUT(3, "退学"),
    DELETE(4, "删除");

    public final Integer type;
    public final String value;

    StudentEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
