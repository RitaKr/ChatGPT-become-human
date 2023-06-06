import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuUI extends JFrame {
    private JPanel backgroundPanel;
    Image backgroundImage;

    ProgressData progressData;
    //ChatUI chatUI = new ChatUI();

    public MainMenuUI() {
        super("хз");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.progressData = progressData;

        setSize(890, 710);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        loadBackgroundImage("bg-menu.png");

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
        JLabel title = new JLabel("ChatGPT: Become Human", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setForeground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        backgroundPanel.add(title, c);

        // create and customize the buttons
        String[] buttonNames = {"Play", "Instruction", "Settings"};
        for (int i = 0; i < buttonNames.length; i++) {
            JButton button = new JButton(buttonNames[i]);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.setBackground(new Color(138, 19, 178));
            button.setForeground(Color.WHITE);
            c.gridy = i + 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(25,0,0,0);  // Add some space between the buttons
            backgroundPanel.add(button, c);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (finalI){
                        case 0: {
                            Main.chatUI.setVisible(true);
                            Main.chatUI.requestFocus();
                            SwingUtilities.invokeLater(()->dispose());
                        }
                    }
                }
            });

        }

        // Make JFrame visible
        //setVisible(true);
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        System.out.println("x "+getWidth() + ", y" + getHeight() );
    }

    private void loadBackgroundImage(String imageName) {
        try {
            ImageIcon icon = new ImageIcon("images/"+imageName); // Replace with the path to your character image file
            backgroundImage = icon.getImage();
            if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                System.out.println("Failed to load image: " + imageName);
                backgroundImage = null;
            }
        } catch (Exception e) {
            System.out.println("Failed to load image: " + imageName);
            e.printStackTrace();
        }
    }
}
