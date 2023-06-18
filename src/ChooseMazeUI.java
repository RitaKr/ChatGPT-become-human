import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class ChooseMazeUI  extends UI  {
    private JPanel buttonsPanel;
    ProgressData progressData;
    MessageWindow messageWindow;
    public void updateProgressData(){
        Main.fetchProgress();
        progressData = Main.getProgress();
        buttonsPanel.removeAll();
        setButtons();
        levelLabel.setText("Current level: "+progressData.getLv());
        levelLabel.updateUI();
    }

    public void setAll(){
        super.backgroundPanel.setLayout(new GridBagLayout());
        setUpperPanel(this);

        // create and customize the label
        JLabel title = new JLabel("Choose maze to play", SwingConstants.CENTER);
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
        String[] buttonImages = {(progressData.getLv()>=1 ? "lv1.png" : "lv1no.png"), (progressData.getLv()>=2 ? "lv2.png" : "lv2no.png"), (progressData.getLv()>=3 ? "lv3.png" : "lv3no.png")};
        for (int i = 0; i < buttonImages.length; i++) {
            JPanel buttonContainer = new JPanel(new BorderLayout());


            JLabel buttonLabel = new JLabel("Level " + (i+1), SwingConstants.CENTER);
            buttonLabel.setFont(new Font("Arial", Font.BOLD, 24));
            buttonLabel.setForeground(Color.WHITE);
            buttonLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

            JButton button = new JButton(new ImageIcon("images/"+buttonImages[i]));
            button.setBackground(null);
            button.setBorderPainted(false);
            button.setBorder(null);
            button.setMargin(new Insets(0,0,0,0));

            button.setCursor(progressData.getLv()>i ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) : Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

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
                    switch (finalI){
                        case 0: {
                            if (progressData.getLv()>=1) {
                                Main.startMazeGame(1);
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                messageWindow = new MessageWindow(ChooseMazeUI.this, "You haven't unlocked this level yet! Try too chat with GPT more", "level unavailable", "OK");
                            }
                            break;
                        }
                        case 1: {
                            if (progressData.getLv()>=2) {
                                Main.startMazeGame(2);
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {
                                messageWindow = new MessageWindow(ChooseMazeUI.this, "You haven't unlocked this level yet! You need to complete level 1 and chat chapter 2 first", "level unavailable", "OK");

                            }
                            break;
                        }
                        case 2: {
                            if (progressData.getLv()>=3) {
                                Main.startMazeGame(3);
                                SwingUtilities.invokeLater(() -> dispose());
                            } else {

                                messageWindow = new MessageWindow(ChooseMazeUI.this, "You haven't unlocked this level yet! You need to complete level 2 and chat chapter 3 first", "level unavailable", "OK");

                            }
                            break;
                        }
                    }

                    requestFocusInWindow();
                }
            });

        }
    }
    public ChooseMazeUI() {
        super("ChatGPT: become human", "bg-menu.png", Main.mainMenuUI);
        progressData = Main.getProgress();
        setAll();

        // Make JFrame visible
        //setVisible(true);
    }


}
