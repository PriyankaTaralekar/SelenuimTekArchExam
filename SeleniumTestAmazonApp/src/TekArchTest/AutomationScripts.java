package TekArchTest;

import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Set;
import java.awt.Robot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AutomationScripts extends ReUsableMethods {
	
	//public static WebDriver driver;
	private static String result;	
	
	// Test Case No : 1
	public static void searchAndAddtoCart() throws IOException, InterruptedException
	{
		try
		{
			String orPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\Amazon_ObjectRepository.xls";
			String tdPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\searchItem.xls";
			readLocators(orPath);
			readTestData(tdPath);
			
			for(int i=1;i<data.length;i++)
			{
				String url = (String) data[i][0];
				String serachText = (String) data[i][1];
				String expected = (String) data[i][2];
				
				driver.get(url);
				Thread.sleep(5000);
				
				//Locate search text box
				setValue(3);
				By searchBox = getBy(locatorType,value);
				WebElement ele_SearchText = driver.findElement(searchBox);
				clear(ele_SearchText);
				enterText(ele_SearchText,serachText,obj_Name);
	
				//Locate serach button
				setValue(4);
				By searchButton = getBy(locatorType,value);
				WebElement ele_SearchBut = driver.findElement(searchButton);
				submitButton(ele_SearchBut,obj_Name);
				Thread.sleep(5000);
				
				//Locate record whose name contains apple iphone6
				setValue(5);
				By searchRec = getBy(locatorType,value);
				WebElement ele_SearchRec = driver.findElement(searchRec);
				clickButton(ele_SearchRec,obj_Name);
				Thread.sleep(4000);
				
				//Locate opened record page item
				setValue(6);
				By byText = getBy(locatorType,value);
				WebElement ele_txt = driver.findElement(byText);
				
				if(ele_txt.getText().contains(serachText))
					System.out.println("successfully opened selected product page");
				
				//Locate addtoCart button
				setValue(7);
				By addToCart = getBy(locatorType,value);
				WebElement ele_addTocartBut = driver.findElement(addToCart);
				clickButton(ele_addTocartBut,obj_Name);
				Thread.sleep(4000);
				
				//Locate button on pop up
				setValue(9);
				By popUpButton = getBy(locatorType,value);
				WebElement ele_NoThnaksButton = driver.findElement(popUpButton);
				clickButton(ele_NoThnaksButton,obj_Name);
				Thread.sleep(4000);
				
				//Locate cart count
				setValue(10);System.out.println(value);
				By cartCount = getBy(locatorType,value);
				WebElement ele_cartCountNo = driver.findElement(cartCount);
				String actual = ele_cartCountNo.getText();
				System.out.println(actual);
				//Verify Result
				result = verify(expected,actual);
				System.out.println(result);
				
				if (result.equals("Pass")) {
					reportMessage = "Search and add to cart functionality worked successfully";
				}
				else {
					reportMessage = "Search and add to cart functionality did not work successfully";
				}
				Update_Report( result, "searchAndAddtoCart_1", reportMessage, driver);		
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}	
	}
	
	// Test Case No : 2
	public static void checkMainTab() throws IOException, InterruptedException
	{
		try
		{
			String orPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\Amazon_ObjectRepository.xls";
			String tdPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\searchItem.xls";
			readLocators(orPath);
			readTestData(tdPath);
			for(int i=1;i<data.length;i++)
			{
				String url = (String) data[i][0];
			//	String expected = (String) data[i][1];
				//String[] expectedValue = expected.split(",");
	
				driver.get(url);
				Thread.sleep(5000);
	
				//Locate department dropDown
				setValue(1);
				By deptDropDown = getBy(locatorType,value);
				WebElement ele_deptLink = driver.findElement(deptDropDown);
				Actions action = new Actions(driver);
				action.moveToElement(ele_deptLink).build().perform();
				Thread.sleep(5000);
				
				
				//locate drop down list
				setValue(2);
				By deptDropDownlist = getBy(locatorType,value);
				WebElement ele_deptDropDownList = driver.findElement(deptDropDownlist);
				if(isdisplayed(ele_deptDropDownList))
				{
					result = "Pass";
					reportMessage  = "Department drop down displayed";
				}
				else
				{
					result = "Fail";
					reportMessage = "Department drop down is not available";
				}
				Update_Report( result, "checkMainTabs", reportMessage, driver);
				
				//Locate amazon link
				setValue(11);
				By amazonLink = getBy(locatorType,value);
				WebElement ele_amazonLink = driver.findElement(amazonLink);
				Actions action1 = new Actions(driver);
				action1.moveToElement(ele_amazonLink).build().perform();
				
				if(isdisplayed(ele_amazonLink))
				{
					result = "Pass";
					reportMessage  = "Amazon link displayed";
				}
				else
				{
					result = "Fail";
					reportMessage = "Amazon link is not available";
				}
				Update_Report( "Pass", "checkMainTabs", reportMessage, driver);
				
				//Locate today's deals link
				setValue(12);
				By todaysDealsLink = getBy(locatorType,value);
				WebElement ele_dealsLink = driver.findElement(todaysDealsLink);
				Actions action2 = new Actions(driver);
				action2.moveToElement(ele_dealsLink).build().perform();
				if(isdisplayed(ele_dealsLink))
				{
					reportMessage = "Today's deals link is displayed";
				}
				else
				{
					reportMessage = "Today's deals link is is not available";
				}
			}
			Update_Report(result, "checkMainTabs", reportMessage, driver);
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}		
	}
	
	// Test Case No : 3
	public static void DepartmentDropDown() throws IOException, InterruptedException
	{
		try
		{
			String orPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\Amazon_ObjectRepository.xls";
			String tdPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\DepartmentDropDown.xls";
			readLocators(orPath);
			readTestData(tdPath);
			for(int i=1;i<data.length;i++)
			{
				String url = (String) data[i][0];
				String expected = (String) data[i][1];
				String[] expectedValue = expected.split("\\n");
	
				driver.get(url);
				Thread.sleep(5000);
	
				//Locate department dropDown
				setValue(1);
				By deptDropDown = getBy(locatorType,value);
				WebElement ele_deptLink = driver.findElement(deptDropDown);
				Actions action = new Actions(driver);
				action.moveToElement(ele_deptLink).build().perform();
				Thread.sleep(5000);
				
				//locate drop down list
				setValue(2);
				By deptDropDownlist = getBy(locatorType,value);
				WebElement ele_deptDropDownList = driver.findElement(deptDropDownlist);
				Thread.sleep(5000);			
				System.out.println(ele_deptDropDownList.getText());
				String[] actual = ele_deptDropDownList.getText().split("\\n");
				
				System.out.println(Arrays.deepToString(actual));
				System.out.println(Arrays.deepToString(expectedValue));
				
				if (Arrays.deepEquals(actual, expectedValue))
				{
					result = "Pass";
					reportMessage = "All drop down list items matches with expected values.";
				} 
				else 
				{
					result = "Fail";
					reportMessage = "All drop down list items does not matche with expected values.";
				}				
				Update_Report( result, "DepartmentDropDown_3", "Execution Completed", driver);
			}	
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}		
	}
	
	// Test Case No : 4
	public static void yourAccDropDown() throws IOException, InterruptedException
	{
		try
		{		
			String orPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\Amazon_ObjectRepository.xls";
			String tdPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\yourAccountDropDown.xls";
			readLocators(orPath);
			readTestData(tdPath);
			System.out.println(data.length);
			//System.out.println(Arrays.deepToString(data));
			for(int i = 1; i < data.length; i++)
			{				
				String url = (String) data[i][0];
				String expected = (String) data[i][1];
				String[] expectedValue = expected.split("\\n");
	
				driver.get(url);
				Thread.sleep(5000);			
	
				//Locate your account dropDown
				setValue(13);
				By yourAccDropDown = getBy(locatorType,value);
				WebElement ele_accountLink = driver.findElement(yourAccDropDown);
				Actions action = new Actions(driver);
				action.moveToElement(ele_accountLink).build().perform();
				Thread.sleep(8000);
				
				//locate drop down list
				setValue(14);
				By AccDropDownlist = getBy(locatorType,value);			
				WebElement dropdown = driver.findElement(AccDropDownlist);
				Thread.sleep(2000);
				
				System.out.println(" ");
				String[] actual= dropdown.getText().split("\\n");
				
				System.out.println(Arrays.deepToString(actual));
				System.out.println(Arrays.deepToString(expectedValue));
				if (Arrays.deepEquals(actual, expectedValue))
				{
					result = "Pass";
					reportMessage = "All drop down contians expected values";
				} 
				else 
				{
					result = "Fail";
					reportMessage = "All drop down doesn't contian expected values";
				}
			    Update_Report( result, "yourAccountDropDown_4", reportMessage, driver);				
			}
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}		
	}
	
	// Test Case No : 5
	public static void allMenuDropDown() throws InterruptedException, IOException
	{		
		try
		{
			String orPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\Amazon_ObjectRepository.xls";
			String tdPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\allMenuDropDown.xls";
			readLocators(orPath);
			readTestData(tdPath);
			
			for(int i = 1;i < data.length; i++)
			{
				String url = (String) data[i][0];
				String expected = (String) data[i][1];
				String[] expectedValue = expected.split("\\n");
				
				String subMenuName = (String) data[i][2];
				String expectedSubMenu = (String) data[i][3];
				String[] expectedSubMenuValues = expectedSubMenu.split("\\n");
	
				driver.get(url);
				Thread.sleep(5000);
	
				//Locate all drop down icon
			/*	setValue(26);
				By allDropDownIcon = getBy(locatorType, value);
				WebElement ele_allMenuIcon = driver.findElement(allDropDownIcon);
				System.out.println(ele_allMenuIcon.getAttribute("Value"));
				isdisplayed(ele_allMenuIcon);*/
				
				//Locate your account dropDown
				setValue(15);
				By allMenuDropDown = getBy(locatorType, value);
				WebElement ele_allMenuLink = driver.findElement(allMenuDropDown);
				Thread.sleep(5000);
			
				Select select= new Select(ele_allMenuLink);
				List<WebElement> dropdown = select.getOptions();
				
				//Thread.sleep(6000);
											
				String[] actual = new String[dropdown.size()];
				int index = 0;
				for(WebElement e: dropdown)
				{
					String s = e.getText();
					if(s != null) 
					{
						actual[index]=s;					
						index++;
					}
				}
				System.out.println(Arrays.toString(actual));
				System.out.println(Arrays.toString(expectedValue));
				
				if (Arrays.deepEquals(actual, expectedValue))
				{
					result = "Pass";
					reportMessage = "All drop down contians expected values";
				} 
				else 
				{
					result = "Fail";
					reportMessage = "All drop down doesn't contian expected values";
				}
				Update_Report( result, "allMenuDropDown_5", reportMessage, driver);
				
				if(result.equals("Pass"))
				{
					
					if(Arrays.asList(actual).contains(subMenuName))
					{
						result = "Pass";
	
						int indexOfMenu =  Arrays.asList(actual).indexOf(subMenuName);
						
						if(indexOfMenu + expectedSubMenuValues.length <= actual.length)
						{				
							indexOfMenu ++;
							for(int k=0; k<expectedSubMenuValues.length; k++)
							{	
								if(actual[indexOfMenu].equals(expectedSubMenuValues[k]))
								{
									indexOfMenu++;
								}
								else
								{
									result = "Fail";
									break;
								}
							}
							if(result.equals("Pass"))
							{
								reportMessage = "All sub menu contains expected values";
							}
							else
							{
								reportMessage = "All sub menu doesn't contains expected values";
							}
							Update_Report( result, "subMenuDropDown_5", "Expected sub menu items are more than Actualsub menu", driver);
						}
						else 
						{
							Update_Report( "Fail", "allMenuDropDown_5","Execution completed", driver);
						}
						
					} 
					else 
					{
						result = "Fail";
						reportMessage = "All sub menu doesn't contain expected values";
						Update_Report( result, "allMenuDropDown_5","All sub menu doesn't contain expected values", driver);				
					}
				}			
			}	
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}			
	}
	
	// Test Case No : 7
	public static void helpMenu() throws InterruptedException, IOException
	{	
		try
		{
			String orPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\Amazon_ObjectRepository.xls";
			String tdPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\helpMenu.xls";
			readLocators(orPath);
			readTestData(tdPath);
			for(int i=1;i<data.length;i++)
			{
				String url = (String) data[i][0];
				String expected = (String) data[i][1];
				String headerinfo = (String) data[i][2];
				String findText = (String) data[i][3];
				String helpPageTitle = (String) data[i][4];
				String[] expectedValue = expected.split(",");
	
				driver.get(url);
				Thread.sleep(5000);
	
				//Locate your help link
				setValue(16);
				By helpLink = getBy(locatorType,value);
				WebElement ele_accountLink = driver.findElement(helpLink);
				clickButton(ele_accountLink,obj_Name);
				Thread.sleep(3000);
				
				String actualTitle = driver.getTitle();
				Thread.sleep(3000);
				//System.out.println("actual title is:" +actualTitle);
				//System.out.println("expected title is: " +helpPageTitle);
	
				//Verify result
				result = verify(helpPageTitle,actualTitle);
				System.out.println(result);
				if(result.equals("Pass"))
				{
					Update_Report( result, "help page title", "displayed properly",driver);
				}
				else
				{
					Update_Report( result, "help page title", " not displayed properly",driver);
				}
				
				if(result.equals("Pass"))
				{
					//Locate help header info
					setValue(17);
					By byText = getBy(locatorType,value);
					WebElement ele_txt = driver.findElement(byText);
					String actual = ele_txt.getText();
					System.out.println(actual);
					
					result = verify(headerinfo,actual);
					System.out.println(result);
					if(result.equals("Pass"))
					{
						Update_Report( result, "help","header info is available", driver);
					}
					else
					{
						Update_Report( result, "help","header info is not available", driver);
					}
					
					//Locate help option links box
					setValue(18);
					By helpoptionsBox = getBy(locatorType,value);
					WebElement ele_helpOption = driver.findElement(helpoptionsBox);
					int noofelements = ele_helpOption.findElements(By.tagName("a")).size();	
					
					int actualLinksSize = 0;
					for(int j=0; j < noofelements; j++)
					{		
						String str = ele_helpOption.findElements(By.tagName("a")).get(j).getText().trim();
						if(str != null && !str.isEmpty()) 
						{
							actualLinksSize++;
						}
					}
				
					String [] actualLinks = new String[actualLinksSize];
					
					int index = 0;
					for(int j=0; j < noofelements; j++)
					{		
						String str = ele_helpOption.findElements(By.tagName("a")).get(j).getText().trim();
						if(str != null && !str.isEmpty()) 
						{
							if (index <= actualLinks.length ){
								actualLinks[index++] = str;
							}
						}
					}
					//System.out.println("printing array" + Arrays.deepToString(actualLinks));
					//System.out.println(Arrays.deepToString(expectedValue));
					if(Arrays.deepEquals(actualLinks, expectedValue))
					{
						result = "Pass";
						Update_Report( result, "helpMenu_6","Help menu link options are present", driver);
					}
					else
					{
						result = "Fail";
						Update_Report( result, "helpMenu_6","Help menu link options are not present", driver);
					}						
					//System.out.println("Two array result "+ result);
					
					if(result.equals("Pass"))
					{
						//Locate find more solution info
						setValue(19);
						By findinfoText = getBy(locatorType,value);
						WebElement ele_findTxt = driver.findElement(findinfoText);
						String actual2 = ele_findTxt.getText();
						System.out.println(actual2);
						
						result = verify(findText,actual2);
						System.out.println("Fina; result is " +  result);
						if(result.equals("Pass")) 
						{
			                Update_Report( result, "help page", "find more solution info is available", driver);
			                
						} 
						else 
						{
							Update_Report( result, "help page", "find more solution info is not available", driver);
				            
						}
		                //Locate find search box
		                setValue(20);
		                By findSearchbox = getBy(locatorType,value);
						WebElement ele_findSearch = driver.findElement(findSearchbox);
						if(isdisplayed(ele_findSearch))
						{
							Update_Report( "Pass", "helpOptions_7", "Execution completed", driver);
						}
						else
						{
							Update_Report( "Fail", "helpOptions_7", "Execution completed with failure", driver);
						}
					}
				}			
			}	
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
    }
	
	// Test Case No : 6
	public static void emptyCartValidation()
	{
		try
		{
			
			String orPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\Amazon_ObjectRepository.xls";
			String tdPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\emptyCartValidation.xls";
			readLocators(orPath);
			readTestData(tdPath);
			
			for(int i=1;i<data.length;i++)
			{
				String url = (String) data[i][0];
				String serachText = (String) data[i][1];
				String expected = (String) data[i][2];
				String deleteItemMsg = (String) data[i][3];
				String emptyCartHeaderText = (String) data[i][4];
				
				driver.get(url);
				Thread.sleep(5000);
				
				//Locate search text box
				setValue(3);
				By searchBox = getBy(locatorType,value);
				WebElement ele_SearchText = driver.findElement(searchBox);
				clear(ele_SearchText);
				enterText(ele_SearchText,serachText,obj_Name);

				//Locate serach button
				setValue(4);
				By searchButton = getBy(locatorType,value);
				WebElement ele_SearchBut = driver.findElement(searchButton);
				submitButton(ele_SearchBut,obj_Name);
				Thread.sleep(5000);
				
				//Locate record whose name contains apple iphone6
				setValue(21);
				By searchRec = getBy(locatorType,value);
				WebElement ele_SearchRec = driver.findElement(searchRec);
				clickButton(ele_SearchRec,obj_Name);
				Thread.sleep(4000);
				
				//Locate opened record page item
				setValue(6);
				By byText = getBy(locatorType,value);
				WebElement ele_txt = driver.findElement(byText);
				
				if(ele_txt.getText().contains(serachText))
					System.out.println("successfully opened selected product page");
				
				Thread.sleep(4000);
				//Locate addtoCart button
				setValue(7);
				By addToCart = getBy(locatorType,value);
				WebElement ele_addTocartBut = driver.findElement(addToCart);
				clickButton(ele_addTocartBut,obj_Name);
				Thread.sleep(4000);
			
				//Locate button on pop up
			/*	setValue(9);
				By popUpButton = getBy(locatorType,value);
				WebElement ele_NoThnaksButton = driver.findElement(popUpButton);
				clickButton(ele_NoThnaksButton,obj_Name);
				Thread.sleep(4000);*/
				
				//Locate cart count
				setValue(10);
				By cartCount = getBy(locatorType,value);
				WebElement ele_cartCountNo = driver.findElement(cartCount);
				String actual = ele_cartCountNo.getText();
				System.out.println(actual);
				//Verify Result
				result = verify(expected,actual);
				System.out.println(result);
				Update_Report( result, "addtoCart"," opened selected item page", driver);
				
				if(result.equals("Pass")) 
				{					
					//Locate cart button
					setValue(22);
					By cartButton = getBy(locatorType,value);
					WebElement ele_cartButton = driver.findElement(cartButton);
					clickButton(ele_cartButton,obj_Name);
					Thread.sleep(4000);
					
					//Locate delete link from cart
					setValue(23);
					By deleteButton = getBy(locatorType, value);
					WebElement ele_deleteButton = driver.findElement(deleteButton);
					clickButton(ele_deleteButton, obj_Name);
					Thread.sleep(4000);
					
					//Locate cart header info for deleted item
					setValue(24);
					By cartHeader = getBy(locatorType,value);
					WebElement ele_cartHeader = driver.findElement(cartHeader);
					String actualDeleteItemMsg = ele_cartHeader.getText();
				
					if(actualDeleteItemMsg.toLowerCase().contains(deleteItemMsg.toLowerCase()))
						Update_Report( "Pass", "addtoCart","Selected item deleted successfully", driver);
					else
						Update_Report( "Fail", "addtoCart","Selected item not deleted successfully", driver);
					
					//Click on cart button
					setValue(22);
					By emptycartButton = getBy(locatorType,value);
					WebElement ele_emptycartButton = driver.findElement(emptycartButton);
					clickButton(ele_emptycartButton,obj_Name);
					Thread.sleep(4000);
					
					//Locate empty cart header
					setValue(25);
					By emptycartHeader = getBy(locatorType,value);
					WebElement ele_emptycartHeader = driver.findElement(emptycartHeader);
					String actual2 = ele_emptycartHeader.getText();
					
					System.out.println(actual2);
					System.out.println(emptyCartHeaderText);
					//Verify Result
					result = verify(emptyCartHeaderText, actual2);
					System.out.println("Final Result: " + result);
					Update_Report( result, "emptyCartValidation_6"," opened selected item page", driver);
					
					//if(actual2.contains(emptyCartHeaderText)) {
					//	//	Update_Report( result, "emptyCart","deleted selected item successfully", driver);
					//	//Verify Result					
					//	Update_Report( "Pass", "emptyCartValidation_6"," opened selected item page", driver);
					//}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}		
	}
	
	//Test Case No : 8
	public static void searchItemAddQuantity() throws IOException
	{
		try
		{
		
			String orPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\Amazon_ObjectRepository.xls";
			String tdPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\searchItemandAddQuantity.xls";
			readLocators(orPath);
			readTestData(tdPath); 
			
			for(int i=1; i < data.length; i++)
			{
				String url = (String) data[i][0];
				System.out.println(url);
				String searchText = (String) data[i][1];
				String resultName = (String) data[i][2];
			    String expectedQuantity = (String) data[i][3];
			
				
				driver.get(url);
				Thread.sleep(5000);
				
				//Locate search text box
				setValue(3);
				By searchBox = getBy(locatorType,value);
				WebElement ele_SearchText = driver.findElement(searchBox);
				clear(ele_SearchText);
				enterText(ele_SearchText,searchText,obj_Name);

				//Locate search button
				setValue(4);
				By searchButton = getBy(locatorType,value);
				WebElement ele_SearchBut = driver.findElement(searchButton);
				submitButton(ele_SearchBut,obj_Name);
				Thread.sleep(5000);
				
				//Locate search result window
				setValue(27);
				By searchResWin = getBy(locatorType,value);
				WebElement ele_SearchResult = driver.findElement(searchResWin);
				if(ele_SearchResult.getText().contains(resultName))
				{
					result = "Pass";
					Update_Report( result, "searchResultWindow_8"," contains expected values", driver);
				} 
				else 
				{
					result = "Fail";
					Update_Report( result, "searchResultWindow_8","doesn't contain expected values", driver);
				}
				
				if(result.equals("Pass"))
				{
					//Locate book name in result window
					setValue(28);
					By selectBook = getBy(locatorType,value);
					WebElement ele_selectBookItem = driver.findElement(selectBook);
					clickButton(ele_selectBookItem,obj_Name);
					Thread.sleep(4000);
				
					//Locate header info
					setValue(29);
					By byText = getBy(locatorType,value);
					WebElement ele_txt = driver.findElement(byText);
					
					if(ele_txt.getText().contains(searchText))
						Update_Report( "Pass", "search text"," Successfully opened selected product page", driver);
					
					//Locate quantity section
					setValue(30);
					By quantitySec = getBy(locatorType,value);
					WebElement ele_quantitySection = driver.findElement(quantitySec);
					clickButton(ele_quantitySection,obj_Name);
					Thread.sleep(4000);
					
					//Locate quantity value section
					setValue(31);
					By quantityVal = getBy(locatorType,value);
					WebElement ele_quantityValue = driver.findElement(quantityVal);
					clickButton(ele_quantityValue,obj_Name);
					Thread.sleep(4000);
					
					//Locate addtoCart button
					setValue(32);
					By addToCart = getBy(locatorType,value);
					WebElement ele_addTocartBut = driver.findElement(addToCart);
					clickButton(ele_addTocartBut,obj_Name);
					Thread.sleep(9000);
					
					/*setValue(4);
					By searchButton1 = getBy(locatorType,value);
					WebElement ele_SearchBut1 = driver.findElement(searchButton1);
					submitButton(ele_SearchBut1,obj_Name);
					
					Actions actions = new Actions(driver);
					actions.moveToElement(ele_SearchBut1).build().perform();
					Thread.sleep(5000);*/
					
					//Locate cart count
					setValue(10);
					By cartCount = getBy(locatorType,value);
					WebElement ele_cartCountNo = driver.findElement(cartCount);
					String actual = ele_cartCountNo.getText();
					System.out.println(actual);
					//Verify Result
					result = verify(expectedQuantity, actual);
					System.out.println(result);
					if (result.equals("Pass"))
						reportMessage = "Cart count matching with expected value";
					else
						reportMessage = "Cart count is not matching with expected value";
					
					Update_Report( result, "Add to cart", reportMessage , driver);
				}
			}
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}		
		
	}
	
	//Test Case No : 9
	public static void saveForLater() throws IOException
	{
		try
		{			
			String orPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\Amazon_ObjectRepository.xls";
			String tdPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\searchItemandUpdateQuantity.xls";
			readLocators(orPath);
			readTestData(tdPath); 
			
			for(int i=1; i < data.length; i++)
			{
				String url = (String) data[i][0];
				System.out.println(url);
				String searchText = (String) data[i][1];
				String resultName = (String) data[i][2];
			    String expectedQuantity = (String) data[i][3];
			    String expectedHeaderInfo = (String) data[i][4];
				
				driver.get(url);
				Thread.sleep(5000);
				
				//Locate search text box
				setValue(3);
				By searchBox = getBy(locatorType,value);
				WebElement ele_SearchText = driver.findElement(searchBox);
				clear(ele_SearchText);
				enterText(ele_SearchText,searchText,obj_Name);

				//Locate search button
				setValue(4);
				By searchButton = getBy(locatorType,value);
				WebElement ele_SearchBut = driver.findElement(searchButton);
				submitButton(ele_SearchBut,obj_Name);
				Thread.sleep(5000);
				
				//Locate search result window
				setValue(27);
				By searchResWin = getBy(locatorType,value);
				WebElement ele_SearchResult = driver.findElement(searchResWin);
				if(ele_SearchResult.getText().contains(resultName))
				{
					result = "Pass";
					reportMessage = " contains expected values";
				} 
				else 
				{
					result = "Fail";
					reportMessage = "doesn't contain expected values";
				}
				
				Update_Report( result, "searchResultWindow_8", reportMessage, driver);
				
				if(result.equals("Pass"))
				{
					//Locate book name in result window
					setValue(28);
					By selectBook = getBy(locatorType,value);
					WebElement ele_selectBookItem = driver.findElement(selectBook);
					clickButton(ele_selectBookItem,obj_Name);
					Thread.sleep(4000);
				
					//Locate header info
					setValue(29);
					By byText = getBy(locatorType,value);
					WebElement ele_txt = driver.findElement(byText);
					
					if(ele_txt.getText().contains(searchText))
						Update_Report( "Pass", "search text"," Successfully opened selected product page", driver);
					
					//Locate quantity section
					setValue(30);
					By quantitySec = getBy(locatorType,value);
					WebElement ele_quantitySection = driver.findElement(quantitySec);
					clickButton(ele_quantitySection,obj_Name);
					Thread.sleep(4000);
					
					//Locate quantity value section
					setValue(31);
					By quantityVal = getBy(locatorType,value);
					WebElement ele_quantityValue = driver.findElement(quantityVal);
					clickButton(ele_quantityValue,obj_Name);
					Thread.sleep(4000);
					
					//Locate addtoCart button
					setValue(32);
					By addToCart = getBy(locatorType,value);
					WebElement ele_addTocartBut = driver.findElement(addToCart);
					clickButton(ele_addTocartBut,obj_Name);
					Thread.sleep(9000);
				
					//Locate cart count
					setValue(10);
					By cartCount = getBy(locatorType,value);
					WebElement ele_cartCountNo = driver.findElement(cartCount);
					clickButton(ele_cartCountNo,obj_Name);
					
					//Update the quantity section
					setValue(34);
					By updateQuantitySec = getBy(locatorType,value);
					WebElement ele_updateQuantitySection = driver.findElement(updateQuantitySec);
					clickButton(ele_updateQuantitySection,obj_Name);
					Thread.sleep(4000);
					
					//Locate quantity value section
					setValue(33);
					By updateQuantityVal = getBy(locatorType,value);
					WebElement ele_updateQuantityValue = driver.findElement(updateQuantityVal);
					clickButton(ele_updateQuantityValue,obj_Name);
					Thread.sleep(4000);
					
					//Locate cart count
					setValue(10);
					By updatesCartCount = getBy(locatorType,value);
					WebElement ele_updatedCartCountNo = driver.findElement(updatesCartCount);
					String actual = ele_updatedCartCountNo.getText();
					System.out.println(actual);
					//Verify Result
					result = verify(expectedQuantity, actual);
					System.out.println(result);
					if (result.equals("Pass"))
						reportMessage = "Cart count matching with expected value";
					else
						reportMessage = "Cart count is not matching with expected value";
					
					Update_Report( result, "cart count verification after edit", reportMessage , driver);
					
					//Locate save for later
					setValue(35);
					By saveForLater = getBy(locatorType,value);
					WebElement ele_saveForLater = driver.findElement(saveForLater);
					clickButton(ele_saveForLater,obj_Name);
					Thread.sleep(4000);
					
					//verify result
					setValue(36);
					By headerInfoAfterSavingItem = getBy(locatorType,value);
					WebElement ele_headerInfo = driver.findElement(headerInfoAfterSavingItem);
					String actual1 = ele_headerInfo.getText();
					System.out.println(actual1);
					//Verify Result
					result = verify(expectedHeaderInfo, actual1);
					System.out.println(result);
					if (result.equals("Pass"))
						reportMessage = "Headerinformation is matching with expected value";
					else
						reportMessage = "Headerinformation is not matching with expected value";
					
					driver.close();
				}
			}
		}
		catch(Exception e)
		{			
			System.out.println(e);
		}		
	}
	
	//Test Case No : 10
	public static void verifyAllDropDown() throws IOException
	{
		try
		{			
			String orPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\Amazon_ObjectRepository.xls";
			String tdPath = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\verifyAllDropDown.xls";
			readLocators(orPath);
			readTestData(tdPath); 
			
			for(int i=1; i < data.length; i++)
			{
				String url = (String) data[i][0];
				String searchText = (String) data[i][1];
				 
				driver.get(url);
				Thread.sleep(2000);
				
				//Locate search text box
				setValue(3);
				By searchBox = getBy(locatorType,value);
				WebElement ele_SearchText = driver.findElement(searchBox);
				clear(ele_SearchText);
				enterText(ele_SearchText,searchText,obj_Name);
				
				Thread.sleep(2000);

				//Locate search suggestions drop down
				setValue(37);
				By searchSuggestions = getBy(locatorType,value);System.out.println("value us " + value);
				WebElement ele_SearchSuggestions = driver.findElement(searchSuggestions);
				isdisplayed(ele_SearchSuggestions);
				
				//System.out.println(ele_SearchSuggestions.getText());
				List<WebElement> options = driver.findElements(searchSuggestions);
				String[] actual= new String[options.size()];
				int index=0;
				for(WebElement e: options)
				{
					String s = e.getText();
					if(s != null) 
					{
						actual[index]=s;					
						index++;
					}
				}
				
				System.out.println(Arrays.toString(actual));
				for(int m=0; m<actual.length; m++)
				{
					if(actual[m].contains(searchText) && 
							!actual[m].equals("in Electronics") && 
							!actual[m].equals("in Cell Phones & Accessories"))
					{
						result = "Pass";
						reportMessage = "search suggestion menu contains typed value";
					}
					else
					{
						result = "Fail";
						reportMessage = "search suggestion menu does not contains typed value";
						break;
					}
				}				
				Update_Report( result, "Verify for all drop down after entering text in search", reportMessage , driver);			
			}			
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}	
		
	}
}
