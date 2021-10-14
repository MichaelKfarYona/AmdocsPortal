package tests;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.DefaultEditorKit.PasteAction;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.TestNG;

import com.sun.jna.platform.win32.User32;


public class _exe {
	static ChromeDriver driver;
	
	
	public static String createDate(int number) {
		SimpleDateFormat SDFormat = new SimpleDateFormat("E MM/dd/yyyy"); 
	     Calendar cal = Calendar.getInstance(); 

	     String curr_date = SDFormat.format(cal.getTime()); 
	     System.out.println("Formatted Date: " + curr_date);
	     cal.add(Calendar.DATE, number);
	     
	     String newDate = SDFormat.format(cal.getTime()); // substract 1 month
	     System.out.println("Formatted Date: " + newDate);
	     return newDate;
	}
	
	public static String createTime(int d, int m) {
		SimpleDateFormat SDFormat = new SimpleDateFormat("h:mm a"); 
	     Calendar cal = Calendar.getInstance(); 

	     String curr_date = SDFormat.format(cal.getTime()); 
	     System.out.println("Formatted Date: " + curr_date);
	     
	     cal.add(Calendar.HOUR, d);
	     cal.add(Calendar.MINUTE, m);
	     
	     String newTime = SDFormat.format(cal.getTime()); 
	     System.out.println("Formatted Time: " + newTime);
	     return newTime;
	}
	
	public static void pasteString(String anystring) throws AWTException {
		StringSelection stringSelection2 = new StringSelection(anystring);
		Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard2.setContents(stringSelection2, stringSelection2);
		Robot rob = new Robot();
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_V);
		rob.keyRelease(KeyEvent.VK_V);
		rob.keyRelease(KeyEvent.VK_CONTROL); rob.delay(500);
	}

	
	// create screen shot
	 private static File getHomeDir() {
	        FileSystemView fsv = FileSystemView.getFileSystemView();
	        return fsv.getHomeDirectory();
	    }
	 
	 private static BufferedImage grabScreen() { 
	        try {
	            return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	        } catch (SecurityException e) {
	        } catch (AWTException e) {
	        }
	        return null;
	    }
	 
	 /**
	 * @param iteration
	 * @param XML
	 */
	public static void runTestSuite(int iteration, String XML) {
	      List<String> suites = new ArrayList<String>(); 
	      suites.add(XML); //path of .xml file to be run-provide complete path 
	      TestNG tng = new TestNG(); 
	      tng.setTestSuites(suites); 
	      	for(int i=0;i<iteration;i++) { 
	      		tng.run(); i++; }//run test suite
		}
	// delete file
	public static void deleteTheFile(String fullPath) {
		File file = new File(fullPath);
	    if(file.delete()){
	        System.out.println("File deleted!");
	    }else System.out.println("There is no such file in the list");
		}
	 // MAIN
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		String characterCode = "\033[31;44;1m"; 
		char character = 'A';
		System.out.println(characterCode + character);
		System.out.println((char) 27 + "[31mWarning! " + (char)27 + "[0m");
		System.out.println((char)27 + "[31m" + "ERROR MESSAGE IN RED");
		
		ArrayList list = new ArrayList();
		System.out.println(list.isEmpty());
		














	}


}
