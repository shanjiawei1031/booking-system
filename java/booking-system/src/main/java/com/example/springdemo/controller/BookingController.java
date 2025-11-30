package com.example.springdemo.controller;

import com.example.springdemo.Bean.Response;
import com.example.springdemo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/booking-url")
    public Response doBooking(@RequestParam long userId) {
        return bookingService.doBooking(userId);
    }


    @GetMapping("/bookings")
    public Response getBookingUrl(@RequestParam long userId) {
        return bookingService.getBookingUrl(userId);
    }

    @PostMapping("/bookings/cancel")
    public Response cancelBooking(@RequestParam long userId) {
        return bookingService.cancelBooking(userId);
    }

    @PostMapping("/webhook/cal")
    public Response receiveCal(@RequestParam long userId) {
        return bookingService.receiveCal(userId);
    }

}
