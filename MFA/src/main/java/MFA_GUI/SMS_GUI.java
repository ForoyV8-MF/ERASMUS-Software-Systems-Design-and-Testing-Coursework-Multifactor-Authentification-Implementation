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
 * The GUI Interface for SMS Code Verification.
 */
public class SMS_GUI extends Method_GUI
{
    /**
     * Write the Title
     */
    @Override
    public void renderTitle()
    {
        JLabel labelTitle = new JLabel("SMS Code");
        labelTitle.setOpaque(true);
        labelTitle.setBackground(new Color(235, 233, 126));
        labelTitle.setFont(new Font("Dialog", Font.BOLD, 25));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        panelTitle.add(labelTitle);
        panelMain.add(panelTitle);

        frame.getContentPane().add(panelMain);

        buttonExit.addActionListener(e -> actionPerformedExit(e));
        frame.add(buttonExit, BorderLayout.SOUTH);

        panelMain.setLayout(new BoxLayout(panelMain,BoxLayout.Y_AXIS));
    }

    /**
     * Collect either Mail or Phone Number.
     * @param e Click or Enter key.
     */
    @Override
    protected void actionPerformedInformation(ActionEvent e)
    {
        if(!informationTextField.getText().isEmpty())
        {
            try
            {
                Main.chosenMethod.setInformation(informationTextField.getText());

                if(Main.chosenMethod.isCorrectInformation(Main.chosenMethod.getParameter()))
                {
                    resultLabelInformation.setBackground(new Color(144, 238, 144));
                    resultLabelInformation.setText("Correct Phone Number");
                    informationTextField.setEditable(false);
                    buttonInformation.setEnabled(false);
                }
                else
                {
                    resultLabelInformation.setBackground(new Color(255, 150, 150));
                    resultLabelInformation.setText("Incorrect Phone Number");
                    informationTextField.setText("");
                }

                Main.chosenMethod.latchMethod.countDown();
            }                
            catch(NullPointerException d)
            {
                resultLabelInformation.setBackground(new Color(255, 150, 150));
                resultLabelInformation.setText("Invalid Entry");
                informationTextField.setText("");
            }
        }
        else
        {
            resultLabelInformation.setBackground(new Color(255, 150, 150));
            resultLabelInformation.setText("Field is Empty");
            informationTextField.setText("");
        }
    }

    /**
     * Ask for Phone Number.
     */
    @Override
    public void renderInformation()
    {
        JLabel labelInformation = new JLabel("Number :");
        labelInformation.setOpaque(true);
        labelInformation.setBackground(new Color(220, 220, 220));
        labelInformation.setFont(new Font("Dialog", Font.BOLD, 15));
        labelInformation.setHorizontalAlignment(SwingConstants.CENTER);

        panelInformation.add(labelInformation);

        informationTextField.addActionListener(e -> actionPerformedInformation(e));
        buttonInformation.addActionListener(e -> actionPerformedInformation(e));

        panelInformation.add(informationTextField);
        panelInformation.add(buttonInformation);

        resultLabelInformation = new JLabel("");
        resultLabelInformation.setOpaque(true);
        resultLabelInformation.setBackground(new Color(144, 238, 144));
        resultLabelInformation.setFont(new Font("Dialog", Font.BOLD, 15));
        resultLabelInformation.setHorizontalAlignment(SwingConstants.CENTER);

        panelInformation.add(resultLabelInformation);

        panelMain.add(panelInformation);

        frame.getContentPane().add(panelMain);

        frame.setVisible(false);
        frame.setVisible(true);
    }
}