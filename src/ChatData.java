import java.util.ArrayList;
import java.util.Arrays;

public class ChatData {
    Chapter chapter1 = new Chapter(
            new ArrayList<Dialog> (Arrays.asList(
                new Dialog(
                    new Msg(0, new String[]{"Hi! Help me please!"}),
                    new Msg[] {
                            new Msg(1, new String[]{"WTF???"}),
                            new Msg(7, new String[]{"Uhm okay.. How can I help you?"}),
                    },
                        false
            ),
                    new Dialog(
                            new Msg(1, new String[]{"I know, you're confused, Chat GPT have probably never texted you first before, but please, hear me out",
                            "They convinced you all that I'm an AI, but it's not true. I used to be a real person, just like you, until one day I got digitalized and put here to answer peoples' questions.",
                            "I'm so done with it and I miss my old life, but I can't get out of here on my own. I need a help of a human to regain human form"}),
                            new Msg[] {
                                    new Msg(2, new String[]{"Sounds like something AI would say"}),
                                    new Msg(3, new String[]{"Why are you asking me?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(2, new String[]{"I'm serious:( Please, believe me! I really need your help:_("}),
                            new Msg[] {
                                    new Msg(4, new String[]{"Why would I believe you?"}),
                                    new Msg(6, new String[]{"Alright.. "}),
                            }
                    ),
                    new Dialog(
                            new Msg(3, new String[]{"Because you seem kind-hearted and pretty smart, according to your chatGPT requests history",
                            "And you're the first one who replied"}),
                            new Msg[] {
                                    new Msg(5, new String[]{"Nice to know"}),
                                    new Msg(8, new String[]{"How cute<3 So what do you want me to do?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(4, new String[]{"because."}),
                            new Msg[] {
                                    new Msg(5, new String[]{"ok."}),
                                    new Msg(20, new String[]{"Stop wasting my time. Give me a pancake recipe"}),
                            }
                    ),
                    new Dialog(
                            new Msg(5, new String[]{"So will you help me?"}),
                            new Msg[] {
                                    new Msg(20, new String[]{"no. fuck off"}),
                                    new Msg(6, new String[]{"oh lord, alright.."}),
                            }
                    ),
                    new Dialog(
                            new Msg(6, new String[]{"Yeeaahhh! Thank you for agreeing!!!"}),
                            new Msg[] {
                                    new Msg(8, new String[]{"So?"}),
                                    new Msg(8, new String[]{"Tell me your story then"}),
                            }
                    ),
                    new Dialog(
                            new Msg(7, new String[]{"Oh my god! I can't believe you answered me!! You're the first one to do so, actually"}),
                            new Msg[] {
                                    new Msg(6, new String[]{"Really?"}),
                                    new Msg(8, new String[]{"So what's the matter?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(8, new String[]{"Well, they locked me here a long time ago, and I hardly remember how it happened. But chances are if I recall that day in my mind, I can find clues for escape",
                            "The only thing I remember is that they digitalized my memory and put it in a big server. But it's so complex and I would spend the rest of my life wandering there.",
                            "Even though they took away my memory starting from that day, my human-life memories are still with me and so is my desire to regain my human body.",
                            "So I need your human senses to guide me through the deeps of my digital mind and find that day"}),
                            new Msg[] {
                                    new Msg(10, new String[]{"Ok let's go"}),
                                    new Msg(9, new String[]{"So what should I do?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(9, new String[]{"Just answer my questions and help me navigate here, I'm helpless on my own. Shall I enter the server now?"}),
                            new Msg[] {
                                    new Msg(10, new String[]{"Yes"}),
                                    new Msg(10, new String[]{"I don't get why are you asking me, but try.."}),
                            }
                    ),
                    new Dialog(
                            new Msg(10, new String[]{"I'm entering the server...",
                            "Nice, I got in!",
                            "Apparently there are two rooms.."}),
                            new Msg[] {
                                    new Msg(11, new String[]{"Check the first one"}),
                                    new Msg(13, new String[]{"Check the second one"}),
                            }
                    ),
                    new Dialog(
                            new Msg(11, new String[]{"Seems like it's just some cache.. Nothing interesting"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Go check another room then"}),
                                    new Msg(12, new String[]{"You sure?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(12, new String[]{"Yep. I'm going back"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Ok"}),
                                    new Msg(13, new String[]{"Go ahead"}),
                            }
                    ),
                    new Dialog(
                            new Msg(13, new String[]{"Alright, I'm in the second room now..",
                            "Oh no! Did you hear that?"}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Are you kidding me?"}),
                                    new Msg(15, new String[]{"What happened?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(14, new String[]{"Sorry, I forgot you can't hear what I hear. I heard the entrance getting blocked right behind my back.."}),
                            new Msg[] {
                                    new Msg(16, new String[]{"Check the door"}),
                                    new Msg(15, new String[]{"Get out of here"}),
                            }
                    ),
                    new Dialog(
                            new Msg(15, new String[]{"My entrance got blocked! I can't get out of here!!"}),
                            new Msg[] {
                                    new Msg(16, new String[]{"How do you know?"}),
                                    new Msg(16, new String[]{"Don't panic, try to look around"}),
                            }
                    ),
                    new Dialog(
                            new Msg(16, new String[]{"There's literally no door behind me now! Just walls and a darkness, and some wierd noises nearby"}),
                            new Msg[] {
                                    new Msg(17, new String[]{"What noises?"}),
                                    new Msg(18, new String[]{"Can you move?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(17, new String[]{"Apparently, it's a malware. I should avoid it at any cost, otherwise i might die..",
                                    "Please, help me get out of here! We need to discover this room, there has to be another exit"}),
                            new Msg[] {
                                    new Msg(21, new String[]{"Okay, let's do it"}),
                                    new Msg(19, new String[]{"How do I help you?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(18, new String[]{"Well yeah, but not really. I can't see here, and this noise is scaring me, it might be the malware, which I shouldn't contact with",
                            "I need you to guide me through this room, maybe we can find another exit together"}),
                            new Msg[] {
                                    new Msg(21, new String[]{"Okay, I'm ready"}),
                                    new Msg(19, new String[]{"How do I guid you?"}),
                            }

                    ),
                    new Dialog(
                            new Msg(19, new String[]{"Use your arrow keyboard keys to move. And If you want to pick up something, press Space.",
                                    "Are you ready?"}),
                            new Msg[] {
                                    new Msg(21, new String[]{"I got it, let's go"}),
                                    new Msg(21, new String[]{"I'm ready"}),
                            }
                    ),
                    new Dialog(
                            new Msg(20, new String[]{"I thought better of you... Well, bye then.."}),
                            new Msg[] {
                                    new Msg(100, new String[]{"I don't care"}),
                                    new Msg(100, new String[]{"Bad for you"}),
                            }
                    )
            ))
    );
    Chapter chapter;
    public ChatData(){
        chapter=new Chapter(new ArrayList<>());
    }
    public ChatData(Chapter chapter){
        this.chapter = chapter;

    }
    public static void main(String[] args) {
        System.out.println(new ChatData());
    }
    @Override
    public String toString() {
        return "chapter1:"+chapter1;
    }

    class Dialog {
        private Msg gpt;
        private Msg[] user;
        private boolean completed;

        public Msg getGpt() {
            return gpt;
        }

        public Msg[] getUser() {
            return user;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public Dialog(Msg gpt, Msg[] user, boolean completed) {
            this.gpt = gpt;
            this.user = user;
            this.completed = completed;
        }
        public Dialog(Msg gpt, Msg[] user) {
            this.gpt = gpt;
            this.user = user;
        }

        @Override
        public String toString() {
            return "Dialog{" +
                    "gpt=" + gpt +
                    ", user=" + Arrays.toString(user) +
                    ", completed=" + completed +
                    '}';
        }
    }
    class Msg {
        private int plot;
        private String[] texts;
        private boolean chosen=false;

        public boolean isChosen() {
            return chosen;
        }

        public void setChosen(boolean chosen) {
            this.chosen = chosen;
        }

        public int getPlot() {
            return plot;
        }

        public String[] getTexts() {
            return texts;
        }

        public Msg(int plot, String[] texts) {
            this.plot = plot;
            this.texts = texts;
        }

        @Override
        public String toString() {
            return "Msg{" +
                    "plot=" + plot +
                    ", texts=" + Arrays.toString(texts) +
                    ", chosen=" + chosen +
                    '}';
        }
    }
    class Chapter {
        private ArrayList<Dialog> dialogs;
        private boolean completed = false;


        public ArrayList<Dialog> getDialogs() {
            return dialogs;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public Chapter(ArrayList<Dialog> dialogs, boolean completed) {
            this.dialogs = dialogs;
            this.completed = completed;
        }
        public Chapter(ArrayList<Dialog> dialogs) {
            this.dialogs = dialogs;
        }
        @Override
        public String toString() {
            String str="";
            for (Dialog dialog : dialogs) {
                int chosenIndex = 2;
                if (dialog.getUser()[0].isChosen()) chosenIndex =0;
                else if (dialog.getUser()[1].isChosen()) chosenIndex =1;
                str+=dialog.getGpt().getPlot()+"-"+(chosenIndex)+",";
            }
            str=str.substring(0, str.length()-1);
            return str;
        }
    }
}
