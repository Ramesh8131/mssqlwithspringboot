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
	
	@Column(name="Operating_Voltage")
	private Double operatingVoltage;
	
	@Column(name="Operating_Current")
	private Double operatingCurrent;
	
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

	public String getHeatNo() {
		return heatNo;
	}

	public void setHeatNo(String heatNo) {
		this.heatNo = heatNo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMelterName() {
		return melterName;
	}

	public void setMelterName(String melterName) {
		this.melterName = melterName;
	}

	public int getTap() {
		return tap;
	}

	public void setTap(int tap) {
		this.tap = tap;
	}

	public int getCurve() {
		return curve;
	}

	public void setCurve(int curve) {
		this.curve = curve;
	}

	public Double getNominalPower() {
		return nominalPower;
	}

	public void setNominalPower(Double nominalPower) {
		this.nominalPower = nominalPower;
	}

	public Double getOperatingVoltage() {
		return operatingVoltage;
	}

	public void setOperatingVoltage(Double operatingVoltage) {
		this.operatingVoltage = operatingVoltage;
	}

	public Double getOperatingCurrent() {
		return operatingCurrent;
	}

	public void setOperatingCurrent(Double operatingCurrent) {
		this.operatingCurrent = operatingCurrent;
	}

	public Double getPowerFactor() {
		return powerFactor;
	}

	public void setPowerFactor(Double powerFactor) {
		this.powerFactor = powerFactor;
	}

	public Double getElectrode01() {
		return electrode01;
	}

	public void setElectrode01(Double electrode01) {
		this.electrode01 = electrode01;
	}

	public Double getElectrode02() {
		return electrode02;
	}

	public void setElectrode02(Double electrode02) {
		this.electrode02 = electrode02;
	}

	public Double getElectrode03() {
		return electrode03;
	}

	public void setElectrode03(Double electrode03) {
		this.electrode03 = electrode03;
	}

	public Double getArgonCunsumption() {
		return argonCunsumption;
	}

	public void setArgonCunsumption(Double argonCunsumption) {
		this.argonCunsumption = argonCunsumption;
	}

	public Double getPowerTimevar() {
		return powerTimevar;
	}

	public void setPowerTimevar(Double powerTimevar) {
		this.powerTimevar = powerTimevar;
	}
	
	

}
