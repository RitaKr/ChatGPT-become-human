import javax.swing.*;
import java.awt.*;

public class Item {
    private int x;
    private int y;
    private int col;
    private int row;
    private int width;
    private int height;
    private Image characterImage;
    private boolean visible = true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setCharacterImage(Image characterImage) {
        this.characterImage = characterImage;
    }

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
    public void loadCharacterImage(String image) {
        ImageIcon icon = new ImageIcon("images/"+image); // Replace with the path to your character image file
        characterImage = icon.getImage();
        width = icon.getIconWidth();
        height = icon.getIconHeight();
        //System.out.println("w: "+width+", h:"+ height);
    }

//    public Item(String itemImage, int x, int y) {
//        this.x = x;
//        this.y = y;
//        loadCharacterImage(itemImage);
//    }
    public Item(String itemImage) {
        loadCharacterImage(itemImage);
    }
    public Item(String itemImage, int row, int col) {
        this.row = row;
        this.col = col;
        loadCharacterImage(itemImage);
        calculateCoordinates();

    }
    public Item(String itemImage, int row, int col, int width, int height) {
        this.row = row;
        this.col = col;
        loadCharacterImage(itemImage);
        this.width = width;
        this.height = height;
        calculateCoordinates();

    }
    public Item(int row, int col, int width, int height) {
        this.row = row;
        this.col = col;
        this.width = width;
        this.height = height;
        calculateCoordinates();
    }
    private void calculateCoordinates(){
        x = col*(Maze.cellSize + Maze.wallSize) + Maze.wallSize + (Maze.cellSize -width)/2;
        y = row*(Maze.cellSize + Maze.wallSize) + Maze.wallSize + (Maze.cellSize -height)/2;
    }
    public Item() {
    }
    public void remove(){
        characterImage = null;
    }
    public void draw(Graphics g) {
        if (visible) g.drawImage(characterImage, x, y, width, height,null);
    }

}
