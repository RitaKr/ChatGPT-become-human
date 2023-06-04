public class Teleport extends Item {
    private int destinationX;
    private int destinationY;
    private int destinationCol;
    private int destinationRow;
    private Side entranceSide;

    public Teleport(int row, int col, int destinationRow, int destinationCol, Side entranceSide) {
        super("teleport.png", row, col);
        this.destinationCol = destinationCol;
        this.destinationRow = destinationRow;
        this.entranceSide = entranceSide;
        calculateCoordinates(MazeGame.chatGPT.getWidth(), MazeGame.chatGPT.getHeight());

    }

    public void teleportCharacter(){
        MazeGame.chatGPT.setX(destinationX);
        MazeGame.chatGPT.setY(destinationY);
    }
    public void calculateCoordinates(int width, int height){
        destinationX = destinationCol*(Maze.cellSize + Maze.wallSize) + Maze.wallSize + (Maze.cellSize -width)/2;
        destinationY = destinationRow*(Maze.cellSize + Maze.wallSize) + Maze.wallSize + (Maze.cellSize -height)/2;
        switch (entranceSide) {
            case TOP -> destinationY-=20;
            case BOTTOM -> destinationY+=20;
            case LEFT -> destinationX-=20;
            case RIGHT -> destinationX+=20;
        }
    }

}
