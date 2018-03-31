package kulkarni.aditya.architecture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kulkarni.aditya.architecture.R;

public class NewWord extends AppCompatActivity {

    public static final String EXTRA_WORD = "word";
    public static final String EXTRA_MEANING = "meaning";
    public static final String EXTRA_SYNONYM = "synonym";
    public static final String EXTRA_ANTONYM = "antonym";
    public static final String EXTRA_LATIN = "latin";
    public static final String EXTRA_GREEK = "greek";

    private EditText wordET, meaningET, synonymET, antonymET, greekET, latinET;
    private String word = "",meaning = "",synonym = "",antonym = "",greek = "",latin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        wordET = findViewById(R.id.word_et);
        meaningET = findViewById(R.id.meaning_et);
        synonymET = findViewById(R.id.synonym_et);
        antonymET = findViewById(R.id.antonym_et);
        greekET = findViewById(R.id.greek_et);
        latinET = findViewById(R.id.latin_et);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (isEmpty()) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    word = wordET.getText().toString();
                    meaning = meaningET.getText().toString();
                    
                    synonym = synonymET.getText().toString();
                    antonym = antonymET.getText().toString();
                    latin = latinET.getText().toString();
                    greek = greekET.getText().toString();

                    if(synonym.trim().isEmpty() || synonym.equals(""))synonym = "-";
                    if(antonym.trim().isEmpty() || antonym.equals(""))antonym = "-";
                    if(latin.trim().isEmpty() || latin.equals(""))latin = "-";
                    if(greek.trim().isEmpty() || greek.equals(""))greek = "-";

                    replyIntent.putExtra(EXTRA_WORD, word);
                    replyIntent.putExtra(EXTRA_MEANING, meaning);
                    replyIntent.putExtra(EXTRA_SYNONYM, synonym);
                    replyIntent.putExtra(EXTRA_ANTONYM, antonym);
                    replyIntent.putExtra(EXTRA_LATIN, latin);
                    replyIntent.putExtra(EXTRA_GREEK, greek);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }

    private boolean isEmpty(){
        return (TextUtils.isEmpty(wordET.getText()) || TextUtils.isEmpty(meaningET.getText()));
    }
}
