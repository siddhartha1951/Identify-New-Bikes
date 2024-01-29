package pageObjects;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountLogin extends BasePage{

	public AccountLogin(WebDriver driver) {
		super(driver);
	}
	
	//Elements
	
	//Login/SignUp button element
	@FindBy(xpath="//div[@class='h-sid h-sid-s fnt-14']")
	WebElement btnLoginSignUp;
	
	//Login with google button element
	@FindBy(xpath="//div[@class='d-tblc rel']//div[@data-track-label='Popup_Login/Register_with_Google']")
	WebElement btnRegWithGoogle;
	
	//Text area to give input email id
	@FindBy(xpath="//input[@id='identifierId']")
	WebElement txtEmail;
	
	//Next Button element
	@FindBy(xpath="//span[normalize-space()='Next']")
	WebElement clickNextButton;
	
	//Get the error message if email id is wrong
	@FindBy(xpath="//div[@class='o6cuMc Jj6Lae']")
	WebElement errorMesg;
	
	//Actions
	
	//Click on login//sign up button
	public void clickOnLoginSignUp() {
		btnLoginSignUp.click();
	}
	
	//Click on register with google button and switching the driver to "Sign in - Google Accounts" Title
	public void clickRegisterWithGoogle() {
		btnRegWithGoogle.click();
		Set<String> winHandleBefore = driver.getWindowHandles();  // Handle browser window switch driver to Sign in - Google Accounts Page

		for(String winHandle : winHandleBefore){
			String title = driver.switchTo().window(winHandle).getTitle();
			if(title.equals("Sign in - Google Accounts")) {
				driver.switchTo().window(winHandle);
			}
		}
	}
	
	//Send the email id to email input area
	public void loginWithEmail(String email) {
		txtEmail.sendKeys(email);
		clickNextButton.click();
	}
	
	//Get the error message shown after giving invalid email input
	public String getErrorMessage() {
		try{
			String er = errorMesg.getText();
			return er;
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
	
	

}
