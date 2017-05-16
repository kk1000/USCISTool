package uscis.teknepal.com.uscistool;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.Normalizer;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.text.Spanned;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.text.Html.fromHtml;

/**
 * Created by TekNepal on 5/15/17. Free
 */

public class AlertReceiver extends BroadcastReceiver {

    private static ExecutorService networkThread = Executors.newSingleThreadExecutor();
    String savedReceipt;
    Date date;
    Spanned stat;

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        savedReceipt = preferences.getString("receipt", "");
        Spanned statts = checkStatus();

        createNotification(context, "Case Updated", savedReceipt, "Alert");

    }

    public void createNotification(Context context, String s, String s1, String alert) {
        PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, Notification.class), 0);

        NotificationCompat.Builder nBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.uscis)
                .setContentTitle(s)
                .setTicker(alert)
                .setContentText(s1);

        nBuilder.setContentIntent(notificIntent);
        nBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        nBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, nBuilder.build());
    }
    private Spanned checkStatus() {
        try {
            networkThread.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        CaseTracker cs = new CaseTracker();
                        String[] status;
                        status = cs.check(savedReceipt);

                        //title.setText(status[0]);
                        stat = fromHtml("<p>"+date+"</p><h4>" + status[0] + "</h4>");
                        //body.setText(status[0] + "\n" + status[1]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return stat;
    }

}
