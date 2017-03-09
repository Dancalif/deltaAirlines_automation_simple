package pageObjects;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import main.Util.WebUtil;

public class BookTripTab_POF {
	// int days = 14;
	boolean booleanFlag = false;
	boolean ifDisplayed = false;

	By findFlightsButton = By.id("findFlightsSubmit");
	By bookATripTab = By.xpath(".//*[@id='navBookTrip']");
	By departureDateTextfield = By.xpath(".//*[@id='departureDate']");
	By returnDateTextfield = By.xpath(".//*[@id='returnDate']");
	By paginationFlightResultsBottom = By.xpath(".//*[@id='_paginationBottom_tmplHolder']/ul/li[1]/span");
	By bookATripTitle = By.xpath(".//*[@id='_tripSummaryHeader_tmplHolder']/div[1]/h1");
	By statesList = By.xpath(".//*[@id='scroll_stateProvince'] // div // div // ul // li");
	By statesAirportList = By.xpath(".//*[@id='scroll_stateProvinceAirport'] // div // ul // li");
	By countriesAirportsList = By.xpath(".//*[@id='country_button']/div[4] // div // ul // li");
	By countryList = By.xpath(".//*[@id='scroll_country'] // div // ul // li");
	By fromTextfield = By.xpath(".//*[@id='srcCityLookup']");
	By toTextfield = By.xpath(".//*[@id='destCityLookup']");
	By usAndCanada = By.xpath(".//*[@id='ui-id-4']");
	By country = By.xpath(".//*[@id='ui-id-3']");

	public void verifyFindFlightSubmitButton(WebDriver driver) {
		// To verify if Find Flight button is displayed
		WebUtil.waitForElementVisible(driver, findFlightsButton);
		try {
			ifDisplayed = WebUtil.ifElementDisplayed(driver, findFlightsButton);
		} catch (Exception e) {
		}
		if (ifDisplayed == false) {
			// Click on Book a Trip tab
			WebUtil.click(driver, bookATripTab);
			ifDisplayed = WebUtil.ifElementDisplayed(driver, findFlightsButton);
			// Verify if user is navigated to Book a Trip module
			Assert.assertTrue(ifDisplayed = false, "Couldn't get Find Flight page. Please verify what's wrong.");
		}
	}

	// Entering From destination
	public void enterFromDestination(WebDriver driver, String stateWantedFrom, String airportWantedFrom)
			throws InterruptedException {
		WebUtil.click(driver, fromTextfield);
		WebUtil.click(driver, usAndCanada);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> stateList = WebUtil.createListOfElements(driver, statesList);
		do {
			for (int i = 0; i < stateList.size(); i++) {
				if (booleanFlag == true) {
					break;
				}
				js.executeScript("arguments[0].click();", stateList.get(i));
				if (stateList.get(i).getText().equalsIgnoreCase(stateWantedFrom)) {
					stateList.get(i).click();
					Thread.sleep(1000);
					try {
						List<WebElement> airports = WebUtil.createListOfElements(driver, statesAirportList);
						for (int k = 0; k < airports.size(); k++) {
							js.executeScript("arguments[0].scrollIntoView(true);", airports.get(k));
							if (airports.get(k).getText().contains(airportWantedFrom)) {
								airports.get(k).click();
								booleanFlag = true;
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
		} while (booleanFlag == false);
	}

	// Entering To destination
	public void enterToDestionation(WebDriver driver, String countryWantedTo, String airportWantedTo)
			throws InterruptedException {
		booleanFlag = false;
		WebUtil.click(driver, toTextfield);
		WebUtil.click(driver, country);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> countriesList = WebUtil.createListOfElements(driver, countryList);
		do {
			for (int i = 0; i < countriesList.size(); i++) {
				if (booleanFlag == true) {
					break;
				}
				js.executeScript("arguments[0].click();", countriesList.get(i));
				countriesList.get(i).click();
				Thread.sleep(1000);
				if (countriesList.get(i).getText().equalsIgnoreCase(countryWantedTo)) {
					countriesList.get(i).click();
					Thread.sleep(500);
					try {
						List<WebElement> airports = WebUtil.createListOfElements(driver, countriesAirportsList);
						for (int k = 0; k < airports.size(); k++) {
							js.executeScript("arguments[0].scrollIntoView(true);", airports.get(k));
							if (airports.get(k).getText().contains(airportWantedTo)) {
								airports.get(k).click();
								Thread.sleep(2000);
								booleanFlag = true;
								break;
							}
						}
					} catch (Exception e) {
					}
				}
			}
		} while (booleanFlag == false);
	}

	// Enter flight dates
	public void enterFlightDates(WebDriver driver, int daysFromCurrentDate) throws InterruptedException {
		WebUtil.input(driver, WebUtil.randDate(daysFromCurrentDate, 0), departureDateTextfield);
		Thread.sleep(1000);
		WebUtil.input(driver, WebUtil.randDate(daysFromCurrentDate, daysFromCurrentDate), returnDateTextfield);
		Thread.sleep(1000);
	}

	// Verify flight search results
	public int verifyFlightsSearchResult(WebDriver driver) throws InterruptedException {
		int totalSearchResults = 0;
		Thread.sleep(2000);
		String searchResults = driver.findElement(paginationFlightResultsBottom).getText();
		Pattern pattern = Pattern.compile("of (.*?) flight");
		Matcher matcher = pattern.matcher(searchResults);
		while (matcher.find()) {
			totalSearchResults = Integer.parseInt(matcher.group(1));
		}
		return totalSearchResults;
	}

	// Get Find Flights button visible and click on it
	public void clickFindFlightsButton(WebDriver driver) throws Exception {
		WebUtil.getElementVisible(driver, findFlightsButton);
		WebUtil.click(driver, findFlightsButton);
	}

	// Wait for Book A Trip Title to be visible
	public void waitForBookTripTitleVisible(WebDriver driver) {
		WebUtil.waitForElementVisible(driver, bookATripTitle);
	}
}