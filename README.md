# Notes

An Android notes app written in Java. Notes (a title and text) are stored
in a local SQLite database and listed in a RecyclerView; a note can be
added, edited, or all notes deleted at once.

## Features

- A list of notes with dividers, loaded from SQLite (`SQLiteOpenHelper`)
- Adding a note via a FAB, with validation that the fields are filled in
- Editing a note by tapping its card
- Deleting all notes from the toolbar menu
- Navigation Drawer, separate layouts for landscape orientation
- Unfinished pieces: the settings screen, the card context menu, and search by title
  (a `Filterable` adapter) are not yet completed

## Tech stack

| Area     | Technologies                                            |
|----------|---------------------------------------------------------|
| Language | Java 8                                                  |
| UI       | Fragments, RecyclerView, Material Components, DrawerLayout |
| Storage  | SQLite (`SQLiteOpenHelper`)                             |
| Build    | Gradle 7.3.3, Android Gradle Plugin 7.0.4               |

## Project structure

```
app/src/main/java/com/tlenparty/notes/
├── MainActivity.java         # toolbar, drawer, menu, fragment container
├── ListNotesFragment.java    # list of notes
├── AddNoteFragment.java      # note creation
├── UpdateNotesFragment.java  # note editing
├── SettingsFragment.java     # settings screen (stub)
├── NotesAdapter.java         # RecyclerView adapter with a filter
├── Note.java                 # model (Parcelable)
└── DataBaseClass.java        # SQLite access
```

## Build

Requires JDK 11–17 and Android SDK 30.

```bash
./gradlew assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`.
