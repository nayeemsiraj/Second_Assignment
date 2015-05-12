package com.cse24gmail.jakir.second_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Jakir on 5/12/2015.
 */
public class DBAdapter {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DBAdapter(Context context){
        dbHelper=new DBHelper(context);
    }

    public void openDB(){
        database=dbHelper.getWritableDatabase();
    }
    public void closeDB(){
        dbHelper.close();
    }

    public long addEmployee(Employee employee){
        ContentValues values=new ContentValues();
        values.put(DBHelper.NAME_FIELD,employee.getName());
        values.put(DBHelper.TITLE_FIELD,employee.getTitle());
        values.put(DBHelper.EMAIL_FIELD,employee.getEmail());
        values.put(DBHelper.ADDRESS_FIELD,employee.getAddress());
        values.put(DBHelper.CITY_FIELD,employee.getCity());
        values.put(DBHelper.COUNTRY_FIELD,employee.getCountry());

        long inserted=database.insert(dbHelper.TABLE_EMPLOYEE,null,values);

        return inserted;
    }


    public ArrayList<Employee> getEmployeeInfo(){
        ArrayList<Employee> employees=new ArrayList<Employee>();
        database=dbHelper.getReadableDatabase();

        Cursor cursor=database.query(DBHelper.TABLE_EMPLOYEE, null, null, null, null, null, null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                String name=cursor.getString(cursor.getColumnIndex(DBHelper.NAME_FIELD));
                int id=Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.ID_FIELD)));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.EMAIL_FIELD));
                String title=cursor.getString(cursor.getColumnIndex(DBHelper.TITLE_FIELD));
                String address=cursor.getString(cursor.getColumnIndex(DBHelper.ADDRESS_FIELD));
                String city=cursor.getString(cursor.getColumnIndex(DBHelper.CITY_FIELD));
                String country=cursor.getString(cursor.getColumnIndex(DBHelper.COUNTRY_FIELD));

                Employee employee=new Employee(id,name,title,email,address,city,country);
                employees.add(employee);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return employees;
    }
    public ArrayList<Employee> getEmployeeInfoById(int empId){
        ArrayList<Employee> employees=new ArrayList<Employee>();
        database=dbHelper.getReadableDatabase();

        String queryy="select * from "+DBHelper.TABLE_EMPLOYEE+" where "+DBHelper.TABLE_EMPLOYEE+"."+DBHelper.ID_FIELD+" = "
                +empId;

        Cursor cursor=database.rawQuery(queryy,null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                String name=cursor.getString(cursor.getColumnIndex(DBHelper.NAME_FIELD));
                int id=Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.ID_FIELD)));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.EMAIL_FIELD));
                String title=cursor.getString(cursor.getColumnIndex(DBHelper.TITLE_FIELD));
                String address=cursor.getString(cursor.getColumnIndex(DBHelper.ADDRESS_FIELD));
                String city=cursor.getString(cursor.getColumnIndex(DBHelper.CITY_FIELD));
                String country=cursor.getString(cursor.getColumnIndex(DBHelper.COUNTRY_FIELD));

                Employee employee=new Employee(id,name,title,email,address,city,country);
                employees.add(employee);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return employees;
    }

    public ArrayList<Employee> getEmployeeInfoByName(String eName){
        ArrayList<Employee> employees=new ArrayList<Employee>();
        database=dbHelper.getReadableDatabase();

        String queryy="select * from "+DBHelper.TABLE_EMPLOYEE+" WHERE "+DBHelper.TABLE_EMPLOYEE+"."+DBHelper.NAME_FIELD+" LIKE '%"+eName+"%'";


        Cursor cursor=database.rawQuery(queryy,null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                String name=cursor.getString(cursor.getColumnIndex(DBHelper.NAME_FIELD));
                int id=Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.ID_FIELD)));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.EMAIL_FIELD));
                String title=cursor.getString(cursor.getColumnIndex(DBHelper.TITLE_FIELD));
                String address=cursor.getString(cursor.getColumnIndex(DBHelper.ADDRESS_FIELD));
                String city=cursor.getString(cursor.getColumnIndex(DBHelper.CITY_FIELD));
                String country=cursor.getString(cursor.getColumnIndex(DBHelper.COUNTRY_FIELD));

                Employee employee=new Employee(id,name,title,email,address,city,country);
                employees.add(employee);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return employees;
    }


    public void delete(long id) {
        database=dbHelper.getReadableDatabase();
        database.delete(DBHelper.TABLE_EMPLOYEE, DBHelper.ID_FIELD + "=?",
                new String[] { String.valueOf(id) });

        database.close();
    }

    public void allDelete() {
        database=dbHelper.getReadableDatabase();
        database.delete(dbHelper.TABLE_EMPLOYEE, null, null);
        database.close();
    }



}
