package com.example.ex05.controller;

import com.example.ex05.model.dto.OrderRequest;
import com.example.ex05.model.entity.StockOrder;
import com.example.ex05.service.PlaceOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private PlaceOrderService placeOrderService;

    @PostMapping("/place")
    public ResponseEntity<StockOrder> placeOrder(
            @RequestHeader("X-User") String user,
            @Valid @RequestBody OrderRequest request) {

        // Không cần try-catch nhờ vào GlobalExceptionHandler
        StockOrder order = placeOrderService.placeOrder(request);
        return ResponseEntity.ok(order);
    }
}