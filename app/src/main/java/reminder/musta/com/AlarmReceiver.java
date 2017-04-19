package reminder.musta.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by musta on 4/20/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm set", Toast.LENGTH_SHORT).show();
        Intent newIntent = new Intent(context, RingtonePlayingService.class);
        context.startService(newIntent);
    }
}
