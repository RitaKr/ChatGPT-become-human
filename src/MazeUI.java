import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class MazeUI extends JFrame {
    static MazeGame game;
    JPanel upperPanel = new JPanel(new BorderLayout());
    JLabel levelLabel = new JLabel();
    JPanel heartsPanel = new HeartsPanel();
    static JButton quitButton;
    static ImageIcon crossIcon = new ImageIcon("images/cross.png");
    static Color bg = new Color(45, 36, 58);
    static boolean lv1completed;
    static boolean lv2completed;
    static boolean lv3completed;

    public MazeGame getGame() {
        return game;
    }

    public void setGame(MazeGame game) {
        MazeUI.game = game;
    }

    public boolean isLv1completed() {
        return lv1completed;
    }

    public void setLv1completed(boolean lv1completed) {
        MazeUI.lv1completed = lv1completed;
    }

    public boolean isLv2completed() {
        return lv2completed;
    }

    public void setLv2completed(boolean lv2completed) {
        MazeUI.lv2completed = lv2completed;
    }

    public boolean isLv3completed() {
        return lv3completed;
    }

    public void setLv3completed(boolean lv3completed) {
        MazeUI.lv3completed = lv3completed;
    }

    static int menuHeight = 40;
    static boolean mazeCompleted = false;

    public boolean isMazeCompleted() {
        return mazeCompleted;
    }

    public void setMazeCompleted(boolean mazeCompleted) {
        MazeUI.mazeCompleted = mazeCompleted;
    }

    public MazeUI(){
        super("ChatGPT: become human");
        //System.out.println("Maze Game");
        game = new MazeGame(false, Main.getProgress().getLv());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        getContentPane().setLayout(new BorderLayout());
        setUpperPanel();
        add(upperPanel, BorderLayout.NORTH);

        add(game, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        //setVisible(true);

        // Register arrow key listeners to move the character
        game.addKeyListener(new ArrowKeyListener(game));
        game.requestFocus();


    }
    public MazeUI(int level){
        super("ChatGPT: become human");
        //System.out.println("Maze Game");
        game = new MazeGame(true, level);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        getContentPane().setLayout(new BorderLayout());
        setUpperPanel();
        add(upperPanel, BorderLayout.NORTH);

        add(game, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        //setVisible(true);

        // Register arrow key listeners to move the character
        game.addKeyListener(new ArrowKeyListener(game));
        game.requestFocus();


    }
    public void setUpperPanel(){
        upperPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
        upperPanel.setBackground(bg);
        upperPanel.setPreferredSize(new Dimension(game.getWidth(), menuHeight));
        upperPanel.setAlignmentX(CENTER_ALIGNMENT);
        upperPanel.setAlignmentY(CENTER_ALIGNMENT);

        levelLabel = new JLabel("Level "+game.getLevel());
        levelLabel.setForeground(Color.WHITE);

        heartsPanel.setBackground(null);

        quitButton = new JButton(crossIcon);
        quitButton.setBackground(null);

        quitButton.setPreferredSize(new Dimension(30, 30));
        quitButton.setMargin(null);
        quitButton.setBorder(null);
        quitButton.setBorderPainted(false);
        quitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
                requestFocus();
            }
        });


        upperPanel.add(levelLabel, BorderLayout.WEST);
        upperPanel.add(heartsPanel, BorderLayout.CENTER);
        upperPanel.add(quitButton, BorderLayout.EAST);

    }
    static void quit() {
        if (game.isMusicPlaying()) game.pauseMusic();
        //System.out.println(game.isMusicPlaying());
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to quit maze?","Quit maze", JOptionPane.YES_NO_OPTION);
        if (answer == 0) {
            Main.mazeUI.updateUpperPanel();
            if (Main.mazeUI.getGame().fromChooseMaze) Main.chooseMazeUI.setVisible(true);
            else Main.mainMenuUI.setVisible(true);
            SwingUtilities.invokeLater(()->Main.mazeUI.setVisible(false));
        } else {
            game.playMusic();

            game.requestFocus();
        }
    }
    public void updateUpperPanel() {
        levelLabel.setText("Level " + game.getLevel());
        levelLabel.updateUI();
        heartsPanel.repaint();
    }

    public void repaintHeartsPanel() {
        if (upperPanel != null) {
            heartsPanel.repaint();
        }
    }

    static class HeartsPanel extends JPanel {
        Image heart;
        int heartSize = 20;
        int distance = 10;

        int y;
        int x;
        public HeartsPanel(){
            loadImage("heart.png");

        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ////System.out.println("width "+super.getWidth()+", height "+super.getHeight());
            x = (super.getWidth()-(heartSize+distance)*3)/2;
            y = (super.getHeight()-heartSize)/2;
            if (MazeGame.chatGPT.getLives()>0) g.drawImage(heart, x,y, heartSize, heartSize, null);
            if (MazeGame.chatGPT.getLives()>1) g.drawImage(heart, x+heartSize+distance,y, heartSize, heartSize, null);
            if (MazeGame.chatGPT.getLives()>2) g.drawImage(heart, x+(heartSize+distance)*2,y, heartSize, heartSize, null);
        }
        private void loadImage(String imageName) {
            ImageIcon icon = new ImageIcon("images/"+imageName); // Replace with the path to your character image file
            heart = icon.getImage();
            ////System.out.println("w: "+width+", h:"+ height);
        }

    }

}

