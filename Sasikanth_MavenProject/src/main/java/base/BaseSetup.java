package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import Utils.CommonUtils;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseSetup {

	public static Properties CONFIG;
	// public static Logger APP_LOGS = Logger.getLogger("LpaasLogger");
	public static CommonUtils _Utility = new CommonUtils();
	public static String baseUrl = "https://www.manyavar.com/";
	//public static String baseUrlUi=""
	public static String browserType;
	public static String userDirectory = System.getProperty("user.dir");
	public static String user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36";
	public static String mobile_user_agent = "Mozilla/5.0 (Linux; U; Android 7.1.1; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.";
	public static WebDriver driver;

	public BaseSetup() {
		CONFIG = new Properties();
		FileInputStream cn = null;
		try {
			cn = new FileInputStream(userDirectory + "\\src\\java\\com\\lpaas\\utils\\config.properties");
			CONFIG.load(cn);
		} catch (IOException e) {
			e.getMessage();
		}
	}

	/**
	 * Set driver according to the preference - global.
	 * 
	 * @param driverType -Java generic return type
	 * @return
	 */

	public static WebDriver getDriver() {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;
	}

	public WebDriver getDriver(String browserName) {
		WebDriver driver = null;
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
		}
		return driver;
	}

	public <T> T getDriver(T driverType) {
		return (T) setDriver(browserType, driverType);
	}

	/**
	 * Set driver according to the preference - hard-coded.
	 * 
	 * @param driverType
	 * @param browsername
	 * @return
	 */
	public <T> T getDriver(T driverType, String browsername) {
		return (T) setDriver(browsername, driverType);
	}

	/**
	 * Match and call the appropriate driver
	 * 
	 * @param browserType
	 * @param driverType
	 */

	@SuppressWarnings("unchecked")
	private <T> T setDriver(String browserType, T driverType) {
		switch (browserType.toLowerCase()) {
		case "chrome":
			driverType = (T) initChromeDriver();
			break;
		case "firefox":
			driverType = (T) initFirefoxDriver();
			break;
		case "chromemobile":
			driverType = (T) initMobileChromeDriver();
			break;
		default:
			// APP_LOGS.info("Browser : " + browserType + " is invalid, Launching Chrome as
			// default browser of choice..");
			driverType = (T) initChromeDriver();
		}
		return driverType;
	}

	private static WebDriver initChromeDriver() {
		/*
		 * CONFIG = new Properties(); FileInputStream cn = null; try { cn = new
		 * FileInputStream(userDirectory +
		 * "\\src\\java\\com\\shopster\\utils\\config.properties"); CONFIG.load(cn); }
		 * catch (IOException e) { e.getMessage(); }
		 */
		// APP_LOGS.info("Launching google chrome with new profile..");
		String OS = System.getProperty("os.name").toString().toLowerCase();
		System.out.println(OS);
		// APP_LOGS.debug(OS);
		boolean macFlag = false;
		ChromeOptions options = new ChromeOptions();
		/*
		 * if(OS.indexOf("windows") >= 0) { //macFlag = true;
		 * System.setProperty("webdriver.chrome.driver",
		 * CONFIG.getProperty("chromedriverpath")); } else if(OS.indexOf("nix") >= 0 ||
		 * OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ) {
		 * APP_LOGS.debug(CONFIG.getProperty("chromedriverpath_linux"));
		 * System.setProperty("webdriver.chrome.driver",
		 * CONFIG.getProperty("chromedriverpath_linux"));
		 * options.addArguments("--headless"); options.addArguments("--no-sandbox");
		 * options.addArguments("--disable-dev-shm-usage");
		 * //options.addArguments("screenshot"); } else {
		 */
		System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("chromedriverpath"));
		// }
		// System.setProperty("webdriver.http.factory", "apache");

		if (CONFIG.getProperty("isBlockerEnable").contains("true")) {
			options.addExtensions(new File(userDirectory + "/src/test/resources/extensions/uBlock.crx"));
			options.addExtensions(
					new File(userDirectory + "/src/test/resources/extensions/chrome-ga-optout-extension.crx"));
		} else
			options.addArguments("--disable-extensions");

		options.addArguments("--allow-no-sandbox-job");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("disable-infobars");
		// options.addArguments("--start-maximized");
		options.addArguments("--disable-plugins-discovery");
		options.addArguments("--profile-directory=Default");
		options.addArguments("--fast-start");
		options.addArguments("--disable-gpu");
		options.addArguments("--user-agent=" + user_agent);
		options.addArguments("-incognito");

		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		logPrefs.enable(LogType.DRIVER, Level.ALL);

		// options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		options.setCapability("goog:loggingPrefs", logPrefs);

		// Hack for - Chrome is being controlled by automated test software
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		String downloadFilepath = System.getProperty("user.dir") + "/downloads/";
		// APP_LOGS.debug("Chrome Download path set to: " + downloadFilepath);
		File downloadt_folder = new File(downloadFilepath);
		if (!downloadt_folder.exists()) {
			downloadt_folder.mkdir();
		}

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.default_directory", downloadFilepath);
		prefs.put("profile.default_content_setting_values.notifications", 1);

		options.setExperimentalOption("prefs", prefs);
		WebDriver driver = null;
		ChromeDriverService driverService = ChromeDriverService.createDefaultService();
		try {
			driver = new ChromeDriver(driverService, options);
		} catch (Throwable t) {
			// APP_LOGS.error(t.getMessage());
			t.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if (macFlag == true) {
			Dimension dim = new Dimension(1250, 800);
			driver.manage().window().setSize(dim);
		}
		return driver;
	}

	/**
	 * Chromedriver windows web emulator implementation
	 * 
	 * @return Web-driver type object
	 */
	public static WebDriver initMobileChromeDriver() {
		String OS = System.getProperty("os.name").toString().toLowerCase();
		// APP_LOGS.debug("Detecting os : " + OS);
		ChromeOptions options = new ChromeOptions();
		if (OS.indexOf("mac") >= 0) {
			System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("chromedriverpath_mac"));
		} else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0) {
			System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("chromedriverpath_linux"));
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
		} else {
			System.setProperty("webdriver.chrome.driver", CONFIG.getProperty("chromedriverpath"));
		}

		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Nexus 5");
		// mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us;
		// Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko)
		// Chrome/18.0.1025.166 Mobile Safari/535.19");
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		logPrefs.enable(LogType.DRIVER, Level.ALL);

		options.setExperimentalOption("mobileEmulation", mobileEmulation);
		options.addArguments("--allow-no-sandbox-job");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-plugins-discovery");
		options.addArguments("--profile-directory=Default");
		options.addArguments("--disable-notifications");
		options.addArguments("--fast-start");
		options.addArguments("--disable-gpu");
		options.addArguments("--user-agent=" + mobile_user_agent);

		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

		options.setCapability("goog:loggingPrefs", logPrefs);
		// options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

		/* Hack for - Chrome is being controlled by automated test software */
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		options.setExperimentalOption("prefs", prefs);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().setPosition(new Point(0, 0));
		// driver.manage().window().setSize(new Dimension(376, 730));
		driver.manage().window().setSize(new Dimension(450, 720));
		return driver;
	}

	/**
	 * Firefox driver web implementation
	 * 
	 * @return WebDriver
	 */
	private static WebDriver initFirefoxDriver() {
		// APP_LOGS.info("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", CONFIG.getProperty("firefoxdriverpath"));
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public static void OpenBaseUrl(WebDriver _OpenBrowserDriver) {
		System.setProperty("java.net.preferIPv4Stack", "true");
		_OpenBrowserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// _OpenBrowserDriver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		try {
			_OpenBrowserDriver.navigate().to(baseUrl);
			CommonUtils.set_flag_checkTimeStamp(_OpenBrowserDriver);
		} catch (Throwable t) {
			JavascriptExecutor js = (JavascriptExecutor) _OpenBrowserDriver;
			for (int i = 0; i < 25; i++) {
				CommonUtils.Thread_Sleep(1000);
				String isReady = js.executeScript("return document.readyState").toString();
				// To check page ready state.
				if (isReady.equals("complete")) {
					// APP_LOGS.debug("is Dom Ready: " + isReady);
					break;
				}
			}
			try {
				// APP_LOGS.debug("---Trying to Stop loading-----");
				js.executeScript("return window.stop()");
				// APP_LOGS.debug("---Done-----");
				_OpenBrowserDriver.navigate().to(baseUrl);
				CommonUtils.set_flag_checkTimeStamp(_OpenBrowserDriver);
				// APP_LOGS.debug("---Navigation Done-----");
			} catch (Throwable er) {
			}
			CommonUtils.Thread_Sleep(1000);
			_OpenBrowserDriver.navigate().to(baseUrl);
			CommonUtils.set_flag_checkTimeStamp(_OpenBrowserDriver);
		}
	}

}
