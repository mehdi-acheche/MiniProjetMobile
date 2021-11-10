package tn.esprit.myapplication;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.myapplication.database.Evenement;

public class EvenementAdapter extends RecyclerView.Adapter<EvenementAdapter.EvenementViewHolder>{
    private List<Evenement> evenement;
    private EvenementListener  evenementlistener;

    public EvenementAdapter(List<Evenement> evenement, EvenementListener evenementlistener) {
        this.evenement = evenement;
       this.evenementlistener = evenementlistener;
    }

    @NonNull
    @Override
    public EvenementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EvenementViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.activity_view_content_layout,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull EvenementViewHolder holder, int position) {
holder.bindEvenemnt(evenement.get(position));
    }

    @Override
    public int getItemCount() {
        return evenement.size();
    }
public Evenement getSlectedEvenement(){
        Evenement selectedEvenement=new Evenement();
        for(Evenement Evenement:evenement){
            if(Evenement.isSelected){
                selectedEvenement=Evenement;
            }
        }
        return selectedEvenement;
}
    class EvenementViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layoutEvenement;
        View viewBackground;
        RoundedImageView imageEvenement;
        TextView EvenementName, textCreatedby, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        public EvenementViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutEvenement = itemView.findViewById(R.id.layoutEvenement);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imageEvenement = itemView.findViewById(R.id.imageEvenement);
            EvenementName = itemView.findViewById(R.id.NomEvenement);
            textCreatedby = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.TextSory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);

        }
            void bindEvenemnt(final Evenement evenement){
                EvenementName.setText(evenement.getNameEvenement());
              // textCreatedby.setText(evenement.getNombresEvenement());
                textStory.setText(evenement.getDescriptionEvenement());
                ratingBar.setRating(evenement.getRating());
                imageEvenement.setImageBitmap(BitmapFactory.decodeByteArray(evenement.getImage(),0,evenement.getImage().length));

                if (evenement.isSelected) {

                    viewBackground.setBackgroundResource(R.drawable.evenement_selected_backrground);
                    imageSelected.setVisibility(View.VISIBLE);
                } else {
                    viewBackground.setBackgroundResource(R.drawable.evenement_background);
                    imageSelected.setVisibility(View.GONE);
                }
                layoutEvenement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(evenement.isSelected){
                            viewBackground.setBackgroundResource(R.drawable.evenement_background);
                            imageSelected.setVisibility(View.GONE);
                            evenement.isSelected= false;
                            if(!getSlectedEvenement().isSelected){
                                evenementlistener.onEvnementAction(false);

                            }

                        }else{
                            viewBackground.setBackgroundResource(R.drawable.evenement_selected_backrground);
                            imageSelected.setVisibility(View.VISIBLE);
                            evenement.isSelected=true;
                            evenementlistener.onEvnementAction(true);
                        }
                    }
                });
                // imageEvenement.setImageResource( evenement.image);
            }
        }




}
