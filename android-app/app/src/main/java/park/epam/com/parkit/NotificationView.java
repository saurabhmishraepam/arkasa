package park.epam.com.parkit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import park.epam.com.parkit.constants.AppConstant;
import park.epam.com.parkit.service.HttpService;

public class NotificationView extends Activity {

    HttpService httpService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        Log.d("on notification view", "..");
        Button yes_Button = (Button) findViewById(R.id.yes_button);
        Button no_Button = (Button) findViewById(R.id.no_button);
        httpService = new HttpService();

        yes_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("in Yes","");
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("in Yes","");
                        Map<String, Object> params = new HashMap<>();
                        params.put("empId", "123"); // Need to fetch employee id from cache;
                        params.put("isComing", true);
                        params.put("time", new Timestamp(new Date().getTime()));
                        httpService.sendPutRequest(AppConstant.APP_SERVER_URL + "notification", params);

                    }
                });
                showDialog();
            }
        });

        no_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("in NO","");
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("in No","");
                        Map<String, Object> params = new HashMap<>();
                        params.put("empId", "123"); // Need to fetch employee id from cache;
                        params.put("isComing", false);
                        params.put("time", new Timestamp(new Date().getTime()));
                        httpService.sendPutRequest(AppConstant.APP_SERVER_URL + "notification", params);

                    }
                });
                finish();
            }
        });
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();

//
    }

    public void showDialog(){
        AlertDialog.Builder ab = new AlertDialog.Builder(NotificationView.this);
        ab.setTitle("");
        ab.setMessage("Thanks for the response. Please click Yes for exit");
        ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                //if you want to kill app . from other then your main avtivity.(Launcher)
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(1);

                //if you want to finish just current activity

                finishAffinity();
               System.exit(0);
            }
        });
       /* ab.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });*/

        ab.show();
    }
    public void showMessage(){
        Toast.makeText(getBaseContext(), "Thanks for the response.", Toast.LENGTH_LONG).show();
    }
}