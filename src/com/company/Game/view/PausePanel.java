package com.company.Game.view;

import com.company.Game.Game.controller.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("serial")
public class PausePanel extends JPanel {
    private static final Image img = new ImageIcon("maxresdefault.jpg").getImage();
    private static Font font = createFont();
    JDialog dialog;
    private static final PausePanel instance = new PausePanel();
    private StopWatch stopWatch;
    private View view;

    private PausePanel() {

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Box box = Box.createVerticalBox();

        JButton resumeBtn = new JButton("RESUME");
        JButton restartBtn = new JButton( "Restart");
        JButton quitBtn = new JButton( "Quit");

        resumeBtn.addActionListener( e->resumeBtnAction());
        restartBtn.addActionListener( e->restartBtnAction());
        quitBtn.addActionListener( e->quitBtnAction() );

        restartBtn.setFont(font.deriveFont(Font.BOLD , 20));
        resumeBtn.setFont(font.deriveFont(Font.BOLD , 20));
        quitBtn.setFont(font.deriveFont(Font.BOLD , 20));

        resumeBtn.setForeground(Color.BLUE);
        restartBtn.setForeground(Color.RED);
        quitBtn.setForeground(Color.GREEN);

        JPanel resumePanel = new JPanel();
        resumePanel.setOpaque(false);
        resumePanel.add(resumeBtn);

        JPanel restartPanel = new JPanel();
        restartPanel.setOpaque(false);
        restartPanel.add(restartBtn);

        JPanel quitPanel = new JPanel();
        quitPanel.setOpaque(false);
        quitPanel.add(quitBtn);

        Dimension dim = new Dimension(190 , 30);
        resumeBtn.setPreferredSize(dim);
        restartBtn.setPreferredSize(dim);
        quitBtn.setPreferredSize(dim);

        box.add(resumePanel);
        box.add(restartPanel);
        box.add(quitPanel);

        resumeBtn.setFocusable(false);
        restartBtn.setFocusable(false);
        quitBtn.setFocusable(false);

        add(box);
    }

    public void resumeBtnAction()
    {
        view.getGamePanel().requestFocusInWindow();
        stopWatch.setPaused(false);
        dialog.dispose();
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

    public void setStopWatch(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    public static PausePanel getInstance() {
        return instance;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setDialog(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img , 0 , 0 , null, this);
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
