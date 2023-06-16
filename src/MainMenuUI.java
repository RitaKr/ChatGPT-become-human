import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuUI extends JFrame {
    private JPanel backgroundPanel;
    JLabel title;
    final static Color bgColor = new Color(138, 19, 178);
    Image backgroundImage;
    Image startImage = new ImageIcon("images/start.png").getImage();
    Image buttonImage = new ImageIcon("images/button-pattern.png").getImage();
    Image redButtonImage = new ImageIcon("images/button-pattern-red.png").getImage();
    Image startHoverImage = new ImageIcon("images/start-white.png").getImage();
    Image buttonHoverImage = new ImageIcon("images/button-pattern-white.png").getImage();
    GridBagConstraints c = new GridBagConstraints();
    private Font font = new Font("Arial", Font.BOLD, 22);
    private Color buttonColor = new Color(45, 114, 255);
    private Color buttonColor1 = new Color(222, 68, 68);
    private Color buttonColorHover = new Color(225, 225, 255);
    int btnWidth = 280;
    int btnHeight = 90;


    public MainMenuUI() {
        super("ChatGPT: become human");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.progressData = progressData;

        setSize(890, 710);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        loadBackgroundImage("bg-menu4.gif");

        backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };

        backgroundPanel.setPreferredSize(new Dimension(890, 710));
        add(backgroundPanel, BorderLayout.CENTER);

        // create and customize the label
        title = new JLabel("ChatGPT: Become Human", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setForeground(Color.WHITE);

        c.gridx = 0;
        c.gridy = 0;
        backgroundPanel.add(title, c);

        JButton startButton = createButton("", buttonColor, startImage, startHoverImage);
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(25,100,0,100);  // Add some space between the buttons
        backgroundPanel.add(startButton, c);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Main.chatUI.updateProgressData();
                Main.chatUI.setVisible(true);
                Main.chatUI.requestFocus();
                SwingUtilities.invokeLater(()->dispose());
            }
        });

        // create and customize the buttons
        String[] buttonNames = {"Instruction", "Mazes", "Reset progress"};
        for (int i = 1; i <= buttonNames.length; i++) {
            JButton button = createButton(buttonNames[i-1],(i<buttonNames.length ? buttonColor : buttonColor1), (i<buttonNames.length ? buttonImage : redButtonImage), buttonHoverImage);

            c.gridy = i + 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(10,100,0,100);  // Add some space between the buttons
            backgroundPanel.add(button, c);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.fetchProgress();
                    switch (finalI){
                        case 1: {
                            Main.instructionUI.updateProgressData();
                            Main.instructionUI.setVisible(true);
                            Main.instructionUI.requestFocus();
                            SwingUtilities.invokeLater(()->dispose());
                            break;
                        }
                        case 2: {
                            Main.chooseMazeUI.updateProgressData();
                            Main.chooseMazeUI.setVisible(true);
                            Main.chooseMazeUI.requestFocus();
                            SwingUtilities.invokeLater(()->dispose());
                            break;
                        }
                        case 3: {
                            MessageWindow messageWindow = new MessageWindow(MainMenuUI.this, "Are you sure you want to reset the progress? You will need to open the program again", "Reset progress", "Yes", "No");
                            messageWindow.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    if (messageWindow.isYes()) {
                                        Main.resetProgress();
                                        dispose();
                                        System.exit(0);
                                    }
                                }
                            });

                            break;
                        }
                    }

                }
            });

        }

        // Make JFrame visible
        //setVisible(true);
    }
    private JButton createButton(String text, Color textColor, Image backgroundImage, Image hoverImage) {
        JButton button = new JButton(new ImageIcon(backgroundImage.getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH))) {
            @Override
            protected void paintComponent(Graphics g) {
                // Make the background transparent
                g.setColor(new Color(0, 0, 0, 0));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                // Ensure the button is not opaque
                return false;
            }
        };
        //button.setBackground(i<buttonNames.length-1 ? bgColor : Color.red);
        //button.setForeground(Color.WHITE);

        button.setLayout(new BorderLayout());

        JLabel buttonText = new JLabel(text, SwingConstants.CENTER);
        buttonText.setPreferredSize(new Dimension(btnWidth, btnHeight));
        buttonText.setHorizontalAlignment(SwingConstants.CENTER);
        buttonText.setVerticalAlignment(SwingConstants.CENTER);
        buttonText.setForeground(textColor);
        buttonText.setFont(font);
        button.add(buttonText, BorderLayout.CENTER); // Add the label to the button's center

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);


        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMargin(null);
        button.setSize(new Dimension(btnWidth, btnHeight));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonText.setForeground(buttonColorHover);
                button.setIcon(new ImageIcon(hoverImage.getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH)));  // Set the hover image
                //startFadeIn(btn);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonText.setForeground(textColor);
                button.setIcon(new ImageIcon(backgroundImage.getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH)));  // Restore the default image
                //startFadeOut(btn);
            }
        });

        return button;
    }
    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        //System.out.println("x "+getWidth() + ", y" + getHeight() );
    }

//    private void loadBackgroundImage(String imageName) {
//        try {
//            ImageIcon icon = new ImageIcon("images/"+imageName); // Replace with the path to your character image file
//            backgroundImage = icon.getImage();
//            if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
//                //System.out.println("Failed to load image: " + imageName);
//                backgroundImage = null;
//            }
//        } catch (Exception e) {
//            //System.out.println("Failed to load image: " + imageName);
//            e.printStackTrace();
//        }
//    }
    private void loadBackgroundImage(String imageName) {
        String imagePath = "images/" + imageName;

        if (imagePath.toLowerCase().endsWith(".gif")) {
            backgroundImage = Toolkit.getDefaultToolkit().createImage(imagePath);
        } else {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }
    }
}
