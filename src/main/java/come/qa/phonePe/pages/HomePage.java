package come.qa.phonePe.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import come.qa.phonePe.constant.AppConstant;
import come.qa.phonePe.utils.ElementUtil;
import come.qa.phonePe.utils.JavaScriptUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	public static final Logger log = LogManager.getLogger(HomePage.class);
	
	By loggedInUserName=By.xpath("//h1[text()='Hello, MDP User03 !!']");
	By quarterDropdownTitle=By.xpath("//label[text()='Quarter']");
	By managerDropdownTitle=By.xpath("//div/p[text()='Manager']");
	By spanDropdownTitle=By.xpath("//label[text()='Span']");
	By quarterDropdownList=By.xpath("quarterDropdownTitle");
//	By quarterDropdownField=By.xpath("(//span[@class='ant-select-selection-search']//input[@type='search'])[1]");
//	By quarterDropdownField=By.xpath("//input[@id='rc_select_0']");
//	By quarterDropdownField=By.xpath("(//input[@class='ant-select-selection-search-input'])[1]");
//	By quarterDropdownField=By.xpath("//label[text()='Quarter']/parent::div//input[@type='search']");
	By quarterDropdownField=By.xpath("//label[text()='Quarter']/parent::div/descendant::span[text()='Q4 2024: Oct - Nov - Dec']");
	
    By spanDropdownList=By.xpath("(//span[@class='ant-select-selection-search']//input[@type='search'])[3]");
    By dataSummaryButton=By.xpath("//button[@type='button']/span[text()='Data Summary']");
    By actionPlanButton=By.xpath("//span[text()='Action Plan']");
    By resourcesButton=By.xpath("//span[text()='Resources']");
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		jsUtil = new JavaScriptUtil(this.driver);
	}
	
	public String getHomePageTitle() {
		String loginTitle = eleUtil.waitForTitleIs("Team Pulse", 5);
		log.info("The tilte of login page :" + loginTitle);
		return loginTitle;
	}

	public String getHomePageURL() {
		String loginURL = eleUtil.waitForURLContains("https://mdpserverqa.innoventestech.in/ui/", 5);
		log.info("The URL of Login Page: " + loginURL);
		return loginURL;
	}
	
	public String getLoggedInUserName() {
		 String LoggedInUser = eleUtil.getElement(loggedInUserName).getText();
		 log.info("Logged in User Name :"+LoggedInUser);
		 return LoggedInUser;	 
	}
	
	public String getQuarterTitleBarText() {
		String querterTitleText = eleUtil.getElement(quarterDropdownTitle).getText();
		log.info("Title of quarter dropdown :"+querterTitleText);
		return querterTitleText;
	}
	
	public String getManagerDropdownTitleBarText() {
		return  eleUtil.getElement(managerDropdownTitle).getText();
	}
	
	public String getSpanDropdownTitleBarText() {
		return  eleUtil.getElement(spanDropdownTitle).getText();
	}
	
	
	public List<String> getQuerterDropdownList() throws InterruptedException {
		Actions action= new Actions(driver);
//		eleUtil.waitForVisibilityOfElement(quarterDropdownField, AppConstant.DEFAULT_MEDIUM_WAIT);
		Thread.sleep(3000);
		boolean isDisplayed=eleUtil.getElement(quarterDropdownField).isDisplayed();
		System.out.println(isDisplayed);
		boolean enabled = eleUtil.getElement(quarterDropdownField).isEnabled();
		System.out.println(enabled);
		eleUtil.waitForVisibilityOfElement(quarterDropdownField, AppConstant.DEFAULT_MEDIUM_WAIT).click();;
//		action.moveToElement(eleUtil.getElement(quarterDropdownField)).build().perform();
//		eleUtil.getElement(quarterDropdownList).click();
//		eleUtil.waitForVisibilityOfElement(quarterDropdownField, AppConstant.DEFAULT_MEDIUM_WAIT);
		List<String> quarterList = eleUtil.getDropDownOption(quarterDropdownField);
		return quarterList;
		
	}
	
	public String getDataSummarButtonText() {
		String dataSummaryText=eleUtil.getElement(dataSummaryButton).getText();
		log.info("Data summary button text: "+dataSummaryText);
		return dataSummaryText;
	}
	
	public String getActionPlanButtonText() {
		String actionPlanText=eleUtil.getElement(actionPlanButton).getText();
		log.info("Action Plan button text: "+actionPlanText);
		return actionPlanText;
	}
	
	public String getResourcesButtonText() {
		String resourcesText=eleUtil.getElement(resourcesButton).getText();
		log.info("Resources button text: "+resourcesText);
		return resourcesText;
	}
	
}
