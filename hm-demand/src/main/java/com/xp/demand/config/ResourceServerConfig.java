package com.xp.demand.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author Climb.Xu
 * @date 2020/9/23 12:08
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "demand-server";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //不使用session
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID) //资源id
                .tokenServices(tokenServices()) //验证令牌的服务
                .stateless(true);
    }

    //资源服务令牌解析服务
    @Bean
    public ResourceServerTokenServices tokenServices() {
        //使用远程服务请求授权服务器校验token,必须指定校验token的url,client_id,client_secret
        RemoteTokenServices services = new RemoteTokenServices();//远程   DefaultTokenServices本地
        services.setCheckTokenEndpointUrl("http://localhost:9000/oauth/check_token");
        services.setClientId("demand-server");
        services.setClientSecret("secret");
        return services;
    }
}
