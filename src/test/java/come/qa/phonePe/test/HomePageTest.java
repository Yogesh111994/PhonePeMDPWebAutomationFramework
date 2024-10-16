package come.qa.phonePe.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import come.qa.phonePe.base.BaseTest;
import come.qa.phonePe.constant.AppConstant;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class HomePageTest extends BaseTest {

	
	@Severity(SeverityLevel.BLOCKER)
	@BeforeClass
	public void loginTest() {
		try {
			homePage=loginPage.doLogIn(prop.getProperty("userId"),prop.getProperty("password"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	@Test(priority = 2)
	public void HomePageTitleTest() {
		String actualLoginPageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualLoginPageTitle, AppConstant.HOME_PAGE_TITLE);
	}
	@Test
	public void HomePageURLTest() {
		String actualHomePageURL=homePage.getHomePageURL();
		Assert.assertTrue(actualHomePageURL.contains(AppConstant.HOME_PAGE_URL));
	}
	@Test
	public void loggedInUserNameTest() {
		String loggedInUser=homePage.getLoggedInUserName();
		Assert.assertEquals(loggedInUser, AppConstant.LOGGED_IN_USER_NAME);
	}
	
	@Test
	public void quarterDropdownTitleTest() {
		String actualQuarterTitleBarText = homePage.getQuarterTitleBarText();
		Assert.assertEquals(actualQuarterTitleBarText, AppConstant.QUARTER_DROPDOWN_TITLE_TEXT);	
	}
	
	@Test
	public void managerDropdownTitleTest() {
		String actualManagerTitleBarText = homePage.getManagerDropdownTitleBarText();
		Assert.assertEquals(actualManagerTitleBarText, AppConstant.MANAGER_DROPDOWN_TITLE_TEXT);	
	}
	
	@Test
	public void SpanDropdownTitleTest() {
		String actualSpanTitleBarText = homePage.getSpanDropdownTitleBarText();
		Assert.assertEquals(actualSpanTitleBarText, AppConstant.SPAN_DROPDOWN_TITLE_TEXT);	
	}
	
	@Test(enabled=true)
	public void quarterDropdownListTest() throws InterruptedException {
		List<String> querterDropdownList = homePage.getQuerterDropdownList();
		Assert.assertTrue(querterDropdownList.containsAll(AppConstant.QUARTER_DROPDOWN_LIST));
	}
	
	
	@Test
	public void dataSummaryButtonTest() {
		String actualDataSummaryButtonText = homePage.getDataSummarButtonText();
		Assert.assertEquals(actualDataSummaryButtonText, AppConstant.DATA_SUMMARY_BUTTON_TEXT);	
	}
	
	@Test
	public void actionPlanButtonTest() {
		String actualActionPlanButtonText = homePage.getActionPlanButtonText();
		Assert.assertEquals(actualActionPlanButtonText, AppConstant.ACTION_PLAN_BUTTON_TEXT);	
	}
	
	@Test
	public void resourcesButtonTest() {
		String actualResourcesButtonText = homePage.getResourcesButtonText();
		Assert.assertEquals(actualResourcesButtonText, AppConstant.RESOURCES_BUTTON_TEXT);	
	}
	
	
	
	
}
