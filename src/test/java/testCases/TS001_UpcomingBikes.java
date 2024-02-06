package testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.UpcomingBikes;
import testBase.BaseClass;
import utilityFiles.ExcelUtils;
import utilityFiles.Screenshots;

//@Listeners(utilityFiles.ExtentReportManager.class)
public class TS001_UpcomingBikes extends BaseClass{

	@Test(priority =1,groups={"Master","Regression"})
	public void clickUpcomingBikes() throws InterruptedException, IOException{
		UpcomingBikes ub = new UpcomingBikes(driver);
		Screenshots ss = new Screenshots(driver);
		
		ss.ScreenShot("S1_Zigwheels");
		ub.clickUpcomingBikes();
		ss.ScreenShot("S2_UpcomingBikes");
		Thread.sleep(3000);
	}
	
	@Test(priority=2,groups={"Master","Regression"})
	public void selectManufacturer() throws InterruptedException, IOException {
		UpcomingBikes ub = new UpcomingBikes(driver);
		ExcelUtils excel = new ExcelUtils();
		String xlFile = System.getProperty("user.dir")+"\\testData\\testexcel.xlsx";
		String Manufacturer = excel.getCellData(xlFile, "UpComingBikes", 0, 1);
		ub.selectBikeManufacturer(Manufacturer);
		Screenshots ss = new Screenshots(driver);
		
		ss.ScreenShot("S3_HondaUpcomingBikes");
		Boolean honda = ub.validateHonda().startsWith(Manufacturer);
		Assert.assertEquals(honda, true);
		ub.scrollToViewMore();
		Thread.sleep(3000);
		ss.ScreenShot("S4_HondaUpcomingBikes2");

	}
	
	
	@Test(priority=3,groups={"Master","Regression"},dependsOnMethods={"selectManufacturer"})
	public void printBikeDetails() throws InterruptedException, IOException {
		UpcomingBikes ub = new UpcomingBikes(driver);
		getBikeNames();
		getBikePrices();
		getBikeLaunchDate();
		ub.bikeDetails();
		ub.navigateToHomePage();
	}
}
