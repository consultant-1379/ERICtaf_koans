package com.ericsson.taf.workshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by ekonsla on 24/05/2016.
 */
public class SeleniumTestExample {
    /**
     * In this example you will need to have a selenium binary for chrome download the latest release
     * from here: https://chromedriver.storage.googleapis.com/index.html
     * when running Ui tests locally you must use
     * the command -Dwebdriver.chrome.driver="Path/to/binary.exe" to specify the path to the selenium binary
     * in the VM options: when you edit configurations.
     */
    public static void main(String[] args) {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://atvts3003.athtem.eei.ericsson.se:8882/login");
        WebDriverWait wait = new WebDriverWait(webDriver,7);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("eaLogin-Holder-loginUsername")));
        WebElement loginInputField = webDriver.findElement(By.className("eaLogin-Holder-loginUsername"));
        WebElement passwordInputField = webDriver.findElement(By.className("eaLogin-Holder-loginPassword"));
        WebElement submitButton = webDriver.findElement(By.className("eaLogin-Holder-formButton"));

        loginInputField.sendKeys("taf");
        passwordInputField.sendKeys("taf");
        submitButton.click();
        webDriver.close();
    }
}