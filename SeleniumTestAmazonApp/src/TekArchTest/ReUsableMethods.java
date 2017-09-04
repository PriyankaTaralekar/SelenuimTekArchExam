package TekArchTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ReUsableMethods extends Driver
{
	static Object[][] locator;
	static Object[][] data;
	static Object[][] matrix;
	static String locatorType;
	static String value;
	static String obj_Name;
	
	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	static int j = 1;

	static String fireFoxBrowser;
	static String chromeBrowser;

	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;
	static String testCaseResult;
	static String failureScreenShot;
	static String resultPath;
	static String reportMessage;
	
	/* Name Of the method: enterText
	 * Brief Description: Enter the text value to the text box
	 * Arguments: obj --> Text box object, textVal --> value to be entered, objName --> name of the object
	 * Created by: Automation team
	 * Creation Date: Aug 23 2017
	 * Last Modified: Aug 23 2017
	 * */
	public static void enterText(WebElement obj, String textVal, String objName) throws IOException{
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			Update_Report("Pass", "enterText", textVal + " is entered in " + objName +" field", driver);
			//System.out.println("Pass: " + textVal + " is entered in " + objName +" field");
		} else {
			Update_Report("Fail", "enterText", objName + " field is not displayed,please check your application", driver);
			//System.out.println("Fail: " + objName + " field is not displayed,please check your application");
		}

	}
	/* Name Of the method: clickButton
	 * Brief Description: Click on button
	 * Arguments: obj --> web object,  objName --> name of the object
	 * Created by: Automation team
	 * Creation Date: Aug 23 2017
	 * Last Modified: Aug 23 2017
	 * */
	
	public static void clickButton(WebElement obj,  String objName) throws IOException {
		
		if(obj.isDisplayed()){
			obj.click();
			Update_Report("Pass", "Click Button", objName +" is clicked.", driver);
		} else {
			Update_Report("Fail", "Click Button", objName + " field is not displayed,please check your application", driver);
		}
		
	}
	
    public static void submitButton(WebElement obj,  String objName) throws IOException{
		
		if(obj.isDisplayed()) {
			obj.submit();
			Update_Report("Pass", "Submit Button", objName +" is clicked.", driver);
			//System.out.println("Pass: " + objName +" is clicked.");
		} else {
			Update_Report("Fail", "Submit Button", objName + " field is not displayed,please check your application", driver);
			//System.out.println("Fail: " + objName + " field is not displayed,please check your application");
		}
		
	}
	
	/* Method Name: readXlSheet
	 * Method description:Read content from Xl sheet
	 * Arguments:dt_path --> Path of Xl sheet, sheetName --> Name of the sheet user is accessing 

	 * Created by:Automation Team
	 * Creation Date: July 26 2017
	 * Last Modified: July 26 2017
	 */
	public static String[][] readXlSheet(String dt_path, String sheetName) throws IOException{

		
		File xlFile = new File(dt_path);
        FileInputStream xlDoc = new FileInputStream(xlFile);
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);
		HSSFSheet sheet = wb.getSheet(sheetName);

		int iRowCount = sheet.getLastRowNum()+1;
		int iColCount = sheet.getRow(0).getLastCellNum();
		DataFormatter objDefaultFormat = new DataFormatter();
		FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) wb);

		String[][] xlData = new String[iRowCount][iColCount];

		for(int i = 0; i < iRowCount; i++)
		{
			for(int j = 0; j <iColCount; j++)
			{
				HSSFCell cell =  sheet.getRow(i).getCell(j);
				objFormulaEvaluator.evaluate(cell); // This will evaluate the cell, And any type of cell will return string value
			    String cellValueStr = objDefaultFormat.formatCellValue(cell,objFormulaEvaluator);
				xlData[i][j] = cellValueStr; //sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		wb.close();
		return xlData;
	}
	
	
	
	public static void Update_Excel(String dt_path, String sheetName, Integer rowNum, Integer columnNum) throws IOException 
	{
		
		String file = new String (dt_path); 
		FileInputStream fis = new FileInputStream(file); 
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		CreationHelper createHelper = wb.getCreationHelper();
		
		HSSFSheet sheet= wb.getSheet(sheetName);
		System.out.println(rowNum);
		
		HSSFCell cell = sheet.getRow(rowNum).getCell(columnNum);
		HSSFCellStyle style = wb.createCellStyle();
		System.out.println(testCaseResult);
		
		if(testCaseResult.equals("Pass"))
		{
			 style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			 style.setFillForegroundColor(HSSFColor.GREEN.index);	
			 if(cell.getHyperlink() != null) {
				    cell.setHyperlink(null);
			}
		}
		else 
		{
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.RED.index);	
					
			//Create a hyperlink
			Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
		    //link to a file in the report folder		    
		    link = createHelper.createHyperlink(Hyperlink.LINK_FILE);
		    link.setAddress(resultPath);   //stored html report in resultPath
		    cell.setHyperlink((org.apache.poi.ss.usermodel.Hyperlink) link);

			//underline the link
			HSSFFont font = wb.createFont();
			font.setUnderline(HSSFFont.U_DOUBLE);
			style.setFont(font);   
			
		}
		
		cell.setCellStyle(style);	
		FileOutputStream fileOS = new FileOutputStream(dt_path);
		wb.write(fileOS);		
	//	fileOS.close();
		wb.close();
	}
		/*catch(Exception e){
			System.out.println(e);
		}*/
	
	
	public static String screenshot(WebDriver dr) throws IOException{
		
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String str_time = dateFormat.format(exec_time);
		String  ss1Path = "C:/Priyanka/TekArch/Screenshot/"+ str_time+".png";
		File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(ss1Path));
		return ss1Path;
	}
	
	/*
	 * Name of the Method: startReport
	 * Brief description : Creates HTML report template
	 * Arguments: scriptname:test script name to run,ReportsPath:HTML report path to create,browserName:browser the script is running
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
	 */
	public static void startReport(String scriptName, String ReportsPath,String browserName) throws IOException{
		j =0;
		String strResultPath = null;
		String testScriptName =scriptName;

		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";
		
		resultPath = htmlname;
		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ browserName + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");


	}
	
	/*
	 * Name of the Method: Update_Report
	 * Brief description : Updates HTML report with test results
	 * Arguments: Res_type:holds the response of test script,Action:Action performed,result:contains test results
	 * Created by: Automation team
	 * Creation date : July 17 2017
	 * last modified:  July 17 2017
	 */
	public static void Update_Report(String Res_type,String Action, String result, WebDriver dr) throws IOException {
		
		try {
		
			Date exec_time = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String str_time = dateFormat.format(exec_time);
			
			testCaseResult = Res_type; System.out.println(Res_type + " " + Action + " " + result);
			
			if (Res_type.startsWith("Pass")) {
				
				bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
						+ (j++)
						+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
						+ Action
						+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
						+ str_time
						+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
						+ "Passed"
						+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
						+ result + "</FONT></TD></TR>");
	
			} else if (Res_type.startsWith("Fail")) {
				//To generate report in only single file
				
				String ss1Path = screenshot(dr);
				failureScreenShot = ss1Path;
				exeStatus = "Failed";
				report = 1;
				bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
						+ (j++)
						+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
						+ Action
						+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
						+ str_time
						+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
						+ "<a href= "
						+ ss1Path
						
						+ "  style=\"color: #FF0000\"> Failed </a>"
	
							+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
							+ result + "</FONT></TD></TR>");
	
	
			} 
		}
		catch (Exception ex) {
			System.out.println("Inside Update Report " + ex.getStackTrace());
		}
	}
	
	public static void setValue(int row)
	{
		obj_Name=(String) locator[row][0];
		locatorType = (String) locator[row][1];
		value=(String) locator[row][2];
	}
	
	/* Name of the method : getWebElement
	 * Brief description : Returns WebElement object for given objectType and objectProperty
	 * Created by : Priyanka
	 * Creation Date : 24 Aug 2017
	 * Last modified : 24 Aug 2017
	 */
	public static By getBy(String objectType, String objectProperty)
	{
		switch (objectType)
		{
			case "id":
				return By.id(objectProperty);
			case "xpath":
				return By.xpath(objectProperty);
			case "className":
				return By.className(objectProperty);
			case "name":
				return By.name(objectProperty);
			case "linkText":
				return By.linkText(objectProperty);
			case "partialLinkText":
				return By.partialLinkText(objectProperty);
			case "cssSelector":
				return By.cssSelector(objectProperty);
			case "tagName":
				return By.tagName(objectProperty);
			default:
				System.out.println("Unknown type");
				return null;

		}	
	}
	
	public static void readLocators(String path)
	{
		locator = readExcel(path); 
	}

	public static void readTestData(String path) throws InterruptedException
	{
		data = readExcel(path);
	}

	public static Object[][] readExcel(String path)
	{
		try
		{
			File file = new File(path);
			FileInputStream xf = new FileInputStream(file);
			HSSFWorkbook xwb = new HSSFWorkbook(xf);
			ArrayList<String> sNames = new ArrayList<String>();

			// retrieve all the sheet in a file
			for (int i=0; i<xwb.getNumberOfSheets(); i++) 
			{
				sNames.add( xwb.getSheetName(i) );
			}
			
			//Iterate through each sheet and retrieves the data and stores it in an arraylist
			for(String s: sNames)
			{			
				HSSFSheet sheet = xwb.getSheet(s);
				int row = sheet.getLastRowNum()+1;
				int col = sheet.getRow(0).getLastCellNum();
				matrix = new Object[row][col];
				DataFormatter objDefaultFormat = new DataFormatter();
				FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) xwb);
				for(int i=0;i<row;i++)
				{
					for(int j=0;j<col;j++)
					{
						HSSFCell cell = sheet.getRow(i).getCell(j);
						if(cell!=null)
						{
							objFormulaEvaluator.evaluate(cell); // This will evaluate the cell, And any type of cell will return string value
						    String cellValueStr = objDefaultFormat.formatCellValue(cell,objFormulaEvaluator);

							//String value= cell.getStringCellValue();
							matrix[i][j] = cellValueStr;

						}
					}
				}
			}
			xwb.close();
		}
		catch(Exception e)
		{
			
		}
		return matrix;
	}
	
	//get attribute value
		public static String getAttributeValue(WebElement ele_attribute)
		{
			String actual = ele_attribute.getAttribute("value");
			return actual;
		}

		//Clear text box
		public static void clear(WebElement textBox)
		{
			textBox.clear();
		}
		
		public static String verify(String expected, String actual)
		{
			String result = null;
			if(expected.equals(actual))
			{
				result = "Pass";
				return result;
			}
			else
			{
				result = "Fail";
				return result;
			}
		}
		
		public static Boolean isdisplayed(WebElement obj)
		{	
			if(obj != null && obj.isDisplayed())
			{
				System.out.println("Pass: " + obj +" is displayed.");
				return true;
			}
			else
			{
				System.out.println("Fail: " + obj + " field is not displayed,please check your application");
				return false;
			}

	}
}

