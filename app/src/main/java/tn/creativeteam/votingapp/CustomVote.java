package tn.creativeteam.votingapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomVote extends ArrayAdapter<String>{

    Activity context;
    String[] ids;
    String[] names;
    String[] votes;
    Integer[]photos;

    public CustomVote(@NonNull Activity context, int resource,
                      String [] i, String [] n,String [] v,Integer [] p) {
        super(context, R.layout.my_row,i);

        this.context=context;
        ids=i;
        names=n;
        votes=v;
        photos=p;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(R.layout.my_row,null,true);

        TextView tvID=(TextView)v.findViewById(R.id.star_id);
        TextView tvName=(TextView)v.findViewById(R.id.star_name);
        TextView tvVote=(TextView)v.findViewById(R.id.star_vote);
        ImageView iv= (ImageView)v.findViewById(R.id.star_photo);

        tvID.setText(ids[position]);
        tvName.setText(names[position]);
        tvVote.setText(votes[position]);
        iv.setImageResource(photos[position]);

        return v;
    }
}
