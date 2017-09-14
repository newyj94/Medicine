package org.androidtown.hello;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class NewActivity3_display extends Activity {

    private DBHelper dbhp;
    TextView medicine;
    TextView usage;
    TextView nickname;
    Button a;

    int id = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new3_display);
       medicine = (TextView)findViewById(R.id.editTextMedicine);
        usage= (TextView)findViewById(R.id.editTextUsage);
        nickname = (TextView)findViewById(R.id.editTextNickName);
        dbhp = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Cursor rs = dbhp.getData(Value);
                id = Value;
                rs.moveToFirst();
                String m = rs.getString(rs.getColumnIndex(DBHelper.CUSTOMER_COLUMN_MEDICINE));
//                String n = rs.getString(rs.getColumnIndex(DBHelper.CUSTOMER_COLUMN_NICKNAME));
                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.savebtn);
                b.setVisibility(View.INVISIBLE);
                Button a = (Button)findViewById(R.id.alarmsavebtn);
                medicine.setText((CharSequence) m);
              //  nickname.setText((CharSequence) n);

            }
        }
    }

    public void insert(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (dbhp.updateCustomer(id, nickname.getText().toString(), medicine.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), NewActivity3main.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            }
                else {
                    if (dbhp.insertCustomer(nickname.getText().toString(),medicine.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "추가되었음", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "추가되지 않았음", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            }
        }
    public void delete(View view) {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                dbhp.deleteCustomer(id);
                Toast.makeText(getApplicationContext(), "삭제되었음", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "삭제되지 않았음", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void edit(View view) {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (dbhp.updateCustomer(id, nickname.getText().toString(), medicine.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

