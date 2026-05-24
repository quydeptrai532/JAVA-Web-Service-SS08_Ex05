package com.example.ex05.aspect;

import com.example.ex05.model.dto.OrderRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.ex05.service.PlaceOrderService.placeOrder(..)) && args(request)")
    public void logIncomingOrder(OrderRequest request) {
        logger.info("[ORDER(1) - LOGGING] Nhận lệnh {} mã {} khối lượng {} với giá {}",
                request.getOrderType(), request.getStockCode(), request.getQuantity(), request.getPrice());
    }
}