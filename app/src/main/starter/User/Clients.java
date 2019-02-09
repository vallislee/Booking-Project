package User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ngivy1
 *
 */

public class Clients extends Users {

  // Creates a map used to store the client information
  @SuppressWarnings("rawtypes")
  private Map client = new HashMap();

  /**
   * @author ngivy1 Constructor
   * 
   * @param email
   *          --> stores the email address of the client which would be used as
   *          the key
   * @param password
   *          --> stores the password of the client's account
   */
  @SuppressWarnings("unchecked")
  public Clients(String email, String password) {
    client.put(email, password);
  }

  /**
   * @author ngivy1 This method is used to add billing information so that the
   *         clients are able to pay for their vacations.
   * @param lname
   *          --> stores the last name of the client
   * @param fname
   *          --> stores the first name of the client
   * @param address
   *          --> stores the address of the client
   * @param payment
   *          --> stores the credit-card number of the client
   * @param expiry
   *          --> stores the expiry date of the credit card in the format of
   *          YYYY-MM-DD
   */
  @SuppressWarnings("unchecked")
  public void addBillingInfo(String email, String lName, String fName, String address, String payment, String expiry) {
    @SuppressWarnings("rawtypes")
    Set value = new HashSet();
    // Puts all the given information into a set
    value.add(lName);
    value.add(fName);
    value.add(address);
    value.add(payment);
    value.add(expiry);
    // Fills in the map using the email as a key
    client.put(email, value);
  }

  /**
   * @author ngivy1 This method is used to edit the billing information of the
   *         client. And in order to do so, the client must enter ALL
   *         information required.
   * @param lname
   *          --> stores the last name of the client
   * @param fname
   *          --> stores the first name of the client
   * @param address
   *          --> stores the address of the client
   * @param payment
   *          --> stores the credit-card number of the client
   * @param expiry
   *          --> stores the expiry date of the credit card in the format of
   *          YYYY-MM-DD
   */
  @SuppressWarnings("unchecked")
  public void editBillingInfo(String email, String lName, String fName, String address, String payment, String expiry) {
    {
      @SuppressWarnings("rawtypes")
      Set value = new HashSet();
      // Puts all the given information into a set
      value.add(lName);
      value.add(fName);
      value.add(address);
      value.add(payment);
      value.add(expiry);
      // Fills in the map using the email as a key
      client.put(email, value);
    }
  }
}