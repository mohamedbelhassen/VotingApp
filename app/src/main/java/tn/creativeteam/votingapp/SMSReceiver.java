package tn.creativeteam.votingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    MyDb mdb;
    SQLiteDatabase db;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("err: ", "sms received");
        Toast.makeText(context,"sms received ",Toast.LENGTH_LONG).show();


        Bundle bundle=intent.getExtras();
        SmsMessage[]msgs=null;
        String messageReceived="";
        if(bundle!=null){
            //---retrieve the SMS message received---
            Object[]pdus=(Object[])bundle.get("pdus");
            msgs= new SmsMessage[pdus.length];
            for(int i=0;i<msgs.length;i++){
                msgs[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                messageReceived+=msgs[i].getMessageBody().toString();
                messageReceived+="\n";
            }

            mdb=new MyDb(context);
            db=mdb.getWritableDatabase();
            db.execSQL("insert into voting values(?,?)",
                    new String[]{messageReceived,msgs[0].getOriginatingAddress()});
            db.execSQL("update stars set votes=votes + 1 where id=?",
                    new String[]{messageReceived});
        }

    }
}
