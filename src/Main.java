import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;

public class Main {
    private static ProgressData progress;
    static MazeUI mazeUI;
    public static ProgressData getProgress() {
        return progress;
    }
    static MainMenuUI mainMenuUI = new MainMenuUI();
    static ChatUI chatUI;
    static ChooseMazeUI chooseMazeUI;
    static InstructionUI instructionUI;
    static MediaPlayer backgroundMediaPlayer;


    public static void startMazeGame(){
        pauseMusic();
        mazeUI = new MazeUI();
        mazeUI.setVisible(true);
    }
    public static void startMazeGame(int level){
        stopMusic();
        mazeUI = new MazeUI(level);
        mazeUI.setVisible(true);
    }

    public static void main(String[] args) {
        fetchProgress();
        //System.out.println("current progress: \n"+progress);
        chatUI = new ChatUI();
        chooseMazeUI = new ChooseMazeUI();
        instructionUI = new InstructionUI();
        //mazeUI = new MazeUI();
        //startMazeGame();
        mainMenuUI.setVisible(true);
        setMusic("background-music.mp3", 0.1);
        playMusic();
    }
    private static void updateUsername(String newName) {
        progress.setUsername(newName);
        updateProgress();
    }
    public static void updateLevel() {
        int level = progress.getLv()<3 ? (progress.getLv()+1) : progress.getLv();
        progress.setLv(level);
        updateProgress();
    }
    public static void setLevel(int level) {
        progress.setLv(level);
        updateProgress();
    }
    public static void setAlive(boolean alive) {
        progress.setAlive(alive);
        updateProgress();
    }
    public static void updateChatData(ChatData chatData) {

        progress.setChapter1(chatData.chapter.toString());
        updateProgress();
    }

    public static void updateProgress(){
        ////System.out.println("current progress (update): \n"+progress);
        String progressString = "username:"+progress.getUsername()+"; lv:"+progress.getLv()+"; alive:"+progress.isAlive()+"; chapter1:"+progress.getChapter1()+"";
        writeFile("progress.txt", progressString);

    }
    public static void resetProgress(){
        ////System.out.println("current progress (update): \n"+progress);
        String progressString = "username:player; lv:0; alive:true; chapter1:0-2";
        writeFile("progress.txt", progressString);

    }
    public static void fetchProgress(){
        String filePath = "progress.txt"; // Replace with your file path

        String fileContent = readFile(filePath);
        if (fileContent.isEmpty()) fileContent = "username:player; lv:0; alive:true; chapter1:0-2";
        ////System.out.println(fileContent);
        progress = new ProgressData(fileContent);

    }
    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMusic(String path, double volume) {
        // Initialize JavaFX environment
        new JFXPanel();


        // Create a File object with the MP3 file
        File musicFile = new File("music/" +path);

        // Create a Media object with the File object
        Media media = new Media(musicFile.toURI().toString());

        // Create a MediaPlayer object to play the media
        backgroundMediaPlayer = new MediaPlayer(media);

        // Configure the MediaPlayer to loop the music
        backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Start playing the music
        backgroundMediaPlayer.setVolume(volume);


    }
    public static void playMusic() {
        if (backgroundMediaPlayer!=null)backgroundMediaPlayer.play();

    }
    public static void pauseMusic() {

        if (backgroundMediaPlayer!=null) backgroundMediaPlayer.pause();
    }
    public static void stopMusic() {

        if (backgroundMediaPlayer!=null) backgroundMediaPlayer.stop();
    }
}

