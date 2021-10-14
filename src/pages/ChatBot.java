package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.AmdocsPortalPage.ApplicationName;

public class ChatBot {
	WebDriver driver;

	public enum AreaOfSupport {
		ACCOUNTS_AND_PERMISSIONS, MOBILE_AND_TELEPHONE, OUTLOOK_EMAIL_CALENDAR, INFRA_DBA, SOFTWARE, UNIX,
		CONFERENCING_TEAMS_AND_SKYPE
	}

	public ChatBot(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectAreaForSupport(AreaOfSupport item) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@data-icon-name='allAppsLogo']")));
		WebElement allAppsCor = driver.findElement(By.xpath("//i[@data-icon-name='allAppsLogo']"));
		allAppsCor.click();

		String parametrListItem = null;
		switch (item) {
		case ACCOUNTS_AND_PERMISSIONS:
			parametrListItem = "App1";
			break;
		case MOBILE_AND_TELEPHONE:
			parametrListItem = "App1";
			break;
		case OUTLOOK_EMAIL_CALENDAR:
			parametrListItem = "App1";
			break;
		case INFRA_DBA:
			parametrListItem = "App1";
			break;
		case SOFTWARE:
			parametrListItem = "App1";
			break;
		case UNIX:
			parametrListItem = "App1";
			break;
		case CONFERENCING_TEAMS_AND_SKYPE:
			parametrListItem = "App1";
			break;

		}
		WebElement leftMenuItemElement = driver.findElement(By.xpath("//div[@title='" + parametrListItem + "']"));
		leftMenuItemElement.click();
	}

}
