package reminder.musta.com;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TimePicker alarmTimePicker = null;
    PendingIntent pendingIntent = null;
    AlarmManager alarmManager = null;
    private FloatingActionMenu floatingActionMenu = null;
    private FloatingActionButton fbStartAlarm = null;
    private FloatingActionButton fbCancelAlarm = null;
    private FloatingActionButton fbOthers = null;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimePicker = (TimePicker) findViewById(R.id.time_picker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.fab_menu);
        fbStartAlarm = (FloatingActionButton) findViewById(R.id.menu_item_start_alarm);
        fbCancelAlarm = (FloatingActionButton) findViewById(R.id.menu_item_cancel_alarm);
        fbOthers = (FloatingActionButton) findViewById(R.id.menu_item_others);
        floatingActionMenu.setClosedOnTouchOutside(true);
        floatingActionMenu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("floatingActionMenu", " clicked");
                if (floatingActionMenu.isOpened()) {
                    Log.i("floatingActionMenu", " opened");
                    Toast.makeText(getApplicationContext(), "fb Menu", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("floatingActionMenu", " closed");
                }
                floatingActionMenu.toggle(true);
            }
        });
        fbStartAlarm.setOnClickListener(this);
        fbCancelAlarm.setOnClickListener(this);
        fbOthers.setOnClickListener(this);

    }

    private void setAlarm(){
        Calendar calendar = Calendar.getInstance();
        if (Build.VERSION.SDK_INT>=23){
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());
        }else {
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
        }

        intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60, pendingIntent);
    }

    private void cancelAlarm(){
        Intent newIntent = new Intent(this, RingtonePlayingService.class);
        stopService(newIntent);

        intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.menu_item_start_alarm: {
                Log.i("start alarm", " clicked");
                setAlarm();
                floatingActionMenu.toggle(true);
                break;
            }
            case R.id.menu_item_cancel_alarm: {
                Log.i("cancel alarm", " clicked");
                cancelAlarm();
                floatingActionMenu.toggle(true);
                break;
            }
            case R.id.menu_item_others: {
                Log.i("others ", " clicked");
                floatingActionMenu.toggle(true);
                break;
            }
        }
    }
}
