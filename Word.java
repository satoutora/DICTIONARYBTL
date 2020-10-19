package sample;

public class Word {
    private String word_target;
    private String word_type;
    private String word_means;

    public String getWord_target() {
        return this.word_target;
    }

    public String getWord_means( ) {
        return this.word_means;
    }

    public String getWord_type() {
        return this.word_type;
    }

    public void setWord_type(String word_type) {
        this.word_type = word_type;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public void setWord_mean(String word_means) {
        this.word_means = word_means;
    }

    Word() {}

    public Word(String word_target, String word_type, String word_means) {
        this.word_target = word_target;
        this.word_type = word_type;
        this.word_means = word_means;
    }
}
