package com.example.wallet_management_service.controller;

import com.example.wallet_management_service.exception.WalletNotFoundException;
import com.example.wallet_management_service.model.entity.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class WalletControllerTest {

    @Test
    void getBalanceOk(@Autowired WebTestClient client) {
        assert Objects.nonNull(client.get()
                .uri("/api/v1/wallets/550e8400-e29b-41d4-a716-446655440008")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .returnResult()
                .getResponseBody());
    }

    @Test
    void getBalanceBadRequest(@Autowired WebTestClient client) {
        assert Objects.nonNull(client.get()
                .uri("/api/v1/wallets/1")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody());
    }

    @Test
    void getBalanceNotFound(@Autowired WebTestClient client) {
        assert Objects.equals(client.
                get().
                uri("/api/v1/wallets/550e8400-e29b-41d4-a716-446655440118").
                exchange().
                expectStatus().isBadRequest().
                expectBody(String.class).
                returnResult().
                getResponseBody(), new WalletNotFoundException("550e8400-e29b-41d4-a716-446655440118").getMessage());
    }

    @Test
    void postWalletOkDeposit(@Autowired WebTestClient client) {
        assert Objects.nonNull(client.
                post().
                uri("/api/v1/wallet").
                contentType(MediaType.APPLICATION_JSON).
                bodyValue("{\n" +
                        "\"valletId\": \"550e8400-e29b-41d4-a716-446655440000\",\n" +
                        "\"operationType\": \"DEPOSIT\",\n" +
                        " \"amount\": \"1\"\n" +
                        "}").
                exchange().
                expectStatus().isOk().
                expectBody(Wallet.class).
                returnResult().
                getResponseBody());

    }

    @Test
    void postWalletOkWithdraw(@Autowired WebTestClient client) {
        assert Objects.nonNull(client.
                post().
                uri("/api/v1/wallet").
                contentType(MediaType.APPLICATION_JSON).
                bodyValue("{\n" +
                        "\"valletId\": \"550e8400-e29b-41d4-a716-446655440000\",\n" +
                        "\"operationType\": \"WITHDRAW\",\n" +
                        " \"amount\": \"1\"\n" +
                        "}").
                exchange().
                expectStatus().isOk().
                expectBody(Wallet.class).
                returnResult().
                getResponseBody());

    }

    @Test
    void postWalletBadWithdraw(@Autowired WebTestClient client) {
        assert Objects.requireNonNull(client.
                post().
                uri("/api/v1/wallet").
                contentType(MediaType.APPLICATION_JSON).
                bodyValue("{\n" +
                        "\"valletId\": \"550e8400-e29b-41d4-a716-446655440000\",\n" +
                        "\"operationType\": \"WITHDRAW\",\n" +
                        " \"amount\": \"1000000\"\n" +
                        "}").
                exchange().
                expectStatus().isBadRequest().
                expectBody(String.class).
                returnResult().
                getResponseBody()).startsWith("Не достаточно средств");
    }

    @Test
    void postWalletBadJson(@Autowired WebTestClient client) {
        assert Objects.nonNull(client.
                post().
                uri("/api/v1/wallet").
                contentType(MediaType.APPLICATION_JSON).
                bodyValue("{\n" +
                        "\"valletd\": \"550e8400-e29b-41d4-a716-446655440000\",\n" +
                        "\"operationTpe\": \"WITHDRAW\",\n" +
                        " \"amout\": \"1000000\"\n" +
                        "}").
                exchange().
                expectStatus().isBadRequest().
                expectBody(String.class).
                returnResult().
                getResponseBody());
    }

    @Test
    void postWalletNotFoundWallet(@Autowired WebTestClient client) {
        assert Objects.equals(client.
                post().
                uri("/api/v1/wallet").
                contentType(MediaType.APPLICATION_JSON).
                bodyValue("{\n" +
                        "\"valletId\": \"550e8400-e29b-41d4-a716-446655440111\",\n" +
                        "\"operationType\": \"WITHDRAW\",\n" +
                        " \"amount\": \"1000000\"\n" +
                        "}").
                exchange().
                expectStatus().isBadRequest().
                expectBody(String.class).
                returnResult().
                getResponseBody(), new WalletNotFoundException("550e8400-e29b-41d4-a716-446655440111").getMessage());
    }
}