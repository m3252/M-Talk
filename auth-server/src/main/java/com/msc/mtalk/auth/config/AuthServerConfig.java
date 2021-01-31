package com.msc.mtalk.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer //OAuth2 인증 서버 등록 및 Rest 엔드포인트를 추가할 것을 알린다.
@RequiredArgsConstructor
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final TokenStore tokenStore;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;


    /**
    * 인증 서버에 등록될 클라이언트 정의
    * 즉 OAuth2 서비스로 보호되는 서비스에 접근할 수 있는 클라이언트 애플리케이션을 등록하는 과정
    */

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 애플리케이션 정보를 위한 저장소 DB or Inmemory
                .withClient("api-chat") //해당 애플리케이션이 토큰을 발급 받기 위해 인증 서버 호출시 제시할 명시크릿과 애플리케이션
                .secret(passwordEncoder.encode("mChat"))
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("read", "write") //토큰 요청 시 애플리케이션의 권한 경계 정의
                .accessTokenValiditySeconds(60 * 60)
                .refreshTokenValiditySeconds(6 * 60 * 60)
                .autoApprove(true);
    }

    /**
    * AuthorizationServerConfigAdapter 안에서 사용될 컴포넌트 정의
    * 스프링에 기본 인증 관리자와 사용자 상세 서비스를 이용하겠다는 선언
    */

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}


