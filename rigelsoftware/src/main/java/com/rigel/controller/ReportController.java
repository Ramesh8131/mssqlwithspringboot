package com.rigel.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rigel.security.JwtTokenUtil;

@RestController
@RequestMapping("/api/")
public class ReportController {
	
	@Autowired
	com.rigel.service.DownloadFile downloadFile;
	
//    @Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;
//
//	
//	@RequestMapping(value="token",method=RequestMethod.POST)
//	public ResponseEntity<Map<String,Object>> generateToken() {
//		Map<String,Object> response=new HashMap<>();
//		final UserDetails userDetails = userDetailsService.loadUserByUsername("");
//		final String token =jwtTokenUtil.generateToken(userDetails);
//		response.put("Token", token);
//		return new ResponseEntity<>(response,HttpStatus.OK);
//	}
	
	@RequestMapping(value="genarateReport",method=RequestMethod.GET)
	public void genarateReport(HttpServletResponse response) {
		downloadFile.diownload();
	}

}
