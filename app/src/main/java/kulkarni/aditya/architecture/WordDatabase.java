package kulkarni.aditya.architecture;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import kulkarni.aditya.architecture.model.Word;

/**
 * Created by maverick on 3/21/18.
 */

@Database(entities = {Word.class}, version = 1)
public abstract class WordDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordDatabase INSTANCE;

    static WordDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDatabase.class, "word_list_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
