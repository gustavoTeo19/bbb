package com.example.barberboost.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class LoyaltyPlanDto {
    @NotNull
    private Double discount;
    @NotNull
    private int necessaryAmount;


}
