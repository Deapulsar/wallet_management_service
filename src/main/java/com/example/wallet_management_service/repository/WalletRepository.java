package com.example.wallet_management_service.repository;

import com.example.wallet_management_service.model.entity.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WalletRepository extends CrudRepository<Wallet, UUID> {}
