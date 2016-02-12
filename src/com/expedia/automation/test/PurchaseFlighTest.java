package com.expedia.automation.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.expedia.automation.manager.UIElements;
import com.expedia.automation.manager.WebManager;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

@RunWith(value = Parameterized.class)
public class PurchaseFlighTest {
	private String Origin;
	private String Destination;
	private String DepartureDate;
	private String ReturnDate;
	private String Adult;
	
	@Before
	public void openBrowser(){
		WebManager.connect();
		
	}
	@After
	public void closeBrowser() throws Exception{
	
		WebManager.driver.close();
	}
	
	@Parameters
	public static Collection testData() throws IOException {
		return getTestData("testdata/flightDetails.csv");
	}

	public PurchaseFlighTest(String Origin, String Destination, String DepartureDate, String ReturnDate, String Adult) {
		this.Origin = Origin;
		this.Destination = Destination;
		this.DepartureDate = DepartureDate;
		this.ReturnDate = ReturnDate;
		this.Adult = Adult;
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
	
	public void writeFile(ArrayList<String> results) throws Exception{
		FileOutputStream fos = new FileOutputStream("TestResult/Test.csv");
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		
		for (String logs: results){
			bw.write(logs);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		osw.close();
		fos.close();
	}
	

	@Test
	public void selectDateFlight() throws Exception, InterruptedException{
		ArrayList<String> results = new ArrayList<String>();
		
		WebDriver driver = WebManager.driver;
		Actions action = new Actions(driver);
		Integer x = Integer.valueOf(Destination);
		
		try {
			Thread.sleep(3000);
			WebElement clickSignup = driver.findElement(By.id(UIElements.LOGIN_HEADER_BUTTON));
			assertTrue(clickSignup.isDisplayed());
			results.add("\nSign-In Button,"+ clickSignup.isDisplayed());
			clickSignup.click();
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Sign-In Button," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		driver.findElement(By.id(UIElements.LOGIN_EMAIL_FIELD)).sendKeys("arnold21492@gmail.com");
		Thread.sleep(5000);
		
		driver.findElement(By.id(UIElements.LOGIN_PASSWORD_FIELD)).sendKeys("Dlonra1968");
		Thread.sleep(5000);
		
		try {
			WebElement clickLogin = driver.findElement(By.id(UIElements.LOGIN_SUBMIT_BUTTON));
			assertTrue(clickLogin.isDisplayed());
			results.add("\nSubmit Button,"+ clickLogin.isDisplayed());
			clickLogin.click();
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Submit Button," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		
		try {
			WebElement clickFlightTab = driver.findElement(By.id(UIElements.HOME_FLIGHT_TAB));
			assertTrue(clickFlightTab.isDisplayed());
			clickFlightTab.click();
			results.add("\nFlight Tab,"+ clickFlightTab.isDisplayed());
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Flight Tab," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		try {
			WebElement selectflightOrigin = driver.findElement(By.id(UIElements.ORIGIN_PLACE));
			assertTrue(selectflightOrigin.isDisplayed());
			results.add("\nFlight Origin Dropdown,"+ selectflightOrigin.isDisplayed());
			selectflightOrigin.click();
			Thread.sleep(5000);
			selectflightOrigin.sendKeys(Origin);
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Flight Origin Dropdown," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		try {
			WebElement clickflightDestination = driver.findElement(By.id(UIElements.DESTINATION_PLACE));
			assertTrue(clickflightDestination.isDisplayed());
			results.add("\nFlight Destination Dropdown,"+ clickflightDestination.isDisplayed());
			action.moveToElement(clickflightDestination).click().build().perform();
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Flight Destination Dropdown," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		Select flightDestinationName = new Select(driver.findElement(By.id(UIElements.DESTINATION_PLACE)));
		flightDestinationName.selectByIndex(x);
		Thread.sleep(5000);
		
		try {
			WebElement selectDateDeparture = driver.findElement(By.id(UIElements.DEPARTURE_DATE));
			assertTrue(selectDateDeparture.isDisplayed());
			results.add("\nDatepicker for Departure,"+ selectDateDeparture.isDisplayed());
			selectDateDeparture.click();
			selectDateDeparture.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			selectDateDeparture.sendKeys(DepartureDate);
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Datepicker for Departure," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		try {
			WebElement selectDateReturn = driver.findElement(By.id(UIElements.RETURN_DATE));
			assertTrue(selectDateReturn.isDisplayed());
			results.add("\nDatepicker for Return,"+ selectDateReturn.isDisplayed());
			selectDateReturn.click();
			selectDateReturn.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			selectDateReturn.sendKeys(ReturnDate);
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Datepicker for Return," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		try {
			WebElement selectPassengerCount = driver.findElement(By.id(UIElements.PASSENGER_COUNT_DROPDOWN));
			assertTrue(selectPassengerCount.isDisplayed());
			results.add("\nNumber of Passenger Dropdown,"+ selectPassengerCount.isDisplayed());
			selectPassengerCount.click();
			selectPassengerCount.sendKeys(Adult);
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Number of Passenger Dropdown," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		try {
			WebElement clickSearchFlight = driver.findElement(By.id(UIElements.FLIGHT_SEARCH_BUTTON));
			assertTrue(clickSearchFlight.isDisplayed());
			results.add("\nSearch Button,"+ clickSearchFlight.isDisplayed());
			clickSearchFlight.click();
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Search Button," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			WebElement clickSortFlightBy = driver.findElement(By.xpath(UIElements.SORT_BY_DROPDOWN));
			assertTrue(clickSortFlightBy.isDisplayed());
			results.add("\nFlight Type Sort Dropdown,"+ clickSortFlightBy.isDisplayed());
			action.moveToElement(clickSortFlightBy).click().build().perform();
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Flight Type Sort Dropdown," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		Select setDepartureFlightBy = new Select(driver.findElement(By.xpath(UIElements.SORT_BY_DROPDOWN)));
		setDepartureFlightBy.selectByIndex(2);
		Thread.sleep(5000);
		
		try {
			WebElement filterDepartureAirline = driver.findElement(By.xpath(UIElements.AIRLINE_FILTER));
			assertTrue(filterDepartureAirline.isDisplayed());
			results.add("\nFilter for Departure Airline,"+ filterDepartureAirline.isDisplayed());
			filterDepartureAirline.click();
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Filter for Departure Airline," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		try {
			WebElement clickSelectFlightDept = driver.findElement(By.xpath(UIElements.SELECT_FLIGHT_BUTTON));
			assertTrue(clickSelectFlightDept.isDisplayed());
			results.add("\nSelect Flight Departure,"+ clickSelectFlightDept.isDisplayed());
			clickSelectFlightDept.click();
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Select Flight Departure," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		Select setReturnFlightBy = new Select(driver.findElement(By.xpath(UIElements.SORT_BY_DROPDOWN)));
		setReturnFlightBy.selectByIndex(2);
		Thread.sleep(5000);
		
		try {
			WebElement filterReturnAirline = driver.findElement(By.xpath(UIElements.AIRLINE_FILTER));
			assertTrue(filterReturnAirline.isDisplayed());
			results.add("\nFilter for Return Airline,"+ filterReturnAirline.isDisplayed());
			filterReturnAirline.click();
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Filter for Return Airline," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		try {
			WebElement clickSelectFlighReturn = driver.findElement(By.xpath(UIElements.SELECT_FLIGHT_BUTTON));
			assertTrue(clickSelectFlighReturn.isDisplayed());
			results.add("\nSelect Flight Return,"+ clickSelectFlighReturn.isDisplayed());
			clickSelectFlighReturn.click();
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Select Flight Return," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		
		try {
			WebElement selectSkipChoice = driver.findElement(By.xpath(UIElements.SKIP_ADD_HOTEL));
			assertTrue(selectSkipChoice.isDisplayed());
			results.add("\nSkip Offer,"+ selectSkipChoice.isDisplayed());
			selectSkipChoice.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            results.add("Skip Offer," + e.getMessage().replace('\n',' '));
            writeFile(results);
		}
		Thread.sleep(7000);
		
		String oldTab = driver.getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    newTab.remove(oldTab);
	    Thread.sleep(5000);

	    driver.switchTo().window(newTab.get(0));
	    Thread.sleep(5000);
	    
		try {
			String successMessage = driver.findElement(By.cssSelector(UIElements.SUCCESS_BOOK_MESSAGE)).getText();
			String expectedMessage1 = "Nice Job! You picked one of our cheapest flights.";
			assertEquals(successMessage, expectedMessage1);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String successMessage2 = driver.findElement(By.cssSelector(UIElements.SUCCESS_BOOK_MESSAGE2)).getText();
		String expectedMessage2 = "Book now so you don't miss out on this price!";
		assertEquals(successMessage2, expectedMessage2);
	  
		
		WebElement DetailDeparture = driver.findElement(By.id(UIElements.FLIGHT_DEPARTURE_DETAILS));
		System.out.println(DetailDeparture.getText());
		
		Thread.sleep(5000);
		
		WebElement DetailReturn = driver.findElement(By.id(UIElements.FLIGHT_RETURN_DETAILS));
		System.out.println(DetailReturn.getText());
		Thread.sleep(5000); 		

	}
}
