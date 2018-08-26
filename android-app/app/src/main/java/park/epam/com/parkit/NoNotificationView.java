package park.epam.com.parkit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class NoNotificationView extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        Log.d("on NO view","..");

    }
}
