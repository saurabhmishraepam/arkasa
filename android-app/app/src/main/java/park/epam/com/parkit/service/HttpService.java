package park.epam.com.parkit.service;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONStringer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static park.epam.com.parkit.constants.AppConstant.APP_SERVER_URL;

public class HttpService {
    public Object sendPutRequest(String URL, Map<String, Object> params) {
        try {
            Log.d("URL:", URL);
            URL urlObj = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(20000);
            ObjectMapper mapper = new ObjectMapper();
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(mapper.writeValueAsString(params));
            wr.flush();
            wr.close();
            int responsecode = conn.getResponseCode();
            Log.d("Data pushed:", "responsecode:" + responsecode);
            String response = "";
            if (responsecode == 200) {
                DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());
                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = dataInputStream.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                response = sb.toString();
                Log.d("Response message :", response);
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Something went wrong...", "");
        return "";
    }

    public void callHttpService(Map<String, String> params) {
        Log.d("Calling", "Register httpservice");
        try {
            String url = APP_SERVER_URL + "emp/add";
            Log.d("URL:", url);
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(20000);
            //conn.getPermission();

            //  String paramsString = "{\"empId\":\"1234\", \"empName\":\"saurabh\", \"mobileNumber\":\"9985104433\", \"parkType\":\"DEDICATED\"}  ";
            ObjectMapper mapper = new ObjectMapper();

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(mapper.writeValueAsString(params));
            wr.flush();
            wr.close();
            int responsecode = conn.getResponseCode();
            Log.d("data posted :", "responsecode:" + responsecode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
