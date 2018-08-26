package park.epam.com.parkit;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import park.epam.com.parkit.cached.EmployeeCached;
import park.epam.com.parkit.dto.DashboardDto;
import park.epam.com.parkit.dto.LocationRequestDto;
import park.epam.com.parkit.service.DashBoardService;
import park.epam.com.parkit.service.HttpService;

import static park.epam.com.parkit.constants.AppConstant.APP_SERVER_URL;

public class DashboardActivity extends AppCompatActivity {
    GPSTracker gps;
    HttpService httpService;
    DashBoardService dashBoardService;
    DashboardDto dashboardDto = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        gps = new GPSTracker(DashboardActivity.this);
        // check if GPS enabled
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            //Toast.makeText(getApplicationContext(), gps.distanceAll, Toast.LENGTH_LONG).show();
        //    addNotification(gps.distanceAll);
        } else {
            gps.showSettingsAlert();
        }
       String responseData = getIntent().getExtras().getString("liveData");
        Log.d("Dashboard data ",responseData);
        ObjectMapper mapper = new ObjectMapper();
        try {
             dashboardDto =   mapper.readValue(responseData,DashboardDto.class);

            Log.d("dto",dashboardDto.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (dashboardDto != null) {
                TextView available = (TextView) findViewById(R.id.available_slots);
                available.setText(String.valueOf(dashboardDto.getFloatersAvailable()));
                TextView occupied = (TextView) findViewById(R.id.occupied_slots);
                occupied.setText(String.valueOf(dashboardDto.getOccupiedSlots()));
                TextView onthewayUsers = (TextView) findViewById(R.id.ontheway_users);
                onthewayUsers.setText(String.valueOf(dashboardDto.getOnTheWay()));
                TextView rank = (TextView) findViewById(R.id.rank);
                rank.setText(String.valueOf(dashboardDto.getMyRank()));

            }
        }catch (Exception e){
            Log.e("excepton :",e.getMessage());
        }


        //callLiveStatusService();



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
                    dashboardDto = mapper1.readValue(response.toString(), DashboardDto.class);
                    Log.d("Response Code :", response.toString());
                   Log.d("dashboardDto", dashboardDto.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        );
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(dashboardDto != null) {
            Log.d("not null","");
            TextView available = (TextView) findViewById(R.id.available_slots);
            available.setText(dashboardDto.getFloatersAvailable());
        }
    }

}
