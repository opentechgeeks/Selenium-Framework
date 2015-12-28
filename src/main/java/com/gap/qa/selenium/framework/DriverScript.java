package com.gap.qa.selenium.framework;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.gap.qa.selenium.framework.*;


public class DriverScript {
	private static List<String> businessFlowData;
		

public static void main(String[] args) throws Exception{
		
	businessFlowData = CoreFunctions.getBusinessFlow();
	List<String> myList = new ArrayList<String>();
	myList = CoreFunctions.rdFromXLforListOfSuites();
	CoreFunctions.getBusinessFlow();
	CoreFunctions.executeTestcase(businessFlowData);
		
}
	
}
