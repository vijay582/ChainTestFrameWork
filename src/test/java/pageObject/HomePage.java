package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utlity.WaitUtils;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="(//button[text()='ADD TO CART'])[1]")
	private WebElement textaddToCart;
	
	@FindBy(xpath ="//title[text()='Swag Labs']")
	private WebElement title;
	@FindBy(id="shopping_cart_container")
	private WebElement cartCount;
	
	public void addToCart()
	{
		textaddToCart.click();

	}
	
	public void verifyTitle()
	{
		title.getText();
	}
	public int  verifyCartCount()
	{ 
		WaitUtils.waitForElement(driver, cartCount);
		return Integer.parseInt(cartCount.getText());
	}

	}


