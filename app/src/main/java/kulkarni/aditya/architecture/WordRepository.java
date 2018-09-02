package kulkarni.aditya.architecture;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.AsyncTask;

import kulkarni.aditya.architecture.model.Word;

/**
 * Created by maverick on 3/21/18.
 */

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<PagedList<Word>> mAllWords;
    private LiveData<Word> mWordDetail;
    private DataSource.Factory<Integer,Word> wordFactory;

    WordRepository(Application application) {
        WordDatabase db = WordDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        wordFactory = mWordDao.getWords();
        mAllWords = new LivePagedListBuilder<>(wordFactory,10).build();
    }

    LiveData<PagedList<Word>> getAllWords() {
        return mAllWords;
    }

    LiveData<Word> getmWordDetail(String word) {
        mWordDetail = mWordDao.getWordDetail(word);
        return mWordDetail;
    }

    void insertWord(Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    void deleteWord(String word){
        new deleteAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word,Void,Void>{

        private WordDao mInsertAsyncTaskDao;

        public insertAsyncTask(WordDao dao) {
            this.mInsertAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mInsertAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<String,Void,Void>{

        private WordDao mDeleteAsyncTaskDao;

        public deleteAsyncTask(WordDao dao) {
            this.mDeleteAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mDeleteAsyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }
}
