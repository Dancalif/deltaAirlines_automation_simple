package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.FlightStatusTab_POF;

public class FlightStatusTab_Test extends Main_Test {
	@Test(enabled = true)
	@Parameters({ "flightNumber" })
	public void verifyFlightStatusResuts(String flightNumber) throws ParseException, InterruptedException {
		FlightStatusTab_POF flightStatusTab = new FlightStatusTab_POF();
		// Navigate to Flight Status tab page
		flightStatusTab.clickFlightStatusTab(driver);
		// To click on flight date drop down menu
		flightStatusTab.clickFlightDateOptionMenu(driver);
		// Logic to randomly select flight date option
		// Collect the web elements to select: Flight Date Options
		flightStatusTab.clickRandomElementFromList(driver);
		// Retrieving the selected flight date
		String mySelectedDate = flightStatusTab.parseSelectedFlightDates(driver);
		// Entering flight number
		flightStatusTab.enterFlightNumber(driver, flightNumber);
		// Click View Status Button
		flightStatusTab.clickViewStatusButton(driver);
		// Retrieving the flight number to verify
		String foundFlightNumber = flightStatusTab.parseFlightNumber(driver);
		// Asserting if flight number is correct
		Assert.assertTrue(foundFlightNumber.equalsIgnoreCase(flightNumber), "The flight number is wrong, please check");
		// Retrieving the flight date to assert
		String flightDate = flightStatusTab.parseFlightDates(driver);
		// Formating the dates and compare them to assert
		DateFormat format = new SimpleDateFormat("MMMM dd yyyy", Locale.ENGLISH);
		DateFormat format1 = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
		Date date = format.parse(mySelectedDate);
		Date date1 = format1.parse(flightDate);
		// Asserting if the selected and returned dates are matching
		Assert.assertEquals(date, date1, "The dates are wrong, please verify.");
	}
}
