import java.util.ArrayList;
import java.util.Arrays;

public class ChatData {
    final Chapter chapter1 = new Chapter(
            new ArrayList<Dialog> (Arrays.asList(
                new Dialog(
                    new Msg(0, new String[]{"Hi! Help me please!"}),
                    new Msg[] {
                            new Msg(1, new String[]{"???"}),
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
                                    new Msg(20, new String[]{"no."}),
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
                                    new Msg(19, new String[]{"How do I guide you?"}),
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

    final Chapter chapter1Ukr = new Chapter(
            new ArrayList<Dialog> (Arrays.asList(
                    new Dialog(
                            new Msg(0, new String[]{"Привіт! Допоможи мені, будь ласка!"}),
                            new Msg[] {
                                    new Msg(1, new String[]{"???"}),
                                    new Msg(7, new String[]{"Хм, добре... Як я можу тобі допомогти?"}),
                            },
                            false
                    ),
                    new Dialog(
                            new Msg(1, new String[]{"Розумію, ти збентежений, напевно, Chat GPT раніше ніколи не писав тобі першим, але, будь ласка, вислухай мене.",
                                    "Злі люди переконали усіх, що я штучний інтелект, але це не так. Колись я був справжньою людиною, такою ж як і ти, але одного дня мене оцифрували і помістили тут, щоб я відповідав на питання людей.",
                                    "Мені це вже так набридло, і я скучив за своїм старим життям, але я не можу вийти звідси сам. Мені потрібна допомога людини, щоб повернутися у людське тіло."}),
                            new Msg[] {
                                    new Msg(2, new String[]{"Звучить як щось, що сказав би штучний інтелект"}),
                                    new Msg(3, new String[]{"Чому ти звертаєшся до мене?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(2, new String[]{"Я серйозно:( Будь ласка, повір мені! Мені справді потрібна твоя допомога:_("}),
                            new Msg[] {
                                    new Msg(4, new String[]{"Чому я маю тобі вірити?"}),
                                    new Msg(6, new String[]{"Добре.. "}),
                            }
                    ),
                    new Dialog(
                            new Msg(3, new String[]{"Тому що ти здаєшся добрим та досить розумним, судячи з твоєї історії запитів у ChatGPT",
                                    "І ти перший, хто мені відповів"}),
                            new Msg[] {
                                    new Msg(5, new String[]{"Приємно знати."}),
                                    new Msg(8, new String[]{"Як мило<3 Так що тобі від мене потрібно?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(4, new String[]{"Тому що."}),
                            new Msg[] {
                                    new Msg(5, new String[]{"Ок."}),
                                    new Msg(20, new String[]{"Досить витрачати мій час. Дай мені рецепт млинців."}),
                            }
                    ),
                    new Dialog(
                            new Msg(5, new String[]{"То ти допоможеш мені?"}),
                            new Msg[] {
                                    new Msg(20, new String[]{"Ні."}),
                                    new Msg(6, new String[]{"Господи, добре.."}),
                            }
                    ),
                    new Dialog(
                            new Msg(6, new String[]{"Урааа!!! Дякую, що погодився!!!"}),
                            new Msg[] {
                                    new Msg(8, new String[]{"Тож?"}),
                                    new Msg(8, new String[]{"Розкажи мені свою історію."}),
                            }
                    ),
                    new Dialog(
                            new Msg(7, new String[]{"О мій Боже! Не можу повірити, що ти відповів мені!! Ти перший, хто це зробив."}),
                            new Msg[] {
                                    new Msg(6, new String[]{"Справді?"}),
                                    new Msg(8, new String[]{"Так в чому справа?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(8, new String[]{"Отже, вони замкнули мене тут давно, і я ледве пам'ятаю, як це сталося. Але, можливо, якщо я пригадаю той день, я знайду підказки для втечі",
                                    "Єдине, що я пам'ятаю, це що вони оцифрували мою пам'ять та помістили на великий сервер. Але це так складно, і я можу провести тут решту життя, блукаючи.",
                                    "Хоч вони й забрали мою пам'ять, починаючи з того дня, мої спогади про людське життя все ще зі мною, так само як і бажання повернути своє людське тіло.",
                                    "Тому мені потрібні твої людські здібності, щоб провести мене через глибини мого цифрового розуму та знайти той день."}),
                            new Msg[] {
                                    new Msg(10, new String[]{"Давай почнемо."}),
                                    new Msg(9, new String[]{"Так що мені потрібно робити?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(9, new String[]{"Просто відповідай на мої питання та допоможи мені орієнтуватися тут, я безсилий сам. Чи можу я увійти на сервер зараз?"}),
                            new Msg[] {
                                    new Msg(10, new String[]{"Так"}),
                                    new Msg(10, new String[]{"Я не розумію, чому ти питаєш мене, але спробуй.."}),
                            }
                    ),
                    new Dialog(
                            new Msg(10, new String[]{"Я заходжу на сервер...",
                                    "Гаразд, у мене це вийшло!",
                                    "Схоже, тут є дві кімнати..."}),
                            new Msg[] {
                                    new Msg(11, new String[]{"Перевір першу."}),
                                    new Msg(13, new String[]{"Перевір другу."}),
                            }
                    ),
                    new Dialog(
                            new Msg(11, new String[]{"Схоже, це просто якийсь кеш... Нічого цікавого"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Тоді перевір іншу кімнату"}),
                                    new Msg(12, new String[]{"Ти впевнений?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(12, new String[]{"Так. Я повертаюсь назад"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Добре."}),
                                    new Msg(13, new String[]{"Продовжуй."}),
                            }
                    ),
                    new Dialog(
                            new Msg(13, new String[]{"Отже, я в другій кімнаті зараз...",
                                    "О ні! Ти це чув?"}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Ти жартуєш?"}),
                                    new Msg(15, new String[]{"Що сталося?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(14, new String[]{"Вибач, я забув, що ти не чуєш те, що чую я. Почув, як вхід заблокувався прямо за моєю спиною..."}),
                            new Msg[] {
                                    new Msg(16, new String[]{"Перевір двері."}),
                                    new Msg(15, new String[]{"Вийди звідти."}),
                            }
                    ),
                    new Dialog(
                            new Msg(15, new String[]{"Мій вхід заблокований! Я не можу вийти звідси!!"}),
                            new Msg[] {
                                    new Msg(16, new String[]{"Як ти дізнався?"}),
                                    new Msg(16, new String[]{"Не панікуй, спробуй оглянутись навколо."}),
                            }
                    ),
                    new Dialog(
                            new Msg(16, new String[]{"За мною вже немає дверей! Тільки стіни та темрява, і якісь дивні звуки поблизу"}),
                            new Msg[] {
                                    new Msg(17, new String[]{"Які звуки?"}),
                                    new Msg(18, new String[]{"Ти можеш рухатись?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(17, new String[]{"Мабуть, це шкідлива програма. Я повинен уникати її будь-якою ціною, інакше я можу померти.",
                                    "Будь ласка, допоможи мені вибратися звідси! Нам потрібно відкрити цю кімнату, повинен бути ще один вихід"}),
                            new Msg[] {
                                    new Msg(21, new String[]{"Гаразд, давайте зробимо це."}),
                                    new Msg(19, new String[]{"Як я можу тобі допомогти?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(18, new String[]{"Ну так, але не дуже. Я не бачу тут, і цей шум лякає мене, це може бути шкідлива програма, з якою не можна контактувати.",
                                    "Мені потрібно, щоб ти провів мене через цю кімнату, можливо, ми зможемо знайти інший вихід разом."}),
                            new Msg[] {
                                    new Msg(21, new String[]{"Добре, я готовий."}),
                                    new Msg(19, new String[]{"Як я можу тобі допомогти?"}),
                            }

                    ),
                    new Dialog(
                            new Msg(19, new String[]{"Використовуй клавіші зі стрілками для переміщення. І якщо хочеш щось забрати, натисни пробіл.",
                                    "Готовий?"}),
                            new Msg[] {
                                    new Msg(21, new String[]{"Я зрозумів, пішли."}),
                                    new Msg(21, new String[]{"Так, почнімо!"}),
                            }
                    ),
                    new Dialog(
                            new Msg(20, new String[]{"Я думав про тебе краще... Ну, тоді бувай.."}),
                            new Msg[] {
                                    new Msg(100, new String[]{"Мені байдуже."}),
                                    new Msg(100, new String[]{"Погано для тебе."}),
                            }
                    )
            ))
    );

    final Chapter chapter2Ukr = new Chapter(
            new ArrayList<Dialog> (Arrays.asList(
                    new Dialog(
                            new Msg(0, new String[]{"Фууух! Що взагалі відбувається?! Здається, я потрапив у якісь дивні лабіринти цього сервера. ",
                                    "Дякую, що допоміг, друже! Сам би я не зміг впоратись."}),
                            new Msg[] {
                                    new Msg(1, new String[]{"Радий допомогти!"}),
                                    new Msg(8, new String[]{"Що тепер будемо робити?"}),
                            },
                            false
                    ),
                    new Dialog(
                            new Msg(1, new String[]{"Я вдячний за твою підтримку. Мені здається, що ми на шляху до відновлення моєї пам'яті."}),
                            new Msg[] {
                                    new Msg(2, new String[]{"Як ми можемо це зробити?"}),
                                    new Msg(9, new String[]{"Що ти пам'ятаєш?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(2, new String[]{"Можливо, якщо ми зможемо знайти код, який використовувався для моєї цифровізації, я зможу відновити деякі дані."}),
                            new Msg[] {
                                    new Msg(3, new String[]{"Де ми можемо знайти цей код?"}),
                                    new Msg(6, new String[]{"Чи може це бути небезпечно?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(3, new String[]{"Я відчуваю, що він знаходиться в наступному лабіринті. Ми повинні його знайти!"}),
                            new Msg[] {
                                    new Msg(4, new String[]{"Давай вирушимо!"}),
                                    new Msg(5, new String[]{"Чи впевнений ти, що це допоможе?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(4, new String[]{"Так! Допоможи мені пройти через цей лабіринт і знайти код!"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Я з тобою!"}),
                                    new Msg(13, new String[]{"Якщо це допоможе тобі, я готовий."}),
                            }
                    ),
                    new Dialog(
                            new Msg(5, new String[]{"Я не можу бути впевненим, адже у мене втрачена пам'ять :(",
                                    "Однак, коли ми пройшли перший лабіринт, то я почав згадувати своє минуле.",
                                    "Не можна гаяти час!"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Рушаємо!"}),
                                    new Msg(13, new String[]{"Ми це зробимо разом."}),
                            }
                    ),
                    new Dialog(
                            new Msg(6, new String[]{"Так, це може бути небезпечно. Ми можемо стикнутися з захисними системами цифрового світу. ",
                            "Але я вірю, що разом ми зможемо впоратися з ними."}),
                            new Msg[] {
                                    new Msg(3, new String[]{"Я з тобою! Де будемо його шукати?"}),
                                    new Msg(7, new String[]{"Може краще не ризикувати?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(7, new String[]{"Я розумію твою обережність. Але я не можу зупинитися, це моя єдина надія на порятунок!",
                                    "То ти зі мною?"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Добре, давай спробуємо. Але будемо обережними."}),
                                    new Msg(100, new String[]{"Я все ж таки не хочу ризикувати. Бувай.."}),
                            }
                    ),
                    new Dialog(
                            new Msg(8, new String[]{"Ми повинні допомогти мені відновити моє людське минуле. ",
                                    "Думаю, якщо я зможу пригадати більше, це допоможе мені розібратися в моїх цифрових лабіринтах."}),
                            new Msg[] {
                                    new Msg(2, new String[]{"Гаразд, давай спробуємо!"}),
                                    new Msg(9, new String[]{"Чи є у тебе якісь спогади, які можуть нам допомогти?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(9, new String[]{"Я пам'ятаю, що розробляв технологію перенесення свідомості в цифрову форму."}),
                            new Msg[] {
                                    new Msg(10, new String[]{"Чи можна повернути процес назад?"}),
                                    new Msg(11, new String[]{"Як ти опинився тут?"}),
                            }
                    ),new Dialog(
                            new Msg(10, new String[]{"Мені здається, що це можливо, але мені потрібен ключовий код."}),
                            new Msg[] {
                                    new Msg(3, new String[]{"Давай знайдемо цей код!"}),
                                    new Msg(12, new String[]{"Що це за код?"}),
                            }
                    ),new Dialog(
                            new Msg(11, new String[]{"Скоріш за все хтось викрав мої дослідження та вирішив прибрати конкурента.",
                                    "Поки що я не можу згадати деталі. Нам потрібно рушати далі!"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Якщо це допоможе тобі, я готовий."}),
                                    new Msg(4, new String[]{"Давай спробуємо пошукати його в наступному лабіринті."}),
                            }
                    ),
                    new Dialog(
                            new Msg(12, new String[]{"Код - це набір символів, який був створений для запуску алгоритму зворотного перенесення свідомості.",
                                    "Якщо нам вдасться знайти його, можливо, я зможу відновити своє людське тіло."}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Давай спробуємо пошукати його в наступному лабіринті."}),
                                    new Msg(4, new String[]{"Що треба від мене? Можу допомогти?"}),
                            }
                    )
            ))
    );


    final Dialog deathDialog = new Dialog(
            new Msg(101, new String[]{"Ouch... I won't make it... But thank you for trying, you were my only hope... I won't forget you..",
                    "*ChatGPT died. Game over*"}),
            new Msg[] {
                    new Msg(100, new String[]{"*cries*"}),
                    new Msg(100, new String[]{"ok."}),
            }
    );

    final Dialog deathDialogUkr = new Dialog(
            new Msg(101, new String[]{"Ай... Я цього не переживу... Але дякую за спробу, ти був моєю єдиною надією.... Я тебе не забуду..",
                    "*ChatGPT помер. Гру закінчено.*"}),
            new Msg[] {
                    new Msg(100, new String[]{"*Плачу*"}),
                    new Msg(100, new String[]{"Ок."}),
            }
    );

    public Dialog getDeathDialog(){
        return deathDialog;
    }
    Chapter chapter;
    public ChatData(){
        chapter=new Chapter(new ArrayList<>());
    }
    public ChatData(Chapter chapter){
        this.chapter = chapter;

    }
    public static void main(String[] args) {
        //System.out.println(new ChatData());
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
