import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
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
    static SettingsUI settingsUI;
    static MediaPlayer backgroundMediaPlayer;
    static double volumeCoef, volumeCoef1;


    public static void startMazeGame(){
        pauseMusic();
        mazeUI = new MazeUI(volumeCoef, volumeCoef1);
        mazeUI.setVisible(true);
    }
    public static void startMazeGame(int level){
        pauseMusic();
        mazeUI = new MazeUI(level, volumeCoef, volumeCoef1);
        mazeUI.setVisible(true);
    }

    public static void main(String[] args) {
        fetchProgress();
        volumeCoef=1.0;
        volumeCoef1=1.0;
        //System.out.println("current progress: \n"+progress);
        chatUI = new ChatUI();
        chooseMazeUI = new ChooseMazeUI();
        instructionUI = new InstructionUI();
        settingsUI = new SettingsUI();
        //mazeUI = new MazeUI();
        //startMazeGame();
        mainMenuUI.setVisible(true);
        setMusic("background-music.mp3", 0.1);
        SwingUtilities.invokeLater(() -> {
            playMusic();
        });

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
    public static void updateDialogCount() {
        int dialogCount = progress.getDialogCount()+1;
        progress.setDialogCount(dialogCount);
        updateProgress();
    }
    public static void updateTotalDialogCount() {
        int dialogCount = progress.getTotalDialogCount()+1;
        progress.setTotalDialogCount(dialogCount);
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
    public static void setDeathReason(String reason) {
        progress.setDeathReason(reason);
        updateProgress();
    }
    public static String getDeathReason() {
        return progress.getDeathReason();
    }
    public static void updateChatData(ChatData chatData) {

        progress.setChapter1String(chatData.yourChapter1.toString());
        progress.setChapter2String(chatData.yourChapter2.toString());
        progress.setChapter3String(chatData.yourChapter3.toString());
        updateProgress();
    }

    public static void updateProgress(){
        ////System.out.println("current progress (update): \n"+progress);
        String progressString = "lang:"+progress.getLanguage()+"; username:"+progress.getUsername()+"; lv:"+progress.getLv()+"; alive:"+progress.isAlive()+"; deathReason:"+progress.getDeathReason()+"; msgCount:"+progress.getDialogCount()+"; chapter1:"+progress.getChapter1String()+"; chapter2:"+progress.getChapter2String()+"; chapter3:"+progress.getChapter3String()+"; finaleUnlocked:"+progress.isFinaleUnlocked();
        writeFile("progress.txt", progressString);

    }
    public static void resetProgress(){
        ////System.out.println("current progress (update): \n"+progress);
        String progressString = "lang:en; username:player; lv:0; alive:true; deathReason:none; msgCount:0; chapter1:0-2; chapter2:null; chapter3:null; finaleUnlocked:false";
        writeFile("progress.txt", progressString);

    }
    public static void fetchProgress(){
        String filePath = "progress.txt"; // Replace with your file path

        String fileContent = readFile(filePath);
        if (fileContent.isEmpty()) fileContent = "lang:en; username:player; lv:0; msgCount:0; alive:true; deathReason:none; chapter1:0-2; chapter2:null; chapter3:null; finaleUnlocked:false";
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
    public static void playEffect(String path, double volume) {
        JFXPanel jfxPanel = new JFXPanel();

        Platform.runLater(() -> {
            File musicFile = new File("music/" + path);
            Media media = new Media(musicFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.setVolume(volume * volumeCoef1);
            mediaPlayer.play();
        });
    }
    public static void setMusic(String path, double volume) {
        // Initialize JavaFX environment if not already initialized
        new JFXPanel();


            // Create a File object with the MP3 file
            File musicFile = new File("music/" + path);

            // Create a Media object with the File object
            Media media = new Media(musicFile.toURI().toString());

            // Create a MediaPlayer object to play the media
            backgroundMediaPlayer = new MediaPlayer(media);

            // Configure the MediaPlayer to loop the music
            backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            // Start playing the music
            backgroundMediaPlayer.setVolume(volume * volumeCoef);

            System.out.println("Background music is set.");
            System.out.println("Background music volume: " + backgroundMediaPlayer.getVolume());

    }

    public static void playMusic() {
        if (backgroundMediaPlayer != null) {
            backgroundMediaPlayer.play();
            System.out.println("Background music is playing.");
        } else {
            System.out.println("Background music is null. Make sure to set the music first.");
        }
    }

    public static void updateVolume(double volume) {
        if (backgroundMediaPlayer != null && backgroundMediaPlayer.getStatus() != MediaPlayer.Status.UNKNOWN) {
            backgroundMediaPlayer.setVolume(volume);
            System.out.println(backgroundMediaPlayer.getVolume());
        }
    }

    public static void pauseMusic() {
        if (backgroundMediaPlayer != null && backgroundMediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            backgroundMediaPlayer.pause();
        }
    }
    public static void stopMusic() {
        if (backgroundMediaPlayer != null && backgroundMediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            backgroundMediaPlayer.stop();
        }
    }
    public static boolean isMusicPlaying() {
        MediaPlayer.Status status = backgroundMediaPlayer.getStatus();

        if (status == MediaPlayer.Status.PLAYING) {
            // The MediaPlayer is currently playing
            //System.out.println("The track is playing");
            return true;
        } else {
            // The MediaPlayer is in a different state (e.g., stopped)
            //System.out.println("The track is in a different state");
            return false;
        }

    }
    public static String getLanguage() {
        return progress==null ? "en" : progress.getLanguage();
    }
    public static void setLanguage(String language) {
        progress.setLanguage(language);
        updateProgress();
    }


}

