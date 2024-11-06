package com.nextstep.multiauhtnticate.configuration;
import com.nextstep.multiauhtnticate.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

//    In Java, an AntMatcher is often used in Spring Security to define patterns for securing
//    endpoints, while JWT (JSON Web Token) is a method for securely transmitting
//    information. When combining JWT with an AntMatcher, you can use it to secure specific
//    endpoints based on URL patterns.


    private final JwtAuthFilter authFilter;

    @Autowired
    public SecurityConfig(JwtAuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/toleSudar/v1/saveMember","/toleSudar/v1/loginAsMember","/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-ui/**").permitAll()
                .antMatchers("/api/v1/authenticate").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws  Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/toleSudar/v1/saveMember","/toleSudar/v1/loginAsMember","/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-ui/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public SecurityFilterChain StudentSecurityFilterChain(HttpSecurity http) throws  Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/toleSudar/v1/saveMember","/toleSudar/v1/loginAsMember","/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-ui/**").hasRole("Student")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }

    @Bean
    public SecurityFilterChain TeacherSecurityFilterChain(HttpSecurity http) throws  Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/toleSudar/v1/saveMember","/toleSudar/v1/loginAsMember","/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-ui/**").hasRole("Teacher")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }
    @Bean
    public  SecurityFilterChain LibrarianSecurityFilterChain(HttpSecurity http)throws  Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/toleSudar/v1/saveMember","/toleSudar/v1/loginAsMember","/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-ui/**").hasRole("Librarian")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
