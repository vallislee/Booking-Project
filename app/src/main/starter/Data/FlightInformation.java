package Data;

import java.util.ArrayList;

/**
 * 
 */

/**
 * @author hungalex
 * @param <T>
 *
 */
public class FlightInformation {
	private static String number;
	private static String departureDateTime;
	private static String arrivalDateTime;
	private static String airLine;
	private static String origin;
	private static String destination;
	private static String price;
	
	private static ArrayList<String> flightInfo = new ArrayList<String>();
	private static ArrayList<ArrayList<String>> flights = new ArrayList<ArrayList<String>>();
	
	/**
	 * @param number2
	 * @param departureDateTime
	 * @param arrivalDateTime
	 * @param airLine
	 * @param origin
	 * @param destination
	 * @param price2
	 */
	public FlightInformation(String number, String departureDateTime, String arrivalDateTime, String airLine,
			String origin, String destination, String price) {
		super();
		FlightInformation.number = number;
		FlightInformation.departureDateTime = departureDateTime;
		FlightInformation.arrivalDateTime = arrivalDateTime;
		FlightInformation.airLine = airLine;
		FlightInformation.origin = origin;
		FlightInformation.destination = destination;
		FlightInformation.price = price;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public static void setNumber(String number) {
		FlightInformation.number = number;
	}

	/**
	 * @return the departureDateTime
	 */
	public String getDepartureDateTime() {
		return departureDateTime;
	}

	/**
	 * @param departureDateTime the departureDateTime to set
	 */
	public static void setDepartureDateTime(String departureDateTime) {
		FlightInformation.departureDateTime = departureDateTime;
	}

	/**
	 * @return the arrivalDateTime
	 */
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}

	/**
	 * @param arrivalDateTime the arrivalDateTime to set
	 */
	public static void setArrivalDateTime(String arrivalDateTime) {
		FlightInformation.arrivalDateTime = arrivalDateTime;
	}

	/**
	 * @return the airLine
	 */
	public String getAirLine() {
		return airLine;
	}

	/**
	 * @param airLine the airLine to set
	 */
	public static void setAirLine(String airLine) {
		FlightInformation.airLine = airLine;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public static void setOrigin(String origin) {
		FlightInformation.origin = origin;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public static void setDestination(String destination) {
		FlightInformation.destination = destination;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public static void setPrice(String price) {
		FlightInformation.price = price;
	}
	
	public static void setFlightInfo(String number, String departureDateTime, String arrivalDateTime,
			String airLine, String origin, String destination, String price) {
		flightInfo.add(number);
		flightInfo.add(departureDateTime);
		flightInfo.add(arrivalDateTime);
		flightInfo.add(airLine);
		flightInfo.add(origin);
		flightInfo.add(destination);
		flightInfo.add(price);
	}
	
	public static ArrayList<String> getFlightInfo() {
		return flightInfo;
	}

	public static void setFlights(ArrayList<String> flightInfo) {
		flights.add(flightInfo);
	}
	
	public static ArrayList<ArrayList<String>> getFlights() {
		return flights;
	}
}
