package autoTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class fileUpload {
    public static void main(String[]args) throws InterruptedException, IOException {
        String downloadPath = System.getProperty("user.dir");
        ChromeOptions options=new ChromeOptions();
        //Map smo skinuli sa sajta https://developer.chrome.com/docs/chromedriver/capabilities
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", prefs);
        //options.setExperimentalOption("prefs", )


        WebDriver driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.ilovepdf.com/pdf_to_jpg");
        driver.findElement(By.id("pickfiles")).click();
        Thread.sleep(3000);
        Runtime.getRuntime().exec("C:\\Users\\Pc\\Documents\\check\\fileupload.exe");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("processTask")));

        driver.findElement(By.id("processTask")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pickfiles")));
        driver.findElement(By.id("pickfiles")).click();
        Thread.sleep(5000);
        File f = new File(downloadPath+"/plant_page-0001.jpg");
        //koristimo if naredbu da utvrdimo da fajl koji je skinut zaista postoji
        if(f.exists()){
            Assert.assertTrue(f.exists());
            System.out.println("File exist");
            //koristimo if naredbu da obrisemo fajl koji smo skinuli
            if(f.delete()){
                System.out.println("File deleted");
            }
        }else{
            System.out.println("File does not exist");        }
        driver.quit();

    }
}
