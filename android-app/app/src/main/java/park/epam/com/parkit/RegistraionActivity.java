package park.epam.com.parkit;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import park.epam.com.parkit.park.epam.com.dao.EmplyeeProvider;
import park.epam.com.parkit.service.HttpService;

import static park.epam.com.parkit.constants.AppConstant.APP_SERVER_URL;

public class RegistraionActivity extends AppCompatActivity {

    Map<String,String> map = new HashMap<>();
    HttpService httpService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraion);
        httpService = new HttpService();
    }
    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        String emp_name= ((EditText)findViewById(R.id.input_name)).getText().toString();
        values.put(EmplyeeProvider.NAME,
                emp_name);
        String emp_id = ((EditText)findViewById(R.id.input_eid)).getText().toString();

        values.put(EmplyeeProvider.EMPID,
                emp_id);
        String input_email = ((EditText)findViewById(R.id.input_email)).getText().toString();
        String input_mobile = ((EditText)findViewById(R.id.input_email)).getText().toString();
        Uri uri = getContentResolver().insert(
                EmplyeeProvider.CONTENT_URI, values);
        map.put("empId",emp_id);
        map.put("empName",emp_name);
        map.put("email",input_email);
        map.put("mobileNumber",input_mobile);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Object  response = httpService.sendPutRequest(APP_SERVER_URL + "emp/add", map);
                Log.d("Response Code :", response.toString());

            }
        });

        Toast.makeText(getBaseContext(),
                "Registration Successful..", Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieveEmployee(View view) {
        // Retrieve student records
        String URL = "content://park.epam.com.parkit.park.epam.com.dao.EmplyeeProvider";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(EmplyeeProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( EmplyeeProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( EmplyeeProvider.EMPID)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }

}
