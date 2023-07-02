package com.rigel.util;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rigel.model.ScadaReportDate;
import com.rigel.repo.ScadaReportDateRepo;

@Component
public class AutofillData implements CommandLineRunner{
	
	@Autowired
	ScadaReportDateRepo scadaReportDateRepo;
	

	@Override
	public void run(String... args) throws Exception {
		ScadaReportDate f=new ScadaReportDate();
		f.setId("1");
		f.setDated(new Timestamp(new Date().getTime()));
		scadaReportDateRepo.save(f);
		
		
	}

}
