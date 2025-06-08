package com.example.wallet_management_service.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(String message){
        super("Не достаточно средств: "+message+"!");
        log.error("Не достаточно средств: "+message+"!");
    }
}
