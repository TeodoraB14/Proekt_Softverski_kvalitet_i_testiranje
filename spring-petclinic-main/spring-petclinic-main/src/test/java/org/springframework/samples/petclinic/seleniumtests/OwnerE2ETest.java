package org.springframework.samples.petclinic.seleniumtests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OwnerE2ETest {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeEach
	void setUp() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");

		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	@AfterEach
	void tearDown() {
		driver.quit();
	}

	@Test
	void createOwner_FINALLY_WORKS() {

		driver.get("http://localhost:8080/owners/new");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));

		driver.findElement(By.id("firstName")).sendKeys("Ana");
		driver.findElement(By.id("lastName")).sendKeys("Anastasova");
		driver.findElement(By.id("address")).sendKeys("Main Street 1");
		driver.findElement(By.id("city")).sendKeys("Skopje");
		driver.findElement(By.id("telephone")).sendKeys("0711234566");

		driver.findElement(By.cssSelector("button[type='submit']")).click();

		wait.until(ExpectedConditions.urlMatches(".*/owners/\\d+"));

		WebElement body = driver.findElement(By.tagName("body"));
		assertTrue(body.getText().contains("Ana Anastasova"));
	}
}
