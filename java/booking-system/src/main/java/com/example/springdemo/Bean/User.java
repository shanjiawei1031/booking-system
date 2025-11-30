package com.example.springdemo.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    public long userId;  //用户名

    public String bookingUrl; //订阅地址

    public int bookingStatus; //订阅状态

    public String email;  //邮箱
}
