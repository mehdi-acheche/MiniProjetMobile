package tn.esprit.myapplication.database;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserEvenementDao {

    @Query("SELECT * FROM UserEvenement")
    List<UserEvenement> getAlluser();
    @Insert
   void insertOne (UserEvenement user);
    @Delete
    void delete (UserEvenement user);

    @Query("Select * from UserEvenement where username =(:username)and password=(:password)" )
    UserEvenement Login (String username,String password);
    @Query("Select * from UserEvenement where uid =(:idUser) ")
    UserEvenement getUser (int idUser);

    @Query("SELECT * FROM UserEvenement where email=(:email)")
    UserEvenement existe_email(String email);
    @Query("UPDATE  UserEvenement  set  email =:email,password =:password")
    void update_password(String password, String email);
    @Query("SELECT * FROM UserEvenement where   password =:password AND email =:email")
    UserEvenement find_User(String password, String email);



}
