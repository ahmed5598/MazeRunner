package com.company.Game.view;

import com.company.Game.Game.controller.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by FAST on 13-May-18.
 */
public class EndDialog {
    public JDialog getDialog() {
        return dialog;
    }

    private static final int ALPHA = 175; // how much see-thru. 0 to 255
    private static final Color GP_BG = new Color(255, 255, 255, ALPHA);
    JDialog dialog;
    StopWatch stopWatch;
    String reasonOD;
    View view;
    JPanel myDialogPanel;
    JButton restartBtn;
    JButton quitBtn;
    JLabel reasonOD_Lbl;

    public EndDialog(StopWatch stopWatch, String reasonOD, View view) {
        this.stopWatch = stopWatch;
        this.reasonOD = reasonOD;
        this.view = view;
        myDialogPanel = new JPanel(new GridLayout(3 , 1 , 10 , 10))
        {
            Image img = new ImageIcon("maxresdefault.jpg").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(img , 0 , 0 , null, this);
            }
        };
        JPanel lblPanel = new Panel();

        restartBtn = new JButton( "Restart");
        quitBtn = new JButton( "Quit");
        reasonOD_Lbl = new JLabel(reasonOD);

        restartBtn.addActionListener( e->restartBtnAction());
        quitBtn.addActionListener( e->quitBtnAction() );

        Font font = createFont();

        restartBtn.setFont(font.deriveFont(Font.BOLD , 20));
        reasonOD_Lbl.setFont(font.deriveFont(Font.BOLD , 20));
        lblPanel.setOpaque(false);
        quitBtn.setFont(font.deriveFont(Font.BOLD , 20));

        reasonOD_Lbl.setForeground(Color.BLUE);
        restartBtn.setForeground(Color.RED);
        quitBtn.setForeground(Color.GREEN);

        lblPanel.add(reasonOD_Lbl);
        myDialogPanel.add(lblPanel);
        myDialogPanel.add(restartBtn);
        myDialogPanel.add(quitBtn);

        myDialogPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    public void execute()
    {
        stopWatch.setPaused(true);
        JPanel glassPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // magic to make it dark without side-effects
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        glassPane.setOpaque(false);
        glassPane.setBackground(GP_BG);
        view.getFrame().setGlassPane(glassPane);
        glassPane.setVisible(true);

        dialog = new JDialog(view.getFrame() , JDialog.ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().add(myDialogPanel);  // add its JPanel to it
        dialog.setUndecorated(true); // give it no borders (if desired)
        dialog.pack(); // size it
        dialog.setLocationRelativeTo(view.getFrame()); // ** Center it over the JFrame **
        dialog.setVisible(true);  // display it, pausing the GUI below it

        // at this point the dialog is no longer visible, so get rid of glass pane
        glassPane.setVisible(false);
    }

    public void restartBtnAction()
    {
        stopWatch.setPaused(false);
        view.restartGame();
        dialog.dispose();
    }

    public void quitBtnAction()
    {
        stopWatch.setPaused(false);
        view.quitButtonAction();
        dialog.dispose();
    }

    public static Font createFont()
    {
        Font ttfBase = null;
        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("PressStart2P.ttf"));
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
        }
        catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return ttfBase;
    }
}
