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

import java.net.URLDecoder;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class FacebookOAuthController {

    private String FACEBOOK_CLIENT_ID = "XXX";

    private String FACEBOOK_CLIENT_SECRET = "YYY";

    private String FACEBOOK_AUTH_URL = "https://www.facebook.com/v19.0/dialog/oauth";

    private String FACEBOOK_TOKEN_URL = "https://graph.facebook.com/v19.0/oauth/access_token";

    // 步驟 2. 拼接跳轉的 url，將使用者跳轉到 Facebook 認證中心頁面
    @GetMapping("/facebook/buildAuthUrl")
    public String buildAuthUrl() {
        // 使用 Facebook 所提供的 auth url，使用者點擊此 url 之後，可以開始 OAuth 2.0 的授權流程
        String authUrl = FACEBOOK_AUTH_URL;

        // 拼接 auth url 的請求參數
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl(authUrl)
                .queryParam("response_type", "code")
                .queryParam("client_id", FACEBOOK_CLIENT_ID)
                .queryParam("scope", "public_profile,email")
                .queryParam("redirect_uri", "http://localhost:3000/oauth2-facebook-demo.html")
                .queryParam("state", generateRandomState());

        return uriBuilder.toUriString();
    }

    // 步驟 5. 傳遞 code、client_id、client_secret 的值給 Facebook 認證中心，換取 access_token 的值
    @PostMapping("/facebook/exchangeToken")
    public String exchangeToken(@RequestBody ExchangeTokenRequest exchangeTokenRequest) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        // 使用 Facebook 所提供的 token url，傳遞 code 的值過去，即可取得使用者的 access_token
        String tokenUrl = FACEBOOK_TOKEN_URL;

        // 填寫 request body 中的請求參數
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("client_id", FACEBOOK_CLIENT_ID);
        body.add("client_secret", FACEBOOK_CLIENT_SECRET);
        body.add("code", URLDecoder.decode(exchangeTokenRequest.getCode()));
        body.add("redirect_uri", "http://localhost:3000/oauth2-facebook-demo.html");

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

    // 步驟 7. 使用 access_token，取得使用者在 Facebook 中的數據
    @GetMapping("/facebook/getFacebookUser")
    public String getFacebookUser(@RequestParam String accessToken) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
//        headers.setBearerAuth(accessToken);

        // call Facebook 的 api，取得使用者在 Facebook 中的基本資料
        String url = "https://graph.facebook.com/me?fields=id,name,email";

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

    private String generateRandomState() {
        SecureRandom sr = new SecureRandom();
        byte[] data = new byte[6];
        sr.nextBytes(data);
        return Base64.getUrlEncoder().encodeToString(data);
    }
}
