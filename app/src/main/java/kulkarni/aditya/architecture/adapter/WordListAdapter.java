package kulkarni.aditya.architecture.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kulkarni.aditya.architecture.R;
import kulkarni.aditya.architecture.activity.WordDetail;
import kulkarni.aditya.architecture.model.Word;

/**
 * Created by maverick on 3/21/18.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    private List<Word> mWords,mWordsCopy;
    private Context mContext;

    public WordListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.word_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.word.setText(mWords.get(position).getWord());
        holder.meaning.setText(mWords.get(position).getMeaning());
    }

    public void setToDos(List<Word> words){
        mWords = words;
        mWordsCopy = new ArrayList<>();
        mWordsCopy.addAll(mWords);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView word, meaning;
        private final LinearLayout wordLayout;
        ViewHolder(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
            meaning = itemView.findViewById(R.id.meaning);
            wordLayout = itemView.findViewById(R.id.word_layout);

            wordLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent wordDetailIntent = new Intent(mContext, WordDetail.class);
                    int position = getAdapterPosition();
//                    Word wordObject = mWords.get(position);
                    wordDetailIntent.putExtra("EXTRA_WORD",mWords.get(position).getWord());
                    mContext.startActivity(wordDetailIntent);
                }
            });

            wordLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(mContext,"long",Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    }

    public void filter(String query) {
        query = query.toLowerCase(Locale.getDefault());
        mWords.clear();
        if (query.length() == 0) {
            mWords.addAll(mWordsCopy);
        } else {

            for (int i = 0; i < mWordsCopy.size(); i++) {

                String word = mWordsCopy.get(i).getWord();
                String meaning = mWordsCopy.get(i).getMeaning();

                if (word != null && meaning != null) {
                    if (word.toLowerCase().contains(query) || meaning.toLowerCase().contains(query)) {
                        mWords.add(mWordsCopy.get(i));
                    }
                }
            }
        }

        notifyDataSetChanged();


    }
}
