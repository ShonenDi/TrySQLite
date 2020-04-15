package com.shonen.ukr.trysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView header;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor userCursor;
    private SimpleCursorAdapter simpleCursorAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = findViewById(R.id.header);
        listView = findViewById(R.id.list);

        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        //open connection
        db = databaseHelper.getReadableDatabase();

        // get data from DB in cursor values
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
        // set column will adding to ListView from Cursor
        String[] headers = new String[]{DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_YEAR};
        // create adapter, pass the cursor to adapter
        simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        header.setText("Elements funded " + userCursor.getCount());
        listView.setAdapter(simpleCursorAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //close all elements
        userCursor.close();
        db.close();
    }
}
