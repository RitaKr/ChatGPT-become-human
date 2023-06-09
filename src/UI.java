import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class UI extends JFrame {
    GridBagConstraints c = new GridBagConstraints();
    final static Color transparent = new Color(0,0,0, 0.00f);
    JPanel backgroundPanel;
    Image backgroundImage;
    Font font16 = new Font("Arial", Font.PLAIN, 16);
    Font font18 = new Font("Arial", Font.BOLD, 20);
    Font font22 = new Font("Arial", Font.BOLD, 22);
    Font font24 = new Font("Arial", Font.BOLD, 24);
    Font titleFont = new Font("Arial", Font.BOLD, 35);
    Color buttonColor = new Color(45, 114, 255);
    Color buttonColorHover = new Color(225, 225, 255);
    Color titleColor = Color.white;
    Color textColor = Color.white;
    JPanel upperPanel = new JPanel(new BorderLayout());
    JLabel levelLabel = new JLabel();
    JButton quitButton;
    Image crossImage = new ImageIcon("images/x.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    Image crossHoverImage = new ImageIcon("images/x-hover.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    Color upperPanelBg = new Color(35, 35, 47);
    Color backgroundColor = new Color(85, 82, 93);


    int btnWidth = 280;
    int btnHeight = 90;
    private String title;
    private String backgroundImagePath;
    int frameWidth = 890;
    int frameHeight = 710;
    static int menuHeight = 40;
    JFrame quitFrame;

    boolean enableDefaultQuit = true;

    /**
     * Конструктор для створення об'єкта загального інтерфейсу UI.
     *
     * @param title             заголовок UI
     * @param backgroundImagePath шлях до зображення фону
     * @param quitFrame         вікно для виходу
     */
    public UI(String title, String backgroundImagePath, JFrame quitFrame)  {
        super(title);
        this.title = title;
        this.backgroundImagePath = backgroundImagePath;
        this.quitFrame = quitFrame;
        setUI();
    }

    /**
     * Конструктор для створення об'єкта загального інтерфейсу UI без зображення фону.
     *
     * @param title     заголовок UI
     * @param quitFrame вікно для виходу
     */
    public UI(String title,  JFrame quitFrame)  {
        super(title);
        this.title = title;
        this.quitFrame = quitFrame;
        setUI();
    }

    /**
     * Конструктор для створення об'єкта загального інтерфейсу UI з зображенням фону.
     *
     * @param title             заголовок UI
     * @param backgroundImagePath шлях до зображення фону
     */
    public UI(String title, String backgroundImagePath)  {
        super(title);
        this.title = title;
        this.backgroundImagePath = backgroundImagePath;
        this.quitFrame = this;
        setUI();
    }

    /**
     * Конструктор для створення об'єкта загального інтерфейсу UI з включеною функцією стандартного закриття.
     *
     * @param title             заголовок UI
     * @param enableDefaultQuit флаг, що визначає, чи включити стандартну функцію закриття
     */
    public UI(String title, boolean enableDefaultQuit)  {
        super(title);
        this.title = title;
        this.quitFrame = this;
        this.enableDefaultQuit = enableDefaultQuit;
        setUI();
    }

    /**
     * Встановлює вигляд UI, включаючи налаштування шрифту та фону.
     */
    void setUI() {

        try {
            // Load the font file
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font.ttf"));

            // Set the font size and style as desired
            titleFont = customFont.deriveFont(35f).deriveFont(Font.BOLD);
            font16 = customFont.deriveFont(16f).deriveFont(Font.PLAIN);
            font18 = customFont.deriveFont(18f).deriveFont(Font.BOLD);
            font22 = customFont.deriveFont(22f).deriveFont(Font.BOLD);
            font24 = customFont.deriveFont(24f).deriveFont(Font.BOLD);
            // Use the custom font in your Swing components

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        //setUpperPanel(this);
        try {
            loadBackgroundImage(backgroundImagePath);

            backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backgroundImage != null) {
                        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                    }
                }
            };
        } catch (Exception e) {
            backgroundPanel = new JPanel();
            backgroundPanel.setBackground(backgroundColor);
        }


        backgroundPanel.setPreferredSize(new Dimension(frameWidth, frameHeight));

        add(backgroundPanel, BorderLayout.CENTER);

        setFocusable(true);
        requestFocusInWindow();
        super.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE: {
                        quit(quitFrame);
                        break;
                    }
                    case KeyEvent.VK_SHIFT:
                        if (Main.isMusicPlaying()) {
                            System.out.println("The track is playing");
                            Main.pauseMusic();
                        } else {
                            System.out.println("The track is paused");
                            Main.playMusic();
                        }

                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /**
     * Створює кнопку з заданим текстом, кольором тексту, фоновим зображенням та зображенням при наведенні, шириною та висотою.
     *
     * @param text            текст кнопки
     * @param textColor       колір тексту
     * @param backgroundImage фонове зображення
     * @param hoverImage      зображення при наведенні
     * @param width           ширина кнопки
     * @param height          висота кнопки
     * @return створена кнопка
     */
    JButton createButton(String text, Color textColor, Image backgroundImage, Image hoverImage, int width, int height) {
        JButton button = new JButton(new ImageIcon(backgroundImage.getScaledInstance(width, height, Image.SCALE_SMOOTH))) {
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

        button.setLayout(new BorderLayout());

        JLabel buttonText = new JLabel(text, SwingConstants.CENTER);
        buttonText.setPreferredSize(new Dimension(btnWidth, btnHeight));
        buttonText.setHorizontalAlignment(SwingConstants.CENTER);
        buttonText.setVerticalAlignment(SwingConstants.CENTER);
        buttonText.setForeground(textColor);
        buttonText.setFont(font18);
        button.add(buttonText, BorderLayout.CENTER); // Add the label to the button's center

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);


        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMargin(null);
        button.setSize(new Dimension(width, height));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Main.playEffect("hover.wav", 0.2);
                buttonText.setForeground(buttonColorHover);
                button.setIcon(new ImageIcon(hoverImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));  // Set the hover image
                //startFadeIn(btn);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonText.setForeground(textColor);
                button.setIcon(new ImageIcon(backgroundImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)));  // Restore the default image
                //startFadeOut(btn);
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.playEffect("click.wav", 0.2);
            }
        });

        return button;
    }

    /**
     * Оновлює дані про прогрес та відповідно змінює текст заголовка рівня.
     */
    public void updateProgressData(){
        Main.fetchProgress();
        updateUpperPanel();
    }

    /**
     * Завантажує зображення фону.
     *
     * @param imageName ім'я зображення фону
     */
    private void loadBackgroundImage(String imageName) {
        String imagePath = "images/" + imageName;

        if (imagePath.toLowerCase().endsWith(".gif")) {
            backgroundImage = Toolkit.getDefaultToolkit().createImage(imagePath);
        } else {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }
    }

    /**
     * Оновлює верхню панель інтерфейсу.
     */
    public void updateUpperPanel() {
        levelLabel.setText((Main.getLanguage().equals("en") ? "Current level " : "Поточний рівень ") + Main.getProgress().getLv());
        levelLabel.updateUI();
    }
    /**
     * Встановлює верхню панель для поточного фрейму.
     *
     * @param currentFrame поточний фрейм
     */
    public void setUpperPanel(JFrame currentFrame){

        upperPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
        upperPanel.setBackground(upperPanelBg);
        upperPanel.setPreferredSize(new Dimension(frameWidth, menuHeight));
        upperPanel.setAlignmentX(CENTER_ALIGNMENT);
        upperPanel.setAlignmentY(CENTER_ALIGNMENT);

        levelLabel = new JLabel((Main.getLanguage().equals("en") ? "Current level: " : "Поточний рівень: ")+Main.getProgress().getLv());
        levelLabel.setForeground(textColor);
        levelLabel.setFont(font16);


        quitButton = setIconButton(new ImageIcon(crossImage), new ImageIcon(crossHoverImage),30, 0);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.playEffect("click.wav", 0.2);
                if (enableDefaultQuit) {
                    quit(quitFrame);
                    currentFrame.requestFocus();
                }
            }
        });

        upperPanel.add(levelLabel, BorderLayout.WEST);
        upperPanel.add(quitButton, BorderLayout.EAST);

    }

    /**
     * Creates a button with an icon.
     *
     * @param icon       the icon of the button
     * @param hoverIcon  the icon of the button when hovered
     * @param size       the size of the button (width and height)
     * @param padding    the padding of the button
     * @return the button with the icon
     */
    private JButton setIconButton(ImageIcon icon,ImageIcon hoverIcon,int size, int padding) {
        JButton button = new JButton(icon);
        button.setBackground(null);

        button.setPreferredSize(new Dimension(size, size));
        button.setMargin(new Insets(padding, padding, padding, padding));
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(hoverIcon);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(icon);

            }
        });
        return button;
    }

    /**
     * Закриває поточне вікно та відображає вказане вікно призначення.
     * @param destinationFrame Вікно, яке буде показане після закриття поточного вікна.
     */
    public void quit(JFrame destinationFrame){
        destinationFrame.setVisible(true);
        SwingUtilities.invokeLater(this::dispose);

    }








}
