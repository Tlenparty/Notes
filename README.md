# Notes

Android-приложение для заметок на Java. Заметки (заголовок и текст) хранятся
в локальной базе SQLite, список отображается в RecyclerView; заметку можно
добавить, отредактировать или удалить все разом.

## Возможности

- Список заметок с разделителями, загрузка из SQLite (`SQLiteOpenHelper`)
- Добавление заметки через FAB с проверкой заполненности полей
- Редактирование заметки по нажатию на карточку
- Удаление всех заметок из меню тулбара
- Navigation Drawer, отдельные разметки для альбомной ориентации
- Заготовки: экран настроек, контекстное меню карточки и поиск по заголовку
  (`Filterable`-адаптер) пока не доведены до конца

## Стек

| Область    | Технологии                                              |
|------------|---------------------------------------------------------|
| Язык       | Java 8                                                  |
| UI         | Fragments, RecyclerView, Material Components, DrawerLayout |
| Хранение   | SQLite (`SQLiteOpenHelper`)                             |
| Сборка     | Gradle 7.3.3, Android Gradle Plugin 7.0.4               |

## Структура

```
app/src/main/java/com/tlenparty/notes/
├── MainActivity.java         # тулбар, drawer, меню, контейнер фрагментов
├── ListNotesFragment.java    # список заметок
├── AddNoteFragment.java      # создание заметки
├── UpdateNotesFragment.java  # редактирование заметки
├── SettingsFragment.java     # экран настроек (заготовка)
├── NotesAdapter.java         # адаптер RecyclerView с фильтром
├── Note.java                 # модель (Parcelable)
└── DataBaseClass.java        # работа с SQLite
```

## Сборка

Требуется JDK 11–17 и Android SDK 30.

```bash
./gradlew assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`.
