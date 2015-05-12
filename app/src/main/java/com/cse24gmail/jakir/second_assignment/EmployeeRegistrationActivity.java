package com.cse24gmail.jakir.second_assignment;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EmployeeRegistrationActivity extends ActionBarActivity {
    private EditText etName;
    private EditText etTitle;
    private EditText etEmail;
    private EditText etAddress;
    private EditText etCity;
    private EditText etCountry;

    private Button btnSave;
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);

        initialization();
    }

    private void initialization() {
        etAddress= (EditText) findViewById(R.id.etAddress);
        etName= (EditText) findViewById(R.id.etName);
        etTitle= (EditText) findViewById(R.id.etTitle);
        etCity= (EditText) findViewById(R.id.etCity);
        etCountry= (EditText) findViewById(R.id.etCountry);
        etEmail= (EditText) findViewById(R.id.etEmail);

        dbAdapter=new DBAdapter(this);

        btnSave= (Button) findViewById(R.id.btnSave);
        btnSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        btnSave.getBackground().setAlpha(100);
                        break;
                    case MotionEvent.ACTION_UP:
                        btnSave.getBackground().setAlpha(255);
                        // insert data to database ..........
                        String name=etName.getText().toString().trim();
                        String title=etTitle.getText().toString().trim();
                        String email=etEmail.getText().toString().trim();
                        String address=etAddress.getText().toString().trim();
                        String city=etCity.getText().toString().trim();
                        String country=etCountry.getText().toString().trim();
                        if(name.isEmpty() || title.isEmpty() || email.isEmpty()
                                || address.isEmpty() || city.isEmpty()|| country.isEmpty()){
                            AlertDialog.Builder alert=new AlertDialog.Builder(EmployeeRegistrationActivity.this);
                            alert.setTitle("Oops");
                            alert.setMessage("Please Enter All Information Carefully!!");
                            alert.show();
                        }else{

                            dbAdapter.openDB();
                            Employee employee=new Employee(name,title,email,address,city,country);
                            long inserted=dbAdapter.addEmployee(employee);
                            if(inserted>=0){
                                Toast.makeText(getApplicationContext(), "Data Saved On Database Successfully!", Toast.LENGTH_SHORT).show();
                            }
                            Intent gotoMainActivity=new Intent(EmployeeRegistrationActivity.this,MainActivity.class);
                            startActivity(gotoMainActivity);
                            finish();

                        }

                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent takeUserToMainActivity=new Intent(EmployeeRegistrationActivity.this,MainActivity.class);
        startActivity(takeUserToMainActivity);
        finish();
    }
}
