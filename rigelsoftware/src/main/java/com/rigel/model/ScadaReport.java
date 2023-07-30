package com.rigel.model;


import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kisco_lrf_report")
@Setter
@Getter
public class ScadaReport {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	@Column(length = 36, nullable = false, updatable = false,columnDefinition="UNIQUEIDENTIFIER default NEWID()")
	private String id;
	
	@Column(nullable = false, updatable = false,columnDefinition="datetime default current_timestamp")
	private Timestamp dated;

	@Column(name="HEAT_NO")
	private String heatNo;
	
	@Column(name="Grade")
	private String grade;
	
	@Column(name="Melter_Name")
	private String melterName;
	
	@Column(name="Tap")
	private int tap;
	
	@Column(name="Curve")
	private int curve;
	
	@Column(name="Nominal_Power")
	private Double nominalPower;
	
	@Column(name="Operating_Voltage1")
	private Double operatingVoltage1;
	
	@Column(name="Operating_Voltage2")
	private Double operatingVoltage2;
	
	@Column(name="Operating_Voltage3")
	private Double operatingVoltage3;
	
	@Column(name="Operating_Current1")
	private Double operatingCurrent1;
	
	@Column(name="Operating_Current2")
	private Double operatingCurrent2;
	
	@Column(name="Operating_Current3")
	private Double operatingCurrent3;
	
	@Column(name="Power_Factor")
	private Double powerFactor;
	
	@Column(name="Electrode01")
	private Double electrode01;

	@Column(name="Electrode02")
	private Double electrode02;

	@Column(name="Electrode03")
	private Double electrode03;
	
	@Column(name="Argon_Cunsumption")
	private Double argonCunsumption;

	@Column(name="power_timevar")
	private Double powerTimevar;
	
	@Column(name="Argon_Flow_rate")
	private Double argonFlowRate;
	
	@Column(name="Active_Energy ")
	private Double activeEnergy ;
	

}
