package testCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;

import pageObjects.UpcomingBikes;
import pageObjects.UsedCars;
import testBase.BaseClass;
import utilityFiles.Screenshots;

//@Listeners(utilityFiles.ExtentReportManager.class)
public class TS002_UsedCars extends BaseClass{

	@Test(priority = 1,groups={"Master","Regression"})
	public void findUsedCars() throws InterruptedException, IOException {
		UsedCars uc = new UsedCars(driver);
		UpcomingBikes ub = new UpcomingBikes(driver);
		Screenshots ss = new Screenshots(driver);
		
		uc.clickFindUsedCars();
		ss.ScreenShot("S5_UsedCars");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1500);", "");
		ss.ScreenShot("S6_UsedCars2");

		uc.getPopularCarModels();
		ub.navigateToHomePage();
	}
	
}
