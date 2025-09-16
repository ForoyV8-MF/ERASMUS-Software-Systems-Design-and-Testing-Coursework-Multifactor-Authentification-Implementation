/****************************************************************************/
/*  ADAPTED BY : Vérin Clément                              ISIMA (ZZ2, F1) */
/*  Supervised by : Daniele Scrimieri                   BradFord University */
/*  February / March 2025                                                   */
/*                                                                          */
/*                    CourseWork #1 MFA Implementation                      */
/*                                                                          */
/* Authentification_Method.java, SMS_Code.java, Mail_Code.java              */
/* Clients.java, Main.java, Color_Printer.java                              */
/* Clients_Test.java                                                        */
/* SMS_Code_Test.java, Mail_Code_Test.java                                  */
/* Package MFA_Java                                                         */
/*                                                                          */
/* VS CODE                                                   javac, javadoc */
/****************************************************************************/

package MFA_Terminal;

import java.util.Map;
import java.util.HashMap;

/**
 * This class contains every user's username and password, stored in a HashTable.
 * This only adapts the HashMap provided in the Lab1.
 * Everything is Static in this class, so we don't have to instance each client in a class thanks to the HashMap.
 * @author Vérin Clément, Dr Daniele Scrimieri
 */
public class Clients
{
    private static final Map<String, String> CLIENTS_DATA_BASE = new HashMap<>();

    static
    {
        CLIENTS_DATA_BASE.put("clclement2", "azerty123");
        CLIENTS_DATA_BASE.put("dascrimieri", "verystrongpassword1");
    }

    /**
     * This method consults the hash table to verify if it contains the given username.
     * @param givenUsername the username to verify.
     * @return boolean, true if correct username, false if not.
     */
    public static final boolean verifyUsername(String givenUsername)
    {
        if(CLIENTS_DATA_BASE.containsKey(givenUsername))
            return true;
        else
            return false;
    }

    /**
     * This method consults the hash table to verify if the given password matches with the username given previously.
     * @param givenUsername the related Username.
     * @param givenPassword the password to verify.
     * @return boolean, true if correct password, false if not.
     */
    public static final boolean verifyPassword(String givenUsername, String givenPassword)
    {
        if(givenPassword.equals(CLIENTS_DATA_BASE.get(givenUsername)))
            return true;
        else
            return false;
    }
}