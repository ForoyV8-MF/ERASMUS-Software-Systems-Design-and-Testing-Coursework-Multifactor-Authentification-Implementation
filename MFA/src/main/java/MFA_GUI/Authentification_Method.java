/****************************************************************************/
/*  ADAPTED BY : Vérin Clément                              ISIMA (ZZ2, F1) */
/*  Supervised by : Daniele Scrimieri                   BradFord University */
/*  February / March 2025                                                   */
/*                                                                          */
/*             CourseWork #2 MFA Extension & Further Testing                */
/*                                                                          */
/* Authentification_Method.java, Mail_Code.java, SMS_Code.java              */
/* Clients.java, Main.java                                                  */
/*                                                                          */
/* Platform_GUI.java, Windows_GUI.java, Linux_GUI.java                      */
/* Method_GUI.java, Mail_GUI.java, SMS_GUI.java                             */
/*                                                                          */
/* Package MFA_GUI                                                          */
/*                                                                          */
/* VS CODE                                                javac 17, javadoc */
/****************************************************************************/

package MFA_GUI;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;

/**
 * The main abstract class providing several method bodies for the upcoming authentification methods.
 * An authentification method basically has to ask information provided by the user.
 * And then, it revifies them.
 * @author Vérin Clément
 * @version 1.0
 */
public abstract class Authentification_Method
{
    /**
     * Information is either a Mail or a phone number. But it stays as String.
     */
    protected String information;

    /**
     * The correct code the user need to type.
     */
    protected int code_sent;

    /**
     * The last code entered by user.
     */
    protected int code_entered;

    /**
     * Scanner used by both this class and his test phase.
     */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * A Latch waiting for Client's next entry from GUI.
     */
    public CountDownLatch latchMethod;

    /**
     * Will have to verify is the given information (Mail or phone number) is correct.
     * @param information Mail or Phone number (String)
     * @return True if correct, False if not
     */
    public abstract boolean isCorrectInformation(String information);

    /**
     * Verify is the code sent is equal to the user's given code. (Code Format XXX XXX)
     * @param code the user's given code.
     * @return boolean, true if correct code, false if not.
     */
    public boolean isCorrectCode(int code)
    {
        if(code == code_sent)
            return true;
        else
            return false;
    }

    /**
     * This method contains everything related to information entering and user's given informations.
     */
    public abstract void getInformation();

    /**
     * Ask for the user's received code, only between 100 000 and 999 999.
     * The function waits 1 second at the end, to simulate the verification delay of the code.
     */
    public abstract void verifyInformation();

    /**
     * code_sent getter.
     * @return the last code sent.
     */
    public int get_code_sent()
    {
        return code_sent;
    }

    /**
     * code_entered getter.
     * @return the last code enterred.
     */
    public int getCodeEntered()
    {
        return code_entered;
    }

    /**
     * code_enterred Setter.
     * @param code The code to set
     */
    public void setCodeEntered(int code)
    {
        this.code_entered = code;
    }

    /**
     * Parameter Getter.
     * @return The current Parameter (Mail or Phone number)
     */
    public String getParameter()
    {
        return this.information;
    }

    /**
     * code_sent getter.
     * @param parameter The Parameter to enter (Mail or Phone number)
     */
    public void setInformation(String parameter)
    {
        this.information = parameter;
    }
}