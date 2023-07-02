package com.rigel.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rigel.service.PlcReportService;

@Component
public class Scheduler {
	
	@Autowired
	PlcReportService plcReportService;
	
	@Scheduled(fixedRate = 1000*60)
	   public void fixedRateSch() {
		try {
			Utility.sendHttpGETRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//          int reportIntervel=1;
//	      Date endDtae = new Date();
//	      plcReportService.downloadReport(endDtae,reportIntervel);		     
//	      String strDate = sdf.format(endDtae);
////	      System.out.println("Fixed Rate scheduler:: " + strDate);
	   }

}
