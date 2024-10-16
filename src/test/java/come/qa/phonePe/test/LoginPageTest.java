package come.qa.phonePe.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import come.qa.phonePe.base.BaseTest;
import come.qa.phonePe.constant.AppConstant;

public class LoginPageTest extends BaseTest{

	@Test(priority = 1)
	public void doLoginTest() throws InterruptedException {
		loginPage.doLogIn(prop.getProperty("userId"), prop.getProperty("password"));	
		//Assert.assertEquals(homePage.getHomePageTitle(), AppConstant.HOME_PAGE_TITLE);
	}
	
	
}
