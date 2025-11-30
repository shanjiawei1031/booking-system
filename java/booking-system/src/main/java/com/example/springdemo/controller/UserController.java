package com.example.springdemo.controller;

import com.example.springdemo.Bean.BookStatusEnum;
import com.example.springdemo.Bean.Response;
import com.example.springdemo.Bean.User;
import com.example.springdemo.util.GuavaUtil;
import com.fasterxml.jackson.databind.type.MapType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/user/add")
    public Response addUser(@RequestParam long userId, @RequestParam String email) {
        User user = new User();
        user.userId = userId;
        user.email = email;
        user.bookingStatus = 1;
        user.bookingUrl = "";
        GuavaUtil.putCache(Arrays.asList(user));
        return Response.getSuccessResponse("操作成功");
    }

    @GetMapping("/user/show")
    public Response showUser(@RequestParam long userId) {
        User user = GuavaUtil.getUser(userId);
        if (user == null) {
            return Response.getErrorResponse(userId + "用户不存在");
        }
        Response response = new Response();
        response.userId = user.userId;
        response.bookingUrl = Arrays.asList(user.bookingUrl);
        response.email = user.email;
        response.bookingStatus = BookStatusEnum.getByCode(user.bookingStatus).getDesc();
        return response;
    }

    @PostMapping("/coach/add")
    public Response addCoach(@RequestParam long coach, @RequestParam int start, @RequestParam int end) {
        Response res = Response.getSuccessResponse("添加成功");
        GuavaUtil.addCoach(coach, start, end);
        return res;
    }

    @GetMapping("/coach/get")
    public Response getCoach() {
        Response res = Response.getSuccessResponse("成功");
        Map<Long, String> map = GuavaUtil.getCoach();
        res.coachName = map.keySet().toString();
        return res;
    }
}
