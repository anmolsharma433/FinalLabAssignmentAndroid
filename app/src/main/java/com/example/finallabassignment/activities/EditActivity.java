package com.example.finallabassignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finallabassignment.R;
import com.example.finallabassignment.constants.Constants;
import com.example.finallabassignment.database.AppDatabase;
import com.example.finallabassignment.database.AppExecutors;
import com.example.finallabassignment.model.Person;


public class EditActivity extends AppCompatActivity
{
    EditText fname, lastname, email, city, phoneNumber;
    Button button;
    int tempPersonId;
    Intent intent;
    private AppDatabase DB;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initViews();

        DB = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Person_Id))
        {
            button.setText("Update Contact");
            tempPersonId = intent.getIntExtra(Constants.UPDATE_Person_Id, -1);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Person person = DB.personDao().loadPersonById(tempPersonId);
                    settingListView(person);
                }
            }); }
    }



    private void initViews() {
        fname = findViewById(R.id.edit_firstname);
        lastname = findViewById(R.id.edit_lastname);
        email = findViewById(R.id.edit_email);
        city = findViewById(R.id.edit_city);
        phoneNumber = findViewById(R.id.edit_number);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButton();
            }
        });
    }

    public void saveButton()
    {
        final Person person = new Person(
                fname.getText().toString(),
                lastname.getText().toString(),
                phoneNumber.getText().toString(),
                email.getText().toString(),
                city.getText().toString());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra(Constants.UPDATE_Person_Id)) {
                    DB.personDao().insertPerson(person);
                } else {
                    person.setId(tempPersonId);
                    DB.personDao().updatePerson(person);
                }
                finish();
            }
        });
    }

    private void settingListView(Person person)
    {
        if (person == null) {
            return;
        }

        fname.setText(person.getName());
        lastname.setText(person.getlastname());
        phoneNumber.setText(person.getNumber());
        email.setText(person.getEmail());
        city.setText(person.getCity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
