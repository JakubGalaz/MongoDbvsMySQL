package com.app;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestQueries {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "Obrazki/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testQuery() throws Exception {
        driver.get("http://localhost:8081");
        sleep();

        WebElement stronyDydaktyczne = driver.findElement(By.xpath("/html/body/nav/a[2]/button"));
        stronyDydaktyczne.click();
        sleep();

//        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
//        for (WebElement link : allLinks) {
//            String linkText = link.getText();
//            if (linkText.startsWith("dr ") || linkText.startsWith("mgr "))
//                System.out.println(linkText);
//        }
    }

    @After
    public void shoutDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}