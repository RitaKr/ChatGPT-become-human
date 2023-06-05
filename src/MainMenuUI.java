import javax.swing.*;
import java.awt.*;

public class MainMenuUI extends JFrame {
    private JPanel backgroundPanel;

    public MainMenuUI() {
        super("хз");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(890, 710);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        /// Background image
        backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("images/bg-menu.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        backgroundPanel.setPreferredSize(new Dimension(800, 600));

        add(backgroundPanel, BorderLayout.CENTER);
        backgroundPanel.setLayout(new GridBagLayout());


        // create and customize the label
        JLabel title = new JLabel("ChatGPT: Become Human", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        // Add border to the label
//        title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        backgroundPanel.add(title, c);

        // create and customize the buttons
        String[] buttonNames = {"Start game", "Instruction", "Settings"};
        for (int i = 0; i < buttonNames.length; i++) {
            JButton button = new JButton(buttonNames[i]);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.setBackground(new Color(96, 0, 128));
            button.setForeground(Color.WHITE);
            c.gridy = i + 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(25,0,0,0);  // Add some space between the buttons
            backgroundPanel.add(button, c);
        }

        // Make JFrame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenuUI();
    }
}
