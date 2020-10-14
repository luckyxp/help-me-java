package com.xp.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author Climb.Xu
 * @date 2020/9/22 23:31
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private AuthenticationManager authenticationManager;

    //配置客户端详细信息服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()//使用in-memory储存
                .withClient("order-server") //client_id
                .secret(NoOpPasswordEncoder.getInstance().encode("secret"))//客户端密钥
                .resourceIds("order-server","user-server","demand-server") //可访问的资源列表
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")//该client允许授权的类型
                .scopes("all") //允许授权的范围
                .autoApprove(false) //false 跳转到授权页面 true 不跳转
                .redirectUris("localhost:9000/test")//验证回调地址
                .and().withClient("admin")
                .secret(NoOpPasswordEncoder.getInstance().encode("secret"))
                .resourceIds("order-server","user-server","demand-server")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("all")
                .autoApprove(false)
                .redirectUris("localhost:9000/test")
                .and().withClient("demand-server")
                .secret(NoOpPasswordEncoder.getInstance().encode("secret"))
                .resourceIds("order-server","user-server","demand-server")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("all")
                .autoApprove(false)
                .redirectUris("localhost:9000/test")
                .and().withClient("user-server")
                .secret(NoOpPasswordEncoder.getInstance().encode("secret"))
                .resourceIds("order-server","user-server","demand-server")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("all")
                .autoApprove(false)
                .redirectUris("localhost:9000/test");
    }


    //令牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService); //客户端信息服务
        services.setSupportRefreshToken(true); //是否产生刷新令牌
        services.setTokenStore(tokenStore); //令牌储存策略
        services.setAccessTokenValiditySeconds(7200); //令牌默认有效期 2小时
        services.setRefreshTokenValiditySeconds(259200); //刷新令牌默认有效期3天
        return services;
    }


    //设置授权码模式的授权码如何存取
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();//内存方式
    }
    //令牌访问端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
     endpoints.authenticationManager(authenticationManager)//认证管理器 密码模式需要
                .authorizationCodeServices(authorizationCodeServices)//授权码服务
                .tokenServices(tokenServices())//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);//运行POST提交
    }
    //令牌访问端点安全策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")  // /oauth/token_key 公开
                .checkTokenAccess("permitAll()") // /oauth/check_token 公开
                .allowFormAuthenticationForClients();//表单认证,申请令牌
    }
}
