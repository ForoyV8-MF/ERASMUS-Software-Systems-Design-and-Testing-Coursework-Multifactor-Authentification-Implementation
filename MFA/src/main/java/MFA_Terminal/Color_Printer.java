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
 * Just a class containing some UTF8 terminal colors, provided by my school ISIMA.
 * @author Vérin Clément, Loïc Yon (ISIMA teacher)
 * @version 1.0 
 */
public class Color_Printer
{
    /** Reset */
    public static final String RESET = "\u001B[0m";

    /** Red */
    public static final String RED = "\u001B[31m";
    
    /** Green */
    public static final String GREEN = "\u001B[32m";
    
    /** Yellow */
    public static final String YELLOW = "\u001B[33m";
    
    /** Blue */
    public static final String BLUE = "\u001B[34m";
    
    /** Purple */
    public static final String PURPLE = "\u001B[35m";
    
    /** Cyan */
    public static final String CYAN = "\u001B[36m";

    /** Black Background */
    public static final String BG_BLACK = "\u001B[40m";
    
    /** Red Background */
    public static final String BG_RED = "\u001B[41m";
    
    /** Green Background */
    public static final String BG_GREEN = "\u001B[42m";
    
    /** Yellow Background */
    public static final String BG_YELLOW = "\u001B[43m";
    
    /** Blue Background */
    public static final String BG_BLUE = "\u001B[44m";
    
    /** Purple Background */
    public static final String BG_PURPLE = "\u001B[45m";
    
    /** Cyan Background */
    public static final String BG_CYAN = "\u001B[46m";
    
    /** White Background */
    public static final String BG_WHITE = "\u001B[47m";

    /** Bold */
    public static final String BOLD = "\u001B[1m";
    
    /** Underline */
    public static final String UNDERLINE = "\u001B[4m";
    
    /** Blink */
    public static final String BLINK = "\u001B[5m";
    
    /** Reverse */
    public static final String REVERSE = "\u001B[7m";

}
