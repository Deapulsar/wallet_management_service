package com.example.wallet_management_service;

import com.example.wallet_management_service.model.entity.Wallet;
import com.example.wallet_management_service.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.UUID;

@SpringBootApplication
public class WalletManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletManagementServiceApplication.class, args);
	}

}
