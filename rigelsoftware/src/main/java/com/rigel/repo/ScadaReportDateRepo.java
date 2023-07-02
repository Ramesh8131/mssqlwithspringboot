package com.rigel.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rigel.model.ScadaReportDate;

@Repository
public interface ScadaReportDateRepo extends JpaRepository<ScadaReportDate, String>{
	
	

}
