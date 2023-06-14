import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class MessageWindow extends JFrame {
    private JPanel contentPane;
    Component parent;
    private String message;
    private String title;

    private Image backgroundImage;
    private Image buttonImage;
    private Font font = new Font("Arial", Font.BOLD, 20);
    private Font font1 = new Font("Arial", Font.PLAIN, 16);
    private Color textColor = Color.WHITE;
    private Color buttonColor = Color.WHITE;
    private final int width = 400;
    private final int height = 250;
    private int btnHeight = 80;
    private int btnWidth = 200;
    public MessageWindow(Component parent, String message, String title) {
        this.message = message;
        this.title = title;
        this.parent = parent;

        backgroundImage = new ImageIcon("images/message-bg.jpg").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        buttonImage = new ImageIcon("images/button-lightblue.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(width, height));
        setLocationRelativeTo(parent);

        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JLabel lblMessage = new JLabel("<html><div style=\"text-align: center;\">"+message+"</div></html>");
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setVerticalAlignment(SwingConstants.CENTER);
        lblMessage.setFont(font);
        lblMessage.setForeground(textColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(lblMessage, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(new Insets(20, 0, 60, 0)));
        JButton btnOk = new JButton(new ImageIcon(buttonImage)) {
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

        btnOk.setLayout(new BorderLayout()); // Set button layout to BorderLayout

        JLabel lblButtonText = new JLabel("OK");
        lblButtonText.setHorizontalAlignment(SwingConstants.CENTER);
        lblButtonText.setVerticalAlignment(SwingConstants.CENTER);
        lblButtonText.setForeground(buttonColor);
        lblButtonText.setFont(font1);
        btnOk.add(lblButtonText, BorderLayout.CENTER); // Add the label to the button's center

        btnOk.setContentAreaFilled(false);
        btnOk.setFocusPainted(false);
        btnOk.setBorderPainted(false);
        btnOk.setOpaque(false);
        btnOk.setPreferredSize(new Dimension(btnWidth, btnHeight));

        btnOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnOk.setMargin(null);

        btnOk.addActionListener(e -> dispose());
        buttonPanel.add(btnOk);


        gbc.gridy = 1;
        gbc.weighty = 0.0;
        contentPane.add(buttonPanel, gbc);

        JLabel lblBackground = new JLabel(new ImageIcon(backgroundImage));
        gbc.gridy = 0;
        gbc.gridheight = 2;
        contentPane.add(lblBackground, gbc);

        setTitle(title);
        setVisible(true);
    }

    private static class CustomButtonUI extends BasicButtonUI {
        @Override
        protected void installDefaults(AbstractButton button) {
            super.installDefaults(button);
            button.setContentAreaFilled(false);
        }
    }


    public static void main(String[] args) {
        MessageWindow messageWindow = new MessageWindow(null, "You completed level " +1+ "!", "Maze completed");

    }
}
