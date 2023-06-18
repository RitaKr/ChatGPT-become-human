import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MazeUI extends UI {
    static MazeGame game;
//    JPanel upperPanel = new JPanel(new BorderLayout());
//    JLabel levelLabel = new JLabel();
    JPanel heartsPanel = new HeartsPanel();
//    static JButton quitButton;
//    static Image crossImage = new ImageIcon("images/x.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//    static Image crossHoverImage = new ImageIcon("images/x-hover.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

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

    static boolean mazeCompleted = false;

    public boolean isMazeCompleted() {
        return mazeCompleted;
    }

    public void setMazeCompleted(boolean mazeCompleted) {
        MazeUI.mazeCompleted = mazeCompleted;
    }

    public MazeUI(){
        super("ChatGPT: become human", false);
        //System.out.println("Maze Game");
        game = new MazeGame(false, Main.getProgress().getLv());
        setUI();

    }
    public MazeUI(int level){
        super("ChatGPT: become human", false);
        //System.out.println("Maze Game");
        game = new MazeGame(true, level);
        setUI();

    }

    private void setUI() {
        setResizable(false);

        getContentPane().setLayout(new BorderLayout());
        setUpperPanel(this);
        heartsPanel.setBackground(null);
        super.upperPanel.add(heartsPanel, BorderLayout.CENTER);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmQuit();
                game.requestFocus();
            }
        });

        add(super.upperPanel, BorderLayout.NORTH);

        add(game, BorderLayout.CENTER);
        pack();


        // Register arrow key listeners to move the character
        game.addKeyListener(new ArrowKeyListener(game));
        game.requestFocus();
    }

    void confirmQuit() {
        if (game.isMusicPlaying()) game.pauseMusic();
        //System.out.println(game.isMusicPlaying());
        //int answer = JOptionPane.showConfirmDialog(null, "Do you want to quit maze?","Quit maze", JOptionPane.YES_NO_OPTION);

        MessageWindow messageWindow = new MessageWindow(MazeUI.game, "Do you really want to quit maze?", "Quit maze", "Yes", "No");
        //setScene(level);
        messageWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (messageWindow.isYes()) {
                    Main.mazeUI.updateUpperPanel();
                    if (Main.mazeUI.getGame().fromChooseMaze) {
                        quit(Main.chooseMazeUI);
                    }
                    else {
                        quit(Main.mainMenuUI);
                    }
                    Main.playMusic();
                    //SwingUtilities.invokeLater(()->{Main.mazeUI.setVisible(false); Main.playMusic();});
                } else {

                    game.playMusic();
                    game.requestFocus();
                }
            }
        });
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

