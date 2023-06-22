import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuizWindow extends JDialog {
    private final int width = 500;
    private final int height = 600;
    private int btnHeight = 100;
    private int btnWidth = 400;
    private JPanel contentPane;
    Component parent;
    private String message;
    private String title = "Quiz";

    private Image backgroundImage = new ImageIcon("images/bg-instruction.jpg").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

    private Image buttonImage = new ImageIcon("images/button-darkpurple.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
    private Image buttonHoverImage = new ImageIcon("images/button-blue.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);

    private Image correctAnswerImage = new ImageIcon("images/button-green.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
    private Image wrongAnswerImage = new ImageIcon("images/button-red-transparent.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);

    private Font font = new Font("Arial", Font.BOLD, 20);
    private Font font1 = new Font("Arial", Font.PLAIN, 16);
    private Color textColor = Color.WHITE;
    private Color buttonColor = Color.WHITE;

    private Quiz quiz;
    private static int chosenAnswer;

    public boolean isAnsweredCorrectly() {
        return quiz.isAnsweredCorrectly();
    }

    /**
     * Конструктор для створення вікна вікторини.
     *
     * @param parent компонент-батько, на якому буде розміщене вікно
     * @param quiz   об'єкт вікторини
     */
    public QuizWindow(Component parent, Quiz quiz) {
        super((Window) SwingUtilities.getWindowAncestor(parent), "Quiz", ModalityType.APPLICATION_MODAL);
        this.parent = parent;
        this.quiz = quiz;
        this.message = quiz.getQuestion();
        if (quiz.isCompleted()) chosenAnswer = quiz.getChosenAnswer();

        initializeUI();
    }

    /**
     * Ініціалізує графічний інтерфейс вікна вікторини.
     */
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(lblMessage, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));

        for (int i = 0; i < quiz.getAnswers().size(); i++) {
            JButton answerBtn = createButton(quiz.getAnswers().get(i).getText());
            buttonPanel.add(answerBtn);

            int finalI = i;
                answerBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!quiz.isCompleted()) {
                            Main.playEffect("click.wav", 0.2);
                            chosenAnswer = finalI;
                            quiz.setChosenAnswer(finalI);
                            if (finalI == quiz.getCorrectAnswerIndex()) {
                                quiz.setAnsweredCorrectly(true);
                                answerBtn.setIcon(new ImageIcon(correctAnswerImage));
                            } else {
                                quiz.setAnsweredCorrectly(false);
                                answerBtn.setIcon(new ImageIcon(wrongAnswerImage));
                            }
                            Timer timer = new Timer(1000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    dispose();
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        }
                    }
                });
            if (quiz.isCompleted() && i==chosenAnswer) {
                if (i == quiz.getCorrectAnswerIndex()) answerBtn.setIcon(new ImageIcon(correctAnswerImage));
                else answerBtn.setIcon(new ImageIcon(wrongAnswerImage));
            }

        }


        gbc.gridy = 1;
        gbc.weighty = 5.0;
        contentPane.add(buttonPanel, gbc);

        JLabel lblBackground = new JLabel(new ImageIcon(backgroundImage));
        gbc.gridy = 0;
        gbc.gridheight = 2;
        contentPane.add(lblBackground, gbc);

        setTitle(title);
        setVisible(true);
    }

    /**
     * Створює кнопку з вказаним текстом.
     * @param text текст кнопки
     * @return створена кнопка
     */
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

        btn.addActionListener(e -> {quiz.setCompleted(true);});

            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!quiz.isCompleted()) {
                        Main.playEffect("hover.wav", 0.2);
                        btn.setIcon(new ImageIcon(buttonHoverImage));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!quiz.isCompleted()) btn.setIcon(new ImageIcon(buttonImage));  // Restore the default image
                }
            });

        return btn;
    }


}


