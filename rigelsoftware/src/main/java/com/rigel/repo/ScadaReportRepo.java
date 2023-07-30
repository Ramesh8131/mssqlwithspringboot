package com.rigel.repo;

import com.rigel.model.ScadaReport;
import com.rigel.model.ScadaReportDate;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScadaReportRepo extends JpaRepository<ScadaReport, String> {

	@Query(value = "select * from kisco_lrf_report where dated BETWEEN :startDate and :endDate order by dated asc", nativeQuery = true)
	public List<ScadaReport> fetchAllRecords(Date startDate, Date endDate);

}
