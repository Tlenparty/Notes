package com.geekbrains.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateNotesFragment extends Fragment {

    EditText title, description;
    Button btn_updateNote;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        initButtonUpdate(view);
        initFields(view);
    }

    private void initFields(View view) {
        title = view.findViewById(R.id.title_update);
        description = view.findViewById(R.id.description_update);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String titleStr = bundle.getString("title");
            String descriptionStr = bundle.getString("description");
            id = bundle.getString("id");
            title.setText(titleStr);
            description.setText(descriptionStr);
        }
    }

    private void initButtonUpdate(View view) {
        btn_updateNote = view.findViewById(R.id.btn_updateNote);
        btn_updateNote.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                DataBaseClass db = new DataBaseClass(getActivity());
                db.updateNotes(title.getText().toString(), description.getText().toString(), id);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                ListNotesFragment fragment = new ListNotesFragment();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
            } else {
                Toast.makeText(getActivity(), "Оба поля обязательны!", Toast.LENGTH_LONG).show();
            }
        });
    }
}