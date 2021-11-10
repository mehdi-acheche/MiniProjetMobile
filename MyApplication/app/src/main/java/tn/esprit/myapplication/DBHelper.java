package tn.esprit.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME ="eventusapp.db";

    public DBHelper( Context context) {
        super(context,"userEvent.db", null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table userEvent(id INTEGER primary key autoincrement,username TEXT  ,password TEXT,email TEXT,phone INTEGER )");
        MyDB.execSQL("create Table event(id INTEGER primary key autoincrement,eventname TEXT  ,description TEXT,nombre INTEGER,lieu TEXT,image TEXT )");



    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists userEvent");
        MyDB.execSQL("drop Table if exists event");
        onCreate(MyDB);
    }
    public Boolean inserData(String username, String password,int phone,String email)
    {
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("email",email);
        contentValues.put("phone",phone);





        long result=MyDB.insert("userEvent",null,contentValues);


if(result==-1 ) return false;
else
    return true;



    }

    public Boolean inserDataEvent(String eventname,String description,Integer nombre, String lieu,String image)
    {
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("eventname",eventname);
        contentValues.put("description",description);
        contentValues.put("nombre",nombre);
        contentValues.put("lieu",lieu);
        contentValues.put("image",image);

        long resulta=MyDB.insert("event",null,contentValues);
        if(resulta==-1) return false;
        else
            return true;





    }
     public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
         Cursor cursor = MyDB.rawQuery("Select * from userEvent where username = ?",new String[]{username});
         if(cursor.getCount()>0)
             return true;
         else
             return false;


     }


     public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from userEvent where username = ? and password = ?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
     }
}
