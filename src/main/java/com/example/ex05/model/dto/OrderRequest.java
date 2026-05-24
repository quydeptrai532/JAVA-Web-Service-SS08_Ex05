package com.example.ex05.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OrderRequest {

    // Bẫy định dạng: Yêu cầu chính xác 3 ký tự in hoa
    @NotBlank(message = "Mã chứng khoán không được trống")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Mã chứng khoán phải gồm đúng 3 ký tự in hoa (VD: VNM, FPT)")
    private String stockCode;

    @NotNull(message = "Khối lượng không được trống")
    @Min(value = 100, message = "Khối lượng tối thiểu là 100")
    private Integer quantity;

    @NotNull(message = "Giá không được trống")
    @Positive(message = "Giá phải lớn hơn 0")
    private Double price;

    @NotBlank(message = "Loại lệnh không được trống")
    private String orderType;

    // Bẫy lô giao dịch: Khối lượng phải là bội số của 100
    // Spring Validation sẽ tự động gọi hàm có tiền tố "is" được gắn @AssertTrue
    @JsonIgnore
    @AssertTrue(message = "Khối lượng đặt lệnh phải là bội số của 100 (VD: 100, 200, 1500)")
    public boolean isLotSizeValid() {
        return quantity != null && quantity % 100 == 0;
    }
}