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

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public Mob(String image, int speed, int row, int col, int direction) {
        super(image, speed, row, col, true);
        this.direction = direction;
    }

    public void move(int d) {
        if (direction==0) {
            super.setY(super.getY()+d);
            //y-=d;
        } else if (direction==1) {
            super.setX(super.getX()+d);
            //x+=d;
        }

    }
    public void turnAround(){
        reverse=!reverse;
    }
}
