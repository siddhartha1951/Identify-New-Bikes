package pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilityFiles.ExcelUtils;

public class UsedCars extends BasePage{

	public UsedCars(WebDriver driver) {
		super(driver);
	}
	
	//Elements 
	
	//Dropdown of Usedcars element
	@FindBy(xpath="//a[normalize-space()='Used Cars']")
	WebElement drpdownUsedCars;

	//Chennai option of the dropdown element
	@FindBy(xpath="//span[@onclick=\"goToUrl('/used-car/Chennai')\"]")
	WebElement findUsedCarsInChennai;
	
	//List of Popular car models available
	@FindBy(xpath="//ul[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']//li")
	List<WebElement> popularCarModels;
	
	//Actions
	
	//Click on Chennai from Used Cars dropdown
	public void clickFindUsedCars() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(drpdownUsedCars).perform();
		Thread.sleep(3000);
		findUsedCarsInChennai.click();
	}
	
	//get the popular car models present in Chennai and show on the window
	public void getPopularCarModels() throws IOException {
		ExcelUtils excel = new ExcelUtils();
		String xlFile = System.getProperty("user.dir")+"\\testData\\testexcel.xlsx";
		for(int i = 0; i<popularCarModels.size();i++){
			System.out.println(popularCarModels.get(i).getText());
			excel.setCellData(xlFile, "UsedCars", i+1, 0, popularCarModels.get(i).getText());
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("arguments[0].click();", popularCarModels.get(i));
		}
	}
	
	
	
	

}
