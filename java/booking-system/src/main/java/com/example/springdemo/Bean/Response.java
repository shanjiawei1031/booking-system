package com.example.springdemo.Bean;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class Response implements Serializable {

    public int code;
    public String msg;

    public List<String> bookingUrl;
    public String bookingStatus;
    public long userId;
    public String email;

    public String coachName;
    public String bookingStartTime;
    public String bookingEndTime;

    public Response() {
        code = 200;
        msg = "";
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static Response getErrorResponse(String msg) {
        Response res = new Response();
        res.setCode(10001);
        res.setMsg(msg);
        return res;
    }

    public static Response getSuccessResponse(String msg) {
        Response res = new Response();
        res.setCode(200);
        res.setMsg(msg);
        return res;
    }





}
