package com.shajaraapp.shajara;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.shajaraapp.shajara.database.DatabaseHandler;
import com.shajaraapp.shajara.model.Timings;

import java.util.Calendar;
import java.util.Date;

public class AlarmReceiverClass extends BroadcastReceiver {
    MainActivity mainActivity;
    DatabaseHandler db;
    SettingsActivity set;
    CalenderActivity calenderActivity;

    public void onReceive(Context context, Intent intent) {

        db = new DatabaseHandler(context);
        mainActivity = new MainActivity();
        set= new SettingsActivity();
        calenderActivity = new CalenderActivity();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (hour == 3) {
            db.addnewDate(new Timings(mainActivity.today, "0", "0", "0", "0", "0", "0", "0"));
        }
        if (hour == 4 ) {
            createFajrNotification(context);
        }
        if ((hour == 13) &&((day == Calendar.SUNDAY) || (day == Calendar.MONDAY) || (day == Calendar.TUESDAY) || (day == Calendar.WEDNESDAY) || (day == Calendar.THURSDAY) || (day == Calendar.SATURDAY))) {
            createZuhrNotification(context);
        }
        if (hour == 16 && min == 30){
            createAsrNotification(context);
        }
        if (hour == 18 && min == 50){
            createMagribNotification(context);
        }
        if (hour == 20 && min == 30){
            createIshaaNotification(context);
        }
        if (hour == 2 ) {
            createTahajjudNotification(context);
        }

        if ((day == Calendar.FRIDAY) && hour == 13) {

            createNotification(context);
        }

    }

    public void createNotification(Context context) {
        PendingIntent intent = PendingIntent.getActivity(context, 0, new Intent(context, SettingsActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("TIME FOR JUMMAH PRAYER ")
                .setContentText("start getting ready Now")
                .setSmallIcon(R.drawable.ic_toys_black_24dp);

        builder.setContentIntent(intent);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);

        NotificationManager NotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager.notify(0, builder.build());


    }

    public void createIshaaNotification(Context context) {
        PendingIntent intent = PendingIntent.getActivity(context, 5, new Intent(context, SettingsActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("TIME FOR ISHAA PRAYER ")
                .setContentText("start getting ready Now")
                .setSmallIcon(R.drawable.ic_toys_black_24dp);

        builder.setContentIntent(intent);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);

        NotificationManager NotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager.notify(5, builder.build());


    }

    public void createMagribNotification(Context context) {
        PendingIntent intent = PendingIntent.getActivity(context, 4, new Intent(context, SettingsActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("TIME FOR MAGRIB PRAYER ")
                .setContentText("start getting ready Now")
                .setSmallIcon(R.drawable.ic_toys_black_24dp);

        builder.setContentIntent(intent);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);

        NotificationManager NotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager.notify(4, builder.build());


    }

    public void createFajrNotification(Context context) {
        PendingIntent intent = PendingIntent.getActivity(context, 1, new Intent(context, SettingsActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("TIME FOR FAJR PRAYER ")
                .setContentText("start getting ready Now")
                .setSmallIcon(R.drawable.ic_toys_black_24dp);

        builder.setContentIntent(intent);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);

        NotificationManager NotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager.notify(1, builder.build());


    }

    public void createZuhrNotification(Context context) {
        PendingIntent intent = PendingIntent.getActivity(context, 2, new Intent(context, SettingsActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("TIME FOR DHUHUR PRAYER ")
                .setContentText("start getting ready Now")
                .setSmallIcon(R.drawable.ic_toys_black_24dp);

        builder.setContentIntent(intent);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);

        NotificationManager NotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager.notify(2, builder.build());


    }

    public void createAsrNotification(Context context) {
        PendingIntent intent = PendingIntent.getActivity(context, 3, new Intent(context, SettingsActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("TIME FOR ASR PRAYER ")
                .setContentText("start getting ready Now")
                .setSmallIcon(R.drawable.ic_toys_black_24dp);

        builder.setContentIntent(intent);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);

        NotificationManager NotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager.notify(3, builder.build());


    }

    public void createTahajjudNotification(Context context) {
        PendingIntent intent = PendingIntent.getActivity(context, 6, new Intent(context, SettingsActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("TIME FOR TAHAJJUD PRAYER ")
                .setContentText("start getting ready Now")
                .setSmallIcon(R.drawable.ic_toys_black_24dp);

        builder.setContentIntent(intent);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);

        NotificationManager NotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager.notify(6, builder.build());


    }

}
