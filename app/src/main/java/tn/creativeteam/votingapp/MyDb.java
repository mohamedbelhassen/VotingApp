package tn.creativeteam.votingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDb extends SQLiteOpenHelper {

    public static String username;
    public MyDb(Context context) {
        super(context, "voting.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username varchar(20),password varchar(20));");
        db.execSQL("insert into users values('admin','aden');");
        db.execSQL("create table stars(id number(2),name varchar(50), votes number(4));");

        db.execSQL("create table voting (id number(2),mobile varchar(20));");

        db.execSQL("insert into stars values (1,'Serjey Brin',0);");
        db.execSQL("insert into stars values (2,'Bill Gates',0);");
        db.execSQL("insert into stars values (3,'Mark Zuckerberg',0);");
        db.execSQL("insert into stars values (4,'Larry Ellison',0);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
