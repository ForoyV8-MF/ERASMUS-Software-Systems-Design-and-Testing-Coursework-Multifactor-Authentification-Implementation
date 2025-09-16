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
 * This class adapts the Title for Windows GUI.
 * @author Vérin Clément, Daniele Scrimieri
 */
public class Windows_GUI extends Platform_GUI
{
    /**
     * Adapting the Title for Windows GUI.
     */
    @Override
    public void renderTitle()
    {
        JLabel labelTitle = new JLabel("Windows");
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
}