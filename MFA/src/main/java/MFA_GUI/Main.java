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

import java.util.Map;
import java.util.HashMap;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

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

    private static boolean isCorrectChoice = false;

    /**
     * Allow the Main method to choose which Authentification Method to use.
     */
    public static int methodNumber = 1;

    /**
     * The current Authentification Method selected.
     */
    public static Authentification_Method chosenMethod = null;

    private static boolean isChosenPlatform = false;
    private static Platform_GUI GUI;

    /**
     * The last username proposed by user.
     */
    public static String username;

    /**
     * The las password proposed by user.
     */
    public static String password;

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
     * A Latch waiting for Client's next entry from GUI.
     */
    public static CountDownLatch latchMain;
    
    /**
     * Main Method.
     * @param args main method parameter.
     */
    public static void main(String[] args)
    {
        System.out.println(Color_Printer.PURPLE + Color_Printer.BOLD + "\nWelcome to this awesome login interface !\n" + Color_Printer.RESET);
        System.out.print(Color_Printer.BOLD + "Which platform are you using ? \n" + Color_Printer.RESET
        + "Windows : 1\n"
        + "Linux : 2\n");

        do
        {
            System.out.print(Color_Printer.BOLD + "Enter your choice : " + Color_Printer.RESET);

            int choice = scanner.nextInt();

            if(choice == 1)
            {
                System.out.print(Color_Printer.PURPLE + Color_Printer.BOLD + "\n// Launching Windows \\\\\n\n" + Color_Printer.RESET);
                GUI = new Windows_GUI();
                isChosenPlatform = true;
            }
            else if(choice == 2)
            {
                System.out.print(Color_Printer.PURPLE + Color_Printer.BOLD + "\n// Launching Linux \\\\\n\n" + Color_Printer.RESET);
                GUI = new Linux_GUI();
                isChosenPlatform = true;
            }
            else
                System.out.print(Color_Printer.RED + Color_Printer.BOLD + "Incorrect Entry. Please try again.\n" + Color_Printer.RESET);

        } while(!isChosenPlatform);

        GUI.renderTitle();
        GUI.renderUsername();

        int tries = 3;

        do
        {
            System.out.print(Color_Printer.BOLD + "Enter your username : " + Color_Printer.RESET);

            try
            {
                latchMain = new CountDownLatch(1);
                latchMain.await();
            }
            catch(InterruptedException e) { System.out.print("Interrupted Exception"); }

            if(Clients.verifyUsername(Main.username))
            {
                System.out.print(Color_Printer.GREEN + Color_Printer.BOLD + "Username is correct.\n" + Color_Printer.RESET);

                GUI.renderPassword();

                do
                {
                    System.out.print(Color_Printer.BOLD + "Enter password : " + Color_Printer.RESET);

                    try
                    {
                        latchMain = new CountDownLatch(1);
                        latchMain.await();
                    }
                    catch(InterruptedException e) { System.out.print("Interrupted Exception"); }

                    if(Clients.verifyPassword(Main.username, Main.password))
                        System.out.print(Color_Printer.BOLD + Color_Printer.GREEN + "Password is correct.\n");
                    else
                    {
                        Main.numberOfTries--;

                        if(Main.numberOfTries != 0)
                            System.out.print(Color_Printer.RED + Color_Printer.BOLD + "Incorrect Password. Please try again.\n" + Color_Printer.RESET + Color_Printer.BOLD + "\n(Remaining Trials : " + Main.numberOfTries + ")\n" + Color_Printer.RESET);
                    }

                } while(!Clients.verifyPassword(Main.username, Main.password) && Main.numberOfTries > 0);
            }
            else
                System.out.print(Color_Printer.RED + Color_Printer.BOLD + "Incorrect Username. Please try again.\n" + Color_Printer.RESET);
    
        } while(!Clients.verifyUsername(Main.username));

        if(Clients.verifyUsername(Main.username) && Clients.verifyPassword(Main.username, Main.password) && Main.numberOfTries > 0)
        {   
            // This is the Print you have to update if a new Authentification Method is needed.
            System.out.print(Color_Printer.BOLD + "\nPlease choose your second authentification method by typing the corresponding number.\n" + Color_Printer.RESET + Color_Printer.BOLD 
                + "Code Mail : 1\n"
                + "Code Message : 2\n"
                + Color_Printer.BOLD + "\nYour Choice : " + Color_Printer.RESET);

            Main.numberOfTries = 3;

            GUI.renderMethod();

            do
            {
                try
                {
                    latchMain = new CountDownLatch(1);
                    latchMain.await();
                }
                catch(InterruptedException e) { System.out.print("Interrupted Exception"); }

                switch(methodNumber)
                {
                    case 1 :
                    {
                        System.out.println(1);
                        chosenMethod = new Mail_Code();
                        isCorrectChoice = true;
                        break;
                    }

                    case 2 :
                    {
                        System.out.println(2);
                        chosenMethod = new SMS_Code();
                        isCorrectChoice = true;
                        break;
                    }

                    default :
                        System.out.print(Color_Printer.RED + Color_Printer.BOLD + "Incorrect choice. Please try again." + Color_Printer.RESET
                        + "\nYour Choice : " + Color_Printer.RESET);
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
        {
            System.out.println(Color_Printer.BOLD + Color_Printer.GREEN + "You have been logged in !\n" + Color_Printer.RESET);
            GUI.renderSucess();
        }
        else
        {
            System.out.println(Color_Printer.BOLD + Color_Printer.RED + "Login Failed !\n" + Color_Printer.RESET);
            GUI.renderFail();
        }
    }
}