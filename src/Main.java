import java.io.*;

public class Main {
    private static ProgressData progress;
    static MazeUI mazeUI;
    public static ProgressData getProgress() {
        return progress;
    }


    public static void startMazeGame(){
        mazeUI = new MazeUI(Main.getProgress().getLv());
        mazeUI.setVisible(true);
    }

    public static void main(String[] args) {
        fetchProgress();
        System.out.println("current progress: \n"+progress);
        startMazeGame();
    }
    private static void updateUsername(String newName) {
        progress.setUsername(newName);
        updateProgress();
    }
    public static void updateLevel() {
        int level = progress.getLv()+1;
        progress.setLv(level);
        updateProgress();
    }
    public static void setLevel(int level) {
        progress.setLv(level);
        updateProgress();
    }

    public static void updateProgress(){
        //System.out.println("current progress (update): \n"+progress);
        String progressString = "username:"+progress.getUsername()+"; lv:"+progress.getLv()+"; msg:"+progress.getMsg()+"; plot:"+progress.getPlot()+""
                ;
        writeFile("progress.txt", progressString);

    }
    public static void fetchProgress(){
        String filePath = "progress.txt"; // Replace with your file path

        String fileContent = readFile(filePath);
        if (fileContent.isEmpty()) fileContent = "username:player; lv:1; msg:1; plot:1";
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

