package come.qa.phonePe.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import come.qa.phonePe.exception.FrameworkException;

public class DriverFactory {

	protected WebDriver driver;
	protected Properties prop;
	protected OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static final Logger log = LogManager.getLogger(DriverFactory.class);
	public static String highlight = null;
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");

		System.out.println("The browser name is :" + browserName);
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				log.info("Running it on remote chrome browser");
				initRemoteDriver(browserName);
			} else {
				log.info("Running it on local chrome browser");
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				log.info("Running it on remote firefox browser");
				initRemoteDriver(browserName);
			} else {
				log.info("Running it on local firefox browser");
				// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
		case "edge":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				log.info("Running it on remote edge browser");
				initRemoteDriver(browserName);
			} else {
				log.info("Running it on edge edge browser");
				// driver = new EdgeDriver(optionsManager.getEdgeOptions());
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;
		case "safri":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Test case running on default browser");
			driver = new ChromeDriver();
			break;
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public void initRemoteDriver(String browserName) {

		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;
			default:
				log.info("wrong browser info.... can not run on grid remote machine....");
				break;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public Properties initProp() {

		prop = new Properties();
		FileInputStream fis = null;
		String envName = System.getProperty("envName");
		System.out.println("Test are running on :" + envName);
		try {
			if (envName == null) {
				fis = new FileInputStream("./src/test/resources/config/config.qa.properties");
				System.out.println("Test is running on default enviournment on :" + envName);
			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					fis = new FileInputStream("./src/test/resources/config/config.qa.properties");
					System.out.println("Test are running on :" + envName);
					break;
				case "dev":
					fis = new FileInputStream("./src/test/resources/config/config.dev.properties");
					System.out.println("Test are running on :" + envName);
					break;
				case "prod":
					fis = new FileInputStream("./src/test/resources/config/config.prod.properties");
					System.out.println("Test are running on :" + envName);
					break;
				case "stage":
					fis = new FileInputStream("./src/test/resources/config/config.stage.properties");
					System.out.println("Test are running on :" + envName);
					break;
				default:
					System.out.println("Please pass the right enviournment name:" + envName);
					throw new FrameworkException("ENV NAME IS WRONG ....");

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getScreenshot(String methodName) {
		TakesScreenshot screenshot=  (TakesScreenshot) getDriver();
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
		+ ".png";
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
