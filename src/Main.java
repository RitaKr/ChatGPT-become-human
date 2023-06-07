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


    public static void startMazeGame(){
        mazeUI = new MazeUI();
        mazeUI.setVisible(true);
    }
    public static void startMazeGame(int level){
        mazeUI = new MazeUI(level);
        mazeUI.setVisible(true);
    }

    public static void main(String[] args) {
        fetchProgress();
        System.out.println("current progress: \n"+progress);
        chatUI = new ChatUI();
        chooseMazeUI = new ChooseMazeUI();
        instructionUI = new InstructionUI();
        //mazeUI = new MazeUI();
        //startMazeGame();
        mainMenuUI.setVisible(true);
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
        //System.out.println("current progress (update): \n"+progress);
        String progressString = "username:"+progress.getUsername()+"; lv:"+progress.getLv()+"; alive:"+progress.isAlive()+"; chapter1:"+progress.getChapter1()+"";
        writeFile("progress.txt", progressString);

    }
    public static void resetProgress(){
        //System.out.println("current progress (update): \n"+progress);
        String progressString = "username:player; lv:0; alive:true; chapter1:0-2";
        writeFile("progress.txt", progressString);

    }
    public static void fetchProgress(){
        String filePath = "progress.txt"; // Replace with your file path

        String fileContent = readFile(filePath);
        if (fileContent.isEmpty()) fileContent = "username:player; lv:0; alive:true; chapter1:0-2";
        //System.out.println(fileContent);
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
}

