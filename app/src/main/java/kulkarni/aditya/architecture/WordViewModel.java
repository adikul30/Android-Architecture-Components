package kulkarni.aditya.architecture;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import kulkarni.aditya.architecture.model.Word;

/**
 * Created by maverick on 3/21/18.
 */

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<PagedList<Word>> mAllWords;
    private LiveData<Word> mWordDetail;

    public WordViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new WordRepository(application);
        this.mAllWords = mRepository.getAllWords();
    }

    public LiveData<PagedList<Word>> getAllWords() { return mAllWords; }

    public LiveData<Word> getWord(String word) {
        this.mWordDetail = mRepository.getmWordDetail(word);
        return mWordDetail;
    }

    public void insert(Word word) {
        mRepository.insertWord(word);
    }

    public void delete(String word){
        mRepository.deleteWord(word);
    }
}
