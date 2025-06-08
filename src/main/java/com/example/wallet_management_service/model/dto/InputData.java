package com.example.wallet_management_service.model.dto;

import com.example.wallet_management_service.model.constants.OperationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Модель входных данных для изменения баланса кошелька.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputData {
    @NotNull
    private UUID valletId;
    @NotNull
    private OperationType operationType;
    @NotNull
    @Min(value = 1)
    private Integer amount;
}
