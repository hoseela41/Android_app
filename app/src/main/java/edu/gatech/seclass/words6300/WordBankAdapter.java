package edu.gatech.seclass.words6300;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class WordBankAdapter extends ArrayAdapter<Word> {

    private static final String TAG = "WordBankAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView word;
        TextView frequency;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public WordBankAdapter(Context context, int resource, ArrayList<Word> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the word information
        String words = getItem(position).getWord();
        int frequency = getItem(position).getFrequency();

        //Create the word object with the information
        Word word = new Word(words,frequency);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.word = (TextView) convertView.findViewById(R.id.textView1);
            holder.frequency = (TextView) convertView.findViewById(R.id.textView2);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        /*
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);


        result.startAnimation(animation);

         */
        lastPosition = position;

        holder.word.setText("Word :" + word.getWord());
        holder.frequency.setText("Frequency :" + String.valueOf(word.getFrequency()));

        return convertView;
    }
}