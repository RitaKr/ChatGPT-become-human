import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsUI extends UI{
    private static final Color TITLE_COLOR = new Color(197, 198, 238);
    private static final Color BG_COLOR = new Color(1, 2, 26);
    JPanel titlePanel;
    JLabel titleLabel;

    public SettingsUI() {
        super("ChatGPT: become human", "bg-instruction.jpg", Main.mainMenuUI);
        super.backgroundPanel.setLayout(new BorderLayout());
        setUpperPanel(this);

        // Заголовок
        drawTitle();








        add(super.upperPanel, BorderLayout.NORTH);
    }
    private void drawTitle() {
        titleLabel = new JLabel(Main.getLanguage().equals("uk") ? "Налаштування гри" : "Game settings", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(TITLE_COLOR);
        //titleLabel.setBorder(new LineBorder(TITLE_COLOR));
        titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBorder(new EmptyBorder(8,0,8, 0));
        titlePanel.setBackground(BG_COLOR);
        super.backgroundPanel.add(titlePanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new SettingsUI();

    }
}
