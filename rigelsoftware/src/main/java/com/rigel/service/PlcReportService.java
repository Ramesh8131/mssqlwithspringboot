package com.rigel.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rigel.model.ScadaReport;
import com.rigel.repo.ScadaReportDateRepo;
import com.rigel.repo.ScadaReportRepo;

import javafx.stage.FileChooserBuilder;

@Service
public class PlcReportService {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	SimpleDateFormat fileNameformatter = new SimpleDateFormat("dd_MM_yyyy hh_mm");
	SimpleDateFormat timeFormate = new SimpleDateFormat("hh:mm:ss");
	SimpleDateFormat dateformatter = new SimpleDateFormat("dd_MM_yyyy");
	
	@Value("${report.xls.path}")
	private String excelPath;

	
	@Autowired
	ScadaReportRepo scadaReportRepo;
	
	@Autowired
	ScadaReportDateRepo scadaReportDateRepo;


	public void downloadReport(Date endDate,int reportIntervel){
		Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.MINUTE, -reportIntervel);
		Date startDtae1=startDate.getTime();
		System.out.println("start date:---"+startDtae1);
		System.out.println("end date:---"+endDate);
		repotList(startDtae1,endDate);	
	}
	@SuppressWarnings("deprecation")
	public List<?> repotList(Date startDate, Date endDate){
		//
		int fontSize=10;
		Workbook workbook = new XSSFWorkbook();
         //		XSSFWorkbook workbook = new XSSFWorkbook();
		String fileName=fileNameformatter.format(endDate).replace("\\s+", "_");
		Sheet sheet = workbook.createSheet(fileName);
//	    sheet.protectSheet("password");
		sheet.setFitToPage(true);
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 3800);
		sheet.setColumnWidth(5, 3800);
		sheet.setColumnWidth(6, 3800);
		sheet.setColumnWidth(7, 3800);
		sheet.setColumnWidth(8, 1200);
		sheet.setColumnWidth(9, 2000);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 3000);
		sheet.setColumnWidth(12, 3800);
		sheet.setColumnWidth(13, 3500);
		
		sheet.setColumnWidth(14, 2500);
		sheet.setColumnWidth(15, 2500);
		sheet.setColumnWidth(16, 2500);
		
		sheet.setColumnWidth(17, 4100);
		sheet.setFitToPage(true);
			       
		XSSFCellStyle headerStyle = (XSSFCellStyle) workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//		headerStyle.setBorderTop(BorderStyle.THICK);
//		headerStyle.setBorderBottom(BorderStyle.THICK);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short) fontSize);
		font.setBold(true);
		font.setColor(IndexedColors.WHITE.getIndex());
		headerStyle.setFont(font);
	    headerStyle.setLocked(true); // read only
//		headerStyle.setWrapText(false);
//		headerStyle.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		
	    
    	XSSFRow header0 = (XSSFRow) sheet.createRow(3);  
    	XSSFCell headerCell0= null;
    	headerCell0 = header0.createCell(0);
		headerCell0.setCellValue(" ");
		headerCell0.setCellStyle(headerStyle);
		headerCell0.setAsActiveCell();
    	for (int i = 1; i < 10; i++) {
    		headerCell0 = header0.createCell(i);
    		headerCell0.setCellValue("");
    		headerCell0.setCellStyle(headerStyle);
    	}
    	headerCell0 = header0.createCell(10);  
    	headerCell0.setCellValue("Nominal Operating"); 
    	headerCell0.setCellStyle(headerStyle);
		
    	headerCell0 = header0.createCell(11);  
    	headerCell0.setCellValue("Operating"); 
    	headerCell0.setCellStyle(headerStyle);
		
    	headerCell0 = header0.createCell(12);  
    	headerCell0.setCellValue("Operating Max"); 
    	headerCell0.setCellStyle(headerStyle);
		
    	headerCell0 = header0.createCell(13);  
    	headerCell0.setCellValue(""); 
    	headerCell0.setCellStyle(headerStyle);
    	
    	CellRangeAddress electrodeSpeed = new CellRangeAddress(3, 3, 14, 16);
    	
    	headerCell0 = header0.createCell(14);  
    	headerCell0.setCellValue("Electrode Speed"); 
    	headerCell0.setCellStyle(headerStyle);
    	sheet.addMergedRegion(electrodeSpeed);
        RegionUtil.setBorderBottom(BorderStyle.THICK, electrodeSpeed, sheet);
        
        headerCell0 = header0.createCell(17);  
    	headerCell0.setCellValue("Argon"); 
    	headerCell0.setCellStyle(headerStyle);
		
		
    	XSSFRow header = (XSSFRow) sheet.createRow(4);
    	short h=300;
    	header.setHeight(h);
		XSSFCell headerCell = header.createCell(0);
		headerCell.setCellValue("Heat No");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(1);
		headerCell.setCellValue("Grade");
		headerCell.setCellStyle(headerStyle);
		
		
		headerCell = header.createCell(2);
		headerCell.setCellValue("Melter Name");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(3);
		headerCell.setCellValue("Date");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(4);
		headerCell.setCellValue("Power On Time");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(5);
		headerCell.setCellValue("Power Off Time");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(6);
		headerCell.setCellValue("Arching Time");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(7);
		headerCell.setCellValue("Holding Time");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(8);
		headerCell.setCellValue("Tap");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(9);
		headerCell.setCellValue("Curve");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(10);
		headerCell.setCellValue("Power(MW)");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(11);
		headerCell.setCellValue("Voltage(V)");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(12);
		headerCell.setCellValue("Current(KA)");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(13);
		headerCell.setCellValue("Power Factor");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(14);
		headerCell.setCellValue("Electrode 1");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(15);
		headerCell.setCellValue("Electrode 2");
		headerCell.setCellStyle(headerStyle);
		
		headerCell = header.createCell(16);
		headerCell.setCellValue("Electrode 3");
		headerCell.setCellStyle(headerStyle);
				
		headerCell = header.createCell(17);
		headerCell.setCellValue("Consumption(LPM)");
		headerCell.setCellStyle(headerStyle);
		
		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		
		CellStyle style2 = workbook.createCellStyle();
		style2.setWrapText(true);
		style2.setAlignment(HorizontalAlignment.CENTER);
		style2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style2.setBorderBottom(BorderStyle.THIN);
		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		 CreationHelper createHelper = workbook.getCreationHelper();  
	       
		List<ScadaReport> pdf=scadaReportRepo.fetchAllRecords(startDate, endDate);
		for (int j = 0; j < pdf.size(); j++) {
			int i=j+1;
			ScadaReport scadaReportData=pdf.get(j);
			Row row = sheet.createRow(j+5);

			Cell cell0 = row.createCell(0);
			cell0.setCellValue(scadaReportData.getHeatNo());
			cell0.setCellStyle(style);
			
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(scadaReportData.getGrade());
			cell1.setCellStyle(style);
			
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(scadaReportData.getMelterName());
			cell2.setCellStyle(style);

			Cell cell3 = row.createCell(3);
			Date date=new Date();
			try {
				date = formatter.parse(scadaReportData.getDated().toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(date);
			short fmt=createHelper.createDataFormat().getFormat("dd-MM-yyyy hh:mm:ss");
			style2.setDataFormat(fmt);  
			cell3.setCellValue(new Date(date.getTime()));
			cell3.setCellStyle(style2);
			//power on time
			String time=timeFormate.format(scadaReportData.getDated());
			System.out.println("time:---"+time);
			boolean powerOfTime=scadaReportData.getPowerTimevar()>=1;
			
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(powerOfTime?time:"Nill");
			cell4.setCellStyle(style);

			//power off time
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(!powerOfTime?time:"Nill");
			cell5.setCellStyle(style);
			
			// arck time
			
			boolean archingTime=scadaReportData.getOperatingCurrent()>0;
		
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(archingTime?time:"Nill");
			cell6.setCellStyle(style);

			//holding time
			Cell cell7 = row.createCell(7);
			cell7.setCellValue(!archingTime?time:"Nill");
			cell7.setCellStyle(style);

			Cell cell8 = row.createCell(8);
			cell8.setCellValue(scadaReportData.getTap());
			cell8.setCellStyle(style);
			
			Cell cell9 = row.createCell(9);
			cell9.setCellValue(scadaReportData.getCurve());
			cell9.setCellStyle(style);

			Cell cell10 = row.createCell(10);
			cell10.setCellValue(scadaReportData.getNominalPower());
			cell10.setCellStyle(style);

			Cell cell11 = row.createCell(11);
			cell11.setCellValue(scadaReportData.getOperatingVoltage());
			cell11.setCellStyle(style);

			Cell cell12 = row.createCell(12);
			cell12.setCellValue(scadaReportData.getOperatingCurrent());
			cell12.setCellStyle(style);

			Cell cell13 = row.createCell(13);
			cell13.setCellValue(scadaReportData.getPowerFactor());
			cell13.setCellStyle(style);

			
			Cell cell14 = row.createCell(14);
			cell14.setCellValue(scadaReportData.getElectrode01());
			cell14.setCellStyle(style);

			Cell cell15 = row.createCell(15);
			cell15.setCellValue(scadaReportData.getElectrode02());
			cell15.setCellStyle(style);
			

			Cell cell16 = row.createCell(16);
			cell16.setCellValue(scadaReportData.getElectrode03());
			cell16.setCellStyle(style);

			Cell cell17= row.createCell(17);
			cell17.setCellValue(scadaReportData.getArgonCunsumption());
			cell17.setCellStyle(style);
		
		}
		
//		 Row rootheader = sheet.createRow(0);
//		 rootheader.setHeight((short) 800);
		  XSSFCellStyle headerStyle1 = (XSSFCellStyle) workbook.createCellStyle();
			headerStyle1.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			headerStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle1.setBorderTop(BorderStyle.THICK);
			headerStyle1.setBorderBottom(BorderStyle.THICK);
			headerStyle1.setBorderLeft(BorderStyle.THICK);
			headerStyle1.setBorderRight(BorderStyle.THICK);
			headerStyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			headerStyle1.setRightBorderColor(IndexedColors.BLACK.getIndex());
			headerStyle1.setAlignment(HorizontalAlignment.CENTER);
			headerStyle1.setLocked(true);
			
			XSSFFont font1 = ((XSSFWorkbook) workbook).createFont();
			font1.setFontName("Times New Roman");
			font1.setItalic(true);
			font1.setFontHeightInPoints((short) 18);
			font1.setBold(true);
			font1.setColor(IndexedColors.WHITE.getIndex());
			headerStyle1.setFont(font1);
//			headerStyle1.setLocked(true); // read only
//			headerStyle1.setWrapText(true);
			
			XSSFRow row = (XSSFRow) sheet.createRow(0);  
			XSSFCell cell = row.createCell(0);  
            cell.setCellValue("LRF REPORT"); 
            cell.setCellStyle(headerStyle1);
            sheet.addMergedRegion(new CellRangeAddress(0,1,0,17));
            
            headerStyle1.setFillForegroundColor(IndexedColors.BLACK.getIndex());
//			headerStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFRow row2 = (XSSFRow) sheet.createRow(2);  
			XSSFCell cell2 = row2.createCell(0);  
            cell2.setCellValue("Kisco Castings India Pvt Ltd(Mandi Gobindgarh, Punjab 147301)"); 
            cell2.setCellStyle(headerStyle1);
            
            CellRangeAddress cellRangeAddress=new CellRangeAddress(2,2,0,17);
            sheet.addMergedRegion(cellRangeAddress);
        String folderName=dateformatter.format(endDate);
		String currentDir=null;
        try {
			 currentDir=System.getenv("APPDATA");
			 System.out.println(currentDir);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        File currDir = new File(currentDir+"/RigelAutomationSoft/lrfReport/");
		if(!currDir.exists()) {
			currDir.mkdirs();
		}
		System.out.println(currDir.getAbsolutePath());
		try {
			for(File oldFile:currDir.listFiles()) {
				oldFile.delete();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
       
		File distFile=new File(excelPath+"/"+folderName);
		if(!distFile.exists()) {
			distFile.mkdir();
		}
		String excelFile = currDir.getAbsolutePath()+"/LRF_REPORT_"+ fileName+".csv";
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(excelFile);
			workbook.write(outputStream);
			
			workbook.close();
			File srcFile=new File(currDir.getAbsolutePath()+"/"+"LRF_REPORT_"+ fileName+".csv");
			FileUtils.copyFile(srcFile,new File(distFile.getAbsolutePath()+"/LRF_REPORT_"+ fileName+".csv"));
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
