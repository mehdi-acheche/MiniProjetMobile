package tn.esprit.myapplication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={UserEvenement.class,Evenement.class},version =8,exportSchema = false)
public abstract class AppDataBase  extends RoomDatabase {

    public abstract UserEvenementDao userEvenementDao();
    public abstract EvenementDao EvenementDao();

    private static AppDataBase INSTANCE;
    private static  String  DATABASE_NAME ="EventusApp";
    public static AppDataBase getDbInstance(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  INSTANCE;
    }



}
