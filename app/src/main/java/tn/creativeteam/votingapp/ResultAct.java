package tn.creativeteam.votingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ResultAct extends AppCompatActivity {

    EditText et;

    MyDb mdb;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        et=(EditText)findViewById(R.id.etResult);

    }

    public void sendFR(View v) {
        mdb=new MyDb(this);
        db=mdb.getReadableDatabase();
        Cursor cur=db.rawQuery("select * from stars",null);
        cur.moveToFirst();
        String msg=et.getText().toString()+"\n";

        while(cur.isAfterLast()==false){
            msg+=cur.getString(1)+" has got "+cur.getString(2)+ "\n";
            cur.moveToNext();
        }
        Toast.makeText(this,"sms : "+msg,Toast.LENGTH_LONG).show();
        Cursor cur2=db.rawQuery("select mobile from voting",null);
        cur2.moveToFirst();
        SmsManager manager=SmsManager.getDefault();
        while (cur2.isAfterLast()==false){
            Toast.makeText(this,"sms sent to : "+cur2.getString(0),Toast.LENGTH_SHORT).show();
            //Please note that you have to test sending voting results on real Android device
            //if you test it from the emulator the following instruction will crash since emulator did not have SIM card
            manager.sendTextMessage(cur2.getString(0),null,msg,null,null);
            cur2.moveToNext();
        }
    }
}
