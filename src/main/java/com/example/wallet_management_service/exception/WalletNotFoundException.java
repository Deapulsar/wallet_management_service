package com.example.wallet_management_service.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException(String message){
        super("Кошелёк \""+message+"\" не найден!");
        log.error("Кошелёк \""+message+"\" не найден!");
    }
}
