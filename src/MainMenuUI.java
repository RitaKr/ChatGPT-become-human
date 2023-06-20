import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuUI extends UI {
    private JPanel backgroundPanel;
    JLabel title;
    final static Color bgColor = new Color(138, 19, 178);
    Image backgroundImage;
    Image startImage = new ImageIcon("images/start.png").getImage();
    Image buttonImage = new ImageIcon("images/button-pattern.png").getImage();
    Image buttonHoverImage = new ImageIcon("images/button-pattern-white.png").getImage();
    Image redButtonImage = new ImageIcon("images/button-pattern-red.png").getImage();
    Image startHoverImage = new ImageIcon("images/start-white.png").getImage();
    JComboBox languageComboBox = new JComboBox();

    GridBagConstraints c = new GridBagConstraints();
    private Font font = new Font("Arial", Font.BOLD, 22);
    private Color buttonColor = new Color(45, 114, 255);
    private Color buttonColor1 = new Color(222, 68, 68);
    private Color buttonColorHover = new Color(225, 225, 255);
    int btnWidth = 280;
    int btnHeight = 90;


    public MainMenuUI() {
        super("ChatGPT: become human", "bg-menu4.gif");

        setThisUI();
        // Make JFrame visible
        //setVisible(true);
    }
    private void setThisUI(){
        Main.fetchProgress();
        super.backgroundPanel.setLayout(new GridBagLayout());
//
//
//        languageComboBox = new JComboBox<>(new String[]{"English", "Українська"});
//        languageComboBox.setSelectedItem("English"); // Set "English" as the default selection
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.anchor = GridBagConstraints.NORTHEAST; // Установите якорь в верхний правый угол
//        constraints.gridx = GridBagConstraints.RELATIVE; // Установите относительное положение по горизонтали
//        constraints.gridy = 0; // Установите положение по вертикали (в данном случае 0 для верхней строки)
//        constraints.insets = new Insets(10, 10, 10, 10); // Установите отступы, если необходимо
//
//        // Добавьте элемент на панель с использованием GridBagConstraints
//        super.backgroundPanel.add(languageComboBox, constraints);
//        languageComboBox.setBounds(frameWidth-120, 20, 100, 40);
//        languageComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String selectedLanguage = (String) languageComboBox.getSelectedItem();
//                changeLanguage(selectedLanguage);
//            }
//        });

        //super.backgroundPanel.setLayout(new GridBagLayout());
        // create and customize the label
        title = new JLabel("ChatGPT: Become Human", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setForeground(titleColor);

        c.gridx = 0;
        c.gridy = 0;
        super.backgroundPanel.add(title, c);

        JButton startButton = createButton("", buttonColor, startImage, startHoverImage);
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(25,100,0,100);  // Add some space between the buttons
        super.backgroundPanel.add(startButton, c);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //Main.playEffect("click.wav", 0.2);
                Main.chatUI.updateProgressData();
                Main.chatUI.setVisible(true);
                Main.chatUI.requestFocus();
                SwingUtilities.invokeLater(()->dispose());
            }
        });

        // create and customize the buttons
        String[] buttonNames = Main.getLanguage().equals("en") ? new String[]{"Instruction", "Mazes", "Reset progress"} : new String[]{"Інструкція", "Лабіринти", "Скинути прогрес"};
        for (int i = 1; i <= buttonNames.length; i++) {
            JButton button = createButton(buttonNames[i-1],(i<buttonNames.length ? buttonColor : buttonColor1), (i<buttonNames.length ? buttonImage : redButtonImage), buttonHoverImage);

            c.gridy = i + 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(10,100,0,100);  // Add some space between the buttons
            super.backgroundPanel.add(button, c);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Main.fetchProgress();
                    // Main.playEffect("click.wav", 0.2);
                    switch (finalI){
                        case 1: {
                            Main.instructionUI.updateProgressData();
                            Main.instructionUI.setVisible(true);
                            Main.instructionUI.requestFocus();
                            SwingUtilities.invokeLater(()->dispose());
                            break;
                        }
                        case 2: {
                            Main.chooseMazeUI.updateProgressData();
                            Main.chooseMazeUI.setVisible(true);
                            Main.chooseMazeUI.requestFocus();
                            SwingUtilities.invokeLater(()->dispose());
                            break;
                        }
                        case 3: {
                            MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                                    new MessageWindow(MainMenuUI.this, "Are you sure you want to reset the progress? You will need to open the program again", "Reset progress", "Yes", "No")
                                    : new MessageWindow(MainMenuUI.this, "Ви впевнені, що хочете скинути прогрес? Вам доведеться відкрити програму знову", "Скинути прогрес", "Так", "Ні");;
                            messageWindow.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    if (messageWindow.isYes()) {
                                        Main.resetProgress();
                                        dispose();
                                        System.exit(0);
                                    }
                                }
                            });

                            break;
                        }
                    }

                }
            });

        }
    }

    private void changeLanguage(String selectedLanguage) {
        System.out.println("changeLanguage: " + selectedLanguage);
        Main.setLanguage(selectedLanguage.equals("English") ? "en" : "uk");
//        super.remove(super.backgroundPanel);
        super.setUI();
        setThisUI();
    }


}
