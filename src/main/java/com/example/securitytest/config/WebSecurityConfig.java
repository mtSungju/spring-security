package com.example.securitytest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*
         filter는 가장 먼저 URL요청을 받음
         filter 기반으로 동작 , DispathcherServlet을 호출하기전에 처리됨
         SecurityFilterChain이 가로채서 설정된 인증,인가부분을 체그하고 난 후 다음흐름으로 이어짐

        HttpSecurity란?
         spring Security의 각종 설정은 HttpSecurity로 함

         authorizeHttpRequests()
         => security 처리에 HttpServletRequest를 이용한다는 것을 의미
         */

        /*
         * SpringSecurityFilterChain 부분에 formLogin 부분을 추가해 주시면 설정 클래스를 만들지 않았을 때처럼 Spring에서 제공하는 화면이 기본 화면이 됩니다.
         * */
        http.authorizeHttpRequests((authorizeRequests)->
                authorizeRequests
                        .requestMatchers("/api1").hasRole("user")
                        .requestMatchers("/api2").hasRole("admin")
                        .anyRequest().authenticated()

        ).formLogin((formLogin)->formLogin
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
        );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        /*
        결국 로그인 처리가 되면 UserDetailsSerive 객체가 반환되게 되는데요.
         이 부분의 아이디가 user1이고 패스워드가 1234인 유저정보를 픽스하기 위해 임의로 내용을 추가하였습니다.
        **/

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user1").password("1234").roles("user").build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
