package driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Data.FlightInformation;
import Data.Itinerary;

/** A Driver used for autotesting the project backend. */
public class Driver<K,V> {

  @SuppressWarnings("rawtypes")
  public static Map clientMap = new HashMap();

  /**
   * @author: ngivy1 Uploads client information to the application from the file at
   *          the given path.
   * 
   * @param path
   *          the path to an input csv file of client information with lines in
   *          the format:
   *          LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate (the
   *          ExpiryDate is stored in the format YYYY-MM-DD)
   */
  @SuppressWarnings("unchecked")
  public static void uploadClientInfo(String path) {
    try {
      //Initialized buffered readers and file readers
      FileReader fileRead = new FileReader(path);
      BufferedReader in = new BufferedReader(fileRead);
      
      String line = " ";
      
      //Goes through the file
      while (in.readLine() != " "){
        line = in.readLine(); //Reads the file line by line
        String [] word = line.split(",");//Split by comma since it's csv file
        //initialize variables
        String lastName = word[0];
        String firstName = word[1];
        String email = word[2];
        String address = word[3];
        String expiry = word[4];
        
        //Create a new set
        @SuppressWarnings("rawtypes")
        HashSet clientInfoSet = new HashSet();
        //Store the variables within the set
        clientInfoSet.add(lastName);
        clientInfoSet.add(firstName);
        clientInfoSet.add(address);
        clientInfoSet.add(expiry);
        
        //Store the email as the key and the set of information as
        // the value
        clientMap.put(email, clientInfoSet);
      }
      in.close();
    } catch (FileNotFoundException fnfe) {
      System.out.println ("File not found");
    } catch (IOException ioe) {
      System.out.println ("Error");
    }
  }

  /**
   * @author ngivy1 Downloads the client information to a CSV file from the
   *         application using the string path and the map given.
   * 
   * @param path
   *          --> the path to a new CSV file of client information with lines in
   *          the format:
   *          LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate (the
   *          ExpiryDate is stored in the format YYYY-MM-DD)
   */
  public static void downloadClientInfo(String path) {
    try {
      FileWriter fileWrite = new FileWriter(path);
      BufferedWriter out = new BufferedWriter(fileWrite);
      
      //Obtains the set of keys within the map
      @SuppressWarnings({ "rawtypes" })
      Set V = clientMap.keySet();
      //Convert the set to an array
      Object [] clientKeys = V.toArray();
      
      //Goes through all the keys (emails)
      for (int i = 0; i< clientKeys.length;i++){
        //Gets the value of the key
        @SuppressWarnings("rawtypes")
        Set s = (Set) clientMap.get(clientKeys[i]);
        //Convert the set of values to an array
        Object [] arrayClient = s.toArray();
        String word = "";
        //Put the values in CSV file format
        if (i == 2){
          word = word + arrayClient[i] + ",";
        }
        else if (i == arrayClient.length - 1){
          word = word + arrayClient[i];
        }
        else{
          word = word + arrayClient[i] + ","; 
        }
        //Writes the line of client information to the file
        out.write(word);
      }
      
      out.close();
    } catch (FileNotFoundException fnfe) {
      System.out.println("File not found");
    } catch (IOException ioe) {
      System.out.println("Error");
    }
  }

  /**
   * Uploads flight information to the application from the file at the given
   * path.
   * 
   * @param path
   *          the path to an input csv file of flight information with lines in
   *          the format:
   *          Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,
   *          Destination,Price (the dates are in the format YYYY-MM-DD; the
   *          price has exactly two decimal places)
   */
  public static void uploadFlightInfo(String path) {
    BufferedReader bufferLine = null;
    String line = null;
    String delimeter = ",";
    
    try {
      bufferLine = new BufferedReader(new FileReader(path));
      while ((line = bufferLine.readLine()) != null) {
        String[] flight = line.split(delimeter);
        FlightInformation.setNumber(flight[0]);
        FlightInformation.setDepartureDateTime(flight[1]);
        FlightInformation.setArrivalDateTime(flight[2]);
        FlightInformation.setAirLine(flight[3]);
        FlightInformation.setOrigin(flight[4]);
        FlightInformation.setDestination(flight[5]);
        FlightInformation.setPrice(flight[6]);
        
        FlightInformation.setFlightInfo(flight[0], flight[1], flight[2], flight[3], flight[4], flight[5],
            flight[6]);
        
        FlightInformation.setFlights(FlightInformation.getFlightInfo());
      }
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    finally {
      if (bufferLine != null) {
        try {
          bufferLine.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * @author ngivy1 Returns the information stored for the client with the given
   *         email.
 * @param <E>
   * 
   * @param email
   *          the email address of a client
   * @return the information stored for the client with the given email in this
   *         format:
   *         LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate (the
   *         ExpiryDate is stored in the format YYYY-MM-DD)
   */
  public static <E> String getClient(String email) {
    // TODO: complete this method body
    //Get the value corresponding to the email(key)
    @SuppressWarnings("unchecked")
	Set<E> V = (Set<E>) clientMap.get(email);
    //Convert the set to an array
    Object [] arrayClient = V.toArray();
    String word = "";
    //Goes through the array with the client information
    for (int i = 0; i< arrayClient.length; i++){
      if (i == 2){
        word = word + email;
      }
      else{
        word = word + " "+ arrayClient[i]; 
      }
    }
    return word;
  }

  /**
   * Returns all flights that depart from origin and arrive at destination on
   * the given date.
   * 
   * @param date
   *          a departure date (in the format YYYY-MM-DD)
   * @param origin
   *          a flight origin
   * @param destination
   *          a flight destination
   * @return the flights that depart from origin and arrive at destination on
   *         the given date formatted with one flight per line in exactly this
   *         format:
   *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
   *         ,Price (the dates are in the format YYYY-MM-DD; the price has
   *         exactly two decimal places)
   */
  public static String getFlights(String date, String origin, String destination) {
	DecimalFormat decimal = new DecimalFormat("#.##");
	
    for (int i = 0; i < FlightInformation.getFlights().size(); i++) {
      String [] flight = (String[]) FlightInformation.getFlights().get(i).toArray();
      
      if (date == flight[1].substring(0,10) && origin == flight[4] && destination == flight[5]) {
    	  System.out.println(flight[0]+flight[1].substring(0,10)+flight[2].substring(0,10)+flight[3]+flight[4]+flight[5]+
    			  decimal.format(Double.parseDouble(flight[6])));
      }
    }
    return null;
  }

  /**
   * Returns all itineraries that depart from origin and arrive at destination
   * on the given date. If an itinerary contains two consecutive flights F1 and
   * F2, then the destination of F1 should match the origin of F2. To simplify
   * our task, if there are more than 6 hours between the arrival of F1 and the
   * departure of F2, then we do not consider this sequence for a possible
   * itinerary (we judge that the stopover is too long).
   * 
   * @param date
   *          a departure date (in the format YYYY-MM-DD)
   * @param origin
   *          a flight original
   * @param destination
   *          a flight destination
   * @return itineraries that depart from origin and arrive at destination on
   *         the given date with stopovers at or under 6 hours. Each itinerary
   *         in the output should contain one line per flight, in the format:
   *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
   *         followed by total price (on its own line, exactly two decimal
   *         places), followed by total duration (on its own line, in format
   *         HH:MM).
   * @throws ParseException 
   */
  public static String getItineraries(String date, String origin, String destination) throws ParseException {
	  Itinerary.setItinerary(date, origin, destination);
	  Itinerary.getItineraryList();
	  DecimalFormat decimal = new DecimalFormat("#.##");
	  SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	  ArrayList<String> flights = null;
	  
	  for (int i = 0; i < Itinerary.getItineraryList().size(); i++) {
		  Double totalPrice = 0.00;

		  String [] first = (String []) Itinerary.getItineraryList().get(i).get(1).toArray();
		  long startTime = dateTimeFormat.parse(first[1]).getTime();
		  
		  String [] last = (String []) Itinerary.getItineraryList().get(i).get(Itinerary.getItineraryList().size()-1).toArray();
		  long endTime = dateTimeFormat.parse(last[1]).getTime();
		  
		  for (int j = 0; j < Itinerary.getItineraryList().get(i).size(); j++) {
			  flights = Itinerary.getItineraryList().get(i).get(j);
			  
			  for (int k = 0; k < flights.size(); k++) {
				  String [] flight = (String []) flights.toArray();
				  totalPrice = totalPrice + Double.parseDouble(flight[6]);	
				  System.out.println(flight[0]+flight[1].substring(0,10)+flight[2].substring(0,10)+flight[3]+flight[4]+flight[5]+
						  decimal.format(Double.parseDouble(flight[6])));
			  }
		  }
		  System.out.println(decimal.format(totalPrice));
		  System.out.println((double) Math.abs((endTime - startTime)/(1000*60*60)));
	  }
	  return null;
  }
  /**
   * Returns the same itineraries as getItineraries produces, but sorted
   * according to total itinerary cost, in non-decreasing order.
   * 
   * @param date
   *          a departure date (in the format YYYY-MM-DD)
   * @param origin
   *          a flight original
   * @param destination
   *          a flight destination
   * @return itineraries (sorted in non-decreasing order of total itinerary
   *         cost) that depart from origin and arrive at destination on the
   *         given date with stopovers at or under 6 hours. Each itinerary in
   *         the output should contain one line per flight, in the format:
   *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
   *         followed by total price (on its own line, exactly two decimal
   *         places), followed by total duration (on its own line, in format
   *         HH:MM).
   */
 
  public static String getItinerariesSortedByCost(String date, String origin, String destination) {
      // getting itineraries as ArrayList.
      // BubbleSort.
      Double [] cost = new Double [Itinerary.getItineraryList().size()];
      ArrayList<String> flights = null;
      DecimalFormat decimal = new DecimalFormat("#.##");
      ArrayList<ArrayList<String>> tempItinerary;
      Double tempCost;
      
      for (int i = 0; i < Itinerary.getItineraryList().size(); i++) {
          for (int j = 0; j < Itinerary.getItineraryList().get(i).size(); j++) {
            // sign lenA as the length of the current Itinerary. Similarly to the second Itinerary.
        	String [] flight = (String [])Itinerary.getItineraryList().get(i).get(j).toArray();  
			cost[i] = cost[i]+Double.parseDouble(flight[6]);
          }
      }

      // compare the first and the second cost, and sign the temp as the first itinerary;
      // if the first itinerary is more expensive than the second then swap.
      for (int k = 1; k < Itinerary.getItineraryList().size(); k++) {
    	  if (cost[k-1] > cost[k]) {
    		  tempItinerary = Itinerary.getItineraryList().get(k-1);
    		  tempCost = cost[k-1];
    		  
    		  Itinerary.getItineraryList().set(k-1, Itinerary.getItineraryList().get(k));
    		  cost[k-1] = cost[k];
    		  
    		  Itinerary.getItineraryList().set(k, tempItinerary);
    		  cost[k] = tempCost;
    	  }
      }
      
      for (int x = 0; x < Itinerary.getItineraryList().size(); x++) {
    	  Double totalCost = 0.00;
    	  for (int y = 0; y < Itinerary.getItineraryList().get(x).size(); y++) {
    		  flights = Itinerary.getItineraryList().get(x).get(y);
			  for (int z = 0; z < flights.size(); z++) {
				  String [] flight = (String []) flights.toArray();
				  totalCost = totalCost + Double.parseDouble(flight[6]);	
				  System.out.println(flight[0]+flight[1].substring(0,10)+flight[2].substring(0,10)+flight[3]+flight[4]+flight[5]+
						  decimal.format(Double.parseDouble(flight[6])));
			  }  
    	  }
    	  System.out.println(decimal.format(totalCost));
      }
      return null;
  }
  
  /**
   * Returns the same itineraries as getItineraries produces, but sorted
   * according to total itinerary travel time, in non-decreasing order.
   * 
   * @param date
   *          a departure date (in the format YYYY-MM-DD)
   * @param origin
   *          a flight original
   * @param destination
   *          a flight destination
   * @return itineraries (sorted in non-decreasing order of travel itinerary
   *         travel time) that depart from origin and arrive at destination on
   *         the given date with stopovers at or under 6 hours. Each itinerary
   *         in the output should contain one line per flight, in the format:
   *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
   *         followed by total price (on its own line, exactly two decimal
   *         places), followed by total duration (on its own line, in format
   *         HH:MM).
   * @throws ParseException 
   */
  public static String getItinerariesSortedByTime(String date, String origin, String destination) throws ParseException {
	  Double [] timePeriod = new Double [Itinerary.getItineraryList().size()];
	  DecimalFormat decimal = new DecimalFormat("#.##");
      ArrayList<String> flights = null;
      SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      ArrayList<ArrayList<String>> tempItinerary;
      Double tempTimePeriod;
      
      for (int i = 0; i < Itinerary.getItineraryList().size(); i++) {
          for (int j = 0; j < Itinerary.getItineraryList().get(i).size(); j++) {
            // sign lenA as the length of the current Itinerary. Similarly to the second Itinerary.
			String [] first = (String []) Itinerary.getItineraryList().get(i).get(1).toArray();
   		    long startTime = dateTimeFormat.parse(first[1]).getTime();
  		  
  		    String [] last = (String []) Itinerary.getItineraryList().get(i).get(Itinerary.getItineraryList().size()-1).toArray();
  		    long endTime = dateTimeFormat.parse(last[1]).getTime();
			
  		    timePeriod[i] = (double) Math.abs((endTime - startTime)/(1000*60*60));
          }
      }

      // compare the first and the second cost, and sign the temp as the first itinerary;
      // if the first itinerary is longer than the second then swap.
      for (int k = 1; k < Itinerary.getItineraryList().size(); k++) {
    	  if (timePeriod[k-1] > timePeriod[k]) {
    		  tempItinerary = Itinerary.getItineraryList().get(k-1);
    		  tempTimePeriod = timePeriod[k-1];
    		  
    		  Itinerary.getItineraryList().set(k-1, Itinerary.getItineraryList().get(k));
    		  timePeriod[k-1] = timePeriod[k];
    		  
    		  Itinerary.getItineraryList().set(k, tempItinerary);
    		  timePeriod[k] = tempTimePeriod;
    	  }
      }
      
      for (int x = 0; x < Itinerary.getItineraryList().size(); x++) {
    	  for (int y = 0; y < Itinerary.getItineraryList().get(x).size(); y++) {
    		  flights = Itinerary.getItineraryList().get(x).get(y);
			  for (int z = 0; z < flights.size(); z++) {
				  String [] flight = (String []) flights.toArray();
				  System.out.println(flight[0]+flight[1].substring(0,10)+flight[2].substring(0,10)+flight[3]+flight[4]+flight[5]+
						  decimal.format(Double.parseDouble(flight[6])));
			  }  
    	  }
    	  System.out.println(timePeriod[x]);
      }
      return null;
  }
}