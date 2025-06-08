package com.example.wallet_management_service.service;

import com.example.wallet_management_service.exception.InsufficientFundsException;
import com.example.wallet_management_service.exception.WalletNotFoundException;
import com.example.wallet_management_service.model.entity.Wallet;
import com.example.wallet_management_service.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class Util {
    private final WalletRepository walletRepository;

    public Wallet getWalletIfPresent(UUID id){
        Optional<Wallet> ret = this.walletRepository.findById(id);
        if (ret.isPresent()) return ret.get();
        else throw new WalletNotFoundException(id.toString());
    }

    public Integer reducingIfPossible(Integer amount, Integer change){
        int result = amount - change;
        if (result>=0) return result;
        else throw  new InsufficientFundsException(amount.toString());
    }

}
