package com.geekbrains.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

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
1. Подумайте о функционале вашего приложения заметок. Какие экраны там могут быть, помимо основного
со списком заметок? Как можно использовать меню и всплывающее меню в вашем приложении? Не обязательно
сразу пытаться реализовать весь этот функционал, достаточно создать макеты и структуру, а реализацию
пока заменить на заглушки или всплывающие уведомления (Toast). Используйте подход Single Activity для
отображения экранов.
В качестве примера: на главном экране приложения у вас список всех заметок, при нажатии на заметку
открывается экран с этой заметкой. В меню главного экрана у вас есть иконка поиска по заметкам и
сортировка.
В меню «Заметки» у вас есть иконки «Переслать» (или «Поделиться»), «Добавить ссылку или фотографию к
заметке».
2. Создайте боковое навигационное меню для своего приложения и добавьте туда хотя бы один экран,
например
«Настройки» или «О приложении».
3. * Создайте полноценный заголовок для NavigationDrawer’а. К примеру, аватарка пользователя,
его имя
и какая-то дополнительная информация.
   */

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFabButton();
        setActionFabButton();
        initMenus();
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

    private void setActionFabButton() {
        fab.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }

    private void initFabButton() {
        fab = findViewById(R.id.fab);
    }

}