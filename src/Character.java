public class Character extends Item {

    private int speed = 8;

    private int lives;
    private boolean alive = true;
    private boolean mob;


    public boolean isMob() {
        return mob;
    }



    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }



    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Конструктор для створення об'єкта типу Character.
     *
     * @param speed швидкість персонажа
     * @param row рядок, у якому знаходиться персонаж
     * @param col стовпець, у якому знаходиться персонаж
     * @param mob прапорець, що позначає, чи є персонаж рухомим
     */
    public Character(int speed, int row, int col, boolean mob) {
        super("chatGPT.png", row, col, 60, 60);
        this.speed = speed;
        this.mob = mob;
        lives = 3;
    }

    /**
     * Конструктор для створення об'єкта типу Character.
     *
     * @param image шлях до зображення персонажа
     * @param speed швидкість персонажа
     * @param row рядок, у якому знаходиться персонаж
     * @param col стовпець, у якому знаходиться персонаж
     * @param mob прапорець, що позначає, чи є персонаж рухомим
     */
    public Character(String image, int speed, int row, int col, boolean mob) {
        super(image, row, col, 60, 60);
        this.speed = speed;
        lives = 3;
        this.mob = mob;
    }

    /**
     * Зміщує персонажа на вказані значення по горизонталі та вертикалі.
     *
     * @param dx зміщення по горизонталі
     * @param dy зміщення по вертикалі
     */
    public void move(int dx, int dy) {
        super.setX(super.getX() + dx);
        super.setY(super.getY() + dy);
    }

    /**
     * Зменшує кількість життів гравця на одне.
     * Якщо кількість життів стає рівною нулю, встановлює прапорець "alive" у значення false.
     */
    public void looseLife() {
        if (lives > 0) {
            lives--;
        }
        if (lives == 0) {
            alive = false;
        }
    }


}
