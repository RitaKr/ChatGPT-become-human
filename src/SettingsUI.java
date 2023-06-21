import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    JPanel languagePanel, volumePanel, volumePanel1;
    JLabel languageLabel, volumeLabel, volumeLabel1;
    JSlider volumeSlider, volumeSlider1;
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
        languageComboBox.setForeground(textColor); // Set the color of the slider track and thumb
        languageComboBox.setBackground(BG_COLOR);
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

        languageLabel =  new JLabel(Main.getLanguage().equals("en") ? "Language:" : "Мова:", SwingConstants.CENTER);
        languageLabel.setFont(font22);
        languageLabel.setForeground(textColor);

        languagePanel.add(languageLabel, BorderLayout.NORTH);
        languagePanel.add(languageComboBox, BorderLayout.CENTER);
        super.backgroundPanel.add(languagePanel, c);

        c.gridy = 2;
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(false);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setForeground(textColor); // Set the color of the slider track and thumb
        volumeSlider.setBackground(BG_COLOR);
        volumeSlider.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add a change listener to respond to volume changes
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double volume = volumeSlider.getValue() / 100.0;
                Main.updateVolume(volume*0.2);
                System.out.println("Volume: " + volume);
                // Add your logic here to handle the volume change
            }
        });

        volumePanel = new JPanel(new BorderLayout());
        volumePanel.setBackground(transparent);

        volumeLabel =  new JLabel(Main.getLanguage().equals("en") ? "Music volume:" : "Гучність музики:", SwingConstants.CENTER);
        volumeLabel.setFont(font22);
        volumeLabel.setForeground(textColor);

        volumePanel.add(volumeLabel, BorderLayout.NORTH);
        volumePanel.add(volumeSlider, BorderLayout.CENTER);
        super.backgroundPanel.add(volumePanel, c);


        c.gridy = 3;
        volumeSlider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider1.setMajorTickSpacing(20);
        volumeSlider1.setMinorTickSpacing(5);
        volumeSlider1.setPaintTicks(false);
        volumeSlider1.setPaintLabels(true);
        volumeSlider1.setForeground(textColor); // Set the color of the slider track and thumb
        volumeSlider1.setBackground(BG_COLOR);
        volumeSlider1.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add a change listener to respond to volume changes
        volumeSlider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double volume = volumeSlider1.getValue() / 100.0;
                Main.volumeCoef1 =  volume;
                System.out.println("Volume: " + volume);
                // Add your logic here to handle the volume change
            }
        });

        volumePanel1 = new JPanel(new BorderLayout());
        volumePanel1.setBackground(transparent);

        volumeLabel1 =  new JLabel(Main.getLanguage().equals("en") ? "Sounds volume:" : "Гучність звуків:", SwingConstants.CENTER);
        volumeLabel1.setFont(font22);
        volumeLabel1.setForeground(textColor);

        volumePanel1.add(volumeLabel1, BorderLayout.NORTH);
        volumePanel1.add(volumeSlider1, BorderLayout.CENTER);
        super.backgroundPanel.add(volumePanel1, c);


        add(super.upperPanel, BorderLayout.NORTH);
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
        volumeLabel.setText(Main.getLanguage().equals("en") ? "Music volume:" : "Гучність музики:");
        volumeLabel1.setText(Main.getLanguage().equals("en") ? "Sounds volume:" : "Гучність звуків:");
    }
    public static void main(String[] args) {
        new SettingsUI().setVisible(true);


    }
}
