package com.example.springdemo.Bean;

public enum BookStatusEnum {
    // 初始状态
    PENDING(1, "初始状态"),
    // 支付成功且预约确认，等待开课
    BOOKING_CREATED(2, "支付成功且预约确认(Active)，等待开课"),
    // 预约已取消
    BOOKING_CANCELLED(3, "预约已取消"),
    // 课程正常结束
    MEETING_ENDED(4, "课程正常结束"),
    // 用户或导师未出席
    NO_SHOW(5, "用户或导师未出席");

    // 状态编码
    private final Integer code;
    // 状态描述
    private final String desc;

    // 构造方法
    BookStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // 获取编码
    public Integer getCode() {
        return code;
    }

    // 获取描述
    public String getDesc() {
        return desc;
    }

    // 根据code反向获取枚举实例
    public static BookStatusEnum getByCode(Integer code) {
        for (BookStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
