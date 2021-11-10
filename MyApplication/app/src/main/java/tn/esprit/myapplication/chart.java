package tn.esprit.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import tn.esprit.myapplication.database.AppDataBase;

public class chart extends AppCompatActivity {
    TextView tvR, tvPython, tvCPP, tvJava;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);





    // Link those objects with their
    // respective id's that
    // we have given in .XML file
    tvR = findViewById(R.id.tvR);
    tvPython = findViewById(R.id.tvPython);
    tvCPP = findViewById(R.id.tvCPP);

    pieChart = findViewById(R.id.piechart);

    // Creating a method setData()
    // to set the text in text view and pie chart
    setData();
}

    private void setData()
    {
        AppDataBase db= AppDataBase.getDbInstance(getApplicationContext());

        // Set the percentage of language used
        tvPython.setText(Integer.toString(5));
      tvR .setText(Integer.toString(db.EvenementDao().getAllEvenement().size()));
        tvCPP.setText(Integer.toString(db.userEvenementDao().getAlluser().size()));


        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Nombre des Evenemnts",
                        Integer.parseInt(tvR.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Nombres de Participants",
                        Integer.parseInt(tvPython.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Nombres Total des Utilisateur",
                        Integer.parseInt(tvCPP.getText().toString()),
                        Color.parseColor("#EF5350")));


        // To animate the pie chart
        pieChart.startAnimation();
    }
}