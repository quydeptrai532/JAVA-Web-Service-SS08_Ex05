package com.example.ex05.service;

import com.example.ex05.exception.MarginViolationException;
import com.example.ex05.model.dto.OrderRequest;
import com.example.ex05.model.entity.StockOrder;
import com.example.ex05.repository.StockOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlaceOrderService {

    @Autowired
    private StockOrderRepository stockOrderRepository;

    private static final double REFERENCE_PRICE = 100.0;

    @Transactional
    public StockOrder placeOrder(OrderRequest request) {
        // Logic nghiệp vụ lõi: Kiểm tra biên độ giá (+/- 7%)
        double priceDeviation = Math.abs(request.getPrice() - REFERENCE_PRICE) / REFERENCE_PRICE;

        if (priceDeviation > 0.07) {
            throw new MarginViolationException("Mức giá đặt lệnh vượt quá biên độ +/- 7% so với giá tham chiếu (100.0).");
        }

        // Lưu lệnh vào Database nếu mọi thứ hợp lệ
        StockOrder order = new StockOrder();
        order.setStockCode(request.getStockCode());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setOrderType(request.getOrderType());

        return stockOrderRepository.save(order);
    }
}