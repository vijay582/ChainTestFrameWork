package testCase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;

import pageObject.HomePage;
import pageObject.LoginPage;
import testBase.BaseClass;

@Listeners(ChainTestListener.class)
public class LoginTest extends BaseClass {

	HomePage hp;
	LoginPage lp;
	int count = 0;

	@Test(priority = 1)
	public void verifyLogin() {
		lp = new LoginPage(driver);

		ChainTestListener.log("Entering username: standard_user");
		lp.setUserName(prop.getProperty("Username"));
		ChainTestListener.log("Entering password: secret_sauce");
		lp.setPassword(prop.getProperty("Password"));
		ChainTestListener.log("Driver hit on login button");
		lp.clickLogin();

		System.out.println("Username: " + prop.getProperty("userName"));
		System.out.println("Password: " + prop.getProperty("password"));

	}

	@Test(priority = 2)
	public void verifyHomePage() {

		hp = new HomePage(driver);
		ChainTestListener.log("Clicked on add to cart");
		hp.addToCart();

		ChainTestListener.log("Checked the  add to cart count");

		ChainTestListener.log("verifyed the application title");
		hp.verifyTitle();
		ChainTestListener.log("fecthed  the application title");
		String actualTitle = driver.getTitle();
		ChainTestListener.log("Assert  the application title");
		Assert.assertEquals(actualTitle, "Swag Labs", "Page title doesn't match!");

	}
   @Test(priority =3)
	public void verifyCart() {
		hp.verifyCartCount();
		int expectedCount = 1; // Example expected count
		Assert.assertEquals(hp.verifyCartCount(), expectedCount);

		ChainTestListener.log("Checked the  add to cart count");
		System.out.println("Size of the cart:" + hp.verifyCartCount());
		ChainTestListener.log("Printed  the  add to cart count");
	}

}
