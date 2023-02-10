package DiningEdgeAutomation.Mobile;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import DiningEdgeAutomation.Mobile.utils.AppiumUtils;
import DiningEdgeAutomation.Mobile.utils.TakeScreenshot;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest extends AppiumUtils {

	public String filePath;
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	TakeScreenshot takeScreenshot;
	public ExtentTest logExtent;
	boolean status = false;
	public ExtentReports extent;

	@BeforeClass
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "//target//ExtentReport.html", true);
		extent.addSystemInfo("User Name", "Automation Testing by Ⓜ️");
		extent.addSystemInfo("Environment", "DiningEdge Mobile Automation");
	}

	@BeforeMethod
	public void testMethodName(Method method) {
		stepStartMessage(method.getName());
	}

	public void stepStartMessage(String testStepName) {
		Reporter.log(" ", true);
		Reporter.log("***** STARTING TEST STEP:- " + testStepName + " *****", true);
		Reporter.log(" ", true);
	}

	@BeforeTest
	public void configureAppium() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//resources//data.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		int port = Integer.parseInt(prop.getProperty("port"));

		service = startService(ipAddress, port);

		// this config things for appium
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixcel_6");
		options.setApp(System.getProperty("user.dir") + "//src//main//java//resources//app-release-6.apk");

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		takeScreenshot = new TakeScreenshot(this.getClass().getSimpleName(), driver);
	}

	@AfterMethod
	public void take_screenshot_on_failure(ITestResult result) {
		takeScreenshot.takeScreenShotOnException(result);

		if (result.getStatus() == ITestResult.FAILURE) {
			status = true;
			logExtent.log(LogStatus.FAIL, "Test Case Failed :: " + result.getName());
			logExtent.log(LogStatus.FAIL, "Error is " + result.getThrowable());
			filePath = takeScreenshot.takeScreenshot();
			logExtent.log(LogStatus.FAIL, logExtent.addScreenCapture(filePath));// to add screenshot in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			logExtent.log(LogStatus.SKIP, "Test Case Skipped :: " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logExtent.log(LogStatus.PASS, "Test Case PASSED :: " + result.getName());
		}
		extent.endTest(logExtent);
	}

	@AfterClass
	public void endReport() {
		extent.flush();
		extent.close();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}