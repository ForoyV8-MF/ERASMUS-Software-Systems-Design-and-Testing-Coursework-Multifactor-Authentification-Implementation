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

package MFA_Terminal_TEST;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.*;

import java.util.Scanner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import MFA_Terminal.*;

/**
 * Testing the Client Class.
 */
public class ClientsTest
{
    /**
     * ErrorCollector from JUnit 4.
     */
    @Rule public ErrorCollector collector = new ErrorCollector();

    String username_1 = "clclement2";
    String username_2 = "patrundle";
    String username_3 = "dascrimieri";

    /**
     * Test the verifyUsername() method (3 Assertions)
     */
    @Test public void testVerifyUsername()
    {
        try { assertTrue("Correct Username has been rejected", Clients.verifyUsername(username_1)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }

        try { assertTrue("Nonexistent Username has been accepted", !Clients.verifyUsername(username_2)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }

        try { assertTrue("Correct Username has been rejected", Clients.verifyUsername(username_3)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }
    }

    /**
     * Test the verifyPassword() method (4 Assertions)
     */
    @Test public void testVerifyPassword()
    {
        String password_1 = "azerty123";
        String password_2 = "azerty";
        String password_3 = "verystrongpassword1";

        try { assertTrue("Correct Password has been rejected", Clients.verifyPassword(username_1,password_1)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }

        try { assertTrue("Incorrect Password has been accepted", !Clients.verifyPassword(username_1,password_2)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }

        try { assertTrue("Incorrect Password has been accepted", !Clients.verifyPassword(username_3,password_1)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }

        try { assertTrue("Correct Password has been rejected", Clients.verifyPassword(username_3,password_3)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }
    }
}