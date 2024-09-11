package com.example.apackage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DataFetch extends AppCompatActivity {

    private TextView dataFetchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datafetch);

        // Find the TextView to display log messages
        dataFetchTextView = findViewById(R.id.dataFetchTextView);

        // Log the intent data for debugging
        Intent intent = getIntent();
        if (intent == null) {
            updateLog("Intent is null.");
            return;
        }

        Uri fileUri = intent.getData();
        if (fileUri == null) {
            updateLog("Uri is null. Intent: " + intent.toString());
            return;
        }

        updateLog("Uri received: " + fileUri.toString() + "\n");

        // Open and read the file from the URI
        try (InputStream inputStream = getContentResolver().openInputStream(fileUri)) {
            if (inputStream != null) {
                byte[] data = new byte[inputStream.available()];
                inputStream.read(data);
                String fileContent = new String(data, StandardCharsets.UTF_8);
                updateLog("File content: " + fileContent);
            } else {
                updateLog("Failed to open InputStream for URI: " + fileUri);
            }
        } catch (Exception e) {
            updateLog("Error reading file: " + e.getMessage());
        }
    }

    // Method to update the TextView with log messages
    private void updateLog(String message) {
        dataFetchTextView.append(message + "\n");
    }
}
