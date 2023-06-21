import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlidingDoor extends Item{
    private int originRow;
    private int originCol;
    private final boolean horizontal;
    private int destinationX;
    private int destinationY;
    private int destinationRow;
    private int destinationCol;
    private int originX;
    private int originY;
    private Maze settings;

    public Maze getSettings(){
        return settings;
    }
    public SlidingDoor(Maze settings, int originCol , int originRow, int destinationCol, int destinationRow, boolean horizontal) {
        super("sliding-door.png");
        this.originCol = originCol;
        this.originRow = originRow;
        this.destinationCol= destinationCol;
        this.destinationRow = destinationRow;
        this.settings = settings;
        settings.maze[originRow][originCol].setT(1);
        settings.maze[destinationRow][destinationCol].setT(0);
        this.horizontal = horizontal;
        super.setWidth((horizontal? Maze.cellSize : Maze.wallSize)+10);
        super.setHeight((horizontal? Maze.wallSize : Maze.cellSize)+10);
        calculateCoordinates();
        super.setX(originX);
        super.setY(originY);


    }
    private void calculateCoordinates(){
        originX = (horizontal? Maze.wallSize : 0) + originCol*(Maze.cellSize + Maze.wallSize)-5;
        originY = (horizontal? 0: Maze.wallSize) + originRow*(Maze.cellSize + Maze.wallSize)-5;
        destinationX = (horizontal? Maze.wallSize : 0) + destinationCol*(Maze.cellSize + Maze.wallSize)-5;
        destinationY =(horizontal? 0: Maze.wallSize) + destinationCol*(Maze.cellSize + Maze.wallSize)-5;

    }
    @Override
    public void draw(Graphics g){
//        g.setColor(new Color(84, 0, 255));
//        g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        super.draw(g);
    }
    public void move() {
        int delay = 400; // Delay in milliseconds
        int increment = 10; // Amount to move in each step
        int steps = (Math.abs(destinationX - originX)) / increment; // Calculate the number of steps needed

        Timer timer = new Timer(delay / steps, new ActionListener() {
            int stepCount = 0;

            public void actionPerformed(ActionEvent e) {
                if (horizontal) {
                    settings.maze[originRow][originCol].setT(0);
                    if (destinationX < originX) {

                        if (SlidingDoor.super.getX()>destinationX) SlidingDoor.super.setX(SlidingDoor.super.getX()-increment);
                        else SlidingDoor.super.setX(destinationX);
                    } else if (destinationX > originX) {

                        if (SlidingDoor.super.getX()<destinationX) SlidingDoor.super.setX(SlidingDoor.super.getX()+increment);
                        else SlidingDoor.super.setX(destinationX);
                    }
                } else {
                    settings.maze[originRow][originCol].setL(0);
                    if (destinationY < originY) {

                        if (SlidingDoor.super.getY()>destinationY) SlidingDoor.super.setY(SlidingDoor.super.getY()-increment);
                        else SlidingDoor.super.setY(destinationY);
                    } else if (destinationY > originY) {

                        if (SlidingDoor.super.getY()<destinationY) SlidingDoor.super.setY(SlidingDoor.super.getY()+increment);
                        else SlidingDoor.super.setY(destinationY);
                    }
                }

                stepCount++;
                if (stepCount >= steps) {
                    ((Timer) e.getSource()).stop(); // Stop the timer when the animation is complete
                    //maze[originRow][originCol].setT(0);
                    if (horizontal) {
                        settings.maze[destinationRow][destinationCol].setT(1);
                        int temp = destinationCol;
                        destinationCol = originCol;
                        originCol = temp;
                    } else {
                        settings.maze[destinationRow][destinationCol].setL(1);
                        int temp = destinationRow;
                        destinationRow = originRow;
                        originRow = temp;
                    }

                    calculateCoordinates();
                }

                //repaint(); // Repaint the component to update the position
            }
        });

        timer.start(); // Start the timer to initiate the animation
    }


}