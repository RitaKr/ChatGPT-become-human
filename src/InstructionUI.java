import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class InstructionUI extends JFrame {
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 35);
    private static final Color TITLE_COLOR = new Color(70, 137, 197);
    private static final Color BG_COLOR = new Color(1, 2, 26);
    private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Color TEXT_COLOR = Color.WHITE;

    private static final String BACKGROUND_IMAGE_PATH = "images/bg-instruction.jpg";
    private static final String MOB_IMAGE_PATH = "images/virus.png";
    ImageIcon bgIcon = new ImageIcon(BACKGROUND_IMAGE_PATH);
    JPanel backgroundPanel;
    Image backgroundImage = bgIcon.getImage(); //.getScaledInstance(890, 670, Image.SCALE_DEFAULT);
    JPanel contentPanel;
    JPanel titlePanel;
    JLabel titleLabel;
    JScrollPane scrollPane;
    JPanel mobPanel;
    JLabel mobInstructionLabel;
    JLabel mobLabel;
    public InstructionUI() {
        super("Інструкція до гри");
        try {

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(890, 710);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());


            // Задання фону

            //bgIcon.setImage(); // Скейлуємо фонове зображення
            //JLabel bg = new JLabel(bgIcon);
            backgroundPanel = new JPanel(new BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backgroundImage != null) {
                        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                    }
                }
            };

            backgroundPanel.setPreferredSize(new Dimension(890, 710));



            // Заголовок
            titleLabel = new JLabel("Інструкція до гри", SwingConstants.CENTER);
            titleLabel.setFont(TITLE_FONT);
            titleLabel.setForeground(TITLE_COLOR);
            //titleLabel.setBorder(new LineBorder(TITLE_COLOR));
            titlePanel = new JPanel();
            titlePanel.add(titleLabel);
            titlePanel.setBorder(new EmptyBorder(8,0,8, 0));
            titlePanel.setBackground(BG_COLOR);
            backgroundPanel.add(titlePanel, BorderLayout.NORTH);

            // Панель для вмісту
            contentPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backgroundImage != null) {
                        System.out.println("bg drawn to content panel");
                        g.drawImage(backgroundImage, 0, -40, this.getWidth(), this.getHeight()+40, this);
                    }
                }
            };
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            //contentPanel.setBackground(null);
            //contentPanel.setBackground(Color.black);


            // Інструкції для моба
            mobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            mobPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
            mobPanel.setOpaque(false);
            mobPanel.setBackground(null);

            ImageIcon mobIcon = new ImageIcon(MOB_IMAGE_PATH);
            mobLabel = new JLabel(mobIcon);
            mobLabel.setBackground(null);
            mobPanel.add(mobLabel);

            String mobText = "<html><div WIDTH=700><b>Моби</b>. Це рухомі об'єкти, які створюють труднощі у проходженні гри. Якщо зіштовхнутися з мобом, то ви втрачаєте одне життя.</div></html>";
            mobInstructionLabel = new JLabel(mobText);
            mobInstructionLabel.setFont(TEXT_FONT);
            mobInstructionLabel.setForeground(TEXT_COLOR);
            mobPanel.add(mobInstructionLabel);

            contentPanel.add(mobPanel);

            // Скрол
            scrollPane = new JScrollPane(contentPanel);

            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);


            backgroundPanel.add(scrollPane, BorderLayout.CENTER);
            add(backgroundPanel, BorderLayout.CENTER);
            setVisible(true);

        } catch (Exception e) {
            System.out.println("Помилка при завантаженні зображень");
        }
    }

    public static void main(String[] args) {
        new InstructionUI();

    }
}
