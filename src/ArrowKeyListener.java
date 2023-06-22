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
                    game.playEffect("button.wav", 0.5);
                    game.moveSlidingDoor();

                }
                if (game.key!=null && game.isCollisionWithMob(game.key)){
                    game.playEffect("pickup.wav", 0.15);
                    game.moveRotatingDoor();
                    game.key=null;
                }
                if (game.quiz1Item !=null && game.isCollisionWithMob(game.quiz1Item)) {
                    boolean previouslyCompleted = game.quiz1.isCompleted();
                    game.quizWindow = new QuizWindow(Main.mazeUI, game.quiz1);
                    if (!previouslyCompleted) game.getBonus();
                    if (game.quiz3!=null && !game.quiz3.isCompleted()) game.quiz3 = game.getRandomQuiz();
                    if (game.quiz2!=null && !game.quiz2.isCompleted()) game.quiz2 = game.getRandomQuiz();
                    System.out.println("is completed: "+ game.quiz1.isCompleted() +", is answered correctly: "+ game.quizWindow.isAnsweredCorrectly());
                    System.out.println(Main.getLanguage().equals("en") ? game.quizes : game.quizesUkr);
                }
                if (game.quiz2Item !=null && game.isCollisionWithMob(game.quiz2Item)) {
                    boolean previouslyCompleted = game.quiz2.isCompleted();
                    game.quizWindow = new QuizWindow(Main.mazeUI, game.quiz2);
                    if (!previouslyCompleted) game.getBonus();
                    if (game.quiz3!=null && !game.quiz3.isCompleted()) game.quiz3 = game.getRandomQuiz();
                    if (game.quiz1!=null && !game.quiz1.isCompleted()) game.quiz1 = game.getRandomQuiz();

                    System.out.println("is completed: "+ game.quiz2.isCompleted() +", is answered correctly: "+ game.quizWindow.isAnsweredCorrectly());
                    System.out.println(Main.getLanguage().equals("en") ? game.quizes : game.quizesUkr);
                }
                if (game.quiz3Item !=null && game.isCollisionWithMob(game.quiz3Item)) {
                    boolean previouslyCompleted = game.quiz3.isCompleted();
                    game.quizWindow = new QuizWindow(Main.mazeUI, game.quiz3);
                    if (!previouslyCompleted) game.getBonus();

                    if (game.quiz1!=null && !game.quiz1.isCompleted()) game.quiz1 = game.getRandomQuiz();
                    if (game.quiz2!=null && !game.quiz2.isCompleted()) game.quiz2 = game.getRandomQuiz();
                    System.out.println("quiz3 is completed: "+ game.quiz3.isCompleted()+", is answered correctly: "+ game.quizWindow.isAnsweredCorrectly());
                    System.out.println(Main.getLanguage().equals("en") ? game.quizes : game.quizesUkr);
                }
                break;
            case KeyEvent.VK_ENTER:
                game.moveRotatingDoor();

                break;
            case KeyEvent.VK_SHIFT:
                if (game.isMusicPlaying()) {
                    // The MediaPlayer is currently playing
                    System.out.println("The track is playing");
                    game.pauseMusic();
                } else {
                    // The MediaPlayer is currently paused
                    System.out.println("The track is paused");
                    game.playMusic();
                }

                break;
            case KeyEvent.VK_ESCAPE: {
                Main.mazeUI.mazeCompleted = false;
                Main.mazeUI.confirmQuit();
                break;
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
