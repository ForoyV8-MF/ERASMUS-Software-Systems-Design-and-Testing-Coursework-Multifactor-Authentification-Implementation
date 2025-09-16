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
 * Testing the Mail_Code Class.
 */
public class MailCodeTest
{
    InputStream originalStream_in;
    PrintStream originalStream_out;

    PrintStream ignoringStream;
    String simulatedOutput;

    Mail_Code mail_Method;

    /**
     * Creating a new instance for testing and blocking the prints.
     * Blocking the prints.
     */
    @Before public void disablePrintsAndDeclaration()
    {
        originalStream_in = System.in;
        originalStream_out = System.out;
    
        ignoringStream = new PrintStream(new java.io.ByteArrayOutputStream());
        System.setOut(ignoringStream);

        mail_Method = new Mail_Code();
    }

    /**
     * ErrorCollector from JUnit 4.
     */
    @Rule public ErrorCollector collector = new ErrorCollector();

    /**
     * Test the isCorrectMail() method (3 Assertions).
     */
    @Test public void testIsCorrectMail()
    {
        String mail_1 = "verin.clement03@gmail.com";
        String mail_2 = "carvrinbradford.ac.uk";
        String mail_3 = "carvrin@bradford.ac.uk";

        try { assertTrue("Correct Mail has been rejected", mail_Method.isCorrectMail(mail_1)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }

        try { assertFalse("Incorrect Mail has been accepted", mail_Method.isCorrectMail(mail_2)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }

        try { assertTrue("Correct Mail has been rejected", mail_Method.isCorrectMail(mail_3)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }
    }

    /**
     * Test the isCorrectCode() method (2 Assertions).
     */
    @Test public void testIsCorrectCode()
    {
        int code_1 = 100000;
        int code_2 = 150000;

        try { assertTrue("Correct Code has been rejected", mail_Method.isCorrectCode(code_1)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }

        try { assertFalse("Incorrect Code has been accepted", mail_Method.isCorrectCode(code_2)); }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }
    }

    /**
     * Test the getInformation() method by submitting a simulated input (2 Assertions).
     */
    @Test public void testGetInformation()
    {
        String mail_1 = "verin.clement03gmail.com\n";
        String mail_2 = "verin.clement03@gmail.com\n";

        String simulatedOutput = mail_1 + mail_2;
        System.setIn(new ByteArrayInputStream(simulatedOutput.getBytes()));

        mail_Method.scanner = new Scanner(System.in);

        try
        {
            mail_Method.getInformation();
            assertFalse("Incorrect Mail has been accepted", mail_Method.isCorrectMail(mail_1));
        }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }

        try
        {
            assertTrue("Correct Mail has been rejected", mail_Method.isCorrectMail(mail_2));
        }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }
    }


    /**
     * Test the verifyInformation() method by submitting a simulated input (2 Assertions).
     */
    @Test public void testVerifyInformation()
    {
        mail_Method.scanner = new Scanner(System.in);

        try
        {
            mail_Method.verifyInformation();

            int code_1 = mail_Method.get_code_sent() - 1;
            simulatedOutput = "" + code_1;

            System.setIn(new ByteArrayInputStream((simulatedOutput + "\n").getBytes()));

            assertFalse("Incorrect Code has been accepted", mail_Method.isCorrectCode(code_1));
        }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }

        try
        {
            int code_2 = mail_Method.get_code_sent();
            simulatedOutput = "" + code_2;

            System.setIn(new ByteArrayInputStream((simulatedOutput + "\n").getBytes()));

            assertTrue("Correct Code has been rejected", mail_Method.isCorrectCode(code_2));
        }
        catch (Throwable e)
        { collector.addError(e); e.printStackTrace(); }
    }
    
    /**
     * Re-initialize original streams.
     */
    @After public void enablePrints()
    {
        System.setIn(originalStream_in);
        System.setOut(originalStream_out);
    }
}