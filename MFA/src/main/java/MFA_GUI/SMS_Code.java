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
 * This is the SMS Code Verification authentification method, implementing the superior interface,  pretty the same than Mail Code.
 * @author Vérin Clément
 * @version 1.0
 */
public class SMS_Code extends Authentification_Method
{
    Method_GUI GUI = new SMS_GUI();

    /**
     * Verify is the phone number format is correct (not a really exigent criteria though, just looking if it begins with a '+')
     * @param num the user's given phone number.
     * @return boolean, true if correct, false if not.
     */
    @Override
    public boolean isCorrectInformation(String num)
    {
        if(num.contains("+"))
            return true;
        else
            return false;
    }

    /**
     * Ask for the user's phone number, should begin with a '+'.
     * The function waits 1 second at the end, to simulate the sending delay of the code.
     */
    @Override
    public void getInformation()
    {
        System.out.println(Color_Printer.BOLD + Color_Printer.GREEN + "\n// SMS Verification \\\\" + Color_Printer.RESET +" \n");
        System.out.print(Color_Printer.BOLD + "Please enter your Phone Number in this format : +XXYYYYYY... \n\n" + Color_Printer.RESET
        + Color_Printer.BOLD + "Your Phone Number : " + Color_Printer.RESET);

        GUI.renderTitle();
        GUI.renderInformation();

        do
        {
            try
            {
                latchMethod = new CountDownLatch(1);
                latchMethod.await();
            }
            catch(InterruptedException e) { System.out.print("Interrupted Exception"); }

            if(!isCorrectInformation(information))
                System.out.print(Color_Printer.BOLD + Color_Printer.RED + "Phone Number format is incorrect. Please try again.\n" 
                + Color_Printer.RESET + Color_Printer.BOLD + "Your Phone Number : " + Color_Printer.RESET);

        } while(!isCorrectInformation(information));

        try{TimeUnit.SECONDS.sleep(1);}
        catch(Throwable e) {e.printStackTrace();}
    }

    /**
     * Ask for the user's received code, only between 100 000 and 999 999.
     * The function waits 1 second at the end, to simulate the verification delay of the code.
     */
    @Override
    public void verifyInformation()
    {    
        if(isCorrectInformation(information))
        {
            Random random = new Random();
            code_sent = random.nextInt(999999 - 100000 + 1) + 100000;
            
            System.out.print(Color_Printer.BOLD + "\nA code has been sent to " + Color_Printer.BLUE + information + "\n" + Color_Printer.RESET
            + "\n(Let's just say you received this code : " + Color_Printer.BOLD + code_sent + Color_Printer.RESET + ")\n"
            + Color_Printer.BOLD + "\nPlease enter this code : ");

            GUI.renderCode();

            do
            {
                if(!isCorrectCode(code_entered))
                {
                    try
                    {
                        latchMethod = new CountDownLatch(1);
                        latchMethod.await();
                    }
                    catch(InterruptedException e) { System.out.print("Interrupted Exception"); }

                    if(code_entered >= 100000 && code_entered <= 999999)
                    {
                        Main.numberOfTries--;

                        if(!isCorrectCode(code_entered) && Main.numberOfTries != 0)
                        {
                            System.out.print(Color_Printer.BOLD + Color_Printer.RED + "\nCode is incorrect. Please try again.\n" + Color_Printer.RESET
                            + Color_Printer.BOLD + "\n(Remaining Trials : " + Main.numberOfTries + ")"
                            + Color_Printer.RESET + Color_Printer.BOLD + "\nEnter received code : " + Color_Printer.RESET);                        
                        }
                    }
                    else
                    {
                        System.out.print(Color_Printer.RED + Color_Printer.BOLD + "Incorrect entry. Please try again." + Color_Printer.RESET
                        + "\nYour Choice : " + Color_Printer.RESET);
                    }
                }

            } while(Main.numberOfTries > 0 && !isCorrectCode(code_entered));

            try{TimeUnit.SECONDS.sleep(1);}
            catch(Throwable e) {e.printStackTrace();}

            if(isCorrectCode(code_entered) && Main.numberOfTries >= 0)
            {
                Main.isLoggedIn = true;
                System.out.println(Color_Printer.GREEN + Color_Printer.BOLD + "\nCode is correct. Thanks for your cooperation !\n");
            }
            else
            {
                Main.isLoggedIn = false;
                System.out.println(Color_Printer.RED + Color_Printer.BOLD + "\nCode has been incorrect too many times.\n");                
            }
        }
    }
}