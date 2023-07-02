package com.rigel.security;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityTokenConfig {

	@Autowired
	private JwtConfig jwtConfig;

	@Value("${security.jwt.header}")
	private String tokenHeader;
	
	@Value("${jwt.route.authentication.path}")
	private String authenticationPath;
	
	@Autowired
	private UserDetailService userDetailService;
	
	private final String[] AUTH_WHITELIST = {
	        "/authenticate",
	        "/swagger-resources/**",
	        "/swagger-ui.html/",
	        "/v2/api-docs/",
	        "/webjars/**"
	};
	
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}
	@Bean
	public JwtConfig jwtConfig() {
		return new JwtConfig();
	}
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
          .disable()
//          .authorizeRequests()
          
        	.exceptionHandling()
    		.authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
    		.addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig,userDetailService), UsernamePasswordAuthenticationFilter.class)
    		.authorizeRequests()
    		.antMatchers(AUTH_WHITELIST).permitAll()
    		.antMatchers(HttpMethod.POST,"/api/token/**","/token/**").permitAll()
//          .antMatchers(HttpMethod.DELETE)
//          .hasRole("2")
//          .antMatchers("/admin/**")
//          .hasAnyRole("1")
//          .antMatchers("/user/**")
//          .hasAnyRole("1", "2")
//          .anonymous()
          .anyRequest()
          .authenticated()
          .and()
          .httpBasic()
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
	       http.headers()
	       .frameOptions().sameOrigin()
	       .cacheControl();
	       http.cors();
        return http.build();
    }

	
	 @Bean
	    public WebSecurityCustomizer webSecurityCustomizer(){
	    	return (web) -> web.ignoring()
	            .antMatchers(
	                HttpMethod.POST,
	                authenticationPath)
	            .and()
	            .ignoring()
	            .antMatchers(
	                HttpMethod.GET,"/","/*.html",
	                "/favicon.ico",
	                "/**/*.html",
	                "/swagger-ui.html#/",
//	                "/demouser/v2/api-docs/",
	                "/v2/api-docs/",
	                "/**/*.jsp",
	                "/**/*.css",
	                "/**/*.js",
	                "/**/*.png",
	                "/**/*.jpg",
	                "/**/*.jpeg",
	                "/**/*.JPG",
	                "/**/*.gif",
	                "/**/*.map",
	                "/fonts/**",
	                "/js/**",
	                "css/**",
	                "/images/**",
	                "/frontend/app/static/**"
	            ).and()
	            .ignoring()
	            .antMatchers("/h2-console/**/**");
	    }
	 
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        final CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowCredentials(true);
//	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.addAllowedOrigin("http://localhost:8091");
	        configuration.setAllowedMethods(Arrays.asList("HEAD",
	                "GET", "POST", "PUT", "DELETE", "PATCH"));
	        // setAllowCredentials(true) is important, otherwise:
	        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
	        
	        // setAllowedHeaders is important! Without it, OPTIONS preflight request
	        // will fail with 403 Invalid CORS request
	        configuration.setAllowedHeaders(Arrays.asList("Authorization","filter", "Cache-Control", "Content-Type"));
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
}