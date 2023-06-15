import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private int btnWidth = 180;
    private boolean yes;
    public boolean isYes() {
        return yes;
    }
    private boolean confirmDialog;

    private String btnText1;
    private String btnText2;
    public MessageWindow(Component parent, String message, String title, String btnText1, String btnText2) {
        this.message = message;
        this.title = title;
        this.parent = parent;
        confirmDialog = true;
        btnWidth = 180;
        this.btnText1 = btnText1;
        this.btnText2 = btnText2;
        backgroundImage = new ImageIcon("images/message-bg.jpg").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        buttonImage = new ImageIcon("images/button-lightblue.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
        initializeUI();
    }
    public MessageWindow(Component parent, String message, String title, String btnText2) {
        this.message = message;
        this.title = title;
        this.parent = parent;
        confirmDialog = false;
        btnWidth = 200;
        this.btnText2 = btnText2;
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
        if (confirmDialog) {
            JButton btnYes = createButton(btnText1);
            buttonPanel.add(btnYes);
            btnYes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    yes = true;
                    dispose();
                }
            });
        }
        JButton btnNo = createButton(btnText2);
        buttonPanel.add(btnNo);

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


    private JButton createButton(String text) {
        JButton btn = new JButton(new ImageIcon(buttonImage)) {
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

        btn.setLayout(new BorderLayout()); // Set button layout to BorderLayout

        JLabel lblButtonText = new JLabel(text);
        lblButtonText.setHorizontalAlignment(SwingConstants.CENTER);
        lblButtonText.setVerticalAlignment(SwingConstants.CENTER);
        lblButtonText.setForeground(buttonColor);
        lblButtonText.setFont(font1);
        btn.add(lblButtonText, BorderLayout.CENTER); // Add the label to the button's center

        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        btn.setPreferredSize(new Dimension(btnWidth, btnHeight));

        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMargin(null);

        btn.addActionListener(e -> dispose());
        return btn;
    }


    public static void main(String[] args) {
        MessageWindow messageWindow = new MessageWindow(null, "You completed level " +1+ "!", "Maze completed", "OK");

    }
}
