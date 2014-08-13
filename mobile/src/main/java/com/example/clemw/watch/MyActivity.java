package com.example.clemw.watch;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MyActivity extends ActionBarActivity {

    private Button button;
    private int mId;
    private ToggleButton been;
    private ToggleButton save;
    private ToggleButton love;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        setUpButton();
        setUpRatings();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Context context = getApplicationContext();
            CharSequence text = "Settings toast!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpButton() {
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MyActivity.this)
                                .setSmallIcon(R.drawable.ic_launcher)
                                .setContentTitle("Marlowe")
                                .setContentText("$10 dollar off your next meal");
                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(MyActivity.this, MyActivity.class);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MyActivity.this);
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(MyActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(mId, mBuilder.build());

            }
        });
    }
    //Docs: http://developer.android.com/guide/topics/ui/controls/togglebutton.html
    private void setUpRatings() {
        been = (ToggleButton) findViewById(R.id.been);
        save = (ToggleButton) findViewById(R.id.save);
        love = (ToggleButton) findViewById(R.id.love);

        been.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    save.setVisibility(View.GONE);
                    love.setVisibility(View.VISIBLE);
                    save.setChecked(false);
                } else {
                    // The toggle is disabled
                    save.setVisibility(View.VISIBLE);
                    love.setVisibility(View.GONE);
                }
            }
        });

        love.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    been.setVisibility(View.GONE);
                } else {
                    // The toggle is disabled
                    been.setVisibility(View.VISIBLE);
                }
            }
        });

        save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    been.setChecked(false);
                } else {
                    // The toggle is disabled
                }
            }
        });
    }
}
