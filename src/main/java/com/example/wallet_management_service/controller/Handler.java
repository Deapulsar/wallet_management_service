package com.example.wallet_management_service.controller;

import com.example.wallet_management_service.exception.InsufficientFundsException;
import com.example.wallet_management_service.exception.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Компонент Handler перехватывает исключения, возникающие при работе сервиса (ошибки 50X),
 * возвращая клиенту статус BAD_REQUEST.
 */
@ControllerAdvice
public class Handler {
    /**
     * Перехватывает исключения, связанные с отсутсвием кошелька в базе.
     * @param ex - исключение WalletNotFoundException.
     * @return - ответ с кодом 400 и сообщением исключения.
     */
    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<Object> handleWalletNotFoundException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    /**
     * Перехватывает исключения, связанные с невозможностью снятия суммы.
     * @param ex -исключение InsufficientFundsException.
     * @return - ответ с кодом 400 и сообщением исключения.
     */
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<Object> handleInsufficientFundsException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
