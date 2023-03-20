package test;

import org.openqa.selenium.*;
import driver.driverFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

@Test
public class Testcase2 {
//    Test Steps:
//  1. Goto https://kingshoes.vn/
//  2. Click on Adidas menu
//  3. In the list of all Shoes, read the cost of Shoes (which is 2,800,000)
//  4. Click on Shoes
//  5. Read the Shoes from detail page.
    //  6. Compare Product value in list and details page should be equal (2,800,000).

    public static void Testcase2(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        DesiredCapabilities cp = new DesiredCapabilities();
        cp.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(cp);
        ChromeDriver driver = new ChromeDriver(options);
        try{
//            1. Goto https://kingshoes.vn/
            driver.get("https://kingshoes.vn/");

            //  2. Click on Adidas menu
            WebElement adidas = driver.findElement(By.xpath("/html[1]/body[1]/header[1]/nav[1]/div[1]/div[2]/ul[1]/li[3]/a[1]"));
            adidas.click();

            //  3. In the list of all Shoes, read the cost of Shoes (which is 2,800,000)
            WebElement price_path = driver.findElement(By.xpath("//div[6]//div[1]//div[2]//div[1]//span[1]"));
            String priceList = price_path.getText();
            System.out.println(priceList);
            //  4. Click on Shoes
            WebElement product = driver.findElement(By.xpath("/html[1]/body[1]/main[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/a[1]"));
            product.click();
            //  5. Read the Shoes from detail page.
            WebElement price_path1 = driver.findElement(By.xpath("//b[@class='new-price']"));
            String priceDetail = price_path1.getText();
            System.out.println(priceDetail);
            //  6. Compare Product value in list and details page should be equal (2,800,000).
            if(priceDetail.equals(priceList)){
                System.out.println("Bằng nhau");
            } else if (priceDetail!=priceList) {
                System.out.println("Không bằng nhau");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
//        driver.close();
//        driver.quit();
    }

}
