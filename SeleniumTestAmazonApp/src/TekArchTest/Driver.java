package TekArchTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

//combination of modular framework and color and hyperlink excel
public class Driver 
{
	static WebDriver driver;
	
	
	public static void main(String[] args) throws Exception
	{
		String dt_path = "C:\\Priyanka\\TekArch\\SeleniumTestAmazonApp\\TestSuit.xls";
		String[][] recData = ReUsableMethods.readXlSheet(dt_path, "Sheet1");

		for(int i = 1; i < recData.length; i++)
		{
			String execute = recData[i][1];
			
			if(execute.equalsIgnoreCase("Y"))
			{
				String testCase = recData[i][2];
				String chromeExecute = recData[i][3];
				String firefoxExecute = recData[i][4];
				String ieExecute = recData[i][5];			
		
				try
				{
				    if(chromeExecute.equalsIgnoreCase("Y"))
		            { 		 
						System.setProperty("webdriver.chrome.driver", "C:\\Priyanka\\TekArch\\chromedriver.exe");
					    driver = new ChromeDriver();
					   					   
					    ReUsableMethods.startReport(testCase, "C:\\Priyanka\\TekArch\\", "Chrome");

					    // Java Reflection
						Method tc = AutomationScripts.class.getMethod(testCase);
						tc.invoke(tc);
							
						ReUsableMethods.Update_Excel(dt_path, "Sheet1", i, 3);
		            }
					if(firefoxExecute.equalsIgnoreCase("Y"))
					{
						System.setProperty("webdriver.gecko.driver", "C:\\Priyanka\\TekArch\\geckodriver.exe");
						driver =  new FirefoxDriver();
						
						ReUsableMethods.startReport(testCase, "C:\\Priyanka\\TekArch\\", "Firefox");
						System.out.println("before invoke");
						
						Method tc = AutomationScripts.class.getMethod(testCase);
						tc.invoke(tc);
						System.out.println("after invoke");
						ReUsableMethods.Update_Excel(dt_path, "Sheet1", i, 4);
					}
					if (ieExecute.equalsIgnoreCase("Y"))
		            { 		 
						 System.out.println("in IE");
						 System.setProperty("webdriver.ie.driver", "C:\\Priyanka\\TekArch\\IEDriverServer.exe");
					     driver = new InternetExplorerDriver();
					     driver.manage().window().maximize();
					     ReUsableMethods.startReport(testCase, "C:\\Priyanka\\TekArch\\", "IE");				      
					   
					     Method tc = AutomationScripts.class.getMethod(testCase);
						 tc.invoke(tc);
							
						 ReUsableMethods.Update_Excel(dt_path, "Sheet1", i, 5);
		            }
					ReUsableMethods.driver.close();			
					ReUsableMethods.bw.close();
					
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}	
	}
}
