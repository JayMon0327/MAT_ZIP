package com.mat.zip.boss.service;

import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReviewAnalysisService {
    public JSONObject analyze(String requestBody) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-NCP-APIGW-API-KEY-ID", "");
        headers.set("X-NCP-APIGW-API-KEY", "");
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8)); //인코딩 형태는 고정. 

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        String url = "";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String responseBody = response.getBody();

        try {
            responseBody = new String(responseBody.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject(responseBody);
    }
}



