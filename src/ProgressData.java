import java.util.Arrays;

class ProgressData {
    private static String username;
    private static int lv;
    private static String chapter1;
    private static boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        ProgressData.alive = alive;
    }

    public static String getChapter1() {
        return chapter1;
    }

    public static void setChapter1(String chapter1) {
        ProgressData.chapter1 = chapter1;
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


    public void setProgressData(String dataString) {
        String[] lines = dataString.split(";");
        System.out.println(Arrays.asList(lines));

        for (String line : lines) {
            String[] parts = line.split(":");
            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (key) {
                case "username":
                    username = value;
                    break;
                case "lv":
                    lv = Integer.parseInt(value);
                    break;
                case "alive":
                    alive = Boolean.parseBoolean(value);
                    break;
                case "chapter1":
                    chapter1 = value;
                    break;
                default:
                    // Handle unrecognized keys, if needed
                    break;
            }
        }

    }
    public ChatData getChatData(){
        String[] dialogs = chapter1.split(",");
        ChatData data = new ChatData();
        for (int i=0; i< dialogs.length; i++) {
            String[] parts = dialogs[i].split("-");
            int key = Integer.parseInt(parts[0].trim());
            int value = Integer.parseInt(parts[1].trim());
            data.chapter.getDialogs().add(data.chapter1.getDialogs().get(key));
            if (value!=2) {
                data.chapter.getDialogs().get(i).getUser()[value].setChosen(true);
                data.chapter.getDialogs().get(i).setCompleted(true);
            }
            //data.chapter1.getDialogs().get(key).setCompleted(value);
        }
        System.out.println(data);
        return data;
    }
    @Override
    public String toString(){
        return "Username: " + username+";\n"
                +"Level: " + lv+";\n"
                +"Alive: " + alive+";\n"
                +"Chapter1: " + chapter1+";\n";
    }

}