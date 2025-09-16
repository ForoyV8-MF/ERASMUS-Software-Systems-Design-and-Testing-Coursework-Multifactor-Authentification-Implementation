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
import java.util.Scanner;

/**
 * Main class, bringing a main method.
 * Ask for user's username and password, and proceeds to the 2nd authentification factor, by asking which one is chosen.
 */
public class Main
{
    /**
     * Scanner used by both this class and his test phase.
     */
    public static Scanner scanner = new Scanner(System.in);

    private static int methodNumber = 1;
    private static boolean isCorrectChoice = false;

    private static Authentification_Method chosenMethod = null;

    private static String username;
    private static String password;

    /**
     * Used by an Authentification Method in order to make know to the Main Class if login is a success.
     */
    public static boolean isLoggedIn = false;

    /**
     * Number of code/password enterring tries before failure.
     * Re-initializes between these two.
     */
    public static int numberOfTries = 3;

    /**
     * Main Method.
     * @param args main method argument.
     */
    public static void main(String[] args)
    {
        System.out.println(Color_Printer.PURPLE + Color_Printer.BOLD + "\nWelcome to this awesome login interface !\n" + Color_Printer.RESET);

        int tries = 3;
        do
        {
            System.out.print(Color_Printer.BOLD + "Enter your username : " + Color_Printer.RESET);

            username = scanner.nextLine();

            if(Clients.verifyUsername(username))
            {
                System.out.print(Color_Printer.GREEN + Color_Printer.BOLD + "Username is correct.\n" + Color_Printer.RESET);

                do
                {
                    System.out.print(Color_Printer.BOLD + "Enter password : " + Color_Printer.RESET);

                    password = scanner.nextLine();

                    if(Clients.verifyPassword(username, password))
                        System.out.print(Color_Printer.BOLD + Color_Printer.GREEN + "Password is correct.\n");
                    else
                    {
                        Main.numberOfTries--;

                        if(Main.numberOfTries != 0)
                            System.out.print(Color_Printer.RED + Color_Printer.BOLD + "Incorrect Password. Please try again.\n" + Color_Printer.RESET + Color_Printer.BOLD + "\n(Remaining Trials : " + Main.numberOfTries + ")\n" + Color_Printer.RESET);
                    }


                } while(!Clients.verifyPassword(username, password) && Main.numberOfTries > 0);
            }
            else
                System.out.print(Color_Printer.RED + Color_Printer.BOLD + "Incorrect Username. Please try again.\n" + Color_Printer.RESET);
    
        } while(!Clients.verifyUsername(username));

        if(Clients.verifyUsername(username) && Clients.verifyPassword(username, password) && Main.numberOfTries > 0)
        {   
            // This is the Print you have to update if a new Authentification Method is needed.
            System.out.print(Color_Printer.BOLD + "\nPlease choose your second authentification method by typing the corresponding number.\n" + Color_Printer.RESET + Color_Printer.BOLD 
                + "Code Mail : 1\n"
                + "Code Message : 2\n"
                + Color_Printer.BOLD + "\nYour Choice : " + Color_Printer.RESET);

            Main.numberOfTries = 3;

            do
            {
                if(scanner.hasNextInt())
                {
                    methodNumber = scanner.nextInt();
     
                    switch(methodNumber)
                    {
                        case 1 :
                        {
                            chosenMethod = new Mail_Code();
                            isCorrectChoice = true;
                            break;
                        }

                        case 2 :
                        {
                            chosenMethod = new SMS_Code();
                            isCorrectChoice = true;
                            break;
                        }

                        default :
                            System.out.print(Color_Printer.RED + Color_Printer.BOLD + "Incorrect choice. Please try again." + Color_Printer.RESET
                            + "\nYour Choice : " + Color_Printer.RESET);
                    }
                }
                else
                {
                    System.out.print(Color_Printer.RED + Color_Printer.BOLD + "Incorrect choice. Please try again." + Color_Printer.RESET
                    + "\nYour Choice : " + Color_Printer.RESET);
                    scanner.next();
                }

            } while(!isCorrectChoice && tries > 0);

            chosenMethod.getInformation();
            chosenMethod.verifyInformation();
        }
        else
        {
            Main.isLoggedIn = false;
            System.out.println(Color_Printer.RED + Color_Printer.BOLD + "\nPassword has been incorrect too many times.\n");                
        }

        if(Main.isLoggedIn)
            System.out.println(Color_Printer.BOLD + Color_Printer.GREEN + "You have been logged in !\n" + Color_Printer.RESET);
        else
            System.out.println(Color_Printer.BOLD + Color_Printer.RED + "Login Failed !\n" + Color_Printer.RESET);
    }
}