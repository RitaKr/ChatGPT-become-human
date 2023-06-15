import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class QuizWindow extends JFrame {
    private final int width = 500;
    private final int height = 500;
    private int btnHeight = 80;
    private int btnWidth = 360;
    private JPanel contentPane;
    Component parent;
    private String message;
    private String title;

    private Image backgroundImage = new ImageIcon("images/message-bg.jpg").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

    private Image buttonImage = new ImageIcon("images/button-darkpurple.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
    private Image buttonHoverImage = new ImageIcon("images/button-blue.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);

    private Image correctAnswerImage = new ImageIcon("images/button-green.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
    private Image wrongAnswerImage = new ImageIcon("images/button-red-transparent.png").getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);

    private Font font = new Font("Arial", Font.BOLD, 20);
    private Font font1 = new Font("Arial", Font.PLAIN, 16);
    private Color textColor = Color.WHITE;
    private Color buttonColor = Color.WHITE;

    private boolean answeredCorrectly;

    public boolean isAnsweredCorrectly() {
        return answeredCorrectly;
    }

    private Quiz quiz;

    public Quiz getQuiz() {
        return quiz;
    }
    private Timer timer;
    private float alpha;
    public QuizWindow(Component parent, Quiz quiz) {
        this.title = "Quiz";
        this.parent = parent;
        this.quiz = quiz;
        this.message = quiz.getQuestion();


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
        buttonPanel.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));

        for (int i = 0; i < quiz.getAnswers().size(); i++) {
            JButton answerBtn = createButton(quiz.getAnswers().get(i).getText());
            buttonPanel.add(answerBtn);

            int finalI = i;
                answerBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!quiz.isCompleted()) {
                            if (finalI == quiz.getCorrectAnswerIndex()) {
                                answeredCorrectly = true;
                                answerBtn.setIcon(new ImageIcon(correctAnswerImage));
                            } else {
                                answeredCorrectly = false;
                                answerBtn.setIcon(new ImageIcon(wrongAnswerImage));
                            }
                            Timer timer = new Timer(4000, new ActionListener() {
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

        btn.addActionListener(e -> {quiz.setCompleted(true);});

            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!quiz.isCompleted()) btn.setIcon(new ImageIcon(buttonHoverImage));  // Set the hover image
                    //startFadeIn(btn);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!quiz.isCompleted()) btn.setIcon(new ImageIcon(buttonImage));  // Restore the default image
                    //startFadeOut(btn);
                }
            });

        return btn;
    }


    public static void main(String[] args) {
        QuizWindow quizWindow = new QuizWindow(null, new Quiz("1. Який з цих методів використовується для виводу тексту в консоль в Java?", new ArrayList<>(Arrays.asList(new Answer("A. System.out.display()"), new Answer("B. System.out.print()", true), new Answer("C. Console.write()"), new Answer("D. Print.console()")))));
        quizWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Run the remaining code here
                System.out.println("is answered correctly: " +quizWindow.isAnsweredCorrectly());
            }
        });
    }
}

class Quiz {
    private String question;
    private ArrayList<Answer> answers;
    private boolean completed = false;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
    public Answer getCorrectAnswer() {
        return answers.get(getCorrectAnswerIndex());
    }
    public int getCorrectAnswerIndex() {
        Object[] bools =  answers.stream().map(Answer::isCorrect).toArray();
        return Arrays.stream(bools).toList().indexOf(true);
    }
    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Quiz(String question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;

    }
    public Quiz(String question, ArrayList<Answer> answers, boolean completed) {
        this.question = question;
        this.answers = answers;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "\nquestion='" + question + '\'' +
                ", \nanswers=" + answers +
                ", \ncompleted=" + completed +
                "\n}";
    }
}
class Answer {
    private String text;
    private boolean correct = false;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Answer(String text) {
        this.text = text;
    }
    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "{" + text + "(" + correct +")}";
    }
}
