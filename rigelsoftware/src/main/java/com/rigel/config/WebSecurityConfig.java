//package com.app.demouser.config;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//public class WebSecurityConfig {
//	
//	
////	   @Autowired
////	    private JwtAuthenticationEntryPoint unauthorizedHandler;
////
////	    @Autowired
////	    private JwtTokenUtil jwtTokenUtil;
////
////	    @Autowired
////	    private UserService jwtUserDetailsService;
////
////	    @Value("${jwt.header}")
////	    private String tokenHeader;
////
//	    @Value("${jwt.route.authentication.path}")
//	    private String authenticationPath;
////	    
////	    @Autowired
////	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////	        auth
////	            .userDetailsService(jwtUserDetailsService)
////	            .passwordEncoder(passwordEncoderBean());
////	    }
//
//	    @Bean
//	    public PasswordEncoder passwordEncoderBean() {
//	        return new BCryptPasswordEncoder();
//	    }
//
////	    @Bean
////	    public AuthenticationManager authenticationManagerBean() throws Exception {
////	        return super.authenticationManagerBean();
////	    }
//	
//	    
//	    @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        http.csrf()
//	          .disable()
//	          .authorizeRequests()
//	          .antMatchers(HttpMethod.DELETE)
//	          .hasRole("ADMIN")
//	          .antMatchers("/admin/**")
//	          .hasAnyRole("ADMIN")
//	          .antMatchers("/user/**")
//	          .hasAnyRole("USER", "ADMIN")
//	          .antMatchers("/login/**")
//	          .anonymous()
//	          .anyRequest()
//	          .authenticated()
//	          .and()
//	          .httpBasic()
//	          .and()
//	          .sessionManagement()
//	          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	        
//		       http.headers()
//		       .frameOptions().sameOrigin()
//		       .cacheControl();
//		       http.cors();
//	        return http.build();
//	    }
//
//	    @Bean
//	    public WebSecurityCustomizer webSecurityCustomizer(){
//	    	return (web) -> web.ignoring()
//	            .antMatchers(
//	                HttpMethod.POST,
//	                authenticationPath)
//	            .and()
//	            .ignoring()
//	            .antMatchers(
//	                HttpMethod.GET,"/","/*.html",
//	                "/favicon.ico",
//	                "/**/*.html",
//	                "/swagger-ui.html#/",
////	                "/demouser/v2/api-docs/",
//	                "/v2/api-docs/",
//	                "/**/*.jsp",
//	                "/**/*.css",
//	                "/**/*.js",
//	                "/**/*.png",
//	                "/**/*.jpg",
//	                "/**/*.jpeg",
//	                "/**/*.JPG",
//	                "/**/*.gif",
//	                "/**/*.map",
//	                "/fonts/**",
//	                "/js/**",
//	                "css/**",
//	                "/images/**",
//	                "/frontend/app/static/**"
//	            ).and()
//	            .ignoring()
//	            .antMatchers("/h2-console/**/**");
//	    }
//	    
//	    /*@Bean
//	    CorsFilter corsFilter() {
//	        CorsFilter filter = new CorsFilter();
//	        return filter;
//	    }*/
//	    
//	    @Bean
//	    public CorsConfigurationSource corsConfigurationSource() {
//	        final CorsConfiguration configuration = new CorsConfiguration();
//	        configuration.setAllowedOrigins(Arrays.asList("*"));
//	        configuration.setAllowedMethods(Arrays.asList("HEAD",
//	                "GET", "POST", "PUT", "DELETE", "PATCH"));
//	        // setAllowCredentials(true) is important, otherwise:
//	        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//	        configuration.setAllowCredentials(true);
//	        // setAllowedHeaders is important! Without it, OPTIONS preflight request
//	        // will fail with 403 Invalid CORS request
//	        configuration.setAllowedHeaders(Arrays.asList("Authorization","filter", "Cache-Control", "Content-Type"));
//	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", configuration);
//	        return source;
//	    }
//
//}