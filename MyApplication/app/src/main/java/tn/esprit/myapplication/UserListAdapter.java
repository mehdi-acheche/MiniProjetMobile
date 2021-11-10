package tn.esprit.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import tn.esprit.myapplication.database.UserEvenement;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {
    private Context context;
    private List<UserEvenement> userList;

    public UserListAdapter(Context context){
this.context=context;

    }
    public  void SetUserList(List<UserEvenement> userList){

        this.userList = userList;
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvFirstName.setText(this.userList.get(position).username);
        holder.tvLastName.setText(this.userList.get(position).email);

    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

       TextView tvFirstName;
       TextView tvLastName;

        public MyViewHolder(View view){
            super(view);

           tvFirstName= view.findViewById(R.id.tvFirstName);
           tvLastName= view.findViewById(R.id.tvLastName);

        }
    }
}
