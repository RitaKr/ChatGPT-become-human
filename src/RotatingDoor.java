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

    public RotatingDoor(Maze settings, int row, int col, Side originSide, boolean clockwise) {
        super();
        this.row = row;
        this.col = col;
        this.originSide = originSide;
        this.clockwise = clockwise;
        this.settings = settings;
        changeWall(originSide, 1);
        calculateCoordinates();
        setRotationSettings();
        super.setX(originX);
        super.setY(originY);
        currentAngle = 0;
    }
    private void calculateCoordinates(){
        switch (originSide){
            case LEFT: {
                originX = col*(Maze.cellSize + Maze.wallSize);
                originY = (destinationSide==Side.TOP ? Maze.wallSize : 0) + row*(Maze.cellSize + Maze.wallSize);
                break;
            }
            case TOP: {
                originX = (destinationSide==Side.LEFT ? Maze.wallSize : 0) +  col*(Maze.cellSize + Maze.wallSize);
                originY = row*(Maze.cellSize + Maze.wallSize);
                break;
            }
            case RIGHT: {
                originX  = (col+1)*(Maze.cellSize + Maze.wallSize);
                originY = (destinationSide==Side.TOP ? Maze.wallSize : 0) + row*(Maze.cellSize + Maze.wallSize);
                break;
            }
            case BOTTOM: {
                originX  = (destinationSide==Side.LEFT ? Maze.wallSize : 0) + col*(Maze.cellSize + Maze.wallSize);
                originY = (row+1)*(Maze.cellSize + Maze.wallSize);
                break;
            }
        }

        super.setWidth((originSide==Side.RIGHT || originSide==Side.LEFT) ? Maze.wallSize : Maze.cellSize + Maze.wallSize);
        super.setHeight((originSide==Side.RIGHT || originSide==Side.LEFT) ? Maze.cellSize + Maze.wallSize : Maze.wallSize);

    }
    private void setRotationSettings(){
        if (clockwise && originSide==Side.TOP || !clockwise && originSide==Side.BOTTOM) destinationSide=Side.LEFT;
        else if (clockwise && originSide==Side.BOTTOM || !clockwise && originSide==Side.TOP) destinationSide=Side.RIGHT;
        else if (clockwise && originSide==Side.LEFT || !clockwise && originSide==Side.RIGHT)  destinationSide=Side.BOTTOM;
        else if (clockwise && originSide==Side.RIGHT || !clockwise && originSide==Side.LEFT)  destinationSide=Side.TOP;

        if (destinationSide==Side.TOP && originSide==Side.LEFT || destinationSide==Side.LEFT && originSide==Side.TOP) rotationCorner=Corner.topLeft;
        else if (destinationSide==Side.TOP && originSide==Side.RIGHT || destinationSide==Side.RIGHT && originSide==Side.TOP) rotationCorner=Corner.topRight;
        else if (destinationSide==Side.BOTTOM && originSide==Side.LEFT || destinationSide==Side.LEFT && originSide==Side.BOTTOM) rotationCorner=Corner.bottomLeft;
        else if (destinationSide==Side.BOTTOM && originSide==Side.RIGHT || destinationSide==Side.RIGHT && originSide==Side.BOTTOM) rotationCorner=Corner.bottomRight;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.gray);

        // Save the original transform
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();

        // Set the rotation transform based on the current angle and bottom-left origin
        AffineTransform transform = new AffineTransform();
        switch (rotationCorner) {
            case topLeft -> transform.rotate(Math.toRadians(currentAngle), super.getX(), super.getY());
            case topRight -> transform.rotate(Math.toRadians(currentAngle), super.getX()+super.getWidth(), super.getY());
            case bottomRight -> transform.rotate(Math.toRadians(currentAngle), super.getX()+super.getWidth(), super.getY() + super.getHeight());
            case bottomLeft -> transform.rotate(Math.toRadians(currentAngle), super.getX(), super.getY() + super.getHeight());


        }

        g2d.transform(transform);

        // Draw the rotated door
        g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());

        // Restore the original transform
        g2d.setTransform(originalTransform);
    }
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
