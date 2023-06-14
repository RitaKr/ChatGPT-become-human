import javax.swing.*;
import java.awt.*;

public class MessageWindow extends JFrame {
    private JPanel contentPane;
    private String message;
    private String title;
    private int messageType;
    private Image backgroundImage;
    private Font font;
    private Color textColor;
    private Color buttonColor;

    public MessageWindow(String message, String title) {
        this.message = message;
        this.title = title;


        font = new Font("Arial", Font.BOLD, 16);
        textColor = Color.WHITE;
        buttonColor = Color.BLUE;

        backgroundImage = new ImageIcon("images/bg1.jpg").getImage();

        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblMessage = new JLabel(message);
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setFont(font);
        lblMessage.setForeground(textColor);
        lblMessage.setBounds(10, 20, 380, 40);
        contentPane.add(lblMessage);

        JButton btnOk = new JButton("OK");
        btnOk.setFont(font);
        btnOk.setBackground(buttonColor);
        btnOk.setForeground(textColor);
        btnOk.setBounds(150, 100, 100, 30);
        btnOk.addActionListener(e -> dispose());
        contentPane.add(btnOk);

        JLabel lblBackground = new JLabel();
        lblBackground.setBounds(0, 0, 400, 200);
        lblBackground.setIcon(new ImageIcon(backgroundImage));
        contentPane.add(lblBackground);

        setTitle(title);
        setVisible(true);
    }


    public static void main(String[] args) {
        MessageWindow messageWindow = new MessageWindow("You completed level " +1+ "!", "Maze completed");

    }
}
