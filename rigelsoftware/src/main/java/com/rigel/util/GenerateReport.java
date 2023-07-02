//package com.rigel.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import com.rigel.service.PlcReportService;
//
//
//@Component
//@Configuration
//public class GenerateReport {
//	
//	@Autowired
//	PlcReportService plcReportService;
//	
//	
//	@EventListener(ApplicationReadyEvent.class)
//	public void doSomethingAfterStartup() {
//		plcReportService.downloadReport();
//	}
//
//}