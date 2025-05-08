package demoblaze.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class JavaUtility {
	static WebDriver driver;
	public static String getNewUser()
	{
		String newUser = LocalDateTime.now().toString().replace(":", "").replace(" ", "");
		return newUser;
	}
	
	public String popup(WebDriver driver ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.alertIsPresent());
		 Alert alert = driver.switchTo().alert();
         String alertMessage = alert.getText();
         alert.accept();
		return alertMessage;
	}
	
	public static void setDriver(WebDriver driverInstance) {
	    driver = driverInstance;
	}

	public static String takeScreenShot(String testCaseName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        File destinationFile = new File("C:\\Users\\cibhi\\eclipse-workspace\\DemoBlaze1\\screenshots\\" + testCaseName + ".png");
        try {
            FileUtils.copyFile(scrFile, destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String path = "<img src= " + destinationFile.getAbsolutePath() + " width = 200px height=200px />";

        Reporter.log(path);
        return destinationFile.getAbsolutePath();
    }

}