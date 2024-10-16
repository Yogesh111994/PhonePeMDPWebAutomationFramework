package come.qa.phonePe.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import come.qa.phonePe.constant.AppConstant;
import come.qa.phonePe.utils.ElementUtil;
import come.qa.phonePe.utils.JavaScriptUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public static final Logger log = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}

	By userId = By.id("identifierId");
	By nextButton = By.xpath("//span[text()='Next']");
	By pawd = By.xpath("//input[@name='Passwd']");
	By password = By.xpath("//input[@name='Passwd']");

	public HomePage doLogIn(String emailId, String emailPassword) throws InterruptedException {
		eleUtil.waitForVisibilityOfElement(userId, 5).clear();
		log.info("Username: " + emailId + " :: " + "password :" + emailPassword);
		eleUtil.waitForVisibilityOfElement(userId, AppConstant.DEFAULT_MEDIUM_WAIT).sendKeys(emailId);
		eleUtil.doClick(nextButton);
		eleUtil.waitForVisibilityOfElement(password, 5).clear();
		eleUtil.waitForVisibilityOfElement(password, AppConstant.DEFAULT_MEDIUM_WAIT).click();
		eleUtil.waitForVisibilityOfElement(pawd, AppConstant.DEFAULT_MEDIUM_WAIT).sendKeys(emailPassword);
		eleUtil.doClick(nextButton);
		log.info("User is logged in");
		return new HomePage(driver);

	}
}
