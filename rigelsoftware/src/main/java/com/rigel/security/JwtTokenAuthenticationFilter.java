package com.rigel.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtTokenAuthenticationFilter extends  OncePerRequestFilter {
    
	private final JwtConfig jwtConfig;
	
	private UserDetailService userDetailService;
	
	public JwtTokenAuthenticationFilter(JwtConfig jwtConfig, UserDetailService userDetailService) {
		this.jwtConfig = jwtConfig;
		this.userDetailService=userDetailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
//		System.out.println(jwtConfig);
		String header = request.getHeader(jwtConfig.getHeader());
//		System.out.println(jwtConfig.getHeader()+" header "+header);
		if(header == null || !header.startsWith(jwtConfig.getPrefix())) {
			chain.doFilter(request, response);  		// If not valid, go to the next filter.
			return;
		}
				
		String token = header.substring(7);
		System.out.println("Token "+token);
		try {	
			Claims claims = Jwts.parser()
					.setSigningKey(jwtConfig.getSecret())
					.parseClaimsJws(token)
					.getBody();
			String username = claims.getSubject();
			JwtUser userDetails = (JwtUser) userDetailService.loadUserByUsername(username);
			System.out.println("Username  "+username);

			if(userDetails!=null) {
				System.out.println("Username  "+userDetails.getUsername());
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
						 userDetails.getUsername(), null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			} else {
				SecurityContextHolder.clearContext();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// In case of failure. Make sure it's clear; so guarantee user won't be authenticated
			SecurityContextHolder.clearContext();
		}
		
		// go to the next filter in the filter chain
		chain.doFilter(request, response);
	}

}