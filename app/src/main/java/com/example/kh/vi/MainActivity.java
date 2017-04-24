package com.example.kh.vi;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kh.vi.Module.MyAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
SqliteDemo sqliteDemo;
    private EditText etid;
    private Button btninsert;
    private Button btnupdate;
    private Button btndelete;
    private Button btnloaddata;
    private TextView txtdata;
    private GridView grid;
    private String text;
    private ArrayList<Integer> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                text = String.valueOf(arrayList.get(position));
                Toast.makeText(MainActivity.this,text , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
       // txtdata = (TextView) findViewById(R.id.txt);
        grid = (GridView) findViewById(R.id.griddata);
        etid = (EditText) findViewById(R.id.etid);
        btninsert = (Button) findViewById(R.id.btninsert);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnloaddata = (Button) findViewById(R.id.btnloaddata);
        btninsert.setOnClickListener(OnClickListener);
        btnupdate.setOnClickListener(OnClickListener);
        btndelete.setOnClickListener(OnClickListener);
        btnloaddata.setOnClickListener(OnClickListener);
        sqliteDemo = new SqliteDemo(MainActivity.this);
    }


    private View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        switch(v.getId()){
            case R.id.btninsert: long a = sqliteDemo.insertdb(getValue(etid));
               // Toast.makeText(MainActivity.this,String.valueOf(a), Toast.LENGTH_SHORT).show();
                if(a!=-1)
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnloaddata:
                arrayList = new ArrayList<Integer>();

                Cursor cursor = sqliteDemo.loaddata();
                StringBuffer stringBuffer = new StringBuffer();
                if (cursor!=null)
                    cursor.moveToFirst();
                while (cursor.isAfterLast() == false) {
                    arrayList.add (cursor.getInt(cursor.getColumnIndex(sqliteDemo.ID)));
                    stringBuffer.append ("\n");
                    cursor.moveToNext();
                }
                MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.listdata, arrayList);
                grid.setAdapter(adapter);

                break;
            case R.id.btnupdate:
                if (getValue(etid)==-1)
                    return;
                long update=10;
                try {
                     update = sqliteDemo.updatedb(Integer.parseInt(text) , getValue(etid));
                }
                catch (Exception e){
                update = 0;
                }
                if(update ==0)
                Toast.makeText(MainActivity.this,"fail" , Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btndelete:
                if (getValue(etid)==-1)
                    return;
                long delete = sqliteDemo.deletedb(getValue(etid));
                if(delete==0)
                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                break;

        }
        }
    };

    private int getValue(EditText etid) {
        try{
      return  Integer.parseInt(etid.getText().toString().trim());}
        catch (Exception e){
            return -1;
        }
    }
//    private int getInt(EditText etid) {
//        try{
//            return  Integer.parseInt(etid.getText().toString().trim());}
//        catch (Exception e){
//            return -1;
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
    sqliteDemo.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sqliteDemo.closeDB();
    }
}
