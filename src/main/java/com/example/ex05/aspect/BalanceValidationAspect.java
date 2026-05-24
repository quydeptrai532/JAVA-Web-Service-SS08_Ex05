package com.example.ex05.aspect;

import com.example.ex05.exception.InsufficientFundsException;
import com.example.ex05.model.dto.OrderRequest;
import com.example.ex05.model.entity.AccountBalance;
import com.example.ex05.repository.AccountBalanceRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(3)
public class BalanceValidationAspect {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Before("execution(* com.example.ex05.service.PlaceOrderService.placeOrder(..)) && args(request)")
    public void validateBalance(OrderRequest request) {
        if ("BUY".equalsIgnoreCase(request.getOrderType())) {
            String username = getCurrentUser();
            AccountBalance account = accountBalanceRepository.findById(username)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản."));

            double requiredAmount = request.getQuantity() * request.getPrice();

            if (account.getCashAvailable() < requiredAmount) {
                throw new InsufficientFundsException(
                        String.format("Số dư không đủ. Cần: %.2f, Hiện có: %.2f", requiredAmount, account.getCashAvailable())
                );
            }
        }
    }

    private String getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String user = request.getHeader("X-User");
        if (user == null) {
            throw new RuntimeException("Thiếu Header X-User");
        }
        return user;
    }
}