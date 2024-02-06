package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import utilityFiles.ExcelUtils;

public class UpcomingBikes extends BasePage{

	public UpcomingBikes(WebDriver driver) {
		super(driver);
	}

	//Elements
	
	//Homepage --> zigwheels logo 
	@FindBy(xpath="//div[@class='row qlc']//div[@class='col-lg-2']")
	WebElement homePage;					
	
	//New Bikes dropdown element
	@FindBy(xpath = "//div[@class='col-lg-12 pl-0']/ul/li[3]")
	WebElement drpdownNewBikes;
	
	//Upcoming Bikes option of the dropdown
	@FindBy(xpath="//span[@onclick=\"goToUrl('/upcoming-bikes')\"]")
	WebElement optionUpcomingBikes;
	
	//Manufacturer dropdown
	@FindBy(xpath="//select[@id='makeId']")
	WebElement selectBikeManufacturer;
	
	// Honda Text Element
	@FindBy(xpath="//h1[@class='mt-0 pt-2 pull-left zm-cmn-colorBlack']")
	WebElement isHondaBikes;
	
	//View More button element
	@FindBy(xpath ="//li[@class='txt-c clr moreModels mb-20']//span[@class='zw-cmn-loadMore']")
	WebElement viewMoreBikes;
	
	//Upcoming Bikes Elements
	//Available bikes Elements
	@FindBy(xpath="//ul[@class='mk-row srp_main_div clr']//li[contains(@class,'col-lg-4 txt-c rel modelItem')]")
	List<WebElement> bikesAvailable;
	
	//Available bikes' name text elements
	@FindBy(xpath="//a[@data-track-label='model-name']")	
	List<WebElement> modelName;
	
	//Available bikes' price text elements
	@FindBy(xpath="//a[@data-track-label='model-name']//following-sibling::div[1]")
	List<WebElement> price;
	
	//Available bikes' launch date elements
	@FindBy(xpath="//a[@data-track-label='model-name']//following-sibling::div[2]")
	List<WebElement> date;
	
	
	//Actions
	
	//Click on Zigwheels to navigate to Homepage
	public void navigateToHomePage() {
		homePage.click();
	}
	
	//Click on Upcoming bikes from New Bikes dropdown menu
	public void clickUpcomingBikes() throws InterruptedException
	{
		Actions action = new Actions(driver);
		action.moveToElement(drpdownNewBikes).build().perform();
		Thread.sleep(3000);
		optionUpcomingBikes.click();
	}
	
	//Select Honda from the Manufacturer using Select class
	public void selectBikeManufacturer(String Manufacturer) throws InterruptedException {
		Select objSelect = new Select(selectBikeManufacturer);
		objSelect.selectByVisibleText(Manufacturer);
	}
	
	//Check if Honda Bikes are shown
	public String validateHonda() {
		try {
			String hondabike = isHondaBikes.getText();
			return hondabike;
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
	
	//Scroll the page and click on view more button
	public void scrollToViewMore() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1300);", "");
		Thread.sleep(2000);		
		viewMoreBikes.click();
		Thread.sleep(3000);
	}
	
	//Get the bike name, price and launch date and print on console
	public void bikeDetails() throws InterruptedException, IOException {
		
		for(int i=0;i<modelName.size();i++) 
		{
			if(Integer.parseInt(bikesAvailable.get(i).getAttribute("data-price"))<400000 )
						{
							System.out.println(modelName.get(i).getText());
							System.out.println(price.get(i).getText());
							System.out.println(date.get(i).getText());
							System.out.println("=================================");
						}else {
							continue;
						}
				}
	}
	
	// Get only Names of the bikes
	public List<String> bikeNames() throws InterruptedException, IOException {
		List<String> lst = new ArrayList<String>();
		
		for(int i=0;i<modelName.size();i++) 
		{
			if(Integer.parseInt(bikesAvailable.get(i).getAttribute("data-price"))<400000 )
						{
							String bikeName = modelName.get(i).getText();
							lst.add(bikeName);
						}else {
							continue;
						}
				}
		return lst;
	}
	
	//Get only price of the bikes
	public List<String> bikePrices() throws InterruptedException, IOException {
		List<String> lst = new ArrayList<String>();
		
		for(int i=0;i<price.size();i++) 
		{
			if(Integer.parseInt(bikesAvailable.get(i).getAttribute("data-price"))<400000 )
						{
							String bikePrice = price.get(i).getText();
							lst.add(bikePrice);
						}else {
							continue;
						}
				}
		return lst;
	}
	
	//Get only Launch date of the bikes
	public List<String> bikeLaunchDate() throws InterruptedException, IOException {
		List<String> lst = new ArrayList<String>();
		
		for(int i=0;i<date.size();i++) 
		{
			if(Integer.parseInt(bikesAvailable.get(i).getAttribute("data-price"))<400000 )
						{
							String bikeLaunchDate = date.get(i).getText();
							lst.add(bikeLaunchDate);
						}else {
							continue;
						}
				}
		return lst;
	}
}
