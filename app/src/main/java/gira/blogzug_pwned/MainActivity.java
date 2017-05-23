package gira.blogzug_pwned;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;

public class MainActivity extends Activity {

	public static Intent serviceIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Intent intent = new Intent(this, POSTService.class);
		final PendingIntent pending = PendingIntent.getService(this, 0, intent, 0);
		final AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pending);
		long interval = 610000;
		alarm.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), interval, pending);

		serviceIntent = new Intent(this, POSTService.class);
		startService(serviceIntent);

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Notification noti = new Notification.Builder(this)
				.setContentTitle("BlogZug - Pwned")
				.setContentText("Updating blogzug.php every 610 seconds!")
				.setSmallIcon(R.drawable.ic_sent).build();
		noti.flags = noti.flags | Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH | Notification.FLAG_FOREGROUND_SERVICE;
		notificationManager.notify(0, noti);
	}
}
