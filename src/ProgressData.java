import java.util.Arrays;
import java.util.Objects;

class ProgressData {
    private static String username;
    private static int lv;
    private static int dialogCount;
    private static int totalDialogCount;
    private static String deathReason;

    public String getDeathReason() {
        return deathReason;
    }

    public void setDeathReason(String deathReason) {
        ProgressData.deathReason = deathReason;
    }

    public int getTotalDialogCount() {
        return totalDialogCount;
    }

    public void setTotalDialogCount(int totalDialogCount) {
        ProgressData.totalDialogCount = totalDialogCount;
    }

    public static String getChapter3String() {
        return chapter3String;
    }

    public static void setChapter3String(String chapter3String) {
        ProgressData.chapter3String = chapter3String;
    }

    private static String chapter1String;
    private static String chapter2String;
    private static boolean alive;
    private static String chapter3String;
    private static String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        ProgressData.language = language;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        ProgressData.alive = alive;
    }

    public String getChapter1String() {
        return chapter1String;
    }

    public void setChapter1String(String chapter1String) {
        ProgressData.chapter1String = chapter1String;
    }

    public String getChapter2String() {
        return chapter2String;
    }

    public void setChapter2String(String chapter2String) {
        ProgressData.chapter2String = chapter2String;
    }
    public ProgressData(String dataString) {
        setProgressData(dataString);
    }
    // Getters and setters for the fields

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }
    public int getDialogCount() {
        return dialogCount;
    }

    public void setDialogCount(int dialogCount) {
        this.dialogCount = dialogCount;
    }

    public void setProgressData(String dataString) {
        String[] lines = dataString.split(";");
        System.out.println(Arrays.asList(lines));

        for (String line : lines) {
            String[] parts = line.split(":");
            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (key) {
                case "lang":
                    language = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "lv":
                    lv = Integer.parseInt(value);
                    break;
                case "alive":
                    alive = Boolean.parseBoolean(value);
                    break;
                case "deathReason":
                    deathReason = value;
                    break;
                case "dialogCount":
                    dialogCount = Integer.parseInt(value);
                    break;
                case "chapter1":
                    chapter1String = value;
                    break;
                case "chapter2":
                    chapter2String = value;
                    break;
                default:
                    // Handle unrecognized keys, if needed
                    break;
            }
        }

    }
    public ChatData getChatData(){
        ChatData data = new ChatData();
        //setting chapter1
        String[] chapter1dialogs = chapter1String.split(",");
        for (int i=0; i< chapter1dialogs.length; i++) {
            String[] parts = chapter1dialogs[i].split("-");
            int key = Integer.parseInt(parts[0].trim());
            int value = Integer.parseInt(parts[1].trim());
            if (Objects.equals(language, "en")) {
                data.yourChapter1.getDialogs().add(data.chapter1.getDialogs().get(key));
            } else if (Objects.equals(language, "uk")) {
                data.yourChapter1.getDialogs().add(data.chapter1Ukr.getDialogs().get(key));
            }
            if (value!=2) {
                data.yourChapter1.getDialogs().get(i).getUser()[value].setChosen(true);
                data.yourChapter1.getDialogs().get(i).setCompleted(true);
            }
            //data.chapter1.getDialogs().get(key).setCompleted(value);
        }

        //setting chapter2
        String[] chapter2dialogs = chapter2String.split(",");
        try  {
            for (int i = 0; i < chapter2dialogs.length; i++) {
                String[] parts = chapter2dialogs[i].split("-");
                int key = Integer.parseInt(parts[0].trim());
                int value = Integer.parseInt(parts[1].trim());
                if (Objects.equals(language, "en")) {
                    data.yourChapter2.getDialogs().add(data.chapter2.getDialogs().get(key));
                } else if (Objects.equals(language, "uk")) {
                    data.yourChapter2.getDialogs().add(data.chapter2Ukr.getDialogs().get(key));
                }
                if (value != 2) {
                    data.yourChapter2.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter2.getDialogs().get(i).setCompleted(true);
                }
                //data.chapter1.getDialogs().get(key).setCompleted(value);
            }
        } catch (NumberFormatException e) {
            System.out.println("chapter2 is not unlocked yet");
        }
        //System.out.println(data);
        return data;
    }

    @Override
    public String toString(){
        return "Language: " + language+";\n"
                +"Username: " + username+";\n"
                +"Level: " + lv+";\n"
                +"Alive: " + alive+";\n"
                +"DeathReason: " + alive+";\n"
                +"DialogCount: " + dialogCount +";\n"
                +"Chapter1: " + chapter1String +";\n"
                +"Chapter2: " + chapter2String +";\n";
    }

}