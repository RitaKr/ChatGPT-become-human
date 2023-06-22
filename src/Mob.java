public class Mob extends Character {
    private int direction; //0 vertical, 1 horizontal
    private boolean collapsed = false;
    private boolean reverse = false;
    private boolean damaging = true;
    private boolean frozen = false;

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isDamaging() {
        return damaging;
    }

    public void setDamaging(boolean damaging) {
        this.damaging = damaging;
    }

    public int getDirection() {
        return direction;
    }
    public boolean isReverse() {
        return reverse;
    }

    /**
     * Створює об'єкт моба з заданими параметрами.
     * @param image шлях до зображення моба
     * @param speed швидкість руху моба
     * @param row рядок, в якому знаходиться моб на початку
     * @param col стовпець, в якому знаходиться моб на початку
     * @param direction напрямок руху моба
     */
    public Mob(String image, int speed, int row, int col, int direction) {
        super(image, speed, row, col, true);
        this.direction = direction;
    }

    /**
     * Здійснює рух моба у вказаному напрямку.
     * @param d відстань для переміщення в заданому напрямку
     */
    public void move(int d) {
        if (direction==0) {
            super.setY(super.getY()+d);
            //y-=d;
        } else if (direction==1) {
            super.setX(super.getX()+d);
            //x+=d;
        }
    }

    /**
     * Змінює напрямок руху моба на протилежний.
     */
    public void turnAround(){
        reverse=!reverse;
    }
}
