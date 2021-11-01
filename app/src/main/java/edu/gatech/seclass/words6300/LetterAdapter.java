package edu.gatech.seclass.words6300;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LetterAdapter extends ArrayAdapter<Letter> {

    private LayoutInflater mInflater;
    private ArrayList<Letter> lettersList ;
    private int mViewResourceId;
    //private Context myContext;

    public LetterAdapter(Context context, int textViewResourceId, ArrayList<Letter> letters){
        super(context, textViewResourceId, letters);
        //this.myContext = context;
        this.lettersList = letters;


        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = convertView;
        if (listItem == null)
            listItem = mInflater.inflate(mViewResourceId, parent, false);


       // convertView = mInflater.inflate(mViewResourceId, null);
        Letter letter = lettersList.get(position);
        Log.d("check position", String.valueOf(position));

         if(letter != null){
        TextView letterName= (TextView) listItem.findViewById(R.id.letterName);
        TextView letterPlayed = (TextView) listItem.findViewById(R.id.numOfPlayed);
        TextView letterBackPool = (TextView) listItem.findViewById(R.id.numOfbackpool);
        TextView percentChoesen = (TextView) listItem.findViewById(R.id.percentOfChosen);



            if (letterName != null){
                letterName.setText(letter.getLetter());
        //letterName.setText(letter.getLetter());
            }
            if (letterPlayed != null){
                letterPlayed.setText(String.valueOf(letter.getNumPlayed()));
        //letterPlayed.setText(letter.getNumPlayed());



            }
            if (letterBackPool != null){
                letterBackPool.setText(String.valueOf(letter.getNumBackPool()));
       // letterBackPool.setText(letter.getNumBackPool());

            }
            if (percentChoesen != null){
                percentChoesen.setText(String.valueOf(letter.getPercentChosen()));
        //percentChoesen.setText(String.valueOf(letter.getPercentChosen()));

        Log.d("Deb", "Fabrication de la View : "+position);
            }

     //   }

    }return listItem;


}}
