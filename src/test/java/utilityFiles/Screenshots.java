package utilityFiles;

import java.io.File;
import java.io.IOException;
import testBase.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.BasePage;

public class Screenshots extends BasePage{
	public Screenshots(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}

	//Whole Page Screenshot
	public String ScreenShot(String path) throws IOException {
		String p = System.getProperty("user.dir")+"\\screenshots\\";
		p+=path+".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(p);
		FileUtils.copyFile(src, trg);
		return p;
	}
}
