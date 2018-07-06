package tn.creativeteam.votingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class VotingRate extends AppCompatActivity {

    ListView lv;
    Integer[] photos={R.drawable.sergey_brin,R.drawable.bill_gates,
            R.drawable.mark_zuckerberg,R.drawable.larry_ellison};
    MyDb mdb;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_rate);

        lv=(ListView)findViewById(R.id.vote_list);

        mdb=new MyDb(this);
        db=mdb.getReadableDatabase();

        Cursor cur=db.rawQuery("select * from stars",null);

        String[] ids= new String[cur.getCount()];
        String[] names= new String[cur.getCount()];
        String[] votes= new String[cur.getCount()];
        int x=0;
        cur.moveToFirst();
        while (cur.isAfterLast()==false){
            ids[x]=cur.getString(0);
            names[x]=cur.getString(1);
            votes[x]=cur.getString(2);
            x++;
            cur.moveToNext();
        }

        CustomVote cv= new CustomVote(this,R.layout.my_row,ids,names,votes,photos);
        lv.setAdapter(cv);
    }
}
