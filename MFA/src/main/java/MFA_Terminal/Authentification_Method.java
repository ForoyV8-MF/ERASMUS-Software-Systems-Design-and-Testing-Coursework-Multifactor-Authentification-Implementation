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

/**
 * The main interface providing two function bodies for the upcoming authentification methods.
 * An authentification method basically has to ask information provided by the user -> first method.
 * And then, it revifies them -> 2nd method.
 * @author Vérin Clément
 * @version 1.0
 */
public interface Authentification_Method
{
    /**
     * This method contains everything related to information entering and user's given informations.
     */
    void getInformation();

    /**
     * This method contains everything related to information verification.
     */
    void verifyInformation();
}