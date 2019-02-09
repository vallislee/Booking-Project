/**
 * 
 */
package Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author hungalex
 *
 */
public class Itinerary extends FlightInformation {
	private static ArrayList<ArrayList<String>> itinerary = new ArrayList<ArrayList<String>>();
	private static ArrayList<ArrayList<ArrayList<String>>> itineraryList = new ArrayList<ArrayList<ArrayList<String>>>();
	
	/**
	 * @param number
	 * @param departureDateTime
	 * @param arrivalDateTime
	 * @param airLine
	 * @param origin
	 * @param destination
	 * @param price
	 */
	public Itinerary(String number, String departureDateTime, String arrivalDateTime, String airLine, String origin,
			String destination, String price) {
		super(number, departureDateTime, arrivalDateTime, airLine, origin, destination, price);
	}

	public static void setItinerary(String date, String origin, String destination) throws ParseException {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		if (date.length() <= 10) {
			for (int i = 0; i < FlightInformation.getFlights().size(); i++) {
				String [] flight = (String []) FlightInformation.getFlights().get(i).toArray();
				if (date == flight[1].substring(0,10) && origin == flight[4]){
					itinerary.add(FlightInformation.getFlights().get(i));
					if (destination == flight[5]) {
						setItineraryList();
						itinerary = null;
					}
					else {
						setItinerary(date, flight[5], destination);
					}
				}		
			}
		}
		else {
			
			long arrivalTime = dateTimeFormat.parse(date).getTime();
			
			for (int i = 0; i < FlightInformation.getFlights().size(); i++) {
				String [] flight = (String []) FlightInformation.getFlights().get(i).toArray();
				long departureTime = dateTimeFormat.parse(flight[6]).getTime();
				if (Math.abs((arrivalTime-departureTime)/(1000*60*60)) <= 6 && origin == flight[4]){
					itinerary.add(FlightInformation.getFlights().get(i));
					if (destination == flight[5]) {
						setItineraryList();
						itinerary = null;
					}
					else {
						setItinerary(date, flight[5], destination);
					}
				}		
			}
		}
	}
	
	public ArrayList<ArrayList<String>> getItineary() {
		return itinerary;
	}
	
	public static void setItineraryList() {
		itineraryList.add(itinerary);
	}
	
	public ArrayList<ArrayList<ArrayList<String>>> getItineraryList1() {
		return itineraryList;
	}
	
	public static ArrayList<ArrayList<String>> getItinerary() {
		return itinerary;
	}
	
	public static ArrayList<ArrayList<ArrayList<String>>> getItineraryList() {
		itineraryList.add(itinerary);
		
		return itineraryList;
	}
}
