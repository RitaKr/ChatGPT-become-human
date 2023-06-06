import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class InstructionUI extends JPanel {
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 35);
    private static final Color TITLE_COLOR = new Color(221,160,221);
    private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Color TEXT_COLOR = Color.WHITE;

    private static final String BACKGROUND_IMAGE_PATH = "images/bg-instruction.jpg";
    private static final String MOB_IMAGE_PATH = "images/virus.png";

    public InstructionUI() {
        try {
            // Задання фону
            ImageIcon bgIcon = new ImageIcon(BACKGROUND_IMAGE_PATH);
            bgIcon.setImage(bgIcon.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT)); // Скейлуємо фонове зображення
            JLabel bg = new JLabel(bgIcon);
            this.setLayout(new BorderLayout());
            bg.setLayout(new BorderLayout());
            this.add(bg, BorderLayout.CENTER);

            // Заголовок
            JLabel titleLabel = new JLabel("Інструкція до гри", SwingConstants.CENTER);
            titleLabel.setFont(TITLE_FONT);
            titleLabel.setForeground(TITLE_COLOR);
            titleLabel.setBorder(new LineBorder(Color.WHITE));
            bg.add(titleLabel, BorderLayout.NORTH);

            // Панель для вмісту
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setBackground(null);

            // Скрол
            JScrollPane scrollPane = new JScrollPane(contentPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);
            bg.add(scrollPane, BorderLayout.CENTER);

            // Інструкції для моба
            JPanel mobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            mobPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
            mobPanel.setOpaque(false);
            ImageIcon mobIcon = new ImageIcon(MOB_IMAGE_PATH);
            JLabel mobLabel = new JLabel(mobIcon);
            mobPanel.add(mobLabel);

            String mobText = "<html><div WIDTH=700><b>Моби</b>. Це рухомі об'єкти, які створюють труднощі у проходженні гри. Якщо зіштовхнутися з мобом, то ви втрачаєте одне життя.</div></html>";
            JLabel mobInstructionLabel = new JLabel(mobText);
            mobInstructionLabel.setFont(TEXT_FONT);
            mobInstructionLabel.setForeground(TEXT_COLOR);
            mobPanel.add(mobInstructionLabel);

            contentPanel.add(mobPanel);

        } catch (Exception e) {
            System.out.println("Помилка при завантаженні зображень");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Інструкція до гри");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new InstructionUI());
        frame.setVisible(true);
    }
}
