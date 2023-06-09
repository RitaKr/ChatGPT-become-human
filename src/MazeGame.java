import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class MazeGame extends JPanel {

    private Maze settings;
    Maze.Cell[][] maze;
    private static int mazeWidth = Maze.cols * Maze.cellSize + (Maze.cols + 1) * Maze.wallSize;
    private static int mazeHeight = Maze.rows * Maze.cellSize + (Maze.rows + 1) * Maze.wallSize;
    private static int menuHeight = 40;
    private boolean gameOver = false;
    private boolean mazeCompleted = false;
    private int level;

    MediaPlayer mediaPlayer;

    public int getLevel() {
        return level;
    }

    public static Character chatGPT;
    Mob mob1;
    Mob mob2;
    Mob mob3;
    Teleport teleport1;
    Teleport teleport2;
    Teleport teleport3;
    Teleport teleport4;
    SlidingDoor slidingDoor;
    Item slideDoorButton;
    RotatingDoor rotatingDoor;
    Item key;
    Item finish;
    Item quiz1Item, quiz2Item, quiz3Item;
    double volumeCoef=1.0;
    double volumeCoef1=1.0;

    static ArrayList<Quiz> quizes =  new ArrayList<Quiz>(Arrays.asList(
            new Quiz("Which of these methods is used to output text to the console in Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. System.out.display()"), new Answer("B. System.out.print()", true), new Answer("C. Console.write()"), new Answer("D. Print.console()")))),

            new Quiz("Which of the following operators is used to compare two values for equality in Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. =="), new Answer("B. ="), new Answer("C. equals()"), new Answer("D. There is more than 1 correct answer", true)))),

            new Quiz("How is the superclass constructor called in Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. super()", true), new Answer("B. this()"), new Answer("C. extends()"), new Answer("D. super")))),

            new Quiz("What keywords in Java are used to handle exceptions?",
                    new ArrayList<>(Arrays.asList(new Answer("A. If / Else"), new Answer("B. Throw / Catch"), new Answer("C. Error / Exception"), new Answer("D. Try / Catch", true)))),

            new Quiz("Which Java keyword is used to instantiate a class?",
                    new ArrayList<>(Arrays.asList(new Answer("A. class"), new Answer("B. new", true), new Answer("C. this"), new Answer("D. instance")))),

            new Quiz("What Java keyword is used to follow?",
                    new ArrayList<>(Arrays.asList(new Answer("A. inherit"), new Answer("B. superclass"), new Answer("C. extends", true), new Answer("D. implements")))),

            new Quiz("What is the name of the mechanism that allows one object to acquire the properties of another object in Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. polymorphism"), new Answer("B. encapsulation"), new Answer("C. follow", true), new Answer("D. abstraction")))),

            new Quiz("What Java keyword is used to install a class?",
                    new ArrayList<>(Arrays.asList(new Answer("A. class"), new Answer("B. new", true), new Answer("C. this"), new Answer("D. instance")))),

            new Quiz("What happens when you try to divide a number by 0 in Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. ArithmeticException", true), new Answer("B. return 0"), new Answer("C. The largest positive number is returned"), new Answer("D. Nothing")))),

            new Quiz("Which of these classes is part of the Java standard library?",
                    new ArrayList<>(Arrays.asList(new Answer("A. String", true), new Answer("B. TextBox"), new Answer("C. Label"), new Answer("D. Button")))),

            new Quiz("Which access modifier means that an element is accessible from any other class?",
                    new ArrayList<>(Arrays.asList(new Answer("A. private"), new Answer("B. public", true), new Answer("C. protected"), new Answer("D. None of these")))),

            new Quiz("Which of these keywords is used to indicate no meaning?",
                    new ArrayList<>(Arrays.asList(new Answer("A. null", true), new Answer("B. none"), new Answer("C. NaN"), new Answer("D. void")))),

            new Quiz("Which of these operators is a ternary operator?",
                    new ArrayList<>(Arrays.asList(new Answer("A. +"), new Answer("B. -"), new Answer("C. %"), new Answer("D. ?", true)))),

            new Quiz("How is an array of integers declared in Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. int array[];", true), new Answer("B. array int[];"), new Answer("C. int array();"), new Answer("D. array<int> a;")))),

            new Quiz("Which of these modifiers allows you to restrict access to class members only to the class in which they are declared?",
                    new ArrayList<>(Arrays.asList(new Answer("A. public"), new Answer("B. private", true), new Answer("C. protected"), new Answer("D. default"))))

            )
    );

    static ArrayList<Quiz> quizesUkr =  new ArrayList<Quiz>(Arrays.asList(
            new Quiz("Який з цих методів використовується для виводу тексту в консоль в Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. System.out.display()"), new Answer("B. System.out.print()", true), new Answer("C. Console.write()"), new Answer("D. Print.console()")))),

            new Quiz("Який з наступних операторів використовується для порівняння двох значень на рівність в Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. =="), new Answer("B. ="), new Answer("C. equals()"), new Answer("D. Декілька правильних відповідей", true)))),

            new Quiz("Як у Java викликається конструктор суперкласу?",
                 new ArrayList<>(Arrays.asList(new Answer("A. super()", true), new Answer("B. this()"), new Answer("C. extends()"), new Answer("D. super")))),

            new Quiz("Які ключові слова в Java використовуються для обробки винятків?",
                             new ArrayList<>(Arrays.asList(new Answer("A. If / Else"), new Answer("B. Throw / Catch"), new Answer("C. Error / Exception"), new Answer("D. Try / Catch", true)))),

            new Quiz("Яке ключове слово в Java використовується для створення екземпляра класу?",
                             new ArrayList<>(Arrays.asList(new Answer("A. class"), new Answer("B. new", true), new Answer("C. this"), new Answer("D. instance")))),

            new Quiz("Яке ключове слово в Java використовується для наслідування?",
                    new ArrayList<>(Arrays.asList(new Answer("A. inherit"), new Answer("B. superclass"), new Answer("C. extends", true), new Answer("D. implements")))),

            new Quiz("Як називається механізм, що дозволяє одному об'єкту набувати властивостей іншого об'єкта в Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. Поліморфізм"), new Answer("B. Капсулювання"), new Answer("C. Наслідування", true), new Answer("D. Абстракція")))),

            new Quiz("Яке ключове слово в Java використовується для створення екземпляра класу?",
                    new ArrayList<>(Arrays.asList(new Answer("A. class"), new Answer("B. new", true), new Answer("C. this"), new Answer("D. instance")))),

            new Quiz("Що відбувається при спробі поділити число на 0 в Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. Виникає помилка ArithmeticException", true), new Answer("B. Повертається 0"), new Answer("C. Повертається найбільше додатне число"), new Answer("D. Нічого")))),

            new Quiz("Який із цих класів є частиною стандартної бібліотеки Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. String", true), new Answer("B. TextBox"), new Answer("C. Label"), new Answer("D. Button")))),

            new Quiz("Який модифікатор доступу означає, що елемент доступний з будь-якого іншого класу?",
                    new ArrayList<>(Arrays.asList(new Answer("A. private"), new Answer("B. public", true), new Answer("C. protected"), new Answer("D. None of these")))),

            new Quiz("Який із цих ключових слів використовується для вказівки на відсутність значення?",
                    new ArrayList<>(Arrays.asList(new Answer("A. null", true), new Answer("B. none"), new Answer("C. NaN"), new Answer("D. void")))),

            new Quiz("Який із цих операторів є тернарним оператором?",
                    new ArrayList<>(Arrays.asList(new Answer("A. +"), new Answer("B. -"), new Answer("C. %"), new Answer("D. ?", true)))),

            new Quiz("Як оголошується масив цілих чисел в Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. int array[];", true), new Answer("B. array int[];"), new Answer("C. int array();"), new Answer("D. array<int> a;")))),

            new Quiz("Який із цих модифікаторів дозволяє обмежити доступ до членів класу тільки для класу, в якому вони оголошені?",
                    new ArrayList<>(Arrays.asList(new Answer("A. public"), new Answer("B. private", true), new Answer("C. protected"), new Answer("D. default"))))

            )
    );
    QuizWindow quizWindow;
    Quiz quiz3;
    Quiz quiz1;
    Quiz quiz2;

    private Image backgroundImage;
    //private ImageIcon backgroundImage;
    boolean fromChooseMaze;

    public Character getChatGPT() {
        return chatGPT;
    }

    /**
     * Створює новий об'єкт гри в лабіринт.
     *
     * @param fromChooseMaze прапорець, що позначає, чи була гра запущена зі вікна вибору лабіринту
     * @param level          рівень лабіринту (якщо гра запущена з вікна вибором лабіринту, якщо ні - рівень підтягується з прогресу)
     * @param volumeCoef     коефіцієнт гучності фонової музики
     * @param volumeCoef1    коефіцієнт гучності звукових ефектів
     */
    public MazeGame(boolean fromChooseMaze, int level, double volumeCoef, double volumeCoef1) {
        this.volumeCoef= volumeCoef;
        this.volumeCoef1= volumeCoef1;
        this.fromChooseMaze = fromChooseMaze;
        if (fromChooseMaze) {
            this.level = level;
            setScene(level);
        } else  {
            this.level = Main.getProgress().getLv();
            setScene();
        }
    }
    /**
     * Встановлює сцену гри з вказаним рівнем лабіринту.
     *
     * @param level рівень лабіринту
     */
    public void setScene(int level) {
        setMaze(level);
    }

    /**
     * Встановлює сцену гри з використанням поточного рівня лабіринту.
     */
    public void setScene() {
        Main.setLevel(level);
        setMaze(level);

    }

    /**
     * Встановлює сцену гри з використанням вказаного рівня лабіринту.
     *
     * @param level рівень лабіринту
     */
    private void setMaze(int level) {
        gameOver = false;
        settings = new Maze(level);
        maze = settings.maze;

        switch (level) {
            case 1 ->{
                loadBackgroundImage("bg1-blur.gif");
                setMusic("music/marjim-dizzy.mp3", 0.05);
                chatGPT = new Character(8, 5, 0, false);

                mob1 = new Mob("virus.png", 2, 4, 3, 1);
                mob2 = new Mob("virus.png", 3, 2, 5, 0);
                mob3 = null;
                slidingDoor = new SlidingDoor(settings, 7, 4, 5,  4, true);
                rotatingDoor = new RotatingDoor(settings, 0, 5, Side.LEFT, true);

                teleport1 = new Teleport("teleport.png",3, 0, 3 , 6, Side.TOP);
                teleport2 = new Teleport("teleport.png",3, 6, 3, 0, Side.TOP);
                teleport3 = null;
                teleport4 = null;

                slideDoorButton = new Item("doorButton.png",5, 5, 60, 60);
                key = new Item("key.png",0, 7, 70, 30);
                finish = new Item("finish.png",0, 0, 90, 80);
                quiz1Item = new Item("quiz.png",4, 0, 40, 60);
                quiz2Item = null;
                quiz3Item = null;

                quiz1 = getRandomQuiz();
            }
            case 2 -> {
                loadBackgroundImage("bg2-blur.gif");
                setMusic("music/marjim-invincible.mp3", 0.05);
                chatGPT = new Character(8, 5, 0, false);

                mob1 = new Mob("virus.png", 4, 0, 0, 1);
                mob2 = new Mob("virus.png", 5, 3, 2, 1);
                mob3 = null;

                slidingDoor = new SlidingDoor(settings, 1, 4, 3,  4, true);
                rotatingDoor = new RotatingDoor(settings, 4, 7, Side.BOTTOM, false);

                teleport1 = new Teleport("teleport.png",1, 1, 0 , 7, Side.BOTTOM);
                teleport2 = new Teleport("teleport.png",0, 7, 1, 1, Side.BOTTOM);
                teleport3 = new Teleport("teleport2.png",4, 4, 5, 2, Side.LEFT);
                teleport4 = new Teleport("teleport2.png",5, 2, 4, 4, Side.BOTTOM);

                slideDoorButton = new Item("doorButton.png",1, 3, 60, 60);
                key = new Item("key.png",4, 3, 70, 30);
                finish = new Item("finish.png",5, 1, 90, 80);
                quiz1Item = new Item("quiz.png",3, 1, 40, 60);
                quiz2Item = new Item("quiz.png",2, 5, 40, 60);
                quiz3Item = null;

                quiz1 = getRandomQuiz();
                quiz2 = getRandomQuiz();
                }
            case 3 -> {
                loadBackgroundImage("bg3-blur.gif");
                setMusic("music/marjim-go-big.mp3", 0.05);
                chatGPT = new Character(8, 5, 0, false);

                mob1 = new Mob("virus.png", 4, 4, 0, 0);
                mob2 = new Mob("virus.png", 4, 0, 2, 1);
                mob3 = new Mob("virus.png", 5, 5, 6, 0);

                slidingDoor = new SlidingDoor(settings, 6, 1, 5,  1, true);
                rotatingDoor = new RotatingDoor(settings, 3, 3, Side.LEFT, true);

                teleport1 = new Teleport("teleport.png",4, 2, 3 , 7, Side.BOTTOM);
                teleport2 = new Teleport("teleport.png",3, 7, 4, 2, Side.LEFT);
                teleport3 = new Teleport("teleport2.png",1, 1, 2, 5, Side.LEFT);
                teleport4 = new Teleport("teleport2.png",2, 5, 1, 1, Side.TOP);

                slideDoorButton = new Item("doorButton.png",3, 2, 60, 60);
                key = new Item("key.png",5, 7, 70, 30);
                finish = new Item("finish.png",4, 7, 90, 80);
                quiz1Item = new Item("quiz.png",4, 4, 40, 60);
                quiz2Item = new Item("quiz.png",2, 1, 40, 60);
                quiz3Item = new Item("quiz.png",1, 2, 40, 60);

                quiz1 = getRandomQuiz();
                quiz2 = getRandomQuiz();
                quiz3 = getRandomQuiz();
            }
        }
        repaint();
        playMusic();
    }

    /**
     * Генерує випадкове незавершене питання зі списку питань.
     *
     * @return незавершене питання типу Quiz
     */
    public Quiz getRandomQuiz(){
        java.util.List<Quiz> uncompletedQuizes = Main.getLanguage().equals("en") ?
                quizes.stream().filter(quiz -> !quiz.isCompleted()).toList()
                : quizesUkr.stream().filter(quiz -> !quiz.isCompleted()).toList();
        return uncompletedQuizes.get((int)Math.floor(Math.random() * uncompletedQuizes.size()));
    }

    /**
     * Малює фонове зображення на графічному контексті.
     *
     * @param g графічний контекст
     */
    private void drawBackground(Graphics g){
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Завантажує фонове зображення з заданою назвою зображення.
     *
     * @param imageName назва зображення
     */
    private void loadBackgroundImage(String imageName) {
        String imagePath = "images/" + imageName;

        if (imagePath.toLowerCase().endsWith(".gif")) {
            backgroundImage = Toolkit.getDefaultToolkit().createImage(imagePath);
        } else {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }
    }


    /**
     * Динамічно перемальовує компонент з графічним контекстом g, зокрема реалізує рух мобів.
     *
     * @param g графічний контекст
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Main.mazeUI.updateUpperPanel();

        drawBackground(g);
        // Draw the maze layout
        g.setColor(settings.wallsColor);

        drawMaze(g);

        drawStaticItems(g);
        drawMovableItems(g);

        // Draw the character
        drawCharacters(g);
        try {
            Thread.sleep(15); // Adjust the delay duration as needed (in milliseconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mob1!=null) moveMob(mob1, mob1.getSpeed());
        if (mob2!=null) moveMob(mob2, mob2.getSpeed());
        if (mob3!=null) moveMob(mob3, mob3.getSpeed());

    }

    /**
     * Малює лабіринт на графічному контексті g.
     *
     * @param g графічний контекст
     */
    private void drawMaze(Graphics g) {
        for (int row = 0; row < Maze.rows; row++) {
            for (int col = 0; col < Maze.cols; col++) {
                int x= settings.maze[row][col].getX();
                int y= settings.maze[row][col].getY();
                if (settings.maze[row][col].getT() ==1){
                    g.fillRect(x, y, 2* Maze.wallSize + Maze.cellSize, Maze.wallSize);
                }
                if (settings.maze[row][col].getR() ==1) {
                    g.fillRect(x+ Maze.wallSize + Maze.cellSize, y, Maze.wallSize, 2* Maze.wallSize + Maze.cellSize);
                }
                if (settings.maze[row][col].getB() ==1){
                    g.fillRect(x, y+ Maze.wallSize + Maze.cellSize, 2* Maze.wallSize + Maze.cellSize, Maze.wallSize);
                }
                if (settings.maze[row][col].getL() ==1) {
                    g.fillRect(x, y, Maze.wallSize, 2* Maze.wallSize + Maze.cellSize);
                }
            }
        }
        g.fillRect(mazeWidth- Maze.wallSize, mazeHeight- Maze.wallSize, Maze.wallSize, Maze.wallSize);

    }

    /**
     * Малює персонажі у лабіринті.
     *
     * @param g графічний контекст
     */
    private void drawCharacters(Graphics g) {
        // Draw the character at its current position
        chatGPT.draw(g);
        if (mob1!=null) mob1.draw(g);
        if (mob2!=null) mob2.draw(g);
        if (mob3!=null) mob3.draw(g);
    }

    /**
     * Малює статичні предмети в лабіринті.
     * @param g графічний контекст для малювання
     */
    private void drawStaticItems(Graphics g) {
        if (teleport1!=null) teleport1.draw(g);
        if (teleport2!=null) teleport2.draw(g);
        if (teleport3!=null) teleport3.draw(g);
        if (teleport4!=null) teleport4.draw(g);

        if (slideDoorButton!=null) slideDoorButton.draw(g);
        if (key!=null) key.draw(g);
        if (finish!=null) finish.draw(g);
        if (quiz1Item !=null) quiz1Item.draw(g);
        if (quiz2Item !=null) quiz2Item.draw(g);
        if (quiz3Item !=null) quiz3Item.draw(g);

    }
    /**
     * Малює рухомі предмети в лабіринті.
     * @param g графічний контекст для малювання
     */
    private void drawMovableItems(Graphics g) {
        if (slidingDoor!=null) slidingDoor.draw(g);
        if (rotatingDoor!=null) rotatingDoor.draw(g);

    }


    /**
     * Переміщує персонажа чат-бота у лабіринті.
     * @param dx зміщення по осі X
     * @param dy зміщення по осі Y
     */
    public void moveChatGPT(int dx, int dy) {
        int newX = chatGPT.getX() + dx;
        int newY = chatGPT.getY() + dy;

        if (!gameOver) {
            // Preventing going through walls
            if (isPositionValid(chatGPT, newX, newY) && chatGPT.isAlive()) {
                chatGPT.move(dx, dy);
                repaint();
            }
            // Checking collisions with mobs
            if (mob1!=null && isCollisionWithMob(mob1) && mob1.isDamaging() || mob2!=null && isCollisionWithMob(mob2) && mob2.isDamaging() || mob3!=null && isCollisionWithMob(mob3) && mob3.isDamaging()) {
                chatGPT.looseLife();
                Main.mazeUI.repaintHeartsPanel();
                playEffect("mob-collision.wav", 0.15);
                shimmerCharacter(chatGPT);
                if (mob1!=null && isCollisionWithMob(mob1)) freezeMob(mob1);
                if (mob2!=null && isCollisionWithMob(mob2)) freezeMob(mob2);
                if (mob3!=null && isCollisionWithMob(mob3)) freezeMob(mob3);
                //chatGPT.setAlive(false);

            }
            // Checking if character is in teleport
            if (teleport1!=null && isInside(teleport1, 0)) {
                playEffect("teleport.wav", 0.3);
                teleport1.teleportCharacter();

            } else if (teleport2!=null && isInside(teleport2, 0)) {
                teleport2.teleportCharacter();
                playEffect("teleport.wav", 0.3);
            }
            if (teleport3!=null && isInside(teleport3, 0)) {
                teleport3.teleportCharacter();
                playEffect("teleport.wav", 0.3);
            } else if (teleport4!=null && isInside(teleport4, 0)) {
                teleport4.teleportCharacter();
                playEffect("teleport.wav", 0.3);
            }


            checkGameOver();

        }
    }

    /**
     * Переміщує рухомого персонажа (Mob) у лабіринті.
     * @param character персонаж, якого потрібно перемістити
     * @param d відстань переміщення
     */
    public void moveMob(Mob character, int d) {
        int direction = character.isReverse() ? -1 : 1;
        int newX = character.getX();
        int newY = character.getY();
        if (character.getDirection() == 0) {
            newY = character.getY() + d * direction;
        } else if (character.getDirection() == 1) {
            newX = character.getX() + d * direction;
        }

        if (!gameOver) {
            if (!character.isFrozen()) { // Check if the mob is not frozen
                if (isPositionValid(character, newX, newY)) {
                    character.move(d * direction);
                } else {
                    character.turnAround();
                }
                if (isCollisionWithMob(character) && character.isDamaging()) {
                    chatGPT.looseLife();
                    Main.mazeUI.repaintHeartsPanel();
                    playEffect("mob-collision.wav", 0.15);
                    shimmerCharacter(chatGPT);
                    checkGameOver();

                    freezeMob(character);
                }
            }
            repaint();
        }
    }

    /**
     * Перевіряє, чи гра завершилася (геймовер або успішне завершення рівня).
     * Виконує відповідні дії в залежності від стану гри.
     */
    public void checkGameOver(){
        if (!chatGPT.isAlive() ) {
            stopMusic();
            gameOver =true;
            playEffect("gameover.wav", 0.1);
            if (fromChooseMaze) {
                MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                        new MessageWindow(this, "You died. Coming back to Maze selection...", "Game over", "Ok...")
                    : new MessageWindow(this, "Ви померли. Повертаємось до вибору лабіринтів...", "Гру закінчено", "Ок...");
                messageWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Main.chooseMazeUI.setVisible(true);
                        SwingUtilities.invokeLater(() -> Main.mazeUI.setVisible(false));
                        Main.playMusic();
                    }
                });

            } else {

                MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                        new MessageWindow(this, "You died. Coming back to chat...", "Game over", "Ok...")
                        : new MessageWindow(this, "Ви померли. Повертаємось до чату...", "Гру закінчено", "Ок...");

                messageWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Main.chatUI.setVisible(true);
                        Main.chatUI.addDeathMessage(0);
                        Main.setAlive(false);
                        SwingUtilities.invokeLater(() -> Main.mazeUI.setVisible(false));
                        Main.playMusic();
                    }
                });

            }

        } else if (isInside(finish, 0)) {
            stopMusic();
            playEffect("level-complete.wav", 0.1);
            gameOver =true;
            mazeCompleted=true;
            MazeUI.mazeCompleted = true;


            if (fromChooseMaze) {
                if (Main.getProgress().getChatData().yourChapter2.isCompleted() && level==1 ||
                        Main.getProgress().isFinaleUnlocked() && level==2) {
                        MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                                new MessageWindow(this, "You completed level "+level+"! Next level is also unlocked. Want to continue?", "Maze completed", "Yes", "No")
                                : new MessageWindow(this, "Ви пройшли рівень "+level+"! Наступний рівень також доступний. Хочете продовжити?", "Лабіринт пройдено", "Так", "Ні");
                        messageWindow.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                if (messageWindow.isYes()) {
                                    level++;
                                    setScene(level);
                                } else {
                                    Main.chooseMazeUI.setVisible(true);
                                    SwingUtilities.invokeLater(()-> Main.mazeUI.setVisible(false));
                                    stopMusic();
                                }
                            }
                        });

                    } else if (level==3){
                        MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                                new MessageWindow(this, "You completed level " + level + "! It was the last level", "Maze completed", "Go to maze selection")
                                : new MessageWindow(this, "Ви пройшли рівень "+level+"! Це був останній рівень", "Лабіринт пройдено", "Назад до лабіринтів");

                        messageWindow.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                // Run the remaining code here
                                Main.chooseMazeUI.setVisible(true);
                                SwingUtilities.invokeLater(() -> Main.mazeUI.setVisible(false));
                                stopMusic();
                            }
                        });

                    } else {
                        MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                                new MessageWindow(this, "You completed level "+level+"! But the next level is not unlocked yet.", "Maze completed", "Go to maze selection")
                                : new MessageWindow(this, "Ви пройшли рівень "+level+"! Але наступний рівень ще не розблокований", "Лабіринт пройдено", "Назад до лабіринтів");

                        messageWindow.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                // Run the remaining code here
                                Main.chooseMazeUI.setVisible(true);
                                SwingUtilities.invokeLater(() -> Main.mazeUI.setVisible(false));
                                stopMusic();
                            }
                        });
                    }
            } else {//came here from chat
                Main.mazeUI.setMazeCompleted(true);

                if (level<3) {
                    MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                            new MessageWindow(this, "You completed level "+level+"!", "Maze completed", "Go to chat")
                            : new MessageWindow(this, "Ви пройшли рівень "+level+"!", "Лабіринт пройдено", "Назад до чату");

                    messageWindow.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            level++;
                            Main.setLevel(level);
                            Main.chatUI.updateProgressData();
                            if (level==2)  Main.chatUI.startChapter2();
                            else Main.chatUI.startChapter3();
                            Main.chatUI.setVisible(true);
                            stopMusic();
                            SwingUtilities.invokeLater(()-> Main.mazeUI.setVisible(false));
                            Main.playMusic();
                        }
                    });

                } else {
                    MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                            new MessageWindow(this, "You completed level "+level+"! It was the last level", "Maze completed", "Go to chat")
                            : new MessageWindow(this, "Ви пройшли рівень "+level+"! Це був останній рівень", "Лабіринт пройдено", "Назад до чату");

                    messageWindow.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            Main.getProgress().getChatData().yourChapter3.setCompleted(true);
                            Main.getProgress().setFinaleUnlocked(true);
                            Main.updateProgress();
                            Main.chatUI.addFinal();
                            Main.chatUI.setVisible(true);
                            stopMusic();
                            SwingUtilities.invokeLater(()-> Main.mazeUI.setVisible(false));
                            Main.playMusic();
                        }
                    });
                }


            }

        }


    }

    /**
     * Заморожує ворога на півтори секунди.
     * @param character Ворог, якого потрібно заморозити.
     */
    private static void freezeMob(Mob character) {
        character.setFrozen(true); // Freeze the mob
        character.setDamaging(false);
        new Thread(() -> {
            try {
                Thread.sleep(1500); // Pause execution for 2 seconds (2000 milliseconds)
                character.setFrozen(false); // Unfreeze the mob after the pause
                character.setDamaging(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Здійснює ефект мерехтіння для персонажа протягом певного часу під час зіткнення з мобом.
     * @param character Персонаж, для якого потрібно застосувати ефект мерехтіння.
     */
    public void shimmerCharacter(Character character) {
        int duration = 500; // Duration of the shimmer in milliseconds
        int interval = 100; // Interval for toggling visibility in milliseconds

        Timer shimmerTimer = new Timer(interval, new ActionListener() {
            boolean isVisible = true;
            int elapsedTime = 0;

            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                character.setVisible(isVisible);
                repaint();

                elapsedTime += interval;
                if (elapsedTime >= duration) {
                    character.setVisible(true);
                    ((Timer) e.getSource()).stop();
                    repaint();
                }
            }
        });

        shimmerTimer.start();
    }

    /**
     * Здійснює рух ковзної двері.
     * Якщо ковзна двері існує, виконує звуковий ефект відкриття дверей, зміщує двері та оновлює налаштування.
     */
    public void moveSlidingDoor(){
        if (slidingDoor!=null) {
            playEffect("sliding-door-open.wav", 0.6);
            slidingDoor.move();
            settings = slidingDoor.getSettings();
            repaint();
        }
    }

    /**
     * Здійснює рух обертових дверей.
     * Якщо обертові двері існують, виконує звуковий ефект відкриття дверей, обертає двері та оновлює налаштування.
     */
    public void moveRotatingDoor(){
        if (rotatingDoor!=null) {
            playEffect("rotating-door-open.wav", 0.1);
            rotatingDoor.move();
            settings = rotatingDoor.getSettings();
            repaint();
        }
    }


    /**
     * Перевіряє, чи персонаж знаходиться всередині певного предмета з заданим відступом.
     * @param item предмет, для якого перевіряється положення персонажа
     * @param margin відступ, що враховується при перевірці (додавання до границь предмета)
     * @return {@code true} якщо персонаж знаходиться всередині предмета з врахуванням відступу, {@code false} - в іншому випадку
     */
    boolean isInside(Item item, int margin) {
        int characterTop = chatGPT.getY();
        int characterBottom = chatGPT.getY() + chatGPT.getHeight();
        int characterLeft = chatGPT.getX();
        int characterRight = chatGPT.getX() + chatGPT.getWidth();

        // Calculate the boundaries of the teleport
        int teleportTop = item.getY();
        int teleportBottom = item.getY() + item.getHeight();
        int teleportLeft = item.getX();
        int teleportRight = item.getX() + item.getWidth();

        return characterBottom<=(teleportBottom+margin) && characterTop>=(teleportTop-margin) && characterLeft>=(teleportLeft-margin) && characterRight<=(teleportRight+margin);

    }

    /**

     * Перевіряє, чи є задана позиція допустимою для персонажа з урахуванням стін лабіринту.
     * @param character персонаж, для якого перевіряється позиція
     * @param x нове значення координати x
     * @param y нове значення координати y
     * @return {@code true} якщо позиція є допустимою, {@code false} - в іншому випадку
     */
    public boolean isPositionValid(Character character, int x, int y) {
        int chW = character.getWidth();
        int chH = character.getHeight();
        if (x < Maze.wallSize || x + chW  > mazeWidth - Maze.wallSize ||
                y < Maze.wallSize || y + chH > mazeHeight - Maze.wallSize) {
            return false;
        }
        for (int row = 0; row < Maze.rows; row++) {
            for (int col = 0; col < Maze.cols; col++) {
                int wallX = settings.maze[row][col].getX();
                int wallY = settings.maze[row][col].getY();


                if (settings.maze[row][col].getT() == 1 && isRectCollision(x, y, chW, chH, wallX, wallY, Maze.wallSize + Maze.cellSize, Maze.wallSize)) {
                    // Collision with north wall
                    return false;
                }
                if (settings.maze[row][col].getR() == 1 && isRectCollision(x, y, chW, chH, wallX + Maze.wallSize + Maze.cellSize, wallY, Maze.wallSize, Maze.wallSize + Maze.cellSize)) {
                    // Collision with east wall
                    return false;
                }
                if (settings.maze[row][col].getB() == 1 && isRectCollision(x, y, chW, chH, wallX, wallY + Maze.wallSize + Maze.cellSize, Maze.wallSize + Maze.cellSize, Maze.wallSize)) {
                    // Collision with south wall
                    return false;
                }
                if (settings.maze[row][col].getL() == 1 && isRectCollision(x, y, chW, chH, wallX, wallY, Maze.wallSize, Maze.wallSize + Maze.cellSize)) {
                    // Collision with west wall
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Перевіряє, чи відбувається зіткнення двох прямокутників.
     * @param x1 координата x першого прямокутника
     * @param y1 координата y першого прямокутника
     * @param width1 ширина першого прямокутника
     * @param height1 висота першого прямокутника
     * @param x2 координата x другого прямокутника
     * @param y2 координата y другого прямокутника
     * @param width2 ширина другого прямокутника
     * @param height2 висота другого прямокутника
     * @return {@code true} якщо відбувається зіткнення, {@code false} - в іншому випадку
     */
    private boolean isRectCollision(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        return x1 < (x2 + width2) && (x1 + width1) > x2 && y1 < (y2 + height2) && (y1 + height1) > y2;
    }

    /**
     * Перевіряє, чи відбувається зіткнення персонажа зі зазначеним об'єктом.
     * @param item об'єкт, з яким перевіряється зіткнення
     * @return {@code true} якщо відбувається зіткнення, {@code false} - в іншому випадку
     */
    public boolean isCollisionWithMob(Item item) {
        // Calculate the boundaries of the character
        int characterTop = chatGPT.getY();
        int characterBottom = chatGPT.getY() + chatGPT.getHeight();
        int characterLeft = chatGPT.getX();
        int characterRight = chatGPT.getX() + chatGPT.getWidth();

        // Calculate the boundaries of the mob
        int mobTop = item.getY();
        int mobBottom = item.getY() + item.getHeight();
        int mobLeft = item.getX();
        int mobRight = item.getX()+ item.getWidth();

        // Check for collisions in different directions
        boolean collisionTop = characterBottom >= mobTop+10 && characterTop <= mobTop+10 && characterRight >= mobLeft+10 && characterLeft <= mobRight-10;
        boolean collisionBottom = characterTop <= mobBottom-10 && characterBottom >= mobBottom-10 && characterRight >= mobLeft+10 && characterLeft <= mobRight-10;
        boolean collisionLeft = characterRight >= mobLeft+10 && characterLeft <= mobLeft+10 && characterBottom >= mobTop+10 && characterTop <= mobBottom-10;
        boolean collisionRight = characterLeft <= mobRight-10 && characterRight >= mobRight-10 && characterBottom >= mobTop+10 && characterTop <= mobBottom-10;


        return collisionTop || collisionBottom || collisionLeft || collisionRight;

    }
    /**
     * Повертає пріоритетний розмір панелі.
     * @return об'єкт Dimension, що представляє пріоритетний розмір панелі
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(mazeWidth, mazeHeight);  // Set the preferred size of the panel
    }

    /**
     * Застосовує бонус, в залежності від того, чи правильно була дана відповідь на вікторину
     */
    public void getBonus(){
        if (quizWindow.isAnsweredCorrectly()) {
            if (chatGPT.getLives()<3) {
                playEffect("pickup.wav", 0.15);
                chatGPT.setLives(chatGPT.getLives() + 1);
                Main.mazeUI.repaintHeartsPanel();
            } else {

                if (mob2==null && mob1 !=null) {
                    shimmerCharacter(mob1);
                    mob1 = null;
                }
                else if (mob2 != null) {
                    shimmerCharacter(mob2);
                    mob2 = null;
                }
            }
        } else {
            playEffect("mob-collision.wav", 0.15);
            chatGPT.looseLife();
            shimmerCharacter(chatGPT);
            Main.mazeUI.repaintHeartsPanel();
        }
    }

    /**
     * Перевіряє, чи відтворюється фонова музика.
     *
     * @return {@code true}, якщо фонова музика відтворюється, {@code false} - в іншому випадку.
     */
    public boolean isMusicPlaying() {
        MediaPlayer.Status status = mediaPlayer.getStatus();
        return status == MediaPlayer.Status.PLAYING;
    }

    /**
     * Відтворює аудіоефект з вказаним шляхом і гучністю.
     *
     * @param path   шлях до аудіофайлу ефекту
     * @param volume гучність ефекту (від 0.0 до 1.0)
     */
    public void playEffect(String path, double volume) {
        new JFXPanel();
        Platform.runLater(() -> {
            File musicFile = new File("music/" + path);
            Media media = new Media(musicFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.setVolume(volume * volumeCoef1);
            mediaPlayer.play();
        });
    }

    /**
     * Налаштовує фонову музику з вказаним шляхом і гучністю.
     *
     * @param path   шлях до аудіофайлу музики
     * @param volume гучність музики (від 0.0 до 1.0)
     */
    public void setMusic(String path, double volume) {
        new JFXPanel();

        File musicFile = new File(path);
        Media media = new Media(musicFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(volume * volumeCoef);
    }

    /**
     * Відтворює фонову музику, якщо вона встановлена.
     */
    public void playMusic() {
        if (mediaPlayer!=null) mediaPlayer.play();

    }

    /**
     * Ставить фонову музику на паузу.
     */
    public void pauseMusic() {

        if (mediaPlayer!=null) mediaPlayer.pause();
    }

    /**
     * Зупиняє фонову музику.
     */
    public void stopMusic() {

        if (mediaPlayer!=null) mediaPlayer.stop();
    }


}


