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

import java.awt.*;
import javax.swing.*;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main abstract class providing several method bodies for all different platform GUI
 * @author Vérin Clément
 * @version 1.0
 */
public abstract class Platform_GUI
{
    protected JFrame frame = new JFrame();

    protected JPanel panelTitle = new JPanel();
    protected JPanel panelUsername = new JPanel();
    protected JPanel panelPassword = new JPanel();
    protected JPanel panelMethod = new JPanel();
    protected JPanel panelMessage = new JPanel();
    protected JPanel panelMain = new JPanel();

    protected JButton buttonUsername = new JButton("Enter");
    protected JButton buttonPassword = new JButton("Enter");
    protected JButton buttonMethodMail = new JButton("Mail");
    protected JButton buttonMethodSMS = new JButton("Phone");
    protected JButton buttonExit = new JButton("Exit");

    protected JLabel resultLabelUsername;
    protected JLabel resultLabelPassword;

    protected JTextField usernameTextField = new JTextField(10);
    protected JTextField passwordTextField = new JTextField(10);

    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setResizable(false);
        frame.setLocation(1200,120);
        frame.setVisible(true);
    }

    protected void actionPerformedExit(ActionEvent e)
    {
        frame.setVisible(false); 
        System.exit(0);
    }

    /**
     * Write Title (Which Platform)
     */
    public abstract void renderTitle();

    protected void actionPerformedUsername(ActionEvent e)
    {
        if(!usernameTextField.getText().isEmpty())
        {
            try
            {
                Main.username = usernameTextField.getText();

                if(Clients.verifyUsername(Main.username))
                {
                    resultLabelUsername.setBackground(new Color(144, 238, 144));
                    resultLabelUsername.setText("Correct Username");
                    usernameTextField.setEditable(false);
                    buttonUsername.setEnabled(false);
                }
                else
                {
                    resultLabelUsername.setBackground(new Color(255, 150, 150));
                    resultLabelUsername.setText("Inexisting Username");
                    usernameTextField.setText("");
                }

                Main.latchMain.countDown();
            }                
            catch(NullPointerException d)
            {
                resultLabelUsername.setBackground(new Color(255, 150, 150));
                resultLabelUsername.setText("Invalid Entry");
                usernameTextField.setText("");
            }   
        }
        else
        {
            resultLabelPassword.setBackground(new Color(255, 150, 150));
            resultLabelPassword.setText("Field is Empty");
            passwordTextField.setText("");
        }
    }

    /**
     * Ask for Username.
     */
    public void renderUsername()
    {
        JLabel labelUsername = new JLabel("Username :");
        labelUsername.setOpaque(true);
        labelUsername.setBackground(new Color(220, 220, 220));
        labelUsername.setFont(new Font("Dialog", Font.BOLD, 15));
        labelUsername.setHorizontalAlignment(SwingConstants.CENTER);

        panelUsername.add(labelUsername);

        usernameTextField.addActionListener(e -> actionPerformedUsername(e));
        buttonUsername.addActionListener(e -> actionPerformedUsername(e));

        panelUsername.add(usernameTextField);
        panelUsername.add(buttonUsername);

        resultLabelUsername = new JLabel("");
        resultLabelUsername.setOpaque(true);
        resultLabelUsername.setBackground(new Color(144, 238, 144));
        resultLabelUsername.setFont(new Font("Dialog", Font.BOLD, 15));
        resultLabelUsername.setHorizontalAlignment(SwingConstants.CENTER);

        panelUsername.add(resultLabelUsername);

        panelMain.add(panelUsername);

        frame.getContentPane().add(panelMain);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    protected void actionPerformedPassword(ActionEvent e)
    {
        if(!passwordTextField.getText().isEmpty())
        {
            try
            {
                Main.password = passwordTextField.getText();

                if(Clients.verifyPassword(Main.username, Main.password))
                {
                    resultLabelPassword.setBackground(new Color(144, 238, 144));
                    resultLabelPassword.setText("Correct Password");
                    passwordTextField.setEditable(false);
                    buttonPassword.setEnabled(false);
                }
                else
                {
                    resultLabelPassword.setBackground(new Color(255, 150, 150));
                    resultLabelPassword.setText("Incorrect Password");
                    passwordTextField.setText("");
                }

                Main.latchMain.countDown();
            }
            catch(NullPointerException d)
            {
                resultLabelPassword.setBackground(new Color(255, 150, 150));
                resultLabelPassword.setText("Invalid Entry");
                passwordTextField.setText("");
            }
        }
        else
        {
            resultLabelPassword.setBackground(new Color(255, 150, 150));
            resultLabelPassword.setText("Field is Empty");
            passwordTextField.setText("");
        }
    }

    /**
     * Ask for password.
     */
    public void renderPassword()
    {
        JLabel labelPassword = new JLabel("Password :");
        labelPassword.setOpaque(true);
        labelPassword.setBackground(new Color(220, 220, 220));
        labelPassword.setFont(new Font("Dialog", Font.BOLD, 15));
        labelPassword.setHorizontalAlignment(SwingConstants.CENTER);

        panelPassword.add(labelPassword);

        passwordTextField.addActionListener(e -> actionPerformedPassword(e));
        buttonPassword.addActionListener(e -> actionPerformedPassword(e));

        panelPassword.add(passwordTextField);
        panelPassword.add(buttonPassword);

        resultLabelPassword = new JLabel("");
        resultLabelPassword.setOpaque(true);
        resultLabelPassword.setBackground(new Color(144, 238, 144));
        resultLabelPassword.setFont(new Font("Dialog", Font.BOLD, 15));
        resultLabelPassword.setHorizontalAlignment(SwingConstants.CENTER);

        panelPassword.add(resultLabelPassword);

        panelMain.add(panelPassword);

        frame.getContentPane().add(panelMain);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    protected void actionPerformedMail(ActionEvent e)
    {
        Main.methodNumber = 1;

        buttonMethodMail.setEnabled(false);
        buttonMethodSMS.setEnabled(false);
        Main.latchMain.countDown();
    }

    protected void actionPerformedSMS(ActionEvent e)
    {
        Main.methodNumber = 2;

        buttonMethodMail.setEnabled(false);
        buttonMethodSMS.setEnabled(false);
        Main.latchMain.countDown();
    }

    /**
     * Ask for Authentification Method.
     */
    public void renderMethod()
    {
        buttonMethodMail.addActionListener(e -> actionPerformedMail(e));
        buttonMethodSMS.addActionListener(e -> actionPerformedSMS(e));

        panelMethod.add(buttonMethodMail);
        panelMethod.add(buttonMethodSMS);

        panelMain.add(panelMethod);

        frame.getContentPane().add(panelMain);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    /**
     * Displays Success Login
     */
    public void renderSucess()
    {
        JLabel labelMessage = new JLabel("You have been Logged In !");
        labelMessage.setOpaque(true);
        labelMessage.setBackground(new Color(144, 238, 144));
        labelMessage.setFont(new Font("Dialog", Font.BOLD, 20));
        labelMessage.setHorizontalAlignment(SwingConstants.CENTER);

        panelMessage.add(labelMessage);

        panelMain.add(panelMessage);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    /**
     * Displays Failed Login
     */
    public void renderFail()
    {
        JLabel labelMessage = new JLabel("Fail to Login.");
        labelMessage.setOpaque(true);
        labelMessage.setBackground(new Color(255, 150, 150));
        labelMessage.setFont(new Font("Dialog", Font.BOLD, 20));
        labelMessage.setHorizontalAlignment(SwingConstants.CENTER);

        panelMessage.add(labelMessage);

        panelMain.add(panelMessage);

        frame.setVisible(false);
        frame.setVisible(true);
    }
}