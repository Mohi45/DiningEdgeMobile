package DiningEdgeAutomation.Mobile;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static DiningEdgeAutomation.Mobile.utils.ConfigPropertyReader.getProperty;

import DiningEdgeAutomation.Mobile.PageActions.HomePage;
import DiningEdgeAutomation.Mobile.PageActions.LoginPage;
import DiningEdgeAutomation.Mobile.utils.CustomFunctions;

public class SendOGTest extends BaseTest {

	LoginPage loginPage;
	HomePage homepage;
	
	@BeforeTest
	public void intilizeObjects() {
		loginPage = new LoginPage(driver);
		homepage = new HomePage(driver);
	}
	
	@Test
	public void sendOGTest_FromOrder_Guide() {
		loginTest();
		homePageActions();
	}

	public void loginTest() {
		logExtent = extent
				.startTest("Login Test");
		loginPage.loginToThePage(getProperty("username"), getProperty("password"));
	}
	
	public void homePageActions() {
		homepage.clickOnOrderEdge();
		CustomFunctions.hardWaitForScript();
		homepage.clickOnSendButton();
		CustomFunctions.hardWaitForScript();
		homepage.addUnits("Automation Cheney", "PFG");
		CustomFunctions.hardWaitForScript();
	}

}
