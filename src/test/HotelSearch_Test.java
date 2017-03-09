package test;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HotelSearch_POF;

public class HotelSearch_Test extends Main_Test {
	@Test(enabled = true)
	@Parameters({ "daysFromCurrentDate", "inputLocation", "locationInDropdown" })
	public void verifyHotelSearch(int daysFromCurrentDate, String inputLocation, String locationInDropdown)
			throws InterruptedException {
		HotelSearch_POF hotelSearch = new HotelSearch_POF();
		// Getting drop down under shop upper tab
		hotelSearch.mouseOverOnShopTab(driver);
		// Clicking Hotels option
		hotelSearch.clickHotelsOption(driver);
		// Enter hotel location
		hotelSearch.enterHotelLocation(driver, inputLocation);
		hotelSearch.createHotelLocationsList(driver, locationInDropdown);
		// Enter check in and out dates
		hotelSearch.enterCheckInOutDate(driver, daysFromCurrentDate);
		// Selecting number of rooms
		hotelSearch.clickHotelNumberOfRooms(driver);
		hotelSearch.clickRandomElementFromList(driver);
		// Selecting number of adults
		hotelSearch.selectNumberOFAdults(driver);
		// Clicking find hotels button
		hotelSearch.clickFindHotelsButton(driver);
		// Asserting of user was navigated to the expected page
		hotelSearch.waitForHotelSearchResultsTitle(driver);
		// Double verify - if the number of hotels foud is greater than 0
		int totalNumHotels = hotelSearch.getStringParseToInt(driver);
		Assert.assertTrue(totalNumHotels > 0);
	}
}
