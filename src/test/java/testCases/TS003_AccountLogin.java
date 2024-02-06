package testCases;

import java.io.IOException;

import org.testng.annotations.*;

import pageObjects.AccountLogin;
import testBase.BaseClass;
import utilityFiles.ExcelUtils;
import utilityFiles.Screenshots;

//@Listeners(utilityFiles.ExtentReportManager.class)
public class TS003_AccountLogin extends BaseClass{

	@Test(priority = 1,groups={"Master","Sanity"})
	public void clickLoginButton() throws InterruptedException, IOException {
		AccountLogin al = new AccountLogin(driver);
		ExcelUtils excel = new ExcelUtils();
		String xlFile = System.getProperty("user.dir")+"\\testData\\testexcel.xlsx";
		Screenshots ss = new Screenshots(driver);
		
		al.clickOnLoginSignUp();
		ss.ScreenShot("S7_LoginOptions");
		al.clickRegisterWithGoogle();
		String email = excel.getCellData(xlFile, "AccountVerification", 0, 1);
		al.loginWithEmail(email);
		Thread.sleep(3000);
		ss.ScreenShot("S8_ErrorMessage");
	}
	
	@Test(priority = 2,groups={"Master","Sanity"})
	public void checkLoginStatus() throws IOException{
		AccountLogin al = new AccountLogin(driver);
		ExcelUtils excel = new ExcelUtils();
		String xlFile = System.getProperty("user.dir")+"\\testData\\testexcel.xlsx";
		
		String txt = al.getErrorMessage();
		System.out.println(txt);
		excel.setCellData(xlFile, "AccountVerification", 1, 1, txt);
	}
}
