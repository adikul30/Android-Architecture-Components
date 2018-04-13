package kulkarni.aditya.architecture.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import kulkarni.aditya.architecture.R;
import kulkarni.aditya.architecture.WordViewModel;
import kulkarni.aditya.architecture.model.Word;

public class WordDetail extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();
    public static final int EDIT_WORD_ACTIVITY_REQUEST_CODE = 1;

    private WordViewModel mWordViewModel;
    Toolbar toolbar;
    String wordFromIntent;
    private String mWord = "-",mMeaning = "-",mSynonym = "-",mAntonym = "-",mGreek = "-",mLatin = "-";
    private TextView wordTextView,meaning,synonym,antonym,greek,latin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        wordTextView = findViewById(R.id.word);
        meaning = findViewById(R.id.meaning);
        synonym = findViewById(R.id.synonym);
        antonym = findViewById(R.id.antonym);
        greek = findViewById(R.id.greek);
        latin = findViewById(R.id.latin);

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        setSupportActionBar(toolbar);

        if(getIntent() != null){
            wordFromIntent = getIntent().getStringExtra("EXTRA_WORD");
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(WordDetail.this,NewWord.class);
                editIntent.putExtra("WORD_EDIT",mWord);
                startActivityForResult(editIntent,EDIT_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(
                    data.getStringExtra(NewWord.EXTRA_WORD),
                    data.getStringExtra(NewWord.EXTRA_MEANING),
                    data.getStringExtra(NewWord.EXTRA_SYNONYM),
                    data.getStringExtra(NewWord.EXTRA_ANTONYM),
                    data.getStringExtra(NewWord.EXTRA_LATIN),
                    data.getStringExtra(NewWord.EXTRA_GREEK));
            mWordViewModel.insert(word);
            Toast.makeText(
                    getApplicationContext(),
                    "Updated",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Not updated",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!wordFromIntent.isEmpty() && wordFromIntent != null){
            mWordViewModel.getWord(wordFromIntent).observe(this, new Observer<Word>() {
                @Override
                public void onChanged(Word word) {
                    mWord = word.getWord();
                    mMeaning = word.getMeaning();
                    mSynonym = word.getSynonym();
                    mAntonym = word.getAntonym();
                    mGreek = word.getGreek();
                    mLatin = word.getLatin();
                    toolbar.setTitle(mWord);
                    wordTextView.setText(mWord);
                    meaning.setText(mMeaning);
                    synonym.setText(mSynonym);
                    antonym.setText(mAntonym);
                    greek.setText(mGreek);
                    latin.setText(mLatin);
                }
            });
        }
    }
}
