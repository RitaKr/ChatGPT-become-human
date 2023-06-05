import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


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
    Teleport teleport1;
    Teleport teleport2;
    Teleport teleport3;
    Teleport teleport4;
    SlidingDoor slidingDoor;
    Item slideDoorButton;
    RotatingDoor rotatingDoor;
    Item key;
    Item finish;
    private Image backgroundImage;

    public Character getChatGPT() {
        return chatGPT;
    }

    public MazeGame(int level) {
        this.level = level;
        setScene(level);
    }
    public void setScene(int level) {
        Main.setLevel(level);
        gameOver = false;
        settings = new Maze(level);
        maze = settings.maze;


        switch (level) {
            case 1 ->{
                loadBackgroundImage("bg2.jpg");
                setMusic("music/marjim-go-big.mp3");
                chatGPT = new Character(8, 5, 0, false);

                mob1 = new Mob("virus.png", 2, 4, 3, 1);
                mob2=null;
                slidingDoor = new SlidingDoor(settings, 7, 4, 5,  4, true);
                rotatingDoor = null;

                teleport1 = new Teleport("teleport.png",3, 0, 3 , 6, Side.TOP);
                teleport2 = new Teleport("teleport.png",3, 6, 3, 0, Side.TOP);
                slideDoorButton = new Item("doorButton.png",5, 5);
                key = new Item("key.png",1, 7);
                finish = new Item("finish.png",0, 0, 100, 100);
            }
            case 2 -> {
                loadBackgroundImage("bg1.jpg");
                setMusic("music/marjim-invincible.mp3");
                chatGPT = new Character(8, 5, 0, false);

                mob1 = new Mob("virus.png", 2, 4, 3, 1);
                mob2 = new Mob("virus.png", 3, 2, 5, 0);

                slidingDoor = new SlidingDoor(settings, 7, 4, 5,  4, true);
                rotatingDoor = new RotatingDoor(settings, 0, 5, Side.LEFT, true);

                teleport1 = new Teleport("teleport.png",3, 0, 3 , 6, Side.TOP);
                teleport2 = new Teleport("teleport.png",3, 6, 3, 0, Side.TOP);
                slideDoorButton = new Item("doorButton.png",5, 5);
                key = new Item("key.png",0, 7);
                finish = new Item("finish.png",0, 0, 100, 100);
            }
            case 3 -> {
                loadBackgroundImage("bg3.png");
                setMusic("music/marjim-invincible.mp3");
                chatGPT = new Character(8, 5, 0, false);

                mob1 = new Mob("virus.png", 2, 0, 0, 1);
                mob2 = new Mob("virus.png", 3, 3, 2, 1);

                slidingDoor = new SlidingDoor(settings, 1, 4, 3,  4, true);
                rotatingDoor = new RotatingDoor(settings, 4, 7, Side.BOTTOM, false);

                teleport1 = new Teleport("teleport.png",1, 1, 0 , 7, Side.BOTTOM);
                teleport2 = new Teleport("teleport.png",0, 7, 1, 1, Side.BOTTOM);
                teleport3 = new Teleport("teleport2.png",4, 4, 5, 2, Side.LEFT);
                teleport4 = new Teleport("teleport2.png",5, 2, 4, 4, Side.BOTTOM);

                slideDoorButton = new Item("doorButton.png",1, 3);
                key = new Item("key.png",4, 3);
                finish = new Item("finish.png",5, 1, 100, 100);
            }
        }

        repaint();
        playMusic();
    }
    private void drawBackground(Graphics g){

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        System.out.println("x "+getWidth() + ", y" + getHeight() );
    }
    private void loadBackgroundImage(String imageName) {
        ImageIcon icon = new ImageIcon("images/"+imageName); // Replace with the path to your character image file
        backgroundImage = icon.getImage();

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MazeUI.updateUpperPanel();
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

    }

    private void drawMaze(Graphics g) {
        for (int row = 0; row < Maze.rows; row++) {
            for (int col = 0; col < Maze.cols; col++) {
                int x= settings.maze[row][col].getX();
                int y= settings.maze[row][col].getY();
                if (settings.maze[row][col].getT() ==1){
                    g.fillRect(x, y, Maze.wallSize + Maze.cellSize, Maze.wallSize);
                }
                if (settings.maze[row][col].getR() ==1) {
                    g.fillRect(x+ Maze.wallSize + Maze.cellSize, y, Maze.wallSize, Maze.wallSize + Maze.cellSize);
                }
                if (settings.maze[row][col].getB() ==1){
                    g.fillRect(x, y+ Maze.wallSize + Maze.cellSize, Maze.wallSize + Maze.cellSize, Maze.wallSize);
                }
                if (settings.maze[row][col].getL() ==1) {
                    g.fillRect(x, y, Maze.wallSize, Maze.wallSize + Maze.cellSize);
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
    }
    private void drawStaticItems(Graphics g) {
        if (teleport1!=null) teleport1.draw(g);
        if (teleport2!=null) teleport2.draw(g);
        if (slideDoorButton!=null) slideDoorButton.draw(g);
        if (key!=null) key.draw(g);
        if (finish!=null) finish.draw(g);

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
            if (mob1!=null && isCollisionWithMob(mob1) && mob1.isDamaging() || mob2!=null && isCollisionWithMob(mob2) && mob2.isDamaging()) {
                chatGPT.looseLife();
                MazeUI.HeartsPanel.repaintHeartsPanel();

                System.out.println("shimmer in chatGPT");
                shimmerCharacter(chatGPT);
                if (mob1!=null && isCollisionWithMob(mob1)) freezeMob(mob1);
                if (mob2!=null && isCollisionWithMob(mob2)) freezeMob(mob2);
                //chatGPT.setAlive(false);

            }
            // Checking if character is in teleport
            if (isInside(teleport1, 0)) {
                teleport1.teleportCharacter();

            } else if (isInside(teleport2, 0)) {
                teleport2.teleportCharacter();
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
                    MazeUI.HeartsPanel.repaintHeartsPanel();
                    shimmerCharacter(chatGPT);
                    checkGameOver();

                    System.out.println("Lives left (mob): " + chatGPT.getLives());
                    System.out.println("shimmer in mob");

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
            int answer = JOptionPane.showConfirmDialog(null, "Вот и помер дед максим..", "лох", JOptionPane.YES_NO_OPTION);
            if (answer==0) {
                level=1;
                setScene(level);

            }
        } else if (isInside(finish, 0)) {
            stopMusic();
            gameOver =true;
            mazeCompleted=true;
            JOptionPane.showMessageDialog(null, "You completed level "+level+"! ", "Maze completed", JOptionPane.PLAIN_MESSAGE);
            System.out.println("level completed");
            level++;
            setScene(level);
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
            slidingDoor.move();
            settings = slidingDoor.getSettings();
            repaint();
        }
    }
    public void moveRotatingDoor(){
        if (rotatingDoor!=null) {
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




    public void playMusic() {
        if (mediaPlayer!=null) mediaPlayer.play();

    }
    public boolean isMusicPlaying() {
        MediaPlayer.Status status = mediaPlayer.getStatus();

        if (status == MediaPlayer.Status.PLAYING) {
            // The MediaPlayer is currently playing
            System.out.println("The track is playing");
            return true;
        } else {
            // The MediaPlayer is in a different state (e.g., stopped)
            System.out.println("The track is in a different state");
            return false;
        }

    }
    public void setMusic(String path) {
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
        mediaPlayer.setVolume(0.1);


    }
    public void pauseMusic() {

        if (mediaPlayer!=null) mediaPlayer.pause();
    }
    public void stopMusic() {

        if (mediaPlayer!=null) mediaPlayer.stop();
    }


}


