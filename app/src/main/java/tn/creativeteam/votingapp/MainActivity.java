package tn.creativeteam.votingapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etU, etP;
    MyDb mdb;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //In Android 6 and above, we need to add the following runtime permissions check
        requestSmsReceivePermission();
        requestSmsSendPermission();

        etU=(EditText)findViewById(R.id.etUsername);
        etP=(EditText)findViewById(R.id.etPassword);
    }

    private void requestSmsReceivePermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }

    private void requestSmsSendPermission() {
        String permission = Manifest.permission.SEND_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 2);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("Permission", "SMS_RECEIVE permission has now been granted. Showing result.");
            } else {
                Log.i("Permission", "SMS_RECEIVE permission was NOT granted.");
            }
        }

        if(requestCode == 2){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("Permission", "SEND_SMS permission has now been granted. Showing result.");
            } else {
                Log.i("Permission", "SEND_SMS permission was NOT granted.");
            }
        }
    }

    public void login(View view) {
        mdb= new MyDb(this);
        db=mdb.getReadableDatabase();
        Cursor cur=db.rawQuery("select * from users where username=? and password=?",
                new String[]{etU.getText().toString(),etP.getText().toString()});
        if(cur.getCount()==0)
            Toast.makeText(this,"Invalid User", Toast.LENGTH_LONG).show();
        else{
            MyDb.username=etU.getText().toString();
            Intent i= new Intent(this, ProfileAct.class);
            startActivity(i);
        }

    }
}
