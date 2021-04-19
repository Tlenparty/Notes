package com.geekbrains.notes;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class AddNoteFragment extends Fragment {

    private static final String CURRENT_NOTE = "Current note";
    EditText title, description;
    Button btn_add;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        title = view.findViewById(R.id.etTitle);
        description = view.findViewById(R.id.etDescription);
        btn_add = view.findViewById(R.id.btn_addNote);

        btn_add.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                DataBaseClass db = new DataBaseClass(getActivity());
                db.addNotes(title.getText().toString(), description.getText().toString());
                deleteAddFragment(view);
            } else {
                Toast.makeText(getActivity(), "Заполните поля", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteAddFragment(View view) {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        ListNotesFragment fragment = new ListNotesFragment();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
    }
}