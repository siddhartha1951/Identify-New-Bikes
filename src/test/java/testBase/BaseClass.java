package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import pageObjects.UpcomingBikes;
import utilityFiles.ExcelUtils;

public class BaseClass {

	public static WebDriver driver;
	public Properties p;
	public int choice;
	public Logger logger;
	Scanner sc;
	
	@BeforeTest(groups = { "Master", "Sanity", "Regression" })  //Grouping of Tests
	@Parameters({"browser","os"})   		// getting browser and os  from master.xml
	public void setup(String br, String os) throws IOException
	{	
				ChromeOptions chromeOptions=new ChromeOptions();
				chromeOptions.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
				chromeOptions.addArguments("--disable-notifications");
				
				EdgeOptions edgeOptions=new EdgeOptions();
				edgeOptions.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
				edgeOptions.addArguments("--disable-notifications");
				
				//loading properties file
				FileReader file = new FileReader(".//src//test//resources//config.properties");
				p = new Properties();
				p.load(file);
			
				//Logger
				logger = LogManager.getLogger(this.getClass());
							    
			    //Implementing selenium grid 
			    //If execution env is remote then run with selenium grid in different os
			    if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
			 	{	
				
				DesiredCapabilities capabilities=new DesiredCapabilities();
				
				//os
				if(os.equalsIgnoreCase("windows"))
				{
					capabilities.setPlatform(Platform.WIN11);
				}
				else if(os.equalsIgnoreCase("mac"))
				{
					capabilities.setPlatform(Platform.MAC);
				}
				else
				{
					System.out.println("No matching os..");
					return;
				}
				
				//browser
				switch(br.toLowerCase())
				{
				case "chrome" : capabilities.setBrowserName("chrome"); break;
				case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
				default: System.out.println("No matching browser.."); return;
				}
				
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			    }
			 //If execution_env is local then run in local system
			else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
			{
				//launching browser based on condition - locally
				switch(br.toLowerCase())
				{
				case "chrome": driver=new ChromeDriver(chromeOptions); break;
				case "edge": driver=new EdgeDriver(edgeOptions); break;
				default: System.out.println("No matching browser..");
							return;
				}
			}
			    
				
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				driver.manage().window().maximize();
				driver.get(p.getProperty("appURL"));
				
	}
	
	//Quit the browser
	@AfterTest(groups = { "Master", "Sanity", "Regression" })
	public void tearDown()
	{
		driver.quit();
	}
	

	//Write bike names in excel
	public void getBikeNames() throws InterruptedException, IOException {
		UpcomingBikes ub = new UpcomingBikes(driver);
		ExcelUtils excel = new ExcelUtils();
		String xlFile = System.getProperty("user.dir")+"\\testData\\testexcel.xlsx";
		List<String >bn = ub.bikeNames();
		for(int i=0;i<bn.size();i++) {
			excel.setCellData(xlFile, "UpComingBikes", i+3 , 0,bn.get(i));
		}
	}

	//Write bike prices in excel
	public void getBikePrices() throws InterruptedException, IOException {
		UpcomingBikes ub = new UpcomingBikes(driver);
		ExcelUtils excel = new ExcelUtils();
		String xlFile = System.getProperty("user.dir")+"\\testData\\testexcel.xlsx";
		List<String >bp = ub.bikePrices();
		for(int i=0;i<bp.size();i++) {
			excel.setCellData(xlFile, "UpComingBikes", i+3 , 1,bp.get(i));
		}
	}
	
	//Write bike launch date in excel
	public void getBikeLaunchDate() throws InterruptedException, IOException {
		UpcomingBikes ub = new UpcomingBikes(driver);
		ExcelUtils excel = new ExcelUtils();
		String xlFile = System.getProperty("user.dir")+"\\testData\\testexcel.xlsx";
		List<String >bp = ub.bikeLaunchDate();
		for(int i=0;i<bp.size();i++) {
			excel.setCellData(xlFile, "UpComingBikes", i+3 , 2,bp.get(i));
		}
		
	}

}
