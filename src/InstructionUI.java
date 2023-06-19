import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InstructionUI extends UI {
    private static final Color TITLE_COLOR = new Color(197, 198, 238);
    private static final Color BG_COLOR = new Color(1, 2, 26);
    //private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Color TEXT_COLOR = Color.WHITE;

    private static final String BACKGROUND_IMAGE_PATH = "images/bg-instruction.jpg";
    private static final String GPT_IMAGE_PATH = "images/chatGPT.png";
    private static final String KEYBOARD_IMAGE_PATH = "images/keyboard.png";
    private static final String FINISH_IMAGE_PATH = "images/finish.png";
    private static final String SLIDING_DOOR_IMAGE_PATH = "images/slidingDoor.png";
    private static final String DOOR_BUTTON_IMAGE_PATH = "images/doorButton.png";
    private static final String ROTATING_DOOR_IMAGE_PATH = "images/rotatingDoor.png";
    private static final String KEY_IMAGE_PATH = "images/key.png";
    private static final String MOB_IMAGE_PATH = "images/virus.png";
    private static final String TELEPORT_IMAGE_PATH = "images/teleport.png";
    private static final String TELEPORT2_IMAGE_PATH = "images/teleport2.png";

    ImageIcon bgIcon = new ImageIcon(BACKGROUND_IMAGE_PATH);
    JPanel backgroundPanel;
    Image backgroundImage = bgIcon.getImage(); //.getScaledInstance(890, 670, Image.SCALE_DEFAULT);
    JPanel contentPanel;
    JPanel titlePanel;
    JLabel titleLabel;
    JScrollPane scrollPane;
    JPanel keyboardPanel, mobPanel, GPTPanel, slidingDoorPanel, doorButtonPanel, rotatingDoorPanel, keyPanel, teleportPanel, finishPanel;
    JLabel keyboardInstructionLabel, mobInstructionLabel, GPTInstructionLabel, slidingDoorInstructionLabel, doorButtonInstructionLabel, rotatingDoorInstructionLabel, keyInstructionLabel, teleportInstructionLabel, finishInstructionLabel;
    JLabel keyboardLabel, mobLabel, GPTLabel, slidingDoorLabel, doorButtonLabel, rotatingDoorLabel, keyLabel, teleportLabel, finishLabel;

    public InstructionUI() {
        super("ChatGPT: become human", "bg-instruction.jpg", Main.mainMenuUI);
        super.backgroundPanel.setLayout(new BorderLayout());
        setUpperPanel(this);

            // Заголовок
            drawTitle();
            
            // Панель для вмісту
            contentPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backgroundImage != null) {
                        //System.out.println("bg drawn to content panel");
                        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                    }
                }
            };

            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            //contentPanel.setBackground(transparent);
            //contentPanel.setBackground(null);
            //contentPanel.setBackground(Color.black);

            GPTInstruction();
            keyboardInstruction();
            finishInstruction();
            mobInstruction();
            teleportInstruction();
            slidingDoorInstruction();
            doorButtonInstruction();
            rotatingDoorInstruction();
            keyInstruction();

            // Скрол
            scrollPane = new JScrollPane(contentPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null);

            super.backgroundPanel.add(scrollPane, BorderLayout.CENTER);
            add(super.upperPanel, BorderLayout.NORTH);
            //add(backgroundPanel, BorderLayout.CENTER);


    }


    public void GPTInstruction(){
        GPTPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        GPTPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
        GPTPanel.setOpaque(false);
        GPTPanel.setBackground(null);

        ImageIcon GPTIcon = new ImageIcon(GPT_IMAGE_PATH);
        GPTLabel = new JLabel(GPTIcon);
        GPTLabel.setBackground(null);
        GPTPanel.add(GPTLabel);

        String GPTText = "<html><div WIDTH=650><i><b>ChatGPT — головний персонаж</b></i>, " +
                "яким Ви рухаєтеся коридорами лабіринту та маєте дійти до фінішу </div></html>";
        GPTInstructionLabel = new JLabel(GPTText);
        GPTInstructionLabel.setFont(font16);
        GPTInstructionLabel.setForeground(TEXT_COLOR);
        GPTPanel.add(GPTInstructionLabel);

        contentPanel.add(GPTPanel);
    }

    private void keyboardInstruction(){
        keyboardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        keyboardPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
        keyboardPanel.setOpaque(false);
        keyboardPanel.setBackground(null);

//        ImageIcon mobIcon = new ImageIcon(KEYBOARD_IMAGE_PATH);
        ImageIcon keyboardIcon = new ImageIcon(new ImageIcon(KEYBOARD_IMAGE_PATH).getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
        keyboardLabel = new JLabel(keyboardIcon);
        keyboardLabel.setBackground(null);
        keyboardPanel.add(keyboardLabel);

        String keyboardText = "<html><div WIDTH=650><i><b>Переміщення по лабіринту</b></i> стандартне. Використовуйте стрілки вверх, вниз, вправо, вліво," +
                " щоб рухатися в цих напрямках коридорами лабіринту. </div></html>";
        keyboardInstructionLabel = new JLabel(keyboardText);
        keyboardInstructionLabel.setFont(font16);
        keyboardInstructionLabel.setForeground(TEXT_COLOR);
        keyboardPanel.add(keyboardInstructionLabel);

        contentPanel.add(keyboardPanel);
    }

    private void finishInstruction(){
        finishPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        finishPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
        finishPanel.setOpaque(false);
        finishPanel.setBackground(null);

        ImageIcon finishIcon = new ImageIcon(new ImageIcon(FINISH_IMAGE_PATH).getImage().getScaledInstance(100, 85, Image.SCALE_DEFAULT));
        finishLabel = new JLabel(finishIcon);
        finishLabel.setBackground(null);
        finishPanel.add(finishLabel);

        String finishText = "<html><div WIDTH=650><i><b>Двері фінішу</b></i>, до яких Ви маєте прийти, щоб завершити рівень.</div></html>";
        finishInstructionLabel = new JLabel(finishText);
        finishInstructionLabel.setFont(font16);
        finishInstructionLabel.setForeground(TEXT_COLOR);
        finishPanel.add(finishInstructionLabel);

        contentPanel.add(finishPanel);
    }

    private void mobInstruction() {
        mobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mobPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
        mobPanel.setOpaque(false);
        mobPanel.setBackground(null);

        ImageIcon mobIcon = new ImageIcon(MOB_IMAGE_PATH);
        mobLabel = new JLabel(mobIcon);
        mobLabel.setBackground(null);
        mobPanel.add(mobLabel);

        String mobText = "<html><div WIDTH=700><i><b>Моби.</b></i> Це рухомі об'єкти, які створюють труднощі у проходженні гри. " +
                "Якщо зіштовхнутися з мобом, то ви втрачаєте одне життя.</div></html>";
        mobInstructionLabel = new JLabel(mobText);
        mobInstructionLabel.setFont(font16);
        mobInstructionLabel.setForeground(TEXT_COLOR);
        mobPanel.add(mobInstructionLabel);

        contentPanel.add(mobPanel);
    }

    private void teleportInstruction(){
        teleportPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        teleportPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
        teleportPanel.setOpaque(false);
        teleportPanel.setBackground(null);

        ImageIcon teleportIcon = new ImageIcon(new ImageIcon(TELEPORT_IMAGE_PATH).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        teleportLabel = new JLabel(teleportIcon);
        teleportLabel.setBackground(null);
        teleportPanel.add(teleportLabel);

        ImageIcon teleport2Icon = new ImageIcon(new ImageIcon(TELEPORT2_IMAGE_PATH).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        teleportLabel = new JLabel(teleport2Icon);
        teleportLabel.setBackground(null);
        teleportPanel.add(teleportLabel);

        String teleportText = "<html><div WIDTH=520><i><b>Телепорти</b></i> переносять персонажа з одного місця лабіринту " +
                "в інше. З'являються лише попарно, переміщують лише до телепорту з таким самим виглядом.</div></html>";
        teleportInstructionLabel = new JLabel(teleportText);
        teleportInstructionLabel.setFont(font16);
        teleportInstructionLabel.setForeground(TEXT_COLOR);
        teleportPanel.add(teleportInstructionLabel);

        contentPanel.add(teleportPanel);
    }

    private void slidingDoorInstruction(){
        slidingDoorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        slidingDoorPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
        slidingDoorPanel.setOpaque(false);
        slidingDoorPanel.setBackground(null);

        ImageIcon slidingDoorIcon = new ImageIcon(new ImageIcon(SLIDING_DOOR_IMAGE_PATH).getImage().getScaledInstance(150, 80, Image.SCALE_DEFAULT));
        slidingDoorLabel = new JLabel(slidingDoorIcon);
        slidingDoorLabel.setBackground(null);
        slidingDoorPanel.add(slidingDoorLabel);

        String slidingDoorText = "<html><div WIDTH=650><i><b>Двері, які ковзають лабіринтом.</b></i> Виглядають як фіолетова стінка. " +
                "Щоб їх перемістити, натисніть на клавішу ПРОБІЛ, перебуваючи на рожевій кнопці, яка розміщена десь в коридорах лабіринту. </div></html>";
        slidingDoorInstructionLabel = new JLabel(slidingDoorText);
        slidingDoorInstructionLabel.setFont(font16);
        slidingDoorInstructionLabel.setForeground(TEXT_COLOR);
        slidingDoorPanel.add(slidingDoorInstructionLabel);

        contentPanel.add(slidingDoorPanel);
    }

    private void doorButtonInstruction(){
        doorButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        doorButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
        doorButtonPanel.setOpaque(false);
        doorButtonPanel.setBackground(null);

//        ImageIcon keyboardIcon = new ImageIcon(DOOR_BUTTON_IMAGE_PATH);
        ImageIcon keyboardIcon = new ImageIcon(new ImageIcon(DOOR_BUTTON_IMAGE_PATH).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        doorButtonLabel = new JLabel(keyboardIcon);
        doorButtonLabel.setBackground(null);
        doorButtonPanel.add(doorButtonLabel);

        String doorButtonText = "<html><div WIDTH=650><i><b>Кнопка, що відчиняє двері</b></i>, які ковзають лабіринтом.</div></html>";
        doorButtonInstructionLabel = new JLabel(doorButtonText);
        doorButtonInstructionLabel.setFont(font16);
        doorButtonInstructionLabel.setForeground(TEXT_COLOR);
        doorButtonPanel.add(doorButtonInstructionLabel);

        contentPanel.add(doorButtonPanel);
    }

    private void rotatingDoorInstruction(){
        rotatingDoorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rotatingDoorPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
        rotatingDoorPanel.setOpaque(false);
        rotatingDoorPanel.setBackground(null);

        ImageIcon rotatingDoorIcon = new ImageIcon(new ImageIcon(ROTATING_DOOR_IMAGE_PATH).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        rotatingDoorLabel = new JLabel(rotatingDoorIcon);
        rotatingDoorLabel.setBackground(null);
        rotatingDoorPanel.add(rotatingDoorLabel);

        String slidingDoorText = "<html><div WIDTH=650><i><b>Двері, що обертаються.</b></i> Виглядають як сіра стінка. " +
                "Повертаються на 90 градусів. " +
                "Щоб їх повернути, знайдіть в коридорах лабіринту ключ та натисніть клавішу ПРОБІЛ. </div></html>";
        rotatingDoorInstructionLabel = new JLabel(slidingDoorText);
        rotatingDoorInstructionLabel.setFont(font16);
        rotatingDoorInstructionLabel.setForeground(TEXT_COLOR);
        rotatingDoorPanel.add(rotatingDoorInstructionLabel);

        contentPanel.add(rotatingDoorPanel);
    }

    private void keyInstruction(){
        keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        keyPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // додаємо відступи
        keyPanel.setOpaque(false);
        keyPanel.setBackground(null);

//        ImageIcon keyIcon = new ImageIcon(KEY_IMAGE_PATH);
        ImageIcon keyIcon = new ImageIcon(new ImageIcon(KEY_IMAGE_PATH).getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT));
        keyLabel = new JLabel(keyIcon);
        keyLabel.setBackground(null);
        keyPanel.add(keyLabel);

        String keyText = "<html><div WIDTH=650><i><b>Ключ, що відчиняє двері</b></i>, які обертаються.</div></html>";
        keyInstructionLabel = new JLabel(keyText);
        keyInstructionLabel.setFont(font16);
        keyInstructionLabel.setForeground(TEXT_COLOR);
        keyPanel.add(keyInstructionLabel);

        contentPanel.add(keyPanel);
    }

    private void drawTitle() {
        titleLabel = new JLabel("Інструкція до гри", SwingConstants.CENTER);
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
        new InstructionUI();

    }
}
