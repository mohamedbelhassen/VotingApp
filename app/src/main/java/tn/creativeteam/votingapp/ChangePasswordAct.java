package tn.creativeteam.votingapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordAct extends AppCompatActivity {

    EditText etNP, etCP;
    MyDb mdb;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etNP=(EditText)findViewById(R.id.etNewPassword);
        etCP=(EditText)findViewById(R.id.etConfirmPassword);

    }

    public void changePassword(View v) {
        if(etNP.getText().toString().equals(etCP.getText().toString())) {
            mdb = new MyDb(this);
            db = mdb.getWritableDatabase();
            db.execSQL("update users set password=? where username=?",
                    new String[]{etNP.getText().toString(), MyDb.username});
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"Password not match",Toast.LENGTH_LONG).show();
        }

    }
}
