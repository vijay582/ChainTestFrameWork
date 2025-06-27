package testCase;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;

import pageObject.HomePage;
import pageObject.LoginPage;
import testBase.BaseClass;
import utlity.ExcelUtils;

@Listeners(ChainTestListener.class)
public class LoginTest extends BaseClass {
	private static final Logger logger = LogManager.getLogger(LoginTest.class);
	Alert alert;
	HomePage hp;
	LoginPage lp;
	int count = 0;
	
	@DataProvider(name ="LoginData")
	public Object[][] getLoginData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/TestData/ExcelData.xlsx";
        return ExcelUtils.getTestData(filePath, "Sheet1");
    }

	@Test(dataProvider ="LoginData" ,  priority = 1)
	public void verifyLogin(String Username, String Password) {
		lp = new LoginPage(driver);
		logger.info("Test started...");
		ChainTestListener.log("Entering username: standard_user");
		logger.debug("Entering Username .....");
		lp.setUserName(Username);
		ChainTestListener.log("Entering password: secret_sauce");
		logger.debug("Entering password .....");
		lp.setPassword(Password);
		ChainTestListener.log("Driver hit on login button");
		logger.debug("Clicked on  loginbutton .....");
		lp.clickLogin();

		System.out.println("Username: " + prop.getProperty("Username"));
		System.out.println("Password: " + prop.getProperty("Password"));
		prop.forEach((k, v) -> System.out.println(k + "=" + v));
	    
      
	}
   @Ignore
	@Test(priority = 2)
	public void verifyHomePage() {
       
		hp = new HomePage(driver);
		ChainTestListener.log("Clicked on add to cart");
		logger.debug("Adding item into Cart.....");
		hp.addToCart();

		ChainTestListener.log("Checked the  add to cart count");

		ChainTestListener.log("verifyed the application title");
		logger.debug("Clicked on titlet.....");
		hp.verifyTitle();
		ChainTestListener.log("fecthed  the application title");
		String actualTitle = driver.getTitle();
		ChainTestListener.log("Assert  the application title");
		logger.debug("Verify the title.....");
		Assert.assertEquals(actualTitle, "Swag Labs", "Page title doesn't  match!");
       
       
	}
	@Ignore
   @Test(priority =3)
	public void verifyCart() {
		hp.verifyCartCount();
		int expectedCount = 1; // Example expected count
		Assert.assertEquals(hp.verifyCartCount(), expectedCount);

		ChainTestListener.log("Checked the  add to cart count");
		System.out.println("Size of the cart:" + hp.verifyCartCount());
		ChainTestListener.log("Printed  the  add to cart count");
		hp.acceptAlert();
	}
   @Test()
   public void verilylogout()
   {  
	   
	   hp = new HomePage(driver);
	   logger.debug("Clicked on burgerButton...t.....");
	   hp.clickBurgerButton();
	   ChainTestListener.log("Clicked on Burger button"); 
       hp.clickONLogout();
       ChainTestListener.log("Clicked on Logout Button");
       logger.debug("Clicked on logoutt.....");
   }

}
