package kulkarni.aditya.architecture.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by maverick on 3/21/18.
 */

@Entity(tableName = "word_list")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String word;

    @NonNull
    @ColumnInfo(name = "meaning")
    private String meaning;

    @ColumnInfo(name = "synonym")
    private String synonym;

    @ColumnInfo(name = "antonym")
    private String antonym;

    @ColumnInfo(name = "greek")
    private String greek;

    @ColumnInfo(name = "latin")
    private String latin;

    @ColumnInfo(name = "example")
    private String example;

    public Word(@NonNull String word, @NonNull String meaning, String synonym, String antonym, String greek, String latin, String example) {
        this.word = word;
        this.meaning = meaning;
        this.synonym = synonym;
        this.antonym = antonym;
        this.greek = greek;
        this.latin = latin;
        this.example = example;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    @NonNull
    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(@NonNull String meaning) {
        this.meaning = meaning;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getAntonym() {
        return antonym;
    }

    public void setAntonym(String antonym) {
        this.antonym = antonym;
    }

    public String getGreek() {
        return greek;
    }

    public void setGreek(String greek) {
        this.greek = greek;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public static Comparator<Word> comparator = new Comparator<Word>() {
        @Override
        public int compare(Word w1, Word w2) {

            String firstWord = w1.getWord().toLowerCase();
            String secondWord = w2.getWord().toLowerCase();

            return firstWord.compareTo(secondWord);
        }
    };
}
