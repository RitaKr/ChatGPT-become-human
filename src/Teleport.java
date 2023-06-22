public class Teleport extends Item {
    private int destinationX;
    private int destinationY;
    private int destinationCol;
    private int destinationRow;
    private Side entranceSide;

    /**
     * Створює новий об'єкт телепорту.
     *
     * @param image            шлях до зображення телепорту
     * @param row              рядок, в якому розташований телепорт
     * @param col              стовпець, в якому розташований телепорт
     * @param destinationRow   рядок, в якому знаходиться пункт призначення телепорту
     * @param destinationCol   стовпець, в якому знаходиться пункт призначення телепорту
     * @param entranceSide     сторона, з якої гравець входить в телепорт
     */
    public Teleport(String image, int row, int col, int destinationRow, int destinationCol, Side entranceSide) {
        super(image, row, col, 100, 100);
        this.destinationCol = destinationCol;
        this.destinationRow = destinationRow;
        this.entranceSide = entranceSide;

        calculateCoordinates(MazeGame.chatGPT.getWidth(), MazeGame.chatGPT.getHeight());

    }

    /**
     * Телепортує персонажа до пункту призначення телепорту.
     */
    public void teleportCharacter(){
        MazeGame.chatGPT.setX(destinationX);
        MazeGame.chatGPT.setY(destinationY);
    }

    /**
     * Обчислює координати телепорту для зазначеної ширини та висоти.
     *
     * @param width  ширина телепорту
     * @param height висота телепорту
     */
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
