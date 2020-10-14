package com.xp.user.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * @author Climb.Xu
 * @date 2020/9/24 17:00
 */
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";


//    private final OAuth2RestTemplate oAuth2RestTemplate;
//
//    @Autowired
//    public OAuth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
//        this.oAuth2RestTemplate = oAuth2RestTemplate;
//    }
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        requestTemplate.header(AUTHORIZATION_HEADER,
//                String.format("%s %s",
//                        BEARER_TOKEN_TYPE,
//                        oAuth2RestTemplate.getAccessToken().toString()));
//    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication!=null && authentication.getDetails() instanceof OAuth2AuthenticationDetails){
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenType()));
        }
    }
}
