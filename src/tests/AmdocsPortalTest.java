package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pages.AmdocsPortalPage;
import pages.Settings;


public class AmdocsPortalTest extends Settings {

	public enum LeftMenuItem{
		Element1, Element2, Element3
	}
	public void testEx(LeftMenuItem item) {
	String testest = null;
	switch(item) {
	case Element1 : System.out.println("smth"); break;
	case Element2 : System.out.println("uiyfo"); break;
	}
	}
	
	ExtentHtmlReporter htmlReporter;final String newName = "AT_";
	
	int randomNumber = getRandom();
	String item;
	LinkedList<LinkedList> itemAll;
	ExtentTest testLog = null;
	long startTime = 0;
	long stopTime =0;
	long spendTime = 0;
	
	
	@Test(enabled = true, priority = 0, description = "Portal search")
	@Parameters({"userName", "userP"})
	public void searchField(String userName, String userP) throws Exception {
		testLog = extent.createTest(getClass().getName());
		testLog.log(Status.INFO, "Test: started");
		LinkedList<String> objectList = null;
		AmdocsPortalPage app = new AmdocsPortalPage(driver);
		app.logIn(userName, userP);
		item = app.searchSmth("connect ras");
		
		objectList = app.oneSetResult();
		
		app.createExcelReport(objectList, item);
		//testLog.log(Status.INFO, "BlaBlaBla");
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		testLog.pass("Some comment");
		
	}
	
	LinkedList<String> newSet = new LinkedList<String>();
	LinkedList<String> objectList = new LinkedList<String>();
	
	@Test(enabled = true, priority = 0, description = "Portal search")
	@Parameters({"userName", "userP"})
	public void searchAllField(String userName, String userP) throws Exception {
		//newSet.add("connect ras");
		newSet.add("ras");
		newSet.add("replace monitor");
		
		
		testLog = extent.createTest(getClass().getName());
		testLog.log(Status.INFO, "Test: started");
		
		AmdocsPortalPage app = new AmdocsPortalPage(driver);
		app.logIn(userName, userP);
		itemAll = app.searchSmthList(newSet);
		System.out.println("SIZE : "+itemAll.size());
		
		System.out.println("ARRAY 0 - " + itemAll.get(0).toString()); // To, chto iskali
		System.out.println("ARRAY 1 - " + itemAll.get(1).toString()); // Nazvanie staty
		System.out.println("ARRAY 2 - " + itemAll.get(2).toString()); // to, chto iskali
		System.out.println("ARRAY 3 - " + itemAll.get(3).toString()); //Opisanie(Article...)
		
		 app.createExcelReportAllSet(itemAll);
		
		
		//testLog.log(Status.INFO, "BlaBlaBla");
		
		//Thread.sleep(5000);
		
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		testLog.pass("Some comment");
		
		
	}
	
	/* public void resultSet() {
		createExcelReport(myList.get(0), myList.get(1));
	}
	*/
	
	public void switchTab(int tabNumber) {
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		  driver.switchTo().window(tabs2.get(tabNumber));
	}
}
