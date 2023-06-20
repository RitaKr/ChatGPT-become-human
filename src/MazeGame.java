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


    // Array representing the maze structure

    private Maze settings;
    Maze.Cell[][] maze;
    private static int mazeWidth = Maze.cols * Maze.cellSize + (Maze.cols + 1) * Maze.wallSize;
    private static int mazeHeight = Maze.rows * Maze.cellSize + (Maze.rows + 1) * Maze.wallSize;
    private static int menuHeight = 40;
    private boolean gameOver = false;
    private boolean mazeCompleted = false;
    private int level;

    MediaPlayer mediaPlayer;

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isMazeCompleted() {
        return mazeCompleted;
    }

    public int getLevel() {
        return level;
    }

    public static int getMazeWidth() {
        return mazeWidth;
    }

    public static int getMazeHeight() {
        return mazeHeight;
    }

    static Character chatGPT;
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
    Item quiz1, quiz2, quiz3;
    QuizWindow quizWindow;
    ArrayList<Quiz> quizes =  new ArrayList<Quiz>(Arrays.asList(
            new Quiz("Який з цих методів використовується для виводу тексту в консоль в Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. System.out.display()"), new Answer("B. System.out.print()", true), new Answer("C. Console.write()"), new Answer("D. Print.console()")))),

            new Quiz("Який з наступних операторів використовується для порівняння двох значень на рівність в Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. =="), new Answer("B. =", true), new Answer("C. equals()"), new Answer("D. match()")))),

            new Quiz("Який із наступних варіантів є вірним способом створення масиву int в Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. int array = new int[5];", true), new Answer("B. int[] array = new int 5;"), new Answer("C. array int[] = new int[5];"), new Answer("D. int array new[5];")))),

            new Quiz("Як у Java викликається конструктор суперкласу?",
                 new ArrayList<>(Arrays.asList(new Answer("A. super()", true), new Answer("B. this()"), new Answer("C. extends()"), new Answer("D. super")))),

            new Quiz("Які ключові слова в Java використовуються для обробки винятків?",
                             new ArrayList<>(Arrays.asList(new Answer("A. If / Else"), new Answer("B. Throw / Catch"), new Answer("C. Error / Exception"), new Answer("D. Try / Catch", true)))),

            new Quiz("Яке ключове слово в Java використовується для створення екземпляра класу?",
                             new ArrayList<>(Arrays.asList(new Answer("A. class"), new Answer("B. new", true), new Answer("C. this"), new Answer("D. instance")))),

            new Quiz("Що таке в Java \"garbage collector\"?",
                    new ArrayList<>(Arrays.asList(new Answer("A. Метод для видалення непотрібних файлів"), new Answer("B. Система для автоматичного очищення не використовуваної пам'яті", true), new Answer("C. Метод для зміни значень змінних"), new Answer("D. Сервіс для видалення старих версій Java")))),

            new Quiz("Яке ключове слово в Java використовується для наслідування?",
                    new ArrayList<>(Arrays.asList(new Answer("A. inherit"), new Answer("B. superclass"), new Answer("C. extends", true), new Answer("D. implements")))),

            new Quiz("Як називається механізм, що дозволяє одному об'єкту набувати властивостей іншого об'єкта в Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. Поліморфізм"), new Answer("B. Капсулювання"), new Answer("C. Наслідування", true), new Answer("D. Абстракція")))),

            new Quiz("Яке ключове слово в Java використовується для створення екземпляра класу?",
                    new ArrayList<>(Arrays.asList(new Answer("A. class"), new Answer("B. new", true), new Answer("C. this"), new Answer("D. instance")))),

            new Quiz("Яке ключове слово в Java використовується для створення екземпляра класу?",
                    new ArrayList<>(Arrays.asList(new Answer("A. class"), new Answer("B. new", true), new Answer("C. this"), new Answer("D. instance")))),

            new Quiz("Яке ключове слово в Java використовується для створення екземпляра класу?",
                    new ArrayList<>(Arrays.asList(new Answer("A. class"), new Answer("B. new", true), new Answer("C. this"), new Answer("D. instance")))),

            new Quiz("Що відбувається при спробі поділити число на 0 в Java?",
                    new ArrayList<>(Arrays.asList(new Answer("A. Виникає помилка виконання ArithmeticException", true), new Answer("B. Повертається 0"), new Answer("C. Повертається найбільше додатне число"), new Answer("D. Нічого, програма продовжує працювати нормально")))),

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

            ));
    private Image backgroundImage;
    //private ImageIcon backgroundImage;
    boolean fromChooseMaze;

    public Character getChatGPT() {
        return chatGPT;
    }

    public MazeGame(int level) {
        this.level = level;
        setScene(level);
    }
    public MazeGame() {
        this.level = Main.getProgress().getLv();
        setScene();
    }
    public MazeGame(boolean fromChooseMaze, int level) {
        this.fromChooseMaze = fromChooseMaze;
        if (fromChooseMaze) {
            this.level = level;
            setScene(level);
        } else  {
            this.level = Main.getProgress().getLv();
            setScene();
        }

    }
    public void setScene(int level) {
        setMaze(level);
    }
    public void setScene() {
        Main.setLevel(level);
        setMaze(level);

    }
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
                finish = new Item("finish.png",0, 0, 100, 85);
                quiz1 = new Item("quiz.png",2, 2, 40, 60);
                quiz2 = null;
                quiz3 = null;
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
                finish = new Item("finish.png",5, 1, 100, 85);
                quiz1 = new Item("quiz.png",3, 1, 40, 60);
                quiz2 = new Item("quiz.png",5, 5, 40, 60);
                quiz3 = null;
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
                finish = new Item("finish.png",4, 7, 100, 85);
                quiz1 = new Item("quiz.png",5, 4, 40, 60);
                quiz2 = new Item("quiz.png",2, 1, 40, 60);
                quiz3 = new Item("quiz.png",1, 2, 40, 60);
            }
        }
        repaint();
        playMusic();
    }

    private void drawBackground(Graphics g){

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        //System.out.println("x "+getWidth() + ", y" + getHeight() );
    }

    private void loadBackgroundImage(String imageName) {
        String imagePath = "images/" + imageName;

        if (imagePath.toLowerCase().endsWith(".gif")) {
            backgroundImage = Toolkit.getDefaultToolkit().createImage(imagePath);
        } else {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Main.mazeUI.updateUpperPanel();


        drawBackground(g);
        // Draw the maze layout
        g.setColor(Color.white);
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

    private void drawMaze(Graphics g) {
        for (int row = 0; row < Maze.rows; row++) {
            for (int col = 0; col < Maze.cols; col++) {
                int x= settings.maze[row][col].getX();
                int y= settings.maze[row][col].getY();
                if (settings.maze[row][col].getT() ==1){
                    g.fillRect(x, y, 2*Maze.wallSize + Maze.cellSize, Maze.wallSize);
                }
                if (settings.maze[row][col].getR() ==1) {
                    g.fillRect(x+ Maze.wallSize + Maze.cellSize, y, Maze.wallSize, 2*Maze.wallSize + Maze.cellSize);
                }
                if (settings.maze[row][col].getB() ==1){
                    g.fillRect(x, y+ Maze.wallSize + Maze.cellSize, 2*Maze.wallSize + Maze.cellSize, Maze.wallSize);
                }
                if (settings.maze[row][col].getL() ==1) {
                    g.fillRect(x, y, Maze.wallSize, 2*Maze.wallSize + Maze.cellSize);
                }
            }
        }
        g.fillRect(mazeWidth- Maze.wallSize, mazeHeight- Maze.wallSize, Maze.wallSize, Maze.wallSize);

    }

    private void drawCharacters(Graphics g) {
        // Draw the character at its current position
        chatGPT.draw(g);
        if (mob1!=null) mob1.draw(g);
        if (mob2!=null) mob2.draw(g);
        if (mob3!=null) mob3.draw(g);
    }
    private void drawStaticItems(Graphics g) {
        if (teleport1!=null) teleport1.draw(g);
        if (teleport2!=null) teleport2.draw(g);
        if (teleport3!=null) teleport3.draw(g);
        if (teleport4!=null) teleport4.draw(g);

        if (slideDoorButton!=null) slideDoorButton.draw(g);
        if (key!=null) key.draw(g);
        if (finish!=null) finish.draw(g);
        if (quiz1!=null) quiz1.draw(g);
        if (quiz2!=null) quiz2.draw(g);
        if (quiz3!=null) quiz3.draw(g);


    }
    private void drawMovableItems(Graphics g) {
        if (slidingDoor!=null) slidingDoor.draw(g);
        if (rotatingDoor!=null) rotatingDoor.draw(g);

    }

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
               //System.out.println("shimmer in chatGPT");
                shimmerCharacter(chatGPT);
                if (mob1!=null && isCollisionWithMob(mob1)) freezeMob(mob1);
                if (mob2!=null && isCollisionWithMob(mob2)) freezeMob(mob2);
                if (mob3!=null && isCollisionWithMob(mob3)) freezeMob(mob3);
                //chatGPT.setAlive(false);

            }
            // Checking if character is in teleport
            if (teleport1!=null && isInside(teleport1, 0)) {
                playEffect("teleport.wav", 0.6);
                teleport1.teleportCharacter();

            } else if (teleport2!=null && isInside(teleport2, 0)) {
                teleport2.teleportCharacter();
                playEffect("teleport.wav", 0.6);
            }
            if (teleport3!=null && isInside(teleport3, 0)) {
                teleport3.teleportCharacter();
                playEffect("teleport.wav", 0.6);
            } else if (teleport4!=null && isInside(teleport4, 0)) {
                teleport4.teleportCharacter();
                playEffect("teleport.wav", 0.6);
            }


            checkGameOver();

        }
    }



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
                    //MazeUI.HeartsPanel.repaintHeartsPanel();
                    Main.mazeUI.repaintHeartsPanel();
                    playEffect("mob-collision.wav", 0.15);
                    shimmerCharacter(chatGPT);
                    checkGameOver();

                   //System.out.println("Lives left (mob): " + chatGPT.getLives());
                   //System.out.println("shimmer in mob");

                    freezeMob(character);
                }
            }
            repaint();
        }
    }
    public void checkGameOver(){
        if (!chatGPT.isAlive() ) {
            stopMusic();
            gameOver =true;
            playEffect("gameover.wav", 0.3);
            if (fromChooseMaze) {
                MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                        new MessageWindow(this, "You died. Coming back to Maze selection...", "Game over", "Ok...")
                    : new MessageWindow(this, "Ви померли. Повертаємось до вибору лабіринтів...", "Гру закінчено", "Ок...");
            //setScene(level);
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

                //setScene(level);
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
            playEffect("level-complete.wav", 0.3);
            gameOver =true;
            mazeCompleted=true;
            MazeUI.mazeCompleted = true;


            if (fromChooseMaze) {
                if (Main.getProgress().getLv()>level) {
                    if (level<3) {
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
                                    SwingUtilities.invokeLater(()->Main.mazeUI.setVisible(false));
                                    stopMusic();
                                }
                            }
                        });


                    } else {
                        MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                                new MessageWindow(this, "You completed level " + level + "! It was the last level", "Maze completed", "Go to maze selection")
                                : new MessageWindow(this, "Ви пройшли рівень "+level+"! Це був останній рівень", "Лабіринт пройдено", "Повернутись до лабіринтів");

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

                } else {
                    MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                            new MessageWindow(this, "You completed level "+level+"! But the next level is not unlocked yet.", "Maze completed", "Go to maze selection")
                            : new MessageWindow(this, "Ви пройшли рівень "+level+"! Але наступний рівень ще не розблокований", "Лабіринт пройдено", "Повернутись до лабіринтів");

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
                            : new MessageWindow(this, "Ви пройшли рівень "+level+"!", "Лабіринт пройдено", "Повернутись до чату");

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
                            SwingUtilities.invokeLater(()->Main.mazeUI.setVisible(false));
                            Main.playMusic();
                        }
                    });

                } else {
                    MessageWindow messageWindow = Main.getLanguage().equals("en") ?
                            new MessageWindow(this, "You completed level "+level+"! It was the last level", "Maze completed", "Go to chat")
                            : new MessageWindow(this, "Ви пройшли рівень "+level+"! Це був останній рівень", "Лабіринт пройдено", "Повернутись до чату");

                    messageWindow.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            Main.chatUI.setVisible(true);
                            stopMusic();
                            SwingUtilities.invokeLater(()->Main.mazeUI.setVisible(false));
                            Main.playMusic();
                        }
                    });
                }


            }

            //MazeUI.updateUpperPanel();

        }


    }
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


    public void moveSlidingDoor(){
        if (slidingDoor!=null) {
            playEffect("sliding-door-open.wav", 0.8);
            slidingDoor.move();
            settings = slidingDoor.getSettings();
            repaint();
        }
    }
    public void moveRotatingDoor(){
        if (rotatingDoor!=null) {
            playEffect("rotating-door-open.wav", 0.1);
            rotatingDoor.move();
            settings = rotatingDoor.getSettings();
            repaint();
        }
    }
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
    public boolean isPositionValid(Character character, int x, int y) {
        int chW = character.getWidth();
        int chH = character.getHeight();
        if (x < Maze.wallSize || x + chW  > mazeWidth - Maze.wallSize ||
                y < Maze.wallSize || y + chH > mazeHeight - Maze.wallSize) {
            // Character is outside the maze boundaries
            return false;
        }
        // Check if the character is colliding with any maze walls
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
    private boolean isRectCollision(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        return x1 < (x2 + width2) && (x1 + width1) > x2 && y1 < (y2 + height2) && (y1 + height1) > y2;
    }
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(mazeWidth, mazeHeight);  // Set the preferred size of the panel
    }



    public boolean isMusicPlaying() {
        MediaPlayer.Status status = mediaPlayer.getStatus();

        if (status == MediaPlayer.Status.PLAYING) {
            // The MediaPlayer is currently playing
           //System.out.println("The track is playing");
            return true;
        } else {
            // The MediaPlayer is in a different state (e.g., stopped)
           //System.out.println("The track is in a different state");
            return false;
        }

    }
    public void playEffect(String path, double volume) {
        new JFXPanel();

        File musicFile = new File("music/"+path);
        Media media = new Media(musicFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }
    public void setMusic(String path, double volume) {
        // Initialize JavaFX environment
        new JFXPanel();


        // Create a File object with the MP3 file
        File musicFile = new File(path);

        // Create a Media object with the File object
        Media media = new Media(musicFile.toURI().toString());

        // Create a MediaPlayer object to play the media
        mediaPlayer = new MediaPlayer(media);

        // Configure the MediaPlayer to loop the music
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Start playing the music
        mediaPlayer.setVolume(volume);


    }
    public void playMusic() {
        if (mediaPlayer!=null) mediaPlayer.play();

    }
    public void pauseMusic() {

        if (mediaPlayer!=null) mediaPlayer.pause();
    }
    public void stopMusic() {

        if (mediaPlayer!=null) mediaPlayer.stop();
    }


}


