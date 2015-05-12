package com.cse24gmail.jakir.second_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ListView lvEmployeeList;
    private TextView tvAddNew;
    private ArrayList<Employee> employeeArrayList;
    private MyListViewAdapter adapter;
    private DBAdapter dbAdapter;
    private EditText etSearch;
    static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

    }

    private void initialization() {
        lvEmployeeList= (ListView) findViewById(R.id.lvEmployeeList);
        tvAddNew= (TextView) findViewById(R.id.tvAddNew);
        employeeArrayList=new ArrayList<Employee>();
        dbAdapter=new DBAdapter(this);
        etSearch= (EditText) findViewById(R.id.etSearch);

        employeeArrayList=dbAdapter.getEmployeeInfo();
        adapter=new MyListViewAdapter(this,employeeArrayList);
        lvEmployeeList.setAdapter(adapter);

        tvAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoEmployeeRegistrationForm=new Intent(MainActivity.this,EmployeeRegistrationActivity.class);
                gotoEmployeeRegistrationForm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gotoEmployeeRegistrationForm);
                finish();
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = s.toString().trim();
                employeeArrayList=dbAdapter.getEmployeeInfoByName(name);
                adapter=new MyListViewAdapter(MainActivity.this,employeeArrayList);
                lvEmployeeList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        if (id == R.id.action_add) {
            Intent gotoEmployeeRegistrationForm=new Intent(MainActivity.this,EmployeeRegistrationActivity.class);
            gotoEmployeeRegistrationForm.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(gotoEmployeeRegistrationForm);
            finish();
        }
        if(id==R.id.action_search){
            etSearch.setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }

}
