package DiningEdgeAutomation.Mobile.utils;

import static DiningEdgeAutomation.Mobile.utils.ConfigPropertyReader.getProperty;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;

/**
 * @author Mohit Kumar
 *
 */
public class TakeScreenshot {
	AndroidDriver driver;
	String testname;
	String screenshotPath = "/target/Screenshots";
	public TakeScreenshot(String testname, AndroidDriver driver) {
		this.driver = driver;
		this.testname = testname;
	}

	public String takeScreenshot() {
		screenshotPath = (getProperty("screenshot-path") != null) ? getProperty("screenshot-path") : screenshotPath;
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_ms");
		Date date = new Date();
		String date_time = dateFormat.format(date);
		File file = new File(System.getProperty("user.dir") + File.separator + screenshotPath + File.separator
				+ this.testname + File.separator + date_time);
		boolean exists = file.exists();
		if (!exists) {
			new File(System.getProperty("user.dir") + File.separator + screenshotPath + File.separator + this.testname
					+ File.separator + date_time).mkdirs();
		}
		String saveImgFile = System.getProperty("user.dir") + File.separator + screenshotPath + File.separator
				+ this.testname + File.separator + date_time + File.separator + "screenshot.png";

		// File scrFile = ((TakesScreenshot) driver)
		// .getScreenshotAs(OutputType.FILE);

		File source=driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File(saveImgFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Save Image File Path : " + saveImgFile, true);
		// FileUtils.copyFile(scrFile, new File(saveImgFile));
		return saveImgFile;
	}
	/*
	 * public void takeScreenShotOfCompleteScreen() { Robot robotClassObject = null;
	 * try { robotClassObject = new Robot(); } catch (AWTException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * // Get screen size Rectangle screenSize = new
	 * Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	 * 
	 * // Capturing screenshot by providing size BufferedImage tmp =
	 * robotClassObject.createScreenCapture(screenSize);
	 * 
	 * // Defining destination file path String path =
	 * System.getProperty("user.dir") + "/target/controlPanelScreenShot" +
	 * +System.currentTimeMillis() + ".png";
	 * System.out.println("Console screenshot: " + path); // To copy temp image in
	 * to permanent file try { ImageIO.write(tmp, "png", new File(path)); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */

	public void takeScreenShotOnException(ITestResult result) {
		String takeScreenshot = getProperty("take-screenshot");
		if (result.getStatus() == ITestResult.FAILURE) {
			if (takeScreenshot.equalsIgnoreCase("true") || takeScreenshot.equalsIgnoreCase("yes")) {
				try {
					if (driver != null) {
						takeScreenshot();
					}
				} catch (Exception ex) {
					Reporter.log("Driver is null while taking screen shot", true);
				}
			}
		}
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
