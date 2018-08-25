package park.epam.com.parkit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import park.epam.com.parkit.cached.EmployeeCached;
import park.epam.com.parkit.dto.EmployeeDetails;

public class MainActivity extends AppCompatActivity {

    String CHANNEL_ID = "epampark";
    Button btnShowLocation;
    Button b1;
    Button btnRegister;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("oncrear", "created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        gps = new GPSTracker(MainActivity.this);
        // check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            //Toast.makeText(getApplicationContext(), gps.distanceAll, Toast.LENGTH_LONG).show();
            addNotification(gps.distanceAll);
        } else {
            gps.showSettingsAlert();
        }

        timeCreator();
        EmployeeCached.details.setEmpId("123456");
        if (EmployeeCached.details.getEmpId() != null) {

            findViewById(R.id.open_register_button).setVisibility(View.GONE);

            btnRegister = (Button) findViewById(R.id.live_status);

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), LiveStatus.class);
                    startActivity(intent);
                }
            });

        } else {


            btnRegister = (Button) findViewById(R.id.open_register_button);

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    List<ProviderInfo> returnList = new ArrayList<>();
                    for (PackageInfo pack : getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS)) {
                        ProviderInfo[] providers = pack.providers;
                        if (providers != null) {
                            returnList.addAll(Arrays.asList(providers));
                        }
                    }

                    Log.d("providers", returnList.toString());


                    Intent intent = new Intent(getApplicationContext(), RegistraionActivity.class);
                    startActivity(intent);
                }
            });


        }


    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification(String message) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle("Time To reach Office")
                        .setContentText(message);

        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        Log.d("notification", "notify");
        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("start", "onstart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onresume", "resumed");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("stop", "stoped");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("destroy", "destroyed");
    }


    public void startService(View view) {
        startService(new Intent(getBaseContext(), BasicService.class));

    }

    public void stopService(View view) {

        stopService(new Intent(getBaseContext(), BasicService.class));

    }

    public void timeCreator() {
        Timer timerObj = new Timer();
        TimerTask timerTaskObj = new TimerTask() {
            public void run() {
                Log.d("calling ", "time bomb");
                ///  addNotification("notification from timer");
            }
        };
        timerObj.schedule(timerTaskObj, 0, 20000);

    }
}
