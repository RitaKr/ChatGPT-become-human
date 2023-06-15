import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatUI extends JFrame {
    static JPanel chatArea = new JPanel(new GridBagLayout());
    static JPanel upperPanel = new JPanel(new BorderLayout());
    static JLabel levelLabel = new JLabel();
    static JButton quitButton;
    static ImageIcon crossIcon = new ImageIcon("images/cross.png");
    static Image crossImage = new ImageIcon("images/x.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    static Color upperPanelBg = new Color(45, 36, 58);
    static Color chatBg = new Color(85, 82, 93);
    static Color userMsgBg = new Color(59, 57, 66);
    JPanel bottomPanel = new JPanel(new BorderLayout());
    static JPanel answersPanel = new JPanel(new BorderLayout());
    static JButton sendButton = new JButton();
    static Font font = new Font("Arial", Font.PLAIN, 16);
    GridBagConstraints gbc = new GridBagConstraints();
    ChatData chatData = new ChatData();
    JButton answer1;
    JButton answer2;
    JScrollPane scrollPane;
    public void updateProgressData(){
        Main.fetchProgress();
        levelLabel.setText("Current level: "+Main.getProgress().getLv());
        levelLabel.updateUI();
    }
    public ChatUI() {
        super("ChatGPT: become human");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(chatBg);
        setUpperPanel();

        chatData = Main.getProgress().getChatData();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        chatArea.setBackground(chatBg);

        scrollPane = new JScrollPane(chatArea);
        scrollPane.setPreferredSize(new Dimension(860, 580));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scroll bar
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Enable vertical scroll bar

        answer1 = setAnswerOption("");
        answer2 = setAnswerOption("");
        answersPanel.add(answer1, BorderLayout.WEST);
        answersPanel.add(answer2, BorderLayout.EAST);
        answersPanel.setBackground(userMsgBg);
        setMessages();

        answersPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        //sendButton = setIconButton(sendIcon, 70, 10);

        bottomPanel.add(answersPanel, BorderLayout.CENTER);
        //bottomPanel.add(sendButton, BorderLayout.EAST);

        getContentPane().add(upperPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);

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
                        //System.out.println("quit");
                        quit();
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        //setVisible(true);
    }
    private void quit(){
        Main.mainMenuUI.setVisible(true);
        SwingUtilities.invokeLater(this::dispose);

    }
    private void addMessage(ChatData.Dialog dialog){
        for (String text : dialog.getGpt().getTexts()) {
            MessagePanel message = new MessagePanel(true, text);
            chatArea.add(message, gbc);
        }
        answer1.setText(HTMLfyText(dialog.getUser()[0].getTexts()[0]));
        answer2.setText(HTMLfyText(dialog.getUser()[1].getTexts()[0]));


        //final boolean answered = dialog.isCompleted();
        if (!dialog.isCompleted() && Main.getProgress().isAlive()) {
            answer1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!dialog.isCompleted()) {
                        //answered[0] = true;
                        dialog.getUser()[0].setChosen(true);
                        dialog.setCompleted(true);
                        MessagePanel message = new MessagePanel(false, (dialog.getUser()[0].isChosen() ? dialog.getUser()[0].getTexts()[0] : dialog.getUser()[1].getTexts()[0]));
                        chatArea.add(message, gbc);
                        try {
                            ChatData.Dialog newDialog = chatData.chapter1.getDialogs().get(dialog.getUser()[0].getPlot());
                            chatData.chapter.getDialogs().add(newDialog);
                            ////System.out.println("dialogs after user answer " + chatData.chapter.getDialogs());
                            ////System.out.println("next dialog " + newDialog);
                            addMessage(newDialog);
                        } catch (IndexOutOfBoundsException ex) {
                            //System.out.println("plot "+dialog.getUser()[0].getPlot());
                            //dispose();
                            if (dialog.getUser()[0].getPlot() == chatData.chapter1.getDialogs().size()) {
                                ////System.out.println(true);
                                if (Main.getProgress().getLv()==0) Main.updateLevel();
                                dialog.setCompleted(false);
                                Main.startMazeGame();
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                //System.out.println("exit game");
                                dispose();
                                System.exit(0); // Exit the program
                            }
                        }
                    }
                    ChatUI.super.requestFocus();
                }
            });
            answer2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!dialog.isCompleted()) {
                        //answered[0] = true;
                        dialog.getUser()[1].setChosen(true);
                        dialog.setCompleted(true);
                        MessagePanel message = new MessagePanel(false, (dialog.getUser()[0].isChosen() ? dialog.getUser()[0].getTexts()[0] : dialog.getUser()[1].getTexts()[0]));
                        chatArea.add(message, gbc);
                        try {
                            ChatData.Dialog newDialog = chatData.chapter1.getDialogs().get(dialog.getUser()[1].getPlot());
                            chatData.chapter.getDialogs().add(newDialog);
                            addMessage(newDialog);
                        } catch (IndexOutOfBoundsException ex) {
                            //dispose();
                            //System.out.println("plot "+dialog.getUser()[0].getPlot());
                            if (dialog.getUser()[1].getPlot() == chatData.chapter1.getDialogs().size()) {
                                ////System.out.println(true);
                                if (Main.getProgress().getLv()==0) Main.updateLevel();
                                dialog.setCompleted(false);
                                Main.startMazeGame();
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                //System.out.println("exit game");
                                dispose();
                                System.exit(0); // Exit the program
                            }
                        }
                    }
                    ChatUI.super.requestFocus();
                }
            });
        }
        if (dialog.isCompleted()) {
            MessagePanel message = new MessagePanel(false, (dialog.getUser()[0].isChosen() ? dialog.getUser()[0].getTexts()[0] : dialog.getUser()[1].getTexts()[0] ));
            chatArea.add(message, gbc);
        }


        SwingUtilities.invokeLater(() -> {
            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
        });
        Main.updateChatData(chatData);
        //chatArea.setPreferredSize(new Dimension(860, chatArea.getHeight()+100*(1+dialog.getGpt().getTexts().length)));
    }
    private void setMessages(){
        //System.out.println(chatData.chapter.getDialogs());
        for (ChatData.Dialog dialog : chatData.chapter.getDialogs() ) {
            //if (dialog.isCompleted()) {
            addMessage(dialog);
            //}
        }
        if (!Main.getProgress().isAlive()) {
            addDeathMessage();
        }

    }
    public void addDeathMessage(){
        addMessage(chatData.deathDialog);
    }
    private JButton setIconButton(ImageIcon icon,int size, int padding) {
        JButton button = new JButton(icon);
        button.setBackground(null);

        button.setPreferredSize(new Dimension(size, size));
        button.setMargin(new Insets(padding, padding, padding, padding));
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
    public JButton setAnswerOption(String text) {
        JButton answer = new JButton(HTMLfyText(text));
        answer.setFont(font);
        answer.setBackground(chatBg);
        answer.setAlignmentX(CENTER_ALIGNMENT);
        answer.setAlignmentY(CENTER_ALIGNMENT);
        answer.setBorderPainted(false);
        answer.setForeground(Color.WHITE);
        answer.setPreferredSize(new Dimension(430, 70));
        return answer;
    }
    public void setUpperPanel(){
        upperPanel.setBorder(new EmptyBorder(5, 20, 5, 20));
        upperPanel.setBackground(upperPanelBg);
        upperPanel.setPreferredSize(new Dimension(MazeGame.getMazeWidth(), MazeUI.menuHeight));
        upperPanel.setAlignmentX(CENTER_ALIGNMENT);
        upperPanel.setAlignmentY(CENTER_ALIGNMENT);

        levelLabel = new JLabel("Current level "+Main.getProgress().getLv());
        levelLabel.setForeground(Color.WHITE);


        quitButton = setIconButton(new ImageIcon(crossImage), 30, 0);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });

        upperPanel.add(levelLabel, BorderLayout.WEST);
        upperPanel.add(quitButton, BorderLayout.EAST);

    }
    public static void main(String[] args) {
        Main.fetchProgress();
        new ChatUI();
    }
    private String HTMLfyText(String text) {
        return "<html><div style=\"padding: 5px 10px\">"+text+"</div></html>";
    }
    class MessagePanel extends JPanel {
        JPanel contentPanel = new JPanel(new BorderLayout());
        JLabel messageLabel;
        //JPanel avatarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel avatarImageLabel;
        Image avatarImage;
        ImageIcon chatGPTIcon = new ImageIcon("images/chatGPT.png");
        ImageIcon userIcon = new ImageIcon("images/user-icon.png");

        public MessagePanel(boolean chatGPT, String text){
            setPreferredSize(new Dimension(890, 100));
            setBackground(chatGPT ? chatBg : userMsgBg);
            contentPanel.setBackground(chatGPT ? chatBg : userMsgBg);

            avatarImageLabel = new JLabel(chatGPT ? chatGPTIcon : userIcon);
            avatarImageLabel.setPreferredSize(new Dimension(40, 40));


            messageLabel = new JLabel(HTMLfyText(text));
            messageLabel.setForeground(Color.WHITE);
            messageLabel.setFont(font);
            messageLabel.setVerticalAlignment(SwingConstants.CENTER);

            contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            contentPanel.add(avatarImageLabel, BorderLayout.WEST);
            contentPanel.add(messageLabel, BorderLayout.CENTER);
            contentPanel.setPreferredSize(new Dimension(600, 100));

            add(contentPanel);

        }

        private void loadImage() {
            avatarImage = chatGPTIcon.getImage();
            ////System.out.println("w: "+width+", h:"+ height);
        }

    }
}



