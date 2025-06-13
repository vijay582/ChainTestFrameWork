package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.BaseClass;
import utlity.WaitUtils;

public class LoginPage extends BaseClass {
	WebDriver driver;
	WebDriverWait wait;
	 /**
     * Constructor to initialize LoginPage elements.
     * @param driver WebDriver instance used to locate elements.
     */

	public LoginPage(WebDriver driver) {
        this.driver = driver;
        System.out.println("Driver instance before PageFactory: " + driver);

        PageFactory.initElements(driver, this);  // Initializes PageFactory elements
    }


	
	@FindBy(id ="user-name")
	private WebElement userName;
	
	/**
     * Enters the username in the login field.
     * @param username The username to be entered.
     */

	
	@FindBy(id ="password")
	private WebElement password;
	
	@FindBy(id ="login-button")
	private WebElement loginButton;
	
	public void setUserName(String username) 
	{ 
		WaitUtils.waitForElement(driver, userName);
		userName.sendKeys(username);
	}
	
	
	public void setPassword(String pwd)
	{
		WaitUtils.waitForElement(driver, password);
		password.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		WaitUtils.waitForElement(driver, loginButton);
		loginButton.click();
	}
}
