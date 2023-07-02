package com.rigel.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "report_date")
@Setter
@Getter
public class ScadaReportDate {

	@Id
	private String id;
	
	@Column(nullable = false, updatable = false,columnDefinition="datetime default current_timestamp")
	private Timestamp dated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getDated() {
		return dated;
	}

	public void setDated(Timestamp dated) {
		this.dated = dated;
	}
	
	
}
