package com.geekbrains.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<Note> noteList;

    public MyAdapter(Context context,  List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    // Вызывается столько сколько элементов на экране. 100 элементов, экран на 15, вызовется 15 раз
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Надуваем элемент списка, передаем его во ViewHolder
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent,false);
        return new MyViewHolder(view);
    }

    // Вызывается пока прокручивается список
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.onBind(position);
        //holder.onUpdateNote(position); // для активити( а у нас фрагмент((
    }

    // Возвращение размера списка
    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        LinearLayout layout;
        // В этот конструктор приходит View (элемент списка который из fxml)
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);
            layout = itemView.findViewById(R.id.note_layout);
            layout.setOnClickListener(v -> {
                AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                UpdateNotesFragment updateNotesFragment = new UpdateNotesFragment();
                Intent intent = new Intent(context,SecondActivity.class);
                Bundle bundle = new Bundle();
                updateNotesFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.content,
                        updateNotesFragment, "UPDATE").commit(); // НЕ МОЖЕТ НАЙТИ ВЬЮ почему.. (((
                activity.startActivity(intent);


                //fragment.setArguments(getIntent().getExtras());
         /*       bundle.putString("title",noteList.get(position).getTitle());
                bundle.putString("description",noteList.get(position).getDescription());
                bundle.putString("id",noteList.get(position).getId());*/


              /*  Intent intent = new Intent(context,SecondActivity.class);
                intent.putExtra("title",noteList.get(position).getTitle());
                intent.putExtra("description",noteList.get(position).getDescription());
                intent.putExtra("id",noteList.get(position).getId());
                activity.startActivity(intent);*/
            });
        }

        public void onBind(int position){
            title.setText(noteList.get(position).getTitle());
            description.setText(noteList.get(position).getDescription());

        }

       /* public void onUpdateNote(int position) {

                AppCompatActivity activity = (AppCompatActivity) itemView.getContentDescription();
                Intent intent = new Intent(context,SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",noteList.get(position).getTitle());
                bundle.putString("description",noteList.get(position).getDescription());
                bundle.putString("id",noteList.get(position).getId());
                UpdateNotesFragment fragment = new UpdateNotesFragment();
                fragment.setArguments(bundle);
                context.startActivity(intent);


              *//*  Intent intent = new Intent(context,SecondActivity.class);
                intent.putExtra("title",noteList.get(position).getTitle());
                intent.putExtra("description",noteList.get(position).getDescription());
                intent.putExtra("id",noteList.get(position).getId());
                activity.startActivity(intent);*//*

        }*/
    }
}
