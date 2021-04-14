package com.geekbrains.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.SearchView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    /*
1. Создайте список ваших заметок. +
2. Создайте карточку для элемента списка. +
3. Класс данных, созданный на шестом уроке, используйте для заполнения карточки списка.
* Создайте фрагмент для редактирования данных в конкретной карточке. Этот фрагмент пока можно
вызвать через основное меню.
   */

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMenus();
        addFragment();
    }

    private void addFragment() {
        FirstFragment firstFragment = new FirstFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.first_fragment_container,firstFragment);
        fragmentTransaction.commit();
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
                this,drawerLayout,toolbar,
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Определяем меню приложения (активити)
        getMenuInflater().inflate(R.menu.options_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search_bar); // поиск пункта меню поиска
        SearchView searchView = (SearchView) searchItem.getActionView(); // строка поиска
        searchView.setQueryHint("Search Notes Here");
        SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
            // реагирует на конец ввода поиска
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            // реагирует на нажатие каждой клавиши
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
        searchView.setOnQueryTextListener(listener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== R.id.delete_all_notes){
            deleteAllNotes();
        }
            return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        DataBaseClass db = new DataBaseClass(MainActivity.this);
        db.deleteAllNotes();
        recreate();
    }
}