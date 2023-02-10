package DiningEdgeAutomation.Mobile.PageActions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DiningEdgeAutomation.Mobile.utils.AndroidActions;
import DiningEdgeAutomation.Mobile.utils.CustomFunctions;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends AndroidActions {

	protected AndroidDriver driver;

	public LoginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@text='Enter username']")
	private WebElement user;

	@FindBy(xpath = "//*[@text='Enter password']")
	private WebElement pass;

	@FindBy(xpath = "//android.widget.TextView[@text='Sign in']")
	private WebElement signBtn;

	public void loginToThePage(String userName, String password) {
		CustomFunctions.hardWaitForScript();
		user.click();
		user.sendKeys(userName);
		logMessage("User enters the userName = " + userName);
		pass.click();
		pass.sendKeys(password);
		logMessage("User enters the password = " + password);
		driver.hideKeyboard();
		signBtn.click();
		logMessage("User clicks on the Sign In Button !!");
	}

}
