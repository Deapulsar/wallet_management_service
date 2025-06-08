package com.example.wallet_management_service.controller;

import com.example.wallet_management_service.model.dto.InputData;
import com.example.wallet_management_service.model.entity.Wallet;
import com.example.wallet_management_service.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/wallets/{WALLET_UUID}")
    public Integer getBalance(@PathVariable UUID WALLET_UUID) {
        return this.walletService.getWalletBalance(WALLET_UUID);
    }

    @PostMapping("/wallet")
    @Transactional
    public Wallet postWallet(@Valid @RequestBody InputData data) {
        return this.walletService.changeBalance(data);
    }
}
