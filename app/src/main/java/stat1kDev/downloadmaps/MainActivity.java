package stat1kDev.downloadmaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import static stat1kDev.downloadmaps.DeviceMemory.getAvailableInternalMemorySize;
import static stat1kDev.downloadmaps.DeviceMemory.getTotalInternalMemorySize;

public class MainActivity extends AppCompatActivity {

    private TextView textViewFreeMemory;
    private ProgressBar progressBarMemory;

    private double freeMemory;
    private double allMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextViewFreeMemory();
        initProgressBarMemory();

    }

    public void initTextViewFreeMemory() {
        textViewFreeMemory = findViewById(R.id.tv_free_memory);
        freeMemory = getAvailableInternalMemorySize();
        textViewFreeMemory.setText(getResources().getString(R.string.free_memory, freeMemory));
    }

    public void initProgressBarMemory() {
        progressBarMemory = findViewById(R.id.progress_bar_memory);
        freeMemory = getAvailableInternalMemorySize();
        allMemory = getTotalInternalMemorySize();

        progressBarMemory.setMax((int)allMemory);
        progressBarMemory.setProgress((int)allMemory - (int)freeMemory);

        progressBarMemory.setScaleY(4f);
    }


}
