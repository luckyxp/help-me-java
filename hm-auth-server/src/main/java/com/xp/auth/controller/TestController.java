package com.xp.auth.controller;

import com.xp.auth.OAuthVo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Climb.Xu
 * @date 2020/9/23 14:28
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("/test")
    public String test(@RequestBody OAuthVo vo) throws JSONException {
        MultiValueMap<String, Object> postData = new LinkedMultiValueMap<>();
        postData.add("username", vo.getUsername());
        postData.add("password", vo.getPassword());
        postData.add("client_id", "admin");
        postData.add("client_secret", "secret");
        postData.add("grant_type", "password");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postData, headers);
        String result = restTemplate.postForObject("http://localhost:9000/oauth/token", r, String.class);
        return result;
    }
}
