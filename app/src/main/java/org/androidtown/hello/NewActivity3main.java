
package org.androidtown.hello;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;


public class NewActivity3main extends AppCompatActivity implements View.OnClickListener {

    TextView medicine;
    TextView nickname;

    int id = 0;
    ListView myListView;
    DBHelper dbhp;
    ArrayAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new3_main);

       dbhp = new DBHelper(this);
        ArrayList array_list = dbhp.getAllmedicine();

        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);

        myListView = (ListView) findViewById(R.id.listView1);
        myListView.setAdapter(mAdapter);

        //myListView.setOnItemClickListener(mItemClickListener);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

               int id = 0;
               Log.w("id값은들어가나???", Integer.toString(id));
               String item = (String) ((ListView) parent).getItemAtPosition(position);
               String[] strArray = item.split(" ");
               //  firstId = strArray[0];

               SQLiteDatabase sdb = dbhp.getReadableDatabase();
               Cursor cursor = sdb.rawQuery("Select id from customer where medicine = '" + item + "';", null);
               while (cursor.moveToNext()) {
                   id = cursor.getInt(0);
                   Log.w("idddd???", Integer.toString(id));
               }
               Bundle dataBundle = new Bundle();
               dataBundle.putInt("id", id);
               Intent intent = new Intent(getApplicationContext(), NewActivity3_display.class);
               intent.putExtras(dataBundle);

               startActivity(intent);

           }

       }
       );

    }




    protected void onResume() {
        super.onResume();
      //  mAdapter.clear();
       // mAdapter.addAll(dbhp.getAllmedicine());
       // mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("id",0);
        Intent intent = new Intent(getApplicationContext(), NewActivity3_display.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}


