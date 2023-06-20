import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class ChatUI extends UI {
    static JPanel chatArea = new JPanel(new GridBagLayout());
    static Color chatBg = new Color(85, 82, 93);
    static Color answerBtnColor = chatBg;
    static Color answerBtnHoverColor = new Color(129, 125, 140);
    static Color userMsgBg = new Color(59, 57, 66);
    JPanel bottomPanel = new JPanel(new BorderLayout());
    static JPanel answersPanel = new JPanel(new BorderLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    ChatData chatData = new ChatData();
    JButton answer1;
    JButton answer2;
    JScrollPane scrollPane;
    int additionalDialogs;

    public ChatUI() {
        super("ChatGPT: become human", Main.mainMenuUI);
        setUpperPanel(this);

        chatData = Main.getProgress().getChatData();
        additionalDialogs = 0;
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


    }
    private void addMessage(Dialog dialog, int level){
        for (String text : dialog.getGpt().getTexts()) {
            MessagePanel message = new MessagePanel(true, text);
            chatArea.add(message, gbc);
        }
        if (Main.getProgress().getDialogCount()<(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size()+additionalDialogs)) dialog.setId(Main.getProgress().getDialogCount());
        System.out.println("set id "+Main.getProgress().getDialogCount()+" in addMessage");
        System.out.println("adding message. cur lv: "+level+" (id: "+dialog.getId()+")"+", cur dialog: "+dialog);
        System.out.println("total dialogs: "+(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size()+additionalDialogs)+"");

//        answer1.setText(HTMLfyText(dialog.getUser()[0].getTexts()[0]));
//        answer2.setText(HTMLfyText(dialog.getUser()[1].getTexts()[0]));
        answer1.setText(dialog.getUser()[0].getTexts()[0]);
        answer2.setText(dialog.getUser()[1].getTexts()[0]);

        //final boolean answered = dialog.isCompleted();


        if (Main.getProgress().isAlive()) {
            answer1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("pressed answer1: "+" (id: "+dialog.getId()+")"+", dialog: "+dialog);

                    //System.out.println("total dialogs: "+(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size()+additionalDialogs)+"");


                    //                    if (!dialog.isCompleted() && dialog.getId()==(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size()-1)) {
                    if (!dialog.isCompleted() || dialog.getId()==(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size()+additionalDialogs-1)) {
                        //if (!dialog.isCompleted() && (dialog.getUser()[0].getPlot()<chatData.chapter1.getDialogs().size() && !chatData.yourChapter1.getDialogs().contains(chatData.chapter1.getDialogs().get(dialog.getUser()[0].getPlot())) || dialog.getUser()[0].getPlot()>=chatData.chapter1.getDialogs().size())) {
                        Main.playEffect("click.wav", 0.2);
                        //answered[0] = true;
                        dialog.getUser()[0].setChosen(true);
                        dialog.setCompleted(true);
                        MessagePanel message = new MessagePanel(false, (dialog.getUser()[0].isChosen() ? dialog.getUser()[0].getTexts()[0] : dialog.getUser()[1].getTexts()[0]));
                        chatArea.add(message, gbc);
                        System.out.println("!!!added answer1: "+" (id: "+dialog.getId()+")"+", dialog: "+dialog+"\n");
//                        System.out.println("cur lv: "+level+" (id: "+dialog.getId()+")"+", cur dialog: "+dialog);
//                        System.out.println("total dialogs: "+(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size())+"");

                        dialog.setId(Main.getProgress().getDialogCount());
                        System.out.println("set id "+Main.getProgress().getDialogCount()+" in answer1.addActionListener");
                        Main.updateDialogCount();
                        try {
                            switch (level){
                                case 0: {
                                    Dialog newDialog = Main.getLanguage().equals("en") ?
                                            chatData.chapter1.getDialogs().get(dialog.getUser()[0].getPlot()) :
                                            chatData.chapter1Ukr.getDialogs().get(dialog.getUser()[0].getPlot());
                                    chatData.yourChapter1.getDialogs().add(newDialog);
                                    addMessage(newDialog, 0);
                                    break;
                                }
                                case 1: {
                                    //chatData.yourChapter1.getDialogs().add(dialog);
                                    if (dialog.getUser()[0].getPlot() == 0) {
                                        ////System.out.println(true);
                                        //if (Main.mazeUI==null || !Main.mazeUI.isMazeCompleted()) dialog.setCompleted(false);
                                        Main.startMazeGame();
                                        SwingUtilities.invokeLater(() -> dispose());
                                    } else if (dialog.getUser()[0].getPlot() == 100){
                                        dispose();
                                        System.exit(0);
                                    } else {
                                        addDeathMessage(1);
                                    }
                                    break;
                                }
                                case 2: {
                                    Dialog newDialog = Main.getLanguage().equals("en") ?
                                            chatData.chapter2.getDialogs().get(dialog.getUser()[0].getPlot()) :
                                            chatData.chapter2Ukr.getDialogs().get(dialog.getUser()[0].getPlot());
                                    chatData.yourChapter2.getDialogs().add(newDialog);
                                    addMessage(newDialog, 2);
                                    break;
                                }
                            }

                        } catch (IndexOutOfBoundsException ex) {
                            //System.out.println("plot "+dialog.getUser()[0].getPlot());
                            //dispose();
                            if (dialog.getUser()[0].getPlot() == chatData.chapter1.getDialogs().size() && level<2
                                    || dialog.getUser()[0].getPlot() == chatData.chapter2.getDialogs().size() && level==2) {
                                ////System.out.println(true);
                                //if (Main.mazeUI==null || !Main.mazeUI.isMazeCompleted()) dialog.setCompleted(false);
                                if (Main.getProgress().getLv()==0) Main.updateLevel();
                                Main.startMazeGame();
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                //System.out.println("exit game");
                                dispose();
                                System.exit(0); // Exit the program
                            }
                        }
                    } else if (dialog.getGpt().getPlot()==101) {
                        dispose();
                        System.exit(0);
                    }
//                    else if (dialog.getGpt().getPlot()>=100 || (dialog.getUser()[0].getPlot() == chatData.chapter1.getDialogs().size() && level<2)) {
//
//                        ////System.out.println(true);
//                        //if (Main.mazeUI==null || !Main.mazeUI.isMazeCompleted()) dialog.setCompleted(false);
//                        System.out.println("stating game in this stupid else");
//                        Main.startMazeGame();
//                        SwingUtilities.invokeLater(() -> dispose());
//
//                    }
                    ChatUI.super.requestFocus();
                }
            });
            answer2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("pressed answer2: "+" (id: "+dialog.getId()+")"+", dialog: "+dialog);
                    //System.out.println("total dialogs: "+(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size()+additionalDialogs)+"");

                    //                    if (!dialog.isCompleted() && dialog.getId()==(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size()-1)) {
                    if (!dialog.isCompleted() || dialog.getId()==(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size()+additionalDialogs-1)) {
                        //answered[0] = true;
                        Main.playEffect("click.wav", 0.2);
                        dialog.getUser()[1].setChosen(true);
                        dialog.setCompleted(true);
                        MessagePanel message = new MessagePanel(false, (dialog.getUser()[0].isChosen() ? dialog.getUser()[0].getTexts()[0] : dialog.getUser()[1].getTexts()[0]));
                        chatArea.add(message, gbc);
                        System.out.println("!!!added answer2: "+" (id: "+dialog.getId()+")"+", dialog: "+dialog+"\n");


                        dialog.setId(Main.getProgress().getDialogCount());
                        System.out.println("set id "+Main.getProgress().getDialogCount()+" in answer2.addActionListener");
                        Main.updateDialogCount();

                        try {
                            switch (level){
                                case 0: {
                                    Dialog newDialog = Main.getLanguage().equals("en") ?
                                            chatData.chapter1.getDialogs().get(dialog.getUser()[1].getPlot()) :
                                            chatData.chapter1Ukr.getDialogs().get(dialog.getUser()[1].getPlot());
                                    chatData.yourChapter1.getDialogs().add(newDialog);
                                    addMessage(newDialog, 0);
                                    break;
                                }
                                case 1: {
                                    //chatData.yourChapter1.getDialogs().add(dialog);
                                    if (dialog.getUser()[1].getPlot() == 0) {
                                        ////System.out.println(true);
                                        //if (Main.mazeUI==null || !Main.mazeUI.isMazeCompleted()) dialog.setCompleted(false);
                                        Main.startMazeGame();
                                        SwingUtilities.invokeLater(() -> dispose());
                                    } else if (dialog.getUser()[1].getPlot() == 100){
                                        dispose();
                                        System.exit(0);
                                    } else {
                                        addDeathMessage(1);
                                    }
                                    break;
                                }
                                case 2: {
                                    Dialog newDialog = Main.getLanguage().equals("en") ?
                                            chatData.chapter2.getDialogs().get(dialog.getUser()[1].getPlot()) :
                                            chatData.chapter2Ukr.getDialogs().get(dialog.getUser()[1].getPlot());
                                    chatData.yourChapter2.getDialogs().add(newDialog);
                                    addMessage(newDialog, 2);
                                    break;
                                }
                            }
                        } catch (IndexOutOfBoundsException ex) {
                            //dispose();
                            //System.out.println("plot "+dialog.getUser()[0].getPlot());
                            if (dialog.getUser()[1].getPlot() == chatData.chapter1.getDialogs().size() && level<2
                                    || dialog.getUser()[1].getPlot() == chatData.chapter2.getDialogs().size() && level==2) {
                                ////System.out.println(true);
                                //if (Main.mazeUI==null || !Main.mazeUI.isMazeCompleted()) dialog.setCompleted(false);
                                if (Main.getProgress().getLv()==0) Main.updateLevel();
                                Main.startMazeGame();
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                //System.out.println("exit game");
                                dispose();
                                System.exit(0); // Exit the program
                            }
                        }
                    } else if (dialog.getGpt().getPlot()==101) {
                        dispose();
                        System.exit(0);
                    }
//                    else if (dialog.getGpt().getPlot()>=100 || (dialog.getUser()[1].getPlot() == chatData.chapter1.getDialogs().size() && level<2)) {
//
//                            ////System.out.println(true);
//                            //if (Main.mazeUI==null || !Main.mazeUI.isMazeCompleted()) dialog.setCompleted(false);
//                        System.out.println("stating game in this stupid else");
//                            Main.startMazeGame();
//                            SwingUtilities.invokeLater(() -> dispose());
//
//                    }
                    ChatUI.super.requestFocus();
                }
            });

            answer1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!dialog.isCompleted()) {
                        Main.playEffect("hover.wav", 0.2);
                        answer1.setBackground(answerBtnHoverColor);
                    }

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!dialog.isCompleted()) answer1.setBackground(answerBtnColor);

                }
            });

            answer2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!dialog.isCompleted()) {
                        Main.playEffect("hover.wav", 0.2);
                        answer2.setBackground(answerBtnHoverColor);
                    }

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!dialog.isCompleted()) answer2.setBackground(answerBtnColor);

                }
            });

        }
        Msg chosen = dialog.getUser()[0].isChosen() ? dialog.getUser()[0] : dialog.getUser()[1];
        if (dialog.isCompleted() && dialog.getId()<(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size()+additionalDialogs-1)) {
            MessagePanel message = new MessagePanel(false, chosen.getTexts()[0]);
            System.out.println("i added message: "+chosen.getTexts()[0]);
            chatArea.add(message, gbc);
            dialog.setId(Main.getProgress().getDialogCount());
//            System.out.println("cur lv: "+level+", cur dialog: "+dialog+" (id: "+dialog.getId()+")");
//            System.out.println("total dialogs: "+(chatData.yourChapter1.getDialogs().size()+chatData.yourChapter2.getDialogs().size())+"");

            Main.updateDialogCount();
        }


        SwingUtilities.invokeLater(() -> {
            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
        });
        Main.updateChatData(chatData);
        //chatArea.setPreferredSize(new Dimension(860, chatArea.getHeight()+100*(1+dialog.getGpt().getTexts().length)));
    }
    private void setMessages(){
        Main.getProgress().setDialogCount(0);
        //System.out.println(chatData.chapter.getDialogs());
        for (Dialog dialog : chatData.yourChapter1.getDialogs() ) {
            //if (dialog.isCompleted()) {
            addMessage(dialog, 0);
            //}
        }
        if (!Main.getProgress().isAlive()) {
            if (Main.getDeathReason().equals("mob")) addDeathMessage(0);
            else {
                addDeathMessage(1);
            }
        } else {
            if (Main.getProgress().getLv()>1) {
                if (chatData.yourChapter2.getDialogs().size()>0) {
                    for (Dialog dialog : chatData.yourChapter2.getDialogs() ) {
                        //if (dialog.isCompleted()) {
                        addMessage(dialog, 2);
                        //}
                    }
                }

            }
        }


    }
    public void addDeathMessage(int i){
        switch (i) {
            case 0:{
                Main.setDeathReason("mob");
                if (Main.getLanguage().equals("uk")) addMessage(chatData.deathDialogUkr, 1);
                else if (Main.getLanguage().equals("en")) addMessage(chatData.deathDialog, 1);
                break;
            }
            case 1:{
                Main.setDeathReason("rude");
                if (Main.getLanguage().equals("uk")) addMessage(chatData.deathDialog1Ukr, 1);
                else if (Main.getLanguage().equals("en")) addMessage(chatData.deathDialog1, 1);
                break;
            }
        }

        //Main.updateDialogCount();
        additionalDialogs++;
        Main.setAlive(false);
    }
    public void addFinishMazeMessage(){
        if (Main.getLanguage().equals("uk")) addMessage(chatData.finishMaze1ChapterUkr.get((int) Math.floor(Math.random() * chatData.finishMaze1ChapterUkr.size())), 1);
        else if (Main.getLanguage().equals("en")) addMessage(chatData.finishMaze1Chapter.get((int) Math.floor(Math.random() * chatData.finishMaze1Chapter.size())), 1);
        //Main.updateDialogCount();
        additionalDialogs++;
    }
    public void startChapter2(){
        if (Main.getLanguage().equals("uk")) {
            chatData.yourChapter2.getDialogs().add(chatData.chapter2Ukr.getDialogs().get(0));
            Main.updateChatData(chatData);
            addMessage(chatData.chapter2Ukr.getDialogs().get(0), 2);
        }
        else if (Main.getLanguage().equals("en")) {
            chatData.yourChapter2.getDialogs().add(chatData.chapter2.getDialogs().get(0));
            Main.updateChatData(chatData);
            addMessage(chatData.chapter2.getDialogs().get(0), 2);
        }
        //Main.updateDialogCount();
    }

    public JButton setAnswerOption(String text) {
        //JButton answer = new JButton(HTMLfyText(text));
        JButton answer = new JButton(text);
        answer.setFont(font16);
        answer.setBackground(answerBtnColor);
        answer.setAlignmentX(CENTER_ALIGNMENT);
        answer.setAlignmentY(CENTER_ALIGNMENT);
        answer.setBorderPainted(false);
        answer.setForeground(textColor);
        answer.setPreferredSize(new Dimension(430, 70));
        answer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return answer;
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
            avatarImageLabel.setVerticalAlignment(SwingConstants.TOP);


            //messageLabel = new JLabel(HTMLfyText(text));
            JTextArea messageLabel = new JTextArea(text);
            messageLabel.setAlignmentX(CENTER_ALIGNMENT);
            messageLabel.setEditable(false);
            messageLabel.setLineWrap(true);
            messageLabel.setWrapStyleWord(true);
            messageLabel.setFont(font16);
            messageLabel.setForeground(textColor);
            messageLabel.setOpaque(false);
            messageLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
            messageLabel.setPreferredSize(new Dimension(500, 100));
            //messageLabel.setMaximumSize(new Dimension(600 - 40, 200));
            messageLabel.setForeground(textColor);
            messageLabel.setFont(font16);
            //messageLabel.setVerticalAlignment(SwingConstants.CENTER);

            contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            contentPanel.add(avatarImageLabel, BorderLayout.WEST);
            contentPanel.add(messageLabel, BorderLayout.CENTER);
            //contentPanel.setMinimumSize(new Dimension(600, 100));

            add(contentPanel);

        }


    }
}



