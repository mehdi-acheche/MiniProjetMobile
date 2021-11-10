package tn.esprit.myapplication.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


    @Entity(tableName ="UserEvenement")
    public class UserEvenement implements Serializable {

        @PrimaryKey(autoGenerate = true)
        int uid;
        @ColumnInfo(name="username")
      public  String username;
        @ColumnInfo(name="password")
      public  String password;
        @ColumnInfo(name="email")
      public  String email;
        @ColumnInfo(name="phone")
        int phone;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }
    }
