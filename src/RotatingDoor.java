import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class RotatingDoor extends Item{
    private int row;
    private int col;

    private Side destinationSide;
    private Side originSide;
    private Corner rotationCorner;
    private int originX;
    private int originY;
    private int angle = 90;
    private int currentAngle;
    private boolean clockwise;
    private boolean reverse = false;
    private Maze settings;

    public Maze getSettings(){
        return settings;
    }

    /**
     * Створює об'єкт обертальної двері в лабіринті.
     *
     * @param settings    налаштування лабіринту
     * @param row         рядок, де розташована двера
     * @param col         стовпчик, де розташована двера
     * @param originSide  початкова сторона, з якої починається обертання двері
     * @param clockwise   чи здійснювати обертання за годинниковою стрілкою
     */
    public RotatingDoor(Maze settings, int row, int col, Side originSide, boolean clockwise) {
        super();

        this.row = row;
        this.col = col;
        this.originSide = originSide;
        this.clockwise = clockwise;
        this.settings = settings;
        changeWall(originSide, 1);
        setRotationSettings();
        calculateCoordinates();
        super.setX(originX);
        super.setY(originY);

        currentAngle = 0;
    }

    /**
     * Обчислює координати обертальної двері в залежності від початкової сторони.
     */
    private void calculateCoordinates(){
        switch (originSide){
            case LEFT: {
                originX = col*(Maze.cellSize + Maze.wallSize)-10;
                originY = (destinationSide==Side.BOTTOM ? Maze.wallSize : 0)+ row*(Maze.cellSize + Maze.wallSize)-5;
                break;
            }
            case TOP: {
                originX = (destinationSide==Side.LEFT ? Maze.wallSize : 0) +  col*(Maze.cellSize + Maze.wallSize)-5;
                originY = row*(Maze.cellSize + Maze.wallSize)-10;
                break;
            }
            case RIGHT: {
                originX  = (col+1)*(Maze.cellSize + Maze.wallSize)-10;
                originY = (destinationSide==Side.TOP ? Maze.wallSize : 0) + row*(Maze.cellSize + Maze.wallSize)-5;
                break;
            }
            case BOTTOM: {
                originX  =  col*(Maze.cellSize + Maze.wallSize)-10;
                originY = (row+1)*(Maze.cellSize + Maze.wallSize)-10;
                break;
            }
        }

        super.setWidth((originSide==Side.RIGHT || originSide==Side.LEFT) ? (Maze.wallSize+10) : (Maze.cellSize + Maze.wallSize + 15));
        super.setHeight((originSide==Side.RIGHT || originSide==Side.LEFT) ? (Maze.cellSize + Maze.wallSize + 15) : (Maze.wallSize+10));
        if (originSide==Side.LEFT || originSide==Side.RIGHT) super.loadCharacterImage("rotating-door-vertical.png");
        else super.loadCharacterImage("rotating-door.png");

    }

    /**
     * Встановлює налаштування обертання в залежності від напрямку та початкової сторони.
     */
    private void setRotationSettings(){
        if (clockwise && originSide==Side.TOP || !clockwise && originSide==Side.BOTTOM) destinationSide=Side.LEFT;
        else if (clockwise && originSide==Side.BOTTOM || !clockwise && originSide==Side.TOP) destinationSide=Side.RIGHT;
        else if (clockwise && originSide==Side.LEFT || !clockwise && originSide==Side.RIGHT)  destinationSide=Side.BOTTOM;
        else if (clockwise && originSide==Side.RIGHT || !clockwise && originSide==Side.LEFT)  destinationSide=Side.TOP;

        if (destinationSide==Side.TOP && originSide==Side.LEFT || destinationSide==Side.LEFT && originSide==Side.TOP) rotationCorner=Corner.topLeft;
        else if (destinationSide==Side.TOP && originSide==Side.RIGHT || destinationSide==Side.RIGHT && originSide==Side.TOP) rotationCorner=Corner.topRight;
        else if (destinationSide==Side.BOTTOM && originSide==Side.LEFT) rotationCorner=Corner.bottomRight;
        else if (destinationSide==Side.LEFT && originSide==Side.BOTTOM) rotationCorner=Corner.topLeft;
        else if (destinationSide==Side.BOTTOM && originSide==Side.RIGHT || destinationSide==Side.RIGHT && originSide==Side.BOTTOM) rotationCorner=Corner.bottomLeft;
    }

    /**
     * Намалювати об'єкт обертальних дверей.
     *
     * @param g Графічний контекст, на якому буде показуватися зображення предмета.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 212, 255));

        // Save the original transform
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();

        // Set the rotation transform based on the current angle and bottom-left origin
        AffineTransform transform = new AffineTransform();
        switch (rotationCorner) {
            case topLeft -> transform.rotate(Math.toRadians(currentAngle), super.getX()+15, super.getY()+15);
            case topRight -> transform.rotate(Math.toRadians(currentAngle), super.getX()+super.getWidth(), super.getY());
            case bottomRight -> transform.rotate(Math.toRadians(currentAngle), super.getX()+super.getWidth()-15, super.getY() + super.getHeight()-15);
            case bottomLeft -> transform.rotate(Math.toRadians(currentAngle), super.getX(), super.getY() + super.getHeight());


        }

        g2d.transform(transform);

        // Draw the rotated door
        super.draw(g);
        //g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());

        // Restore the original transform
        g2d.setTransform(originalTransform);
    }

    /**
     * Здійснити рух об'єкта обертальних дверей.
     */
    public void move() {
        int delay = 100; // Delay in milliseconds
        int increment = 5; // Amount to move in each step
        int steps = Math.abs((angle) / increment); // Calculate the number of steps needed


        if (reverse) changeWall(destinationSide, 0);
        else changeWall(originSide, 0);
        Timer timer = new Timer(delay / steps, new ActionListener() {
            int stepCount = 0;
            int direction = clockwise ? 1 : -1;
            int angleTemp = angle*direction;; // Temporary angle
            public void actionPerformed(ActionEvent e) {
                if (!reverse){
                    if (Math.abs(currentAngle) >= Math.abs(angleTemp)) {
                        currentAngle = angleTemp; // Animation complete, set the final angle
                    } else {
                        currentAngle += increment*direction; // Calculate the current angle based on progress
                    }
                } else {
                    if (Math.abs(currentAngle) <= 0) {
                        currentAngle = 0; // Animation complete, set the final angle
                    } else {
                        currentAngle -= increment*direction; // Calculate the current angle based on progress
                    }
                }




                stepCount++;
                if (stepCount > steps) {
                    ((Timer) e.getSource()).stop(); // Stop the timer when the animation is complete
                    direction*=-1;
                    reverse=!reverse;

                    if (reverse) changeWall(destinationSide, 1);
                    else changeWall(originSide, 1);
                    calculateCoordinates();
                }

                //repaint(); // Repaint the component to update the position
            }
        });

        timer.start(); // Start the timer to initiate the animation
    }

    /**
     * Змінити стан стіни в лабіринті.
     * @param wall Сторона стіни, яку потрібно змінити.
     * @param value Нове значення стіни (0, якщо її немає, або 1, якщо є).
     */
    private void changeWall(Side wall, int value) {
        switch (wall) {
            case LEFT:
                settings.maze[row][col].setL(value);
                break;
            case TOP:
                settings.maze[row][col].setT(value);
                break;
            case RIGHT:
                settings.maze[row][col].setR(value);
                break;
            case BOTTOM:
                settings.maze[row][col].setB(value);
                break;
        }
    }

}
