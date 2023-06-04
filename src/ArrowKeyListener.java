import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowKeyListener implements KeyListener{
    private final MazeGame game;
    private Character chatGPT;

    public ArrowKeyListener(MazeGame game) {
        this.game = game;
        this.chatGPT = game.getChatGPT();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Move the character based on the arrow key pressed
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                game.moveChatGPT(-chatGPT.getSpeed(), 0);
                break;
            case KeyEvent.VK_RIGHT:
                game.moveChatGPT(chatGPT.getSpeed(), 0);
                break;
            case KeyEvent.VK_UP:
                game.moveChatGPT(0, -chatGPT.getSpeed());
                break;
            case KeyEvent.VK_DOWN:
                game.moveChatGPT(0, chatGPT.getSpeed());
                break;
            case KeyEvent.VK_SPACE:
                if (game.slideDoorButton!=null && game.isCollisionWithMob(game.slideDoorButton)){
                    game.moveSlidingDoor();
                }
                if (game.key!=null && game.isCollisionWithMob(game.key)){
                    game.moveRotatingDoor();
                    game.key.remove();
                }
                break;
            case KeyEvent.VK_ENTER:
                game.moveRotatingDoor();

                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
