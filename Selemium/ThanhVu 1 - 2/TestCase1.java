//Test Steps
//        Step 1. Go to http://live.techpanda.org/
//        Step 2. Verify Title of the page
//        Step 3. Click on -> MOBILE -> menu
//        Step 4. In the list of all mobile , select SORT BY -> dropdown as name
//        Step 5. Verify all products are sorted by name
package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import driver.driverFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Test
public class TestCase1 {

    public static void testShot(){
//        WebDriver driver = driverFactory.getChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        DesiredCapabilities cp = new DesiredCapabilities();
        cp.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(cp);
        ChromeDriver driver = new ChromeDriver(options);
        try{
            //Step 1 Go to https://kingshoes.vn
            driver.get("https://kingshoes.vn/");

            //Step 2. Verify Title of the page
            String actualTitle = driver.getTitle();
            String expectedTitle = "Cửa Hàng Giày Sneaker Chính Hãng Tại TpHcm - KING SHOES";
            if (actualTitle.equals(expectedTitle)) {
                System.out.println("Tiêu đề của trang là đúng.");
            } else {
                System.out.println("Tiêu đề của trang là sai.");

            }
            // Step 3. Click on -> nike
//
            WebElement nike = driver.findElement(By.xpath("//div[@class='navigation__column center d-none d-xl-block']//ul[@class='main-menu menu']//li[@id='menu77']//a[@href='nike'][normalize-space()='Nike']"));
            nike.click();


            //Step 4. In the list of all Shoes, select SORT BY -> dropdown as name
            WebElement element = driver.findElement(By.xpath("//div[@class='col-sm-12 col-md-3 col-lg-3 col-xl-3 dvtool btn-search-lock']//button[@type='submit'][normalize-space()='Tìm Giày Ngay']"));
            element.click();


            //Step 5. Verify all products are sorted by name
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

// Save the screenshot to a file
            try {
                FileUtils.copyFile(screenshotFile, new File("./src/test/java/test/screenshot.png"));
                System.out.println("Đã lưu hình ảnh");
            } catch (IOException e) {
                e.printStackTrace();
            }





        } catch (Exception e){
            e.printStackTrace();
        }
           driver.close();
        driver.quit();
    }
}
