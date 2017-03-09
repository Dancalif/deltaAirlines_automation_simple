package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import main.Util.WebUtil;

public class HotelSearch_POF {

	By shopDropdownMenu = By.xpath(".//*[@id='dropDownMenubar']/ul/li[1]/div[1]/a");
	By hotelOption = By.xpath("//a[contains(@href, '/content/www/en_US/shop/hotels.html')]");
	By hotelLocationTextfield = By.xpath(".//*[@id='hotelLocation']");
	By checkInDateTextfield = By.xpath(".//*[@id='hotelCheckInDate']");
	By checkOutDateTextfield = By.xpath(".//*[@id='hotelCheckOutDate']");
	By roomsDropdownMenu = By.xpath(".//*[@id='hotelNumberOfRooms-button']/span[1]");
	By adultsDropdownMenu = By.xpath(".//*[@id='hotelNumberOfAdults-button']/span[1]");
	By findHotelsButton = By.xpath(".//*[@id='btnSubmit']");
	By numberOfRoomsDropdown = By.xpath(".//*[@id='hotelNumberOfRooms-menu'] //li");
	By totalNumberOfHotels = By.xpath(".//*[@id='totalProducts']");
	By hotelSearchResultsTitle = By.xpath(".//*[@id='searchHotelForm']/h2");
	By hotelLocationsDropdownMenu = By.xpath(".//*[@id='ui-id-3']//li");

	// Mouse over on shop tab
	public void mouseOverOnShopTab(WebDriver driver) {
		WebUtil.mouseOver(driver, shopDropdownMenu);
	}

	// Click Hotels Option
	public void clickHotelsOption(WebDriver driver) {
		WebUtil.click(driver, hotelOption);
	}

	// Enter Hotel Location
	public void enterHotelLocation(WebDriver driver, String inputLocation) throws InterruptedException {
		Thread.sleep(1000);
		WebUtil.clearTextfield(driver, hotelLocationTextfield);
		WebUtil.input(driver, inputLocation, hotelLocationTextfield);
	}

	// Enter Check In and Out Dates
	public void enterCheckInOutDate(WebDriver driver, int daysFromCurrentDate) throws InterruptedException {
		WebUtil.input(driver, WebUtil.randDate(daysFromCurrentDate, 0), checkInDateTextfield);
		Thread.sleep(1000);
		WebUtil.input(driver, WebUtil.randDate(daysFromCurrentDate, daysFromCurrentDate), checkOutDateTextfield);
		Thread.sleep(1000);
	}

	// Click Hotel Number Of Rooms
	public void clickHotelNumberOfRooms(WebDriver driver) {
		WebUtil.click(driver, roomsDropdownMenu);
	}

	// Select Number OF Adults
	public void selectNumberOFAdults(WebDriver driver) {
		try {
			WebUtil.click(driver, adultsDropdownMenu);
			List<WebElement> adults = WebUtil.createListOfElements(driver, adultsDropdownMenu);
			int totalAdults = 1 + WebUtil.randNumber(7);
			adults.get(totalAdults).click();
		} catch (Exception e) {
		}
	}

	// Click Find Hotels Button
	public void clickFindHotelsButton(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebUtil.click(driver, findHotelsButton);
	}

	// Create Hotel Locations List
	public void createHotelLocationsList(WebDriver driver, String locationInDropdown) {
		List<WebElement> HotelNumberOfRoomsLis = WebUtil.createListOfElements(driver, hotelLocationsDropdownMenu);
		for (int i = 0; i < HotelNumberOfRoomsLis.size(); i++) {
			String location = HotelNumberOfRoomsLis.get(i).getText();
			if (location.equalsIgnoreCase(locationInDropdown)) {
				HotelNumberOfRoomsLis.get(i).click();
			}
		}
	}

	// Click Random Element From List
	public void clickRandomElementFromList(WebDriver driver) {
		List<WebElement> HotelNumberOfRoomsLis = WebUtil.createListOfElements(driver, numberOfRoomsDropdown);
		int roomNum = WebUtil.randNumber(7);
		HotelNumberOfRoomsLis.get(roomNum).click();
	}

	// Wait For Hotel Search Results Title
	public void waitForHotelSearchResultsTitle(WebDriver driver) {
		WebUtil.waitForElementVisible(driver, hotelSearchResultsTitle);
		Assert.assertEquals(driver.findElement(hotelSearchResultsTitle).getText().toLowerCase(),
				"hotel search results");
	}

	// Get String Parse To Int
	public int getStringParseToInt(WebDriver driver) {
		int totalNumHotels = WebUtil.convertStringToInt(driver, totalNumberOfHotels);
		return totalNumHotels;
	}
}
