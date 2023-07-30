package com.rigel.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rigel.service.PlcReportService;

@Component
public class Scheduler {
	
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
    
	@Autowired
	PlcReportService plcReportService;
	// daily 80 report
	@Scheduled(fixedRate = 1000*60*80)
	public void fixedRateSch() {
		
		
		try {
			Calendar currentDate = Calendar.getInstance();
	     	Date date1 = simpleDateFormat.parse("2034-07-17");
	    	Calendar calendar1 = Calendar.getInstance();
	    	calendar1.setTime(date1);
	    	System.out.println(currentDate.getTime());
	    	System.out.println(calendar1.getTime());
//	        if(calendar1.after(currentDate)){
//	        	System.out.println("Data print");
	           	Utility.sendHttpGETRequest(80,"1");
//	      	}else {
//	         	System.out.println("fasle");
//	         	
//	 	    }
	  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ParseException e) {
			// TODO: handle exception
		}
//		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//          int reportIntervel=1;
//	      Date endDtae = new Date();
//	      plcReportService.downloadReport(endDtae,reportIntervel);		     
//	      String strDate = sdf.format(endDtae);
////	      System.out.println("Fixed Rate scheduler:: " + strDate);
	   }

	
	// daily 80 report
	  @Scheduled(fixedRate = 1000*60*60*24*7)
	  public void fixedWekelyRateSch() {
		  try {
				int intevaral=60*24*7;
		        Utility.sendHttpGETRequest(intevaral,"2");
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
      }
  }
