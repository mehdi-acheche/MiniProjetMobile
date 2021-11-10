package tn.esprit.myapplication.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DataConverter {
    public static byte[] cinverImage2byteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,98,stream);
        return stream.toByteArray();
    }

    public static Bitmap converByteArray2Image(byte[] array){
        return BitmapFactory.decodeByteArray(array,0,array.length);



    }
}
