package park.epam.com.parkit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.HashMap;

public class AdminActivity extends AppCompatActivity {

    private HashMap<Integer, String> hmap = new HashMap<Integer, String>();
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        this.loadParkedCar();
    }

    protected void parkEmployee(View view) {
        System.out.print("Park Given Employee and increment the count");
        EditText mEdit   = (EditText)findViewById(R.id.edit_text_empId);
        System.out.println((mEdit.getText().toString()));

        this.addValue(mEdit.getText().toString());
        mEdit.setText("");

    }
    private void loadParkedCar(){


        this.addValueInTable(hmap);
    }
    private void addValueInTable(HashMap<Integer, String> data) {
        table = (TableLayout)findViewById(R.id.tableLayout1);
        for(Integer key : data.keySet())
        {
            TableRow row=new TableRow(this);
            row.setPadding(0,0,0,20);
            String id = Integer.toString(key);
            String timestamp = data.get(key);
            TextView tv1=new TextView(this);
            tv1.setPadding(11, 5, 8, 2);
            tv1.setGravity(Gravity.CENTER);
            tv1.setTextColor(Color.WHITE);
            tv1.setTextSize(18);
            tv1.setText(id);
            TextView tv2=new TextView(this);
            tv2.setTextColor(Color.WHITE);
            tv2.setGravity(Gravity.CENTER);
            tv2.setTextSize(18);
            tv2.setText(timestamp);

            row.addView(tv1);
            row.addView(tv2);
            table.addView(row);
        }
    }
    private void addValue(String id) {
        table = (TableLayout)findViewById(R.id.tableLayout1);

            TableRow row=new TableRow(this);

            TextView tv1=new TextView(this);
            tv1.setPadding(11, 5, 8, 2);
            tv1.setGravity(Gravity.CENTER);
            tv1.setTextColor(Color.WHITE);
            tv1.setTextSize(18);
            tv1.setText(id);
            TextView tv2=new TextView(this);
            tv2.setTextColor(Color.WHITE);
            tv2.setGravity(Gravity.CENTER);
            tv2.setTextSize(18);
            tv2.setText(new Timestamp(System.currentTimeMillis()).toString());

            row.addView(tv1);
            row.addView(tv2);
            table.addView(row);
    }
}
