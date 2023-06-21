import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsUI extends UI{
    private static final Color TITLE_COLOR = new Color(197, 198, 238);
    private static final Color BG_COLOR = new Color(1, 2, 26);
    JPanel titlePanel;
    JLabel titleLabel;
    JLabel title;
    JComboBox languageComboBox;
    JPanel languagePanel;
    JLabel languageLabel;
    GridBagConstraints c = new GridBagConstraints();

    public SettingsUI() {
        super("ChatGPT: become human", "bg-instruction.jpg", Main.mainMenuUI);
        Main.fetchProgress();
        super.backgroundPanel.setLayout(new GridBagLayout());
        setUpperPanel(this);

        // Заголовок
        //drawTitle();
        title = new JLabel(Main.getLanguage().equals("en") ? "Settings" : "Налаштування", SwingConstants.CENTER);
        title.setFont(titleFont);
        title.setForeground(titleColor);

        c.gridx = 0;
        c.gridy = 0;
        super.backgroundPanel.add(title, c);

        languageComboBox = new JComboBox<>(new String[]{"English", "Українська"});
        languageComboBox.setSelectedItem(Main.getLanguage().equals("en") ? "English" : "Українська"); // Set "English" as the default selection
        languageComboBox.setPreferredSize(new Dimension(100, 40));
        languageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLanguage = (String) languageComboBox.getSelectedItem();
                changeLanguage(selectedLanguage);
            }
        });

        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(25,50,0,50);  // Add some space between the buttons
        languagePanel = new JPanel(new BorderLayout());
        languagePanel.setBackground(transparent);

        languageLabel =  new JLabel(Main.getLanguage().equals("en") ? "Language:" : "Мова:");
        languageLabel.setFont(font22);
        languageLabel.setForeground(textColor);

        languagePanel.add(languageLabel, BorderLayout.NORTH);
        languagePanel.add(languageComboBox, BorderLayout.CENTER);
        super.backgroundPanel.add(languagePanel, c);







        add(super.upperPanel, BorderLayout.NORTH);
    }
    private void drawTitle() {
        titleLabel = new JLabel(Main.getLanguage().equals("uk") ? "Налаштування гри" : "Game settings", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(TITLE_COLOR);
        //titleLabel.setBorder(new LineBorder(TITLE_COLOR));
        titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBorder(new EmptyBorder(8,0,8, 0));
        titlePanel.setBackground(BG_COLOR);
        super.backgroundPanel.add(titlePanel, BorderLayout.NORTH);
    }
    private void changeLanguage(String selectedLanguage) {
        System.out.println("changeLanguage: " + selectedLanguage);
        Main.setLanguage(selectedLanguage.equals("English") ? "en" : "uk");
        Main.updateProgress();

        updateProgressData();

        Main.mainMenuUI.updateButtons();

    }
    @Override
    public void updateProgressData(){
        Main.fetchProgress();
        levelLabel.setText((Main.getLanguage().equals("en") ? "Current level: " : "Поточний рівень: ") +Main.getProgress().getLv());
        title.setText(Main.getLanguage().equals("en") ? "Settings" : "Налаштування");
        languageLabel.setText(Main.getLanguage().equals("en") ? "Language:" : "Мова:");

    }
    public static void main(String[] args) {
        new SettingsUI().setVisible(true);


    }
}
