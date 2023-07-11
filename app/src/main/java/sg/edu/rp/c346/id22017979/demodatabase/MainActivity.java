package sg.edu.rp.c346.id22017979.demodatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> al = new ArrayList<String>();
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView lv;
    EditText etTask, etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        etTask = findViewById(R.id.etTask);
        etDate = findViewById(R.id.etDate);

        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aaTask);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etTask.getText().toString(), etDate.getText().toString());

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                al.clear();
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                ArrayList<DBHelper.Task> info = db.getTasks();
                db.close();

                String txt = "";
                String listTxt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                    listTxt += info.get(i);
                    aaTask.add(listTxt);
                    listTxt = "";
                }
                tvResults.setText(txt);

            }
        });


    }


}

