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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
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


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import park.epam.com.parkit.cached.EmployeeCached;
import park.epam.com.parkit.dto.DashboardDto;
import park.epam.com.parkit.dto.EmployeeDetails;
import park.epam.com.parkit.dto.LocationRequestDto;
import park.epam.com.parkit.park.epam.com.dao.EmplyeeProvider;
import park.epam.com.parkit.service.DashBoardService;
import park.epam.com.parkit.service.HttpService;

import static park.epam.com.parkit.constants.AppConstant.APP_SERVER_URL;
import static park.epam.com.parkit.constants.AppConstant.DATABASE_NAME;

public class MainActivity extends AppCompatActivity {

    String CHANNEL_ID = "epampark";
    Button btnShowLocation;
    Button b1;
    Button btnRegister;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;
    HttpService httpService;
    DashBoardService dashBoardService;
    DashboardDto dashboardDto = null;
    String liveResponse = null;

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



        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                EmployeeCached cached = new EmployeeCached();
                LocationRequestDto locationRequestDto = gps.locationRequestDto;
                ObjectMapper mapper = new ObjectMapper();

                Map<String,Object> map = new HashMap<>();
                if(locationRequestDto!=null){

                    String empId = locationRequestDto.getEmpId() == null ? cached.getEmployeeId() :  locationRequestDto.getEmpId();
                    map.put("empId",empId);
                    map.put("id",locationRequestDto.getId());

                    map.put("lastUpdated",new DateTime().getMillis());
                    map.put("isMovingToOffice",EmployeeCached.isComingToOffice);
                    map.put("timeToReachOffice",locationRequestDto.getTimeToReachOffice());
                    map.put("currentDistanceInKms",locationRequestDto.getCurrentDistanceInKms());
                    map.put("lat",locationRequestDto.getLat());
                    map.put("lang",locationRequestDto.getLang());

                }
                ObjectMapper mapper1 = new ObjectMapper();
                try {
                   String s =  mapper1.writeValueAsString(map);
                    System.out.println("s ="+s);

                httpService=new HttpService();
                Object  response = httpService.sendPutRequest(APP_SERVER_URL + "emp/getLiveStatus", map);
                liveResponse = response.toString();
                dashboardDto =  mapper1.readValue(response.toString(),DashboardDto.class);
                Log.d("Response Code :", response.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });



      // EmployeeCached.details.setEmpId("123456");

        if (EmployeeCached.details.getEmpId() != null) {

            findViewById(R.id.open_register_button).setVisibility(View.GONE);

            btnRegister = (Button) findViewById(R.id.live_status);

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    intent.putExtra("liveData",liveResponse);
                    startActivity(intent);
                }
            });

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    EmployeeCached cached = new EmployeeCached();
                    LocationRequestDto locationRequestDto = gps.locationRequestDto;
                    ObjectMapper mapper = new ObjectMapper();

                    Map<String,Object> map = new HashMap<>();
                    if(locationRequestDto!=null){

                        String empId = locationRequestDto.getEmpId() == null ? cached.getEmployeeId() :  locationRequestDto.getEmpId();
                        map.put("empId",empId);
                        map.put("id",locationRequestDto.getId());

                        map.put("lastUpdated",new DateTime().getMillis());
                        map.put("isMovingToOffice",EmployeeCached.isComingToOffice);
                        map.put("timeToReachOffice",locationRequestDto.getTimeToReachOffice());
                        map.put("currentDistanceInKms",locationRequestDto.getCurrentDistanceInKms());
                        map.put("lat",locationRequestDto.getLat());
                        map.put("lang",locationRequestDto.getLang());

                    }
                    ObjectMapper mapper1 = new ObjectMapper();
                    try {
                        String s =  mapper1.writeValueAsString(map);
                        System.out.println("s ="+s);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    httpService=new HttpService();
                    Object  response = httpService.sendPutRequest(APP_SERVER_URL + "emp/getLiveStatus", map);
                    Log.d("Response Code :", response.toString());

                }
            });



        } else {

            findViewById(R.id.live_status).setVisibility(View.GONE);
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
//        Button notify = (Button) findViewById(R.id.notify);
//
//        notify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                addNotificationPending("Are you coming to office or not");
//            }
//        });


        addNotificationPending("Araksa wants to know, are you coming to office?");
      /*  displayNotification("");
        displayNotification("");
        displayNotification("");
        displayNotification("");
        displayNotification("");
        displayNotification("");*/
//        gps = new GPSTracker(MainActivity.this);
//// check if GPS enabled
//        if (gps.canGetLocation()) {
//            double latitude = gps.getLatitude();
//            double longitude = gps.getLongitude();
//            //Toast.makeText(getApplicationContext(), gps.distanceAll, Toast.LENGTH_LONG).show();
//            addNotification(gps.distanceAll);
//
//
//
//        } else {
//            gps.showSettingsAlert();
//        }
//


//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                LocationRequestDto locationRequestDto = gps.locationRequestDto;
//                Map<String,String> map = new HashMap<>();
//                map.put("empId",locationRequestDto.getEmpId());
//                map.put("id",locationRequestDto.getId());
//                map.put("current",locationRequestDto.getCurrent().toString());
//
//                map.put("lastUpdated",new DateTime().getMillis()+"");
//                map.put("isMovingToOffice",EmployeeCached.isComingToOffice+"");
//                map.put("timeToReachOffice",locationRequestDto.getTimeToReachOffice()+"");
//                map.put("currentDistanceInKms",locationRequestDto.getCurrentDistanceInKms()+"");
//                httpService = new HttpService();
//                Object  response = httpService.sendPutRequest(APP_SERVER_URL + "emp/getLiveStatus", map);
//                Log.d("Response Code :", response.toString());
//
//            }
//        });
       // callLiveStatusService();
    }

    private void callLiveStatusService(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Calling live status service..");
                EmployeeCached cached = new EmployeeCached();
                LocationRequestDto locationRequestDto = gps.locationRequestDto;


                ObjectMapper mapper = new ObjectMapper();

                Map<String,Object> map = new HashMap<>();
                String empId = locationRequestDto.getEmpId() == null ? cached.getEmployeeId() :  locationRequestDto.getEmpId();
                map.put("empId",empId);
                map.put("id",locationRequestDto.getId());

                map.put("lastUpdated",new DateTime().getMillis());
                map.put("isMovingToOffice",EmployeeCached.isComingToOffice);
                map.put("timeToReachOffice",locationRequestDto.getTimeToReachOffice());
                map.put("currentDistanceInKms",locationRequestDto.getCurrentDistanceInKms());
                map.put("lat",locationRequestDto.getLat());
                map.put("lang",locationRequestDto.getLang());
                ObjectMapper mapper1 = new ObjectMapper();
                try {
                    String s =  mapper1.writeValueAsString(map);
                    System.out.println("s ="+s);

                httpService=new HttpService();
                dashBoardService = new DashBoardService();
                Object  response = httpService.sendPutRequest(APP_SERVER_URL + "emp/getLiveStatus", map);
                DashboardDto dashboardDto = mapper1.readValue(response.toString(), DashboardDto.class);
                dashBoardService.setDashboardDto(dashboardDto);
                Log.d("Response Code :", response.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

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
                        .setContentTitle(message)
                        .setContentText("");

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

    private void addNotificationPending(String message) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle(message)
                        .setContentText("");

        Intent resultIntent = new Intent(this, NotificationView.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

//        Intent resultIntent1 = new Intent(this, NoNotificationView.class);
//        PendingIntent pendingNoIntent = PendingIntent.getActivity(this, 2, resultIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Intent yesReceive = new Intent();
//        Bundle yesBundle = new Bundle();
//        yesBundle.putInt("userAnswer", 1);//This is the value I want to pass
//        yesReceive.putExtras(yesBundle);
//
//        Intent noReceive = new Intent();
//        Bundle noBundle = new Bundle();
//        noBundle.putInt("userAnswer", 1);//This is the value I want to pass
//        noReceive.putExtras(noBundle);
//
//        PendingIntent pendingIntentYes = PendingIntent.getBroadcast(this, 12345, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        PendingIntent pendingIntentNo = PendingIntent.getBroadcast(this, 12345, noReceive, PendingIntent.FLAG_UPDATE_CURRENT);

//        builder.addAction(R.drawable.notification, "Yes", pendingIntent);
//        builder.addAction(R.drawable.notification, "No", pendingNoIntent);


        builder.setContentIntent(pendingIntent);
        Log.d("notification", "notify");
        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    int numMessages = 1;

    protected void displayNotification(String message) {
        Log.i("Start", "notification");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle("Time To reach Office")
                        .setContentText(message);

        mBuilder.setContentTitle("New Message");
        mBuilder.setContentText("You've received new message.");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setSmallIcon(R.drawable.notification);

        /* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(++numMessages);

        /* Add Big View Specific Configuration */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        events[0] = new String("This is first line....");
        events[1] = new String("This is second line...");
        events[2] = new String("This is third line...");
        events[3] = new String("This is 4th line...");
        events[4] = new String("This is 5th line...");
        events[5] = new String("This is 6th line...");

        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("Big Title Details:");

        // Moves events into the big view
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        mBuilder.setStyle(inboxStyle);

        /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(this, NotificationView.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);

        /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /* notificationID allows you to update the notification later on. */
        manager.notify(0, mBuilder.build());
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

    public String getRegisterdEmp() {
        Log.d("Registerd emp :","");
        EmployeeCached employeeCached = new EmployeeCached();
        Log.d("Employee Id cache",employeeCached.getEmployeeId());
        return  employeeCached.getEmployeeId();
        /*String URL = "content://park.epam.com.parkit.park.epam.com.dao.EmplyeeProvider";
        Cursor cc = mDatabase.rawQuery("SELECT employeeId from employees limit 1",null);
        cc.moveToFirst();
        String empId =  cc.getString(0);
        Log.d("empId:",empId);
        Uri employee = Uri.parse(URL);
        Cursor c = managedQuery(employee, null, null, null, "name");

        if (c.moveToFirst()) {
            do {
                String s = c.getString(c.getColumnIndex(EmplyeeProvider._ID)) +
                        ", " + c.getString(c.getColumnIndex(EmplyeeProvider.NAME)) +
                        ", " + c.getString(c.getColumnIndex(EmplyeeProvider.EMPID));
                Log.d("emp value :", s);
                Toast.makeText(this, s,
                        Toast.LENGTH_SHORT).show();

            } while (c.moveToNext());
        }*/
    }

    public void timeCreator() {

        Timer timerObj = new Timer();
        TimerTask timerTaskObj = new TimerTask() {
            public void run() {
                Log.d("calling ", "time bomb");
                gps = new GPSTracker(MainActivity.this);

                String empId = getRegisterdEmp();
                Log.d("Employee Id :",empId);
                LocationRequestDto locationRequestDto = new LocationRequestDto();
                locationRequestDto.setEmpId(empId);
                gps.locationRequestDto = locationRequestDto;
                // check if GPS enabled
                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    //Toast.makeText(getApplicationContext(), gps.distanceAll, Toast.LENGTH_LONG).show();
                    addNotification(gps.distanceAll);
                } else {
                    gps.showSettingsAlert();
                }
            }
        };
        timerObj.schedule(timerTaskObj, 50000, 20000);

    }
    public void onClickToAdminActivity(View view){
        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
        startActivity(intent);
    }
}
