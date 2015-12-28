package com.gap.qa.selenium.framework;

import java.io.FileInputStream;
import java.io.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import nl.fountain.xelem.excel.Worksheet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gap.qa.selenium.TestSuite;
import com.gap.qa.selenium.framework.*;
import com.gap.qa.selenium.pages.Login;

import java.util.*;

public class CoreFunctions {
	
	public static List<String> businessFlowData;
	//This function is used to get the list of suites that needs to be executed
	public static List<String> rdFromXLforListOfSuites() throws BiffException, IOException{
		List<String> myList = new ArrayList<String>();	
		String FilePath;
		try {
		FilePath = "C:\\Users\\ravikanth_mogulla\\workspace\\com.gap.qa.selenium\\src\\main\\resources\\datatables\\TestSuiteList.xls";
		FileInputStream fs = new FileInputStream(FilePath);
		Workbook wb = Workbook.getWorkbook(fs);
		Sheet sh = wb.getSheet("Sheet1");
		
		int totalNoOfRows = sh.getRows();
		/*System.out.println(totalNoOfRows);*/
		for (int i = 1; i<totalNoOfRows; i++){
			/*System.out.println(sh.getCell(1, i).getContents());*/
			if (sh.getCell(1, i).getContents().equals("Y"))
			{
				myList.add(sh.getCell(0, i).getContents());
						
				
			}
		}
	 
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return myList;	
	
		
	
	}
	
	//This function is used to open up the browser the test case is opted for
	
	
	//This function is to execute the cases from selected test suite
	public static void tcExecFromExcel(String mySheet){
		String FilePath;
		try {
			FilePath = "C:\\Users\\ravikanth_mogulla\\workspace\\com.gap.qa.selenium\\src\\main\\resources\\datatables\\TestCaseList.xls";
			FileInputStream fs = new FileInputStream(FilePath);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(mySheet);
			/*String functionName=sh.getCell(4, 1).getContents().toString();
			System.out.println("functionName --"+functionName);
			Object obj = new BusinessFunctions();
			Method method = obj.getClass().getMethod(functionName,String.class,String.class);
			method.invoke(obj,"NIKHIL","PASSW");*/
			
			/*for (int i = 1; i<sh.getRows(); i++){
				if (sh.getCell(1, i).getContents().equals("Y"))
					
									//						GenericFunctions.openBrowser(sh.getCell(2, i).getContents());
						for (int j=0; j<sh.getColumns();j++)
						{
							//System.out.println(sh.getColumns());
							System.out.println(sh.getCell(4,1).getContents().toString());
							String functionName2=sh.getCell(j, i).getContents().toString();
							System.out.println("functionName --"+functionName);
							Object obj2 = new BusinessFunctions();
							Method method2 = obj2.getClass().getMethod(functionName2);
							method2.invoke(obj);
							
							//BusinessFunctions.functionName();
						}
							
							
					}*/
				
	
		 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		/*GenericFunctions.tcExecFromExcel("SuiteOne");*/
		/*CoreFunctions.getBusinessFlow();*/
					
	}
	
	public static List<String> getBusinessFlow() throws Exception{
		String FilePath;
		FilePath = "C:\\Users\\ravikanth_mogulla\\workspace\\com.gap.qa.selenium\\src\\main\\resources\\datatables\\TestCaseList.xls";
		FileInputStream fs = new FileInputStream(FilePath);
		Workbook wb = Workbook.getWorkbook(fs);
		Sheet sh = wb.getSheet("SuiteTwo");

		int rowNum = getRowByNameAndColumn(sh);
		
				
		if (rowNum == -1) {
			throw new Exception("The test case \""
					
					+ "\" is not found in the Business Flow sheet!");
		}

		String dataValue;
		List<String> businessFlowData = new ArrayList<String>();
		int currentColumnNum = 4;
		while (!"".equals(dataValue = sh.getCell(currentColumnNum,
				rowNum).getContents())) {
			
			businessFlowData.add(dataValue);
			currentColumnNum++;
			
		}
		
		if (businessFlowData.size() == 0) {
			throw new Exception(
					"No business flow found for the test case \""
							+  "\"");
		}

		System.out.println(businessFlowData.size());
		return businessFlowData;
		
		}
			
	public static int getRowByNameAndColumn(Sheet sh) throws Exception  {
		
			
		for (int r = 1; r <= sh.getRows(); r++) {
			if (sh.getCell(0, r).getContents().equals(TestSuite.tcName)) {
				/*System.out.println(TestSuite.tcName);*/
				return r;
				
	
			}
		}
		return -1;
	}
	
	public static void Exec() throws Exception { 
		
		System.out.println(businessFlowData.size());
		executeTestcase(businessFlowData);
		
	}
	
	
	public static void executeTestcase(List<String> businessFlowData)
			throws Exception {
		HashMap<String, Integer> keywordDirectory = new HashMap<String, Integer>();
		

		for (int currentKeywordNum = 0; currentKeywordNum < businessFlowData
				.size(); currentKeywordNum++) {
			String[] currentFlowData = businessFlowData.get(currentKeywordNum)
					.split(",");
			String currentKeyword = currentFlowData[0];
			
					
				invokeBusinessComponent(currentKeyword);
			}
		}
	

	public static void invokeBusinessComponent(String currentKeyword)
			throws IllegalArgumentException, IllegalAccessException,
			 ClassNotFoundException,
			InstantiationException, Exception, SecurityException {
		Boolean isMethodFound = false;
		final String javaFileExtension = ".java";
		File[] packageDirectories = { new File(
				"src/main/java/com/gap/qa/selenium/pages") };
		
		for (File packageDirectory : packageDirectories) {
			File[] packageFiles = packageDirectory.listFiles();
			
			for (int i = 0; i < packageFiles.length; i++) {
				File packageFile = packageFiles[i];
				String fileName = packageFile.getName();
				
				// We only want the .java files
				if (fileName.endsWith(javaFileExtension)) {
					// Remove the .java extension to get the class name
					String className = fileName.substring(0, fileName.length()
							- javaFileExtension.length());
				

					Class<?> reusableComponents = Class
							.forName("com.gap.qa.selenium.pages."
									+ className);
					Method executeComponent;

					try {
						// Convert the first letter of the method to lowercase
						// (in line with java naming conventions)
						currentKeyword = currentKeyword.substring(0, 1)
								.toLowerCase() + currentKeyword.substring(1);
												
						executeComponent = reusableComponents.getMethod(currentKeyword, (Class<?>[]) null);
														
						
					} catch (NoSuchMethodException ex) {
						// If the method is not found in this class, search the
						// next class
						continue;
					}
										
					isMethodFound = true;

					Constructor<?> ctor = reusableComponents
							.getDeclaredConstructors()[0];
					
					Object businessComponent = ctor.newInstance();

					executeComponent.invoke(businessComponent, (Object[]) null);
					
														
					break;
				}
			}
		}

		if (!isMethodFound) {
			throw new IllegalArgumentException("Keyword " + currentKeyword
					+ " not found within any class "
					+ "inside the pages package");
		}
	}

	
}
