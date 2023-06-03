import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;


public class MazeGame extends JPanel {
    private static final int WALL_WIDTH = 10;
    private static int CORRIDOR_WIDTH = 100;
    private static int rows = 6;  // Number of rows in the maze
    private static int cols = 8; // Number of columns in the maze

    // Array representing the maze structure
    private Cell[][] maze = {
            {   new Cell(1, 0, 1, 1, 0, 0),
                new Cell(1, 0, 1, 0, 0, 1),
                new Cell(1, 0, 1, 0, 0, 2),
                new Cell(1, 0, 1, 0, 0, 3),
                new Cell(1, 0, 1, 0, 0, 4),
                new Cell(1, 0, 0, 0, 0, 5),
                new Cell(1, 1, 0, 0, 0, 6),
                new Cell(1, 1, 0, 1, 0, 7),
            },
            {   new Cell(1, 0, 0, 1, 1, 0),
                new Cell(1, 0, 1, 0, 1, 1),
                new Cell(1, 0, 1, 0, 1, 2),
                new Cell(1, 0, 1, 0, 1, 3),
                new Cell(1, 0, 1, 0, 1, 4),
                new Cell(0, 1, 0, 0, 1, 5),
                new Cell(0, 1, 0, 1, 1, 6),
                new Cell(0, 1, 0, 1, 1, 7),
            },
            {   new Cell(0, 1, 0, 1, 2, 0),
                new Cell(1, 0, 0, 1, 2, 1),
                new Cell(1, 0, 1, 0, 2, 2),
                new Cell(1, 0, 1, 0, 2, 3),
                new Cell(1, 0, 1, 0, 2, 4),
                new Cell(0, 1, 0, 0, 2, 5),
                new Cell(0, 1, 0, 1, 2, 6),
                new Cell(0, 1, 0, 1, 2, 7),
            },
            {   new Cell(0, 1, 1, 1, 3, 0),
                new Cell(0, 0, 1, 1, 3, 1),
                new Cell(1, 0, 0, 0, 3, 2),
                new Cell(1, 0, 0, 0, 3, 3),
                new Cell(1, 1, 0, 0, 3, 4),
                new Cell(0, 1, 0, 1, 3, 5),
                new Cell(0, 1, 1, 1, 3, 6),
                new Cell(0, 1, 0, 1, 3, 7),
            },
            {   new Cell(1, 0, 1, 1, 4, 0),
                new Cell(1, 0, 1, 0, 4, 1),
                new Cell(0, 1, 1, 0, 4, 2),
                new Cell(0, 0, 0, 1, 4, 3),
                new Cell(0, 1, 0, 0, 4, 4),
                new Cell(0, 0, 1, 1, 4, 5),
                new Cell(1, 0, 1, 0, 4, 6),
                new Cell(0, 1, 0, 0, 4, 7),
            },
            {   new Cell(1, 0, 1, 1, 5, 0),
                new Cell(1, 0, 1, 0, 5, 1),
                new Cell(1, 0, 1, 0, 5, 2),
                new Cell(0, 0, 1, 0, 5, 3),
                new Cell(0, 1, 1, 0, 5, 4),
                new Cell(1, 0, 1, 1, 5, 5),
                new Cell(1, 0, 1, 0, 5, 6),
                new Cell(0, 1, 1, 0, 5, 7),
            }
    };
    private static final int mazeWidth = cols * CORRIDOR_WIDTH + (cols + 1) * WALL_WIDTH;
    private static int mazeHeight = rows * CORRIDOR_WIDTH + (rows + 1) * WALL_WIDTH;
    private Character chatGPT;
    private Mob mob1;
    private Mob mob2;
    private Teleport teleport1;
    private Teleport teleport2;
    private SlidingDoor slidingDoor;
    SlidingDoorButton slideDoorButton;
    private RotatingDoor rotatingDoor;
    Item key;
    boolean gameOver = false;
    private Image backgroundImage;

    public Character getChatGPT() {
        return chatGPT;
    }

    public MazeGame() {
        setScene();
    }
    public void setScene() {
        chatGPT = new Character(8, WALL_WIDTH+10, mazeHeight-CORRIDOR_WIDTH+10, false);
        //chatGPT = new Character(8, WALL_WIDTH+10+ 6*(WALL_WIDTH+CORRIDOR_WIDTH), WALL_WIDTH+10 + 5*(WALL_WIDTH+CORRIDOR_WIDTH), false);
        mob1 = new Mob("virus.png", 2, WALL_WIDTH+20 + 3*(WALL_WIDTH+CORRIDOR_WIDTH), mazeHeight-(WALL_WIDTH+CORRIDOR_WIDTH)*2+10, 1);
        mob2 = new Mob("virus.png", 2, WALL_WIDTH+20 + 5*(WALL_WIDTH+CORRIDOR_WIDTH), mazeHeight-(WALL_WIDTH+CORRIDOR_WIDTH)*3+10, 0);
        teleport1 = new Teleport(WALL_WIDTH+10, WALL_WIDTH+10 + 3*(WALL_WIDTH+CORRIDOR_WIDTH), WALL_WIDTH+20 + 6*(WALL_WIDTH+CORRIDOR_WIDTH), WALL_WIDTH+5 + 3*(WALL_WIDTH+CORRIDOR_WIDTH));
        teleport2 = new Teleport(WALL_WIDTH+10 + 6*(WALL_WIDTH+CORRIDOR_WIDTH), WALL_WIDTH+10 + 3*(WALL_WIDTH+CORRIDOR_WIDTH), WALL_WIDTH+20, WALL_WIDTH+5 + 3*(WALL_WIDTH+CORRIDOR_WIDTH));
        slidingDoor = new SlidingDoor(7, 4, 5,  4, true);

        slideDoorButton = new SlidingDoorButton(WALL_WIDTH+20+5*(WALL_WIDTH+CORRIDOR_WIDTH), mazeHeight-CORRIDOR_WIDTH+15);
        rotatingDoor = new RotatingDoor(0, 5, 0, 3);
        key = new Item("key.png",WALL_WIDTH+20 + 7*(WALL_WIDTH+CORRIDOR_WIDTH), WALL_WIDTH+20);
        System.out.println(mazeWidth+", "+mazeHeight);
        loadCharacterImage(backgroundImage, "bg1.jpg");
    }
    private void drawBackground(Graphics g){
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    private void loadCharacterImage(Image image, String imageName) {
        ImageIcon icon = new ImageIcon("images/"+imageName); // Replace with the path to your character image file
        backgroundImage = icon.getImage();
        //System.out.println("w: "+width+", h:"+ height);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);
        // Draw the maze layout
        g.setColor(Color.white);
        drawMaze(g);

        //Draw teleports
        drawTeleports(g);

        //Draw sliding door
        slidingDoor.drawSlidingDoor(g);
        rotatingDoor.drawRotatingDoor(g);

        //Draw sliding door button
        g.drawImage(slideDoorButton.getCharacterImage(), slideDoorButton.getX(),slideDoorButton.getY(), null);

        g.drawImage(key.getCharacterImage(), key.getX(), key.getY(), null);

        // Draw the character
        drawCharacters(g);
        try {
            Thread.sleep(15); // Adjust the delay duration as needed (in milliseconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveMob(mob1, mob1.getSpeed());
        moveMob(mob2, mob2.getSpeed());

    }

    private void drawMaze(Graphics g) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x=maze[row][col].x;
                int y=maze[row][col].y;
                if (maze[row][col].T ==1){
                    g.fillRect(x, y, WALL_WIDTH+CORRIDOR_WIDTH, WALL_WIDTH);
                }
                if (maze[row][col].R ==1) {
                    g.fillRect(x+WALL_WIDTH+CORRIDOR_WIDTH, y, WALL_WIDTH, WALL_WIDTH+CORRIDOR_WIDTH);
                }
                if (maze[row][col].B ==1){
                    g.fillRect(x, y+WALL_WIDTH+CORRIDOR_WIDTH, WALL_WIDTH+CORRIDOR_WIDTH, WALL_WIDTH);
                }
                if (maze[row][col].L ==1) {
                    g.fillRect(x, y, WALL_WIDTH, WALL_WIDTH+CORRIDOR_WIDTH);
                }
            }
        }
        g.fillRect(mazeWidth-WALL_WIDTH, mazeHeight-WALL_WIDTH, WALL_WIDTH, WALL_WIDTH);

    }


    private void drawCharacters(Graphics g) {
        // Draw the character at its current position
        if (chatGPT.isVisible()) g.drawImage(chatGPT.getCharacterImage(), chatGPT.getX() , chatGPT.getY() , null);
        if (mob1.isVisible()) g.drawImage(mob1.getCharacterImage(), mob1.getX() , mob1.getY() , null);
        if (mob2.isVisible()) g.drawImage(mob2.getCharacterImage(), mob2.getX() , mob2.getY() , null);
    }
    private void drawTeleports(Graphics g) {
        // Draw the character at its current position
        g.drawImage(teleport1.getCharacterImage(), teleport1.getX() , teleport1.getY() , null);
        g.drawImage(teleport2.getCharacterImage(), teleport2.getX() , teleport2.getY() , null);

    }

    public void moveChatGPT(int dx, int dy) {
        int newX = chatGPT.getX() + dx;
        int newY = chatGPT.getY() + dy;

        if (chatGPT.isAlive()) {
            // Preventing going through walls
            if (isPositionValid(chatGPT, newX, newY) && chatGPT.isAlive()) {
                chatGPT.move(dx, dy);
                repaint();
            }
            // Checking collisions with mobs
            if (isCollisionWithMob(mob1) && mob1.isDamaging() || isCollisionWithMob(mob2) && mob2.isDamaging()) {
                chatGPT.looseLife();
                System.out.println("shimmer in chatGPT");
                shimmerCharacter(chatGPT);
                checkGameOver();
                if (isCollisionWithMob(mob1)) freezeMob(mob1);
                if (isCollisionWithMob(mob2)) freezeMob(mob2);
                //chatGPT.setAlive(false);

            }
            // Checking if character is in teleport
            if (isInside(teleport1, 0)) {
                teleport1.teleportCharacter();

            } else if (isInside(teleport2, 0)) {
                teleport2.teleportCharacter();
            }
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

    public void moveMob(Mob character, int d) {
        int direction = character.isReverse() ? -1 : 1;
        int newX = character.getX();
        int newY = character.getY();
        if (character.getDirection() == 0) {
            newY = character.getY() + d * direction;
        } else if (character.getDirection() == 1) {
            newX = character.getX() + d * direction;
        }

        if (chatGPT.isAlive()) {
            if (!character.isFrozen()) { // Check if the mob is not frozen
                if (isPositionValid(character, newX, newY)) {
                    character.move(d * direction);
                } else {
                    character.turnAround();
                }
                if (isCollisionWithMob(character) && character.isDamaging()) {
                    chatGPT.looseLife();
                    System.out.println("Lives left (mob): " + chatGPT.getLives());
                    System.out.println("shimmer in mob");
                    shimmerCharacter(chatGPT);
                    checkGameOver();
                    freezeMob(character);
                }
                repaint();
            }
        }
    }
    private void checkGameOver(){
        if (!chatGPT.isAlive()) {
            gameOver =true;
            int answer = JOptionPane.showConfirmDialog(null, "Вот и помер дед максим..", "лох", JOptionPane.YES_NO_OPTION);
            if (answer==0) {
                setScene();
                gameOver = false;
                repaint();
            }
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
        slidingDoor.move();
        repaint();
    }
    public void moveRotatingDoor(){
        rotatingDoor.move();
        repaint();
    }

    boolean isPositionValid(Character character, int x, int y) {
        int chW = character.getWidth();
        int chH = character.getHeight();
        if (x < WALL_WIDTH || x + chW  > mazeWidth - WALL_WIDTH ||
                y < WALL_WIDTH || y + chH > mazeHeight - WALL_WIDTH) {
            // Character is outside the maze boundaries
            return false;
        }
        // Check if the character is colliding with any maze walls
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int wallX = maze[row][col].x;
                int wallY = maze[row][col].y;


                if (maze[row][col].T == 1 && isRectCollision(x, y, chW, chH, wallX, wallY, WALL_WIDTH + CORRIDOR_WIDTH, WALL_WIDTH)) {
                    // Collision with north wall
                    return false;
                }
                if (maze[row][col].R == 1 && isRectCollision(x, y, chW, chH, wallX + WALL_WIDTH + CORRIDOR_WIDTH, wallY, WALL_WIDTH, WALL_WIDTH + CORRIDOR_WIDTH)) {
                    // Collision with east wall
                    return false;
                }
                if (maze[row][col].B == 1 && isRectCollision(x, y, chW, chH, wallX, wallY + WALL_WIDTH + CORRIDOR_WIDTH, WALL_WIDTH + CORRIDOR_WIDTH, WALL_WIDTH)) {
                    // Collision with south wall
                    return false;
                }
                if (maze[row][col].L == 1 && isRectCollision(x, y, chW, chH, wallX, wallY, WALL_WIDTH, WALL_WIDTH + CORRIDOR_WIDTH)) {
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
    private boolean isCollisionWithMob(Mob mob) {

        // Calculate the boundaries of the character
        int characterTop = chatGPT.getY();
        int characterBottom = chatGPT.getY() + chatGPT.getHeight();
        int characterLeft = chatGPT.getX();
        int characterRight = chatGPT.getX() + chatGPT.getWidth();

        // Calculate the boundaries of the mob
        int mobTop = mob.getY();
        int mobBottom = mob.getY() + mob.getHeight();
        int mobLeft = mob.getX();
        int mobRight = mob.getX()+ mob.getWidth();

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Game");
        MazeGame game = new MazeGame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(game);
        frame.pack();
        frame.setVisible(true);

        // Register arrow key listeners to move the character
        frame.addKeyListener(new ArrowKeyListener(game));
        frame.requestFocus();
    }
    class Item {
        private int x;
        private int y;
        private int width;
        private int height;
        private Image characterImage;
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public Image getCharacterImage() {
            return characterImage;
        }
        private void loadCharacterImage(String image) {
            ImageIcon icon = new ImageIcon("images/"+image); // Replace with the path to your character image file
            characterImage = icon.getImage();
            width = icon.getIconWidth();
            height = icon.getIconHeight();
            //System.out.println("w: "+width+", h:"+ height);
        }

        public Item(String itemImage, int x, int y) {
            this.x = x;
            this.y = y;
            loadCharacterImage(itemImage);
        }
        public void remove(){
            characterImage = null;
        }
    }
    class SlidingDoorButton extends Item {

        public SlidingDoorButton(int x, int y) {
            super("doorButton.png", x, y);

        }

    }
    class Teleport extends Item {
        private int destinationX;
        private int destinationY;



        public Teleport(int x, int y, int destinationX, int destinationY) {
            super("teleport.png", x, y);
            this.destinationX = destinationX;
            this.destinationY = destinationY;

        }

        public void teleportCharacter(){
            chatGPT.setX(destinationX);
            chatGPT.setY(destinationY);
        }

    }
    class SlidingDoor {
        private int originRow;
        private int originCol;
        private int x;
        private int y;
        private boolean horizontal;
        private Image characterImage;
        private int destinationX;
        private int destinationY;
        private int destinationRow;
        private int destinationCol;
        private int originX;
        private int originY;


        public SlidingDoor(int originCol , int originRow, int destinationCol, int destinationRow, boolean horizontal) {
            this.originCol = originCol;
            this.originRow = originRow;
            this.destinationCol= destinationCol;
            this.destinationRow = destinationRow;
            maze[originRow][originCol].setT(1);
            maze[destinationRow][destinationCol].setT(0);
            this.horizontal = horizontal;
            calculateCoordinates();
            x = originX;
            y = originY;


        }
        private void calculateCoordinates(){
            originX = (horizontal? WALL_WIDTH: 0) + originCol*(CORRIDOR_WIDTH+WALL_WIDTH);;
            originY = (horizontal? 0: WALL_WIDTH) + originRow*(CORRIDOR_WIDTH+WALL_WIDTH);
            destinationX = (horizontal? WALL_WIDTH: 0) + destinationCol*(CORRIDOR_WIDTH+WALL_WIDTH);
            destinationY =(horizontal? 0: WALL_WIDTH) + destinationCol*(CORRIDOR_WIDTH+WALL_WIDTH);
        }
        public void drawSlidingDoor(Graphics g){
            g.setColor(Color.magenta);
            if (horizontal){
                g.fillRect(x, y, CORRIDOR_WIDTH, WALL_WIDTH);
            } else {
                g.fillRect(x, y, WALL_WIDTH, CORRIDOR_WIDTH);
            }

        }
        public void move() {
            int delay = 300; // Delay in milliseconds
            int increment = 5; // Amount to move in each step
            int steps = (Math.abs(destinationX - originX)) / increment; // Calculate the number of steps needed

            Timer timer = new Timer(delay / steps, new ActionListener() {
                int stepCount = 0;

                public void actionPerformed(ActionEvent e) {
                    if (horizontal) {
                        maze[originRow][originCol].setT(0);
                        System.out.println();
                        if (destinationX < originX) {

                            if (x>destinationX) x -= increment;
                            else x = destinationX;
                        } else if (destinationX > originX) {

                            if (x<destinationX) x += increment;
                            else x = destinationX;
                        }
                    }

                    stepCount++;
                    if (stepCount >= steps) {
                        ((Timer) e.getSource()).stop(); // Stop the timer when the animation is complete
                        //maze[originRow][originCol].setT(0);
                        maze[destinationRow][destinationCol].setT(1);
                        int temp = destinationCol;
                        destinationCol = originCol;
                        originCol = temp;
                        calculateCoordinates();
                    }

                    repaint(); // Repaint the component to update the position
                }
            });

            timer.start(); // Start the timer to initiate the animation
        }


    }
    class RotatingDoor {
        private int row;
        private int col;
        private int x;
        private int y;
        private int width;
        private int height;
        private boolean horizontal;
        private Image characterImage;
        private int destinationSide;
        private int originSide;
        private int originX;
        private int originY;
        private int angle;
        private int currentAngle;
        private long startTime;
        private int closed;

        public RotatingDoor(int row, int col, int originSide, int destinationSide) {
            this.row = row;
            this.col = col;
            this.originSide = originSide;
            this.destinationSide = destinationSide;
            changeWall(originSide, 1);
            calculateCoordinates();
            x = originX;
            y = originY;
            currentAngle = 0;
            closed=1;
            startTime = System.currentTimeMillis();
        }

        private void calculateCoordinates(){
            switch (originSide){
                case 0: {//left side
                    originX = col*(CORRIDOR_WIDTH+WALL_WIDTH);
                    originY = row*(CORRIDOR_WIDTH+WALL_WIDTH);
                    width = WALL_WIDTH;
                    height = CORRIDOR_WIDTH+WALL_WIDTH;
                    horizontal = false;
                    if (destinationSide==3) angle=90;
                    else if(destinationSide==1) angle=-90;
                    break;
                }
                case 1: {// top side
                    originX  = col*(CORRIDOR_WIDTH+WALL_WIDTH);
                    originY = row*(CORRIDOR_WIDTH+WALL_WIDTH);
                    width = CORRIDOR_WIDTH+WALL_WIDTH;
                    height = WALL_WIDTH;
                    horizontal = true;
                    if (destinationSide==0) angle=90;
                    else if(destinationSide==2) angle=-90;
                    break;
                }
                case 2: {// right side
                    originX  = (col+1)*(CORRIDOR_WIDTH+WALL_WIDTH);
                    originY = row*(CORRIDOR_WIDTH+WALL_WIDTH);
                    width = WALL_WIDTH;
                    height = CORRIDOR_WIDTH+WALL_WIDTH;
                    horizontal = false;
                    if (destinationSide==3) angle=-90;
                    else if(destinationSide==1) angle=90;
                    break;
                }
                case 3: { // bottom side
                    originX  = col*(CORRIDOR_WIDTH+WALL_WIDTH);
                    originY = (row+1)*(CORRIDOR_WIDTH+WALL_WIDTH);
                    width = CORRIDOR_WIDTH+WALL_WIDTH;
                    height = WALL_WIDTH;
                    if (destinationSide==0) angle=-90;
                    else if(destinationSide==2) angle=90;
                    horizontal = true;
                    break;
                }
            }

        }
        public void drawRotatingDoor(Graphics g) {
            g.setColor(Color.gray);

            // Save the original transform
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform originalTransform = g2d.getTransform();

            // Set the rotation transform based on the current angle and bottom-left origin
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(currentAngle), x, y + height);
            g2d.transform(transform);

            // Draw the rotated door
            g.fillRect(x, y, width, height);

            // Restore the original transform
            g2d.setTransform(originalTransform);
        }
        public void move() {
            int delay = 100; // Delay in milliseconds
            int increment = 5; // Amount to move in each step
            int steps = (angle) / increment; // Calculate the number of steps needed
            changeWall(originSide, 0);
            Timer timer = new Timer(delay / steps, new ActionListener() {
                int stepCount = 0;
                int angleTemp = angle; // Temporary angle
                public void actionPerformed(ActionEvent e) {
                    if (currentAngle >= angleTemp) {
                        currentAngle = angleTemp; // Animation complete, set the final angle


                    } else {
                            currentAngle += increment; // Calculate the current angle based on progress
                    }



                    stepCount++;
                    if (stepCount >= steps) {
                        ((Timer) e.getSource()).stop(); // Stop the timer when the animation is complete
                        changeWall(destinationSide, 1);
                        calculateCoordinates();
                    }

                    repaint(); // Repaint the component to update the position
                }
            });

            timer.start(); // Start the timer to initiate the animation
        }

        private void changeWall(int wall, int value) {
            switch (wall) {
                case 0:
                    maze[row][col].setL(value);
                    break;
                case 1:
                    maze[row][col].setT(value);
                    break;
                case 2:
                    maze[row][col].setR(value);
                    break;
                case 3:
                    maze[row][col].setB(value);
                    break;
            }
        }

    }
    class Cell {
        private int T; //has top border?
        private int R; //has right border?
        private int B; //has bottom border?
        private int L; //has left border?
        private int x; //top left corner of border
        private int y; //top left corner of border
        private int row; //index of cell's row
        private int col; //index of cell's col

        public int getT() {
            return T;
        }

        public void setT(int t) {
            T = t;
        }

        public int getR() {
            return R;
        }

        public void setR(int r) {
            R = r;
        }

        public int getB() {
            return B;
        }

        public void setB(int b) {
            B = b;
        }

        public int getL() {
            return L;
        }

        public void setL(int l) {
            L = l;
        }

        public void setCoordinates(int row, int col) {
            this.row = row;
            this.col = col;
            x = col * (WALL_WIDTH + CORRIDOR_WIDTH);
            y = row * (WALL_WIDTH + CORRIDOR_WIDTH);
        }
        public Cell(int t, int r, int b, int l, int row, int col) {
            T = t;
            R = r;
            B = b;
            L = l;
            this.row = row;
            this.col = col;
            x = col * (WALL_WIDTH + CORRIDOR_WIDTH);
            y = row * (WALL_WIDTH + CORRIDOR_WIDTH);
        }
        public Cell(int t, int r, int b, int l) {
            T = t;
            R = r;
            B = b;
            L = l;

        }
    }
}


