package com.expedia.automation.manager;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebManager {
	public static WebDriver driver;
	public static void connect(){
		
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.expedia.com.ph/");
		System.out.println("Connection to Selenium Web Driver in now established....");
		System.out.println("Application is Launching....");
	}
	
	public static void testResults() throws Exception{
//		FileOutputStream fos = new FileOutputStream("TestResult/Test.csv");
//		OutputStreamWriter osw = new OutputStreamWriter(fos);
//		BufferedWriter bw = new BufferedWriter(osw);
//		
//		for (int i=1; i<11;i++){
//			bw.write("TEST" +i);
//			bw.newLine();
//		}
//		bw.flush();
//		bw.close();
//		osw.close();
//		fos.close();
	}
}
