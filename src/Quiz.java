import java.util.ArrayList;
import java.util.Arrays;

public class Quiz {
    private String question;
    private ArrayList<Answer> answers;
    private boolean completed = false;
    private boolean answeredCorrectly;
    private int chosenAnswer;

    public int getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(int chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    public boolean isAnsweredCorrectly() {
        return answeredCorrectly;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Отримує індекс правильної відповіді.
     * @return індекс правильної відповіді або -1, якщо правильна відповідь не знайдена
     */
    public int getCorrectAnswerIndex() {
        Object[] bools =  answers.stream().map(Answer::isCorrect).toArray();
        return Arrays.stream(bools).toList().indexOf(true);
    }


    /**
     * Конструктор для створення об'єкта вікторини.
     *
     * @param question питання тесту
     * @param answers  список відповідей на питання
     */
    public Quiz(String question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "\nquestion='" + question + '\'' +
                ", \nanswers=" + answers +
                ", \ncompleted=" + completed +
                "\n}";
    }
}
class Answer {
    private String text;
    private boolean correct = false;

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }

    /**
     * Конструктор класу Answer (відповідь на вікторину).
     * Створює об'єкт Answer з вказаним текстом.
     * По замовчуванню вона неправильна
     *
     * @param text текст відповіді
     */
    public Answer(String text) {
        this.text = text;
    }
    /**
     * Конструктор класу Answer (відповідь на вікторину).
     * Створює об'єкт Answer з вказаним текстом та позначенням правильності.
     * @param text текст відповіді
     * @param correct позначення правильності відповіді
     */
    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "{" + text + "(" + correct +")}";
    }
}