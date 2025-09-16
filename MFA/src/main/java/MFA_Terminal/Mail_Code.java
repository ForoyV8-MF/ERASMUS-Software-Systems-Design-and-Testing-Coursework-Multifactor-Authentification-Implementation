/****************************************************************************/
/*  ADAPTED BY : Vérin Clément                              ISIMA (ZZ2, F1) */
/*  Supervised by : Daniele Scrimieri                   BradFord University */
/*  February / March 2025                                                   */
/*                                                                          */
/*                    CourseWork #1 MFA Implementation                      */
/*                                                                          */
/* Authentification_Method.java, SMS_Code.java, Mail_Code.java              */
/* Clients.java, Main.java, Color_Printer.java                              */
/* Authentification_Method_Test.java                                        */
/* Clients_Test.java                                                        */
/* SMS_Code_Test.java, Mail_Code_Test.java                                  */
/* Package MFA_Java                                                         */
/*                                                                          */
/* VS CODE                                                   javac, javadoc */
/****************************************************************************/

package MFA_Terminal;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This is the Mail Code Authentification Verification method, implementing the superior interface, pretty the same than SMS Code.
 * @author Vérin Clément
 * @version 1.0
 */
public class Mail_Code implements Authentification_Method
{
    private String mail;
    private int code_sent;
    private int code_entered;

    /**
     * Scanner used by both this class and his test phase.
     */
    public Scanner scanner;

    /**
     * A constructor just in order to prevent keeping empty variables.
     */
    public Mail_Code()
    {
        this.mail = "";
        this.code_sent = 100000;
        this.code_entered = 100000;
        scanner =  new Scanner(System.in);
    }

    /**
     * Verify is the mail  format is correct (not a really exigent criteria though, just verificating is an '@' is there)
     * @param mail the user's given mail.
     * @return boolean, true if correct, false if not.
     */
    public boolean isCorrectMail(String mail)
    {
        if(mail.contains("@"))
            return true;
        else
            return false;
    }

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
     * Ask for the user's mail, should begin with a '@'.
     * The function waits 1 second at the end, to simulate the sending delay of the code.
     */
    @Override
    public void getInformation()
    {
        System.out.println(Color_Printer.BOLD + Color_Printer.GREEN + "\n// MAIL Verification \\\\" + Color_Printer.RESET +" \n");
        System.out.print(Color_Printer.BOLD + "Please enter your mail in this format : XXX@YYY \n\n" + Color_Printer.RESET
        + Color_Printer.BOLD + "Your Mail : " + Color_Printer.RESET);

        do
        {
            mail = scanner.nextLine();

            if(!isCorrectMail(mail))
                System.out.print(Color_Printer.BOLD + Color_Printer.RED + "Mail format is incorrect. Please try again.\n" 
                + Color_Printer.RESET + Color_Printer.BOLD + "Your Mail : " + Color_Printer.RESET);

        } while(!isCorrectMail(mail));

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
        if(isCorrectMail(mail))
        {
            Random random = new Random();
            code_sent = random.nextInt(999999 - 100000 + 1) + 100000;
            
            System.out.print(Color_Printer.BOLD + "\nA code has been sent to " + Color_Printer.BLUE + mail + "\n" + Color_Printer.RESET
            + "\n(Let's just say you received this code : " + Color_Printer.BOLD + code_sent + Color_Printer.RESET + ")\n"
            + Color_Printer.BOLD + "\nPlease enter this code : ");

            do
            {
                if(!isCorrectCode(code_entered))
                {
                    if(scanner.hasNextInt())
                    {
                        code_entered = scanner.nextInt();

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
                    else
                    {
                        System.out.print(Color_Printer.RED + Color_Printer.BOLD + "Incorrect entry. Please try again." + Color_Printer.RESET
                        + "\nYour Choice : " + Color_Printer.RESET);
                        scanner.next();
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

    /**
     * code_sent getter for Testing phase.
     * @return the last code sent.
     */
    public int get_code_sent()
    {
        return code_sent;
    }
}