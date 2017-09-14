
package org.androidtown.hello;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static java.lang.Integer.toString;

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }
    public static final String DATABASE_NAME = "oursDB.db";
    public static final String CUSTOMER_TABLE_NAME = "customer";
    public static final String CUSTOMER_COLUMN_ID = "id";
    public static final String CUSTOMER_COLUMN_MEDICINE = "medicine";
    public static final String CUSTOMER_COLUMN_NICKNAME = "nickname";



    public void onCreate(SQLiteDatabase data) {
        data.execSQL("CREATE TABLE customer(id INTEGER PRIMARY KEY ," + " medicine TEXT," + " nickname TEXT);");

    }

    public void onUpgrade(SQLiteDatabase data, int oldVersion, int newVersion){
        data.execSQL("DROP TABLE IF EXISTS customer");
        onCreate(data);
    }

    public boolean insertCustomer(String medicine, String nickname) {
        String akak = "하하";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("medicine", medicine);
        db.insert("customer", null, contentValues);
        Log.w("하하값은???",akak );
        contentValues.put("nickname", nickname);
        db.insert("customer", null, contentValues);
        return true;

    }


    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from customer where id=" + id + " ", null);
        return res;

    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CUSTOMER_TABLE_NAME);
        return numRows;
    }

    public boolean updateCustomer(Integer id, String medicine, String nickname) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("medicine",medicine);
        contentValues.put("nickname",nickname);
        db.update("customer",contentValues,"id = ?", new String[] {Integer.toString(id)});
        return true;
    }

    public Integer deleteCustomer(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("customer","id = ?", new String[] {Integer.toString(id)});


    }

    public ArrayList getAllmedicine(){
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from customer",null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add( res.getString(res.getColumnIndex(CUSTOMER_COLUMN_MEDICINE)));
            array_list.add( res.getString(res.getColumnIndex(CUSTOMER_COLUMN_NICKNAME)));
            res.moveToNext();
        }
        return array_list;
    }
}