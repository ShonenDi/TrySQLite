package com.shonen.ukr.trysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        //Создание БД users
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        // запрос к БД (назва таблицы users, столбцы name и age
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER)");
        //добавление данных в БД users
        db.execSQL("INSERT INTO users VALUES ('Tom Smith', 23);");
        db.execSQL("INSERT INTO users VALUES ('John Dou', 31);");
        //получение данных из БД users, возвращает объект Cursor
        Cursor cursor = db.rawQuery("SELECT * FROM users;", null);
        TextView textView = findViewById(R.id.textView);
        // метод для перехода к первому объекту в БД для проверка на наличие данных в БД
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                textView.append("Name " + name + " Age " + age + "\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}
