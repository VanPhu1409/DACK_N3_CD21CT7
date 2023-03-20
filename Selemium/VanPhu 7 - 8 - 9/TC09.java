package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

@Test
public class TC09 {
    public static void main(String[] args){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        DesiredCapabilities cp = new DesiredCapabilities();
        cp.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(cp);
        ChromeDriver driver = new ChromeDriver(options);
        try{
            //Step 1 Go to https://kingshoes.vn/
            driver.get("https://kingshoes.vn/");
            //2. Click on my account link
            WebElement clicksp = driver.findElement(By.xpath("//body/main[@class='ps-main']/section[@class='ps-section--features-product ps-section pt-40 pb-40']/div[@class='ps-container']/div[@class='ps-products']/div[@class='ps-product__columns']/div[1]/div[1]/div[1]/a[1]"));
            clicksp.click();
            Thread.sleep(3000); // Đợi 3 giây
            WebElement buy = driver.findElement(By.xpath("/html[1]/body[1]/main[1]/section[2]/div[1]/div[1]/div[1]/div[4]/form[1]/div[2]/div[3]/button[1]"));
            buy.click();
            Thread.sleep(3000); // Đợi 3 giây

            //4. Click on 'REORDER' link , change QTY & click Update
            WebElement qty = driver.findElement(By.id("qty377"));
            qty.sendKeys("9");
            WebElement update = driver.findElement(By.xpath("//span[normalize-space()='+']"));
            update.click();
            Thread.sleep(3000);
            //6. Complete Billing & Shipping Information
            WebElement proceedPayment = driver.findElement(By.xpath("//span[normalize-space()='+']"));
            proceedPayment.click();
            Thread.sleep(3000);
            WebElement Payment = driver.findElement(By.xpath("//a[contains(text(),'Đặt hàng')]"));
            Payment.click();
            Thread.sleep(3000);

            WebElement name = driver.findElement(By.id("billing_address_full_name"));
            name.sendKeys("test");
            WebElement sdt = driver.findElement(By.id("billing_address_phone"));
            sdt.sendKeys("0831458934");
            WebElement address = driver.findElement(By.id("billing_address_address"));
            address.sendKeys("0831458934");
            WebElement email = driver.findElement(By.id("billing_email"));
            email.sendKeys("test@gmail.com");
            WebElement note = driver.findElement(By.id("billing_note"));
            note.sendKeys("abc");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
