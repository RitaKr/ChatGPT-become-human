class ProgressData {
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

    private static String chapter1String;
    private static String chapter2String;
    private static String chapter3String;
    private static boolean alive;
    private static String language;
    private static boolean finaleUnlocked;

    public boolean isFinaleUnlocked() {
        return finaleUnlocked;
    }

    public void setFinaleUnlocked(boolean finaleUnlocked) {
        ProgressData.finaleUnlocked = finaleUnlocked;
    }

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

    public String getChapter3String() {
        return chapter3String;
    }

    public void setChapter3String(String chapter3String) {
        ProgressData.chapter3String = chapter3String;
    }


    public ProgressData(String dataString) {
        setProgressData(dataString);
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

    /**
     * Встановлює дані прогресу з рядка даних.
     * Рядок даних повинен бути у форматі "ключ:значення;", де кожен ключ-значення пара розділяється символом ":".
     * Підтримувані ключі та їх значення:
     * - "lang": мова гри
     * - "lv": рівень гри
     * - "alive": прапорець, що вказує, чи персонаж живий
     * - "deathReason": причина смерті персонажа
     * - "dialogCount": кількість проведених діалогів
     * - "chapter1": дані глави 1
     * - "chapter2": дані глави 2
     * - "chapter3": дані глави 3
     * - "finaleUnlocked": прапорець, що вказує, чи розблоковано фінал
     *
     * @param dataString рядок даних для встановлення прогресу
     */
    public void setProgressData(String dataString) {
        String[] lines = dataString.split(";");
        //System.out.println(Arrays.asList(lines));

        for (String line : lines) {
            String[] parts = line.split(":");
            String key = parts[0].trim();
            String value = parts[1].trim();

            switch (key) {
                case "lang":
                    language = value;
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
                case "chapter3":
                    chapter3String = value;
                    break;
                case "finaleUnlocked":
                    finaleUnlocked = Boolean.parseBoolean(value);
                    break;
                default:
                    // Handle unrecognized keys, if needed
                    break;
            }
        }

    }

    /**
     * Отримує дані чату зі стрічок глав.
     * Повертає об'єкт ChatData, який містить інформацію про прогрес гравця у кожній главі.
     * @return об'єкт ChatData з даними чату
     */
    public ChatData getChatData(){
        ChatData data = new ChatData();
        //setting chapter1
        String[] chapter1dialogs = chapter1String.split(",");
        String[] chapter2dialogs = chapter2String.split(",");
        String[] chapter3dialogs = chapter3String.split(",");

        for (int i=0; i< chapter1dialogs.length; i++) {
            String[] parts = chapter1dialogs[i].split("-");
            int key = Integer.parseInt(parts[0].trim());
            int value = Integer.parseInt(parts[1].trim());

                data.yourChapter1.getDialogs().add(data.chapter1.getDialogs().get(key));
                data.yourChapter1Ukr.getDialogs().add(data.chapter1Ukr.getDialogs().get(key));
                if (value!=2 && i==chapter1dialogs.length-1 && chapter2String.equals("null")) {
                    data.yourChapter1.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter1.getDialogs().get(i).setCompleted(false);
                    data.yourChapter1Ukr.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter1Ukr.getDialogs().get(i).setCompleted(false);
                } else if (value!=2) {
                    data.yourChapter1.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter1.getDialogs().get(i).setCompleted(true);
                    data.yourChapter1Ukr.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter1Ukr.getDialogs().get(i).setCompleted(true);
                }
        }

        //setting chapter2

        try  {
            for (int i = 0; i < chapter2dialogs.length; i++) {
                String[] parts = chapter2dialogs[i].split("-");
                int key = Integer.parseInt(parts[0].trim());
                int value = Integer.parseInt(parts[1].trim());

                    data.yourChapter2.getDialogs().add(data.chapter2.getDialogs().get(key));
                    data.yourChapter2Ukr.getDialogs().add(data.chapter2Ukr.getDialogs().get(key));
                if (value!=2 && i==chapter2dialogs.length-1 && chapter3String.equals("null")) {
                    data.yourChapter2.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter2.getDialogs().get(i).setCompleted(false);
                    data.yourChapter2Ukr.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter2Ukr.getDialogs().get(i).setCompleted(false);
                }else if (value != 2) {
                    data.yourChapter2.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter2.getDialogs().get(i).setCompleted(true);
                    data.yourChapter2Ukr.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter2Ukr.getDialogs().get(i).setCompleted(true);
                }
            }
            data.yourChapter1.setCompleted(true);
        } catch (NumberFormatException e) {
        }

        //setting chapter3
        try  {
            for (int i = 0; i < chapter3dialogs.length; i++) {
                String[] parts = chapter3dialogs[i].split("-");
                int key = Integer.parseInt(parts[0].trim());
                int value = Integer.parseInt(parts[1].trim());

                    data.yourChapter3.getDialogs().add(data.chapter3.getDialogs().get(key));
                    data.yourChapter3Ukr.getDialogs().add(data.chapter3Ukr.getDialogs().get(key));
                if (value!=2 && i==chapter3dialogs.length-1 && !finaleUnlocked) {
                    data.yourChapter3.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter3.getDialogs().get(i).setCompleted(false);
                    data.yourChapter3Ukr.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter3Ukr.getDialogs().get(i).setCompleted(false);
                } else if (value != 2) {
                    //if (value != 2 && i!=chapter3dialogs.length-1) {
                    data.yourChapter3.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter3.getDialogs().get(i).setCompleted(true);
                    data.yourChapter3Ukr.getDialogs().get(i).getUser()[value].setChosen(true);
                    data.yourChapter3Ukr.getDialogs().get(i).setCompleted(true);
                }
            }
            data.yourChapter2.setCompleted(true);
        } catch (NumberFormatException e) {
        }

        if (finaleUnlocked) data.yourChapter3.setCompleted(true);
        return data;
    }

    @Override
    public String toString(){
        return "Language: " + language+";\n"
                +"Level: " + lv+";\n"
                +"Alive: " + alive+";\n"
                +"DeathReason: " + alive+";\n"
                +"DialogCount: " + dialogCount +";\n"
                +"Chapter1: " + chapter1String +";\n"
                +"Chapter2: " + chapter2String +";\n"
                +"Chapter3: " + chapter3String +";\n"
                +"finaleUnlocked: " + finaleUnlocked +";\n";
    }

}