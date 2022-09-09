package com.geekbrains.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
   1 Обеспечьте хранение данных приложения через Firestore.
   * Организуйте аутентификацию пользователя через Google.
   ** Обеспечьте вход через социальную сеть ВКонтакте или любую аналогичную.
   */

    NotesAdapter myAdapter;
    List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMenus();
        setListNoteFragment();
    }

    private void initMenus() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        // Тумблер. Открытие закрытие тулбара
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // Для красивого открытия иконки
        // Обработка навигационного меню
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Toast.makeText(MainActivity.this, id, Toast.LENGTH_LONG).show();
            return true;
        });
    }

    private void setListNoteFragment() {
        ListNotesFragment fragment = new ListNotesFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Определяем меню приложения (активити)
        getMenuInflater().inflate(R.menu.options_menu, menu);
        noteList = new ArrayList<>();
        myAdapter = new NotesAdapter(this, noteList);
        MenuItem searchItem = menu.findItem(R.id.search_bar); // поиск пункта меню поиска
        SearchView searchView = (SearchView) searchItem.getActionView(); // строка поиска
        searchView.setQueryHint("Search Notes Here");
        SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText); // НЕ РАБОТАЕТ ПОИСК (((((9 почему?(
                return true;
            }
        };
        searchView.setOnQueryTextListener(listener);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_notes:
                deleteAllNotes();
                return  true;
            case R.id.settings:
                setSettingsFragment();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setSettingsFragment() {
        SettingsFragment fragment = new SettingsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
    }

    private void deleteAllNotes() {
        DataBaseClass db = new DataBaseClass(MainActivity.this);
        db.deleteAllNotes();
        recreate();
    }
}