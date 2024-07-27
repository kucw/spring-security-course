package com.kucw.security.controller;

import com.kucw.security.dto.ExchangeTokenRequest;
import com.kucw.security.dto.RefreshTokenRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@CrossOrigin
@RestController
public class LineOAuthController {

    private String LINE_CLIENT_ID = "XXX";

    private String LINE_CLIENT_SECRET = "YYY";

    private String LINE_AUTH_URL = "https://access.line.me/oauth2/v2.1/authorize";

    private String LINE_TOKEN_URL = "https://api.line.me/oauth2/v2.1/token";

    // 步驟 2. 拼接跳轉的 url，將使用者跳轉到 LINE 認證中心頁面
    @GetMapping("/line/buildAuthUrl")
    public String buildAuthUrl() {
        // 使用 LINE 所提供的 auth url，使用者點擊此 url 之後，可以開始 OAuth 2.0 的授權流程
        String authUrl = LINE_AUTH_URL;

        // 拼接 auth url 的請求參數
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(authUrl)
                .queryParam("response_type", "code")
                .queryParam("client_id", LINE_CLIENT_ID)
                .queryParam("scope", "profile+openid+email")
                .queryParam("redirect_uri", "http://localhost:3000/oauth2-line-demo.html")
                .queryParam("state", generateRandomState());

        return uriBuilder.toUriString();
    }

    // 步驟 5. 傳遞 code、client_id、client_secret 的值給 LINE 認證中心，換取 access_token 的值
    @PostMapping("/line/exchangeToken")
    public String exchangeToken(@RequestBody ExchangeTokenRequest exchangeTokenRequest) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        // 使用 LINE 所提供的 token url，傳遞 code 的值過去，即可取得使用者的 access_token
        String tokenUrl = LINE_TOKEN_URL;

        // 填寫 request body 中的請求參數
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", LINE_CLIENT_ID);
        body.add("client_secret", LINE_CLIENT_SECRET);
        body.add("code", exchangeTokenRequest.getCode());
        body.add("redirect_uri", "http://localhost:3000/oauth2-line-demo.html");

        // 發送請求
        String result;
        try {
            result = restTemplate.postForObject(
                    tokenUrl,
                    new HttpEntity<>(body, headers),
                    String.class
            );
        } catch (Exception e) {
            result = e.toString();
        }

        return result;
    }

    // 步驟 7. 使用 access_token，取得使用者在 LINE 中的數據
    @GetMapping("/line/getLineUser")
    public String getLineUser(@RequestParam String accessToken) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
//        headers.setBearerAuth(accessToken);

        // call LINE 的 api，取得使用者在 LINE 中的基本資料
        String url = "https://api.line.me/v2/profile";

        // 發送請求
        String result;
        try {
            result = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        String.class
                    )
                    .getBody();

        } catch (Exception e) {
            result = e.toString();
        }

        return result;
    }

    // 使用 refresh_token，去和 LINE 換發一個新的 access_token
    @PostMapping("/line/refreshToken")
    public String refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        // 使用 LINE 所提供的 token url，傳遞 refresh_token 的值過去，即可取得到新的 access token
        String tokenUrl = LINE_TOKEN_URL;

        // 填寫 request body 中的請求參數
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("client_id", LINE_CLIENT_ID);
        body.add("client_secret", LINE_CLIENT_SECRET);
        body.add("refresh_token", refreshTokenRequest.getRefreshToken());

        // 發送請求
        String result;
        try {
            result = restTemplate.postForObject(
                    tokenUrl,
                    new HttpEntity<>(body, headers),
                    String.class
            );
        } catch (Exception e) {
            result = e.toString();
        }

        return result;
    }

    private String generateRandomState() {
        SecureRandom sr = new SecureRandom();
        byte[] data = new byte[6];
        sr.nextBytes(data);
        return Base64.getUrlEncoder().encodeToString(data);
    }
}
