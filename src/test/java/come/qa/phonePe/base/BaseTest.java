package come.qa.phonePe.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import come.qa.phonePe.factory.DriverFactory;
import come.qa.phonePe.pages.HomePage;
import come.qa.phonePe.pages.LoginPage;

public class BaseTest {

	DriverFactory df;
	protected Properties prop;
	protected WebDriver driver;

	@BeforeTest
	public void setup() {

		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage=new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	protected LoginPage loginPage;
	protected HomePage homePage;
}
