package com.rigel.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name = "user")
@Setter
@Getter
public class User {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String email_id;
	private String mobile_no;
	private String country_code;
	private String password;
	
	private String delivery_to_pincode;
	
	private int status;// 1-active,2-InActive,3-delete
	private Timestamp created_at;
	private int role;// 1- admin,2-user
	private String gender;
	private Date lastPasswordResetDate;

}