package com.cse24gmail.jakir.second_assignment;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class EmployeesDetailsActivity extends ActionBarActivity {

    private TextView tvName;
    private TextView tvEmail;
    private TextView tvTitle;
    private TextView tvAddress;
    private TextView tvCity;
    private TextView tvCountry;

    ArrayList<Employee> employeeArrayList;
    DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees_details);

        initialization();
        retriveDatafromDatabase();
    }

    private void retriveDatafromDatabase() {
        Bundle extras=getIntent().getExtras();
        int empId=extras.getInt("id");

        employeeArrayList=dbAdapter.getEmployeeInfoById(empId);
        for(Employee employee:employeeArrayList){
            tvName.setText(employee.getName());
            tvEmail.setText(employee.getEmail());
            tvTitle.setText(employee.getTitle());
            tvAddress.setText(employee.getAddress());
            tvCity.setText(employee.getCity());
            tvCountry.setText(employee.getCountry());
        }
    }

    private void initialization() {
        tvName= (TextView) findViewById(R.id.tvNameInDetails);
        tvEmail= (TextView) findViewById(R.id.tvEmailInDetails);
        tvAddress= (TextView) findViewById(R.id.tvAddressInDetails);
        tvCity= (TextView) findViewById(R.id.tvCityInDetails);
        tvTitle= (TextView) findViewById(R.id.tvTitleInDetails);
        tvCountry= (TextView) findViewById(R.id.tvCountryInDetails);

        employeeArrayList=new ArrayList<Employee>();
        dbAdapter=new DBAdapter(this);

    }


}
