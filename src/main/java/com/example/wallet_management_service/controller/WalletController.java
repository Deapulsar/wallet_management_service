package com.example.wallet_management_service.controller;

import com.example.wallet_management_service.model.dto.InputData;
import com.example.wallet_management_service.model.entity.Wallet;
import com.example.wallet_management_service.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Компонент WalletController обрабатывает запросы на основные точки взаимодействия с сервисом.
 */
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class WalletController {
    private final WalletService walletService;

    /**
     * Возвращает баланс кошелька.
     * @param WALLET_UUID - UUID кошелька.
     * @return - сумма кошелька.
     * */
    @GetMapping("/wallets/{WALLET_UUID}")
    public Integer getBalance(@PathVariable UUID WALLET_UUID) {
        return this.walletService.getWalletBalance(WALLET_UUID);
    }

    /**
     * Изменет баланс указанного кошелька, если это возможно.
     * @param data - входные данные в формате JSON для изменения баланса.
     * @return - возвращает данные для кошелька(с изменённым балансом) в формате JSON.
     */
    @PostMapping("/wallet")
    @Transactional
    public Wallet postWallet(@Valid @RequestBody InputData data) {
        return this.walletService.changeBalance(data);
    }
}
