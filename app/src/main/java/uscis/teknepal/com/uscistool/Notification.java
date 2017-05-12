package uscis.teknepal.com.uscistool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.text.Html.fromHtml;

public class Notification extends AppCompatActivity {

    private static ExecutorService networkThread = Executors.newSingleThreadExecutor();
    String savedReceipt;
    Date date;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(Notification.this, Home.class);
                    // Launch the Activity using the intent
                    startActivity(intentHome);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(Notification.this, CaseStatus.class);
                    // Launch the Activity using the intent
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notification = new Intent(Notification.this, Notification.class);
                    // Launch the Activity using the intent
                    startActivity(notification);
                    return true;
            }
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        savedReceipt = preferences.getString("receipt", "");
        setContentView(R.layout.activity_notification);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("Case Status for " + savedReceipt);
        title.setTextColor(Color.RED);
        title.setTextSize(15);
        date = new Date();
        checkStatus();
        // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void checkStatus() {
        try {
            networkThread.submit(new Runnable() {
                @Override
                public void run() {
                    TextView body = (TextView) findViewById(R.id.one);
                    try {
                        CaseTracker cs = new CaseTracker();
                        String[] status;
                        status = cs.check(savedReceipt);

                        //title.setText(status[0]);
                        body.setText(fromHtml("<p>"+date+"</p><h4>" + status[0] + "</h4>"));
                        //body.setText(status[0] + "\n" + status[1]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
