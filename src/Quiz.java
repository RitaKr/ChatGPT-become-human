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

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
    public Answer getCorrectAnswer() {
        return answers.get(getCorrectAnswerIndex());
    }
    public int getCorrectAnswerIndex() {
        Object[] bools =  answers.stream().map(Answer::isCorrect).toArray();
        return Arrays.stream(bools).toList().indexOf(true);
    }
    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Quiz(String question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;

    }
    public Quiz(String question, ArrayList<Answer> answers, boolean completed) {
        this.question = question;
        this.answers = answers;
        this.completed = completed;
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

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Answer(String text) {
        this.text = text;
    }
    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "{" + text + "(" + correct +")}";
    }
}