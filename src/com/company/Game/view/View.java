package com.company.Game.view;

import com.company.Game.Game.controller.StopWatch;
import com.company.Game.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class View {
    private JFrame frame;
    private Font ttfBase;

    private StopWatch stopWatch;

    static final String START_VIEW = "s";
    static final String GAME_VIEW = "g";

    private Player player = Player.getInstance();

    private Box box;
    private JPanel cards;
    private JPanel startPanel;
    private JPanel mainPanel;
    private JPanel timePanel;
    private JPanel scorePanel;
    private JPanel bulletsPanel;
    private Panel gamePanel;

    private JButton startBtn;
    private JButton pauseBtn;
    private JButton saveBtn;
    private JButton loadBtn;

    private HealthBar healthBar;
    private JLabel timeLbl , lblScore;
    private JLabel numOfBulletsLbl;
    private JPanel infoPanel;
    private JPanel save_loadPanel;


    //private int score = 0;

    ImageIcon image;
    //private PausePanel pausePanel;
    public JLabel getTimeLbl() {
        return timeLbl;
    }

    public void initializeGUI()
    {
        loadImages();

        cards = new DecoratedPanel( new CardLayout() );
        frame = new JFrame();

        bulletsPanel = new JPanel();
        numOfBulletsLbl = new JLabel( new ImageIcon(Bullet.getBullet()));
        numOfBulletsLbl.setText(player.getNumOfBullets() + "");

        startPanel = new JPanel()
        {
            Image img = new ImageIcon("pikachu.gif").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(img , this.getWidth()/2 - img.getWidth(null)/2 , this.getHeight()/2 - img.getHeight(null)/2 + 75, null, this);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension( img.getWidth(null) , img.getHeight(null));
            }
        };
        startBtn = new JButton("Start");
        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");

        gamePanel = new Panel();

        timeLbl = new JLabel("2:00");
        timePanel = new JPanel();
        healthBar = HealthBar.getInstance();
        mainPanel = new JPanel();
        image = new ImageIcon(CoinsGift.getCoin());
        lblScore = new JLabel(image);
        infoPanel = new JPanel();
        box = Box.createVerticalBox();
        scorePanel = new JPanel();
        lblScore.setText(player.getScore() + "");
        pauseBtn = new JButton(new PauseAction("||"));
        save_loadPanel = new JPanel( new GridLayout(1 , 2));
    }

    public void setGuiComponentsProperties()
    {
        gamePanel.setMinimumSize(new Dimension(700 , 700));
        healthBar.setMaximumSize(new Dimension(Integer.MAX_VALUE , 35));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        timeLbl.setMaximumSize(new Dimension(Integer.MAX_VALUE , 30));
        box.setMaximumSize(new Dimension( 60, 30));
        box.setBorder(BorderFactory.createLineBorder(Color.black));
        box.setAlignmentX(Box.CENTER_ALIGNMENT);
        infoPanel.setOpaque(false);
        mainPanel.setOpaque(false);
        startPanel.setOpaque(false);
        startBtn.setFocusPainted(false);

        try {
            InputStream myStream = new BufferedInputStream(new FileInputStream("PressStart2P.ttf"));
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);

            timeLbl.setFont(ttfBase.deriveFont(Font.BOLD , 20));
            healthBar.getJpBar().setFont(ttfBase.deriveFont(Font.BOLD , 10));
            lblScore.setFont(ttfBase.deriveFont(Font.BOLD , 12));
            saveBtn.setFont(ttfBase.deriveFont(Font.BOLD , 12));
            pauseBtn.setFont(ttfBase.deriveFont(Font.BOLD , 12));
            loadBtn.setFont(ttfBase.deriveFont(Font.BOLD , 12));
            numOfBulletsLbl.setFont(ttfBase.deriveFont(Font.BOLD , 12));
            startBtn.setFont(ttfBase.deriveFont(Font.BOLD , 25));
        }
        catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public View() {

        initializeGUI();
        setGuiComponentsProperties();
        startBtn.addActionListener( e -> startButtonAction());
        saveBtn.addActionListener(e-> saveButtonAction());
        loadBtn.addActionListener(e-> loadButtonAction());


        bulletsPanel.add(numOfBulletsLbl);
        timePanel.add(timeLbl);
        timePanel.add(pauseBtn);
        infoPanel.add(box);
        save_loadPanel.add(saveBtn);
        save_loadPanel.add(loadBtn);
        box.add(healthBar);
        box.add(timePanel);

        startPanel.add(startBtn);
        scorePanel.add(lblScore);

        box.add(scorePanel);
        box.add(bulletsPanel);
        box.add(save_loadPanel);

        mainPanel.add(infoPanel , BorderLayout.EAST);
        mainPanel.add(gamePanel , BorderLayout.CENTER);

        cards.add(startPanel , START_VIEW);
        cards.add(mainPanel , GAME_VIEW);
        changeView( cards , START_VIEW);

        frame.setPreferredSize(new Dimension(810 , 657));
        frame.add(cards);

        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void startButtonAction()
    {
        restartGame();

        changeView(cards , GAME_VIEW);
        gamePanel.setFocusable(true);
    }

    public void quitButtonAction()
    {
        changeView(cards , START_VIEW);
        stopWatch.setPaused(true);
        Player player = Player.getInstance();
        player.setxMaze(1);
        player.setyMaze(1);
    }

    public void saveButtonAction()
    {
        loadBtn.setFocusable(false);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        Memento memento = player.getMemento();

        String state = "";
        if(player.getPlayerState() instanceof HasArmor)
            state = "hasArmor";
        else
            state = "noArmor";
        PrintWriter printToFile = createPrintWriter("file.txt");
            printToFile.println(memento + "," + state);
            for(int i = 0; i < 20 ; i++)
                for (int j = 0; j < 20; j++)
                    printToFile.printf("%d,",memento.getMaze()[i][j]);

        printToFile.close();

    }

    private PrintWriter createPrintWriter(String fileName) {
        File file = new File(fileName);
        PrintWriter infoToWrite = null;
        try {
            infoToWrite = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(file) ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return infoToWrite;
    }

    public void loadButtonAction()
    {
        gamePanel.requestFocusInWindow();
        try {

            File file = new File("file.txt");
            Scanner scanner = new Scanner(file);

            String lineFromFile = "";
            lineFromFile = scanner.nextLine();

            Originator originator = new Originator();
            String[] array = lineFromFile.split(",");

            originator.setHealth(Integer.parseInt(array[4]));
            originator.setScore(Integer.parseInt(array[2]));
            originator.setX(Integer.parseInt(array[0]));
            originator.setY(Integer.parseInt(array[1]));
            originator.setNumOfBullets(Integer.parseInt(array[3]));
            originator.setPlayerState(stateFactory(array[6]));
            originator.setTimeLeft(Integer.parseInt(array[5]));

            lineFromFile = scanner.nextLine();
            String[] mazeArray = lineFromFile.split(",");
            int[][] maze = new int[20][20];
            int index = 0;
            for(int i = 0 ; i< 20; i++)
                for (int j = 0 ; j< 20; j++) {
                    maze[i][j] = Integer.parseInt(mazeArray[index]);
                    index++;
                }

            Memento memento = originator.storeMemento();
            memento.setMaze(maze);
            HealthBar healthBar = HealthBar.getInstance();
            StopWatch.setTimeLeft(memento.getTimeLeft());
            healthBar.getJpBar().setValue(memento.getHealth());
            player.setMemento(memento);
            gamePanel.setMaze(memento.getMaze());

            healthBar.updateHealthBar();
            lblScore.setText(player.getScore() + "");
            updateNumberOfBulletsLbl();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static PlayerState stateFactory(String state)
    {
        Player player = Player.getInstance();
        if(state.equalsIgnoreCase("hasArmor"))
            return player.getHasArmor();

        else
            return player.getNoArmor();
    }

    public void changeView( JPanel cards , String view)
    {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, view);
    }

    public Panel getGamePanel() {
        return gamePanel;
    }

    public void editScore(int update)
    {
        player.setScore(player.getScore() + update);
        lblScore.setText(""+ player.getScore());
    }

    public void loadImages()
    {
        MyImages.loadImages();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException | IllegalArgumentException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }


    public void restartGame()
    {
        gamePanel.resetMaze();
        player.resetAttributes();
        healthBar.updateHealthBar();
        lblScore.setText(player.getScore() + "");
        stopWatch.reset();
        updateNumberOfBulletsLbl();
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public void setStopWatch(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
        ((PauseAction)pauseBtn.getAction()).setStopWatch(stopWatch);
        gamePanel.requestFocusInWindow();
    }

    /*public void setPausePanel(PausePanel pausePanel) {
        this.pausePanel = pausePanel;
    }*/

    public JFrame getFrame() {
        return frame;
    }

    public void updateNumberOfBulletsLbl()
    {
        numOfBulletsLbl.setText(player.getNumOfBullets() + "");
    }
}

