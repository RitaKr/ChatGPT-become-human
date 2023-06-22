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

    /**
     * Завантажує зображення персонажа з вказаного шляху із файлової системи.
     * Встановлює завантажене зображення персонажа, а також отримує його ширину та висоту.
     *
     * @param image шлях до зображення персонажа
     */
    public void loadCharacterImage(String image) {
        ImageIcon icon = new ImageIcon("images/"+image); // Replace with the path to your character image file
        characterImage = icon.getImage();
        width = icon.getIconWidth();
        height = icon.getIconHeight();
        //System.out.println("w: "+width+", h:"+ height);
    }

    /**
     * Створює новий об'єкт типу Item з вказаним зображенням елемента.
     * Завантажує зображення елемента з вказаного шляху із файлової системи.
     *
     * @param itemImage шлях до зображення елемента
     */
    public Item(String itemImage) {
        loadCharacterImage(itemImage);
    }

    /**
     * Створює новий об'єкт типу Item з вказаними параметрами.
     * Завантажує зображення елемента з вказаного шляху із файлової системи.
     *
     * @param itemImage шлях до зображення елемента
     * @param row       рядок, в якому розташований елемент
     * @param col       стовпець, в якому розташований елемент
     * @param width     ширина елемента
     * @param height    висота елемента
     */
    public Item(String itemImage, int row, int col, int width, int height) {
        this.row = row;
        this.col = col;
        loadCharacterImage(itemImage);
        this.width = width;
        this.height = height;
        calculateCoordinates();

    }

    /**
     * Обчислює координати (x, y) елемента на основі його розташування в рядку та стовпці, а також розмірів елемента та розмірів комірки і стінки лабіринту.
     */
    private void calculateCoordinates(){
        x = col*(Maze.cellSize + Maze.wallSize) + Maze.wallSize + (Maze.cellSize -width)/2;
        y = row*(Maze.cellSize + Maze.wallSize) + Maze.wallSize + (Maze.cellSize -height)/2;
    }

    /**
     * Конструктор класу Item без параметрів.
     * Використовується для створення порожнього об'єкта Item без зазначення зображення та координат.
     */
    public Item() {
    }

    /**
     * Метод, який показує зображення предмета на графічному контексті.
     * Якщо предмет видимий, то показується його зображення на вказаному графічному контексті.
     *
     * @param g Графічний контекст, на якому буде показуватися зображення предмета.
     */
    public void draw(Graphics g) {
        if (visible) g.drawImage(characterImage, x, y, width, height,null);
    }

}
