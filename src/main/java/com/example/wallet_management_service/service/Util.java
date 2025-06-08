package com.example.wallet_management_service.service;

import com.example.wallet_management_service.exception.InsufficientFundsException;
import com.example.wallet_management_service.exception.WalletNotFoundException;
import com.example.wallet_management_service.model.entity.Wallet;
import com.example.wallet_management_service.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Компонент Util предоставляет полезный функционал для работы с репозиторием.
 */
@Component
@AllArgsConstructor
public class Util {
    private final WalletRepository walletRepository;

    /**
     * Метод поиска кошелька в базе.
     * @param id - UUID кошелька.
     * @return - кошелёк, или исключение WalletNotFoundException, в случае его отсутсвия.
     */
    public Wallet getWalletIfPresent(UUID id){
        Optional<Wallet> ret = this.walletRepository.findById(id);
        if (ret.isPresent()) return ret.get();
        else throw new WalletNotFoundException(id.toString());
    }

    /**
     * Метод для уменьшения суммы кошелька.
     * @param amount - сумма на счету.
     * @param change - сумма для снятия.
     * @return - разница после снятия, или исключение InsufficientFundsException, если снятие невозможно.
     */
    public Integer reducingIfPossible(Integer amount, Integer change){
        int result = amount - change;
        if (result>=0) return result;
        else throw  new InsufficientFundsException(amount.toString());
    }

}
