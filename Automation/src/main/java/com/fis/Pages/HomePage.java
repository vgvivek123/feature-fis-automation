package com.fis.Pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	public static WebDriver driver;

	public static By first_item = By.xpath("(//a[@class='s-item__link'])[3]");

	public static By atc_btn = By.xpath("//a[@id='atcBtn_btn_1']//span[text()='Add to cart']");

	public static By cart_val = By.xpath("//span[@class='gh-cart__icon']");


	public static By search_box = By.id("gh-ac");
    
	
 public static String validate_cart_items(String url, String book) {
        // Initialize the browser and go to the URL
	    System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		ChromeOptions options = new ChromeOptions();     
        WebDriver driver = new ChromeDriver(options);
		driver.get(url);
    	driver.manage().window().maximize();
    	
        WebElement searchBox = driver.findElement(search_box);
        searchBox.sendKeys(book);
        searchBox.submit();
        
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        WebElement firstItem = driver.findElement(first_item);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", firstItem);
        wait.until(ExpectedConditions.visibilityOf(firstItem));
        firstItem.click();
        

        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
 try {
  
        WebElement atc = wait.until(ExpectedConditions.visibilityOfElementLocated(atc_btn));      
        atc.click();
 }
 catch (Exception e) {
     System.out.println("Add to Cart button not found or clickable.");
     e.printStackTrace();
 }

        WebElement cart = driver.findElement(cart_val);
        String number = cart.getText();
        System.out.println("Items in cart: " + number);
        driver.quit();
        return number;
    }

}
