package com.example.springdemo.impl;

import com.example.springdemo.Bean.BookStatusEnum;
import com.example.springdemo.Bean.Response;
import com.example.springdemo.Bean.User;
import com.example.springdemo.service.BookingService;
import com.example.springdemo.util.CommonUtil;
import com.example.springdemo.util.GuavaUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService {
    @Override
    public Response doBooking(long userId) {
        User user = GuavaUtil.getUser(userId);
        if (user == null) {
            return Response.getErrorResponse(userId + "用户不存在");
        }
        Map<Long, String> map = GuavaUtil.getCoach();
        if (map.isEmpty()) {
            return Response.getErrorResponse("没有可预约的老师");
        }
        Response res = Response.getSuccessResponse("预约成功");
        Long coachId = map.keySet().stream().findFirst().get();
        String values = map.values().stream().findFirst().get();
        String url = String.format(CommonUtil.bookingUrl, userId) + "-" + coachId;
        res.coachName = coachId.toString();
        res.bookingStatus = BookStatusEnum.PENDING.getDesc();
        res.userId = userId;
        res.bookingUrl = Arrays.asList(url);
        res.email = user.email;
        res.bookingStartTime = values.split("_")[0];
        res.bookingEndTime = values.split("_")[1];
        // 更新记录
        user.bookingStatus = BookStatusEnum.PENDING.getCode();
        user.bookingUrl = url;
        GuavaUtil.putCache(Arrays.asList(user));
        return res;
    }

    @Override
    public Response getBookingUrl(long userId) {
        User user = GuavaUtil.getUser(userId);
        Response res = Response.getSuccessResponse("查询成功");
        res.userId = userId;
        res.bookingStatus = BookStatusEnum.getByCode(user.bookingStatus).getDesc();
        res.bookingUrl = Arrays.asList(user.bookingUrl);
        return res;
    }

    @Override
    public Response cancelBooking(long userId) {
        User user = GuavaUtil.getUser(userId);
        if (user == null) {
            return Response.getErrorResponse(userId + "用户不存在");
        }
        user.bookingUrl = "";
        user.bookingStatus = BookStatusEnum.BOOKING_CANCELLED.getCode();
        GuavaUtil.putCache(Arrays.asList(user));
        return Response.getSuccessResponse("操作成功");
    }

    @Override
    public Response receiveCal(long userId) {
        User user = GuavaUtil.getUser(userId);
        user.bookingStatus = BookStatusEnum.BOOKING_CREATED.getCode();
        return Response.getSuccessResponse("操作成功");
    }


}
