package com.example.bongani.mobiapp;

import android.app.AlertDialog;
import android.database.Cursor;
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
    EditText editName, editSurname, editId,editAge;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editAge = (EditText)findViewById(R.id.editText_age);
        btnAddData = (Button)findViewById(R.id.button_add);
        editId = (EditText)findViewById(R.id.editText_id);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        deleteData();
    }

    public void deleteData()
    {

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(editId.getText().toString());
                if (deletedRows >0)
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
            }
        });



    }

    public void AddData()
    {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = myDb.insertData(editName.getText().toString(), editSurname.getText().toString(), editAge.getText().toString());
                if (isInserted == true)
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not inserted", Toast.LENGTH_LONG).show();
            }
        });

    }
    public  void UpdateData()
    {
        btnviewUpdate.setOnClickListener( new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      boolean isUpdate = myDb.updateData(editId.getText().toString(), editName.getText().toString(),
                              editSurname.getText().toString(), editAge.getText().toString());
                      if (isUpdate == true)
                          Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                      else
                          Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                  }
              }
            );
    }

    public  void viewAll() {
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("ERROR", "No Data Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("NAME :" + res.getString(1) + "\n");
                    buffer.append("SURNAME :" + res.getString(2) + "\n");
                    buffer.append("AGE :" + res.getString(3) + "\n\n");
                }
                // show data
               showMessage("Data", buffer.toString());
            }

        });
    }

    public void showMessage (String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

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
