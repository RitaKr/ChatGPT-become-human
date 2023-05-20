import java.util.Arrays;

class ProgressData {
    private static String username;
    private static int lv;
    private static int msg;
    private static int plot;


    public ProgressData(String username, int lv, int msg, int plot) {
        this.username = username;
        this.lv = lv;
        this.msg = msg;
        this.plot = plot;
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

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public int getPlot() {
        return plot;
    }

    public void setPlot(int plot) {
        this.plot = plot;
    }

    public void setProgressData(String dataString) {
        String[] lines = dataString.split(";");
        //System.out.println(Arrays.asList(lines));

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
                case "msg":
                    msg = Integer.parseInt(value);
                    break;
                case "plot":
                    plot = Integer.parseInt(value);
                    break;
                default:
                    // Handle unrecognized keys, if needed
                    break;
            }
        }


    }
    @Override
    public String toString(){
        return "Username: " + username+";\n"
                +"Level: " + lv+";\n"
                +"Last message id: " + msg+";\n"
                +"Plot: " + plot+";\n";
    }

}