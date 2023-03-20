package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;

public class test3 {
    public static void main(String[] args) {
        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Step 1: Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on -> MOBILE -> menu
        WebElement mobileMenu = driver.findElement(By.xpath("//a[text()='Mobile']"));
        mobileMenu.click();

        // Step 3: Click on the Sony Xperia mobile
        WebElement sonyXperiaMobile = driver.findElement(By.xpath("//a[text()='Sony Xperia']"));
        sonyXperiaMobile.click();

        // Step 4: Click on add to cart
        WebElement addtocart = driver.findElement(By.cssSelector("button[title='Add to Cart']"));
        addtocart.click();

        // Step 5: Change "QTY" value to 1000 and click "UPDATE" button
        WebElement qtyInput = driver.findElement(By.cssSelector("input[title='Qty']"));
        qtyInput.clear();
        qtyInput.sendKeys("1000");
        WebElement updateBtn = driver.findElement(By.cssSelector("button[title='Update']"));
        updateBtn.click();

        // Step 6: Verify error message
        WebElement errorMsg = driver.findElement(By.xpath("//span[contains(text(),'Some of the products cannot be ordered in requested quantity.')]"));
        Assert.assertEquals(errorMsg.getText(), "Some of the products cannot be ordered in requested quantity.");

        // Step 7: Click on "EMPTY CART" link
        WebElement emptyCartLink = driver.findElement(By.cssSelector("button[title='Empty Cart']"));
        emptyCartLink.click();

        // Step 8: Verify cart is empty
        WebElement cartEmptyMsg = driver.findElement(By.className("empty"));
        Assert.assertEquals(cartEmptyMsg.getText(), "");

        // Screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile,
                    new File("img\\test3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Close the browser
        driver.quit();
    }
}
