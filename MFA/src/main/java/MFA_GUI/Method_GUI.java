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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main abstract class providing several method bodies for all authentification methods GUI
 * @author Vérin Clément
 * @version 1.0
 */
public abstract class Method_GUI
{
    protected JFrame frame = new JFrame();

    protected JPanel panelTitle = new JPanel();
    protected JPanel panelInformation = new JPanel();
    protected JPanel panelCode = new JPanel();
    protected JPanel panelCodeSent = new JPanel();
    protected JPanel panelMain = new JPanel();

    protected JButton buttonInformation = new JButton("Enter");
    protected JButton buttonCode = new JButton("Enter");
    protected JButton buttonExit = new JButton("Exit");

    protected JLabel resultLabelInformation;
    protected JLabel resultLabelCode;

    protected JTextField informationTextField = new JTextField(10);
    protected JTextField codeTextField = new JTextField(10);

    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(330, 300);
        frame.setResizable(false);
        frame.setLocation(1200,470);
        frame.setVisible(true);
    }

    protected void actionPerformedExit(ActionEvent e)
    {
        frame.setVisible(false); 
        System.exit(0);
    }

    /**
     * Write the Title.
     */
    public abstract void renderTitle();

    /**
     * Collect either Mail or Phone Number.
     * @param e Click or Enter key.
     */
    protected abstract void actionPerformedInformation(ActionEvent e);

    /**
     * Allow the user to fill the Textfield with Information.
     */
    public abstract void renderInformation();

    /**
     * Collect entered password.
     * @param e Click or Enter key.
     */
    protected void actionPerformedCode(ActionEvent e)
    {
        if(!codeTextField.getText().isEmpty())
        {
            try
            {
                Main.chosenMethod.setCodeEntered(Integer.parseInt(codeTextField.getText()));

                if(Main.chosenMethod.isCorrectCode(Main.chosenMethod.getCodeEntered()))
                {
                    resultLabelCode.setBackground(new Color(144, 238, 144));
                    resultLabelCode.setText("Correct Code");
                    codeTextField.setEditable(false);
                    buttonCode.setEnabled(false);
                }
                else
                {
                    resultLabelCode.setBackground(new Color(255, 150, 150));
                    resultLabelCode.setText("Incorrect Code");
                    codeTextField.setText("");
                }

                Main.chosenMethod.latchMethod.countDown();
            }
            catch(NumberFormatException d)
            {
                resultLabelCode.setBackground(new Color(255, 150, 150));
                resultLabelCode.setText("Invalid Entry");
                codeTextField.setText("");
            }
        }
    }

    /** Display the code sent and ask to enter It. */
    public void renderCode()
    {
        JLabel labelCode = new JLabel("Code :");
        labelCode.setOpaque(true);
        labelCode.setBackground(new Color(220, 220, 220));
        labelCode.setFont(new Font("Dialog", Font.BOLD, 15));
        labelCode.setHorizontalAlignment(SwingConstants.CENTER);

        panelCode.add(labelCode);

        codeTextField.addActionListener(e -> actionPerformedCode(e));
        buttonCode.addActionListener(e -> actionPerformedCode(e));

        panelCode.add(codeTextField);
        panelCode.add(buttonCode);

        resultLabelCode = new JLabel("");
        resultLabelCode.setOpaque(true);
        resultLabelCode.setBackground(new Color(144, 238, 144));
        resultLabelCode.setFont(new Font("Dialog", Font.BOLD, 15));
        resultLabelCode.setHorizontalAlignment(SwingConstants.CENTER);

        panelCode.add(resultLabelCode);

        panelMain.add(panelCode);

        frame.getContentPane().add(panelMain);

        frame.setVisible(false);
        frame.setVisible(true);
        
        JFrame frameCode = new JFrame();

        frameCode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCode.setSize(300, 100);
        frameCode.setResizable(false);
        frameCode.setLocation(880,470);
        frameCode.setVisible(true);
        
        JLabel labelCodeSent = new JLabel("Code : " + Main.chosenMethod.get_code_sent());
        labelCodeSent.setOpaque(true);
        labelCodeSent.setBackground(new Color(235, 233, 126));
        labelCodeSent.setFont(new Font("Dialog", Font.BOLD, 30));
        labelCodeSent.setHorizontalAlignment(SwingConstants.CENTER);

        panelCodeSent.add(labelCodeSent);

        frameCode.getContentPane().add(panelCodeSent);
    }
}