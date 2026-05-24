package com.example.ex05.aspect;

import com.example.ex05.exception.MarketClosedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
@Order(2)
public class TradingTimeAspect {

    @Before("execution(* com.example.ex05.service.PlaceOrderService.placeOrder(..))")
    public void checkTradingTime() {
        LocalTime now = LocalTime.now();
        LocalTime marketOpen = LocalTime.of(9, 0);
        LocalTime marketClose = LocalTime.of(15, 0);

        if (now.isBefore(marketOpen) || now.isAfter(marketClose)) {
            throw new MarketClosedException("Sàn giao dịch đã đóng cửa. Khung giờ giao dịch: 09:00 - 15:00.");
        }
    }
}