package com.example.springdemo.util;

import com.example.springdemo.Bean.User;
import com.google.common.cache.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class GuavaUtil {

    public static Cache<Long, Object> userCache;
    public static Cache<Long, String> coachCache;

    static {
        userCache = CacheBuilder.newBuilder()
                .maximumSize(100)    // 容量
                .expireAfterWrite(24, TimeUnit.HOURS) //超时时间
                .build();
        coachCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(24, TimeUnit.HOURS)
                .build();
    }

    public static void putCache(List<User> userList) {
        for (User user : userList) {
            userCache.put(user.userId, user);
        }
    }

    public static void addCoach(long coachId, int start, int end) {
        coachCache.put(coachId, start + "_" + end);
    }

    public static Map<Long, String> getCoach() {
        ConcurrentMap<Long, String> map = coachCache.asMap();
        return map;
    }

    public static User getUser(long userId) {
        return (User) userCache.getIfPresent(userId);
    }


}
