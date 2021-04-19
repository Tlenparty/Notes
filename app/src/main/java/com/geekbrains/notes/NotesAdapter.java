package com.geekbrains.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<Note> noteList;
    List<Note> newList;
    private Fragment fragment;

    public NotesAdapter(Context context, List<Note> noteList, Fragment fragment) {
        this.context = context;
        this.noteList = noteList;
        this.fragment= fragment;
    }

    public NotesAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
        newList = new ArrayList<>(noteList);
    }

    // Вызывается столько сколько элементов на экране. 100 элементов, экран на 15, вызовется 15 раз
    @NonNull
    @Override
    public NotesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Надуваем элемент списка, передаем его во ViewHolder
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_layout, parent, false);
        return new MyViewHolder(view);
    }

    // Вызывается пока прокручивается список. Взаимодействие со списком
    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.MyViewHolder holder, int position) {
        ((Activity) context).registerForContextMenu(holder.layout);
        holder.onBind(position);
    }

    // Возвращение размера списка
    @Override
    public int getItemCount() {
        return noteList == null ? 0 : noteList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private final Filter exampleFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Note> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(newList);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Note note : newList) {
                    if (note.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(note);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            noteList.clear();
            noteList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        LinearLayout layout;
        int position;

        // В этот конструктор приходит View (элемент списка который из fxml)
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);
            layout = itemView.findViewById(R.id.note_layout);
            registerContextMenu(itemView);
           layout.setOnClickListener(v -> {
                AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                UpdateNotesFragment updateNotesFragment = new UpdateNotesFragment();
                Bundle bundle = createAndFillBundle(position);
                updateNotesFragment.setArguments(bundle);
                FragmentManager fm = activity.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.fragment_container,
                        updateNotesFragment, "UPDATE").commit();
            });
           // Обработчик нажатий Не работает (((
           layout.setOnLongClickListener(v -> {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                   itemView.showContextMenu(10, 10);
               }
               return true;
           });
        }

        public void onBind(int position) {
            this.position = position;
            title.setText(noteList.get(position).getTitle());
            description.setText(noteList.get(position).getDescription());
        }

        private Bundle createAndFillBundle(int position) {
            Note note = noteList.get(position);
            Bundle bundle = new Bundle();
            bundle.putString("title", note.getTitle());
            bundle.putString("description", note.getDescription());
            bundle.putString("id", note.getId());
            return bundle;
        }
    }

    private void registerContextMenu(View itemView) {
        if (fragment != null){
            fragment.registerForContextMenu(itemView);
        }
    }
}
