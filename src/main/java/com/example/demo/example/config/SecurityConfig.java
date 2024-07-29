package com.example.demo.example.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.BoardService;
import com.example.demo.service.BoardServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((req) -> req
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/board/**").authenticated()
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll()
            )
            .formLogin((form) -> form 			
                .loginPage("/login")			//로그인 페이지
                .loginProcessingUrl("/loginPro")//로그인 요청을 처리할 URL. 로그인 정보를 제출하면 이쪽으로 POST되고, SpringSecurity가 인증을 시도.
                .defaultSuccessUrl("/", true)	//로그인 성공 후, 리다이렉트 될 링크
                .permitAll()					//인증이 필요없는 페이지로 모두에게 열림.
            )
            .logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //로그아웃 요청을 처리할 url 설정.
                .logoutSuccessUrl("/")										//로그아웃 성공 후, 리다이렉트 될 링크
                .invalidateHttpSession(true)								//로그아웃 시 http 세션 무효화
                .deleteCookies("JSESSIONID", "remember-me")					//두 쿠키를 삭제.
            )
            .rememberMe((rememberMe) -> rememberMe
                .key("myWeb")
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(86400)
            )
            .exceptionHandling((exceptionHandling) -> exceptionHandling
                .accessDeniedPage("/denied")
            )
            .sessionManagement((sessionManagement) -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .invalidSessionUrl("/login")
            )
            .csrf((AbstractHttpConfigurer::disable));

        return http.build();
    }
    

}