package testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
@Listeners(ChainTestListener.class)

public class BaseClass {
	
	public static WebDriver driver;
	
	public  Properties  prop;
	
	
	  @Parameters("browser")
	 @BeforeClass
	public void setUp(String browser) throws IOException 
	{ 
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/test/resources/config.Properties");
	   prop.load(fis);
		if(browser.equalsIgnoreCase("chrome"))
		{
		  driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) 
		{
			driver = new EdgeDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	} 
	
	
	@AfterMethod
	public void attachScreenshotForFailedTestCase(ITestResult result )
	{
		if(!result.isSuccess()) 
		{
			ChainTestListener.embed(getScreenshot(), "image/png");
		}
	}
	
	public byte[] getScreenshot()
	{
	   return 	((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	@AfterClass
	public void tearDown() 
	{
         driver.close();
	}

}
