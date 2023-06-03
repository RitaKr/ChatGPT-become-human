import javax.swing.*;
import java.awt.*;

public class Character {
    private Image characterImage;
    private int speed = 8;
    private int x;
    private int y;
    private int lives = 3;
    private boolean alive = true;
    private int width;
    private int height;
    private boolean mob;
    private boolean visible = true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isMob() {
        return mob;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(Image characterImage) {
        this.characterImage = characterImage;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private void loadCharacterImage(String image) {
        ImageIcon icon = new ImageIcon("images/"+image); // Replace with the path to your character image file
        characterImage = icon.getImage();
        width = icon.getIconWidth();
        height = icon.getIconHeight();
        //System.out.println("w: "+width+", h:"+ height);
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Character(int speed, int x, int y, boolean mob) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.mob = mob;
        loadCharacterImage("chatGPT.png");
    }
    public Character(String image, int speed, int x, int y, boolean mob) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.mob = mob;
        loadCharacterImage(image);
    }
    public void move(int dx, int dy) {
        x+=dx;
        y+=dy;

    }
    public void looseLife(){
        if (lives>1) lives--;
        else alive=false;
    }

}
