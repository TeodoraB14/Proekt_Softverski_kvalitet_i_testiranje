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

public class EditOwnerTest {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeEach
	void setUp() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");

		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	@Test
	void editOwner_WORKING() {

		driver.get("http://localhost:8080/owners");

		WebElement ownerLink = wait.until(
			ExpectedConditions.elementToBeClickable(
				By.xpath("//table//a[contains(@href,'/owners/')]")
			)
		);
		ownerLink.click();

		WebElement editBtn = wait.until(
			ExpectedConditions.elementToBeClickable(By.linkText("Edit Owner"))
		);
		editBtn.click();

		WebElement address = wait.until(
			ExpectedConditions.visibilityOfElementLocated(By.id("address"))
		);
		address.clear();
		address.sendKeys("Edited Address");

		driver.findElement(By.cssSelector("button[type='submit']")).click();

		WebElement addrText = wait.until(
			ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//th[text()='Address']/following-sibling::td")
			)
		);

		assertTrue(addrText.getText().contains("Edited Address"));
	}

	@AfterEach
	void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
