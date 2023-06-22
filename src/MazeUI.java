import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MazeUI extends UI {
    static MazeGame game;
    JPanel heartsPanel = new HeartsPanel();

    public MazeGame getGame() {
        return game;
    }

    static boolean mazeCompleted = false;

    public void setMazeCompleted(boolean mazeCompleted) {
        MazeUI.mazeCompleted = mazeCompleted;
    }

    /**
     * Створює об'єкт MazeUI (вікно лабіринту) з заданими коефіцієнтами гучності (створюється у ChatUI).
     * @param volumeCoef коефіцієнт гучності фонової музики
     * @param volumeCoef1 коефіцієнт гучності ефектів
     */
    public MazeUI(double volumeCoef, double volumeCoef1){
        super("ChatGPT: become human", false);
        //System.out.println("Maze Game");
        game = new MazeGame(false, Main.getProgress().getLv(), volumeCoef, volumeCoef1);
        setThisUI();

    }

    /**
     * Створює об'єкт MazeUI (вікно лабіринту) з заданим рівнем гри та заданими коефіцієнтами гучності (створюється у ChooseMazeUI).
     * @param level рівень лабіринту
     * @param volumeCoef коефіцієнт гучності фонової музики
     * @param volumeCoef1 коефіцієнт гучності ефектів
     */
    public MazeUI(int level, double volumeCoef, double volumeCoef1){
        super("ChatGPT: become human", false);
        //System.out.println("Maze Game");
        game = new MazeGame(true, level, volumeCoef, volumeCoef1);
        setThisUI();

    }

    /**
     * Налаштовує інтерфейс вікна та панель гри в лабіринт
     */
    private void setThisUI() {
        setResizable(false);

        getContentPane().setLayout(new BorderLayout());
        setUpperPanel(this);
        heartsPanel.setBackground(null);
        super.upperPanel.add(heartsPanel, BorderLayout.CENTER);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmQuit();
                game.requestFocus();
            }
        });

        add(super.upperPanel, BorderLayout.NORTH);

        add(game, BorderLayout.CENTER);
        pack();


        // Register arrow key listeners to move the character
        game.addKeyListener(new ArrowKeyListener(game));
        game.requestFocus();
    }

    /**
     * Підтверджує вихід з лабіринту.
     * Виходить з нього, якщо гравець обрав "Так" у діалоговому вікні.
     */
    public void confirmQuit() {
        if (game.isMusicPlaying()) game.pauseMusic();
        MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                new MessageWindow(MazeUI.game, "Do you really want to quit maze?", "Quit maze", "Yes", "No")
                : new MessageWindow(MazeUI.game, "Ви точно хочете покинути лабіринт?", "Покинути лабіринт", "Так", "Ні");
        messageWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (messageWindow.isYes()) {
                    Main.mazeUI.updateUpperPanel();
                    if (Main.mazeUI.getGame().fromChooseMaze) {
                        quit(Main.chooseMazeUI);
                    }
                    else {
                        Main.chatUI.addFinishMazeMessage();
                        quit(Main.chatUI);
                    }
                    Main.playMusic();
                    //SwingUtilities.invokeLater(()->{Main.mazeUI.setVisible(false); Main.playMusic();});
                } else {

                    game.playMusic();
                    game.requestFocus();
                }
            }
        });
    }

    /**
     * Оновлює верхню панель головного вікна.
     */
    @Override
    public void updateUpperPanel() {
        levelLabel.setText((Main.getLanguage().equals("en") ? "Level " : "Рівень ") + game.getLevel());
        levelLabel.updateUI();
        heartsPanel.repaint();
    }

    /**
     * Перемальовує панель з сердечками (життями) гравця
     */
    public void repaintHeartsPanel() {
        if (upperPanel != null) {
            heartsPanel.repaint();
        }
    }

    static class HeartsPanel extends JPanel {
        Image heart;
        int heartSize = 20;
        int distance = 10;

        int y;
        int x;

        /**
         * Створює об'єкт HeartsPanel.
         * Завантажує зображення серця.
         */
        public HeartsPanel(){
            loadImage("heart.png");

        }

        /**
         * Малює компонент HeartsPanel.
         * @param g об'єкт <code>Graphics</code> для малювання
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ////System.out.println("width "+super.getWidth()+", height "+super.getHeight());
            x = (super.getWidth()-(heartSize+distance)*3)/2;
            y = (super.getHeight()-heartSize)/2;
            if (MazeGame.chatGPT.getLives()>0) g.drawImage(heart, x,y, heartSize, heartSize, null);
            if (MazeGame.chatGPT.getLives()>1) g.drawImage(heart, x+heartSize+distance,y, heartSize, heartSize, null);
            if (MazeGame.chatGPT.getLives()>2) g.drawImage(heart, x+(heartSize+distance)*2,y, heartSize, heartSize, null);
        }

        /**
         * Завантажує зображення з файлу.
         * @param imageName ім'я файлу зображення
         */
        private void loadImage(String imageName) {
            ImageIcon icon = new ImageIcon("images/"+imageName); // Replace with the path to your character image file
            heart = icon.getImage();
        }

    }

}

