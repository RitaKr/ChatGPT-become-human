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
    static SettingsUI settingsUI;
    static MediaPlayer backgroundMediaPlayer;
    static double volumeCoef, volumeCoef1;

    /**
     * Метод, який починає гру в лабіринт (викликатися з ChatUI).
     * Зупиняє музику, створює екземпляр MazeUI і встановлює його видимість.
     */
    public static void startMazeGame(){
        pauseMusic();
        mazeUI = new MazeUI(volumeCoef, volumeCoef1);
        mazeUI.setVisible(true);
    }

    /**
     * Метод, який починає гру в лабіринт (викликатися з ChooseMazeUI).
     * Зупиняє музику, створює екземпляр MazeUI і встановлює його видимість.
     */
    public static void startMazeGame(int level){
        pauseMusic();
        mazeUI = new MazeUI(level, volumeCoef, volumeCoef1);
        mazeUI.setVisible(true);
    }


    /**
     * Головний метод програми, який виконується при запуску програми.
     */
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
        playMusic();
    }

    /**
     * Оновлює рівень гри. Збільшує значення рівня на 1, якщо поточний рівень менше 3, в іншому випадку залишає поточний рівень без змін.
     * Оновлює прогрес гри, викликаючи метод `updateProgress()`.
     */
    public static void updateLevel() {
        int level = progress.getLv()<3 ? (progress.getLv()+1) : progress.getLv();
        progress.setLv(level);
        updateProgress();
    }

    /**
     * Оновлює кількість діалогів. Збільшує значення кількості діалогів на 1.
     * Оновлює прогрес гри, викликаючи метод `updateProgress()`.
     */
    public static void updateDialogCount() {
        int dialogCount = progress.getDialogCount()+1;
        progress.setDialogCount(dialogCount);
        updateProgress();
    }

    /**
     * Встановлює рівень гри на задане значення.
     * @param level новий рівень гри
     * Оновлює прогрес гри, викликаючи метод `updateProgress()`.
     */
    public static void setLevel(int level) {
        progress.setLv(level);
        updateProgress();
    }

    /**
     * Встановлює статус "живий" для гравця.
     * @param alive статус "живий" (true - живий, false - не живий)
     * Оновлює прогрес гри, викликаючи метод `updateProgress()`.
     */
    public static void setAlive(boolean alive) {
        progress.setAlive(alive);
        updateProgress();
    }

    /**
     * Встановлює причину смерті гравця.
     * @param reason причина смерті
     * Оновлює прогрес гри, викликаючи метод `updateProgress()`.
     */
    public static void setDeathReason(String reason) {
        progress.setDeathReason(reason);
        updateProgress();
    }
    /**
     * Повертає з прогресу причину смерті гравця.
     * @return причина смерті
     */
    public static String getDeathReason() {
        return progress.getDeathReason();
    }

    /**
     * Оновлює в прогресі гри дані чату.
     * @param chatData дані чату для оновлення
     */
    public static void updateChatData(ChatData chatData) {

        progress.setChapter1String(chatData.yourChapter1.toString());
        progress.setChapter2String(chatData.yourChapter2.toString());
        progress.setChapter3String(chatData.yourChapter3.toString());
        updateProgress();
    }

    /**
     * Оновлює прогрес гри та зберігає його у файлі "progress.txt".
     * Прогрес включає мову гри, рівень, статус гравця (живий чи мертвий), причину смерті, кількість повідомлень в чаті, дані першого розділу чату, дані другого розділу чату, дані третього розділу чату та інформацію про розблокований фінал.
     */
    public static void updateProgress(){
        String progressString = "lang:"+progress.getLanguage()+"; lv:"+progress.getLv()+"; alive:"+progress.isAlive()+"; deathReason:"+progress.getDeathReason()+"; msgCount:"+progress.getDialogCount()+"; chapter1:"+progress.getChapter1String()+"; chapter2:"+progress.getChapter2String()+"; chapter3:"+progress.getChapter3String()+"; finaleUnlocked:"+progress.isFinaleUnlocked();
        writeFile("progress.txt", progressString);

    }

    /**
     * Скидає прогрес гри до початкових значень.
     */
    public static void resetProgress(){
        String progressString = "lang:en; lv:0; alive:true; deathReason:none; msgCount:0; chapter1:0-2; chapter2:null; chapter3:null; finaleUnlocked:false";
        writeFile("progress.txt", progressString);

    }

    /**
     * Завантажує прогрес гри з файлу.
     * Якщо файл порожній, використовується значення за замовчуванням.
     * Після завантаження прогресу створюється об'єкт ProgressData.
     */
    public static void fetchProgress(){
        String filePath = "progress.txt";
        String fileContent = readFile(filePath);
        if (fileContent.isEmpty()) fileContent = "lang:en; lv:0; msgCount:0; alive:true; deathReason:none; chapter1:0-2; chapter2:null; chapter3:null; finaleUnlocked:false";
        progress = new ProgressData(fileContent);

    }

    /**
     * Зчитує вміст файлу за заданим шляхом і повертає його у вигляді рядка.
     *
     * @param filePath шлях до файлу, який потрібно прочитати
     * @return рядок з вмістом файлу
     */
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


    /**
     * Записує дані у файл
     *
     * @param filePath шлях до файлу, в який потрібно записати дані
     * @param content дані, які треба записати в файл
     */
    public static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Відтворює аудіоефект з вказаним шляхом і гучністю.
     *
     * @param path   шлях до аудіофайлу ефекту
     * @param volume гучність ефекту (від 0.0 до 1.0)
     */
    public static void playEffect(String path, double volume) {
        new JFXPanel();

        File musicFile = new File("music/" + path);
        Media media = new Media(musicFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.setVolume(volume * volumeCoef1);
        mediaPlayer.play();

    }

    /**
     * Налаштовує фонову музику з вказаним шляхом і гучністю.
     *
     * @param path   шлях до аудіофайлу музики
     * @param volume гучність музики (від 0.0 до 1.0)
     */
    public static void setMusic(String path, double volume) {
        // Initialize JavaFX environment if not already initialized
        new JFXPanel();

        File musicFile = new File("music/" + path);
        Media media = new Media(musicFile.toURI().toString());
        backgroundMediaPlayer = new MediaPlayer(media);
        backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMediaPlayer.setVolume(volume * volumeCoef);
    }

    /**
     * Відтворює фонову музику, якщо вона встановлена.
     */
    public static void playMusic() {
        if (backgroundMediaPlayer != null) {
            backgroundMediaPlayer.play();
        }
    }

    /**
     * Оновлює гучність фонової музики.
     *
     * @param volume Нове значення гучності (від 0.0 до 1.0).
     */
    public static void updateVolume(double volume) {
        if (backgroundMediaPlayer != null && backgroundMediaPlayer.getStatus() != MediaPlayer.Status.UNKNOWN) {
            backgroundMediaPlayer.setVolume(volume);
            //System.out.println(backgroundMediaPlayer.getVolume());
        }
    }

    /**
     * Ставить фонову музику на паузу.
    */
    public static void pauseMusic() {
        if (backgroundMediaPlayer != null && backgroundMediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            backgroundMediaPlayer.pause();
        }
    }

    /**
     * Перевіряє, чи відтворюється фонова музика.
     *
     * @return {@code true}, якщо фонова музика відтворюється, {@code false} - в іншому випадку.
     */
    public static boolean isMusicPlaying() {
        MediaPlayer.Status status = backgroundMediaPlayer.getStatus();
        return status == MediaPlayer.Status.PLAYING;
    }

    /**
     * Повертає мову гри.
     *
     * @return Мова гри, якщо вона встановлена, або "en" як мова за замовчуванням.
     */
    public static String getLanguage() {
        return progress==null ? "en" : progress.getLanguage();
    }

    /**
     * Встановлює мову гри.
     *
     * @param language Бажана мова гри ("en" або "uk").
     */
    public static void setLanguage(String language) {
        progress.setLanguage(language);
        updateProgress();
    }


}

