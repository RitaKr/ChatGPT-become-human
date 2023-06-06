import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatUI extends JFrame {
    static JPanel chatArea = new JPanel(new GridBagLayout());
    static JPanel upperPanel = new JPanel(new BorderLayout());
    static JButton quitButton;
    static ImageIcon crossIcon = new ImageIcon("images/cross.png");
    static ImageIcon sendIcon = new ImageIcon("images/send.png");
    static Color upperPanelBg = new Color(45, 36, 58);
    static Color chatBg = new Color(85, 82, 93);
    static Color userMsgBg = new Color(59, 57, 66);
    JPanel bottomPanel = new JPanel(new BorderLayout());
    static JPanel answersPanel = new JPanel(new BorderLayout());
    static JButton sendButton = new JButton();
    static Font font = new Font("Arial", Font.PLAIN, 16);
    GridBagConstraints gbc = new GridBagConstraints();
    ProgressData progressData;
    ChatData chatData = new ChatData();
    JButton answer1;
    JButton answer2;
    JScrollPane scrollPane;
    public ChatUI(ProgressData progressData) {
        setTitle("ChatGPT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(chatBg);
        setUpperPanel();

        this.progressData = progressData;
        chatData = progressData.getChatData();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        chatArea.setBackground(chatBg);
        //MessagePanel message1 = new MessagePanel(true, "Some text by chat gpt tftjfy Some text by user jhgjh hgf nhgnff ghjtt jttyj ftyjffy f ytjf yt fj   tftjfy Some text by user jhgjh hgf nhgnff ghjtt jttyj ftyjffy f ytjf yt fj   tftjfy");
        //MessagePanel message2 = new MessagePanel(false, "Some text by user");

        //chatArea.add(message1);
        //chatArea.add(message2);
        //chatArea.setPreferredSize(new Dimension(860, 580));
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

        setVisible(true);
    }
    private void addMessage(ChatData.Dialog dialog){
        for (String text : dialog.getGpt().getTexts()) {
            MessagePanel message = new MessagePanel(true, text);
            chatArea.add(message, gbc);
        }
        answer1.setText(HTMLfyText(dialog.getUser()[0].getTexts()[0]));
        answer2.setText(HTMLfyText(dialog.getUser()[1].getTexts()[0]));


        final boolean[] answered = {false};
        answer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!answered[0]) {
                    answered[0] = true;
                    dialog.getUser()[0].setChosen(true);
                    dialog.setCompleted(true);
                    MessagePanel message = new MessagePanel(false, (dialog.getUser()[0].isChosen() ? dialog.getUser()[0].getTexts()[0] : dialog.getUser()[1].getTexts()[0] ));
                    chatArea.add(message, gbc);
                    try {
                        ChatData.Dialog newDialog = progressData.getChatData().chapter1.getDialogs().get(dialog.getUser()[0].getPlot());
                        chatData.chapter.getDialogs().add(newDialog);
                        System.out.println("dialogs after user answer "+chatData.chapter.getDialogs());
                        System.out.println("next dialog "+newDialog);
                        addMessage(newDialog);
                    } catch (IndexOutOfBoundsException ex) {
                        //dispose();
                        if (dialog.getUser()[0].getPlot()==progressData.getChatData().chapter1.getDialogs().size()) {
                            System.out.println(true);
                            new MazeUI(Main.getProgress().getLv());
                            SwingUtilities.invokeLater(()->dispose());
                        }
                    }
                }
            }
        });
        answer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!answered[0]) {
                    answered[0] = true;
                    dialog.getUser()[1].setChosen(true);
                    dialog.setCompleted(true);
                    MessagePanel message = new MessagePanel(false, (dialog.getUser()[0].isChosen() ? dialog.getUser()[0].getTexts()[0] : dialog.getUser()[1].getTexts()[0] ));
                    chatArea.add(message, gbc);
                    try {
                        ChatData.Dialog newDialog = progressData.getChatData().chapter1.getDialogs().get(dialog.getUser()[1].getPlot());
                        chatData.chapter.getDialogs().add(newDialog);
                        addMessage(newDialog);
                    } catch (IndexOutOfBoundsException ex) {
                        //dispose();
                        if (dialog.getUser()[1].getPlot()==progressData.getChatData().chapter1.getDialogs().size()) {
                            System.out.println(true);
                            new MazeUI(Main.getProgress().getLv());
                            SwingUtilities.invokeLater(()->dispose());
                        }
                    }
                }
            }
        });
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
        System.out.println(chatData.chapter.getDialogs());
        for (ChatData.Dialog dialog : chatData.chapter.getDialogs() ) {
            //if (dialog.isCompleted()) {
            addMessage(dialog);
            //}
        }

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

        //levelLabel = new JLabel("Level "+game.getLevel());
        //levelLabel.setForeground(Color.WHITE);

        //heartsPanel.setBackground(null);

        quitButton = setIconButton(crossIcon, 30, 0);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int answer = JOptionPane.showConfirmDialog(null, "Do you want to go back to the main menu?","Quit chat", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    new MainMenuUI(Main.getProgress());
                    SwingUtilities.invokeLater(()->dispose());
                } else {
                }

            }
        });

        //upperPanel.add(levelLabel, BorderLayout.WEST);
        //upperPanel.add(heartsPanel, BorderLayout.CENTER);
        upperPanel.add(quitButton, BorderLayout.EAST);

    }
    public static void main(String[] args) {
        Main.fetchProgress();
        new ChatUI(Main.getProgress());
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
            //setLayout(new FlowLayout());


            //avatarPanel.setPreferredSize(new Dimension(40, 40));
            //avatarPanel.setBorder(null);


            //if (chatGPT) {
                avatarImageLabel = new JLabel(chatGPT ? chatGPTIcon : userIcon);
                avatarImageLabel.setPreferredSize(new Dimension(40, 40));
                //avatarPanel.setBackground(chatGPT ? chatBg : userMsgBg);

//            } else {
//                avatarImageLabel = new JLabel("You", SwingConstants.CENTER);
//                avatarImageLabel.setPreferredSize(new Dimension(40, 40));
//                //avatarPanel.setPreferredSize(new Dimension(40, 40));
//                avatarPanel.setBackground(Color.gray);
//                avatarImageLabel.setFont(font);
//                avatarImageLabel.setForeground(Color.WHITE);
//                avatarPanel.setAlignmentX(CENTER_ALIGNMENT);
//                avatarPanel.setAlignmentY(CENTER_ALIGNMENT);
//                //avatarImageLabel.setBorder(BorderFactory.createLineBorder(chatBg, 1, true));
//            }
            //avatarPanel.add(avatarImageLabel);


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
            //System.out.println("w: "+width+", h:"+ height);
        }

    }
}



