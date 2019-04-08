
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author GamingDanik
 */
public class RegistrationTest {
    
    public void runTest() throws InterruptedException {
        registerTest();
        buyAlbumTest();
        checkoutTest();
    }
    
    public void registerTest() throws InterruptedException {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/sound%20%20/");
        WebElement element;
        element = driver.findElement(By.id("signup"));
        element.click();
        element = driver.findElement(By.className("firstname"));
        element.sendKeys("Testfsd");

        element = driver.findElement(By.className("lastname"));
        element.sendKeys("Mansdf");
        element = driver.findElement(By.className("registerEmail"));
        element.sendKeys("Test23@gmaiflssdffsdf.com");
        element = driver.findElement(By.className("registerPassword"));
        element.sendKeys("Secure");
        Thread.sleep(1000);
        element = driver.findElement(By.className("signupBtn"));
        element.click();
        Thread.sleep(3000);
        
    }

    public void buyAlbumTest() throws InterruptedException {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/sound%20%20/");
        WebElement element;

        element = driver.findElement(By.className("albumLink"));
        element.click();
        Thread.sleep(2000);
        element = driver.findElement(By.className("customLink"));
        element.click();
        element = driver.findElement(By.id("quantityContainer:add"));
        element.click();
        Thread.sleep(2000);
    }

    public void checkoutTest() throws InterruptedException {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/sound%20%20/");
        WebElement element;
        element = driver.findElement(By.className("checkout"));
        element.click();
        Thread.sleep(1000);
        element = driver.findElement(By.className("proceed"));
        element.click();
        Thread.sleep(1000);
        element = driver.findElement(By.className("address"));
        element.sendKeys("Testfsd");

        element = driver.findElement(By.className("country"));
        element.sendKeys("Mansdf");
        element = driver.findElement(By.className("province"));
        element.sendKeys("sadf");
        element = driver.findElement(By.className("postalCode"));
        element.sendKeys("Ssfe");
        element = driver.findElement(By.className("nameOnCard"));
        element.sendKeys("Secure");
        element = driver.findElement(By.className("ccNumber"));
        element.sendKeys("Secure");
        element = driver.findElement(By.className("expiry"));
        element.sendKeys("Secure");
        element = driver.findElement(By.className("cvv"));
        element.sendKeys("Secure");
        element = driver.findElement(By.className("toInvoice"));
        element.click();
    }

}
