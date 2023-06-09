package com.example.ownfb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ownfb.databinding.ListItemBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myAdapter extends FirebaseRecyclerAdapter<model,myAdapter.ViewHolder> {



    public myAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull model model) {

        holder.binding.tvRcOne.setText(model.getFirst());
        holder.binding.tvRcTwo.setText(model.getSecond());
        holder.binding.tvRcThree.setText(model.getThird());

        //Glide.with(holder.itemView.getContext()).load(model.getImageUrl()).into(holder.binding.iv2);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

       ListItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding=ListItemBinding.bind(itemView);


        }
    }



}
