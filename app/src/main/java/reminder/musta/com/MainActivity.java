package reminder.musta.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionMenu floatingActionMenu = null;
    private FloatingActionButton fbStartAlarm = null;
    private FloatingActionButton fbCancelAlarm = null;
    private FloatingActionButton fbOthers = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.fab_menu);
        fbStartAlarm = (FloatingActionButton) findViewById(R.id.menu_item_start_alarm);
        fbCancelAlarm = (FloatingActionButton) findViewById(R.id.menu_item_cancel_alarm);
        fbOthers = (FloatingActionButton) findViewById(R.id.menu_item_others);
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
                floatingActionMenu.toggle(true);
                break;
            }
            case R.id.menu_item_cancel_alarm: {
                Log.i("cancel alarm", " clicked");
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
