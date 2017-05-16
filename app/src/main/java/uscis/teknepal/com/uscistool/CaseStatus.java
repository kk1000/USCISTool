package uscis.teknepal.com.uscistool;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.text.Html.fromHtml;

public class CaseStatus extends AppCompatActivity {

    private TextView mTextMessage;
    public String receipt = "";
    private static ExecutorService networkThread = Executors.newSingleThreadExecutor();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(getBaseContext(), Home.class);
                    //item.setItemIconTintList(ColorStateList colorStateList);
                    // Launch the Activity using the intent
                    startActivity(intentHome);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(getBaseContext(), CaseStatus.class);
                    // Launch the Activity using the intent
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    Intent notification = new Intent(getBaseContext(), Notification.class);
                    // Launch the Activity using the intent
                    startActivity(notification);
                    return true;
            }
            return false;
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_status);
        final Context context = this;
        boolean stat = isDataConnectionAvailable(this);
        Toast t = Toast.makeText(this, stat +"", Toast.LENGTH_LONG);
        t.show();



        TextView receiptTextView = (TextView) findViewById(R.id.txtcase_id);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String savedReceipt = preferences.getString("receipt", "");

        receiptTextView.setText(savedReceipt);

        setAlarm();

        //zzz mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button status = (Button) findViewById(R.id.button);

        status.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               // Button status = (Button) findViewById(R.id.button);
                TextView receiptTextView = (TextView) findViewById(R.id.txtcase_id);
               // TextView body = (TextView) findViewById(R.id.txtresult_body);
                TextView stat = (TextView) findViewById(R.id.txtresult_body);
                receipt = receiptTextView.getText().toString();

                //save the receipt number on a file. this way the number can be available to all of your app.
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("receipt",receipt);
                editor.apply();
                try  {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!receipt.equals("")) {
                    if ((receipt != null)) {
                        checkStatus();
                    } else {

                        stat.setText(R.string.emptyrecipt);
                    }
                } else {
                    stat.setText(R.string.emptyrecipt);
                }
            }
        });
    }

    private void checkStatus() {

        try {
            networkThread.submit(new Runnable() {
                @Override
                public void run() {
                    TextView body = (TextView) findViewById(R.id.txtresult_body);
                    try {
                        CaseTracker cs = new CaseTracker();
                        String[] status;
                        status = cs.check(receipt);
                        //title.setText(status[0]);
                        body.setText(fromHtml("<h2>"+status[0]+"</h2><br><p>"+status[1]+"</p>"));
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
    public static boolean isDataConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public void setAlarm() {
        GregorianCalendar cal = new GregorianCalendar();
        Intent alertIntent = new Intent(this, AlertReceiver.class);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                PendingIntent.getBroadcast(CaseStatus.this, 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));
    }
}
