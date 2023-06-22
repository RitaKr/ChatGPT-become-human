import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class ChooseMazeUI  extends UI  {
    private JPanel buttonsPanel;
    ProgressData progressData;
    MessageWindow messageWindow;
    JLabel title;
    @Override
    public void updateProgressData(){
        Main.fetchProgress();
        progressData = Main.getProgress();
        title.setText(Main.getLanguage().equals("uk") ? "Оберіть лабіринт для проходження" : "Chose maze to play");
        buttonsPanel.removeAll();
        setButtons();
        levelLabel.setText((Main.getLanguage().equals("en") ? "Current level: " : "Поточний рівень: ")+progressData.getLv());
        levelLabel.updateUI();
    }

    public void setAll(){
        super.backgroundPanel.setLayout(new GridBagLayout());
        setUpperPanel(this);

        // create and customize the label
        title = new JLabel(Main.getLanguage().equals("en") ? "Choose maze to play" :"Оберіть лабіринт для гри", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setForeground(titleColor);

        c.gridx = 0;
        c.gridy = 0;
        super.backgroundPanel.add(title, c);

        // create and customize the buttons
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setBackground(transparent);
        setButtons();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        super.backgroundPanel.add(buttonsPanel, c);


        add(super.upperPanel, BorderLayout.NORTH);
        //add(super.backgroundPanel, BorderLayout.CENTER);
    }
    private void setButtons(){
        String[] buttonImages = {((progressData.getLv()>=1 && progressData.getChatData().yourChapter1.isCompleted()) ? "lv1.png" : "lv1no.png"), ((progressData.getLv()>=2 && progressData.getChatData().yourChapter2.isCompleted())? "lv2.png" : "lv2no.png"), (progressData.isFinaleUnlocked()  ? "lv3.png" : "lv3no.png")};
        for (int i = 0; i < buttonImages.length; i++) {
            JPanel buttonContainer = new JPanel(new BorderLayout());


            JLabel buttonLabel = new JLabel((Main.getLanguage().equals("en") ? "Level " : "Рівень ") + (i+1), SwingConstants.CENTER);
            buttonLabel.setFont(font22);
            buttonLabel.setForeground(Color.WHITE);
            buttonLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

            JButton button = new JButton(new ImageIcon("images/"+buttonImages[i]));
            button.setBackground(null);
            button.setBorderPainted(false);
            button.setBorder(null);
            button.setMargin(new Insets(0,0,0,0));

            if ((progressData.getLv()>=1 && progressData.getChatData().yourChapter1.isCompleted() && i==0) ||
                    (progressData.getLv()>=2 && progressData.getChatData().yourChapter2.isCompleted() && i==1)||
                    (progressData.isFinaleUnlocked() && i==2))
            button.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            c.gridx = i + 1;
            c.fill = GridBagConstraints.VERTICAL;
            c.insets = new Insets(20,0,20,0);  // Add some space between the buttons
            buttonContainer.add(button, BorderLayout.CENTER);
            buttonContainer.add(buttonLabel, BorderLayout.SOUTH);
            buttonContainer.setBorder(new EmptyBorder(10, 10, 10, 10));

            buttonContainer.setBackground(transparent);
            buttonsPanel.add(buttonContainer, c);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.playEffect("click.wav", 0.2);
                    switch (finalI){
                        case 0: {
                            if (progressData.getLv()>=1 && progressData.getChatData().yourChapter1.isCompleted()) {
                                Main.startMazeGame(1);
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                messageWindow = Main.getLanguage().equals("en") ?
                                        new MessageWindow(ChooseMazeUI.this, "You haven't unlocked this level yet! Try too chat with GPT more", "level unavailable", "OK")
                                : new MessageWindow(ChooseMazeUI.this, "Ви ще не розблокували цей рівень! Спробуйте більше поспілкуватись з GPT", "Рівень не доступний", "ОК");
                            }
                            break;
                        }
                        case 1: {
                            if (progressData.getLv()>=2 && progressData.getChatData().yourChapter2.isCompleted()) {
                                Main.startMazeGame(2);
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                messageWindow = Main.getLanguage().equals("en") ?
                                        new MessageWindow(ChooseMazeUI.this, "You haven't unlocked this level yet! You need to complete level 1 and chat chapter 2 first", "level unavailable", "OK")
                                        : new MessageWindow(ChooseMazeUI.this, "Ви ще не розблокували цей рівень! Спершу Ви маєте пройти 1-й рівень лабіринту та 2-й розділ чату", "Рівень не доступний", "ОК");

                            }
                            break;
                        }
                        case 2: {
                            if (progressData.isFinaleUnlocked() ) {
                                Main.startMazeGame(3);
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                messageWindow = Main.getLanguage().equals("en") ?
                                        new MessageWindow(ChooseMazeUI.this, "You haven't unlocked this level yet! You need to complete level 2 and chat chapter 3 first", "level unavailable", "OK")
                                        : new MessageWindow(ChooseMazeUI.this, "Ви ще не розблокували цей рівень! Спершу Ви маєте пройти 2-й рівень лабіринту та 3-й розділ чату", "Рівень не доступний", "ОК");

                            }
                            break;
                        }
                    }

                    requestFocusInWindow();
                }
            });
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (progressData.getLv()>finalI) Main.playEffect("hover.wav", 0.2);

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    requestFocusInWindow();
                }
            });

        }
    }
    public ChooseMazeUI() {
        super("ChatGPT: become human", "bg-instruction.jpg", Main.mainMenuUI);
        progressData = Main.getProgress();
        setAll();

        // Make JFrame visible
        //setVisible(true);
    }


}
