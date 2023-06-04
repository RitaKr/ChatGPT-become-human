import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeUI extends JFrame {
    static MazeGame game;
    static JPanel upperPanel = new JPanel(new BorderLayout());
    static JLabel levelLabel = new JLabel();
    static JPanel heartsPanel = new HeartsPanel();
    static JButton quitButton;
    static ImageIcon crossIcon = new ImageIcon("images/cross.png");
    static Color bg = new Color(45, 36, 58);

    private static int menuHeight = 40;


    public MazeUI(int level){

        System.out.println("Maze Game");
        game = new MazeGame(level);

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
                if (game.isMusicPlaying()) game.pauseMusic();
                System.out.println(game.isMusicPlaying());
                int answer = JOptionPane.showConfirmDialog(null, "Do you want to quit maze?","Quit maze", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {

                    dispose();
                } else {
                    game.playMusic();

                    game.requestFocus();
                }

            }
        });

        upperPanel.add(levelLabel, BorderLayout.WEST);
        upperPanel.add(heartsPanel, BorderLayout.CENTER);
        upperPanel.add(quitButton, BorderLayout.EAST);

    }
    public static void updateUpperPanel() {
        levelLabel.setText("Level " + game.getLevel());
        heartsPanel.repaint();
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
            //System.out.println("width "+super.getWidth()+", height "+super.getHeight());
            x = (super.getWidth()-(heartSize+distance)*3)/2;
            y = (super.getHeight()-heartSize)/2;
            if (MazeGame.chatGPT.getLives()>0) g.drawImage(heart, x,y, heartSize, heartSize, null);
            if (MazeGame.chatGPT.getLives()>1) g.drawImage(heart, x+heartSize+distance,y, heartSize, heartSize, null);
            if (MazeGame.chatGPT.getLives()>2) g.drawImage(heart, x+(heartSize+distance)*2,y, heartSize, heartSize, null);
        }
        private void loadImage(String imageName) {
            ImageIcon icon = new ImageIcon("images/"+imageName); // Replace with the path to your character image file
            heart = icon.getImage();
            //System.out.println("w: "+width+", h:"+ height);
        }
        static void repaintHeartsPanel() {
            if (upperPanel != null) {
                heartsPanel.repaint();
            }
        }
    }

}

