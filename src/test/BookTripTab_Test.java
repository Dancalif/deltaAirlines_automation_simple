package test;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.BookTripTab_POF;

public class BookTripTab_Test extends Main_Test {

	@Test(enabled = true)
	@Parameters({ "stateWantedFrom", "airportWantedFrom", "countryWantedTo", "airportWantedTo", "daysFromCurrentDate" })
	public void verifyFlightSearchResuts(String stateWantedFrom, String airportWantedFrom, String countryWantedTo,
			String airportWantedTo, int daysFromCurrentDate) throws Exception {
		BookTripTab_POF bookTripTab = new BookTripTab_POF();
		// To verify if Find Flight button is displayed
		bookTripTab.verifyFindFlightSubmitButton(driver);
		// Entering From and To destinations
		bookTripTab.enterFromDestination(driver, stateWantedFrom, airportWantedFrom);
		bookTripTab.enterToDestionation(driver, countryWantedTo, airportWantedTo);
		// Getting random dates based on random number method.
		// Departure date = current date + certain number of days. Return
		// date = departure date + some number of days.
		bookTripTab.enterFlightDates(driver, daysFromCurrentDate);
		// Get Find Flights button visible and click on it
		bookTripTab.clickFindFlightsButton(driver);
		bookTripTab.waitForBookTripTitleVisible(driver);
		// Verify if search results are displayed
		int totalSearchResults = bookTripTab.verifyFlightsSearchResult(driver);
		Assert.assertTrue(totalSearchResults > 0, "Number of flights is less than 0");
	}
}
