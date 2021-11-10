package tn.esprit.myapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EvenementDao {
    @Query("SELECT * FROM Evenement ")
    List<Evenement> getAllEvenement();

    @Insert
    void insertOne (Evenement event);
    @Delete
    void delete (Evenement user);
    @Query("Select * from Evenement where nomEvenement =(:name)" )
    Evenement selectEventByName (String name);
    @Update
    void updateEvent(Evenement evenement);

}
