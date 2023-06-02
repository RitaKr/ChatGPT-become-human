public class Mob extends Character{
    private int direction; //0 vertical, 1 horizontal
    private int x, y;
    private boolean collapsed = false;
    private boolean reverse = false;

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

    public Mob(String image, int speed, int x, int y, int direction) {
        super(image, speed, x, y, true);
        this.x=x;
        this.y=y;
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
