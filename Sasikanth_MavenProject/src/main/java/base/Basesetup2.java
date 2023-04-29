package base;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Excels.Excels;
import Utils.CommonUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Sheet;



public class Basesetup2 {

	public static String baseUrl = "http://fossiladmin.fnfsale.in/AddCategory";
	public static String baseUIUrl = "https://wsi.revalsys.com/";
	public static WebDriver driver;
	public static CommonUtils _Utility = new CommonUtils();
	public static Sheet Sheet0 = CommonUtils.readExcelSheet(Excels.filepath2 + "Roles Data.xls",0);
	public static WebDriverWait Wait;
	
	public static WebDriver getDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver getDriver(String browserName) {
		switch (browserName) {
		case "Firefox":
			driver = initFirefoxDriver();
			break;
		case "Chrome":
			driver = initChromeDriver();
			break;
		default:
			// APP_LOGS.info("Browser : " + browserType + " is invalid, Launching Chrome as
			// default browser of choice..");
			driver = initChromeDriver();
		}
		return driver;
	}

	private static WebDriver initChromeDriver() {
		
		WebDriverManager.chromedriver().setup();
		return driver = new ChromeDriver();
	}

	private static WebDriver initFirefoxDriver() {
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public static void OpenBaseUrl() {
		driver.get(baseUrl);
	}

}
