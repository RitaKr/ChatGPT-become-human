import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChooseMazeUI extends JFrame {
    static JPanel upperPanel = new JPanel(new BorderLayout());
    static JLabel levelLabel = new JLabel();
    static JButton quitButton;
    static ImageIcon crossIcon = new ImageIcon("images/cross.png");
    static Color upperPanelBg = new Color(45, 36, 58);
    private JPanel backgroundPanel;
    private JPanel buttonsPanel;
    Image backgroundImage;
    final static Color bgColor = new Color(138, 19, 178);
    final static Color transparent = new Color(0,0,0, 0.00f);
    ProgressData progressData;
    GridBagConstraints c = new GridBagConstraints();
    public void updateProgressData(){
        Main.fetchProgress();
        progressData = Main.getProgress();
        buttonsPanel.removeAll();
        setButtons();
        levelLabel.setText("Current level: "+progressData.getLv());
        levelLabel.updateUI();
    }

    public void setAll(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.progressData = progressData;

        setSize(890, 710);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        loadBackgroundImage("bg-menu.png");
        setUpperPanel();
        backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };

        backgroundPanel.setPreferredSize(new Dimension(890, 690));


        // create and customize the label
        JLabel title = new JLabel("Choose maze to play", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setForeground(Color.WHITE);

        c.gridx = 0;
        c.gridy = 0;
        backgroundPanel.add(title, c);

        // create and customize the buttons
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setBackground(transparent);
        setButtons();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        backgroundPanel.add(buttonsPanel, c);


        add(upperPanel, BorderLayout.NORTH);
        add(backgroundPanel, BorderLayout.CENTER);

        setFocusable(true);
        requestFocusInWindow();
        super.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE: {
                        //System.out.println("quit");
                        quit();
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    private void setButtons(){
        String[] buttonImages = {(progressData.getLv()>=1 ? "lv1.png" : "lv1no.png"), (progressData.getLv()>=2 ? "lv2.png" : "lv2no.png"), (progressData.getLv()>=3 ? "lv3.png" : "lv3no.png")};
        for (int i = 0; i < buttonImages.length; i++) {
            JPanel buttonContainer = new JPanel(new BorderLayout());


            JLabel buttonLabel = new JLabel("Level " + (i+1), SwingConstants.CENTER);
            buttonLabel.setFont(new Font("Arial", Font.BOLD, 24));
            buttonLabel.setForeground(Color.WHITE);
            buttonLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

            JButton button = new JButton(new ImageIcon("images/"+buttonImages[i]));
            button.setBackground(null);
            button.setBorderPainted(false);
            button.setBorder(null);
            button.setMargin(new Insets(0,0,0,0));

            button.setCursor(progressData.getLv()>i ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) : Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            c.gridx = i + 1;
            c.fill = GridBagConstraints.VERTICAL;
            c.insets = new Insets(20,0,20,0);  // Add some space between the buttons
            buttonContainer.add(button, BorderLayout.CENTER);
            buttonContainer.add(buttonLabel, BorderLayout.SOUTH);
            buttonContainer.setBorder(new EmptyBorder(10, 10, 10, 10));

            buttonContainer.setBackground(transparent);
            buttonsPanel.add(buttonContainer, c);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (finalI){
                        case 0: {
                            if (progressData.getLv()>=1) {

                                Main.startMazeGame(1);
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                JOptionPane.showMessageDialog(ChooseMazeUI.super.getContentPane(), "You haven't unlocked this level yet! Try too chat with GPT more", "level unavailable", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        }
                        case 1: {
                            if (progressData.getLv()>=2) {
                                Main.startMazeGame(2);
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                JOptionPane.showMessageDialog(ChooseMazeUI.super.getContentPane(), "You haven't unlocked this level yet! You need to complete level 1 and chat chapter 2 first", "level unavailable", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        }
                        case 2: {
                            if (progressData.getLv()>=3) {
                                Main.startMazeGame(3);
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                JOptionPane.showMessageDialog(ChooseMazeUI.super.getContentPane(), "You haven't unlocked this level yet! You need to complete level 2 and chat chapter 3 first", "level unavailable", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        }
                    }

                    requestFocusInWindow();
                }
            });

        }
    }
    public ChooseMazeUI() {
        super("ChatGPT: become human");
        progressData = Main.getProgress();
        setAll();
        // Make JFrame visible
        //setVisible(true);
    }
    public void setUpperPanel(){
        upperPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
        upperPanel.setBackground(upperPanelBg);
        upperPanel.setPreferredSize(new Dimension(MazeGame.getMazeWidth(), MazeUI.menuHeight));
        upperPanel.setAlignmentX(CENTER_ALIGNMENT);
        upperPanel.setAlignmentY(CENTER_ALIGNMENT);

        levelLabel = new JLabel("Current level: "+progressData.getLv());
        levelLabel.setForeground(Color.WHITE);


        quitButton = setIconButton(crossIcon, 30, 0);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
                ChooseMazeUI.super.requestFocus();
            }
        });

        upperPanel.add(levelLabel, BorderLayout.WEST);
        upperPanel.add(quitButton, BorderLayout.EAST);

    }
    private JButton setIconButton(ImageIcon icon,int size, int padding) {
        JButton button = new JButton(icon);
        button.setBackground(null);

        button.setPreferredSize(new Dimension(size, size));
        button.setMargin(new Insets(padding, padding, padding, padding));
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
    private void quit(){
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to go back to the main menu?","Quit maze selection", JOptionPane.YES_NO_OPTION);
        if (answer == 0) {
            //mainMenuUI = new MainMenuUI(Main.getProgress());
            Main.mainMenuUI.setVisible(true);
            SwingUtilities.invokeLater(this::dispose);
        }
    }
    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        //System.out.println("x "+getWidth() + ", y" + getHeight() );
    }

    private void loadBackgroundImage(String imageName) {
        try {
            ImageIcon icon = new ImageIcon("images/"+imageName); // Replace with the path to your character image file
            backgroundImage = icon.getImage();
            if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                //System.out.println("Failed to load image: " + imageName);
                backgroundImage = null;
            }
        } catch (Exception e) {
            //System.out.println("Failed to load image: " + imageName);
            e.printStackTrace();
        }
    }
}
