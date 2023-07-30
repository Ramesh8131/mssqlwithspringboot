package com.rigel.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownloadFile {

	SimpleDateFormat dateformatter = new SimpleDateFormat("dd_MM_yyyy");
	SimpleDateFormat fileNameformatter = new SimpleDateFormat("dd_MM_yyyy hh_mm");

	@Autowired
	com.rigel.service.PlcReportService plcReportService;

	public void download(int reportIntervel,String downloadType) {
		try {

//			int reportIntervel = 80;
			Date endDtae = new Date();
			plcReportService.downloadReport(endDtae, reportIntervel,downloadType);
//			Date endDate = new Date();
//			String folderName = dateformatter.format(endDate);
//			String fileName = fileNameformatter.format(endDate).replace("\\s+", "_");
//			File file = new File("./lrfReport/" + folderName + "/LRF_REPORT_" + fileName + ".csv");
//			File distFile = new File("./outPutReport/" + folderName+"/LRF_REPORT_" + fileName + ".csv");
//			if (!distFile.exists()) {
//				distFile.mkdirs();
//			}
//			if (file.exists()) {
//				FileCopyUtils.copy(file, distFile);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
