package com.kucw.security.scheduler;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductScheduler {

    private String KEYCLOAK_TOKEN_URL = "http://localhost:5500/realms/master/protocol/openid-connect/token";

    @Scheduled(fixedRate = 3600000)  // 每 60 分鐘執行一次 calculate() 方法
    public void calculate() {
        System.out.println("每 60 分鐘執行一次");

        // 1. 使用 Client Credentials 授權流程，向 Keycloak 取得 access_token
        String accessToken = exchangeAccessToken();

        System.out.println("Keycloak 生成的 access_token 為: " + accessToken);


        // 2. 使用 access_token，請求 /order api
        String result = requestOrderApi(accessToken);

        System.out.println("請求 /order api 的結果為: " + result);
    }

    private String exchangeAccessToken() {
        // 設定 RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        // 填寫 request body 中的請求參數
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", "my-server-client");
        body.add("client_secret", "uXazp9RniNXBCGhdDGoKNPInFJ1TzRGT");
        body.add("scope", "openid email profile myhome");

        // 發送請求
        KeycloakTokenResponse keycloakTokenResponse;

        keycloakTokenResponse = restTemplate.postForObject(
                KEYCLOAK_TOKEN_URL,
                new HttpEntity<>(body, headers),
                KeycloakTokenResponse.class
        );

        // 返回 Keycloak 所生成的 access_token
        return keycloakTokenResponse.getAccessToken();
    }

    private String requestOrderApi(String accessToken) {
        // 設定 RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        String orderUrl = "http://localhost:8080/order";

        // 發送請求
        String result;

        result = restTemplate.exchange(
                        orderUrl,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class
                )
                .getBody();

        // 返回 /order api 的返回值
        return result;
    }
}
