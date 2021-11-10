package tn.esprit.myapplication.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity(tableName ="Evenement")
public class Evenement {
    @PrimaryKey(autoGenerate = true)
    int uid;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB,name="imageEvenement")
    public byte[] image;
public Boolean isSelected =false;
    public float rating=4f;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @ColumnInfo(name="nomEvenement")
    public String NameEvenement;
    @ColumnInfo(name="lieu")
    public String lieuEvenement;
    @ColumnInfo(name="nombre")
    public Integer NombresEvenement;
    @ColumnInfo(name="description")
    public String DescriptionEvenement;

    public String getLieuEvenement() {
        return lieuEvenement;
    }

    public void setLieuEvenement(String lieuEvenement) {
        this.lieuEvenement = lieuEvenement;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }






    public String getNameEvenement() {
        return NameEvenement;
    }

    public void setNameEvenement(String nameEvenement) {
        NameEvenement = nameEvenement;
    }

    public Integer getNombresEvenement() {
        return NombresEvenement;
    }

    public void setNombresEvenement(Integer nombresEvenement) {
        NombresEvenement = nombresEvenement;
    }

    public String getDescriptionEvenement() {
        return DescriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        DescriptionEvenement = descriptionEvenement;
    }


    public Evenement() {
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public Evenement(byte[] image) {
        this.image = image;
    }

    public Evenement(byte[] image, String nameEvenement, String descriptionEvenement) {
        this.image = image;
        NameEvenement = nameEvenement;
        DescriptionEvenement = descriptionEvenement;
    }

    public Evenement(byte[] image, String nameEvenement, String lieu, Integer nombresEvenement, String descriptionEvenement) {
        this.image = image;
        this.NameEvenement = nameEvenement;
        this.NombresEvenement = nombresEvenement;
       this.DescriptionEvenement = descriptionEvenement;
       this.lieuEvenement=lieu;

    }
}
