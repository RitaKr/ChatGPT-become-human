import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MessageWindow extends JDialog {
    private final int width = 400;
    private final int height = 220;
    private int btnHeight = 60;
    private int btnWidth = 180;
    private JPanel contentPane;
    Component parent;
    private String message;
    private String title;

    private Image backgroundImage = new ImageIcon("images/message-bg.jpg").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    private Image buttonImage = new ImageIcon("images/button-lightblue-tansparent.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
    private Image buttonHoverImage = new ImageIcon("images/button-white-transparent.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
    private Font font = new Font("Arial", Font.BOLD, 18);
    private Font font1 = new Font("Arial", Font.PLAIN, 16);
    private Color textColor = Color.WHITE;
    private Color buttonColor = Color.WHITE;

    private boolean yes;
    public boolean isYes() {
        return yes;
    }
    private boolean confirmDialog;

    private String btnText1;
    private String btnText2;

    public MessageWindow(Component parent, String message, String title, String btnText1, String btnText2) {
        super((Window) SwingUtilities.getWindowAncestor(parent), title, ModalityType.APPLICATION_MODAL);
        this.message = message;
        this.title = title;
        this.parent = parent;
        confirmDialog = true;
        this.btnText1 = btnText1;
        this.btnText2 = btnText2;
        initializeUI();
    }

    public MessageWindow(Component parent, String message, String title, String btnText2) {
        super((Window) SwingUtilities.getWindowAncestor(parent), title, ModalityType.APPLICATION_MODAL);
        this.message = message;
        this.title = title;
        this.parent = parent;
        confirmDialog = false;
        btnWidth = 200;

        buttonImage = new ImageIcon("images/button-lightblue-tansparent.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
        buttonHoverImage = new ImageIcon("images/button-white-transparent.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);

        this.btnText2 = btnText2;
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(width, height));
        setLocationRelativeTo(parent);

        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JLabel lblMessage = new JLabel("<html><div style=\"text-align: center; padding: 0 10px\">"+message+"</div></html>");
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setVerticalAlignment(SwingConstants.CENTER);
        lblMessage.setFont(font);
        lblMessage.setForeground(textColor);
        lblMessage.setBorder(new EmptyBorder(new Insets(20, 0, 00, 0)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(lblMessage, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(new Insets(10, 0, 40, 0)));
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

        btn.addActionListener(e -> {Main.playEffect("click.wav", 0.2);dispose();});

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Main.playEffect("hover.wav", 0.2);
                btn.setIcon(new ImageIcon(buttonHoverImage));  // Set the hover image
                //startFadeIn(btn);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setIcon(new ImageIcon(buttonImage));  // Restore the default image
                //startFadeOut(btn);
            }
        });
        return btn;
    }

    public static void main(String[] args) {
        JFrame parent = new JFrame("Parent Frame");
        parent.setSize(500, 300);
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parent.setLocationRelativeTo(null);
        parent.setVisible(true);

        MessageWindow messageWindow = new MessageWindow(parent, "You completed level 1!", "Maze completed", "OK");
    }
}
