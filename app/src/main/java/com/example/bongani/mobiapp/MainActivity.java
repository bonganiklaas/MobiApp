package com.example.bongani.mobiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editAge;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editAge = (EditText)findViewById(R.id.editText_age);
        btnAddData = (Button)findViewById(R.id.button_add);
        AddData();
    }

    public void AddData()
    {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              boolean isInserted=  myDb.insertData(editName.getText().toString(), editSurname.getText().toString(), editAge.getText().toString());
                if(isInserted = true)
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not inserted", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
