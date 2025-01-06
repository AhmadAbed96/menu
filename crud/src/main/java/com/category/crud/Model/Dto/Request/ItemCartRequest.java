package com.category.crud.Model.Dto.Request;

import com.category.crud.Validation.annotation.PricePositive;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCartRequest {
    @NotBlank
    private String ItemId;
    @NotBlank
    private String ItemName;
    @PricePositive
    private double price;

    @PricePositive
    private int quantity;
}
