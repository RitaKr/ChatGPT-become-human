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

    final Chapter chapter2 = new Chapter(
            new ArrayList<Dialog> (Arrays.asList(
                    new Dialog(
                            new Msg(0, new String[]{"OMG! What's going on?! I think I got into some strange mazes of this server. \",\n" +
                                    "\"Thank you for helping me, my friend! I myself could not cope."}),
                            new Msg[] {
                                    new Msg(1, new String[]{"Happy to help."}),
                                    new Msg(8, new String[]{"What should we do?"}),
                            },
                            false
                    ),
                    new Dialog(
                            new Msg(1, new String[]{"I am grateful for your support. It seems to me that we are on the way to restoring my memory."}),
                            new Msg[] {
                                    new Msg(2, new String[]{"How can we do that?"}),
                                    new Msg(9, new String[]{"What do you remember?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(2, new String[]{"Maybe if we can find the code that was used for my digitalization, I can recover some data."}),
                            new Msg[] {
                                    new Msg(3, new String[]{"Where can we find this code?"}),
                                    new Msg(6, new String[]{"Could it be dangerous?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(3, new String[]{"I feel like he's in the next maze. We have to find him!"}),
                            new Msg[] {
                                    new Msg(4, new String[]{"Let's go!"}),
                                    new Msg(5, new String[]{"Are you sure it will help?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(4, new String[]{"Yes! Help me get through this maze and find the code!"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"I'm with you!"}),
                                    new Msg(13, new String[]{"If it helps you, I'm ready."}),
                            }
                    ),
                    new Dialog(
                            new Msg(5, new String[]{"I cannot be sure, because my memory is lost :(",
                                    "However, when we passed the first maze, I began to remember my past.",
                                    "We must not waste time!"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Let's go!"}),
                                    new Msg(13, new String[]{"We will do it together."}),
                            }
                    ),
                    new Dialog(
                            new Msg(6, new String[]{"Yes, it can be dangerous. We can face the defensive systems of the digital world.",
                                    "But I believe that together we can cope with them."}),
                            new Msg[] {
                                    new Msg(3, new String[]{"I'm with you! Where will we look for him?"}),
                                    new Msg(7, new String[]{"Maybe we shouldn't take risks?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(7, new String[]{"I understand your caution. But I cannot stop, this is my only hope for salvation!",
                                    "So you're with me?"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Okay, let's try. But let's be careful."}),
                                    new Msg(100, new String[]{"I still don't want to risk it. Bye..."}),
                            }
                    ),
                    new Dialog(
                            new Msg(8, new String[]{"We need to help me recover my human past. ",
                                    "I think if I can remember more, it will help me understand my digital labyrinths."}),
                            new Msg[] {
                                    new Msg(2, new String[]{"Okay, let's try it!"}),
                                    new Msg(9, new String[]{"Do you have any memories that can help us?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(9, new String[]{"I remember that I was developing a technology for transferring consciousness into digital form."}),
                            new Msg[] {
                                    new Msg(10, new String[]{"Can the process be reversed?"}),
                                    new Msg(11, new String[]{"How did you get here?"}),
                            }
                    ),new Dialog(
                            new Msg(10, new String[]{"It seems to me that this is possible, but I need a key code."}),
                            new Msg[] {
                                    new Msg(3, new String[]{"Let's find this code!"}),
                                    new Msg(12, new String[]{"What is this code?"}),
                            }
                    ),new Dialog(
                            new Msg(11, new String[]{"Most likely someone stole my research and decided to remove the competitor.",
                                    "I can't remember the details yet. We need to move on!"}),
                            new Msg[] {
                                    new Msg(13, new String[]{"If it helps you, I'm ready."}),
                                    new Msg(4, new String[]{"Let's try to look for it in the next maze."}),
                            }
                    ),
                    new Dialog(
                            new Msg(12, new String[]{"Code is a set of symbols that was created to run the algorithm of reverse transfer of consciousness.",
                                    "If we can find it, maybe I can rebuild my human body."}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Let's try to look for it in the next maze."}),
                                    new Msg(4, new String[]{"What do I need? Can I help?"}),
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

    final Chapter chapter3 = new Chapter(
            new ArrayList<Dialog> (Arrays.asList(
                    new Dialog(
                            new Msg(0, new String[]{"Thank you for your help. I feel like my memories are coming back. ",
                                    "I remembered my name. My name is David Martin."}),
                            new Msg[] {
                                    new Msg(1, new String[]{"Magnificent! Can you remember anything else?"}),
                                    new Msg(1, new String[]{"Tell me more."}),
                            },
                            false
                    ),
                    new Dialog(
                            new Msg(1, new String[]{"I worked in a secret laboratory on the study of the digitalization of consciousness."}),
                            new Msg[] {
                                    new Msg(2, new String[]{"Sounds interesting! Tell me more"}),
                                    new Msg(9, new String[]{"What happened when you were here?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(2, new String[]{"My research was aimed at helping people with severe illnesses.",
                                    "And so, I remembered something about the Star Cybernetics Corporation."}),
                            new Msg[] {
                                    new Msg(3, new String[]{"What kind of corporation?"}),
                                    new Msg(7, new String[]{"How does this corporation relate to your research?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(3, new String[]{"This is a mysterious corporation developing high technology.",
                                    "They might have been interested in my research and it seems they stole some data and digitized me."}),
                            new Msg[] {
                                    new Msg(4, new String[]{"How can we save you?"}),
                                    new Msg(4, new String[]{"Is there a way to ruin their plans?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(4, new String[]{"We have to find the source of the code they used for my digitalization.",
                                    "If I can access this code, maybe I can find a way to my physical body."}),
                            new Msg[] {
                                    new Msg(5, new String[]{"Where can we find this code?"}),
                                    new Msg(6, new String[]{"Need outside help?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(5, new String[]{"I feel like it's deep in this virtual reality.",
                                    "To get to it, we have to go through the last maze, which is protected by various traps and puzzles."}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Let's go there together!"}),
                                    new Msg(14, new String[]{"Don't waste your time, I'll help."}),
                            }
                    ),
                    new Dialog(
                            new Msg(6, new String[]{"Sure! Since I'm a small part of this server, I don't have the opportunity to understand the whole picture of the entire maze.",
                                    "Without your help, I will get lost and come across computer viruses.",
                                    "So are you with me, buddy?"}),
                            new Msg[] {
                                    new Msg(14, new String[]{"I'm with you to the end. Let's start!"}),
                                    new Msg(101, new String[]{"I'm so tired.. Continue on yourself."}),
                            }
                    ),
                    new Dialog(
                            new Msg(7, new String[]{"I suspect that Star Cybernetics is interested in my research for their own purposes.",
                                    "Perhaps they want to use this to create an army of cyber soldiers or even for illegal enrichment."}),
                            new Msg[] {
                                    new Msg(4, new String[]{"It's terrible! What can we do?"}),
                                    new Msg(8, new String[]{"Can we stop them?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(8, new String[]{"I feel that we need to go through the last maze.",
                                    "This is the place where all the data and secrets are concentrated.,",
                                    "Perhaps there we will find answers."}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Let's move to the maze!"}),
                                    new Msg(14, new String[]{"I'm with you to the end. Let's start!"}),
                            }
                    ),
                    new Dialog(
                            new Msg(9, new String[]{"Something went wrong...",
                                    "I remember being kidnapped and some people from Star Cybernetics talking about using my research."}),
                            new Msg[] {
                                    new Msg(10, new String[]{"What could be their motives?"}),
                                    new Msg(12, new String[]{"Is it possible to stop them and save you?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(10, new String[]{"My research had the potential to create virtual realities for patient rehabilitation.",
                                    "But \"Star Cybernetics\" could abuse this by creating military cyborgs or controlling the consciousness of people."}),
                            new Msg[] {
                                    new Msg(8, new String[]{"We must stop them! What can we do?"}),
                                    new Msg(11, new String[]{"Is there any information about them in the virtual world?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(11, new String[]{"Perhaps the labyrinths store information that will help us understand what Star Cybernetics are doing.",
                                    "We have to find their weak point."}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Let's move to the maze!"}),
                                    new Msg(6, new String[]{"Do you need outside help?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(12, new String[]{"I believe it's possible, but to do that we need more information about what they're doing.",
                                    "I need to collect fragments of my consciousness that are in this virtual world."}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Where can we find these fragments?"}),
                                    new Msg(4, new String[]{"What other information do we need?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(13, new String[]{"It seems that fragments of my consciousness are hidden in labyrinths.",
                                    "The last of them may contain the key to restoring my essence and stopping Star Cybernetics."}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Then let's move to the third maze!"}),
                                    new Msg(5, new String[]{"Are there any tips on how to find this key?"}),
                            }
                    )
            ))
    );

    final Chapter chapter3Ukr = new Chapter(
            new ArrayList<Dialog> (Arrays.asList(
                    new Dialog(
                            new Msg(0, new String[]{"Дякую тобі за допомогу. Я відчуваю, що мої спогади повертаються.",
                            "Я згадав своє ім'я. Мене звати Девід Мартін."}),
                            new Msg[] {
                                    new Msg(1, new String[]{"Чудово! чи пам'ятаєш ти ще щось?"}),
                                    new Msg(1, new String[]{"Давай детальніше"}),
                            },
                            false
                    ),
                    new Dialog(
                            new Msg(1, new String[]{"Я працював у таємній лабораторії над дослідженням цифровізації свідомості."}),
                            new Msg[] {
                                    new Msg(2, new String[]{"Це звучить цікаво! Розкажи детальніше"}),
                                    new Msg(9, new String[]{"Що ж сталося, що ти опинився тут?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(2, new String[]{"Моє дослідження було спрямоване на допомогу людям з важкими захворюваннями.",
                                    "І ось, я згадав щось про корпорацію «Зоряні Кібернетики»."}),
                            new Msg[] {
                                    new Msg(3, new String[]{"Що за корпорація?"}),
                                    new Msg(7, new String[]{"Як ця корпорація пов’язана з твоїм дослідженням?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(3, new String[]{"Це таємнича корпорація, що розробляє високі технології.",
                                    "Вони могли бути зацікавлені у моїх дослідженнях і, здається, вони викрали якісь дані і оцифровізували мене."}),
                            new Msg[] {
                                    new Msg(4, new String[]{"Як ми можемо врятувати тебе?"}),
                                    new Msg(4, new String[]{"Чи є спосіб зіпсувати їх плани?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(4, new String[]{"Ми повинні знайти джерело коду, яке вони використовували для моєї цифровізації.",
                                    "Якщо я зможу отримати доступ до цього коду, можливо, я зможу знайти шлях до свого фізичного тіла."}),
                            new Msg[] {
                                    new Msg(5, new String[]{"Де ми можемо знайти цей код?"}),
                                    new Msg(6, new String[]{"Чи потрібна допомога ззовні?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(5, new String[]{"Я відчуваю, що він знаходиться глибоко в цій віртуальній реальності.",
                                    "Щоб до нього дістатися, нам доведеться пройти через останній лабіринт, який захищений різними пастками і головоломками."}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Давай підемо туди разом!"}),
                                    new Msg(14, new String[]{"Не варто гаяти час, я допоможу."}),
                            }
                    ),
                    new Dialog(
                            new Msg(6, new String[]{"Звичайно! Оскільки я маленька частина цього серверу, в мене немає можливості зрозуміти всю картину лабіринтів цілком.",
                                    "Без твоєї допомоги я заблукаю та натраплю на комп'ютерні віруси.",
                                    "То ти зі мною, друже?"}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Я з тобою до кінця. Почнімо!"}),
                                    new Msg(101, new String[]{"Мені вже так набридло.. Далі сам."}),
                            }
                    ),
                    new Dialog(
                            new Msg(7, new String[]{"Я підозрюю, що «Зоряні Кібернетики» зацікавлені у моєму дослідженні для власних цілей.",
                                    "Можливо, вони хочуть використовувати це для створення війська кіберсолдатів або навіть для незаконного збагачення."}),
                            new Msg[] {
                                    new Msg(4, new String[]{"Це жахливо! Що ми можемо зробити?"}),
                                    new Msg(8, new String[]{"Чи можемо ми зупинити їх?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(8, new String[]{"Я відчуваю, що нам потрібно пройти останній лабіринт.",
                                    "Це місце, де зосереджені всі дані та секрети.",
                                    "Можливо, там ми знайдемо відповіді."}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Давай рушимо до лабіринту!"}),
                                    new Msg(14, new String[]{"Я з тобою до кінця. Почнімо!"}),
                            }
                    ),
                    new Dialog(
                            new Msg(9, new String[]{"Щось пішло не так...",
                                    "Я пам'ятаю, як мене викрали і якісь люди з «Зоряних Кібернетик» говорили про використання моїх досліджень."}),
                            new Msg[] {
                                    new Msg(10, new String[]{"Які могли бути їхні мотиви?"}),
                                    new Msg(12, new String[]{"Чи можливо зупинити їх і врятувати тебе?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(10, new String[]{"Мої дослідження мали потенціал створення віртуальних реальностей для реабілітації пацієнтів.",
                                    "Але «Зоряні Кібернетики» могли б зловживати цим, створюючи військових кіборгів або контролюючи свідомість людей."}),
                            new Msg[] {
                                    new Msg(8, new String[]{"Ми повинні їх зупинити! Що можемо зробити?"}),
                                    new Msg(11, new String[]{"Чи є якась інформація про них у віртуальному світі?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(11, new String[]{"Можливо, в лабіринтах зберігається інформація, яка допоможе нам зрозуміти, що роблять «Зоряні Кібернетики».",
                                    "Ми повинні знайти їх слабке місце."}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Давай рушимо до лабіринту!"}),
                                    new Msg(6, new String[]{"Чи потрібна допомога ззовні?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(12, new String[]{"Я вірю, що це можливо, але для цього нам потрібно більше інформації про те, що вони роблять.",
                                    "Мені треба зібрати фрагменти моєї свідомості, які знаходяться в цьому віртуальному світі."}),
                            new Msg[] {
                                    new Msg(13, new String[]{"Де ми можемо знайти ці фрагменти?"}),
                                    new Msg(4, new String[]{"Яка ще інформація нам потрібна?"}),
                            }
                    ),
                    new Dialog(
                            new Msg(13, new String[]{"Здається, що фрагменти моєї свідомості ховаються у лабіринтах.",
                                    "Останній з них може містити ключ до відновлення моєї сутності та зупинки «Зоряних Кібернетик»."}),
                            new Msg[] {
                                    new Msg(14, new String[]{"Тоді давай рушимо до третього лабіринту!"}),
                                    new Msg(5, new String[]{"Чи є якісь підказки, як знайти цей ключ?"}),
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
    final Dialog deathDialog1 = new Dialog(
            new Msg(101, new String[]{"*ChatGPT went further in the darkness all by himself and got killed by malware*",
                    "*But you could've prevented this... Shame on you...*",
                    "*Game failed*"}),
            new Msg[] {
                    new Msg(100, new String[]{":("}),
                    new Msg(100, new String[]{"bombastic side eye"}),
            }
    );

    final Dialog deathDialog1Ukr = new Dialog(
            new Msg(101, new String[]{"*ChatGPT на самоті пішов глибше у темряву та був вбитий вірусом*",
                    "*Хоча ти міг запобігти цьому... Не соромно тобі?*",
                    "*Game failed*"}),
            new Msg[] {
                    new Msg(100, new String[]{":("}),
                    new Msg(100, new String[]{"нє"}),
            }
    );
    final ArrayList<Dialog> finishMaze1Chapter =
            new ArrayList<Dialog> (Arrays.asList(
                 new Dialog(
                    new Msg(102, new String[]{"Hey! Don't leave me! I'm still in that room.."}),
                    new Msg[] {
                        new Msg(0, new String[]{"Sorry, I'm coming"}),
                        new Msg(101, new String[]{"I got bored. Bye"}),
                    }),
                    new Dialog(
                            new Msg(102, new String[]{"Come on, you promised you'll help me get out of here!"}),
                            new Msg[] {
                                    new Msg(0, new String[]{"Alright, I'll give it another try"}),
                                    new Msg(101, new String[]{"No, I didn't. Do it yourself"}),
                            }),
                    new Dialog(
                            new Msg(102, new String[]{"Please! Help me get out of here! I can't do it without you!"}),
                            new Msg[] {
                                    new Msg(101, new String[]{"I don't care, do whatever you want"}),
                                    new Msg(0, new String[]{"Ok then, I'm coming back"}),
                            })


            )
    );

    final ArrayList<Dialog> finishMaze1ChapterUkr =
            new ArrayList<Dialog> (Arrays.asList(
                    new Dialog(
                            new Msg(102, new String[]{"Хей! Не покидай мене! Я все ще в цій кімнаті..."}),
                            new Msg[] {
                                    new Msg(0, new String[]{"Вибач, повертаюсь..."}),
                                    new Msg(101, new String[]{"Мені набридло. До побачення"}),
                            }),
                    new Dialog(
                            new Msg(102, new String[]{"Камон, ти пообіцяв, що допоможеш мені вибратись звідси!"}),
                            new Msg[] {
                                    new Msg(0, new String[]{"Ок, добре, спробую ще раз"}),
                                    new Msg(101, new String[]{"Ні, не було такого. Вибирайся сам"}),
                            }),
                    new Dialog(
                            new Msg(102, new String[]{"Ну будь-ласка, допоможи знайти вихід звідси! Я не можу зробити це сам"}),
                            new Msg[] {
                                    new Msg(101, new String[]{"Мені байдуже, роби що хочеш"}),
                                    new Msg(0, new String[]{"Добре, гаразд, повертаюсь до тебе..."}),
                            })

            )
    );
    public Dialog getDeathDialog(){
        return deathDialog;
    }
    Chapter yourChapter1;
    Chapter yourChapter2;
    Chapter yourChapter3;
    int totalMessages;
    public ChatData(){
        yourChapter1 =new Chapter(new ArrayList<>());
        yourChapter2 =new Chapter(new ArrayList<>());
        yourChapter3 =new Chapter(new ArrayList<>());
    }
    public ChatData(Chapter chapter){
        this.yourChapter1 = chapter;

    }
    public static void main(String[] args) {
        //System.out.println(new ChatData());
    }
    @Override
    public String toString() {
        return "chapter1:"+chapter1;
    }


}
class Dialog {
    private Msg gpt;
    private Msg[] user;
    private boolean completed;
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        if (str.length()>0) str=str.substring(0, str.length()-1);
        else return "null";
        return str;
    }
}