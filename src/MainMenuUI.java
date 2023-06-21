import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    JPanel buttonsPanel;

    GridBagConstraints c = new GridBagConstraints();
    private Font font = new Font("Arial", Font.BOLD, 22);
    private Color buttonColor = new Color(45, 114, 255);
    private Color buttonColor1 = new Color(222, 68, 68);
    private Color buttonColorHover = new Color(225, 225, 255);
    int btnWidth = 260;
    int btnHeight = 80;


    public MainMenuUI() {
        super("ChatGPT: become human", "bg-menu4.gif");

        setThisUI();
        // Make JFrame visible
        //setVisible(true);
    }
    private void setThisUI(){
        Main.fetchProgress();
        super.backgroundPanel.setLayout(new GridBagLayout());

        //super.backgroundPanel.setLayout(new GridBagLayout());
        // create and customize the label
        title = new JLabel("ChatGPT: Become Human", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setForeground(titleColor);

        c.gridx = 0;
        c.gridy = 0;
        super.backgroundPanel.add(title, c);

        buttonsPanel = new JPanel(new GridLayout(5, 1, 5, 20));
        buttonsPanel.setBackground(transparent);
        buttonsPanel.setPreferredSize(new Dimension(btnWidth, 420));
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(25,160,0,160);
        setButtons();
        super.backgroundPanel.add(buttonsPanel, c);


    }

  public void setButtons(){
      JButton startButton = createButton("", buttonColor, startImage, startHoverImage, btnWidth, btnHeight);
      buttonsPanel.add(startButton, c);
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
      String[] buttonNames = Main.getLanguage().equals("en") ? new String[]{"Instruction", "Mazes", "Settings", "Reset progress"} : new String[]{"Інструкція", "Лабіринти", "Налаштування", "Скинути прогрес"};
      for (int i = 1; i <= buttonNames.length; i++) {
          System.out.println(buttonNames.length);
          JButton button = createButton(buttonNames[i - 1], (i < buttonNames.length ? buttonColor : buttonColor1), (i < buttonNames.length ? buttonImage : redButtonImage), buttonHoverImage, btnWidth, btnHeight);
//         button.setBorderPainted(true);
//         button.setBorder(BorderFactory.createLineBorder(Color.white, 2));
          buttonsPanel.add(button, c);
          int finalI = i;
          button.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  Main.fetchProgress();
                  // Main.playEffect("click.wav", 0.2);
                  switch (finalI) {
                      case 1: {
                          Main.instructionUI.updateProgressData();
                          Main.instructionUI.setVisible(true);
                          Main.instructionUI.requestFocus();
                          SwingUtilities.invokeLater(() -> dispose());
                          break;
                      }
                      case 2: {
                          Main.chooseMazeUI.updateProgressData();
                          Main.chooseMazeUI.setVisible(true);
                          Main.chooseMazeUI.requestFocus();
                          SwingUtilities.invokeLater(() -> dispose());
                          break;
                      }
                      case 3: {
                          // код для відкриття вікна налаштувань
                          Main.settingsUI.updateProgressData();
                          Main.settingsUI.setVisible(true);
                          Main.settingsUI.requestFocus();
                          SwingUtilities.invokeLater(() -> dispose());
                          break;

                      }
                      case 4: {
                          MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                                  new MessageWindow(MainMenuUI.this, "Are you sure you want to reset the progress? You will need to open the program again", "Reset progress", "Yes", "No")
                                  : new MessageWindow(MainMenuUI.this, "Ви впевнені, що хочете скинути прогрес? Вам доведеться відкрити програму знову", "Скинути прогрес", "Так", "Ні");
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

    public void updateButtons(){
        Main.fetchProgress();
            buttonsPanel.removeAll();
            setButtons();
    }
}
