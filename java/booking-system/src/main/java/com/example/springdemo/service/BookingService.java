package com.example.springdemo.service;

import com.example.springdemo.Bean.Response;

public interface BookingService {

    Response doBooking(long userId);

    Response getBookingUrl(long userId);

    Response cancelBooking(long userId);

    Response receiveCal(long userId);
}
