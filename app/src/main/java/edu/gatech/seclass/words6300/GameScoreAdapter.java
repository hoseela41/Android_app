package edu.gatech.seclass.words6300;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GameScoreAdapter extends ArrayAdapter<GameRecord> {

    private static final String TAG = "GameScoreAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView fianl_score;
        TextView turn;
        TextView avg_score;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public GameScoreAdapter(Context context, int resource, ArrayList<GameRecord> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the game record information
        int id = getItem(position).getId();
        int final_score = getItem(position).getFinal_socre();
        int turn = getItem(position).getTurn();
        float avg_score = getItem(position).getAvg_socre();
        int max_turn = getItem(position).getMax_turn();
        byte[] alphabetFreq = getItem(position).getAlphabetFreq();
        byte[] alphabetScore = getItem(position).getAlphabetScore();

        //Create the gameRecord object with the information
        GameRecord gameRecord = new GameRecord(id,final_score,turn,avg_score,max_turn,alphabetFreq, alphabetScore);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.fianl_score = (TextView) convertView.findViewById(R.id.textView1);
            holder.turn = (TextView) convertView.findViewById(R.id.textView2);
            holder.avg_score = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        DecimalFormat df = new DecimalFormat("0.00");

        holder.fianl_score.setText("Final Score :" + String.valueOf(gameRecord.getFinal_socre()));
        holder.turn.setText("Number of turns :" + String.valueOf(gameRecord.getTurn()));
        holder.avg_score.setText("Average score per turn :" + String.valueOf(df.format(gameRecord.getAvg_socre())));

        return convertView;
    }
}