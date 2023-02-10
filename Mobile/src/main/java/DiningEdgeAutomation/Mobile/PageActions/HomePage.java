package DiningEdgeAutomation.Mobile.PageActions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DiningEdgeAutomation.Mobile.utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;

public class HomePage extends AndroidActions {

	protected AndroidDriver driver;

	public HomePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@text='Order Edge']")
	private WebElement orderEdge;
	
	@FindBy(xpath = "//android.widget.EditText")
	private WebElement serach;

	public void clickOnOrderEdge() {
		waitForElementToClickable(orderEdge);
		orderEdge.click();
		logMessage("User clicks on the Order Edge Button!!");
	}

	public void clickOnSendButton() {
		List<WebElement> element = driver.findElements(By.xpath("//android.widget.TextView"));
		element.get(element.size() - 1).click();
		logMessage("User clicks on the send icon !!");
	}

	public void addUnits(String vendor, String product) {
		driver.findElement(By.xpath("//*[contains(@text,'" + vendor
				+ "')]/../../..//android.widget.TextView[@text='" + product + "']/../..//android.widget.TextView")).click();
		logMessage("User clicks on the add unit icon !!");
	}
	
	public void serachProduct(String product) {
		serach.sendKeys(product);
		logMessage("User enters the "+product+" to the search box !!");
	}
}
