package com.gap.qa.selenium;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import com.gap.qa.selenium.framework.CoreFunctions;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestSuite  { 
	
	public static String tcName;
	public Sheet sh;
	private static List<String> businessFlowData;
	
	public static Sheet excelPath() throws Exception, IOException
	{
		String FilePath;
		FilePath = "C:\\Users\\ravikanth_mogulla\\workspace\\com.gap.qa.selenium\\src\\main\\resources\\datatables\\TestCaseList.xls";
		FileInputStream fs = new FileInputStream(FilePath);
		Workbook wb = Workbook.getWorkbook(fs);
		Sheet sh = wb.getSheet("SuiteTwo");
		return sh;
	}
	
			
	@Test
	public void TC_1() throws Exception
	{
		sh = excelPath();
		tcName = "case1";
		CoreFunctions.getRowByNameAndColumn(sh);
		businessFlowData = CoreFunctions.getBusinessFlow();
		CoreFunctions.executeTestcase(businessFlowData);
				
	}
	
	@Test
	public void TC_2() throws Exception {
	
		sh = excelPath();
		tcName = "case2";
		CoreFunctions.getRowByNameAndColumn(sh);
		businessFlowData = CoreFunctions.getBusinessFlow();
		CoreFunctions.executeTestcase(businessFlowData);
		
	}
	
}
