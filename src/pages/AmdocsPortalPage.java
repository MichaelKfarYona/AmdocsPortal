package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Settings.ApplicationName;

public class AmdocsPortalPage {
	WebDriver driver;
	@FindBy(xpath = "//")
	public WebElement someElement;
	List<SearchResult> srSet ;
	public enum ApplicationName {
		APP_1, APP_2, APP_3
	}

	public void logIn(String name, String pwd) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='lightbox']")));
		WebElement userNameField = driver.findElement(By.xpath("//input[@type='email']"));
		userNameField.sendKeys(name);
		submitElement();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='passwd']")));
			WebElement userPwdField = driver.findElement(By.xpath("//input[@name='passwd']"));
			userPwdField.sendKeys(pwd);
			submitElement();
		} catch (Exception e) {
		}
		;

	}

	public String searchSmth(String item) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='search-box']//input")));
		WebElement field = driver.findElement(By.xpath("//div[@class='search-box']//input"));
		field.sendKeys(item);
		WebElement submitSearchBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		submitSearchBtn.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='tabs ng-scope'] | //div[@class='no-results ng-scope']")));
		return item;

	}
	// Search several elements **************************************************************************************8
	public LinkedList<LinkedList> searchSmthList(List<String> item) {
		LinkedList<String> allSearchItems = new LinkedList<String>();
		LinkedList<String> allSearchRequest = new LinkedList<String>();
		LinkedList<LinkedList> finalResAll = new LinkedList<LinkedList>();
		
		for(int i = 0; i < item.size(); i++) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='search-box']//input")));
		WebElement field = driver.findElement(By.xpath("//div[@class='search-box']//input"));
		field.clear();
		field.sendKeys(item.get(i));
		WebElement submitSearchBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		submitSearchBtn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='tabs ng-scope'] | //div[@class='no-results ng-scope']")));
		
		allSearchItems = oneSetResult();
		int n = oneSetResult().size()/2;
		for(int x=0; x<n; x++) {
		allSearchRequest.add(item.get(i));
		}
	
		System.out.println("*** AllSearchRequest - "+ allSearchRequest.size());//vse
		System.out.println("*** AllSearchRequest - "+ allSearchItems.size()); // iz odnogo poiska
		finalResAll.add(allSearchRequest);
		finalResAll.add(allSearchItems);
		System.out.println("*** FINAL RESULT "+ finalResAll.size());
		}
		return finalResAll;

	} //*****************************************************************************************************
	public LinkedList<String> oneSetResult() {
		 //Map<String, String> states = new HashMap<String, String>();
		LinkedList<String> states = new LinkedList<String>();
		 	List<WebElement> articleNameList = driver.findElements(By.xpath("//div[@class='ng-scope']//div[@class='item ng-scope']//div[@class='name ng-binding']"));
		 	System.out.println(articleNameList.size() + " results for ");
		 	List<WebElement> typeList = driver.findElements(By.xpath("//div[@class='ng-scope']//div[@class='item ng-scope']//span[contains(text(),'')]"));
		 
		
	       for(int i=0; i<articleNameList.size(); i++){
	    	   
				String article = articleNameList.get(i).getText(); // Article tytle
				states.add(article);
				String type = typeList.get(i).getText(); // type
				System.out.println(i + " - "+ article + "||| "+ " "+ type);
				states.add(type);
				
				/*
				SearchResult sr = new SearchResult(article, type);
				System.out.println(sr.getArticleTitle());
				System.out.println(sr.getType());
				*/
				//srSet.add(sr);
		    }    
	       System.out.println("* Items found - "+states.size());
	       
	    
		return states;
		
	}

	public void submitElement() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));
		WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		submitBtn.click();
	}

	public AmdocsPortalPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void lambdaT() {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(5);
		numbers.add(9);
		numbers.add(8);
		numbers.add(1);
		numbers.forEach((n) -> {
			System.out.println(n);
		});
	}

	public void openChatBot() {
		if (doesTheItemExist("//div[@class='text']")) {
			WebElement openChat = driver.findElement(By.xpath("//div[@class='text']"));
			openChat.click();
		}

	}

	public void switchToFrame() {
		WebElement iframeElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.className("chat-frame")));
		driver.switchTo().frame("Chat Support");
	}

	// The first way to check an element
	private boolean doesTheItemExist(String xpath) {
		try {
			driver.findElement(By.id(xpath));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	// The 2nd way to check an element
	public void someElementExist(By by) {
		WebDriverWait waiter = new WebDriverWait(driver, 5000);
		waiter.ignoring(NoSuchElementException.class);
		waiter.until(ExpectedConditions.presenceOfElementLocated(by));
		driver.findElement(by);
	}

    // Create Excel
    
    public void createExcelReport(LinkedList<String> objectList, String item) {
    	int i = 0;
    	int rowN = 1;
    	
    	 try {
    		 String filename = "C://!//SearchReport.xls" ;
             HSSFWorkbook workbook = new HSSFWorkbook();
             HSSFSheet sheet = workbook.createSheet("AutomationReport");  
             HSSFRow rowhead = sheet.createRow((short)0);
             rowhead.createCell(0).setCellValue("#");
             rowhead.createCell(1).setCellValue("Search term");
             rowhead.createCell(2).setCellValue("Result");
             rowhead.createCell(3).setCellValue("Type");
             
             for(i = 0; i< objectList.size(); i++) {

                 HSSFRow row = sheet.createRow((short)rowN);
                 row.createCell(0).setCellValue(rowN);
                 row.createCell(1).setCellValue(item);
                 row.createCell(2).setCellValue(objectList.get(i++));
                 row.createCell(3).setCellValue(objectList.get(i));
                 rowN++;
             }
             
             FileOutputStream fileOut = new FileOutputStream(filename);
             workbook.write(fileOut);
             fileOut.close();
             workbook.close();
             System.out.println("The excel report file has been generated!");

         } catch ( Exception ex ) {
             System.out.println(ex + " Smthing wrong with excel method...");
         }
    }
	//-----------------------------------Excel report for all set-------------------------------------
    public void createExcelReportAllSet(LinkedList<LinkedList> fullList) {
    	int i = 0;
    	int rowN = 1;
    	
    	 try {
             String filename = "C://!//FullSearchReport.xls" ;
             HSSFWorkbook workbook = new HSSFWorkbook();
             HSSFSheet sheet = workbook.createSheet("AutomationReport");  

             HSSFRow rowhead = sheet.createRow((short)0);
             rowhead.createCell(0).setCellValue("#");
             rowhead.createCell(1).setCellValue("Search term");
             rowhead.createCell(2).setCellValue("Result");
             rowhead.createCell(3).setCellValue("Type");
             
				int iterations = fullList.size()/2;
				System.out.println("ITERATIONS : " + iterations);
           	 int first = 0;
           	 int second = 1;
           	int elementInOne = 0;
           //-----			
             for(int iter=0; iter<iterations; iter++) {
            	 int result = 0;
             
            	 int amount2 = fullList.get(second).size()/2; // *
             
            	 for(i = 0; i< amount2; i++) {
            	 	
            	 	HSSFRow row = sheet.createRow((short)rowN);
            	 	row.createCell(0).setCellValue(rowN);
            	 	row.createCell(1).setCellValue(fullList.get(first).get(elementInOne).toString());
            	 	row.createCell(2).setCellValue(fullList.get(second).get(result).toString());
            	 	row.createCell(3).setCellValue(fullList.get(second).get(++result).toString());
            	 	elementInOne++;
					          	 	
            	 	rowN++;
            	 
            	 	result++;
             	}
            	
            	 second+=2;
             }
    //-----             
             FileOutputStream fileOut = new FileOutputStream(filename);
             workbook.write(fileOut);
             fileOut.close();
             workbook.close();
             System.out.println("The excel report file has been generated!");

         } catch ( Exception ex ) {
             System.out.println(ex.fillInStackTrace() + " * Smthing wrong with excel method...");
         }
    }
    
    
    
	public void converterElements(List<WebElement> objectList) {
		String a = null;
		String b;
		for (ListIterator<WebElement> iter = objectList.listIterator(); iter.hasNext(); ) {
			WebElement element = iter.next();
			a = element.findElement(By.xpath("//div[@class='name ng-binding']")).getText();
			// b = element.findElement(By.xpath("//span[contains(text(),'')]")).getText();
			 System.out.println(a);
		}
		
		
	}
}
