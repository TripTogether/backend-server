package TT.tripTogether.config;

import TT.tripTogether.CustomOAuth2AuthService;
import TT.tripTogether.CustomOidcUserService;
import TT.tripTogether.OAuth2AuthenticationFailureHandler;
import TT.tripTogether.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2AuthService customOAuth2AuthService;

    private final CustomOidcUserService customOidcUserService;

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 정적인 파일에 대한 요청들
    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/file/**",
            "/image/**",
            "/swagger/**",
            "/swagger-ui/**",
            // other public endpoints of your API may be appended to this array
            "/h2/**"
    };

    /*
    web FilterChainProxy 생성 필터
     */
    @Override
    public void configure(WebSecurity web) throws Exception{
        //Spring Security 가 인증을 무시할 경로 설정
//        web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/lib/**", "/vendor/**");
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .formLogin().disable()
                .cors().disable()
                .csrf().disable().authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(customOidcUserService)
                .userService(customOAuth2AuthService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);
    }
}
