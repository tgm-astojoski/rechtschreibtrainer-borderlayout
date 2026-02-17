package model;

public class Frage {
    private String question;
    private String answer;
    private boolean image;

    public Frage(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
        setImage(image);
    }

    @Override
    public String toString() {
        if(image){
            return "0#"+question+"#"+answer;
        }else{
            return "1#"+question+"#"+answer;
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
}
