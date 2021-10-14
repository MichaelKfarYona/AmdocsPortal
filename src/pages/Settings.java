package pages;


import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Settings {
	
	 public enum ApplicationName{
			APP_1, APP_2, APP_3
		}
	 //
	 public void pasteFromClipboard(String text) throws AWTException {
		 Robot robot = new Robot();
		 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	 		clipboard.setContents(new StringSelection(text), null);
	 		robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_V);
	 }
		public void chooseApplicationByName(ApplicationName item) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait (driver, 3);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@data-icon-name='allAppsLogo']")));
			WebElement allAppsCor = driver.findElement(By.xpath("//i[@data-icon-name='allAppsLogo']"));
			allAppsCor.click();
		/*
		 * List<WebElement> allApps =
		 * driver.findElements(By.xpath("//i[@data-icon-name='allAppsLogo']"));
		 * //Thread.sleep(3000); if(allApps.size()>0) { allApps.get(0).click();
		 * Thread.sleep(2000); }
		 */
	       
			String parametrListItem = null;
			
			switch(item) { 
			case APP_1: parametrListItem = "App1";  break;
			case APP_2: parametrListItem = "App2";  break;
			case APP_3: parametrListItem = "App3";  break;
			
			}
			
			
			WebElement leftMenuItemElement = (new WebDriverWait(driver, 10))
			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title='"+parametrListItem+"' and contains(text(),'"+parametrListItem+"')]")));
			
			/* WebElement leftMenuItemElement = driver.findElement(By.xpath("//div[@class='hero-container']//div[@title='"+parametrListItem+"']"));
			*/
			leftMenuItemElement.click();
		}
	protected static ExtentReports extent;
	static long timeSpent = 0;
	static long start = 0;
    private static ThreadLocal parentTest = new ThreadLocal();
    private static ThreadLocal test = new ThreadLocal();
	ExtentHtmlReporter htmlReporter;
	// public static ArrayList<String> myList = new ArrayList<String>();
    String driverPath = "c:\\DRIVERS\\chromedriver.exe";
    protected static WebDriver driver;
	

    
    static ExtentTest testLogger;
    @BeforeTest
    public void ReportSetup() {
    	htmlReporter = new ExtentHtmlReporter("AmdocsPortalReport.html");
    	
    	extent = new ExtentReports();
    	
    	extent.attachReporter(htmlReporter);
    }
    @BeforeSuite
    public void setTimePoint() {
    	
    	start = System.currentTimeMillis();
    	}
    @BeforeMethod
    public void Setup(){
    	ChromeOptions options = new ChromeOptions();
    	options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
    	
    	options.addArguments("--disable-notifications");
    	
    	options.addArguments("--lang=en"); 
    	System.setProperty("webdriver.chrome.driver",driverPath);
    	driver = new ChromeDriver(options);
    	driver.manage().window().maximize();
    	driver.get("https://amdocsuat.service-now.com/it_support");
    	driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }

    @AfterMethod
    public static void tearDown(ITestResult results){
    	
		//extent.flush();
        driver.close();
        driver.quit();
    }
    @AfterSuite
    public void timeSpent() {
    	timeSpent = System.currentTimeMillis() - start;
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(timeSpent);
    	SimpleDateFormat format = new SimpleDateFormat("mm:ss");
    	System.out.println("Spent on scripting: "+format.format(cal.getTime()));
    	
    }
    @AfterTest
    public static void tearDownAll() {
    	
    	  	extent.flush();
    }
    
    
    // Create Excel
    
    public void createExcelReport(ArrayList<String> articleAndType) {
    	int i = 0;
    	
    	 try {
             String filename = "C://!//NewExcelFile.xls" ;
             HSSFWorkbook workbook = new HSSFWorkbook();
             HSSFSheet sheet = workbook.createSheet("AutomationReport");  

             HSSFRow rowhead = sheet.createRow((short)0);
             rowhead.createCell(0).setCellValue("#");
             rowhead.createCell(1).setCellValue("Search term");
             rowhead.createCell(2).setCellValue("Score");
             rowhead.createCell(3).setCellValue("Result");
             
             for(i = 0; i< articleAndType.size(); i++) {

                 HSSFRow row = sheet.createRow((short)i+1);
                 row.createCell(0).setCellValue(i+1);
                 row.createCell(1).setCellValue(articleAndType.get(i));
                 row.createCell(2).setCellValue(articleAndType.get(i++));
                 row.createCell(3).setCellValue("mymegameil@mail.com");
             }
             /*
             for(Map.Entry<String, String> item : articleAndType.entrySet()){
                 
                 System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue());
             
             
             HSSFRow row = sheet.createRow((short)1);
             row.createCell(0).setCellValue(one);
             row.createCell(1).setCellValue(item.getKey());
             row.createCell(2).setCellValue(item.getValue());
             row.createCell(3).setCellValue("mymegameil@mail.com");
             }
             */
             
             FileOutputStream fileOut = new FileOutputStream(filename);
             workbook.write(fileOut);
             fileOut.close();
             workbook.close();
             System.out.println("The excel report file has been generated!");

         } catch ( Exception ex ) {
             System.out.println(ex + " Smthing wrong with excel method...");
         }
    }
    
    /**************************************************************************
     * Below I have implemented commonly used methods for working with pages. *
     **************************************************************************/
    
	/* Switch between tabs */
    public void switchNewOpenedTab(int number) {
    	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		  driver.switchTo().window(tabs.get(number));
    }

	/*********************************
	 * Random string with INT length *
	 *********************************/
    public String randomStringGneration(int lineLength) {
    	String result = null;
    	/*Generator sluchainih simvolov*/
		//int lineLength = 36;
		char[] alphabetA = new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789").toCharArray();
		for(int n=0; n < lineLength; n++) {
			int randomSymbol = randomNumInRange(0,alphabetA.length-1);
			char cr = alphabetA[randomSymbol];
			result = String.valueOf(cr);
		}
		return result;
    }
    /***************** 
	 * Random method *
	 *****************/
	public int getRandom() {Random rand = new Random(); 
	int value = rand.nextInt(100000);
	return value;}
	/************************** 
	 * Random method in range *
	 **************************/
	public static int randomNumInRange(int min, int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNum;
	}

}