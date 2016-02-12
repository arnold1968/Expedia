package com.expedia.automation.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.expedia.automation.manager.WebManager;

@RunWith(value = Parameterized.class)
public class LoginTest {
	private String Email;
	private String Password;
	
	@Before
	public void openBrowser(){
		WebManager.connect();
	}
	
	@After
	public void closeBrowser(){
		WebManager.driver.close();
	}

	@Parameters
	public static Collection testData() throws IOException {
		return getTestData("testdata/LoginCredentials.csv");
	}

	public LoginTest(String Email, String Password) {
		this.Email = Email;
		this.Password = Password;
	}

	public static Collection<String[]> getTestData(String fileName)
			throws IOException {
		List<String[]> records = new ArrayList<String[]>();
		String record;
		BufferedReader file = new BufferedReader(new FileReader(fileName));
		while ((record = file.readLine()) != null) {
			String fields[] = record.split(",");
			records.add(fields);
		}
		file.close();
		return records;
	}
	
	@Test
	public void login() throws InterruptedException {
		WebDriver driver = WebManager.driver;
		
		TimeUnit.SECONDS.sleep(20);
		
//		driver.findElement(By.xpath("//*[@id=\"header-account-menu\"]")).click();
		
		WebElement clickAccount = driver.findElement(By.xpath("//*[@id=\"header-account-menu\"]"));
		clickAccount.click();
		TimeUnit.SECONDS.sleep(3);
		System.out.println(clickAccount + " has been clicked!");
		
		WebElement clickSignIn = driver.findElement(By.xpath("//*[@id=\"header-account-signin-button\"]"));
		clickSignIn.click();
		System.out.println(clickSignIn + " has been clicked!");
		TimeUnit.SECONDS.sleep(3);
		
		WebElement enterEmail = driver.findElement(By.xpath("//*[@id=\"signin-loginid\"]"));
		enterEmail.click();
		enterEmail.sendKeys(Email);
		System.out.println(Email + " has Entered!");
		TimeUnit.SECONDS.sleep(3);
		
		WebElement enterPassword = driver.findElement(By.xpath("//*[@id=\"signin-password\"]"));
		enterPassword.click();
		enterPassword.sendKeys(Password);
		System.out.println(Password + " has Entered!");
		TimeUnit.SECONDS.sleep(3);
		
		WebElement clickSubmit = driver.findElement(By.xpath("//*[@id=\"submitButton\"]"));
		clickSubmit.click();
		System.out.println(clickSubmit + " has been clicked!");
		TimeUnit.SECONDS.sleep(3);
		
	}
}
