package tn.esprit.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.makeramen.roundedimageview.RoundedImageView;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.myapplication.database.Evenement;

public class listadapter  extends ArrayAdapter<Evenement> {

    private Context mcontext;
    View viewBackground;
    RoundedImageView imageEvenement;
    TextView EvenementName,textCreatedby,textStory;
    RatingBar ratingBar;
    ImageView imageSelected;

    int mresource;
            private static class ViewHolder{
        ImageView img;

                TextView countryNameView;
                TextView populationView;
            }
            public listadapter(Context context, int resource, List<Evenement> objects){
                super(context,resource,objects);
                mcontext=context;
                mresource=resource;
            }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        byte[] img = getItem(position).getImage();
        String name=getItem(position).getNameEvenement();
        String descipription=getItem(position).getDescriptionEvenement();
        Evenement event = new Evenement(img,name,descipription);
        View result;
        ViewHolder holder;
        if(convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            convertView = inflater.inflate(mresource,parent, false);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.imageEvenement);
            holder.countryNameView = (TextView) convertView.findViewById(R.id.NomEvenement);
            holder.populationView = (TextView) convertView.findViewById(R.id.textCreateBy);
            result = convertView;
            convertView.setTag(holder);
        }

        else{
            holder=(ViewHolder) convertView.getTag();
            result=convertView;

        }
      // Evenement country = this.l.get(position);
        holder.countryNameView.setText(event.getNameEvenement());
        holder.populationView.setText(event.getDescriptionEvenement());
        holder.img.setImageBitmap(BitmapFactory.decodeByteArray(event.getImage(),0,event.getImage().length));
        return convertView;
    }
}
