package com.gdo.araksa.araksa;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpService extends Service {
    public HttpService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void callHttpService(){
        Log.d("Calling","Register httpservice");
        try {
            String url = "http://10.71.12.134:8080/araksa/emp/add";
            Log.d("URL:",url);
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.getPermission();

            String paramsString = "{\"empId\":\"1234\", \"empName\":\"saurabh\", \"mobileNumber\":\"9985104433\", \"parkType\":\"DEDICATED\"}  ";

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(paramsString);
            wr.flush();
            wr.close();
            int responsecode = conn.getResponseCode();
            Log.d("data posted :", "responsecode:" + responsecode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
