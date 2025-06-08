package com.example.wallet_management_service.service;

import com.example.wallet_management_service.model.constants.OperationType;
import com.example.wallet_management_service.model.dto.InputData;
import com.example.wallet_management_service.model.entity.Wallet;
import com.example.wallet_management_service.repository.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Сервис обработки запросов.
 */
@Service
@Data
@AllArgsConstructor
public class WalletService {
    private final Util util;

    /**
     * Возвращает баланс кошелька.
     * @param id - номер кошелька.
     * @return - сумма кошелька.
     */
    @Transactional
    public Integer getWalletBalance(UUID id){
        return this.util.getWalletIfPresent(id).getAmount();
    }

    /**
     * Изменяет баланс кошелька.
     * @param data - входные данные для изменения баланса.
     * @return - изменённый кошелёк.
     */
    @Transactional
    public Wallet changeBalance(InputData data){
        Wallet ret = this.util.getWalletIfPresent(data.getValletId());
        Integer walletAmount = ret.getAmount();
        Integer changeAmount = data.getAmount();
        if (data.getOperationType().equals(OperationType.DEPOSIT)) ret.setAmount(walletAmount+changeAmount);
        else ret.setAmount(this.util.reducingIfPossible(walletAmount,changeAmount));
        return ret;
    }
}
