package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class test4 {

    public static void main(String[] args) throws InterruptedException {
        // Step 1: Go to http://live.techpanda.org/
        WebDriver driver = new ChromeDriver();
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on “MOBILE” menu
        WebElement mobileMenu = driver.findElement(By.xpath("//a[text()='Mobile']"));
        mobileMenu.click();

        // Find and click on "Add To Compare" button for Sony Xperia and Iphone
        WebElement addToCompare1 = driver.findElement(By.xpath("//a[@title='IPhone']/following-sibling::div//a[@class='link-compare']"));
        addToCompare1.click();
        WebElement addToCompare2 = driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//a[@class='link-compare']"));
        addToCompare2.click();

        // Find and click on "COMPARE" button
        WebElement compareButton = driver.findElement(By.cssSelector(".actions button[title='Compare']"));
        compareButton.click();
        Thread.sleep(1000); // Wait for the pop-up window to appear

        // Switch to the popup window
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
            }
        }
        // Verify the pop-up window and check that the products are reflected in it
        WebElement compareHeading = driver.findElement(By.xpath("//h1[text()='Compare Products']"));
        WebElement iphoneProduct = driver.findElement(By.xpath("//a[@title='IPhone']"));
        WebElement samsungProduct = driver.findElement(By.xpath("//a[@title='Samsung Galaxy']"));
        assert compareHeading.isDisplayed();
        assert iphoneProduct.isDisplayed();
        assert samsungProduct.isDisplayed();

        // Screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile,
                    new File("img\\test4.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Close the popup window
        driver.close();

        // Switch back to the main window
        driver.switchTo().window(mainWindowHandle);

        // Quit the driver
        driver.quit();
    }
}
